package com.mingcai.edu.common.utils.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class test {
    private static Logger logger = LoggerFactory.getLogger(test.class);
    public static boolean exportExcel(String fileName, String title, String[] headers, List<Map> dataset, String pattern)
    {
        boolean flag = false;
        Workbook workbook = null;
        if (fileName.endsWith("xlsx"))
        {
            workbook = new XSSFWorkbook();
        } else if (fileName.endsWith("xls"))
        {
            workbook = new HSSFWorkbook();
        } else
        {
            try
            {
                throw new Exception("invalid file name, should be xls or xlsx");
            } catch (Exception e)
            {
                logger.info("必须是xls或者xlsx结尾的文件.");
                e.printStackTrace();
            }

        }

        Sheet sheet = workbook.createSheet(title);
        CellStyle style = workbook.createCellStyle();

        // 列名
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++)
        {
            Cell cell = row.createCell(i);
            sheet.setColumnWidth(i, 5000);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            cell.setCellValue(headers[i]);
        }

        Iterator<Map> it = dataset.iterator();
        int index = 0;
        while (it.hasNext())
        {
            index++;
            row = sheet.createRow(index);

            Map map = it.next();
            logger.info(map.toString());
            Set<String> mapKey = (Set<String>)map.keySet();
            logger.info(mapKey.toString());
            Iterator<String> iterator = mapKey.iterator();
            logger.info(iterator.toString());
            int num  = 0;
            while(iterator.hasNext()){
                Cell cell = row.createCell(num);
                num++;
                String key = iterator.next();
                logger.info(key);
                Object obj = map.get(key);
                if (obj instanceof Date)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    cell.setCellValue(sdf.format(obj));
                } else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                } else if (obj instanceof Double)
                {
                    cell.setCellValue((Double) obj);
                } else
                {
                    cell.setCellValue((String) obj);
                }
            }
        }
        FileOutputStream fos;
        try
        {
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
            fos.close();
            flag = true;
        } catch (FileNotFoundException e)
        {
            logger.info("文件不存在");
            flag = false;
            e.printStackTrace();
        } catch (IOException e)
        {
            logger.info("文件写入错误");
            flag = false;
            e.printStackTrace();

        }
        return flag;
    }
}
