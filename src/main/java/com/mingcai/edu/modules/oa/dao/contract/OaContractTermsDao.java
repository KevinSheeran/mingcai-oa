/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.contract;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.contract.OaContractTerms;

/**
 * 合同项DAO接口
 * @author 坤
 * @version 2018-01-16
 */
@MyBatisDao
public interface OaContractTermsDao extends CrudDao<OaContractTerms> {
	public OaContractTerms getLast(OaContractTerms terms);
	public OaContractTerms sumPrice(OaContractTerms terms);
}