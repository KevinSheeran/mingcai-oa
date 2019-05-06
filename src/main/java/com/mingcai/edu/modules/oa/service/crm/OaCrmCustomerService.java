/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.crm;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.crm.OaCrmCustomer;
import com.mingcai.edu.modules.oa.dao.crm.OaCrmCustomerDao;

/**
 * 联系人Service
 * @author 坤
 * @version 2018-02-07
 */
@Service
@Transactional(readOnly = true)
public class OaCrmCustomerService extends CrudService<OaCrmCustomerDao, OaCrmCustomer> {

	public OaCrmCustomer get(String id) {
		return super.get(id);
	}
	
	public List<OaCrmCustomer> findList(OaCrmCustomer oaCrmCustomer) {
		return super.findList(oaCrmCustomer);
	}
	
	public Page<OaCrmCustomer> findPage(Page<OaCrmCustomer> page, OaCrmCustomer oaCrmCustomer) {
		return super.findPage(page, oaCrmCustomer);
	}
	
	@Transactional(readOnly = false)
	public void save(OaCrmCustomer oaCrmCustomer) {
		super.save(oaCrmCustomer);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaCrmCustomer oaCrmCustomer) {
		super.delete(oaCrmCustomer);
	}
	
}