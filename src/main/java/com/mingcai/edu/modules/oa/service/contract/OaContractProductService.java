/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.contract.OaContractProduct;
import com.mingcai.edu.modules.oa.dao.contract.OaContractProductDao;

/**
 * 销售产品信息Service
 * @author 坤
 * @version 2018-01-09
 */
@Service
@Transactional(readOnly = true)
public class OaContractProductService extends CrudService<OaContractProductDao, OaContractProduct> {

	public OaContractProduct get(String id) {
		return super.get(id);
	}
	
	public List<OaContractProduct> findList(OaContractProduct oaContractProduct) {
		return super.findList(oaContractProduct);
	}
	
	public Page<OaContractProduct> findPage(Page<OaContractProduct> page, OaContractProduct oaContractProduct) {
		return super.findPage(page, oaContractProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractProduct oaContractProduct) {
		super.save(oaContractProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractProduct oaContractProduct) {
		super.delete(oaContractProduct);
	}
	
}