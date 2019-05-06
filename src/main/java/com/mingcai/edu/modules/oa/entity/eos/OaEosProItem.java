/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.modules.sys.entity.User;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 销售项目子项Entity
 * @author kun
 * @version 2019-03-08
 */
public class OaEosProItem extends DataEntity<OaEosProItem> {
	
	private static final long serialVersionUID = 1L;
	private String eosId;		// 项目id
	private String name;		// 名称
	private String proCycle;		// 周期
	private User personLiableUser;		// 负责人
	private String proContent;		// 项目主要交付内容
	private String estimatedAmount;		// 预估金额
	private String grossProfitMargin;		// 毛利率百分比
	private Double inputEstimation;		// 投入估算
	private Date workloadTime;		// 预估完成时间
	private String userIds;		// 支持人员ids
	private String users_names;
	private String eosZId;
	private List<OaWxExtended> oaWxExtendedList;


	public List<OaWxExtended> getOaWxExtendedList() {
		return oaWxExtendedList;
	}
	public void setOaWxExtendedList(List<OaWxExtended> oaWxExtendedList) {
		this.oaWxExtendedList = oaWxExtendedList;
	}

	public String getEosZId() {
		return eosZId;
	}

	public void setEosZId(String eosZId) {
		this.eosZId = eosZId;
	}

	public OaEosProItem() {
		super();
	}

	public String getUsers_names() {
		return users_names;
	}

	public void setUsers_names(String users_names) {
		this.users_names = users_names;
	}

	public OaEosProItem(String id){
		super(id);
	}

	public String getEosId() {
		return eosId;
	}

	public void setEosId(String eosId) {
		this.eosId = eosId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getProCycle() {
		return proCycle;
	}

	public void setProCycle(String proCycle) {
		this.proCycle = proCycle;
	}

	public User getPersonLiableUser() {
		return personLiableUser;
	}

	public void setPersonLiableUser(User personLiableUser) {
		this.personLiableUser = personLiableUser;
	}

	public String getProContent() {
		return proContent;
	}

	public void setProContent(String proContent) {
		this.proContent = proContent;
	}
	
	public String getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(String estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}
	
	public String getGrossProfitMargin() {
		return grossProfitMargin;
	}

	public void setGrossProfitMargin(String grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}
	
	public Double getInputEstimation() {
		return inputEstimation;
	}

	public void setInputEstimation(Double inputEstimation) {
		this.inputEstimation = inputEstimation;
	}
	public Date getWorkloadTime() {
		return workloadTime;
	}
	public void setWorkloadTime(Date workloadTime) {
		this.workloadTime = workloadTime;
	}
	
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
}