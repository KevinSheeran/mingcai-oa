/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaWxStageOfReimbursement;
import com.mingcai.edu.modules.oa.dao.eos.OaWxStageOfReimbursementDao;

/**
 * 报销阶段Service
 * @author 李艺杰
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly = true)
public class OaWxStageOfReimbursementService extends CrudService<OaWxStageOfReimbursementDao, OaWxStageOfReimbursement> {

	public OaWxStageOfReimbursement get(String id) {
		return super.get(id);
	}
	
	public List<OaWxStageOfReimbursement> findList(OaWxStageOfReimbursement oaWxStageOfReimbursement) {
		return super.findList(oaWxStageOfReimbursement);
	}
	
	public Page<OaWxStageOfReimbursement> findPage(Page<OaWxStageOfReimbursement> page, OaWxStageOfReimbursement oaWxStageOfReimbursement) {
		return super.findPage(page, oaWxStageOfReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void save(OaWxStageOfReimbursement oaWxStageOfReimbursement) {
		super.save(oaWxStageOfReimbursement);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaWxStageOfReimbursement oaWxStageOfReimbursement) {
		super.delete(oaWxStageOfReimbursement);
	}
	
}