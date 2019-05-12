/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStart;

import java.util.List;

/**
 * 立项启动DAO接口
 * @author 坤
 * @version 2019-03-21
 */
@MyBatisDao
public interface OaEosProStartDao extends CrudDao<OaEosProStart> {
	public void updateAudit(OaEosProStart oaEosProStart);
	//找到审批完成列表
	public List<OaEosProStart> findListByFlowFinishUser(OaEosProStart oep);
	//找到待审批列表
	public List<OaEosProStart> findListByUser(OaEosProStart oep);
	public List<OaEosProStart> findPcList(OaEosProStart start);
	void updatamoney(OaEosProStart oaEosPro);
	//查询立项项目
    public  List<OaEosProStart> findStartProgram(OaEosProStart oaEosProStart);
}