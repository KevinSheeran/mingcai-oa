/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.user;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 申请销售额Entity
 * @author kun
 * @version 2019-04-18
 */
public class OaUserSalesVolume extends DataEntity<OaUserSalesVolume> {
	
	private static final long serialVersionUID = 1L;

	private String salesVolume="0";		// 销售额
	private String flowId;		// 流程id
	private Integer status=0;		// 状态(默认0,1.申请中，2.通过.-1驳回)
	private String flag="0";		// 是否可用(冻结)
	private String uotaRatio;//使用销售额比例
	public OaUserSalesVolume() {
		super();
	}

	public OaUserSalesVolume(String id){
		super(id);
	}

	public String getUotaRatio() {
		return uotaRatio;
	}

	public void setUotaRatio(String uotaRatio) {
		this.uotaRatio = uotaRatio;
	}

	@Length(min=1, max=64, message="流程id长度必须介于 1 和 64 之间")
	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	public String getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(String salesVolume) {
		this.salesVolume = salesVolume;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=1, max=1, message="是否可用(冻结)长度必须介于 1 和 1 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}