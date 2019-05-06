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
import com.mingcai.edu.modules.oa.entity.contract.OaContractPurchaseTerms;
import com.mingcai.edu.modules.oa.dao.contract.OaContractPurchaseTermsDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 采购合同关联Service
 * @author 坤
 * @version 2018-01-16
 */
@Service
@Transactional(readOnly = true)
public class OaContractPurchaseTermsService extends CrudService<OaContractPurchaseTermsDao, OaContractPurchaseTerms> {
	@Autowired
	OaContractPurchaseTermsDao dao;
	public OaContractPurchaseTerms get(String id) {
		return super.get(id);
	}
	
	public List<OaContractPurchaseTerms> findList(OaContractPurchaseTerms oaContractPurchaseTerms) {
		return super.findList(oaContractPurchaseTerms);
	}
	public OaContractPurchaseTerms sumMoney(OaContractPurchaseTerms oaContractPurchaseTerms) {
		return dao.sumMoney(oaContractPurchaseTerms);
	}
	public List<OaContractPurchaseTerms> getTremList(String id) {
		return dao.getTremList(id);
	}
	public List<OaContractPurchaseTerms> findTremsList(OaContractPurchaseTerms oaContractPurchaseTerms) {
		return dao.findTremsList(oaContractPurchaseTerms);
	}
	public Page<OaContractPurchaseTerms> findPage(Page<OaContractPurchaseTerms> page, OaContractPurchaseTerms oaContractPurchaseTerms) {
		return super.findPage(page, oaContractPurchaseTerms);
	}
	public OaContractPurchaseTerms countNumber(OaContractPurchaseTerms oaContractPurchaseTerms) {
		return dao.countNumber(oaContractPurchaseTerms);
	}
	@Transactional(readOnly = false)
	public void save(HttpServletRequest request) {
		String purchaseId =request.getParameter("purchaseId");
		String finProductId =request.getParameter("finProductId");
		String[] ids=request.getParameterValues("trem");
		if(ids.length>0) {
			for(String id:ids) {
				OaContractPurchaseTerms terms = new OaContractPurchaseTerms();
				terms.setPurchaseId(purchaseId);
				terms.setTremId(id);
				terms.setFinProductId(finProductId);
				super.save(terms);
			}
		}
	}
	@Transactional(readOnly = false)
	public void edit(OaContractPurchaseTerms oaContractPurchaseTerms) {
		super.save(oaContractPurchaseTerms);
	}
	@Transactional(readOnly = false)
	public void delete(OaContractPurchaseTerms oaContractPurchaseTerms) {
		super.delete(oaContractPurchaseTerms);
	}
	
}