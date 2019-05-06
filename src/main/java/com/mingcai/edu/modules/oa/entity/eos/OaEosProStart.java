/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.common.utils.TableUpdateLog.LogCompar;
import org.hibernate.validator.constraints.Length;
import com.mingcai.edu.modules.sys.entity.User;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 立项启动Entity
 * @author 坤
 * @version 2019-03-21
 */
public class OaEosProStart extends DataEntity<OaEosProStart> {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String flowId;		// 流程id
	@LogCompar(name = "personLiableUser")
	private User personLiableUser;		// 项目经理
	@LogCompar(name = "contractAmount")
	private Double contractAmount;		// 合同金额
	@LogCompar(name = "estimatedAmount")
	private Double estimatedAmount;		// 预算金额
	@LogCompar(name = "grossProfitMargin")
	private Integer grossProfitMargin;		// 利润%
	@LogCompar(name = "status")
	private Integer status=0;		// 立项状态(默认0,1.申请中，2.通过.-1驳回)
	@LogCompar(name = "userIds")
	private String userIds;		// 支持人员ids
	private Integer budgetary;		// 预算变更默认0,1.变更
	private Integer deferred=0;		// 延期变更默认0,1.变更
	private Integer progress=0;		// 项目进展，0.按计划进行，1.比计划提前，2.落后计划
	private OaEosPro pro;
	private String userId;
	private String tuserIds;		// 支持人员ids
	private String users_names;
	private String planstatus;
	public String str_status;		// 立项状态（1.预立项，2.预立项申请，3.预立项申请完成，4.立项申请审核，5.立项申请审批，5.立项完成）
	public String apple_date;
	private List<User> users;
	private OaEosFlowItem  flow;
	private  List<OaEosProStartItem> oaEosProStartItemList;
	private Double realInputEstimation=0.0;
	@LogCompar(name = "settlement")
	private String settlement="0";
	private Double totalSum;  //项目花费总金额

	public Double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public OaEosPro getPro() {
		return pro;
	}

	public void setPro(OaEosPro pro) {
		this.pro = pro;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public OaEosFlowItem getFlow() {
		return flow;
	}

	public void setFlow(OaEosFlowItem flow) {
		this.flow = flow;
	}

	public List<OaEosProStartItem> getOaEosProStartItemList() {
		return oaEosProStartItemList;
	}

	public void setOaEosProStartItemList(List<OaEosProStartItem> oaEosProStartItemList) {
		this.oaEosProStartItemList = oaEosProStartItemList;
	}

	public Double getRealInputEstimation() {
		return realInputEstimation;
	}

	public void setRealInputEstimation(Double realInputEstimation) {
		this.realInputEstimation = realInputEstimation;
	}

	public String getTuserIds() {
		return tuserIds;
	}

	public void setTuserIds(String tuserIds) {
		this.tuserIds = tuserIds;
	}

	public String getPlanstatus() {
		return planstatus;
	}

	public void setPlanstatus(String planstatus) {
		this.planstatus = planstatus;
	}

	public String getUsers_names() {
		return users_names;
	}

	public void setUsers_names(String users_names) {
		this.users_names = users_names;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public OaEosProStart() {
		super();
		super.tableName="OaEosProStart";
	}

	public OaEosProStart(String id){
		super(id);
		super.tableName="OaEosProStart";
	}

	@Length(min=0, max=200, message="流程id长度必须介于 0 和 200 之间")
	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	public User getPersonLiableUser() {
		return personLiableUser;
	}

	public void setPersonLiableUser(User personLiableUser) {
		this.personLiableUser = personLiableUser;
	}
	
	public Double getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(Double contractAmount) {
		this.contractAmount = contractAmount;
	}
	
	public Double getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(Double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}
	
	public Integer getGrossProfitMargin() {
		return grossProfitMargin;
	}

	public void setGrossProfitMargin(Integer grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=3000, message="支持人员ids长度必须介于 0 和 3000 之间")
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	public Integer getBudgetary() {
		return budgetary;
	}

	public void setBudgetary(Integer budgetary) {
		this.budgetary = budgetary;
	}
	
	public Integer getDeferred() {
		return deferred;
	}

	public void setDeferred(Integer deferred) {
		this.deferred = deferred;
	}
	
	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}