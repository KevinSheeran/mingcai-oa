/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.common.utils.TableUpdateLog.LogCompar;
import com.mingcai.edu.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;
import com.mingcai.edu.modules.sys.entity.User;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 非销售项目立项Entity
 * @author kun
 * @version 2019-03-27
 */
public class OaEosProUn extends DataEntity<OaEosProUn> {
	
	private static final long serialVersionUID = 1L;
	@LogCompar(name = "name")
	private String name;		// 项目名称
	private String flowId;		// 流程id
	@LogCompar(name = "proNumber")
	private String proNumber;		// 立项编号
	@LogCompar(name = "personLiableUser")
	private User personLiableUser;		// 项目负责人
	@LogCompar(name = "customerName")
	private String customerName;		// 外部对接单位
	@LogCompar(name = "customerUser")
	private String customerUser;		// 对接方负责人
	@LogCompar(name = "customerContactInformation")
	private String customerContactInformation;		// 对接负责人联系方式
	@LogCompar(name = "status")
	private Integer status=0;		// 立项状态（-1驳回，默认0,1申请中，2.完成）
	@LogCompar(name = "proType")
	private Integer proType=0;		// 项目类型
	@LogCompar(name = "proLocation")
	private String proLocation;		// 项目目标
	@LogCompar(name = "departmentId")
	private String departmentId;		// 费用归属部门id
	@LogCompar(name = "estimation")
	private String estimation;		// 费用估算
	@LogCompar(name = "workDate")
	private String workDate;		// 项目周期
	@LogCompar(name = "workload")
	private String workload;		// 内部工作量估算（人/天）
	@LogCompar(name = "proContent")
	private String proContent;		// 项目主要交付内容
	@LogCompar(name = "userIds")
	private String userIds;		// 支持人员ids
	private Office company;	// 归属部门
	private String userId;
	private Integer progress=0;
	private String users_names;
	private List<User> users;
	private OaEosFlowItem  flow;
	public String str_status;		// 立项状态（1.预立项，2.预立项申请，3.预立项申请完成，4.立项申请审核，5.立项申请审批，5.立项完成）
	public String apple_date;
	private  List<OaEosProStartItem> oaEosProStartItemList;
	private OaEosProTimetotal oaEosProTimetotal;
	private  OaWxExtendedSuper oaWxExtendedSuper;
	private Double realInputEstimation=0.0;
	private Double totalSum;  //项目花费总金额

	public Double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	public OaWxExtendedSuper getOaWxExtendedSuper() {
		return oaWxExtendedSuper;
	}

	public void setOaWxExtendedSuper(OaWxExtendedSuper oaWxExtendedSuper) {
		this.oaWxExtendedSuper = oaWxExtendedSuper;
	}

	public OaEosProUn() {
		super();
		super.tableName="OaEosProUn";
	}

	public OaEosProUn(String id){
		super(id);super.tableName="OaEosProUn";
	}

	public Double getRealInputEstimation() {
		return realInputEstimation;
	}

	public void setRealInputEstimation(Double realInputEstimation) {
		this.realInputEstimation = realInputEstimation;
	}

	public OaEosProTimetotal getOaEosProTimetotal() {
		return oaEosProTimetotal;
	}

	public void setOaEosProTimetotal(OaEosProTimetotal oaEosProTimetotal) {
		this.oaEosProTimetotal = oaEosProTimetotal;
	}

	public String getUserId() {
		return userId;
	}

	public Office getCompany() {
		return company;
	}

	public void setCompany(Office company) {
		this.company = company;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsers_names() {
		return users_names;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public void setUsers_names(String users_names) {
		this.users_names = users_names;
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

	@Length(min=0, max=200, message="项目名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="流程id长度必须介于 0 和 64 之间")
	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	@Length(min=0, max=200, message="立项编号长度必须介于 0 和 200 之间")
	public String getProNumber() {
		return proNumber;
	}

	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}
	
	public User getPersonLiableUser() {
		return personLiableUser;
	}

	public void setPersonLiableUser(User personLiableUser) {
		this.personLiableUser = personLiableUser;
	}
	
	@Length(min=0, max=200, message="外部对接单位长度必须介于 0 和 200 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=0, max=200, message="对接方负责人长度必须介于 0 和 200 之间")
	public String getCustomerUser() {
		return customerUser;
	}

	public void setCustomerUser(String customerUser) {
		this.customerUser = customerUser;
	}
	
	@Length(min=0, max=200, message="对接负责人联系方式长度必须介于 0 和 200 之间")
	public String getCustomerContactInformation() {
		return customerContactInformation;
	}

	public void setCustomerContactInformation(String customerContactInformation) {
		this.customerContactInformation = customerContactInformation;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getProType() {
		return proType;
	}

	public void setProType(Integer proType) {
		this.proType = proType;
	}

	@Length(min=0, max=2000, message="项目目标长度必须介于 0 和 2000 之间")
	public String getProLocation() {
		return proLocation;
	}

	public void setProLocation(String proLocation) {
		this.proLocation = proLocation;
	}
	
	@Length(min=0, max=64, message="费用归属部门id长度必须介于 0 和 64 之间")
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	@Length(min=0, max=64, message="费用估算长度必须介于 0 和 64 之间")
	public String getEstimation() {
		return estimation;
	}

	public void setEstimation(String estimation) {
		this.estimation = estimation;
	}
	
	@Length(min=0, max=64, message="项目周期长度必须介于 0 和 64 之间")
	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	@Length(min=0, max=64, message="内部工作量估算（人/天）长度必须介于 0 和 64 之间")
	public String getWorkload() {
		return workload;
	}

	public void setWorkload(String workload) {
		this.workload = workload;
	}
	
	@Length(min=0, max=2000, message="项目主要交付内容长度必须介于 0 和 2000 之间")
	public String getProContent() {
		return proContent;
	}

	public void setProContent(String proContent) {
		this.proContent = proContent;
	}
	
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
}