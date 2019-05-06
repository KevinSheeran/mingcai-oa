/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 销售项目子项DAO接口
 * @author kun
 * @version 2019-03-08
 */
@MyBatisDao
public interface OaEosProItemDao extends CrudDao<OaEosProItem> {
  public   OaEosProItem  selByproid(OaEosProItem oaEosProItem);
  public  List <OaEosProItem> selByprolistid(OaEosProItem oaEosProItem);

  public List<OaEosProItem> findListinit();

  /**
   * 根据以立项id查询
   * @param oaEosProItem
   * @return
   */
  public List<OaEosProItem> findByIdList(OaEosProItem oaEosProItem);

  /**
   * 财务审核通过在把报销金额累加进去
   * @param oaEosProItem
   */
  public void updateCumulative(OaEosProItem oaEosProItem);

  Double itemBySum(@Param("eosId") String eosId);

}