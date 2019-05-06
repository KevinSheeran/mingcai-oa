/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.contract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.contract.OaContractTerms;
import com.mingcai.edu.modules.oa.dao.contract.OaContractTermsDao;

/**
 * 合同项Service
 * @author 坤
 * @version 2018-01-16
 */
@Service
@Transactional(readOnly = true)
public class OaContractTermsService extends CrudService<OaContractTermsDao, OaContractTerms> {
	@Autowired
	OaContractTermsDao dao;
	public OaContractTerms get(String id) {
		return super.get(id);
	}
	public OaContractTerms sumPrice(OaContractTerms oaContractTerms) {
		return dao.sumPrice(oaContractTerms);
	}
	
	public List<OaContractTerms> findList(OaContractTerms oaContractTerms) {
		return dao.findAllList(oaContractTerms);
	}
	
	public Page<OaContractTerms> findPage(Page<OaContractTerms> page, OaContractTerms oaContractTerms) {
		return super.findPage(page, oaContractTerms);
	}
	public OaContractTerms getLast(OaContractTerms oaContractTerms) {
		return dao.getLast(oaContractTerms);
	}
	@Transactional(readOnly = false)
	public void save(OaContractTerms oaContractTerms) {
		super.save(oaContractTerms);

	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractTerms oaContractTerms) {
		super.delete(oaContractTerms);
	}
	
}