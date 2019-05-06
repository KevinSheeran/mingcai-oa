/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.OaProductTask;

import java.util.List;

/**
 * 项目任务DAO接口
 * @author 江坤
 * @version 2017-11-30
 */
@MyBatisDao
public interface OaProductTaskDao extends CrudDao<OaProductTask> {
	public int countUnTask(String productId);
	public int countUserUnTask(String userId);
	public List<OaProductTask> findListByUser(OaProductTask entity);
}