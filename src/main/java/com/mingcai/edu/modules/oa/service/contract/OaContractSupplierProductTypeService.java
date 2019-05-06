/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierProductType;
import com.mingcai.edu.modules.oa.dao.contract.OaContractSupplierProductTypeDao;

/**
 * 供应商产品标签Service
 * @author 坤
 * @version 2018-03-05
 */
@Service
@Transactional(readOnly = true)
public class OaContractSupplierProductTypeService extends CrudService<OaContractSupplierProductTypeDao, OaContractSupplierProductType> {

	public OaContractSupplierProductType get(String id) {
		return super.get(id);
	}
	
	public List<OaContractSupplierProductType> findList(OaContractSupplierProductType oaContractSupplierProductType) {
		return super.findList(oaContractSupplierProductType);
	}
	
	public Page<OaContractSupplierProductType> findPage(Page<OaContractSupplierProductType> page, OaContractSupplierProductType oaContractSupplierProductType) {
		return super.findPage(page, oaContractSupplierProductType);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractSupplierProductType oaContractSupplierProductType) {
		super.save(oaContractSupplierProductType);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractSupplierProductType oaContractSupplierProductType) {
		super.delete(oaContractSupplierProductType);
	}
	
}