/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.modules.oa.entity.eos.OaWxExtended;

import java.util.List;

/**
 * 微信报销详情DAO接口
 * @author 李艺杰
 * @version 2019-03-11
 */
@MyBatisDao
public interface OaWxExtendedDao extends CrudDao<OaWxExtended> {


    public   void  updateAudit(OaWxExtended oaWxExtended);
   public   OaWxExtended  findByid(OaWxExtended oaWxExtended);

    public   List<OaWxExtended>  findBylistid(OaWxExtended oaWxExtended);

    public List<OaWxExtended> findBySuperId(OaWxExtended oaWxExtended);

    public List<OaWxExtended> findBySuperProId (OaWxExtended oaWxExtended);

    public List<PageData> proCount(PageData pd);

    public  List<PageData> proCountItem(PageData pd);

}