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
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierProduct;
import com.mingcai.edu.modules.oa.dao.contract.OaContractSupplierProductDao;

/**
 * 供应商产品信息Service
 * @author 坤
 * @version 2018-01-09
 */
@Service
@Transactional(readOnly = true)
public class OaContractSupplierProductService extends CrudService<OaContractSupplierProductDao, OaContractSupplierProduct> {
	@Autowired
	OaContractSupplierProductDao dao;
	public OaContractSupplierProduct get(String id) {
		return super.get(id);
	}
	
	public List<OaContractSupplierProduct> findList(OaContractSupplierProduct oaContractSupplierProduct) {
		return super.findList(oaContractSupplierProduct);
	}
	public List<OaContractSupplierProduct> findAllList(OaContractSupplierProduct oaContractSupplierProduct) {
		return dao.findAllList(oaContractSupplierProduct);
	}
	public Page<OaContractSupplierProduct> findPage(Page<OaContractSupplierProduct> page, OaContractSupplierProduct oaContractSupplierProduct) {
		return super.findPage(page, oaContractSupplierProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractSupplierProduct oaContractSupplierProduct) {
		super.save(oaContractSupplierProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractSupplierProduct oaContractSupplierProduct) {
		super.delete(oaContractSupplierProduct);
	}
	
}