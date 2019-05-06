/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.contract;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 供应商产品标签Entity
 * @author 坤
 * @version 2018-03-05
 */
public class OaContractSupplierProductType extends DataEntity<OaContractSupplierProductType> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	
	public OaContractSupplierProductType() {
		super();
	}

	public OaContractSupplierProductType(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}