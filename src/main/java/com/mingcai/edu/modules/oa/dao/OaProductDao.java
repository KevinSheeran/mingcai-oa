/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.OaProduct;

/**
 * 项目信息DAO接口
 * @author 江坤
 * @version 2017-11-29
 */
@MyBatisDao
public interface OaProductDao extends CrudDao<OaProduct> {
	    public int insertProductUser(OaProduct p);
	    public int deletePUser(OaProduct p);
}