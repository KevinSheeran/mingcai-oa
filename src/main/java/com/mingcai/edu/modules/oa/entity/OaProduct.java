/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import com.google.common.collect.Lists;
import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 项目信息Entity
 * @author 江坤
 * @version 2017-11-29
 */
public class OaProduct extends DataEntity<OaProduct> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 项目名称
	private List<OaProductUsers> pusers=Lists.newArrayList(); ;//组用户
	private String users_ids;
	private String users_names;
	private String dirPath;
	private String updateById;
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public OaProduct() {
		super();
	}
	public OaProduct(String id){
		super(id);
	}


	@Length(min=1, max=100, message="项目名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OaProductUsers> getPusers() {
		return pusers;
	}

	public void setPusers(List<OaProductUsers> pusers) {
		this.pusers = pusers;
	}

	public String getUsers_ids() {
		return users_ids;
	}

	public void setUsers_ids(String users_ids) {
		this.users_ids = users_ids;
	}

	public String getUsers_names() {
		return users_names;
	}

	public void setUsers_names(String users_names) {
		this.users_names = users_names;
	}

	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}


	public String getUpdateById() {
		return updateById;
	}

	public void setUpdateById(String updateById) {
		this.updateById = updateById;
	}
}