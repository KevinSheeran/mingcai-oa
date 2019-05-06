/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.user;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccountUserLog;

/**
 * 记录用户账户操作信息DAO接口
 * @author 坤
 * @version 2019-04-30
 */
@MyBatisDao
public interface OaUserAccountUserLogDao extends CrudDao<OaUserAccountUserLog> {
	
}