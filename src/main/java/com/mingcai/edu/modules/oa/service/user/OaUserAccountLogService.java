/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccountLog;
import com.mingcai.edu.modules.oa.dao.user.OaUserAccountLogDao;

/**
 * 月结记录Service
 * @author kun
 * @version 2019-04-18
 */
@Service
@Transactional(readOnly = true)
public class OaUserAccountLogService extends CrudService<OaUserAccountLogDao, OaUserAccountLog> {

	public OaUserAccountLog get(String id) {
		return super.get(id);
	}
	
	public List<OaUserAccountLog> findList(OaUserAccountLog oaUserAccountLog) {
		return super.findList(oaUserAccountLog);
	}
	
	public Page<OaUserAccountLog> findPage(Page<OaUserAccountLog> page, OaUserAccountLog oaUserAccountLog) {
		return super.findPage(page, oaUserAccountLog);
	}
	
	@Transactional(readOnly = false)
	public void save(OaUserAccountLog oaUserAccountLog) {
		super.save(oaUserAccountLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaUserAccountLog oaUserAccountLog) {
		super.delete(oaUserAccountLog);
	}
	
}