/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.act.service.ActTaskService;
import com.mingcai.edu.modules.act.utils.ActUtils;
import com.mingcai.edu.modules.oa.dao.OaFinanceEaDao;
import com.mingcai.edu.modules.oa.dao.OaFinanceEaDetailDao;
import com.mingcai.edu.modules.oa.entity.OaFinanceEa;
import com.mingcai.edu.modules.oa.entity.OaFinanceEaDetail;
import com.mingcai.edu.modules.sys.dao.OfficeDao;
import com.mingcai.edu.modules.sys.entity.Office;
import com.mingcai.edu.modules.sys.service.SystemService;
import com.mingcai.edu.modules.sys.utils.UserUtils;

/**
 * 报销Service
 * 
 * @author hxy
 * @version 2017-12-15
 */
@Service
@Transactional(readOnly = true)
public class OaFinanceEaService extends CrudService<OaFinanceEaDao, OaFinanceEa> {

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected OfficeDao officeDao;
	@Autowired
	protected OaFinanceEaDetailDao oaFinanceEaDetailDao;

	public OaFinanceEa getByProcInsId(String procInsId) {
		return dao.getByProcInsId(procInsId);
	}

	/**
	 * 获取报销列表
	 * 
	 * @param
	 */
	public Page<OaFinanceEa> findPage(Page<OaFinanceEa> page, OaFinanceEa oaFinanceEa) {
		oaFinanceEa.setPage(page);
		page.setList(dao.findList(oaFinanceEa));
		return page;
	}

	/**
	 * 获取报销金额
	 *
	 * @param eaId
	 */
	public BigDecimal findMoneyById(String eaId) {
		BigDecimal moneys = dao.findMoneyById(eaId);
		return moneys;
	}

	//
	/**
	 * 审核新增或编辑
	 * 
	 * @param
	 */
	@Transactional(readOnly = false)
	public void save(OaFinanceEa oaFinanceEa) {

		Map<String, Object> variables = new HashMap<String, Object>();
		// 申请发起
		if (StringUtils.isBlank(oaFinanceEa.getId())) {
			if (StringUtils.isNotBlank(oaFinanceEa.getUserFirstApprover().getId())) {
				variables.put("inputUser1",
						systemService.getUser(oaFinanceEa.getUserFirstApprover().getId()).getLoginName());
			}
			if (StringUtils.isNotBlank(oaFinanceEa.getUserSecondApprover().getId())) {
				variables.put("inputUser2",
						systemService.getUser(oaFinanceEa.getUserSecondApprover().getId()).getLoginName());
			}
			if (StringUtils.isNotBlank(oaFinanceEa.getUserThirdApprover().getId())) {
				variables.put("inputUser3",
						systemService.getUser(oaFinanceEa.getUserThirdApprover().getId()).getLoginName());
			}
			variables.put("applyUser", UserUtils.getUser().getLoginName());
			oaFinanceEa.setFirstApproveStatus("0");
			oaFinanceEa.setSecondApproveStatus("0");
			oaFinanceEa.setThirdApproveStatus("0");
			oaFinanceEa.setStatus("1");
			oaFinanceEa.preInsert();
			dao.insert(oaFinanceEa);
			for (OaFinanceEaDetail detail : oaFinanceEa.getDetailList()) {
				if (detail == null || StringUtils.isBlank(detail.getRemarks())) {
					continue;
				}
				detail.setEaId(oaFinanceEa.getId());
				detail.preInsert();
				detail.setMoney(detail.getMoney().multiply(new BigDecimal(100))); // 金钱单位转换为分
				oaFinanceEaDetailDao.insert(detail);
			}
			// 启动流程
			actTaskService.startProcess(ActUtils.PD_EA[0], ActUtils.PD_EA[1], oaFinanceEa.getId(),
					oaFinanceEa.getRemarks(), variables);

		}

		// 重新编辑申请
		else {
			oaFinanceEa.preUpdate();
			oaFinanceEa.getAct().setComment(("yes".equals(oaFinanceEa.getAct().getFlag()) ? "[重申]" : "[销毁]"));
			// 添加意见
			if (StringUtils.isNotBlank(oaFinanceEa.getAct().getComment())) {
				if ("[销毁]".equals(oaFinanceEa.getAct().getComment())) {
					oaFinanceEa.setFirstApproveStatus("3");
					oaFinanceEa.setSecondApproveStatus("3");
					oaFinanceEa.setThirdApproveStatus("3");
					oaFinanceEa.setStatus("3");
				} else {
					if (StringUtils.isNotBlank(oaFinanceEa.getUserFirstApprover().getId())) {
						variables.put("inputUser1",
								systemService.getUser(oaFinanceEa.getUserFirstApprover().getId()).getLoginName());
					}
					if (StringUtils.isNotBlank(oaFinanceEa.getUserSecondApprover().getId())) {
						variables.put("inputUser2",
								systemService.getUser(oaFinanceEa.getUserSecondApprover().getId()).getLoginName());
					}
					if (StringUtils.isNotBlank(oaFinanceEa.getUserThirdApprover().getId())) {
						variables.put("inputUser3",
								systemService.getUser(oaFinanceEa.getUserThirdApprover().getId()).getLoginName());
					}
					oaFinanceEa.setFirstApproveStatus("1");
					oaFinanceEa.setSecondApproveStatus("1");
					oaFinanceEa.setThirdApproveStatus("1");
				}
				taskService.addComment(oaFinanceEa.getAct().getTaskId(), oaFinanceEa.getProInsId(),
						oaFinanceEa.getAct().getComment());
			}
			// 完成流程任务
			variables.put("pass", "yes".equals(oaFinanceEa.getAct().getFlag()) ? true : false);
			taskService.complete(oaFinanceEa.getAct().getTaskId(), variables);
			oaFinanceEa.preUpdate();
			dao.update(oaFinanceEa);
			for (OaFinanceEaDetail detail : oaFinanceEa.getDetailList()) {
				if (StringUtils.isBlank(detail.getEaId())) {
					oaFinanceEaDetailDao.delete(new OaFinanceEaDetail(detail.getId()));
					continue;
				}
				detail.preUpdate();
				detail.setMoney(detail.getMoney().multiply(new BigDecimal(100))); // 金钱单位转换为分
				oaFinanceEaDetailDao.update(detail);
			}
		}
	}

	/**
	 *
	 * @param taskId
	 *            任务ID
	 * @param vars
	 *            任务变量
	 */
	@Transactional(readOnly = false)
	public void complete(String taskId, Map<String, Object> vars) {

		// 设置流程变量
		if (vars == null) {
			vars = Maps.newHashMap();
		}
		String id = (String) vars.get("id");
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (vars.size() > 0 && !(Boolean) vars.get("pass")) {
			// 添加意见
			if (StringUtils.isNotBlank(task.getProcessInstanceId())
					&& StringUtils.isNotBlank((String) vars.get("reason"))) {
				taskService.addComment(taskId, task.getProcessInstanceId(), (String) vars.get("reason"));
			}
			// 更新审批状态
			if ("officeLeaderAudit".equals(task.getTaskDefinitionKey())) {
				dao.updateApproverStatus("3", "1", "1", null, id);
			} else if ("managerAudit".equals(task.getTaskDefinitionKey())) {
				dao.updateApproverStatus("1", "3", "1", null, id);
			} else if ("financeAudit".equals(task.getTaskDefinitionKey())) {
				dao.updateApproverStatus("1", "1", "3", null, id);
			}
			// 删除失活的并行任务
			if (StringUtils.isNotEmpty(task.getProcessInstanceId())) {
				dao.deleteInactivationEx(task.getProcessInstanceId());
			}
			// 驳回所有并行任务
			List<Execution> exs = runtimeService.createExecutionQuery().processInstanceId(task.getProcessInstanceId())
					.list();
			for (Execution ex : exs) {
				if (!ex.getId().equals(task.getExecutionId()) && !ex.getId().equals(task.getProcessInstanceId())) {
					completeToEnd(ex);
				}
			}
		} else {
			// 添加意见
			if (StringUtils.isNotBlank(task.getProcessInstanceId())) {
				if ("cashier".equals(task.getTaskDefinitionKey())) {
					taskService.addComment(taskId, task.getProcessInstanceId(), "已出纳");
				} else {
					taskService.addComment(taskId, task.getProcessInstanceId(), "审核通过");
				}
			}
			// 更新审批状态
			if ("officeLeaderAudit".equals(task.getTaskDefinitionKey())) {
				dao.updateApproverStatus("2", null, null, null, id);
			} else if ("managerAudit".equals(task.getTaskDefinitionKey())) {
				dao.updateApproverStatus(null, "2", null, null, id);
			} else if ("financeAudit".equals(task.getTaskDefinitionKey())) {
				dao.updateApproverStatus(null, null, "2", null, id);
			}
		}
		// 通过当前任务
		taskService.complete(taskId, vars);
	}

	/**
	 * 撤销分支
	 *
	 * @param ex
	 *            执行分支
	 */
	public void completeToEnd(Execution ex) {
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", false);
		Task task = taskService.createTaskQuery().executionId(ex.getId()).active().orderByTaskPriority().desc()
				.orderByTaskCreateTime().desc().singleResult();
		taskService.complete(task.getId(), vars);
		Task newtask = taskService.createTaskQuery().executionId(ex.getId()).active().orderByTaskPriority().desc()
				.orderByTaskCreateTime().desc().singleResult();
		if (newtask != null) { // 如果执行分支已撤销
			completeToEnd(ex);
		}
	}

	/**
	 * 选择部门
	 * 
	 * @param officeId
	 *            部门ID
	 */
	public List<Office> findOfficeList(String officeId) {
		List<Office> officeList = new ArrayList<Office>();
		if (officeId != null) {
			officeList.add(officeDao.get(officeId));
		} else {
			officeList = officeDao.findList(new Office());
		}
		return officeList;
	}

}
