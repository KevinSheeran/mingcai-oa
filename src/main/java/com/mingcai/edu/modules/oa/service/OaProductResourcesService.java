/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.OaProductResources;
import com.mingcai.edu.modules.oa.dao.OaProductResourcesDao;

/**
 * 项目资源Service
 * @author 江坤
 * @version 2017-12-04
 */
@Service
@Transactional(readOnly = true)
public class OaProductResourcesService extends CrudService<OaProductResourcesDao, OaProductResources> {

	
	public OaProductResources get(String id) {
		OaProductResources oaProductResources = super.get(id);
		return oaProductResources;
	}
	
	public List<OaProductResources> findList(OaProductResources oaProductResources) {
		return super.findList(oaProductResources);
	}
	
	public Page<OaProductResources> findPage(Page<OaProductResources> page, OaProductResources oaProductResources) {
		return super.findPage(page, oaProductResources);
	}
	
	@Transactional(readOnly = false)
	public void save(OaProductResources oaProductResources) {
		super.save(oaProductResources);

	}
	@Transactional(readOnly = false)
	public void insert(OaProductResources oaProductResources){
		dao.insert(oaProductResources);
	}
	@Transactional(readOnly = false)
	public void delete(OaProductResources oaProductResources) {
		super.delete(oaProductResources);
	}
	
}