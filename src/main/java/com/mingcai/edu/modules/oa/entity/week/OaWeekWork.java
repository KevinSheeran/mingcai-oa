/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.week;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.Map;

/**
 * 周任务Entity
 * @author 坤
 * @version 2018-01-24
 */
public class OaWeekWork extends DataEntity<OaWeekWork> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 主任务
    private String titles;		// 主任务
	private String startEnd; //时间段
	private Map<String,String> weeksDate;
	private String summary;
	private boolean isshow;
	public OaWeekWork() {
		super();
	}

	public OaWeekWork(String id){
		super(id);
	}

	@Length(min=0, max=2000, message="主任务长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<String, String> getWeeksDate() {
		return weeksDate;
	}

	public void setWeeksDate(Map<String, String> weeksDate) {
		this.weeksDate = weeksDate;
	}

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStartEnd() {
        return startEnd;
    }

    public void setStartEnd(String startEnd) {
        this.startEnd = startEnd;
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
}