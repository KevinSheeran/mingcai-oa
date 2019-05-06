/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.OaFinanceEa;

import java.math.BigDecimal;

/**
 * 报销管理DAO接口
 * 
 * @author 江坤
 * @version 2017-12-12
 */
@MyBatisDao
public interface OaFinanceEaDao extends CrudDao<OaFinanceEa> {

	public OaFinanceEa getByProcInsId(String procInsId);

	public void updateApproverStatus(String firstApproverStatus, String secondApproverStatus,
			String thirdApproverStatus,String status, String id);
	
	public void deleteInactivationEx(String processInstanceId);

	public BigDecimal findMoneyById(String eaId);

}