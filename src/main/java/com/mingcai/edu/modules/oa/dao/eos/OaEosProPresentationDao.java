/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProPresentation;

import java.util.List;

/**
 * 项目周报DAO接口
 * @author kun
 * @version 2019-03-25
 */
@MyBatisDao
public interface OaEosProPresentationDao extends CrudDao<OaEosProPresentation> {
	public OaEosProPresentation getByDate(OaEosProPresentation oaEosProPresentation);
	public List<OaEosProPresentation> getListAllByDate(OaEosProPresentation oaEosProPresentation);
}