/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;
import com.mingcai.edu.modules.sys.entity.User;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 流程Entity
 * @author kun
 * @version 2019-03-08
 */
public class OaEosFlow extends DataEntity<OaEosFlow> {
	
	private static final long serialVersionUID = 1L;
	private String eosId;		// 创建项id
	private User user;		// 审批者id
	private Integer status;		// 审批状态(0,1完成)
	private String content;		// 审批者意见，建议
	private Integer order;		// 流程排序
	private Integer serialNumber;		// 流程序号
	private List<OaEosFlow> oeflist;
	private Integer SendStatus=0;
	private String sendContent;
	public OaEosFlow() {
		super();
	}


	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public Integer getSendStatus() {
		return SendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		SendStatus = sendStatus;
	}

	public List<OaEosFlow> getOeflist() {
		return oeflist;
	}

	public void setOeflist(List<OaEosFlow> oeflist) {
		this.oeflist = oeflist;
	}

	public OaEosFlow(String id){
		super(id);
	}

	@Length(min=0, max=200, message="创建项id长度必须介于 0 和 200 之间")
	public String getEosId() {
		return eosId;
	}

	public void setEosId(String eosId) {
		this.eosId = eosId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=10, message="审批状态(0,1完成)长度必须介于 0 和 10 之间")
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
	
}