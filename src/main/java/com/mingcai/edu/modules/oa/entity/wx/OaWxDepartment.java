/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.wx;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 微信部门Entity
 * @author kun
 * @version 2019-03-05
 */
public class OaWxDepartment extends DataEntity<OaWxDepartment> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 部门名称
	private String parentid;		// 父节点
	private String order;		// 排序
	private List<OaWxUsers> users;
	public OaWxDepartment() {
		super();
	}

	public OaWxDepartment(String id){
		super(id);
	}

	@Length(min=0, max=100, message="部门名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="父节点长度必须介于 0 和 64 之间")
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	@Length(min=0, max=64, message="排序长度必须介于 0 和 64 之间")
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<OaWxUsers> getUsers() {
		return users;
	}

	public void setUsers(List<OaWxUsers> users) {
		this.users = users;
	}
}