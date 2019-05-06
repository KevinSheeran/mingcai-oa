/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.OaProductPrice;

/**
 * 产品报价版本DAO接口
 * @author 坤
 * @version 2018-05-07
 */
@MyBatisDao
public interface OaProductPriceDao extends CrudDao<OaProductPrice> {
	
}