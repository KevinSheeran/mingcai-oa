/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;
import com.mingcai.edu.modules.sys.entity.User;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 操作日志Entity
 * @author 坤
 * @version 2019-03-18
 */
public class OaEosFlowLogo extends DataEntity<OaEosFlowLogo> {
	
	private static final long serialVersionUID = 1L;
	private String itemId;		// 流程项id
	private User user;		// 审批者id
	private Integer status;		// 审批状态(0,1完成,-1驳回)
	private String content;		// 审批者意见，建议
	private Integer sendStatus;		// 消息推送状态 0未推送，1送达
	private String sendContent;		// 推送文本内容
	
	public OaEosFlowLogo() {
		super();
	}

	public OaEosFlowLogo(String id){
		super(id);
	}

	@Length(min=0, max=200, message="流程项id长度必须介于 0 和 200 之间")
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}
	
	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	
}