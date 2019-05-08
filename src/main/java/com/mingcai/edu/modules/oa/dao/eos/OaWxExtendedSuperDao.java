/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.modules.oa.entity.eos.OaWxExtended;
import com.mingcai.edu.modules.oa.entity.eos.OaWxExtendedSuper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 报销主表DAO接口
 * @author 李艺杰
 * @version 2019-03-23
 */
@MyBatisDao
public interface OaWxExtendedSuperDao extends CrudDao<OaWxExtendedSuper> {

    public List<OaWxExtended> getByProId(String priid);
    public   void  updateAudit(OaWxExtendedSuper oaWxExtendedSuper);
    public   OaWxExtendedSuper  findByid(OaWxExtendedSuper oaWxExtendedSuper);
    public  void updateApproPriAtion(OaWxExtendedSuper oaWxExtendedSuper);

    /***
     * 找到未处理报销
     * @param oaWxExtendedSuper
     * @return
     */
    public List<OaWxExtendedSuper> findListByUser(OaWxExtendedSuper oaWxExtendedSuper);
    /***
     * 找到已处理报销
     * @param oaWxExtendedSuper
     * @return
     */
    public List<OaWxExtendedSuper> findFinishListByUser(OaWxExtendedSuper oaWxExtendedSuper);

    /**
     * 已处理
     * @param userId
     * @return
     */
    public  List<OaWxExtendedSuper> findProcessedList(@Param("userId") String userId,OaWxExtendedSuper OaWxExtendedSuper);

    /**
     * 未处理
     * @param userId
     * @return
     */
    public  List<OaWxExtendedSuper> findUntreatedList(@Param("userId") String userId,OaWxExtendedSuper OaWxExtendedSuper);

    /**
     * 财务 
     *
     * @return
     */
    public  List<OaWxExtendedSuper> findFinanceList(OaWxExtendedSuper wesuper);


    List<OaWxExtendedSuper> findByProId(OaWxExtendedSuper oaWxExtendedSuper);

    public PageData sumProUser(OaWxExtendedSuper oaWxExtendedSuper);
}