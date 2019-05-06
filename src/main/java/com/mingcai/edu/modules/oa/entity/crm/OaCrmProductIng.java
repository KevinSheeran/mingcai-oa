/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.crm;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.Date;

/**
 * 项目报备Entity
 * @author 坤
 * @version 2018-02-07
 */
public class OaCrmProductIng extends DataEntity<OaCrmProductIng> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// 拜访方式：1.电话，2.面见，视频
	private String pid;
	private Date visitTime;
	public OaCrmProductIng() {
		super();
	}

	public OaCrmProductIng(String id){
		super(id);
	}

	@Length(min=1, max=64, message="面见，视频长度必须介于 1 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
}