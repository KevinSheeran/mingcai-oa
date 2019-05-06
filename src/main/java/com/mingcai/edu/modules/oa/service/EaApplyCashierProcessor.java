package com.mingcai.edu.modules.oa.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.modules.act.utils.ActUtils;
import com.mingcai.edu.modules.oa.dao.OaFinanceEaDao;
import com.mingcai.edu.modules.oa.entity.OaFinanceEa;

/**
 * 出纳支付处理器
 * @author hxy
 */
@Service
@Transactional
public class EaApplyCashierProcessor implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OaFinanceEaDao oaFinanceEaDao;
	@Autowired
	private RuntimeService runtimeService;
	
	/**
	 * 执行出纳
	 */
	public void notify(DelegateTask delegateTask) {
		String processInstanceId = delegateTask.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		OaFinanceEa oaFinanceEa = new OaFinanceEa(processInstance.getBusinessKey().replace(ActUtils.PD_EA[1] + ":", ""));
		oaFinanceEaDao.updateApproverStatus(null, null, null,"2", oaFinanceEa.getId());
	}

}
