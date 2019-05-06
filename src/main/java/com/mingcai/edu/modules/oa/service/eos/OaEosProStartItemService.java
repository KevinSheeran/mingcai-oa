/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import com.mingcai.edu.common.persistence.DataEntity;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.TableUpdateLog.UnityLog;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProStartItemDao;

/**
 * 立项子项目Service
 * @author kun
 * @version 2019-03-21
 */
@Service
@Transactional(readOnly = true)
public class OaEosProStartItemService extends CrudService<OaEosProStartItemDao, OaEosProStartItem> {
	@Autowired
	private OaEosProService oaEosProService;
	public OaEosProStartItem get(String id) {
		return super.get(id);
	}
	
	public List<OaEosProStartItem> findList(OaEosProStartItem oaEosProStartItem) {
		return super.findList(oaEosProStartItem);
	}
	
	public Page<OaEosProStartItem> findPage(Page<OaEosProStartItem> page, OaEosProStartItem oaEosProStartItem) {
		return super.findPage(page, oaEosProStartItem);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosProStartItem oaEosProStartItem) {

//		if(oaEosProStartItem.getEosId()!=null) {
//			OaEosPro pro=oaEosProService.get(oaEosProStartItem.getEosId());
//			OaEosProStartItem old=this.get(oaEosProStartItem.getId());
//			if(StringUtils.isNotEmpty(pro.getProNumber())){
//				try {
//					UnityLog log = oaEosProStartItem.createLog(DataEntity.UPDATE, old);
//					logdao.unityinsert(log);
//				} catch (IllegalAccessException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		super.save(oaEosProStartItem);

	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosProStartItem oaEosProStartItem) {
		if(oaEosProStartItem.getEosId()!=null) {
			OaEosPro pro=oaEosProService.get(oaEosProStartItem.getEosId());
			OaEosProStartItem old=this.get(oaEosProStartItem.getId());
			if(StringUtils.isNotEmpty(pro.getProNumber())){
				try {
					UnityLog log = oaEosProStartItem.createLog(DataEntity.DELETE, old);
					logdao.unityinsert(log);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		super.delete(oaEosProStartItem);
	}
	@Transactional(readOnly = false)
	public void updateCumulative(OaEosProStartItem oaEosProStartItem){
		dao.updateCumulative(oaEosProStartItem);
	}

	public  List <OaEosProStartItem> selByprolistid(OaEosProStartItem oaEosProStartItem){
	return dao.selByprolistid(oaEosProStartItem);
	}

	public OaEosProStartItem selByproid(OaEosProStartItem oaEosProStartItem){
		return dao.selByproid(oaEosProStartItem);
	}

	public  Double getbysum(String eosId){
		return 	 dao.itemBySum(eosId);
	}
}