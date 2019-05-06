/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import com.mingcai.edu.common.persistence.ActEntity;
import com.mingcai.edu.modules.sys.entity.Office;
import com.mingcai.edu.modules.sys.entity.User;

/**
 * 报销管理Entity
 * 
 * @author hxy
 * @version 2017-12-12
 */
public class OaFinanceEa extends ActEntity<OaFinanceEa> {

	private static final long serialVersionUID = 1L;
	private String eaNo; // 报销单编号
	private String proInsId; // 流程ID
	private Office office; // 归属部门
	private BigDecimal moneys; // 总金额
	private User userFirstApprover; // 经理审批
	private String firstApproveStatus; // 经理审批状态0:未处理，1：处理中，2：通过 3:退回
	private User userSecondApprover; // 总经理审批者
	private String secondApproveStatus; // 总经理审批状态0:未处理，1：处理中，2：通过 3:退回
	private User userThirdApprover; // 票据核对
	private String thirdApproveStatus; // 票据核对状态0:未处理，1：处理中，2：通过 3:退回
	private String status; // 报销状态 0:未处理，1：处理中，2：通过 3:退回
	private String leaderRemark; // 报销状态 0:未处理，1：处理中，2：通过 3:退回
	private boolean accessAble; // 权限控制
	private List<OaFinanceEaDetail> detailList; // 报销详情列表

	// -- 临时属性 --//
	private Task task; // 流程任务
	private Map<String, Object> variables; // 流程变量
	private ProcessInstance processInstance; // 运行中的流程实例
	private HistoricProcessInstance historicProcessInstance; // 历史的流程实例
	private ProcessDefinition processDefinition; // 流程定义
	private User applyuser; // 申请人

	public OaFinanceEa() {
		super();
	}

	public OaFinanceEa(String id) {
		super(id);
	}

	public String getEaNo() {
		return eaNo;
	}

	public void setEaNo(String eaNo) {
		this.eaNo = eaNo;
	}

	public String getProInsId() {
		return proInsId;
	}

	public void setProInsId(String proInsId) {
		this.proInsId = proInsId;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public BigDecimal getMoneys() {
		if(moneys!=null){
			DecimalFormat df = new DecimalFormat("0.00");
			moneys = new BigDecimal(df.format(moneys));
		}
		return moneys;
	}

	public void setMoneys(BigDecimal moneys) {
		this.moneys = moneys;
	}

	public User getUserFirstApprover() { // 审批经理
		return userFirstApprover;
	}

	public void setUserFirstApprover(User userFirstApprover) {
		this.userFirstApprover = userFirstApprover;
	}

	public String getFirstApproveStatus() {
		return firstApproveStatus;
	}

	public void setFirstApproveStatus(String firstApproveStatus) {
		this.firstApproveStatus = firstApproveStatus;
	}

	public User getUserSecondApprover() { // 总审批经理
		return userSecondApprover;
	}

	public void setUserSecondApprover(User userSecondApprover) {
		this.userSecondApprover = userSecondApprover;
	}

	public String getSecondApproveStatus() {
		return secondApproveStatus;
	}

	public void setSecondApproveStatus(String secondApproveStatus) {
		this.secondApproveStatus = secondApproveStatus;
	}

	public User getUserThirdApprover() { // 审批财务经理
		return userThirdApprover;
	}

	public void setUserThirdApprover(User userThirdApprover) {
		this.userThirdApprover = userThirdApprover;
	}

	public String getThirdApproveStatus() {
		return thirdApproveStatus;
	}

	public void setThirdApproveStatus(String thirdApproveStatus) {
		this.thirdApproveStatus = thirdApproveStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public HistoricProcessInstance getHistoricProcessInstance() {
		return historicProcessInstance;
	}

	public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
		this.historicProcessInstance = historicProcessInstance;
	}

	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}

	public boolean isAccessAble() {
		return accessAble;
	}

	public void setAccessAble(boolean accessAble) {
		this.accessAble = accessAble;
	}

	public String getLeaderRemark() {
		return leaderRemark;
	}

	public void setLeaderRemark(String leaderRemark) {
		this.leaderRemark = leaderRemark;
	}

	public List<OaFinanceEaDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<OaFinanceEaDetail> detailList) {
		this.detailList = detailList;
	}

	public User getApplyuser() {
		return applyuser;
	}

	public void setApplyuser(User applyuser) {
		this.applyuser = applyuser;
	}

}