/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.wx;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import com.mingcai.edu.modules.oa.dao.wx.OaWxUsersDao;

/**
 * 微信用户Service
 * @author kun
 * @version 2019-03-05
 */
@Service
@Transactional(readOnly = true)
public class OaWxUsersService extends CrudService<OaWxUsersDao, OaWxUsers> {

	public OaWxUsers get(String id) {
		return super.get(id);
	}
	
	public List<OaWxUsers> findList(OaWxUsers oaWxUsers) {
		return super.findList(oaWxUsers);
	}
	public List<OaWxUsers> findListAll(OaWxUsers oaWxUsers) {
		return dao.findAllList(oaWxUsers);
	}
	public Page<OaWxUsers> findPage(Page<OaWxUsers> page, OaWxUsers oaWxUsers) {
		return super.findPage(page, oaWxUsers);
	}

	@Transactional(readOnly = false)
	public void save(OaWxUsers oaWxUsers) {
		super.save(oaWxUsers);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaWxUsers oaWxUsers) {
		super.delete(oaWxUsers);
	}
	
}