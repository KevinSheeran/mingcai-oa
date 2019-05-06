/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.contract;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 采购合同关联Entity
 * @author 坤
 * @version 2018-01-16
 */
public class OaContractPurchaseTerms extends DataEntity<OaContractPurchaseTerms> {
	
	private static final long serialVersionUID = 1L;
	private String purchaseId;		// 采购合同id
	private String tremId;		// 项id
	private double number;		// 数量
	private double unitPrice;		// 单价
	private String name;
	private String code;
	private String ptId;
	private double pnumber;
	private String punitPrice;
	private double countNumber;
	private String unit;
	private String type;
	private String specifications;
	private String productId;
	private String pname;
	private String sname;
	private String finProductId;
	private String money;
	public OaContractPurchaseTerms() {
		super();
	}

	public OaContractPurchaseTerms(String id){
		super(id);
	}

	@Length(min=0, max=64, message="采购合同id长度必须介于 0 和 64 之间")
	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	@Length(min=0, max=64, message="项id长度必须介于 0 和 64 之间")
	public String getTremId() {
		return tremId;
	}

	public void setTremId(String tremId) {
		this.tremId = tremId;
	}
	
	@Length(min=0, max=64, message="数量长度必须介于 0 和 64 之间")
	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPtId() {
		return ptId;
	}

	public void setPtId(String ptId) {
		this.ptId = ptId;
	}

	public double getPnumber() {
		return pnumber;
	}

	public void setPnumber(double pnumber) {
		this.pnumber = pnumber;
	}

	public String getPunitPrice() {
		return punitPrice;
	}

	public void setPunitPrice(String punitPrice) {
		this.punitPrice = punitPrice;
	}

	public double getCountNumber() {
		return countNumber;
	}

	public void setCountNumber(double countNumber) {
		this.countNumber = countNumber;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getFinProductId() {
		return finProductId;
	}

	public void setFinProductId(String finProductId) {
		this.finProductId = finProductId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "{" +
				"\"purchaseId\":\"" + purchaseId + '\"' +
				",\"tremId\":\"" + tremId + '\"' +
				",\"number\":" + number +
				",\"unitPrice\":\"" + unitPrice + '\"' +
				",\"name\":\"" + name + '\"' +
				",\"code\":\"" + code + '\"' +
				",\"ptId\":\"" + ptId + '"' +
				",\"pnumber\":" + pnumber +
				",\"punitPrice\":\"" + punitPrice + '\"' +
				",\"countNumber\":" + countNumber +
				",\"unit\":\"" + unit + '\"' +
				",\"type\":\"" + type + '\"' +
				",\"specifications\":\"" + specifications + '\"' +
				",\"productId\":\"" + productId + '\"' +
				",\"id\":\"" + id + '\"' +
				'}';
	}
}