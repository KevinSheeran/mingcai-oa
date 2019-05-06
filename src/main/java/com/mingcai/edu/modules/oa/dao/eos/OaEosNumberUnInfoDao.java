/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumberUnInfo;

/**
 * 非销售自编号DAO接口
 * @author 坤
 * @version 2019-04-10
 */
@MyBatisDao
public interface OaEosNumberUnInfoDao extends CrudDao<OaEosNumberUnInfo> {
    public OaEosNumberUnInfo getMaxCode(OaEosNumberUnInfo info);
}