/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.week;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.week.OaWeekWork;
import com.mingcai.edu.modules.oa.dao.week.OaWeekWorkDao;

/**
 * 周任务Service
 * @author 坤
 * @version 2018-01-24
 */
@Service
@Transactional(readOnly = true)
public class OaWeekWorkService extends CrudService<OaWeekWorkDao, OaWeekWork> {

	@Autowired
	OaWeekWorkDao dao;
	public OaWeekWork get(String id) {
		return super.get(id);
	}
	public OaWeekWork getByDate(OaWeekWork id) {
		return dao.getByDate(id);
	}
	public List<OaWeekWork> findList(OaWeekWork oaWeekWork) {
		return super.findList(oaWeekWork);
	}
	
	public Page<OaWeekWork> findPage(Page<OaWeekWork> page, OaWeekWork oaWeekWork) {
		return super.findPage(page, oaWeekWork);
	}
	
	@Transactional(readOnly = false)
	public void save(OaWeekWork oaWeekWork) {
		super.save(oaWeekWork);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaWeekWork oaWeekWork) {
		super.delete(oaWeekWork);
	}
	
}