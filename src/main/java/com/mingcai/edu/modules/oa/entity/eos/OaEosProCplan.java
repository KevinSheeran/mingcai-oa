/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 计划收款Entity
 * @author kun
 * @version 2019-03-21
 */
public class OaEosProCplan extends DataEntity<OaEosProCplan> {
	
	private static final long serialVersionUID = 1L;
	private String eosId;		// 项目id
	private String stage;		// 阶段
	private String cplanContent;		// 收款类型计划
	private Date receivablesDate;		// 收款时间计划
	private Date finishDate;		// 实际收款时间
	private Integer proportion;		// 收款比例
	private Integer status=0;		// 是否完成默认0,1.完成,-1.延期完成
	
	public OaEosProCplan() {
		super();
	}

	public OaEosProCplan(String id){
		super(id);
	}

	@Length(min=0, max=200, message="项目id长度必须介于 0 和 200 之间")
	public String getEosId() {
		return eosId;
	}

	public void setEosId(String eosId) {
		this.eosId = eosId;
	}
	
	@Length(min=0, max=200, message="阶段长度必须介于 0 和 200 之间")
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@Length(min=0, max=500, message="收款类型计划长度必须介于 0 和 500 之间")
	public String getCplanContent() {
		return cplanContent;
	}

	public void setCplanContent(String cplanContent) {
		this.cplanContent = cplanContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReceivablesDate() {
		return receivablesDate;
	}

	public void setReceivablesDate(Date receivablesDate) {
		this.receivablesDate = receivablesDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	
	public Integer getProportion() {
		return proportion;
	}

	public void setProportion(Integer proportion) {
		this.proportion = proportion;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}