/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.utils;

import java.util.ArrayList;
import java.util.List;

import com.mingcai.edu.common.utils.NumberUtils;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.common.utils.SpringContextHolder;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.dao.OaFinanceCategoryDao;
import com.mingcai.edu.modules.oa.dao.OaFinanceCompanyDao;
import com.mingcai.edu.modules.oa.dao.OaFinanceProductDao;
import com.mingcai.edu.modules.oa.dao.contract.OaContractProductDao;
import com.mingcai.edu.modules.oa.dao.contract.OaContractSupplierDao;
import com.mingcai.edu.modules.oa.dao.contract.OaContractTermsDao;
import com.mingcai.edu.modules.oa.dao.eos.OaWxExtendedSuperDao;
import com.mingcai.edu.modules.oa.entity.OaFinanceCategory;
import com.mingcai.edu.modules.oa.entity.OaFinanceCompany;
import com.mingcai.edu.modules.oa.entity.OaFinanceProduct;
import com.mingcai.edu.modules.oa.entity.contract.OaContractProduct;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplier;
import com.mingcai.edu.modules.oa.entity.contract.OaContractTerms;
import com.mingcai.edu.modules.oa.entity.eos.OaWxExtendedSuper;

/**
 * 财务工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
public class EAUtils {

	private static OaFinanceCategoryDao oaFinanceCategoryDao = SpringContextHolder.getBean(OaFinanceCategoryDao.class);
	private static OaFinanceProductDao oaFinanceProductDao = SpringContextHolder.getBean(OaFinanceProductDao.class);
	private static OaFinanceCompanyDao oaFinanceCompanyDao = SpringContextHolder.getBean(OaFinanceCompanyDao.class);
	private static OaContractSupplierDao oaContractSupplierDao = SpringContextHolder.getBean(OaContractSupplierDao.class);
	private static OaContractProductDao oaContractProductDao = SpringContextHolder.getBean(OaContractProductDao.class);
	private static OaContractTermsDao oaContractTermDao = SpringContextHolder.getBean(OaContractTermsDao.class);
	private static OaWxExtendedSuperDao oaWxExtendedSuperDao = SpringContextHolder.getBean(OaWxExtendedSuperDao.class);
	/***
	 * 获取报销类目集合
	 * @return
	 */
	public static List<OaFinanceCategory> getCategory(){
		List<OaFinanceCategory> cats=new ArrayList<OaFinanceCategory>();
		cats=oaFinanceCategoryDao.findAllList(new OaFinanceCategory());
		return cats;
	}

	/***
	 * 获取报销项目集合
	 * @return
	 */
	public static List<OaFinanceProduct> getProduct(){
		List<OaFinanceProduct> pros=new ArrayList<OaFinanceProduct>();
		pros=oaFinanceProductDao.findAllList(new OaFinanceProduct());
		return pros;
	}
	
	/***
	 * 获取报销单位集合
	 * @return
	 */
	public static List<OaFinanceCompany> getCompany(){
		List<OaFinanceCompany> coms=new ArrayList<OaFinanceCompany>();
		coms=oaFinanceCompanyDao.findAllList(new OaFinanceCompany());
		return coms;
	}/***
	 * 获取供应商
	 * @return
	 */
	public static List<OaContractSupplier> getSupplr(){
		List<OaContractSupplier> coms=new ArrayList<OaContractSupplier>();
		coms=oaContractSupplierDao.findAllList(new OaContractSupplier());
		return coms;
	}
	/***
	 * 获取销售产品
	 * @return
	 */
	public static List<OaContractProduct> getSalePro(String id){
		List<OaContractProduct> coms=new ArrayList<OaContractProduct>();
		OaContractProduct pro=new OaContractProduct();
		pro.setContract_id(id);
		coms=oaContractProductDao.findAllList(pro);
		return coms;
	}
	/***
	 * 获取合同项
	 * @return
	 */
	public static List<OaContractTerms> getTrem(String id){
		List<OaContractTerms> coms=new ArrayList<OaContractTerms>();
		OaContractTerms pro=new OaContractTerms();
		pro.setContractId(id);
		coms=oaContractTermDao.findAllList(pro);
		return coms;
	}
	/***
	 * 获取个人项目报销金额
	 * @return
	 */
	public static String sumMoney(String proId,String userid){
		OaWxExtendedSuper owesuper=new OaWxExtendedSuper();
		owesuper.setProId(proId);
		owesuper.setUserId(userid);
		PageData pd=oaWxExtendedSuperDao.sumProUser(owesuper);

		if(pd!=null){
			String cost=pd.getString("cost");
			return NumberUtils.gnumberFarmat(Double.parseDouble(cost));
		}
		return "0";
	}

}
