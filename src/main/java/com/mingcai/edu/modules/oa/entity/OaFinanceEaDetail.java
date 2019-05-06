/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.mingcai.edu.common.persistence.DataEntity;
import com.mingcai.edu.modules.cms.entity.Category;

/**
 * 报销管理详细Entity
 * 
 * @author hxy
 * @version 2017-12-12
 */
public class OaFinanceEaDetail extends DataEntity<OaFinanceEaDetail> {

	private static final long serialVersionUID = 1L;
	private String eaId; // 报销表单
	private Category category; // 费用类别
	private OaProduct oaProduct; // 报销项目
	private BigDecimal billNumber; // 单据数量
	private String payType; // 支付类型
	private BigDecimal money; // 报销金额(分)
	private String remarks; // 报销详细说明

	public OaFinanceEaDetail() {
		super();
	}

	public OaFinanceEaDetail(String id) {
		super(id);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(BigDecimal billNumber) {
		this.billNumber = billNumber;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getMoney() {
		if(money!=null){
			DecimalFormat df = new DecimalFormat("0.00");
			money = new BigDecimal(df.format(money));
		}
		return money;
	}

	public void setMoney(BigDecimal money) {
		if(money!=null){
			DecimalFormat df = new DecimalFormat("0.00");
			money = new BigDecimal(df.format(money));
		}
		this.money = money;
	}

	public String getEaId() {
		return eaId;
	}

	public void setEaId(String eaId) {
		this.eaId = eaId;
	}

	public OaProduct getOaProduct() {
		return oaProduct;
	}

	public void setOaProduct(OaProduct oaProduct) {
		this.oaProduct = oaProduct;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}