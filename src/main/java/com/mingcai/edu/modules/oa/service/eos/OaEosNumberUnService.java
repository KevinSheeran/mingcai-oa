/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumberUn;
import com.mingcai.edu.modules.oa.dao.eos.OaEosNumberUnDao;

/**
 * 非销售编号Service
 * @author 坤
 * @version 2019-04-10
 */
@Service
@Transactional(readOnly = true)
public class OaEosNumberUnService extends CrudService<OaEosNumberUnDao, OaEosNumberUn> {

	public OaEosNumberUn get(String id) {
		return super.get(id);
	}
	public OaEosNumberUn getChecked() {
		return dao.getChecked();
	}

	public List<OaEosNumberUn> findList(OaEosNumberUn oaEosNumberUn) {
		return super.findList(oaEosNumberUn);
	}
	
	public Page<OaEosNumberUn> findPage(Page<OaEosNumberUn> page, OaEosNumberUn oaEosNumberUn) {
		return super.findPage(page, oaEosNumberUn);
	}
	
	@Transactional(readOnly = false)
	public void save(OaEosNumberUn oaEosNumberUn) {
		super.save(oaEosNumberUn);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosNumberUn oaEosNumberUn) {
		super.delete(oaEosNumberUn);
	}
	@Transactional(readOnly = false)
	public void updateChecked(OaEosNumberUn oaEosNumberUn) {
		dao.updateCheckedAll();
		oaEosNumberUn.setChecked("1");
		super.save(oaEosNumberUn);
	}

	
}