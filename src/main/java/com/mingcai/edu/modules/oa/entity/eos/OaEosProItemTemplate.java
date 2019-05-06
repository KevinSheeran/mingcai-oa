/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 销售立项子项模板Entity
 * @author 李艺杰
 * @version 2019-04-22
 */
public class OaEosProItemTemplate extends DataEntity<OaEosProItemTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	
	public OaEosProItemTemplate() {
		super();
	}

	public OaEosProItemTemplate(String id){
		super(id);
	}

	@Length(min=0, max=200, message="名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}