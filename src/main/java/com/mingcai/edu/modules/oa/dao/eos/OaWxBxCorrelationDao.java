/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaWxBxCorrelation;
import com.mingcai.edu.modules.oa.entity.eos.OaWxExtended;

import java.util.List;

/**
 * 基本报销关联DAO接口
 * @author 李艺杰
 * @version 2019-04-10
 */
@MyBatisDao
public interface OaWxBxCorrelationDao extends CrudDao<OaWxBxCorrelation> {

    public List<OaWxBxCorrelation> findBySuperId(OaWxBxCorrelation oaWxBxCorrelation);
    public  List<OaWxBxCorrelation> selByProId(String proid);
}