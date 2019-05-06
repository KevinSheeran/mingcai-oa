/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.contract;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 合同项Entity
 * @author 坤
 * @version 2018-01-16
 */
public class OaContractTerms extends DataEntity<OaContractTerms> {
	private static final long serialVersionUID = 1L;
	private String contractId;		// 合同id
	private double number;		// 数量
	private double divnumber;		// 剩余数量
	private double sumPrice;//总计
	private double unitPrice;		// 单价
	private String name;		// 项名称
	private String unit;		// 计量单位
	private String specifications;		// 规格
	private String type;		// 产品类型，自有，开发，采购
	private String code;//编号
	private Integer indexs;//自编号
	private List<OaContractPurchaseTerms> termsList;//采购合同

	public OaContractTerms() {
		super();
	}

	public OaContractTerms(String id){
		super(id);
	}

	@Length(min=0, max=64, message="合同id长度必须介于 0 和 64 之间")
	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
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
	
	@Length(min=0, max=100, message="项名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=10, message="计量单位长度必须介于 0 和 10 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=2000, message="规格长度必须介于 0 和 2000 之间")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Length(min=0, max=10, message="产品类型，自有，开发，采购长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<OaContractPurchaseTerms> getTermsList() {
		return termsList;
	}

	public void setTermsList(List<OaContractPurchaseTerms> termsList) {
		this.termsList = termsList;
	}

	public double getDivnumber() {
		return divnumber;
	}

	public void setDivnumber(double divnumber) {
		this.divnumber = divnumber;
	}

	public double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Integer getIndexs() {
		return indexs;
	}

	public void setIndexs(Integer indexs) {
		this.indexs = indexs;
	}
}