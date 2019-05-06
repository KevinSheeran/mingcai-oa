/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.week;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.week.OaWeekWorkInfo;
import com.mingcai.edu.modules.oa.dao.week.OaWeekWorkInfoDao;

/**
 * 周任务详情Service
 * @author 坤
 * @version 2018-01-24
 */
@Service
@Transactional(readOnly = true)
public class OaWeekWorkInfoService extends CrudService<OaWeekWorkInfoDao, OaWeekWorkInfo> {

	public OaWeekWorkInfo get(String id) {
		return super.get(id);
	}
	
	public List<OaWeekWorkInfo> findList(OaWeekWorkInfo oaWeekWorkInfo) {
		return super.findList(oaWeekWorkInfo);
	}
	
	public Page<OaWeekWorkInfo> findPage(Page<OaWeekWorkInfo> page, OaWeekWorkInfo oaWeekWorkInfo) {
		return super.findPage(page, oaWeekWorkInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(OaWeekWorkInfo oaWeekWorkInfo) {
		super.save(oaWeekWorkInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaWeekWorkInfo oaWeekWorkInfo) {
		super.delete(oaWeekWorkInfo);
	}
	
}