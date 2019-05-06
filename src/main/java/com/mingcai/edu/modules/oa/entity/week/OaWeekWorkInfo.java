/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.week;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 周任务详情Entity
 * @author 坤
 * @version 2018-01-24
 */
public class OaWeekWorkInfo extends DataEntity<OaWeekWorkInfo> {
	
	private static final long serialVersionUID = 1L;
	private String workId;		// 主表id
	private String title;		// 计划目标
	private String mon;		// 一
	private String tue;		// 二
	private String wed;		// 三
	private String thu;		// 四
	private String fri;		// 五
	private String sat;		// 六
	private String sun;		// 日
	private String opinion;		// 意见
	private String followup;		// 跟进
	
	public OaWeekWorkInfo() {
		super();
	}

	public OaWeekWorkInfo(String id){
		super(id);
	}

	@Length(min=1, max=64, message="主表id长度必须介于 1 和 64 之间")
	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}
	
	@Length(min=0, max=200, message="计划目标长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=200, message="一长度必须介于 0 和 200 之间")
	public String getMon() {
		return mon;
	}

	public void setMon(String mon) {
		this.mon = mon;
	}
	
	@Length(min=0, max=200, message="二长度必须介于 0 和 200 之间")
	public String getTue() {
		return tue;
	}

	public void setTue(String tue) {
		this.tue = tue;
	}
	
	@Length(min=0, max=200, message="三长度必须介于 0 和 200 之间")
	public String getWed() {
		return wed;
	}

	public void setWed(String wed) {
		this.wed = wed;
	}
	
	@Length(min=0, max=200, message="四长度必须介于 0 和 200 之间")
	public String getThu() {
		return thu;
	}

	public void setThu(String thu) {
		this.thu = thu;
	}
	
	@Length(min=0, max=200, message="五长度必须介于 0 和 200 之间")
	public String getFri() {
		return fri;
	}

	public void setFri(String fri) {
		this.fri = fri;
	}
	
	@Length(min=0, max=200, message="六长度必须介于 0 和 200 之间")
	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}
	
	@Length(min=0, max=200, message="日长度必须介于 0 和 200 之间")
	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}
	
	@Length(min=0, max=1000, message="意见长度必须介于 0 和 1000 之间")
	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	@Length(min=0, max=20, message="跟进长度必须介于 0 和 20 之间")
	public String getFollowup() {
		return followup;
	}

	public void setFollowup(String followup) {
		this.followup = followup;
	}
	
}