/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 报销主表Entity
 * @author 李艺杰
 * @version 2019-04-11
 */
public class OaWxBxCorrelationSuper extends DataEntity<OaWxBxCorrelationSuper> {
	
	private static final long serialVersionUID = 1L;
	private String extendedSuperId;		// 大报销单id
	private String proId;		// 项目id
	private  OaEosPro pro;
	private  OaEosProUn un;
	private List<OaWxExtended> oaWxBxCorrelations;
	private List<Map> map;
	private  OaWxExtendedSuper oaWxExtendedSuper;

	private OaWxDepartment oaWxDepartment;

	public OaWxDepartment getOaWxDepartment() {
		return oaWxDepartment;
	}

	public void setOaWxDepartment(OaWxDepartment oaWxDepartment) {
		this.oaWxDepartment = oaWxDepartment;
	}

	public OaWxExtendedSuper getOaWxExtendedSuper() {
		return oaWxExtendedSuper;
	}

	public void setOaWxExtendedSuper(OaWxExtendedSuper oaWxExtendedSuper) {
		this.oaWxExtendedSuper = oaWxExtendedSuper;
	}

	public List<Map> getMap() {
		return map;
	}

	public void setMap(List<Map> map) {
		this.map = map;
	}

	public List<OaWxExtended> getOaWxBxCorrelations() {
		return oaWxBxCorrelations;
	}

	public void setOaWxBxCorrelations(List<OaWxExtended> oaWxBxCorrelations) {
		this.oaWxBxCorrelations = oaWxBxCorrelations;
	}

	public OaEosProUn getUn() {
		return un;
	}

	public void setUn(OaEosProUn un) {
		this.un = un;
	}


	public OaWxBxCorrelationSuper() {
		super();
	}

	public OaWxBxCorrelationSuper(String id){
		super(id);
	}

	@Length(min=0, max=64, message="大报销单id长度必须介于 0 和 64 之间")
	public String getExtendedSuperId() {
		return extendedSuperId;
	}

	public void setExtendedSuperId(String extendedSuperId) {
		this.extendedSuperId = extendedSuperId;
	}
	@Length(min=0, max=64, message="项目id长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public OaEosPro getPro() {
		return pro;
	}

	public void setPro(OaEosPro pro) {
		this.pro = pro;
	}

}