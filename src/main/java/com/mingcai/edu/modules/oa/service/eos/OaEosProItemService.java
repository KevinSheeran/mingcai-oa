/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItem;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProItemDao;

/**
 * 销售项目子项Service
 * @author kun
 * @version 2019-03-08
 */
@Service
@Transactional(readOnly = true)
public class OaEosProItemService extends CrudService<OaEosProItemDao, OaEosProItem> {

	public OaEosProItem get(String id) {
		return super.get(id);
	}
	
	public List<OaEosProItem> findList(OaEosProItem oaEosProItem) {
		return super.findList(oaEosProItem);
	}
	
	public Page<OaEosProItem> findPage(Page<OaEosProItem> page, OaEosProItem oaEosProItem) {
		return super.findPage(page, oaEosProItem);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosProItem oaEosProItem) {
		super.save(oaEosProItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosProItem oaEosProItem) {
		super.delete(oaEosProItem);
	}

	public   OaEosProItem  selByproid(OaEosProItem oaEosProItem){
	 return 	dao.selByproid(oaEosProItem);
	}

	public   List<OaEosProItem>  selByprolistid(OaEosProItem oaEosProItem){
		return 	dao.selByprolistid(oaEosProItem);
	}
	public  List<OaEosProItem>  findListinit(){
		return dao.findListinit();
	}
	@Transactional(readOnly = false)
	public void updateCumulative(OaEosProItem oaEosProItem){
		dao.updateCumulative(oaEosProItem);
	}

	public  Double getbysum(String eosId){
	return 	 dao.itemBySum(eosId);
	}
}