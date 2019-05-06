/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.Date;
import java.util.List;

import com.mingcai.edu.modules.oa.entity.eos.OaWxExtendedSuper;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaWxBxCorrelationSuper;
import com.mingcai.edu.modules.oa.dao.eos.OaWxBxCorrelationSuperDao;

/**
 * 报销主表Service
 * @author 李艺杰
 * @version 2019-04-11
 */
@Service
@Transactional(readOnly = true)
public class OaWxBxCorrelationSuperService extends CrudService<OaWxBxCorrelationSuperDao, OaWxBxCorrelationSuper> {

	public OaWxBxCorrelationSuper get(String id) {
		return super.get(id);
	}

	public OaWxBxCorrelationSuper get2(String id) {
		return dao.get2(id);
	}

	public OaWxBxCorrelationSuper get1(String id) {
		return dao.get1(id);
	}
	public List<OaWxBxCorrelationSuper> findList(OaWxBxCorrelationSuper oaWxBxCorrelationSuper) {
		return super.findList(oaWxBxCorrelationSuper);
	}
	
	public Page<OaWxBxCorrelationSuper> findPage(Page<OaWxBxCorrelationSuper> page, OaWxBxCorrelationSuper oaWxBxCorrelationSuper) {
		return super.findPage(page, oaWxBxCorrelationSuper);
	}
	
	@Transactional(readOnly = false)
	public void save(OaWxBxCorrelationSuper oaWxBxCorrelationSuper) {
		super.save(oaWxBxCorrelationSuper);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaWxBxCorrelationSuper oaWxBxCorrelationSuper) {
		super.delete(oaWxBxCorrelationSuper);
	}


	/**
	 * 已审核
	 *
	 * @return
	 */
	public Page findProcessedList(Page<OaWxBxCorrelationSuper> page, OaWxBxCorrelationSuper wesuper) {

		User user = UserUtils.getUser();
		wesuper.setPage(page);
		page.setList(dao.findProcessedList((user.getId()),wesuper));
		return page;
	}
	/**
	 * 未审核
	 * 	 *
	 * @return
	 */
	public Page findUntreatedList(Page<OaWxBxCorrelationSuper> page, OaWxBxCorrelationSuper wesuper) {
		User user = UserUtils.getUser();
		wesuper.setPage(page);
		page.setList(dao.findUntreatedList((user.getId()),wesuper));
		return page;
	}

	/**
	 * 财务未拨款
	 * @param page
	 * @param wesuper
	 * @return
	 */
	public Page Finance(Page<OaWxBxCorrelationSuper> page, OaWxBxCorrelationSuper wesuper){
		User user = UserUtils.getUser();
		wesuper.setPage(page);
		page.setList(dao.findFinanceList(wesuper));
		return  page;
	}
	public Page<OaWxBxCorrelationSuper>   findFinanceExcel(Page<OaWxBxCorrelationSuper> page,String kssj, String jssj, OaWxBxCorrelationSuper wesuper){
		User user = UserUtils.getUser();
		wesuper.setPage(page);
		page.setList(dao.findFinanceExcel(kssj,jssj));
		return  page;
	}
}