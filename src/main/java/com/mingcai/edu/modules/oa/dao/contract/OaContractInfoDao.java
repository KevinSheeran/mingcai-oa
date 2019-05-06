/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.contract;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.contract.OaContractInfo;

/**
 * 合同管理DAO接口
 * @author 坤
 * @version 2018-01-10
 */
@MyBatisDao
public interface OaContractInfoDao extends CrudDao<OaContractInfo> {
	public OaContractInfo getPro(String id);
	public OaContractInfo getByName(String name);
}