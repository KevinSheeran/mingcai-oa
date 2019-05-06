/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.EaData;
import com.mingcai.edu.modules.oa.entity.OaProduct;
import com.mingcai.edu.modules.sys.entity.Office;
import com.mingcai.edu.modules.sys.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 报销统计DAO接口
 * 
 * @author hxy
 * @version 2017-12-12
 */
@MyBatisDao
public interface EaChartDao extends CrudDao<EaData> {
    List<Map<String, String>> getChartByProType1(EaData eaData); // 根据项目查询(公司)
    List<Map<String, String>> getChartByCateType1(EaData eaData); // 根据费用分类查询(公司)
    List<Map<String, String>> getChartByDepartType1(EaData eaData); // 根据部门查询(公司)
    List<Map<String, String>> getChartByYearType1(EaData eaData); // 根据年度查询(公司)
    List<Map<String, String>> getChartByMonthType1(EaData eaData); // 根据月份查询(公司)
    List<Map<String, String>> getChartByDayType1(EaData eaData); // 根据日期查询(公司)
    List<Map<String, String>> getChartByCateType2(EaData eaData); // 根据费用分类查询(项目)
    List<Map<String, String>> getChartByDepartType2(EaData eaData); // 根据部门查询(项目)
    List<Map<String, String>> getChartByStaffType2(EaData eaData); // 根据员工查询(项目)
    List<Map<String, String>> getChartByYearType2(EaData eaData); // 根据年度查询(项目)
    List<Map<String, String>> getChartByMonthType2(EaData eaData); // 根据月份查询(项目)
    List<Map<String, String>> getChartByDayType2(EaData eaData); // 根据日期查询(项目)
    List<Map<String, String>> getChartByProType3(EaData eaData); // 根据项目查询(部门)
    List<Map<String, String>> getChartByCateType3(EaData eaData); // 根据费用分类查询(部门)
    List<Map<String, String>> getChartByStaffType3(EaData eaData); // 根据员工查询(部门)
    List<Map<String, String>> getChartByYearType3(EaData eaData); // 根据年度查询(部门)
    List<Map<String, String>> getChartByMonthType3(EaData eaData); // 根据月份查询(部门)
    List<Map<String, String>> getChartByDayType3(EaData eaData); // 根据日期查询(部门)
    List<Map<String, String>> getChartByProType4(EaData eaData); // 根据项目查询(员工)
    List<Map<String, String>> getChartByCateType4(EaData eaData); // 根据费用分类查询(员工)
    List<Map<String, String>> getChartByYearType4(EaData eaData); // 根据年度查询(员工)
    List<Map<String, String>> getChartByMonthType4(EaData eaData); // 根据月份查询(员工)
    List<Map<String, String>> getChartByDayType4(EaData eaData); // 根据日期查询(员工)
    List<OaProduct> getProList(); // 获取有报销金额的项目列表
    List<Office> getDepartList(); // 获取有报销金额的部门列表
    List<User> getStaffList(String depart); // 获取有员工列表
}