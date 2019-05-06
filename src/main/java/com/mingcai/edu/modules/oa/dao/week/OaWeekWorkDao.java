/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.week;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.week.OaWeekWork;

/**
 * 周任务DAO接口
 * @author 坤
 * @version 2018-01-24
 */
@MyBatisDao
public interface OaWeekWorkDao extends CrudDao<OaWeekWork> {
    public OaWeekWork getByDate(OaWeekWork oaWeekWork);
}