/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 报销类目管理Entity
 * @author 江坤
 * @version 2017-12-12
 */
public class OaFinanceCategory extends DataEntity<OaFinanceCategory> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 类目编号
	private String name;		// 类目名称
	private String value;
	private String label;
	public OaFinanceCategory() {
		super();
	}

	public OaFinanceCategory(String id){
		super(id);
	}

	@Length(min=1, max=64, message="类目编号长度必须介于 1 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=1, max=100, message="类目名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}