/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierFiles;
import com.mingcai.edu.modules.oa.dao.contract.OaContractSupplierFilesDao;

/**
 * 供应商产品附件Service
 * @author 坤
 * @version 2018-03-05
 */
@Service
@Transactional(readOnly = true)
public class OaContractSupplierFilesService extends CrudService<OaContractSupplierFilesDao, OaContractSupplierFiles> {

	public OaContractSupplierFiles get(String id) {
		return super.get(id);
	}
	
	public List<OaContractSupplierFiles> findList(OaContractSupplierFiles oaContractSupplierFiles) {
		return super.findList(oaContractSupplierFiles);
	}
	
	public Page<OaContractSupplierFiles> findPage(Page<OaContractSupplierFiles> page, OaContractSupplierFiles oaContractSupplierFiles) {
		return super.findPage(page, oaContractSupplierFiles);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractSupplierFiles oaContractSupplierFiles) {
		super.save(oaContractSupplierFiles);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractSupplierFiles oaContractSupplierFiles) {
		super.delete(oaContractSupplierFiles);
	}
	
}