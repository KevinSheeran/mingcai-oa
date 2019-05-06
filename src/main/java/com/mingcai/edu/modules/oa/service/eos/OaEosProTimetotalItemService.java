/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProTimetotalDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProTimetotal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProTimetotalItem;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProTimetotalItemDao;

/**
 * 汇总详情Service
 * @author kun
 * @version 2019-04-01
 */
@Service
@Transactional(readOnly = true)
public class OaEosProTimetotalItemService extends CrudService<OaEosProTimetotalItemDao, OaEosProTimetotalItem> {
	@Autowired
	OaEosProTimetotalDao oaEosProTimetotalDao;
	@Autowired
	OaEosProTimetotalService oaEosProTimetotalService;
	public OaEosProTimetotalItem get(String id) {
		return super.get(id);
	}
	public OaEosProTimetotalItem getByTimes(OaEosProTimetotalItem oaEosProTimetotalItem) {
		return dao.getByTimes(oaEosProTimetotalItem);
	}
	public List<OaEosProTimetotalItem> findList(OaEosProTimetotalItem oaEosProTimetotalItem) {
		return super.findList(oaEosProTimetotalItem);
	}
	
	public Page<OaEosProTimetotalItem> findPage(Page<OaEosProTimetotalItem> page, OaEosProTimetotalItem oaEosProTimetotalItem) {
		return super.findPage(page, oaEosProTimetotalItem);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosProTimetotalItem oaEosProTimetotalItem) {

		super.save(oaEosProTimetotalItem);

	}
	@Transactional(readOnly = false)
	public boolean itemSave(OaEosProTimetotalItem oaEosProTimetotalItem,String userType){
		if(StringUtils.isNotEmpty(oaEosProTimetotalItem.getId())) {
			OaEosProTimetotal total = oaEosProTimetotalDao.get(oaEosProTimetotalItem.getProId());
			OaEosProTimetotalItem item=this.get(oaEosProTimetotalItem.getId());
			OaEosProTimetotalItem saveItem=item;
			if("1".equals(userType)){
				double num=oaEosProTimetotalItem.getPsTotal()-item.getPsTotal();
				total.setPsTotal(total.getPsTotal()+num);
				saveItem.setPsTotal(oaEosProTimetotalItem.getPsTotal());
			}
			if("2".equals(userType)){
				double num=oaEosProTimetotalItem.getSaleTotal()-item.getSaleTotal();
				total.setSaleTotal(total.getSaleTotal()+num);
				saveItem.setSaleTotal(oaEosProTimetotalItem.getSaleTotal());
			}
			if("3".equals(userType)){
				double num=oaEosProTimetotalItem.getRdTotal()-item.getRdTotal();
				total.setRdTotal(total.getRdTotal()+num);
				saveItem.setRdTotal(oaEosProTimetotalItem.getRdTotal());
			}
			this.save(saveItem);
			oaEosProTimetotalService.save(total);
		}else{
			OaEosProTimetotal total = oaEosProTimetotalDao.get(oaEosProTimetotalItem.getProId());
			if(total==null){
				total=new OaEosProTimetotal();
				total.setId(oaEosProTimetotalItem.getProId());
				total.setIsNewRecord(true);
				total.setPsTotal(oaEosProTimetotalItem.getPsTotal());
				total.setRdTotal(oaEosProTimetotalItem.getRdTotal());
				total.setSaleTotal(oaEosProTimetotalItem.getSaleTotal());
				total.preInsert();
				oaEosProTimetotalDao.insert(total);
			}else{
				total.setPsTotal(total.getPsTotal()+oaEosProTimetotalItem.getPsTotal());
				total.setRdTotal(total.getRdTotal()+oaEosProTimetotalItem.getRdTotal());
				total.setSaleTotal(total.getSaleTotal()+oaEosProTimetotalItem.getSaleTotal());
				oaEosProTimetotalService.save(total);
			}
			this.save(oaEosProTimetotalItem);
		}
		return true;
	}
	@Transactional(readOnly = false)
	public void delete(OaEosProTimetotalItem oaEosProTimetotalItem) {
		super.delete(oaEosProTimetotalItem);
	}
	
}