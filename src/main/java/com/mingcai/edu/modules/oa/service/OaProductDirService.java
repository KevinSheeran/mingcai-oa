/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.OaProductDir;
import com.mingcai.edu.modules.oa.dao.OaProductDirDao;

/**
 * 项目文件夹Service
 * @author 江坤
 * @version 2017-12-04
 */
@Service
@Transactional(readOnly = true)
public class OaProductDirService extends CrudService<OaProductDirDao, OaProductDir> {

	
	public OaProductDir get(String id) {
		OaProductDir oaProductDir = super.get(id);
		return oaProductDir;
	}
	
	public List<OaProductDir> findList(OaProductDir oaProductDir) {
		return super.findList(oaProductDir);
	}
	
	public Page<OaProductDir> findPage(Page<OaProductDir> page, OaProductDir oaProductDir) {
		return super.findPage(page, oaProductDir);
	}
	
	@Transactional(readOnly = false)
	public void save(OaProductDir oaProductDir) {
		super.save(oaProductDir);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProductDir oaProductDir) {
		super.delete(oaProductDir);
	}
	
}