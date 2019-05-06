/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import com.mingcai.edu.common.persistence.ActEntity;

/**
 * 报销统计Entity
 *
 * @author hxy
 * @version 2017-12-12
 */
public class EaData extends ActEntity <EaData> {

    private static final long serialVersionUID = 1L;
    private String staff; // 员工
    private String staffName; // 员工名
    private String purpose; // 用途
    private String project; // 项目
    private String projectName; // 项目名
    private String depart; // 部门
    private String departName; // 部门名
    private String beginYear; // 开始年份
    private String beginMonth; // 开始月份
    private String beginDay; // 开始日期
    private String beginDate; // 开始时间
    private String endYear; // 结束年份
    private String endMonth; // 结束年份
    private String endDay; // 结束年份
    private String endDate; // 结束时间
    private String type; // 统计类型
    private String cate; // 统计属性
    private String order; // 排序
    private String chartType;// 图表形式
    private int limitNum; // 限制数量

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(String beginYear) {
        this.beginYear = beginYear;
    }

    public String getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(String beginMonth) {
        if (beginMonth.length() < 2) {
            beginMonth = "0" + beginMonth;
        }
        this.beginMonth = beginMonth;
    }

    public String getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(String beginDay) {
        if (beginDay.length() < 2) {
            beginDay = "0" + beginDay;
        }
        this.beginDay = beginDay;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        if (endMonth.length() < 2) {
            endMonth = "0" + endMonth;
        }
        this.endMonth = endMonth;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        if (endDay.length() < 2) {
            endDay = "0" + endDay;
        }
        this.endDay = endDay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public int getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(int limitNum) {
        this.limitNum = limitNum;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }
}