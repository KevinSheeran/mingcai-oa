/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;
import com.mingcai.edu.modules.sys.entity.User;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 流程项Entity
 * @author 坤
 * @version 2019-03-18
 */
public class OaEosFlowItem extends DataEntity<OaEosFlowItem> {
	
	private static final long serialVersionUID = 1L;
	private String flowId;		// 创建项id
	private User user;		// 审批者id
	private Integer status;		// 审批状态(0,1完成,-1驳回)
	private String content;		// 审批者意见，建议
	private Integer order;		// 流程排序
	private Integer serialNumber;		// 流程序号
	private Integer sendStatus;		// 消息推送状态 0未推送，1送达
	private String sendContent;		// 推送文本内容
	private OaEosFlows oaEosFlows;//

	public OaEosFlows getOaEosFlows() {
		return oaEosFlows;
	}

	public void setOaEosFlows(OaEosFlows oaEosFlows) {
		this.oaEosFlows = oaEosFlows;
	}

	public OaEosFlowItem() {
		super();
	}

	public OaEosFlowItem(String id){
		super(id);
	}

	@Length(min=0, max=200, message="创建项id长度必须介于 0 和 200 之间")
	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=3000, message="审批者意见，建议长度必须介于 0 和 3000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	@Length(min=0, max=5000, message="推送文本内容长度必须介于 0 和 5000 之间")
	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	
}