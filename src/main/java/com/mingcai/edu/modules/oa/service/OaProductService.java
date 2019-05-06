/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mingcai.edu.common.utils.FileUtils;
import com.mingcai.edu.modules.oa.dao.OaProductResourcesDao;
import com.mingcai.edu.modules.oa.dao.OaProductUsersDao;
import com.mingcai.edu.modules.oa.entity.OaProductResources;
import com.mingcai.edu.modules.oa.entity.OaProductUsers;
import com.mingcai.edu.modules.sys.dao.RoleDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.OaProduct;
import com.mingcai.edu.modules.oa.dao.OaProductDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目信息Service
 * @author 江坤
 * @version 2017-11-29
 */
@Service
@Transactional(readOnly = true)
public class OaProductService extends CrudService<OaProductDao, OaProduct> {

	@Autowired
	private OaProductUsersDao oaProductUsersDao;
	@Autowired
	private OaProductResourcesDao oaProductResourcesDao;
	@Autowired
	private RoleDao roleDao;
	public OaProduct get(String id) {
		return super.get(id);
	}
	public List<OaProductUsers> findListByproductId(OaProductUsers pusers){return oaProductUsersDao.findListByproductId(pusers); }
	public List<OaProduct> findList(OaProduct oaProduct) {
		return super.findList(oaProduct);
	}
	public Page<OaProduct> findPage(Page<OaProduct> page, OaProduct oaProduct) {
		return super.findPage(page, oaProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(OaProduct oaProduct,HttpServletRequest request) {

		boolean isnew=oaProduct.getIsNewRecord();
		String longtime=new Date().getTime()+"";
		oaProduct.setDirPath(longtime);
		super.save(oaProduct);
		if(isnew){
			OaProductResources res=new OaProductResources();
			res.setId(oaProduct.getId());
			res.setParentId("0");
			res.setName(oaProduct.getName());
			res.setPath(longtime);
			res.setType("1");
			res.setFormat("dir");
			res.setCreateBy(UserUtils.getUser());
			res.setCreateDate(new Date());
			res.setpIds("");
			res.setProductId(oaProduct.getId());
			oaProductResourcesDao.insert(res);
			FileUtils.updateDirectory(res.getOldPath(),res.getPath(),request,"");
		}else{
			OaProductResources res=oaProductResourcesDao.get(oaProduct.getId());
			res.setName(oaProduct.getName());
			oaProductResourcesDao.update(res);
		}
		String[] ids=oaProduct.getUsers_ids().split(",");
		if(ids.length>0){
			dao.deletePUser(oaProduct);
			List<OaProductUsers> productusers=new ArrayList<OaProductUsers>();
			for(String user:ids){
				OaProductUsers users=new OaProductUsers();
				users.setUser(new User(user));
				users.setProductId(oaProduct.getId());
				oaProductUsersDao.insert(users);
			}
		}
		System.out.println(oaProduct.toString());
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProduct oaProduct) {
		super.delete(oaProduct);
	}

	
}