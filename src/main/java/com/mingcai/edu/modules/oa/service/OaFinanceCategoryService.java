/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.OaFinanceCategory;
import com.mingcai.edu.modules.oa.dao.OaFinanceCategoryDao;

/**
 * 报销类目管理Service
 * @author 江坤
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class OaFinanceCategoryService extends CrudService<OaFinanceCategoryDao, OaFinanceCategory> {

	public OaFinanceCategory get(String id) {
		return super.get(id);
	}
	
	public List<OaFinanceCategory> findList(OaFinanceCategory oaFinanceCategory) {
		return super.findList(oaFinanceCategory);
	}
	
	public Page<OaFinanceCategory> findPage(Page<OaFinanceCategory> page, OaFinanceCategory oaFinanceCategory) {
		return super.findPage(page, oaFinanceCategory);
	}
	
	@Transactional(readOnly = false)
	public void save(OaFinanceCategory oaFinanceCategory) {
		super.save(oaFinanceCategory);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaFinanceCategory oaFinanceCategory) {
		super.delete(oaFinanceCategory);
	}
	
}