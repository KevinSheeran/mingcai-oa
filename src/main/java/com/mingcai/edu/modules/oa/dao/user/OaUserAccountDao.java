/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.user;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccount;

import java.util.List;

/**
 * 用户账户DAO接口
 * @author kun
 * @version 2019-04-18
 */
@MyBatisDao
public interface OaUserAccountDao extends CrudDao<OaUserAccount> {
	public OaUserAccount getBySales(OaUserAccount account);
	public List<OaUserAccount> findListByUser(OaUserAccount account);
	public List<OaUserAccount> findListByFlowFinishUser(OaUserAccount account);
}