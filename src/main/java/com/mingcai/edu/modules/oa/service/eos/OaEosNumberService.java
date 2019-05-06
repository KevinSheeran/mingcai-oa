/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumber;
import com.mingcai.edu.modules.oa.dao.eos.OaEosNumberDao;

/**
 * 项目编号Service
 * @author 李艺杰
 * @version 2019-04-03
 */
@Service
@Transactional(readOnly = true)
public class OaEosNumberService extends CrudService<OaEosNumberDao, OaEosNumber> {

	public OaEosNumber get(String id) {
		return super.get(id);
	}
	
	public List<OaEosNumber> findList(OaEosNumber oaEosNumber) {
		return super.findList(oaEosNumber);
	}
	
	public Page<OaEosNumber> findPage(Page<OaEosNumber> page, OaEosNumber oaEosNumber) {
		return super.findPage(page, oaEosNumber);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosNumber oaEosNumber) {
		super.save(oaEosNumber);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosNumber oaEosNumber) {
		super.delete(oaEosNumber);
	}

	public OaEosNumber getNumberByMobile(OaEosNumber oaEosNumber){
	 return 	dao.getNumberByMobile(oaEosNumber);
	}
	
}