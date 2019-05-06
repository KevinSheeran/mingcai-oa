/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.contract.OaContractPurchaseInfo;
import com.mingcai.edu.modules.oa.dao.contract.OaContractPurchaseInfoDao;

/**
 * 采购合同Service
 * @author 坤
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class OaContractPurchaseInfoService extends CrudService<OaContractPurchaseInfoDao, OaContractPurchaseInfo> {

	public OaContractPurchaseInfo get(String id) {
		return super.get(id);
	}
	
	public List<OaContractPurchaseInfo> findList(OaContractPurchaseInfo oaContractPurchaseInfo) {
		return super.findList(oaContractPurchaseInfo);
	}
	
	public Page<OaContractPurchaseInfo> findPage(Page<OaContractPurchaseInfo> page, OaContractPurchaseInfo oaContractPurchaseInfo) {
		return super.findPage(page, oaContractPurchaseInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractPurchaseInfo oaContractPurchaseInfo) {
		super.save(oaContractPurchaseInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractPurchaseInfo oaContractPurchaseInfo) {
		super.delete(oaContractPurchaseInfo);
	}
	
}