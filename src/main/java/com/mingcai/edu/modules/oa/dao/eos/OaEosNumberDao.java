/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumber;

/**
 * 项目编号DAO接口
 * @author 李艺杰
 * @version 2019-04-03
 */
@MyBatisDao
public interface OaEosNumberDao extends CrudDao<OaEosNumber> {

    /**
     * 根据自定义编号查询
     * @param oaEosNumber
     * @return
     */
    public OaEosNumber getNumberByMobile(OaEosNumber oaEosNumber);
	
}