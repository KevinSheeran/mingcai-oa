/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 报销分类Entity
 * @author 李艺杰
 * @version 2019-04-10
 */
public class OaWxBasicClassificationOfReimbursement extends DataEntity<OaWxBasicClassificationOfReimbursement> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 编号
	private String name;		// 名称
	private OaWxExtended oaWxExtended;
	private  Double cost=0.0;

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public OaWxExtended getOaWxExtended() {
		return oaWxExtended;
	}

	public void setOaWxExtended(OaWxExtended oaWxExtended) {
		this.oaWxExtended = oaWxExtended;
	}

	public OaWxBasicClassificationOfReimbursement() {
		super();
	}

	public OaWxBasicClassificationOfReimbursement(String id){
		super(id);
	}

	@Length(min=0, max=200, message="编号长度必须介于 0 和 200 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=200, message="名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}