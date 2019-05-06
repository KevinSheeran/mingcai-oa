/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.OaFinanceEa;
import com.mingcai.edu.modules.oa.entity.OaFinanceEaDetail;

/**
 * 报销详情DAO接口
 * 
 * @author hxy
 * @version 2017-12-12
 */
@MyBatisDao
public interface OaFinanceEaDetailDao extends CrudDao<OaFinanceEaDetail> {

}