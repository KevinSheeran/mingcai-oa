/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.contract.OaContractPurchaseInfo;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierProduct;
import com.mingcai.edu.modules.oa.service.contract.OaContractPurchaseInfoService;
import com.mingcai.edu.modules.oa.service.contract.OaContractServicesInfoService;
import com.mingcai.edu.modules.oa.service.contract.OaContractSupplierProductService;
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
import com.mingcai.edu.modules.oa.entity.contract.OaContractPurchaseTerms;
import com.mingcai.edu.modules.oa.service.contract.OaContractPurchaseTermsService;

import java.util.List;

/**
 * 采购合同关联Controller
 * @author 坤
 * @version 2018-01-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractPurchaseTerms")
public class OaContractPurchaseTermsController extends BaseController {

	@Autowired
	private OaContractPurchaseTermsService oaContractPurchaseTermsService;
	@Autowired
	private OaContractSupplierProductService oaContractSupplierProductService;
	@Autowired
	private OaContractPurchaseInfoService oaContractPurchaseInfoService;
	@Autowired
	private OaContractServicesInfoService oaContractServicesInfoService;
	@ModelAttribute
	public OaContractPurchaseTerms get(@RequestParam(required=false) String id) {
		OaContractPurchaseTerms entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractPurchaseTermsService.get(id);
		}
		if (entity == null){
			entity = new OaContractPurchaseTerms();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractPurchaseTerms:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractPurchaseTerms oaContractPurchaseTerms, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaContractPurchaseTerms.setPurchaseId(request.getParameter("purchaseId"));
		OaContractPurchaseInfo pinfo=oaContractPurchaseInfoService.get(request.getParameter("purchaseId"));
		String fpid=pinfo!=null?pinfo.getFinProductId():oaContractServicesInfoService.get(request.getParameter("purchaseId")).getFinProductId();
		oaContractPurchaseTerms.setFinProductId(fpid);
		List<OaContractPurchaseTerms> page = oaContractPurchaseTermsService.findTremsList(oaContractPurchaseTerms);
		for(OaContractPurchaseTerms terms:page){
			OaContractPurchaseTerms tt= oaContractPurchaseTermsService.countNumber(terms);
			if(tt==null){
				terms.setCountNumber(0);
			}else{
				terms.setCountNumber(tt.getCountNumber());
			}

		}
		model.addAttribute("oaContractPurchaseTerms", oaContractPurchaseTerms);
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractPurchaseTermsSelectList";
	}
	@RequiresPermissions("oa:contract:oaContractPurchaseTerms:view")
	@RequestMapping(value = "getTremlist")
	public String getTremlist(OaContractPurchaseTerms oaContractPurchaseTerms, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaContractPurchaseTerms.setPurchaseId(request.getParameter("purchaseId"));
		List<OaContractPurchaseTerms> page = oaContractPurchaseTermsService.findList(oaContractPurchaseTerms);
		for(OaContractPurchaseTerms terms:page){
			OaContractPurchaseTerms tt= oaContractPurchaseTermsService.countNumber(terms);
			if(tt==null){
				terms.setCountNumber(0);
			}else{
				terms.setCountNumber(tt.getCountNumber());
			}
		}
		model.addAttribute("oaContractPurchaseTerms", oaContractPurchaseTerms);
		model.addAttribute("page", page);
		String supplierId=request.getParameter("supplierId");
		model.addAttribute("hide",false);
		if(supplierId!=null&&supplierId!="") {
			OaContractSupplierProduct sproduct = new OaContractSupplierProduct();
			sproduct.setSupplierId(supplierId);
			model.addAttribute("productList", oaContractSupplierProductService.findAllList(sproduct));
			model.addAttribute("hide",true);
		}
		return "modules/oa/contract/oaContractPurchaseTermsList";
	}
	@RequiresPermissions("oa:contract:oaContractPurchaseTerms:view")
	@RequestMapping(value = "form")
	public String form(OaContractPurchaseTerms oaContractPurchaseTerms, Model model) {
		model.addAttribute("oaContractPurchaseTerms", oaContractPurchaseTerms);
		return "modules/oa/contract/oaContractPurchaseTermsForm";
	}

	@RequiresPermissions("oa:contract:oaContractPurchaseTerms:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(OaContractPurchaseTerms oaContractPurchaseTerms, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		oaContractPurchaseTermsService.save(request);
		addMessage(redirectAttributes, "保存采购合同关联成功");
		return "success";
	}
	@RequiresPermissions("oa:contract:oaContractPurchaseTerms:edit")
	@RequestMapping(value = "edit")
	@ResponseBody
	public String edit(OaContractPurchaseTerms oaContractPurchaseTerms, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		oaContractPurchaseTerms.setNumber(Double.parseDouble(request.getParameter("number")));
		oaContractPurchaseTerms.setUnitPrice(Double.parseDouble(request.getParameter("unitPrice")));
		oaContractPurchaseTerms.setProductId(request.getParameter("productId"));
		oaContractPurchaseTermsService.save(oaContractPurchaseTerms);
		addMessage(redirectAttributes, "保存采购合同关联成功");
		oaContractPurchaseTerms=oaContractPurchaseTermsService.get(oaContractPurchaseTerms.getId());
		OaContractPurchaseTerms tt= oaContractPurchaseTermsService.countNumber(oaContractPurchaseTerms);
		if(tt==null){
			oaContractPurchaseTerms.setCountNumber(0);
		}else{
			oaContractPurchaseTerms.setCountNumber(tt.getCountNumber());
		}
		return oaContractPurchaseTerms.toString();
	}
	@RequiresPermissions("oa:contract:oaContractPurchaseTerms:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OaContractPurchaseTerms oaContractPurchaseTerms, RedirectAttributes redirectAttributes) {
		oaContractPurchaseTermsService.delete(oaContractPurchaseTerms);
		addMessage(redirectAttributes, "删除采购合同关联成功");
		return "success";
	}

}