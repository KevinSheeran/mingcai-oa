/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowItem;

import java.util.List;

/**
 * 流程项DAO接口
 * @author 坤
 * @version 2019-03-18
 */
@MyBatisDao
public interface OaEosFlowItemDao extends CrudDao<OaEosFlowItem> {
	public List<OaEosFlowItem> findListByFlowId(OaEosFlowItem oaEosFlowItem);
}