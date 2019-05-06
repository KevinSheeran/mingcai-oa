/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.contract;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 供应商产品信息Entity
 * @author 坤
 * @version 2018-01-09
 */
public class OaContractSupplierProduct extends DataEntity<OaContractSupplierProduct> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String specifications;		// 规格
	private String unitPrice;		// 采购单价
	private String supplierId;		// 供应商id
	private String supplierName;
	private String taxPoint;//税点
	private String  enclosurePath;//附件路径
	private String labels ;//标签集合多个用逗号隔开
	public OaContractSupplierProduct() {
		super();
	}

	public OaContractSupplierProduct(String id){
		super(id);
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1000, message="规格长度必须介于 0 和 100 之间")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Length(min=0, max=100, message="采购单价长度必须介于 0 和 100 之间")
	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	@Length(min=1, max=64, message="供应商id长度必须介于 1 和 64 之间")
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getTaxPoint() {
		return taxPoint;
	}

	public void setTaxPoint(String taxPoint) {
		this.taxPoint = taxPoint;
	}

	public String getEnclosurePath() {
		return enclosurePath;
	}

	public void setEnclosurePath(String enclosurePath) {
		this.enclosurePath = enclosurePath;
	}
	@Length(min=1, max=255, message="产品标签分类不能为空")
	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}
}