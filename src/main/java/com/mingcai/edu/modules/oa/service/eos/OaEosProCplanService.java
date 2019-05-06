/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProCplan;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProCplanDao;

/**
 * 计划收款Service
 * @author kun
 * @version 2019-03-21
 */
@Service
@Transactional(readOnly = true)
public class OaEosProCplanService extends CrudService<OaEosProCplanDao, OaEosProCplan> {

	public OaEosProCplan get(String id) {
		return super.get(id);
	}
	
	public List<OaEosProCplan> findList(OaEosProCplan oaEosProCplan) {
		return super.findList(oaEosProCplan);
	}
	
	public Page<OaEosProCplan> findPage(Page<OaEosProCplan> page, OaEosProCplan oaEosProCplan) {
		return super.findPage(page, oaEosProCplan);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosProCplan oaEosProCplan) {
		super.save(oaEosProCplan);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosProCplan oaEosProCplan) {
		super.delete(oaEosProCplan);
	}
	
}