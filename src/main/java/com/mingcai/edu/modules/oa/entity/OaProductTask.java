/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import com.mingcai.edu.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.mingcai.edu.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 项目任务Entity
 * @author 江坤
 * @version 2017-11-30
 */
public class OaProductTask extends DataEntity<OaProductTask> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 任务名称
	private String productId;		// 项目id
	private User taskUser;		// 任务指定人id
	private String taskStatus;		// 任务完成状态
	private Date prodcutEndDate;		// 任务截止时间
	private String productName;//项目名
	public OaProductTask() {
		super();
	}

	public OaProductTask(String id){
		super(id);
	}

	@Length(min=1, max=100, message="任务名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="项目id长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public User getTaskUser() {
		return taskUser;
	}

	public void setTaskUser(User user) {
		this.taskUser = user;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}