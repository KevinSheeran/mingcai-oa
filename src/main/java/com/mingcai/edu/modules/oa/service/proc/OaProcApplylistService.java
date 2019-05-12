/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.proc;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.dao.proc.OaProcApplylistDao;
import com.mingcai.edu.modules.oa.entity.proc.OaProcApplylist;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 采购申请单Service
 * @author 谢一郡
 * @version 2019-05-06
 */
@Service
@Transactional(readOnly = true)
public class OaProcApplylistService extends CrudService<OaProcApplylistDao, OaProcApplylist> {

	public OaProcApplylist get(String id) {
		return super.get(id);
	}
	
	public List<OaProcApplylist> findList(OaProcApplylist oaProcApplylist) {
		return super.findList(oaProcApplylist);
	}
	
	public Page<OaProcApplylist> findPage(Page<OaProcApplylist> page, OaProcApplylist oaProcApplylist) {
		return super.findPage(page, oaProcApplylist);
	}
	
	@Transactional(readOnly = false)
	public void save(OaProcApplylist oaProcApplylist) {
	    oaProcApplylist.setFflowId("");
	    oaProcApplylist.setSflowId("");
		oaProcApplylist.setUser(UserUtils.getUser());
		super.save(oaProcApplylist);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProcApplylist oaProcApplylist) {
		super.delete(oaProcApplylist);
	}
	
}