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
import com.mingcai.edu.modules.oa.entity.contract.OaContractInvoice;
import com.mingcai.edu.modules.oa.dao.contract.OaContractInvoiceDao;

/**
 * 开票信息Service
 * @author 坤
 * @version 2018-01-18
 */
@Service
@Transactional(readOnly = true)
public class OaContractInvoiceService extends CrudService<OaContractInvoiceDao, OaContractInvoice> {
	@Autowired
	OaContractInvoiceDao dao;
	public OaContractInvoice get(String id) {
		return super.get(id);
	}
	
	public List<OaContractInvoice> findList(OaContractInvoice oaContractInvoice) {
		return super.findList(oaContractInvoice);
	}
	public OaContractInvoice sumMoney(OaContractInvoice oaContractInvoice) {
		return dao.sumMoney(oaContractInvoice);
	}
	public Page<OaContractInvoice> findPage(Page<OaContractInvoice> page, OaContractInvoice oaContractInvoice) {
		return super.findPage(page, oaContractInvoice);
	}

	@Transactional(readOnly = false)
	public void save(OaContractInvoice oaContractInvoice) {
		super.save(oaContractInvoice);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractInvoice oaContractInvoice) {
		super.delete(oaContractInvoice);
	}
	
}