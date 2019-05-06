/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 项目编号Entity
 * @author 李艺杰
 * @version 2019-04-03
 */
public class OaEosNumber extends DataEntity<OaEosNumber> {
	
	private static final long serialVersionUID = 1L;
	private String itemNumber;		// 项目编号
	private String itemNumberType;		// 编号类型
	private String correlationId;		// 关联id
	private OaEosPro pro;
	private OaEosProUn un;


	@Override
	public String toString() {
		return "OaEosNumber{" +
				"itemNumber='" + itemNumber + '\'' +
				", itemNumberType='" + itemNumberType + '\'' +
				", correlationId='" + correlationId + '\'' +
				", pro=" + pro +
				", un=" + un +
				'}';
	}

	public OaEosPro getPro() {
		return pro;
	}

	public void setPro(OaEosPro pro) {
		this.pro = pro;
	}

	public OaEosProUn getUn() {
		return un;
	}

	public void setUn(OaEosProUn un) {
		this.un = un;
	}

	public OaEosNumber() {
		super();
	}

	public OaEosNumber(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目编号长度必须介于 0 和 64 之间")
	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	@Length(min=0, max=64, message="编号类型长度必须介于 0 和 64 之间")
	public String getItemNumberType() {
		return itemNumberType;
	}

	public void setItemNumberType(String itemNumberType) {
		this.itemNumberType = itemNumberType;
	}
	
	@Length(min=0, max=64, message="关联id长度必须介于 0 和 64 之间")
	public String getCorrelationId() {
		return correlationId;
	}

	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	
}