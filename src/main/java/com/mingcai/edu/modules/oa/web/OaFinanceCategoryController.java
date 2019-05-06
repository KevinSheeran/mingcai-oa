/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web;

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
import com.mingcai.edu.modules.oa.entity.OaFinanceCategory;
import com.mingcai.edu.modules.oa.service.OaFinanceCategoryService;

/**
 * 报销类目管理Controller
 * @author 江坤
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaFinanceCategory")
public class OaFinanceCategoryController extends BaseController {

	@Autowired
	private OaFinanceCategoryService oaFinanceCategoryService;
	
	@ModelAttribute
	public OaFinanceCategory get(@RequestParam(required=false) String id) {
		OaFinanceCategory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaFinanceCategoryService.get(id);
		}
		if (entity == null){
			entity = new OaFinanceCategory();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaFinanceCategory:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaFinanceCategory oaFinanceCategory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaFinanceCategory> page = oaFinanceCategoryService.findPage(new Page<OaFinanceCategory>(request, response), oaFinanceCategory); 
		model.addAttribute("page", page);
		return "modules/oa/oaFinanceCategoryList";
	}

	@RequiresPermissions("oa:oaFinanceCategory:view")
	@RequestMapping(value = "form")
	public String form(OaFinanceCategory oaFinanceCategory, Model model) {
		model.addAttribute("oaFinanceCategory", oaFinanceCategory);
		return "modules/oa/oaFinanceCategoryForm";
	}

	@RequiresPermissions("oa:oaFinanceCategory:edit")
	@RequestMapping(value = "save")
	public String save(OaFinanceCategory oaFinanceCategory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaFinanceCategory)){
			return form(oaFinanceCategory, model);
		}
		oaFinanceCategoryService.save(oaFinanceCategory);
		addMessage(redirectAttributes, "保存报销类目管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaFinanceCategory/?repage";
	}
	
	@RequiresPermissions("oa:oaFinanceCategory:edit")
	@RequestMapping(value = "delete")
	public String delete(OaFinanceCategory oaFinanceCategory, RedirectAttributes redirectAttributes) {
		oaFinanceCategoryService.delete(oaFinanceCategory);
		addMessage(redirectAttributes, "删除报销类目管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaFinanceCategory/?repage";
	}

}