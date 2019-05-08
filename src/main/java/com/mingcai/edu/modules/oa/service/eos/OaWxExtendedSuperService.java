/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import com.mingcai.edu.modules.oa.entity.eos.OaEosProItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaWxExtendedSuper;
import com.mingcai.edu.modules.oa.dao.eos.OaWxExtendedSuperDao;

/**
 * 报销主表Service
 * @author 李艺杰
 * @version 2019-03-23
 */
@Service
@Transactional(readOnly = true)
public class OaWxExtendedSuperService extends CrudService<OaWxExtendedSuperDao, OaWxExtendedSuper> {

	public OaWxExtendedSuper get(String id) {
		return super.get(id);
	}
	
	public List<OaWxExtendedSuper> findList(OaWxExtendedSuper oaWxExtendedSuper) {
		return super.findList(oaWxExtendedSuper);
	}
	
	public Page<OaWxExtendedSuper> findPage(Page<OaWxExtendedSuper> page, OaWxExtendedSuper oaWxExtendedSuper) {
		return super.findPage(page, oaWxExtendedSuper);
	}
	public Page<OaWxExtendedSuper> findListUn(Page<OaWxExtendedSuper> page, OaWxExtendedSuper oaWxExtendedSuper){
		oaWxExtendedSuper.setPage(page);
		List<OaWxExtendedSuper> list=dao.findListByUser(oaWxExtendedSuper);
		page.setList(list);
		return page;
	}
	public Page<OaWxExtendedSuper> findFinishListByUser(Page<OaWxExtendedSuper> page, OaWxExtendedSuper oaWxExtendedSuper){
		oaWxExtendedSuper.setPage(page);
		List<OaWxExtendedSuper> list=dao.findFinishListByUser(oaWxExtendedSuper);
		page.setList(list);
		return page;
	}

	@Transactional(readOnly = false)
	public void save(OaWxExtendedSuper oaWxExtendedSuper) {
		super.save(oaWxExtendedSuper);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaWxExtendedSuper oaWxExtendedSuper) {
		super.delete(oaWxExtendedSuper);
	}

	@Transactional(readOnly = false)
	public  void updateApproPriAtion(OaWxExtendedSuper oaWxExtendedSuper){
		dao.updateApproPriAtion(oaWxExtendedSuper);
	}

	public List<OaWxExtendedSuper> findByProId(OaWxExtendedSuper oaWxExtendedSuper){
		return  dao.findByProId(oaWxExtendedSuper);
	}

}