/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.wx;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 微信部门用户Entity
 * @author 微信部门用户
 * @version 2019-03-06
 */
public class OaWxDepartmentUsers extends DataEntity<OaWxDepartmentUsers> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户id
	private Integer order;		// 排序
	private Integer isLeaderInDept;		// 表示在所在的部门内是否为上级。；第三方仅通讯录应用可获取
	private OaWxUsers wxuser;
	private OaWxDepartment wxdepartment;
	public OaWxUsers getWxuser() {
		return wxuser;
	}

	public void setWxuser(OaWxUsers wxuser) {
		this.wxuser = wxuser;
	}

	public OaWxDepartment getWxdepartment() {
		return wxdepartment;
	}

	public void setWxdepartment(OaWxDepartment wxdepartment) {
		this.wxdepartment = wxdepartment;
	}

	public OaWxDepartmentUsers() {
		super();
	}

	public OaWxDepartmentUsers(String id){
		super(id);
	}

	@Length(min=0, max=64, message="用户id长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="排序长度必须介于 0 和 64 之间")
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Length(min=0, max=100, message="表示在所在的部门内是否为上级。；第三方仅通讯录应用可获取长度必须介于 0 和 100 之间")
	public Integer getIsLeaderInDept() {
		return isLeaderInDept;
	}

	public void setIsLeaderInDept(Integer isLeaderInDept) {
		this.isLeaderInDept = isLeaderInDept;
	}
	
}