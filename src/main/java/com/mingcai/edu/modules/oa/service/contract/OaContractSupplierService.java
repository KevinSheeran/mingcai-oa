/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplier;
import com.mingcai.edu.modules.oa.dao.contract.OaContractSupplierDao;

/**
 * 供应商信息Service
 * @author 坤
 * @version 2018-01-09
 */
@Service
@Transactional(readOnly = true)
public class OaContractSupplierService extends CrudService<OaContractSupplierDao, OaContractSupplier> {

	public OaContractSupplier get(String id) {
		return super.get(id);
	}
	
	public List<OaContractSupplier> findList(OaContractSupplier oaContractSupplier) {
		return super.findList(oaContractSupplier);
	}
	
	public Page<OaContractSupplier> findPage(Page<OaContractSupplier> page, OaContractSupplier oaContractSupplier) {
		return super.findPage(page, oaContractSupplier);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractSupplier oaContractSupplier) {
		super.save(oaContractSupplier);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractSupplier oaContractSupplier) {
		super.delete(oaContractSupplier);
	}
	
}