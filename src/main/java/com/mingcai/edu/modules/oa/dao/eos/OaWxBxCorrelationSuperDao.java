/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.eos;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.eos.OaWxBxCorrelationSuper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 报销主表DAO接口
 * @author 李艺杰
 * @version 2019-04-11
 */
@MyBatisDao
public interface OaWxBxCorrelationSuperDao extends CrudDao<OaWxBxCorrelationSuper> {

    List<OaWxBxCorrelationSuper> findProcessedList(@Param("userId") String userId,OaWxBxCorrelationSuper wesuper);

    List<OaWxBxCorrelationSuper> findUntreatedList(@Param("userId") String userId, OaWxBxCorrelationSuper wesuper);

    List<OaWxBxCorrelationSuper> findFinanceList(OaWxBxCorrelationSuper wesuper);

    List<OaWxBxCorrelationSuper>   findFinanceExcel(@Param("kssj") String kssj,@Param("jssj")String jssj);

    OaWxBxCorrelationSuper get1(@Param("id") String id);
    OaWxBxCorrelationSuper get2(@Param("extendedSuperId") String extendedSuperId);
}