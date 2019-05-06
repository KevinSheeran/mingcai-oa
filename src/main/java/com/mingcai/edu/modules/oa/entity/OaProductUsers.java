/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import org.hibernate.validator.constraints.Length;
import com.mingcai.edu.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 项目组用户Entity
 * @author 江坤
 * @version 2017-11-29
 */
public class OaProductUsers extends DataEntity<OaProductUsers> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 项目id
	private User user;		// 项目组用户id
	private int userCount;
	public OaProductUsers() {
		super();
	}

	public OaProductUsers(String id){
		super(id);
	}

	@Length(min=1, max=100, message="项目id长度必须介于 1 和 100 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
}