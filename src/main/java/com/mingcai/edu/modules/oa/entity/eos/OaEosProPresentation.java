/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.Map;

/**
 * 项目周报Entity
 * @author kun
 * @version 2019-03-25
 */
public class OaEosProPresentation extends DataEntity<OaEosProPresentation> {
	
	private static final long serialVersionUID = 1L;
	private String eosId;		// 项目id
	private String summary;		// 总结
	private String nextPlan;		// 下周计划
	private String problemHandle;		// 问题处理
	private String proposal;		// 建议
	private String startEnd;		// 时间段
	private String titles;		// 主任务
	private String uername;
	private Map<String,String> weeksDate;
	private boolean isshow;
	private OaEosPro pro;
	public OaEosPro getPro() {
		return pro;
	}

	public void setPro(OaEosPro pro) {
		this.pro = pro;
	}

	public String getUername() {
		return uername;
	}

	public void setUername(String uername) {
		this.uername = uername;
	}

	public String getTitles() {
		return titles;
	}

	public void setTitles(String titles) {
		this.titles = titles;
	}

	public boolean isIsshow() {
		return isshow;
	}

	public void setIsshow(boolean isshow) {
		this.isshow = isshow;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Map<String, String> getWeeksDate() {
		return weeksDate;
	}

	public void setWeeksDate(Map<String, String> weeksDate) {
		this.weeksDate = weeksDate;
	}

	public OaEosProPresentation() {
		super();
	}

	public OaEosProPresentation(String id){
		super(id);
	}

	@Length(min=0, max=200, message="项目id长度必须介于 0 和 200 之间")
	public String getEosId() {
		return eosId;
	}

	public void setEosId(String eosId) {
		this.eosId = eosId;
	}
	
	@Length(min=0, max=5000, message="总结长度必须介于 0 和 5000 之间")
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Length(min=0, max=5000, message="下周计划长度必须介于 0 和 5000 之间")
	public String getNextPlan() {
		return nextPlan;
	}

	public void setNextPlan(String nextPlan) {
		this.nextPlan = nextPlan;
	}
	
	@Length(min=0, max=5000, message="问题处理长度必须介于 0 和 5000 之间")
	public String getProblemHandle() {
		return problemHandle;
	}

	public void setProblemHandle(String problemHandle) {
		this.problemHandle = problemHandle;
	}
	
	@Length(min=0, max=5000, message="建议长度必须介于 0 和 5000 之间")
	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}
	
	@Length(min=0, max=50, message="时间段长度必须介于 0 和 50 之间")
	public String getStartEnd() {
		return startEnd;
	}

	public void setStartEnd(String startEnd) {
		this.startEnd = startEnd;
	}
	
}