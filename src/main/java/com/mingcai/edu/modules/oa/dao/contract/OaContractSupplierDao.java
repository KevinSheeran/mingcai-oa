/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.contract;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplier;

/**
 * 供应商信息DAO接口
 * @author 坤
 * @version 2018-01-09
 */
@MyBatisDao
public interface OaContractSupplierDao extends CrudDao<OaContractSupplier> {
	
}