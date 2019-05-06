/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.OaProductResources;

/**
 * 项目资源DAO接口
 * @author 江坤
 * @version 2017-12-04
 */
@MyBatisDao
public interface OaProductResourcesDao extends CrudDao<OaProductResources> {
	public int updateAllRes(OaProductResources res);
	public int updatePath(OaProductResources res);
}