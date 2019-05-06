/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierProductType;
import com.mingcai.edu.modules.oa.service.contract.OaContractSupplierProductTypeService;

/**
 * 供应商产品标签Controller
 * @author 坤
 * @version 2018-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractSupplierProductType")
public class OaContractSupplierProductTypeController extends BaseController {

	@Autowired
	private OaContractSupplierProductTypeService oaContractSupplierProductTypeService;
	
	@ModelAttribute
	public OaContractSupplierProductType get(@RequestParam(required=false) String id) {
		OaContractSupplierProductType entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractSupplierProductTypeService.get(id);
		}
		if (entity == null){
			entity = new OaContractSupplierProductType();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractSupplierProductType:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractSupplierProductType oaContractSupplierProductType, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractSupplierProductType> page = oaContractSupplierProductTypeService.findPage(new Page<OaContractSupplierProductType>(request, response), oaContractSupplierProductType); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractSupplierProductTypeList";
	}

	@RequiresPermissions("oa:contract:oaContractSupplierProductType:view")
	@RequestMapping(value = "form")
	public String form(OaContractSupplierProductType oaContractSupplierProductType, Model model) {
		model.addAttribute("oaContractSupplierProductType", oaContractSupplierProductType);
		return "modules/oa/contract/oaContractSupplierProductTypeForm";
	}

	@RequiresPermissions("oa:contract:oaContractSupplierProductType:edit")
	@RequestMapping(value = "save")
	public String save(OaContractSupplierProductType oaContractSupplierProductType, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaContractSupplierProductType)){
			return form(oaContractSupplierProductType, model);
		}
		oaContractSupplierProductTypeService.save(oaContractSupplierProductType);
		addMessage(redirectAttributes, "保存供应商产品标签成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractSupplierProductType/?repage";
	}
	
	@RequiresPermissions("oa:contract:oaContractSupplierProductType:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractSupplierProductType oaContractSupplierProductType, RedirectAttributes redirectAttributes) {
		oaContractSupplierProductTypeService.delete(oaContractSupplierProductType);
		addMessage(redirectAttributes, "删除供应商产品标签成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractSupplierProductType/?repage";
	}

}