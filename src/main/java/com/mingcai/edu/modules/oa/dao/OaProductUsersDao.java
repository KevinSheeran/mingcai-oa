/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.OaProductUsers;

import java.util.List;

/**
 * 项目组用户DAO接口
 * @author 江坤
 * @version 2017-11-29
 */
@MyBatisDao
public interface OaProductUsersDao extends CrudDao<OaProductUsers> {
	public List<OaProductUsers> findListByproductId(OaProductUsers pusers);
	public void delListByproductId(OaProductUsers pusers);

	public OaProductUsers countProductId(OaProductUsers user);
}