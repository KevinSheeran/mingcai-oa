/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.OaProductPrice;
import com.mingcai.edu.modules.oa.dao.OaProductPriceDao;

/**
 * 产品报价版本Service
 * @author 坤
 * @version 2018-05-07
 */
@Service
@Transactional(readOnly = true)
public class OaProductPriceService extends CrudService<OaProductPriceDao, OaProductPrice> {

	public OaProductPrice get(String id) {
		return super.get(id);
	}
	
	public List<OaProductPrice> findList(OaProductPrice oaProductPrice) {
		return super.findList(oaProductPrice);
	}
	
	public Page<OaProductPrice> findPage(Page<OaProductPrice> page, OaProductPrice oaProductPrice) {
		return super.findPage(page, oaProductPrice);
	}
	
	@Transactional(readOnly = false)
	public void save(OaProductPrice oaProductPrice) {
		super.save(oaProductPrice);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProductPrice oaProductPrice) {
		super.delete(oaProductPrice);
	}
	
}