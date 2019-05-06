/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.contract;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 供应商信息Entity
 * @author 坤
 * @version 2018-01-09
 */
public class OaContractSupplier extends DataEntity<OaContractSupplier> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 单位名称
	private String contacts;		// 联系人
	private String contactNumber;		// 联系电话
	private String address;		// 联系地址
	private String bankAccount;		// 银行账号
	private String dutyParagraph;		// 税号
	private String taxPoint;		// 可开票点数，多个用逗号隔开
	private String openingBank;//开户行
	public OaContractSupplier() {
		super();
	}

	public OaContractSupplier(String id){
		super(id);
	}

	@Length(min=1, max=100, message="单位名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="联系人长度必须介于 0 和 100 之间")
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	@Length(min=0, max=100, message="联系电话长度必须介于 0 和 100 之间")
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@Length(min=0, max=100, message="联系地址长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="银行账号长度必须介于 0 和 100 之间")
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	@Length(min=0, max=100, message="税号长度必须介于 0 和 100 之间")
	public String getDutyParagraph() {
		return dutyParagraph;
	}

	public void setDutyParagraph(String dutyParagraph) {
		this.dutyParagraph = dutyParagraph;
	}
	
	@Length(min=0, max=100, message="可开票点数，多个用逗号隔开长度必须介于 0 和 100 之间")
	public String getTaxPoint() {
		return taxPoint;
	}

	public void setTaxPoint(String taxPoint) {
		this.taxPoint = taxPoint;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}
}