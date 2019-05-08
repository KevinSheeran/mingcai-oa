/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import com.mingcai.edu.common.persistence.DataEntity;
import com.mingcai.edu.common.utils.excel.annotation.ExcelField;

/**
 * 报销Excel(员工)
 * @author 韩心远
 * @version 2018-09-17
 */
public class EaExcelPer extends DataEntity<EaExcelPer> {

    private static final long serialVersionUID = 1L;
    private String name;		// 员工
    private String value;		// 金额

    public EaExcelPer() {
        super();
    }

    public EaExcelPer(String id){
        super(id);
    }

    @ExcelField(title="员工", align=2, sort=2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelField(title="金额", align=2, sort=3)
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}