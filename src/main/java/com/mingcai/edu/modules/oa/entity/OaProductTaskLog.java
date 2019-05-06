/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 任务日志Entity
 * @author 江坤
 * @version 2017-12-01
 */
public class OaProductTaskLog extends DataEntity<OaProductTaskLog> {
	
	private static final long serialVersionUID = 1L;
	private String taskId;		// 任务id
	private String taskToUserId;		// 任务指定人id
	private String taskStatus;		// 任务完成状态
	private Date prodcutEndDate;		// 任务截止时间
	private String taskType;//执行类型
	private String code ;  //执行的序号，自动增加不可编辑
	private List<OaProductResources> reslist=new ArrayList<OaProductResources>();
	public OaProductTaskLog() {
		super();
	}

	public OaProductTaskLog(String id){
		super(id);
	}

	@Length(min=1, max=64, message="任务id长度必须介于 1 和 64 之间")
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	@Length(min=1, max=64, message="任务指定人id长度必须介于 1 和 64 之间")
	public String getTaskToUserId() {
		return taskToUserId;
	}

	public void setTaskToUserId(String taskToUserId) {
		this.taskToUserId = taskToUserId;
	}
	
	@Length(min=1, max=64, message="任务完成状态长度必须介于 1 和 64 之间")
	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProdcutEndDate() {
		return prodcutEndDate;
	}

	public void setProdcutEndDate(Date prodcutEndDate) {
		this.prodcutEndDate = prodcutEndDate;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<OaProductResources> getReslist() {
		return reslist;
	}

	public void setReslist(List<OaProductResources> reslist) {
		this.reslist = reslist;
	}
}