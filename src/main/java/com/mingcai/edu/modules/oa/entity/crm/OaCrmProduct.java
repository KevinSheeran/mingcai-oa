/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.crm;

import com.mingcai.edu.modules.oa.entity.OaFinanceCompany;
import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 项目报备Entity
 * @author 坤
 * @version 2018-02-07
 */
public class OaCrmProduct extends DataEntity<OaCrmProduct> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String customerIds;		// 客户ids
	private String customerNames="";
	private List<OaCrmCustomer> oaCrmCustomers;
	private String status;		// 状态：1.未跟进，2.跟进中，3.公开，4.申请立项中
	private boolean leader; //是否是领导
	private boolean open;//公开
	private OaFinanceCompany oaFinanceCompany;		// 单位
	private String code;//立项编号
	public OaCrmProduct() {
		super();
	}

	public OaCrmProduct(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=2000, message="客户ids长度必须介于 1 和 2000 之间")
	public String getCustomerIds() {
		return customerIds;
	}

	public void setCustomerIds(String customerIds) {
		this.customerIds = customerIds;
	}

	@Length(min=1, max=64, message="申请立项中长度必须介于 1 和 64 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(String customerNames) {
		this.customerNames = customerNames;
	}

	public List<OaCrmCustomer> getOaCrmCustomers() {
		return oaCrmCustomers;
	}

	public void setOaCrmCustomers(List<OaCrmCustomer> oaCrmCustomers) {
		this.oaCrmCustomers = oaCrmCustomers;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public OaFinanceCompany getOaFinanceCompany() {
		return oaFinanceCompany;
	}

	public void setOaFinanceCompany(OaFinanceCompany oaFinanceCompany) {
		this.oaFinanceCompany = oaFinanceCompany;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}