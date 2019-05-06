/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.dao.OaFinanceCompanyDao;
import com.mingcai.edu.modules.oa.entity.OaFinanceCompany;

/**
 * 报销单位管理Service
 * @author 江坤
 * @version 2017-12-12
 */
@Service
@Transactional(readOnly = true)
public class OaFinanceCompanyService extends CrudService<OaFinanceCompanyDao, OaFinanceCompany> {

	public OaFinanceCompany get(String id) {
		return super.get(id);
	}
	
	public List<OaFinanceCompany> findList(OaFinanceCompany oaFinanceCompany) {
		return super.findList(oaFinanceCompany);
	}
	
	public Page<OaFinanceCompany> findPage(Page<OaFinanceCompany> page, OaFinanceCompany oaFinanceCompany) {
		return super.findPage(page, oaFinanceCompany);
	}
	
	@Transactional(readOnly = false)
	public void save(OaFinanceCompany oaFinanceCompany) {
		super.save(oaFinanceCompany);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaFinanceCompany oaFinanceCompany) {
		super.delete(oaFinanceCompany);
	}
	
}