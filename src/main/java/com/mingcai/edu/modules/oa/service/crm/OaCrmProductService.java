/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.crm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.crm.OaCrmProduct;
import com.mingcai.edu.modules.oa.dao.crm.OaCrmProductDao;

/**
 * 项目报备Service
 * @author 坤
 * @version 2018-02-07
 */
@Service
@Transactional(readOnly = true)
public class OaCrmProductService extends CrudService<OaCrmProductDao, OaCrmProduct> {

	@Autowired
	OaCrmProductDao dao;
	public OaCrmProduct get(String id) {
		return super.get(id);
	}
	
	public List<OaCrmProduct> findList(OaCrmProduct oaCrmProduct) {
		return super.findList(oaCrmProduct);
	}
	public List<OaCrmProduct> findOpenList(OaCrmProduct oaCrmProduct) {
		return dao.findOpenList(oaCrmProduct);
	}
	public  Page<OaCrmProduct> applyList(Page<OaCrmProduct> page,OaCrmProduct oaCrmProduct) {
		oaCrmProduct.setPage(page);
		page.setList(dao.findApplyList(oaCrmProduct));
		return page;
	}

	public Page<OaCrmProduct> findPage(Page<OaCrmProduct> page, OaCrmProduct oaCrmProduct) {
		return super.findPage(page, oaCrmProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(OaCrmProduct oaCrmProduct) {
		super.save(oaCrmProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaCrmProduct oaCrmProduct) {
		super.delete(oaCrmProduct);
	}
	
}