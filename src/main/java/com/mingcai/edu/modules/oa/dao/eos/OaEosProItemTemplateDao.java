/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItemTemplate;

import java.util.List;
import java.util.TreeMap;

/**
 * 销售立项子项模板DAO接口
 * @author 李艺杰
 * @version 2019-04-22
 */
@MyBatisDao
public interface OaEosProItemTemplateDao extends CrudDao<OaEosProItemTemplate> {

    List<PageData> findMap(PageData pageData);
}