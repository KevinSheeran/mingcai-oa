/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.user.OaUserProSettlement;
import com.mingcai.edu.modules.oa.dao.user.OaUserProSettlementDao;

/**
 * 项目费用结算Service
 * @author kun
 * @version 2019-04-18
 */
@Service
@Transactional(readOnly = true)
public class OaUserProSettlementService extends CrudService<OaUserProSettlementDao, OaUserProSettlement> {

	public OaUserProSettlement get(String id) {
		return super.get(id);
	}
	
	public List<OaUserProSettlement> findList(OaUserProSettlement oaUserProSettlement) {
		return super.findList(oaUserProSettlement);
	}
	
	public Page<OaUserProSettlement> findPage(Page<OaUserProSettlement> page, OaUserProSettlement oaUserProSettlement) {
		return super.findPage(page, oaUserProSettlement);
	}
	
	@Transactional(readOnly = false)
	public void save(OaUserProSettlement oaUserProSettlement) {
		super.save(oaUserProSettlement);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaUserProSettlement oaUserProSettlement) {
		super.delete(oaUserProSettlement);
	}
	
}