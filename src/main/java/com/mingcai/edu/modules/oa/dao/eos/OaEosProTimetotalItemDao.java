/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProTimetotal;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProTimetotalItem;

/**
 * 汇总详情DAO接口
 * @author kun
 * @version 2019-04-01
 */
@MyBatisDao
public interface OaEosProTimetotalItemDao extends CrudDao<OaEosProTimetotalItem> {
    /***
     * 根据项目id 周获取项
     * @param item
     * @return
     */
	    public OaEosProTimetotalItem getByTimes(OaEosProTimetotalItem item);
}