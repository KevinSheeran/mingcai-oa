/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccountUserLog;
import com.mingcai.edu.modules.oa.dao.user.OaUserAccountUserLogDao;

/**
 * 记录用户账户操作信息Service
 * @author 坤
 * @version 2019-04-30
 */
@Service
@Transactional(readOnly = true)
public class OaUserAccountUserLogService extends CrudService<OaUserAccountUserLogDao, OaUserAccountUserLog> {

	public OaUserAccountUserLog get(String id) {
		return super.get(id);
	}
	
	public List<OaUserAccountUserLog> findList(OaUserAccountUserLog oaUserAccountUserLog) {
		return super.findList(oaUserAccountUserLog);
	}
	
	public Page<OaUserAccountUserLog> findPage(Page<OaUserAccountUserLog> page, OaUserAccountUserLog oaUserAccountUserLog) {
		return super.findPage(page, oaUserAccountUserLog);
	}
	
	@Transactional(readOnly = false)
	public void save(OaUserAccountUserLog oaUserAccountUserLog) {
		super.save(oaUserAccountUserLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaUserAccountUserLog oaUserAccountUserLog) {
		super.delete(oaUserAccountUserLog);
	}
	
}