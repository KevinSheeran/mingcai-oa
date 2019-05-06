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
import com.mingcai.edu.modules.oa.entity.contract.OaContractInfo;
import com.mingcai.edu.modules.oa.dao.contract.OaContractInfoDao;

/**
 * 合同管理Service
 * @author 坤
 * @version 2018-01-10
 */
@Service
@Transactional(readOnly = true)
public class OaContractInfoService extends CrudService<OaContractInfoDao, OaContractInfo> {
	@Autowired
	OaContractInfoDao dao;
	public OaContractInfo get(String id) {
		return super.get(id);
	}
	public OaContractInfo getPro(String id) {
		return dao.getPro(id);
	}
	public OaContractInfo getByName(String id) {
		return dao.getByName(id);
	}
	public List<OaContractInfo> findList(OaContractInfo oaContractInfo) {
		return super.findList(oaContractInfo);
	}
	
	public Page<OaContractInfo> findPage(Page<OaContractInfo> page, OaContractInfo oaContractInfo) {
		return super.findPage(page, oaContractInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(OaContractInfo oaContractInfo) {
		super.save(oaContractInfo);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaContractInfo oaContractInfo) {
		super.delete(oaContractInfo);
	}
	
}