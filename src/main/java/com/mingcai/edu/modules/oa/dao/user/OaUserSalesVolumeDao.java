/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.user;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.user.OaUserSalesVolume;

/**
 * 申请销售额DAO接口
 * @author kun
 * @version 2019-04-18
 */
@MyBatisDao
public interface OaUserSalesVolumeDao extends CrudDao<OaUserSalesVolume> {
	public OaUserSalesVolume getByUser(OaUserSalesVolume oaUserSalesVolume);
}