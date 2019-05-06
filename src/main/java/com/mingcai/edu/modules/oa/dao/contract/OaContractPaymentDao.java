/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.contract;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.contract.OaContractPayment;

/**
 * 付款情况DAO接口
 * @author 坤
 * @version 2018-01-13
 */
@MyBatisDao
public interface OaContractPaymentDao extends CrudDao<OaContractPayment> {
	
}