/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlows;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowsDao;

/**
 * 流程Service
 * @author 坤
 * @version 2019-03-18
 */
@Service
@Transactional(readOnly = true)
public class OaEosFlowsService extends CrudService<OaEosFlowsDao, OaEosFlows> {

	public OaEosFlows get(String id) {
		return super.get(id);
	}
	
	public List<OaEosFlows> findList(OaEosFlows oaEosFlows) {
		return super.findList(oaEosFlows);
	}
	
	public Page<OaEosFlows> findPage(Page<OaEosFlows> page, OaEosFlows oaEosFlows) {
		return super.findPage(page, oaEosFlows);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosFlows oaEosFlows) {
		super.save(oaEosFlows);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosFlows oaEosFlows) {
		super.delete(oaEosFlows);
	}
	
}