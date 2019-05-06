/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.contract.OaContractInfo;
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
import com.mingcai.edu.modules.oa.entity.OaFinanceProduct;
import com.mingcai.edu.modules.oa.service.OaFinanceProductService;

/**
 * 报销项目管理Controller
 * @author 江坤
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaFinanceProduct")
public class OaFinanceProductController extends BaseController {
	@Autowired
	private OaFinanceProductService oaFinanceProductService;
	
	@ModelAttribute
	public OaFinanceProduct get(@RequestParam(required=false) String id) {
		OaFinanceProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaFinanceProductService.get(id);
		}
		if (entity == null){
			entity = new OaFinanceProduct();
		}
		return entity;
	}
	@RequiresPermissions("oa:contract:oaContractInfo:view")
	@RequestMapping(value = {"proList"})
	public String prolist(OaFinanceProduct oaFinanceProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaFinanceProduct> page = oaFinanceProductService.findPage(new Page<OaFinanceProduct>(request, response), oaFinanceProduct);
		model.addAttribute("page", page);
		return "modules/oa/contract/oaFinanceProductList";
	}
	@RequiresPermissions("oa:oaFinanceProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaFinanceProduct oaFinanceProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaFinanceProduct> page = oaFinanceProductService.findPage(new Page<OaFinanceProduct>(request, response), oaFinanceProduct); 
		model.addAttribute("page", page);
		return "modules/oa/oaFinanceProductList";
	}

	@RequiresPermissions("oa:oaFinanceProduct:view")
	@RequestMapping(value = "form")
	public String form(OaFinanceProduct oaFinanceProduct, Model model) {
		model.addAttribute("oaFinanceProduct", oaFinanceProduct);
		return "modules/oa/oaFinanceProductForm";
	}

	@RequiresPermissions("oa:oaFinanceProduct:edit")
	@RequestMapping(value = "save")
	public String save(OaFinanceProduct oaFinanceProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaFinanceProduct)){
			return form(oaFinanceProduct, model);
		}
		oaFinanceProductService.save(oaFinanceProduct);
		addMessage(redirectAttributes, "保存报销项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaFinanceProduct/?repage";
	}
	
	@RequiresPermissions("oa:oaFinanceProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(OaFinanceProduct oaFinanceProduct, RedirectAttributes redirectAttributes) {
		oaFinanceProductService.delete(oaFinanceProduct);
		addMessage(redirectAttributes, "删除报销项目管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaFinanceProduct/?repage";
	}

}