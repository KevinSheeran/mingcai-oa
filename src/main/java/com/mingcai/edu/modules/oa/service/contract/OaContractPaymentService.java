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
import com.mingcai.edu.modules.oa.entity.contract.OaContractPayment;
import com.mingcai.edu.modules.oa.dao.contract.OaContractPaymentDao;

/**
 * 付款情况Service
 * @author 坤
 * @version 2018-01-13
 */
@Service
@Transactional(readOnly = true)
public class OaContractPaymentService extends CrudService<OaContractPaymentDao, OaContractPayment> {
	@Autowired
	OaContractPaymentDao dao;
	public OaContractPayment get(String id) {
		return super.get(id);
	}
	
	public List<OaContractPayment> findList(OaContractPayment oaContractPayment) {
		return dao.findAllList(oaContractPayment);
	}
	
	public Page<OaContractPayment> findPage(Page<OaContractPayment> page, OaContractPayment oaContractPayment) {
		return super.findPage(page, oaContractPayment);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractPayment oaContractPayment) {
		super.save(oaContractPayment);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractPayment oaContractPayment) {
		super.delete(oaContractPayment);
	}
	
}