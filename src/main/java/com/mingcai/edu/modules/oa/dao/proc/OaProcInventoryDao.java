/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.proc;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.proc.OaProcInventory;

/**
 * 采购清单DAO接口
 * @author 谢一郡
 * @version 2019-05-06
 */
@MyBatisDao
public interface OaProcInventoryDao extends CrudDao<OaProcInventory> {
	
}