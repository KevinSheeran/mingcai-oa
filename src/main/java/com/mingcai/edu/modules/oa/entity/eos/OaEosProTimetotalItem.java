/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 汇总详情Entity
 * @author kun
 * @version 2019-04-01
 */
public class OaEosProTimetotalItem extends DataEntity<OaEosProTimetotalItem> {
	
	private static final long serialVersionUID = 1L;
	private String proId;		// 项目id
	private Double psTotal=0.0;		// 售前total
	private Double saleTotal=0.0;		// 销售total
	private Double rdTotal=0.0;		// 研发total
	private String times;		// 时间段
	
	public OaEosProTimetotalItem() {
		super();
	}

	public OaEosProTimetotalItem(String id){
		super(id);
	}

	@Length(min=1, max=64, message="项目id长度必须介于 1 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	public Double getPsTotal() {
		return psTotal;
	}

	public void setPsTotal(Double psTotal) {
		this.psTotal = psTotal;
	}
	
	public Double getSaleTotal() {
		return saleTotal;
	}

	public void setSaleTotal(Double saleTotal) {
		this.saleTotal = saleTotal;
	}
	
	public Double getRdTotal() {
		return rdTotal;
	}

	public void setRdTotal(Double rdTotal) {
		this.rdTotal = rdTotal;
	}
	
	@Length(min=0, max=200, message="时间段长度必须介于 0 和 200 之间")
	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}
	
}