/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.common.utils.TableUpdateLog.LogCompar;
import org.hibernate.validator.constraints.Length;
import com.mingcai.edu.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 立项子项目Entity
 * @author kun
 * @version 2019-03-21
 */
public class OaEosProStartItem extends DataEntity<OaEosProStartItem> {
	
	private static final long serialVersionUID = 1L;
	private String eosId;		// 项目id
	@LogCompar(name = "code")
	private String code;		// 编号
	@LogCompar(name = "name")
	private String name;		// 名称
	@LogCompar(name = "personLiableUser")
	private User personLiableUser;		// 负责人
	@LogCompar(name = "proContent")
	private String proContent;		// 项目主要交付内容
	@LogCompar(name = "estimatedAmount")
	private Double estimatedAmount;		// 预算金额
	@LogCompar(name = "inputEstimation")
	private Double inputEstimation;		// 投入金额
	@LogCompar(name = "workloadTime")
	private Date workloadTime;		// 预估完成时间
	@LogCompar(name = "deviationTime")
	private String deviationTime;		// 预估偏差时间(天)
	@LogCompar(name = "actualTime")
	private Date actualTime;		// 实际完成时间
	@LogCompar(name = "status")
	private Integer status=0;		// 完成状态默认0,1.完成
	
	public OaEosProStartItem() {
		super();
		super.tableName="OaEosProStartItem";
	}

	public OaEosProStartItem(String id){
		super(id);
		super.tableName="OaEosProStartItem";
	}

	@Length(min=0, max=200, message="项目id长度必须介于 0 和 200 之间")
	public String getEosId() {
		return eosId;
	}

	public void setEosId(String eosId) {
		this.eosId = eosId;
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
	
	public Double getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(Double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
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
	
	public String getDeviationTime() {
		return deviationTime;
	}

	public void setDeviationTime(String deviationTime) {
		this.deviationTime = deviationTime;
	}
	
	public Date getActualTime() {
		return actualTime;
	}

	public void setActualTime(Date actualTime) {
		this.actualTime = actualTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}