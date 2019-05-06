/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 非销售编号Entity
 * @author 坤
 * @version 2019-04-10
 */
public class OaEosNumberUn extends DataEntity<OaEosNumberUn> {
	
	private static final long serialVersionUID = 1L;
	private String itemNumber;		// 项目编号
	private String checked="0";//当前编号是否为主编号

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public OaEosNumberUn() {
		super();
	}

	public OaEosNumberUn(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目编号长度必须介于 0 和 64 之间")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
}