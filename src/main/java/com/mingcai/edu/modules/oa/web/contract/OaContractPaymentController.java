/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.modules.sys.utils.DictUtils;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.contract.OaContractPayment;
import com.mingcai.edu.modules.oa.service.contract.OaContractPaymentService;

/**
 * 付款情况Controller
 * @author 坤
 * @version 2018-01-13
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractPayment")
public class OaContractPaymentController extends BaseController {

	@Autowired
	private OaContractPaymentService oaContractPaymentService;
	
	@ModelAttribute
	public OaContractPayment get(@RequestParam(required=false) String id) {
		OaContractPayment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractPaymentService.get(id);
		}
		if (entity == null){
			entity = new OaContractPayment();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractPayment:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractPayment oaContractPayment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractPayment> page = oaContractPaymentService.findPage(new Page<OaContractPayment>(request, response), oaContractPayment); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractPaymentList";
	}

	@RequiresPermissions("oa:contract:oaContractPayment:view")
	@RequestMapping(value = "form")
	public String form(OaContractPayment oaContractPayment,HttpServletRequest request, Model model) {
		oaContractPayment.setContractId(request.getParameter("contractId"));
		oaContractPayment.setContractType(request.getParameter("type"));
		model.addAttribute("oaContractPayment", oaContractPayment);
		return "modules/oa/contract/oaContractPaymentForm";
	}

	@RequiresPermissions("oa:contract:oaContractPayment:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(OaContractPayment oaContractPayment, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaContractPayment)){
			return null;
		}
		oaContractPaymentService.save(oaContractPayment);
		addMessage(redirectAttributes, "保存付款情况成功");
		JSONObject obj=new JSONObject();
		obj.put("id",oaContractPayment.getId());
		obj.put("type", oaContractPayment.getBatchId());
		obj.put("money",oaContractPayment.getMoney());
		obj.put("paymentDate", DateUtils.formatDateTimeF(oaContractPayment.getPaymentDate()));
		obj.put("proportion",oaContractPayment.getProportion());
		obj.put("contractId",oaContractPayment.getContractId());
		obj.put("status",oaContractPayment.getStatus());
		return obj.toString();
	}
	
	@RequiresPermissions("oa:contract:oaContractPayment:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OaContractPayment oaContractPayment, RedirectAttributes redirectAttributes) {
		oaContractPaymentService.delete(oaContractPayment);
		addMessage(redirectAttributes, "删除付款情况成功");
		return "success";
	}

}