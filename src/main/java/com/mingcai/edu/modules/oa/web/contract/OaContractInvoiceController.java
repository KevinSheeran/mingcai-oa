/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.contract.OaContractInfo;
import com.mingcai.edu.modules.oa.entity.contract.OaContractPurchaseInfo;
import com.mingcai.edu.modules.oa.entity.contract.OaContractServicesInfo;
import com.mingcai.edu.modules.oa.service.contract.OaContractInfoService;
import com.mingcai.edu.modules.oa.service.contract.OaContractPurchaseInfoService;
import com.mingcai.edu.modules.oa.service.contract.OaContractServicesInfoService;
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
import com.mingcai.edu.modules.oa.entity.contract.OaContractInvoice;
import com.mingcai.edu.modules.oa.service.contract.OaContractInvoiceService;

/**
 * 开票信息Controller
 * @author 坤
 * @version 2018-01-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractInvoice")
public class OaContractInvoiceController extends BaseController {

	@Autowired
	private OaContractInvoiceService oaContractInvoiceService;
	@Autowired
	private OaContractInfoService oaContractInfoService;
	@Autowired
	private OaContractPurchaseInfoService oaContractPurchaseInfoService;
	@Autowired
	private OaContractServicesInfoService oaContractServicesInfoService;
	@ModelAttribute
	public OaContractInvoice get(@RequestParam(required=false) String id) {
		OaContractInvoice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractInvoiceService.get(id);
		}
		if (entity == null){
			entity = new OaContractInvoice();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractInvoice:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractInvoice oaContractInvoice, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaContractInvoice.setContractId(request.getParameter("contractId"));
		Page<OaContractInvoice> page = oaContractInvoiceService.findPage(new Page<OaContractInvoice>(request, response), oaContractInvoice);
		String pageNo=request.getParameter("pageNo");
		String pageSize=request.getParameter("pageSize");
		if(!StringUtils.isNotEmpty(pageNo)){
			pageNo="1";
		}
		if(!StringUtils.isNotEmpty(pageSize)){
			pageSize="1";
		}
		page.setPageNo(Integer.parseInt(pageNo));
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", page.toString());
		model.addAttribute("list",page.getList());
		OaContractInvoice invoice=oaContractInvoiceService.sumMoney(oaContractInvoice);
		oaContractInvoice.setTotal(invoice==null?0:invoice.getTotal());
		model.addAttribute("oaContractInvoice", oaContractInvoice);

		return "modules/oa/contract/oaContractInvoiceList";
	}

	@RequiresPermissions("oa:contract:oaContractInvoice:view")
	@RequestMapping(value = "form")
	public String form(OaContractInvoice oaContractInvoice,  HttpServletRequest request,Model model) {
		if(!StringUtils.isNotEmpty(oaContractInvoice.getId())) {
			if ("1".equals(request.getParameter("types"))) {
				OaContractInfo info = oaContractInfoService.get(request.getParameter("contractId"));
				oaContractInvoice.setCompanyName(info.getName());
				oaContractInvoice.setBankAccount(info.getBankAccount());
				oaContractInvoice.setOpeningBank(info.getOpeningBank());
				oaContractInvoice.setDutyParagraph(info.getDutyParagraph());
			}
			if ("2".equals(request.getParameter("types"))) {
				OaContractPurchaseInfo pinfo = oaContractPurchaseInfoService.get(request.getParameter("contractId"));
				oaContractInvoice.setCompanyName(pinfo.getName());
				oaContractInvoice.setBankAccount(pinfo.getBankAccount());
				oaContractInvoice.setOpeningBank(pinfo.getOpeningBank());
				oaContractInvoice.setDutyParagraph(pinfo.getDutyParagraph());
			}
			if ("3".equals(request.getParameter("types"))) {
				OaContractServicesInfo sinfo = oaContractServicesInfoService.get(request.getParameter("contractId"));
				oaContractInvoice.setCompanyName(sinfo.getName());
				oaContractInvoice.setBankAccount(sinfo.getBankAccount());
				oaContractInvoice.setOpeningBank(sinfo.getOpeningBank());
				oaContractInvoice.setDutyParagraph(sinfo.getDutyParagraph());
			}
		}
		model.addAttribute("oaContractInvoice", oaContractInvoice);
		return "modules/oa/contract/oaContractInvoiceForm";
	}

	@RequiresPermissions("oa:contract:oaContractInvoice:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(OaContractInvoice oaContractInvoice, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaContractInvoice)){
			return form(oaContractInvoice,request, model);
		}
		oaContractInvoiceService.save(oaContractInvoice);
		addMessage(redirectAttributes, "保存开票信息成功");
		return "success";
	}
	
	@RequiresPermissions("oa:contract:oaContractInvoice:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractInvoice oaContractInvoice, RedirectAttributes redirectAttributes) {
		oaContractInvoiceService.delete(oaContractInvoice);
		addMessage(redirectAttributes, "删除开票信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractInvoice/?repage";
	}

}