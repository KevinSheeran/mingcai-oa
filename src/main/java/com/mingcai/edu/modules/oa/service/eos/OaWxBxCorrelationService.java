/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaWxBxCorrelation;
import com.mingcai.edu.modules.oa.dao.eos.OaWxBxCorrelationDao;

/**
 * 基本报销关联Service
 * @author 李艺杰
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly = true)
public class OaWxBxCorrelationService extends CrudService<OaWxBxCorrelationDao, OaWxBxCorrelation> {

	public OaWxBxCorrelation get(String id) {
		return super.get(id);
	}
	
	public List<OaWxBxCorrelation> findList(OaWxBxCorrelation oaWxBxCorrelation) {
		return super.findList(oaWxBxCorrelation);
	}
	
	public Page<OaWxBxCorrelation> findPage(Page<OaWxBxCorrelation> page, OaWxBxCorrelation oaWxBxCorrelation) {
		return super.findPage(page, oaWxBxCorrelation);
	}
	
	@Transactional(readOnly = false)
	public void save(OaWxBxCorrelation oaWxBxCorrelation) {
		super.save(oaWxBxCorrelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaWxBxCorrelation oaWxBxCorrelation) {
		super.delete(oaWxBxCorrelation);
	}


	public List<OaWxBxCorrelation> findBySuperId(OaWxBxCorrelation oaWxBxCorrelation){
		return dao.findBySuperId(oaWxBxCorrelation);
	}

	public  List<OaWxBxCorrelation> selByProId(String proid){
		return dao.selByProId(proid);
	}
}