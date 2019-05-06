/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.dao.contract;

import com.mingcai.edu.common.persistence.CrudDao;
import com.mingcai.edu.common.persistence.annotation.MyBatisDao;
import com.mingcai.edu.modules.oa.entity.contract.OaContractPurchaseTerms;

import java.util.List;

/**
 * 采购合同关联DAO接口
 * @author 坤
 * @version 2018-01-16
 */
@MyBatisDao
public interface OaContractPurchaseTermsDao extends CrudDao<OaContractPurchaseTerms> {

    public List<OaContractPurchaseTerms> findTremsList(OaContractPurchaseTerms terms);
    public OaContractPurchaseTerms countNumber(OaContractPurchaseTerms terms);

    public List<OaContractPurchaseTerms> getTremList(String id);

    public OaContractPurchaseTerms sumMoney(OaContractPurchaseTerms terms);
}