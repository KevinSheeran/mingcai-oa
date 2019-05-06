package com.mingcai.edu.modules.oa.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.modules.act.entity.Act;
import com.mingcai.edu.modules.act.utils.ActUtils;
import com.mingcai.edu.modules.oa.entity.OaFinanceCategory;
import com.mingcai.edu.modules.oa.entity.OaFinanceEa;
import com.mingcai.edu.modules.oa.entity.OaFinanceProduct;
import com.mingcai.edu.modules.oa.service.OaFinanceCategoryService;
import com.mingcai.edu.modules.oa.service.OaFinanceEaService;
import com.mingcai.edu.modules.oa.service.OaFinanceProductService;
import com.mingcai.edu.modules.sys.entity.Office;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.OfficeService;
import com.mingcai.edu.modules.sys.utils.UserUtils;

/**
 * 报销Controller
 * 
 * @author hxy
 * @version 2017-12-115
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaFinanceEa")
public class OaFinanceEaController extends BaseController {

	@Autowired
	private OaFinanceEaService oaFinanceEaService;

	@Autowired
	private OaFinanceProductService oaFinanceProductService;

	@Autowired
	private OaFinanceCategoryService oaFinanceCategoryService;

	@Autowired
	protected RuntimeService runtimeService;

	@Autowired
	protected TaskService taskService;

	@Autowired
	protected RepositoryService repositoryService;

	@Autowired
	protected OfficeService officeService;
	
	@ModelAttribute
	public OaFinanceEa get(@RequestParam(required = false) String id) {//
		OaFinanceEa oaFinanceEa = null;
		if (StringUtils.isNotBlank(id)) {
			oaFinanceEa = oaFinanceEaService.get(id);
		}
		if (oaFinanceEa == null) {
			oaFinanceEa = new OaFinanceEa();
		}
		return oaFinanceEa;
	}

	/**
	 * 任务列表
	 * 
	 * @param oaFinanceEa
	 */
	@RequiresPermissions("oa:oaFinanceEa:view")
	@RequestMapping(value = { "list/task", "" })
	public String taskList(Model model) {
		String userId = UserUtils.getUser().getLoginName();
		List<OaFinanceEa> results = new ArrayList<OaFinanceEa>();
		List<Task> todoList = taskService.createTaskQuery().processDefinitionKey(ActUtils.PD_EA[0]).taskAssignee(userId)
				.active().orderByTaskPriority().desc().orderByTaskCreateTime().desc().list();
		for (Task task : todoList) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).active().singleResult();
			String businessKey = processInstance.getBusinessKey();
			OaFinanceEa oaFinanceEas = oaFinanceEaService.get(businessKey.replace(ActUtils.PD_EA[1] + ":", ""));
			if (oaFinanceEas != null) {
				oaFinanceEas.setTask(task);
				oaFinanceEas.setProcessInstance(processInstance);
				oaFinanceEas.setProcessDefinition(repositoryService.createProcessDefinitionQuery()
						.processDefinitionId((processInstance.getProcessDefinitionId())).singleResult());
                oaFinanceEas.setMoneys(oaFinanceEaService.findMoneyById(oaFinanceEas.getId()));
				results.add(oaFinanceEas);
			}
		}
		model.addAttribute("tasks", todoList);
		model.addAttribute("oaFinanceEas", results);
		return "modules/oa/oaFinanceEaTask";
	}

	/**
	 * 报销列表
	 * 
	 * @param oaFinanceEa
	 */
	@RequiresPermissions("oa:oaFinanceEa:view")
	@RequestMapping(value = { "list" })
	public String list(OaFinanceEa oaFinanceEa, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = UserUtils.getUser();
		oaFinanceEa.setCreateBy(user);
		List<Role> rols = UserUtils.getUser().getRoleList();
		for (Role role : rols) {
			if (role.getName().equals(gsAdmin) || role.getName().equals(financeRole)) { // 公司领导或财务
				oaFinanceEa.setAccessAble(true);
			}
		}
		Page<OaFinanceEa> page = oaFinanceEaService.findPage(new Page<OaFinanceEa>(request, response), oaFinanceEa);
		model.addAttribute("page", page);
		return "modules/oa/oaFinanceEaList";
	}

	/**
	 * 读取详细数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "detail")
	public String getTaskDetail(String tkey, String id, String tid, Model model) {
		String view = "modules/oa/oaFinanceEaTaskDetail";
		if ("officeLeaderAudit".equals(tkey)) {
			model.addAttribute("approver", "1");
		} else if ("financeAudit".equals(tkey)) {
			model.addAttribute("approver", "3");
		} else if ("managerAudit".equals(tkey)) {
			model.addAttribute("approver", "2");
		} else if ("cashier".equals(tkey)) {
			model.addAttribute("approver", "4");
		} else if ("applicantModify".equals(tkey)) { // 调整修改
			view = "modules/oa/oaFinanceEaForm";
			model.addAttribute("eaDetailList", oaFinanceEaService.get(id).getDetailList());
			model.addAttribute("productList", oaFinanceProductService.findList(new OaFinanceProduct()));
			model.addAttribute("categoryList", oaFinanceCategoryService.findList(new OaFinanceCategory()));
		}
		model.addAttribute("tkey", tkey);
		model.addAttribute("id", id);
		model.addAttribute("tid", tid);
		return view;
	}

	/**
	 * 申请单操作
	 * 
	 * @param OaFinanceEa
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:oaFinanceEa:view")
	@RequestMapping(value = "form")
	public String form(OaFinanceEa oaFinanceEa, Model model, String tkey, String tid) {

		String view = "oaFinanceEaForm";

		// 查看审批申请单
		if (StringUtils.isNotBlank(oaFinanceEa.getId())) {
			// 查看
			view = "oaFinanceEaView";
		} else { // 新增
			oaFinanceEa.setCreateBy(UserUtils.getUser());
			oaFinanceEa.setOffice(UserUtils.getUser().getOffice());
			oaFinanceEa.setCreateDate(new Date());
			StringBuffer eaNo = new StringBuffer();
			String random = new Integer((int) (Math.random() * 10000)).toString();
			if (random.length() < 4) { // 自动补齐四位
				String zerofillStr = "";
				for (int zerofill = 0; zerofill < 4 - random.length(); zerofill++) {
					zerofillStr += "0";
				}
				random = zerofillStr + random;
			}
			random = (char)(Math.random()*26 + 'A') + random;
			eaNo.append("EA").append(DateUtils.getDate().replace("-", "")).append(random);
			oaFinanceEa.setEaNo(eaNo.toString());
			model.addAttribute("productList", oaFinanceProductService.findList(new OaFinanceProduct()));
			model.addAttribute("categoryList", oaFinanceCategoryService.findList(new OaFinanceCategory()));
		}

		model.addAttribute("oaFinanceEa", oaFinanceEa);
		return "modules/oa/" + view;
	}

	/**
	 * 申请单保存/修改
	 * 
	 * @param OaFinanceEa
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:oaFinanceEa:edit")
	@RequestMapping(value = "save")
	public String save(OaFinanceEa oaFinanceEa, Model model, RedirectAttributes redirectAttributes) {
		oaFinanceEaService.save(oaFinanceEa);
		addMessage(redirectAttributes, "提交审批'" + oaFinanceEa.getCreateBy().getName() + "'成功");
		return "redirect:" + adminPath + "/oa/oaFinanceEa/";
	}

	/**
	 * 完成任务
	 * 
	 * @param taskId
	 *            任务ID
	 * @param vars
	 *            任务流程变量，如下 vars.keys=flag,pass vars.values=1,true
	 *            vars.types=S,B @see
	 *            com.mingcai.edu.modules.act.utils.PropertyType
	 */
	@RequestMapping(value = "complete")
	@ResponseBody
	public String complete(Act act) {
		oaFinanceEaService.complete(act.getTaskId(), act.getVars().getVariableMap());
		return "true";
	}

	/**
	 * 获取机构JSON数据。
	 * 
	 * @param extId
	 *            排除的ID
	 * @param type
	 *            类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade
	 *            显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) String officeId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Office> list = oaFinanceEaService.findOfficeList(officeId);
		for (int i = 0; i < list.size(); i++) {
			Office e = list.get(i);
			if ((StringUtils.isBlank(extId)
					|| (extId != null && !extId.equals(e.getId()) && e.getParentIds().indexOf("," + extId + ",") == -1))
					&& (type == null || (type != null && (type.equals("1") ? type.equals(e.getType()) : true)))
					&& (grade == null || (grade != null && Integer.parseInt(e.getGrade()) <= grade.intValue()))
					&& Global.YES.equals(e.getUseable())) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("pIds", e.getParentIds());
				map.put("name", e.getName());
				if (type != null && "3".equals(type)) {
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}

}
