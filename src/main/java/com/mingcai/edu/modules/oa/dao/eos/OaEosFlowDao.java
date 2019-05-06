/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlow;

import java.util.List;

/**
 * 流程DAO接口
 * @author kun
 * @version 2019-03-08
 */
@MyBatisDao
public interface OaEosFlowDao extends CrudDao<OaEosFlow> {
	public List<OaEosFlow> findListByEosId(OaEosFlow oef);
}