/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.crm;

import java.util.List;

import com.mingcai.edu.modules.oa.dao.crm.OaCrmProductDao;
import com.mingcai.edu.modules.oa.entity.crm.OaCrmProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.crm.OaCrmProductIng;
import com.mingcai.edu.modules.oa.dao.crm.OaCrmProductIngDao;

/**
 * 项目报备Service
 * @author 坤
 * @version 2018-02-07
 */
@Service
@Transactional(readOnly = true)
public class OaCrmProductIngService extends CrudService<OaCrmProductIngDao, OaCrmProductIng> {

	@Autowired
	OaCrmProductDao pdap;
	public OaCrmProductIng get(String id) {
		return super.get(id);
	}
	
	public List<OaCrmProductIng> findList(OaCrmProductIng oaCrmProductIng) {
		return super.findList(oaCrmProductIng);
	}
	
	public Page<OaCrmProductIng> findPage(Page<OaCrmProductIng> page, OaCrmProductIng oaCrmProductIng) {
		return super.findPage(page, oaCrmProductIng);
	}
	
	@Transactional(readOnly = false)
	public void save(OaCrmProductIng oaCrmProductIng) {
		//跟进项目 修改项目状态
		OaCrmProduct product=pdap.get(oaCrmProductIng.getPid());
		if(product.getStatus().equals("1")){
			product.setStatus("2");
			product.preUpdate();
			pdap.update(product);
		}
		super.save(oaCrmProductIng);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaCrmProductIng oaCrmProductIng) {
		super.delete(oaCrmProductIng);
	}
	
}