/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowLogo;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowLogoDao;

/**
 * 操作日志Service
 * @author 坤
 * @version 2019-03-18
 */
@Service
@Transactional(readOnly = true)
public class OaEosFlowLogoService extends CrudService<OaEosFlowLogoDao, OaEosFlowLogo> {

	public OaEosFlowLogo get(String id) {
		return super.get(id);
	}
	
	public List<OaEosFlowLogo> findList(OaEosFlowLogo oaEosFlowLogo) {
		return super.findList(oaEosFlowLogo);
	}
	
	public Page<OaEosFlowLogo> findPage(Page<OaEosFlowLogo> page, OaEosFlowLogo oaEosFlowLogo) {
		return super.findPage(page, oaEosFlowLogo);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosFlowLogo oaEosFlowLogo) {
		super.save(oaEosFlowLogo);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosFlowLogo oaEosFlowLogo) {
		super.delete(oaEosFlowLogo);
	}
	
}