/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.OaProductTaskLog;

/**
 * 任务日志DAO接口
 * @author 江坤
 * @version 2017-12-01
 */
@MyBatisDao
public interface OaProductTaskLogDao extends CrudDao<OaProductTaskLog> {
	public OaProductTaskLog getLast(OaProductTaskLog logs);
}