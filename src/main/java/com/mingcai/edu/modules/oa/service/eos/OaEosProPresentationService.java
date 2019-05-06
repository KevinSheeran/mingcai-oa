/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProPresentation;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProPresentationDao;

/**
 * 项目周报Service
 * @author kun
 * @version 2019-03-25
 */
@Service
@Transactional(readOnly = true)
public class OaEosProPresentationService extends CrudService<OaEosProPresentationDao, OaEosProPresentation> {

	public OaEosProPresentation get(String id) {
		return super.get(id);
	}
	
	public List<OaEosProPresentation> findList(OaEosProPresentation oaEosProPresentation) {
		return super.findList(oaEosProPresentation);
	}
	public List<OaEosProPresentation> getListAllByDate(OaEosProPresentation oaEosProPresentation){
		return dao.getListAllByDate(oaEosProPresentation);
	}
	public OaEosProPresentation getByDate(OaEosProPresentation oaEosProPresentation){
		return dao.getByDate(oaEosProPresentation);
	}
	public Page<OaEosProPresentation> findPage(Page<OaEosProPresentation> page, OaEosProPresentation oaEosProPresentation) {
		return super.findPage(page, oaEosProPresentation);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosProPresentation oaEosProPresentation) {
		super.save(oaEosProPresentation);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosProPresentation oaEosProPresentation) {
		super.delete(oaEosProPresentation);
	}
	
}