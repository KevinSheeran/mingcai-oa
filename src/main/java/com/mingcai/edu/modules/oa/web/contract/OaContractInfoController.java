/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.OaFinanceProduct;
import com.mingcai.edu.modules.oa.entity.contract.*;
import com.mingcai.edu.modules.oa.service.OaFinanceProductService;
import com.mingcai.edu.modules.oa.service.contract.*;
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

import java.util.List;

/**
 * 合同管理Controller
 * @author 坤
 * @version 2018-01-10
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractInfo")
public class OaContractInfoController extends BaseController {
	@Autowired
	private OaContractInfoService oaContractInfoService;
	@Autowired
	private OaFinanceProductService oaFinanceProductService;
	@Autowired
	private OaContractServicesInfoService oaContractServicesInfoService;
	@Autowired
	private OaContractPurchaseInfoService oaContractPurchaseInfoService;

	@Autowired
	private OaContractTermsService oaContractTermsService;
	@Autowired
	private OaContractPaymentService oaContractPaymentService;
	@ModelAttribute
	public OaContractInfo get(@RequestParam(required=false) String id) {
		OaContractInfo entity = null;
		if (StringUtils.isNotEmpty(id)){
			entity = oaContractInfoService.get(id);
		}
		if (entity == null){
			entity = new OaContractInfo();

		}
		return entity;
	}

	@RequiresPermissions("oa:contract:oaContractInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractInfo oaContractInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractInfo> page = oaContractInfoService.findPage(new Page<OaContractInfo>(request, response), oaContractInfo); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractInfoList";
	}


	@RequiresPermissions("oa:contract:oaContractInfo:view")
	@RequestMapping(value = "mainForm")
	public String mainForm(OaContractInfo oaContractInfo,HttpServletRequest request, Model model) {
		model.addAttribute("oaContractInfo", oaContractInfoService.getPro(request.getParameter("finProductId")));
		model.addAttribute("oaFinanceProduct", oaFinanceProductService.get(request.getParameter("finProductId")));
		OaContractPurchaseInfo opi=new OaContractPurchaseInfo();
		opi.setFinProductId(request.getParameter("finProductId"));
		model.addAttribute("purchaseInfoList",oaContractPurchaseInfoService.findList(opi));
		OaContractServicesInfo sinfo=new OaContractServicesInfo();
		sinfo.setFinProductId(request.getParameter("finProductId"));
		model.addAttribute("servicesInfoList",oaContractServicesInfoService.findList(sinfo));
		return "modules/oa/contract/oaContractInfoMainForm";
	}
	@RequiresPermissions("oa:contract:oaContractInfo:view")
	@RequestMapping(value = "form")
	public String form(OaContractInfo oaContractInfo,HttpServletRequest request, Model model) {
		OaFinanceProduct oaf = oaFinanceProductService.get(request.getParameter("finProductId"));
		if(!StringUtils.isNotEmpty(oaContractInfo.getId())) {
			oaContractInfo = oaContractInfoService.getPro(oaContractInfo.getFinProductId());
			if (oaContractInfo == null) {
				OaContractInfo cloneoacontract = oaContractInfoService.getByName(oaf.getCompany().getName());
				if (cloneoacontract != null) {
					oaContractInfo = new OaContractInfo();
					oaContractInfo.setBankAccount(cloneoacontract.getBankAccount());
					oaContractInfo.setOpeningBank(cloneoacontract.getOpeningBank());
					oaContractInfo.setDutyParagraph(cloneoacontract.getDutyParagraph());
					oaContractInfo.setContactNumber(cloneoacontract.getContactNumber());
				}else{
					oaContractInfo = new OaContractInfo();
				}
			}else{
				oaContractInfo = new OaContractInfo();
			}
		}
		model.addAttribute("oaContractInfo", oaContractInfo);
		model.addAttribute("oaFinanceProduct", oaf);
		OaContractPayment pay=new OaContractPayment();
		pay.setContractId(oaContractInfo.getId());
		model.addAttribute("OaContractPaymentList", oaContractPaymentService.findList(pay));
		return "modules/oa/contract/oaContractInfoForm";
	}

	@RequiresPermissions("oa:contract:oaContractInfo:edit")
	@RequestMapping(value = "save")
	public String save(OaContractInfo oaContractInfo, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaContractInfo)){
			return form(oaContractInfo,request, model);
		}
		oaContractInfoService.save(oaContractInfo);
		addMessage(redirectAttributes, "保存合同管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractInfo/form?finProductId="+oaContractInfo.getFinProductId()+"&id="+oaContractInfo.getId();
	}
	
	@RequiresPermissions("oa:contract:oaContractInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractInfo oaContractInfo, RedirectAttributes redirectAttributes) {
		oaContractInfoService.delete(oaContractInfo);
		addMessage(redirectAttributes, "删除合同管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractInfo/?repage";
	}

}