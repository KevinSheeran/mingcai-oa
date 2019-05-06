/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 非销售自编号Entity
 * @author 坤
 * @version 2019-04-10
 */
public class OaEosNumberUnInfo extends DataEntity<OaEosNumberUnInfo> {
	
	private static final long serialVersionUID = 1L;
	private String unNumberId;		// id编号
	private String itemNumber;		// 自编号
	
	public OaEosNumberUnInfo() {
		super();
	}

	public OaEosNumberUnInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="id编号长度必须介于 0 和 64 之间")
	public String getUnNumberId() {
		return unNumberId;
	}

	public void setUnNumberId(String unNumberId) {
		this.unNumberId = unNumberId;
	}
	
	@Length(min=0, max=64, message="自编号长度必须介于 0 和 64 之间")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
}