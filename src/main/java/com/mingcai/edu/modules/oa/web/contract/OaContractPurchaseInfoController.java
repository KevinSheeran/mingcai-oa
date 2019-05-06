/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.contract.OaContractPayment;
import com.mingcai.edu.modules.oa.service.OaFinanceProductService;
import com.mingcai.edu.modules.oa.service.contract.OaContractPaymentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.contract.OaContractPurchaseInfo;
import com.mingcai.edu.modules.oa.service.contract.OaContractPurchaseInfoService;

/**
 * 采购合同Controller
 * @author 坤
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractPurchaseInfo")
public class OaContractPurchaseInfoController extends BaseController {

	@Autowired
	private OaContractPurchaseInfoService oaContractPurchaseInfoService;
	@Autowired
	private OaFinanceProductService oaFinanceProductService;
	@Autowired
	private OaContractPaymentService oaContractPaymentService;
	@ModelAttribute
	public OaContractPurchaseInfo get(@RequestParam(required=false) String id) {
		OaContractPurchaseInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractPurchaseInfoService.get(id);
		}
		if (entity == null){
			entity = new OaContractPurchaseInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractPurchaseInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractPurchaseInfo oaContractPurchaseInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractPurchaseInfo> page = oaContractPurchaseInfoService.findPage(new Page<OaContractPurchaseInfo>(request, response), oaContractPurchaseInfo); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractPurchaseInfoList";
	}

	@RequiresPermissions("oa:contract:oaContractPurchaseInfo:view")
	@RequestMapping(value = "form")
	public String form(OaContractPurchaseInfo oaContractPurchaseInfo,HttpServletRequest request, Model model) {
		oaContractPurchaseInfo.setFinProductId(request.getParameter("finProductId"));
		model.addAttribute("oaContractPurchaseInfo", oaContractPurchaseInfo);
		model.addAttribute("oaFinanceProduct", oaFinanceProductService.get(request.getParameter("finProductId")));
		OaContractPayment pay=new OaContractPayment();
		pay.setContractId(oaContractPurchaseInfo.getId());
		model.addAttribute("OaContractPaymentList", oaContractPaymentService.findList(pay));
		return "modules/oa/contract/oaContractPurchaseInfoForm";
	}

	@RequiresPermissions("oa:contract:oaContractPurchaseInfo:edit")
	@RequestMapping(value = "save")
	public String save(OaContractPurchaseInfo oaContractPurchaseInfo,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaContractPurchaseInfo)){
			return form(oaContractPurchaseInfo,request, model);
		}
		oaContractPurchaseInfoService.save(oaContractPurchaseInfo);
		addMessage(redirectAttributes, "保存采购合同成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractPurchaseInfo/form?finProductId="+oaContractPurchaseInfo.getFinProductId()+"&id="+oaContractPurchaseInfo.getId();
	}
	
	@RequiresPermissions("oa:contract:oaContractPurchaseInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractPurchaseInfo oaContractPurchaseInfo, RedirectAttributes redirectAttributes) {
		oaContractPurchaseInfoService.delete(oaContractPurchaseInfo);
		addMessage(redirectAttributes, "删除采购合同成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractPurchaseInfo/?repage";
	}

}