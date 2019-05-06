/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.contract;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.contract.OaContractServicesInfo;

/**
 * 劳务合同DAO接口
 * @author 坤
 * @version 2018-01-12
 */
@MyBatisDao
public interface OaContractServicesInfoDao extends CrudDao<OaContractServicesInfo> {
	
}