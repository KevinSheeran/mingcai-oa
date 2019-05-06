/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.dao.EaChartDao;
import com.mingcai.edu.modules.oa.entity.EaData;
import com.mingcai.edu.modules.oa.entity.OaProduct;
import com.mingcai.edu.modules.sys.entity.Office;
import com.mingcai.edu.modules.sys.entity.User;
import net.sf.json.JSONObject;
import org.activiti.engine.impl.util.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 报销统计Service
 *
 * @author hxy
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class EaChartService extends CrudService <EaChartDao, EaData> {


    /**
     * 获取所取属性及对应统计金额
     *
     * @param eaData
     */
    public JSONArray getData(EaData eaData) {
        List <Map <String, String>> mapList = null;
        eaData.setBeginDate(eaData.getBeginYear() + eaData.getBeginMonth() + eaData.getBeginDay());
        eaData.setEndDate(eaData.getEndYear() + eaData.getEndMonth() + eaData.getEndDay());
        if ("1".equals(eaData.getCate())) { // 项目
            mapList = this.getChartByPro(eaData);
        }else if ("2".equals(eaData.getCate())) { // 费用
            mapList = this.getChartByCate(eaData);
        }else if ("3".equals(eaData.getCate())) { // 部门
            mapList = this.getChartByDepart(eaData);
        }else if("4".equals(eaData.getCate())) { // 员工
            mapList = this.getChartByStaff(eaData);
        }else if("5".equals(eaData.getCate())) { // 年度
            mapList = this.getChartByYear(eaData);
        }else if("6".equals(eaData.getCate())) { // 月份
            mapList = this.getChartByMonth(eaData);
        }else if("7".equals(eaData.getCate())) { // 日期
            mapList = this.getChartByDay(eaData);
        }

        // 转化为json
        JSONArray json = new JSONArray();
        for (Map <String, String> map : mapList) {
            json.put(JSONObject.fromObject(map));
        }
        return json;
    }

    /**
     * 所有项目全报销统计
     *
     * @param eaData
     */
    private List <Map <String, String>> getChartByPro(EaData eaData) {
        if("1".equals(eaData.getType())){
            return dao.getChartByProType1(eaData);
        }else if("3".equals(eaData.getType())){
            return dao.getChartByProType3(eaData);
        }else if("4".equals(eaData.getType())){
            return dao.getChartByProType4(eaData);
        }
        return null;
    }

    /**
     * 按费用分类查询
     *
     * @param eaData
     */
    private List <Map <String, String>> getChartByCate(EaData eaData) {
        if("1".equals(eaData.getType())){
            return dao.getChartByCateType1(eaData);
        }else if("2".equals(eaData.getType())){
            return dao.getChartByCateType2(eaData);
        }else if("3".equals(eaData.getType())){
            return dao.getChartByCateType3(eaData);
        }else if("4".equals(eaData.getType())){
            return dao.getChartByCateType4(eaData);
        }
        return null;
    }

    /**
     * 按部门查询
     *
     * @param eaData
     */
    private List <Map <String, String>> getChartByDepart(EaData eaData) {
        if("1".equals(eaData.getType())){
            return dao.getChartByDepartType1(eaData);
        }else if("2".equals(eaData.getType())){
            return dao.getChartByDepartType2(eaData);
        }
        return null;
    }

    /**
     * 按员工查询
     *
     * @param eaData
     */
    private List <Map <String, String>> getChartByStaff(EaData eaData) {
        if("2".equals(eaData.getType())){
            return dao.getChartByStaffType2(eaData);
        }else if("3".equals(eaData.getType())){
            return dao.getChartByStaffType3(eaData);
        }
        return null;
    }
    /**
     * 按年度查询
     *
     * @param eaData
     */
    private List <Map <String, String>> getChartByYear(EaData eaData) {
        if("1".equals(eaData.getType())){
            return dao.getChartByYearType1(eaData);
        }else if("2".equals(eaData.getType())){
            return dao.getChartByYearType2(eaData);
        }else if("3".equals(eaData.getType())){
            return dao.getChartByYearType3(eaData);
        }else if("4".equals(eaData.getType())){
            return dao.getChartByYearType4(eaData);
        }
        return null;
    }
    /**
     * 按月份查询
     *
     * @param eaData
     */
    private List <Map <String, String>> getChartByMonth(EaData eaData) {
        if("1".equals(eaData.getType())){
            return dao.getChartByMonthType1(eaData);
        }else if("2".equals(eaData.getType())){
            return dao.getChartByMonthType2(eaData);
        }else if("3".equals(eaData.getType())){
            return dao.getChartByMonthType3(eaData);
        }else if("4".equals(eaData.getType())){
            return dao.getChartByMonthType4(eaData);
        }
        return null;
    }
    /**
     * 按日期查询
     *
     * @param eaData
     */
    private List <Map <String, String>> getChartByDay(EaData eaData) {
        if("1".equals(eaData.getType())){
            return dao.getChartByDayType1(eaData);
        }else if("2".equals(eaData.getType())){
            return dao.getChartByDayType2(eaData);
        }else if("3".equals(eaData.getType())){
            return dao.getChartByDayType3(eaData);
        }else if("4".equals(eaData.getType())){
            return dao.getChartByDayType4(eaData);
        }
        return null;
    }

    public List<OaProduct> getProList(){
        return dao.getProList();
    }
    public List<Office> getDepartList(){
        return dao.getDepartList();
    }
    public List<User> getStaffList(String depart){
        return dao.getStaffList(depart);
    }

}
