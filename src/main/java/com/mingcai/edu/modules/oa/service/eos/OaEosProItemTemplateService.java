/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import com.mingcai.edu.common.utils.PageData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItemTemplate;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProItemTemplateDao;

/**
 * 销售立项子项模板Service
 * @author 李艺杰
 * @version 2019-04-22
 */
@Service
@Transactional(readOnly = true)
public class OaEosProItemTemplateService extends CrudService<OaEosProItemTemplateDao, OaEosProItemTemplate> {

	public OaEosProItemTemplate get(String id) {
		return super.get(id);
	}
	
	public List<OaEosProItemTemplate> findList(OaEosProItemTemplate oaEosProItemTemplate) {
		return super.findList(oaEosProItemTemplate);
	}
	
	public Page<OaEosProItemTemplate> findPage(Page<OaEosProItemTemplate> page, OaEosProItemTemplate oaEosProItemTemplate) {
		return super.findPage(page, oaEosProItemTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosProItemTemplate oaEosProItemTemplate) {
		super.save(oaEosProItemTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosProItemTemplate oaEosProItemTemplate) {
		super.delete(oaEosProItemTemplate);
	}


	public List<PageData> findMap(PageData pageData){
		return dao.findMap(pageData);
	}
	
}