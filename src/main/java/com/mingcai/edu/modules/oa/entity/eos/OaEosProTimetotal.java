/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 项目工时汇总Entity
 * @author kun
 * @version 2019-04-01
 */
public class OaEosProTimetotal extends DataEntity<OaEosProTimetotal> {
	
	private static final long serialVersionUID = 1L;
	private Double psTotal=0.0;		// 售前total
	private Double saleTotal=0.0;		// 销售total
	private Double rdTotal=0.0;		// 研发total
	
	public OaEosProTimetotal() {
		super();
	}

	public OaEosProTimetotal(String id){
		super(id);
	}

	@Length(min=0, max=200, message="售前total长度必须介于 0 和 200 之间")
	public Double getPsTotal() {
		return psTotal;
	}

	public void setPsTotal(Double psTotal) {
		this.psTotal = psTotal;
	}
	
	@Length(min=0, max=64, message="销售total长度必须介于 0 和 64 之间")
	public Double getSaleTotal() {
		return saleTotal;
	}

	public void setSaleTotal(Double saleTotal) {
		this.saleTotal = saleTotal;
	}
	
	@Length(min=0, max=200, message="研发total长度必须介于 0 和 200 之间")
	public Double getRdTotal() {
		return rdTotal;
	}

	public void setRdTotal(Double rdTotal) {
		this.rdTotal = rdTotal;
	}
	
}