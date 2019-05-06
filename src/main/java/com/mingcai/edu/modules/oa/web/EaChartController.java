package com.mingcai.edu.modules.oa.web;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.utils.excel.ExportExcel;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.modules.oa.entity.*;
import com.mingcai.edu.modules.oa.service.EaChartService;
import com.mingcai.edu.modules.sys.entity.User;
import org.activiti.engine.impl.util.json.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 报销统计Controller
 *
 * @author hxy
 * @version 2017-12-21
 */
@Controller
@RequestMapping(value = "${adminPath}/finance/eaChart")
public class EaChartController extends BaseController {

    @Autowired
    private EaChartService eaChartService;

    public static List<EaExcelData> dataList = new ArrayList <EaExcelData>();

    @ModelAttribute
    public EaData get(EaData eaData) {
        if (eaData != null) {
            return eaData;
        }
        return new EaData();
    }

    /**
     * 报销统计
     */
    @RequiresPermissions("finance:eaChart:view")
    @RequestMapping(value = {""})
    public String eaChartView(Model model, EaData eaData) { // 公司报销统计
//        if(eaData.getLimitNum()==0){ // 限制数量
//            eaData.setLimitNum(10);
//        }
        if(eaData.getType()==null){ // 视角类型（1、公司；2、项目；3、部门；4、员工）
            eaData.setType("1");
        }
        if(eaData.getCate()==null){ // 统计类型（1、项目；2、费用；3、部门；）
            eaData.setCate("1");
        }
        if("1".equals(eaData.getType())) {
            if ("1".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", "明材项目报销统计");
                model.addAttribute("hidTotalNum", "项目数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            } else if ("2".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", "明材费用分类报销统计");
                model.addAttribute("hidTotalNum", "费用类别数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            } else if ("3".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", "明材部门报销统计");
                model.addAttribute("hidTotalNum", "参与部门：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("5".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", "明材年度报销统计");
                model.addAttribute("hidTotalNum", "年数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("6".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", "明材月度报销统计");
                model.addAttribute("hidTotalNum", "月数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("7".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", "明材日期统计");
                model.addAttribute("hidTotalNum", "天数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }
        }else if("2".equals(eaData.getType())) {
            if ("2".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getProjectName()+"费用分类报销统计");
                model.addAttribute("hidTotalNum", "费用类别数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            } else if ("3".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getProjectName()+"部门报销统计");
                model.addAttribute("hidTotalNum", "参与部门：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("4".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getProjectName()+"员工报销统计");
                model.addAttribute("hidTotalNum", "参与员工：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("5".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getProjectName()+"年度报销统计");
                model.addAttribute("hidTotalNum", "年数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("6".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getProjectName()+"月度报销统计");
                model.addAttribute("hidTotalNum", "月数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("7".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getProjectName()+"日期报销统计");
                model.addAttribute("hidTotalNum", "天数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }
        }else if("3".equals(eaData.getType())) {
            if ("1".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getDepartName()+"项目报销统计");
                model.addAttribute("hidTotalNum", "项目数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            } else if ("2".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getDepartName()+"费用分类报销统计");
                model.addAttribute("hidTotalNum", "费用类别数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("4".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getDepartName()+"员工报销统计");
                model.addAttribute("hidTotalNum", "参与员工：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("5".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getDepartName()+"年度报销统计");
                model.addAttribute("hidTotalNum", "年数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("6".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getDepartName()+"月度报销统计");
                model.addAttribute("hidTotalNum", "月数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("7".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getDepartName()+"日期报销统计");
                model.addAttribute("hidTotalNum", "天数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }
        }else if("4".equals(eaData.getType())) {
            if ("1".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getStaffName()+"项目报销统计");
                model.addAttribute("hidTotalNum", "项目数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            } else if ("2".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getStaffName()+"费用分类报销统计");
                model.addAttribute("hidTotalNum", "费用类别数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("5".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getStaffName()+"年度报销统计");
                model.addAttribute("hidTotalNum", "年数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("6".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getStaffName()+"月度报销统计");
                model.addAttribute("hidTotalNum", "月数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }else if ("7".equals(eaData.getCate())) {
                model.addAttribute("hidTitle", eaData.getStaffName()+"日期报销统计");
                model.addAttribute("hidTotalNum", "天数：");
                model.addAttribute("hidTotalMoney", "总金额：");
            }
        }
        if(eaData.getBeginYear()==null){ // 开始年份
            eaData.setBeginYear(DateUtils.getYear());
        }
        model.addAttribute("beginYear", eaData.getBeginYear());
        if(eaData.getEndYear()==null){ // 结束年份
            eaData.setEndYear(DateUtils.getYear());
        }
        model.addAttribute("endYear", eaData.getEndYear());
        if(eaData.getBeginMonth()==null){ // 开始月份
            eaData.setBeginMonth("1");
        }
        model.addAttribute("beginMonth", eaData.getBeginMonth());
        if(eaData.getEndMonth()==null){ // 结束月份
            eaData.setEndMonth(DateUtils.getMonth());
        }
        model.addAttribute("endMonth", eaData.getEndMonth());
        if(eaData.getBeginDay()==null){ // 开始日期
            eaData.setBeginDay("1");
        }
        model.addAttribute("beginDay", eaData.getBeginDay());
        if(eaData.getEndDay()==null){ // 结束日期
            eaData.setEndDay(DateUtils.getDay());
        }
        model.addAttribute("endDay", eaData.getEndDay());

        JSONArray mapList = eaChartService.getData(eaData);
        if (mapList != null && !"[]".equals(mapList.toString())) {
            net.sf.json.JSONArray objList = net.sf.json.JSONArray.fromObject(mapList.toString().replaceAll("\"","'"));
            this.dataList = (List<EaExcelData>)objList.toCollection(objList, EaExcelData.class);
            model.addAttribute("mapList", mapList);
        } else {
            model.addAttribute("message", "统计结果不存在");
        }
        model.addAttribute("eaData", eaData);
        model.addAttribute("proList",eaChartService.getProList());
        model.addAttribute("departList",eaChartService.getDepartList());
        if(eaData.getDepart() == null){
            model.addAttribute("staffList",eaChartService.getStaffList(eaChartService.getDepartList().get(0).getId()));
            eaData.setStaff(eaChartService.getStaffList(eaChartService.getDepartList().get(0).getId()).get(0).getId());
        }else{
            model.addAttribute("staffList",eaChartService.getStaffList(eaData.getDepart()));
        }

        return "modules/oa/eaChart";
    }

    /**
     * 获取员工
     */
    @RequestMapping(value = "getStaff")
    @ResponseBody
    public List<User> getStaff(String depart) { // 公司报销统计
        return eaChartService.getStaffList(depart);
    }

    /**
     * 导出Execel
     */
    @RequestMapping(value = "export")
    public String exportFile(String cate, String title,HttpServletResponse response) {
        try {
            String fileName = title + DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            if("1".equals(cate)){
                new ExportExcel(title, EaExcelPro.class).setDataList(dataList).write(response, fileName).dispose();
            }else if("2".equals(cate)){
                new ExportExcel(title, EaExcelCate.class).setDataList(dataList).write(response, fileName).dispose();
            }else if("3".equals(cate)){
                new ExportExcel(title, EaExcelPart.class).setDataList(dataList).write(response, fileName).dispose();
            }else if("4".equals(cate)){
                new ExportExcel(title, EaExcelPer.class).setDataList(dataList).write(response, fileName).dispose();
            }else if("5".equals(cate)){
                new ExportExcel(title, EaExcelYear.class).setDataList(dataList).write(response, fileName).dispose();
            }else if("6".equals(cate)){
                new ExportExcel(title, EaExcelMonth.class).setDataList(dataList).write(response, fileName).dispose();
            }else if("7".equals(cate)){
                new ExportExcel(title, EaExcelDay.class).setDataList(dataList).write(response, fileName).dispose();
            }
            return null;
        } catch (Exception e) {
        }
        return "redirect:" + adminPath + "/finance/eaChart";
    }

}
