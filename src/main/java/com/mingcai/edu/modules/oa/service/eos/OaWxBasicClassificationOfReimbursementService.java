/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaWxBasicClassificationOfReimbursement;
import com.mingcai.edu.modules.oa.dao.eos.OaWxBasicClassificationOfReimbursementDao;

/**
 * 报销分类Service
 * @author 李艺杰
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly = true)
public class OaWxBasicClassificationOfReimbursementService extends CrudService<OaWxBasicClassificationOfReimbursementDao, OaWxBasicClassificationOfReimbursement> {

	public OaWxBasicClassificationOfReimbursement get(String id) {
		return super.get(id);
	}
	
	public List<OaWxBasicClassificationOfReimbursement> findList(OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursement) {
		return super.findList(oaWxBasicClassificationOfReimbursement);
	}
	
	public Page<OaWxBasicClassificationOfReimbursement> findPage(Page<OaWxBasicClassificationOfReimbursement> page, OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursement) {
		return super.findPage(page, oaWxBasicClassificationOfReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void save(OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursement) {
		super.save(oaWxBasicClassificationOfReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursement) {
		super.delete(oaWxBasicClassificationOfReimbursement);
	}
	
}