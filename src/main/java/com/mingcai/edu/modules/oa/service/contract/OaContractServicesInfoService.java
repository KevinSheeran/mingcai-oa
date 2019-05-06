/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.contract;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.contract.OaContractServicesInfo;
import com.mingcai.edu.modules.oa.dao.contract.OaContractServicesInfoDao;

/**
 * 劳务合同Service
 * @author 坤
 * @version 2018-01-12
 */
@Service
@Transactional(readOnly = true)
public class OaContractServicesInfoService extends CrudService<OaContractServicesInfoDao, OaContractServicesInfo> {

	public OaContractServicesInfo get(String id) {
		return super.get(id);
	}
	
	public List<OaContractServicesInfo> findList(OaContractServicesInfo oaContractServicesInfo) {
		return super.findList(oaContractServicesInfo);
	}
	
	public Page<OaContractServicesInfo> findPage(Page<OaContractServicesInfo> page, OaContractServicesInfo oaContractServicesInfo) {
		return super.findPage(page, oaContractServicesInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractServicesInfo oaContractServicesInfo) {
		super.save(oaContractServicesInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractServicesInfo oaContractServicesInfo) {
		super.delete(oaContractServicesInfo);
	}
	
}