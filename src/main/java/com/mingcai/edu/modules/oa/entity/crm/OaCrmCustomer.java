/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.crm;

import com.mingcai.edu.modules.oa.entity.OaFinanceCompany;
import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 联系人Entity
 * @author 坤
 * @version 2018-02-07
 */
public class OaCrmCustomer extends DataEntity<OaCrmCustomer> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 单位名称
	private String phone;		// 联系电话
	private String post;		// 职务
	private OaFinanceCompany  oaFinanceCompany;		// 单位
	
	public OaCrmCustomer() {
		super();
	}

	public OaCrmCustomer(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public OaFinanceCompany getOaFinanceCompany() {
		return oaFinanceCompany;
	}

	public void setOaFinanceCompany(OaFinanceCompany oaFinanceCompany) {
		this.oaFinanceCompany = oaFinanceCompany;
	}
}