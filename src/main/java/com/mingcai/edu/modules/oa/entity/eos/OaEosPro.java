/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.common.utils.TableUpdateLog.LogCompar;
import com.mingcai.edu.modules.oa.entity.user.OaUserProSettlement;
import org.hibernate.validator.constraints.Length;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 销售项目立项Entity
 * @author kun
 * @version 2019-03-08
 */
public class OaEosPro extends DataEntity<OaEosPro> implements Comparable<OaEosPro>{
	
	private static final long serialVersionUID = 1L;
	private String name;		// 项目名称
	@LogCompar(name = "paNumber")
	private String paNumber;		// 预立项编号
	@LogCompar(name = "proNumber")
	private String proNumber;		// 立项编号
	private User personLiableUser;		// 责任销售
	private String customerName;		// 客户名称
	private String customerUser;		// 客户方负责人
	private String customerContactInformation;		// 客户负责人联系方式
	private Integer status=1;		// 立项状态（1.预立项，2.预立项申请，3.预立项申请完成，4.立项申请审核，5.立项申请审批，5.立项完成）
	private String proType;		// 项目类型
	private String proLocation;		// 客户/项目定位(客户是否为行业领先地位，项目是否为标杆项目）
	private String proCapitalSource;		// 资金来源（1.全部落实，2.部分落实，3.未落实）
	private String proBudget;		// 资金预算（1.全部落实，2.部分落实，3.未落实）
	private String proCycle;		// 周期（1.已确定，2.不确定）
	private String proContent;		// 项目主要交付内容
	private String estimatedAmount;		// 项目预估金额
	private String grossProfitMargin;		// 毛利率百分比
	private String inputEstimation;		// 前期投入估算
	private String workload;		// 前期预估工作量（人/天）
	private String userIds;		// 支持人员ids
	private String toExamine;		// 管理，财务审核状态0,1完成
	private String approval;		// 审批状态0,1完成
	private String users_names;
	public String str_status;		// 立项状态（1.预立项，2.预立项申请，3.预立项申请完成，4.立项申请审核，5.立项申请审批，5.立项完成）
	public String apple_date;
	private String userId;
	private List<User> users;
	private OaEosFlowItem  flow;
	private  List<OaEosProItem> oaEosProItemList;
	private  List<OaEosProStartItem> oaEosProStartItemList;
	private Integer type;//1.预立项，2.立项，3.非销售
	private OaEosProTimetotal oaEosProTimetotal;
	private Double total=0.0;
	private Double realInputEstimation=0.0;
	private Double totalSum;  //项目花费总金额
	private OaEosProStart start;//立项项目
	private OaUserProSettlement oaUserProSettlement;//项目结算情况
	public Double getTotalSum() {
		return totalSum;
	}

	public OaUserProSettlement getOaUserProSettlement() {
		return oaUserProSettlement;
	}

	public void setOaUserProSettlement(OaUserProSettlement oaUserProSettlement) {
		this.oaUserProSettlement = oaUserProSettlement;
	}

	public void setTotalSum(Double totalSum) {
		this.totalSum = totalSum;
	}

	public List<OaEosProStartItem> getOaEosProStartItemList() {
		return oaEosProStartItemList;
	}

	public void setOaEosProStartItemList(List<OaEosProStartItem> oaEosProStartItemList) {
		this.oaEosProStartItemList = oaEosProStartItemList;
	}

	public OaEosProStart getStart() {
		return start;
	}

	public void setStart(OaEosProStart start) {
		this.start = start;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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

	public List<OaEosProItem> getOaEosProItemList() {
		return oaEosProItemList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setOaEosProItemList(List<OaEosProItem> oaEosProItemList) {
		this.oaEosProItemList = oaEosProItemList;
	}

	public OaEosFlowItem getFlow() {
		return flow;
	}

	public void setFlow(OaEosFlowItem flow) {
		this.flow = flow;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public OaEosPro() {
		super();
		super.tableName="OaEosPro";
	}


    public String getUsers_names() {
        return users_names;
    }

    public void setUsers_names(String users_names) {
        this.users_names = users_names;
    }

    public OaEosPro(String id){
		super(id);super.tableName="OaEosPro";
	}

	@Length(min=0, max=200, message="项目名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=200, message="预立项编号长度必须介于 0 和 200 之间")
	public String getPaNumber() {
		return paNumber;
	}

	public void setPaNumber(String paNumber) {
		this.paNumber = paNumber;
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
	
	@Length(min=0, max=200, message="客户名称长度必须介于 0 和 200 之间")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=0, max=200, message="客户方负责人长度必须介于 0 和 200 之间")
	public String getCustomerUser() {
		return customerUser;
	}

	public void setCustomerUser(String customerUser) {
		this.customerUser = customerUser;
	}
	
	@Length(min=0, max=200, message="客户负责人联系方式长度必须介于 0 和 200 之间")
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
	
	@Length(min=1, max=64, message="项目类型长度必须介于 1 和 64 之间")
	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}
	
	public String getProLocation() {
		return proLocation;
	}

	public void setProLocation(String proLocation) {
		this.proLocation = proLocation;
	}
	
	public String getProCapitalSource() {
		return proCapitalSource;
	}

	public void setProCapitalSource(String proCapitalSource) {
		this.proCapitalSource = proCapitalSource;
	}
	
	public String getProBudget() {
		return proBudget;
	}

	public void setProBudget(String proBudget) {
		this.proBudget = proBudget;
	}
	
	public String getProCycle() {
		return proCycle;
	}

	public void setProCycle(String proCycle) {
		this.proCycle = proCycle;
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
	
	public String getInputEstimation() {
		return inputEstimation;
	}

	public void setInputEstimation(String inputEstimation) {
		this.inputEstimation = inputEstimation;
	}
	
	public String getWorkload() {
		return workload;
	}

	public void setWorkload(String workload) {
		this.workload = workload;
	}
	
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
	@Length(min=1, max=10, message="管理，财务审核状态0,1完成长度必须介于 1 和 10 之间")
	public String getToExamine() {
		return toExamine;
	}

	public void setToExamine(String toExamine) {
		this.toExamine = toExamine;
	}
	
	@Length(min=1, max=10, message="审批状态0,1完成长度必须介于 1 和 10 之间")
	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

	@Override
	public int compareTo(OaEosPro arg0) {
		return arg0.getTotal().compareTo(this.getTotal());      //这里定义你排序的规则。
	}
}