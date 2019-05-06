/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.contract;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.Date;

/**
 * 付款情况Entity
 * @author 坤
 * @version 2018-01-13
 */
public class OaContractPayment extends DataEntity<OaContractPayment> {
	
	private static final long serialVersionUID = 1L;
	private String contractId;		// 合同id
	private String money;		// 付款金额
	private String batchId;		// 付款批次
	private String contractType;		// 合同类型
	private Date paymentDate;//付款日期
	private String proportion;//支付比例
	private String status="0";//付款状态
	public OaContractPayment() {
		super();
	}

	public OaContractPayment(String id){
		super(id);
	}

	@Length(min=0, max=64, message="合同id长度必须介于 0 和 64 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	
	@Length(min=0, max=64, message="付款金额长度必须介于 0 和 64 之间")
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}
	
	@Length(min=0, max=64, message="付款批次长度必须介于 0 和 64 之间")
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	@Length(min=0, max=10, message="合同类型长度必须介于 0 和 10 之间")
	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getProportion() {
		return proportion;
	}

	public void setProportion(String proportion) {
		this.proportion = proportion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}