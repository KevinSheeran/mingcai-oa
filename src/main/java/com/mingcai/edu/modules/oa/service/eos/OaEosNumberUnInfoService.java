/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.dao.eos.OaEosNumberUnDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumberUn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumberUnInfo;
import com.mingcai.edu.modules.oa.dao.eos.OaEosNumberUnInfoDao;

/**
 * 非销售自编号Service
 * @author 坤
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly = true)
public class OaEosNumberUnInfoService extends CrudService<OaEosNumberUnInfoDao, OaEosNumberUnInfo> {

	@Autowired
	private OaEosNumberUnDao undao;
	public OaEosNumberUnInfo get(String id) {
		return super.get(id);
	}
	
	public List<OaEosNumberUnInfo> findList(OaEosNumberUnInfo oaEosNumberUnInfo) {
		return super.findList(oaEosNumberUnInfo);
	}
	
	public Page<OaEosNumberUnInfo> findPage(Page<OaEosNumberUnInfo> page, OaEosNumberUnInfo oaEosNumberUnInfo) {
		return super.findPage(page, oaEosNumberUnInfo);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosNumberUnInfo oaEosNumberUnInfo) {
		OaEosNumberUnInfo max=dao.getMaxCode(oaEosNumberUnInfo);
		OaEosNumberUn un=undao.get(oaEosNumberUnInfo.getUnNumberId());
		if(max!=null&&StringUtils.isNotEmpty(max.getId())){
			OaEosNumberUnInfo info=new OaEosNumberUnInfo();
			info.setItemNumber(fromatNumber(Integer.parseInt(max.getItemNumber())+1));
			info.setUnNumberId(un.getId());
			super.save(info);
		}else{
			OaEosNumberUnInfo info=new OaEosNumberUnInfo();
			info.setItemNumber(fromatNumber(1));
			info.setUnNumberId(un.getId());
			super.save(info);
		}
	}
	public String fromatNumber(Integer number){
		if(number<10){
			return "000"+number;
		}
		if(number<100){
			return "00"+number;
		}
		if(number<1000){
			return "0"+number;
		}
		return "0000";
	}
	@Transactional(readOnly = false)
	public void delete(OaEosNumberUnInfo oaEosNumberUnInfo) {
		super.delete(oaEosNumberUnInfo);
	}
	
}