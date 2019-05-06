/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStart;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProUn;

import java.util.List;

/**
 * 非销售项目立项DAO接口
 * @author kun
 * @version 2019-03-27
 */
@MyBatisDao
public interface OaEosProUnDao extends CrudDao<OaEosProUn> {
    //找到待审批列表
    public List<OaEosProUn> findListByUser(OaEosProUn oep);

    //找到审批完成列表
    public List<OaEosProUn> findListByFlowFinishUser(OaEosProUn oep);

    public void updateAudit(OaEosProUn oep);
    public OaEosProUn selByprozid(OaEosProUn oaEosProUn);
    void updatamoney(OaEosProUn oaEosPro);
}