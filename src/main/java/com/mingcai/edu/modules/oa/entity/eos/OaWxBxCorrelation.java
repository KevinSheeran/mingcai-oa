/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 基本报销关联Entity
 * @author 李艺杰
 * @version 2019-04-10
 */
public class OaWxBxCorrelation extends DataEntity<OaWxBxCorrelation> {
	
	private static final long serialVersionUID = 1L;
	private String detailsId;		// 报销详情id
	private String stageId;		// 报销阶段id
	private String classificationId;		// 基本分类idid
	private String superId;
	private  OaWxExtended ex;
	private  OaEosProUn un;
	private  OaWxBasicClassificationOfReimbursement lx;
	private  OaWxStageOfReimbursement jd;
	private  OaWxBxCorrelationSuper oaWxBxCorrelationSuper;
	private  OaEosProItem oaEosProItem;
	private  OaEosProStartItem oaEosProStartItem;

	public OaEosProItem getOaEosProItem() {
		return oaEosProItem;
	}

	public void setOaEosProItem(OaEosProItem oaEosProItem) {
		this.oaEosProItem = oaEosProItem;
	}

	public OaEosProStartItem getOaEosProStartItem() {
		return oaEosProStartItem;
	}

	public void setOaEosProStartItem(OaEosProStartItem oaEosProStartItem) {
		this.oaEosProStartItem = oaEosProStartItem;
	}

	public OaWxBxCorrelationSuper getOaWxBxCorrelationSuper() {
		return oaWxBxCorrelationSuper;
	}

	public void setOaWxBxCorrelationSuper(OaWxBxCorrelationSuper oaWxBxCorrelationSuper) {
		this.oaWxBxCorrelationSuper = oaWxBxCorrelationSuper;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public OaWxExtended getEx() {
		return ex;
	}

	public void setEx(OaWxExtended ex) {
		this.ex = ex;
	}


	public OaEosProUn getUn() {
		return un;
	}

	public void setUn(OaEosProUn un) {
		this.un = un;
	}

	public OaWxBasicClassificationOfReimbursement getLx() {
		return lx;
	}

	public void setLx(OaWxBasicClassificationOfReimbursement lx) {
		this.lx = lx;
	}

	public OaWxStageOfReimbursement getJd() {
		return jd;
	}

	public void setJd(OaWxStageOfReimbursement jd) {
		this.jd = jd;
	}

	public OaWxBxCorrelation() {
		super();
	}

	public OaWxBxCorrelation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="报销详情id长度必须介于 0 和 64 之间")
	public String getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	@Length(min=0, max=64, message="报销阶段id长度必须介于 0 和 64 之间")
	public String getStageId() {
		return stageId;
	}

	public void setStageId(String stageId) {
		this.stageId = stageId;
	}


	@Length(min=0, max=64, message="基本分类idid长度必须介于 0 和 64 之间")
	public String getClassificationId() {
		return classificationId;
	}

	public void setClassificationId(String classificationId) {
		this.classificationId = classificationId;
	}

}