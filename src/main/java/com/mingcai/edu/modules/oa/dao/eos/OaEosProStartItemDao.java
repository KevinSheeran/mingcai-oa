/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 立项子项目DAO接口
 * @author kun
 * @version 2019-03-21
 */
@MyBatisDao
public interface OaEosProStartItemDao extends CrudDao<OaEosProStartItem> {
    /**
     * 根据以立项id查询
     * @param oaEosProStartItem
     * @return
     */
	public List<OaEosProStartItem> findByIdList(OaEosProStartItem oaEosProStartItem);

    /**
     * 财务审核通过在把报销金额累加进去
     * @param oaEosProStartItem
     */
    public void updateCumulative(OaEosProStartItem oaEosProStartItem);

    public OaEosProStartItem selByproid(OaEosProStartItem oaEosProStartItem);

    public  List <OaEosProStartItem> selByprolistid(OaEosProStartItem oaEosProStartItem);

    Double itemBySum(@Param("eosId") String eosId);

}