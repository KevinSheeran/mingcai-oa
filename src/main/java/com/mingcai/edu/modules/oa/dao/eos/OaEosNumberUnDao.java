/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumberUn;

/**
 * 非销售编号DAO接口
 * @author 坤
 * @version 2019-04-10
 */
@MyBatisDao
public interface OaEosNumberUnDao extends CrudDao<OaEosNumberUn> {
	public void updateCheckedAll();
	public OaEosNumberUn getChecked();
}