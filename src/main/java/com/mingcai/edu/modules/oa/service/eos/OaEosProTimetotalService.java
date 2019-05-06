/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProTimetotal;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProTimetotalDao;

/**
 * 项目工时汇总Service
 * @author kun
 * @version 2019-04-01
 */
@Service
@Transactional(readOnly = true)
public class OaEosProTimetotalService extends CrudService<OaEosProTimetotalDao, OaEosProTimetotal> {

	public OaEosProTimetotal get(String id) {
		return super.get(id);
	}
	
	public List<OaEosProTimetotal> findList(OaEosProTimetotal oaEosProTimetotal) {
		return super.findList(oaEosProTimetotal);
	}
	
	public Page<OaEosProTimetotal> findPage(Page<OaEosProTimetotal> page, OaEosProTimetotal oaEosProTimetotal) {
		return super.findPage(page, oaEosProTimetotal);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosProTimetotal oaEosProTimetotal) {
		super.save(oaEosProTimetotal);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosProTimetotal oaEosProTimetotal) {
		super.delete(oaEosProTimetotal);
	}
	
}