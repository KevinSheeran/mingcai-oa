/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;

import java.util.List;

/**
 * 销售项目立项DAO接口
 * @author kun
 * @version 2019-03-08
 */
@MyBatisDao
public interface OaEosProDao extends CrudDao<OaEosPro> {

    public void updateAudit(OaEosPro oep);

    //找到待审批列表
    public List<OaEosPro> findListByUser(OaEosPro oep);

    //找到审批完成列表
    public List<OaEosPro> findListByFlowFinishUser(OaEosPro oep);
    //查询所有
    public  List<OaEosPro> findAllList();

    public OaEosPro  selByprozid(OaEosPro oaEosPro);
    //统计首页功能项的数量
    public PageData index(PageData pd);
    //统计项目情况
    public PageData procount(PageData pd);

    void updatamoney(OaEosPro oaEosPro);
    //找到销售负责的项目
    public List<OaEosPro> findListByPersonLiableUser(OaEosPro oaEosPro);
    //查找销售项目
    public List<OaEosPro> findEosPro(OaEosPro oaEosPro);
}