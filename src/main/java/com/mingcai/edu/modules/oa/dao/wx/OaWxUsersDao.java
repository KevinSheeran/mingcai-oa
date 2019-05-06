/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.wx;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;

/**
 * 微信用户DAO接口
 * @author kun
 * @version 2019-03-05
 */
@MyBatisDao
public interface OaWxUsersDao extends CrudDao<OaWxUsers> {
	
}