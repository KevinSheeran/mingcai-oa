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
import com.mingcai.edu.modules.oa.entity.contract.OaContractServicesInfo;
import com.mingcai.edu.modules.oa.service.contract.OaContractServicesInfoService;

/**
 * 劳务合同Controller
 * @author 坤
 * @version 2018-01-12
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractServicesInfo")
public class OaContractServicesInfoController extends BaseController {

	@Autowired
	private OaContractServicesInfoService oaContractServicesInfoService;
	@Autowired
	private OaFinanceProductService oaFinanceProductService;
	@Autowired
	private OaContractPaymentService oaContractPaymentService;
	@ModelAttribute
	public OaContractServicesInfo get(@RequestParam(required=false) String id) {
		OaContractServicesInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractServicesInfoService.get(id);
		}
		if (entity == null){
			entity = new OaContractServicesInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractServicesInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractServicesInfo oaContractServicesInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractServicesInfo> page = oaContractServicesInfoService.findPage(new Page<OaContractServicesInfo>(request, response), oaContractServicesInfo); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractServicesInfoList";
	}

	@RequiresPermissions("oa:contract:oaContractServicesInfo:view")
	@RequestMapping(value = "form")
	public String form(OaContractServicesInfo oaContractServicesInfo,HttpServletRequest request, Model model) {
		oaContractServicesInfo.setFinProductId(request.getParameter("finProductId"));
		model.addAttribute("oaContractServicesInfo", oaContractServicesInfo);
		model.addAttribute("oaFinanceProduct", oaFinanceProductService.get(request.getParameter("finProductId")));
		OaContractPayment pay=new OaContractPayment();
		pay.setContractId(oaContractServicesInfo.getId());
		model.addAttribute("OaContractPaymentList", oaContractPaymentService.findList(pay));
		return "modules/oa/contract/oaContractServicesInfoForm";
	}

	@RequiresPermissions("oa:contract:oaContractServicesInfo:edit")
	@RequestMapping(value = "save")
	public String save(OaContractServicesInfo oaContractServicesInfo,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaContractServicesInfo)){
			return form(oaContractServicesInfo,request, model);
		}
		oaContractServicesInfoService.save(oaContractServicesInfo);
		addMessage(redirectAttributes, "保存劳务合同成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractServicesInfo/form?finProductId="+oaContractServicesInfo.getFinProductId()+"&id="+oaContractServicesInfo.getId();
	}
	
	@RequiresPermissions("oa:contract:oaContractServicesInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractServicesInfo oaContractServicesInfo, RedirectAttributes redirectAttributes) {
		oaContractServicesInfoService.delete(oaContractServicesInfo);
		addMessage(redirectAttributes, "删除劳务合同成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractServicesInfo/?repage";
	}

}