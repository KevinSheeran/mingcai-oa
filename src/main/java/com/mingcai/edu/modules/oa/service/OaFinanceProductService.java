/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.OaFinanceProduct;
import com.mingcai.edu.modules.oa.dao.OaFinanceProductDao;

/**
 * 报销项目管理Service
 * @author 江坤
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class OaFinanceProductService extends CrudService<OaFinanceProductDao, OaFinanceProduct> {

	public OaFinanceProduct get(String id) {
		return super.get(id);
	}
	
	public List<OaFinanceProduct> findList(OaFinanceProduct oaFinanceProduct) {
		return super.findList(oaFinanceProduct);
	}
	
	public Page<OaFinanceProduct> findPage(Page<OaFinanceProduct> page, OaFinanceProduct oaFinanceProduct) {
		return super.findPage(page, oaFinanceProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(OaFinanceProduct oaFinanceProduct) {
		super.save(oaFinanceProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaFinanceProduct oaFinanceProduct) {
		super.delete(oaFinanceProduct);
	}
	
}