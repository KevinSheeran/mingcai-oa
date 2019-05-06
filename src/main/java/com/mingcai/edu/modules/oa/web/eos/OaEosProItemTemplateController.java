/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

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
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItemTemplate;
import com.mingcai.edu.modules.oa.service.eos.OaEosProItemTemplateService;

/**
 * 销售立项子项模板Controller
 * @author 李艺杰
 * @version 2019-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProItemTemplate")
public class OaEosProItemTemplateController extends BaseController {

	@Autowired
	private OaEosProItemTemplateService oaEosProItemTemplateService;
	
	@ModelAttribute
	public OaEosProItemTemplate get(@RequestParam(required=false) String id) {
		OaEosProItemTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProItemTemplateService.get(id);
		}
		if (entity == null){
			entity = new OaEosProItemTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosProItemTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosProItemTemplate oaEosProItemTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosProItemTemplate> page = oaEosProItemTemplateService.findPage(new Page<OaEosProItemTemplate>(request, response), oaEosProItemTemplate); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProItemTemplateList";
	}

	@RequiresPermissions("oa:eos:oaEosProItemTemplate:view")
	@RequestMapping(value = "form")
	public String form(OaEosProItemTemplate oaEosProItemTemplate, Model model) {
		model.addAttribute("oaEosProItemTemplate", oaEosProItemTemplate);
		return "modules/oa/eos/oaEosProItemTemplateForm";
	}

	@RequiresPermissions("oa:eos:oaEosProItemTemplate:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProItemTemplate oaEosProItemTemplate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProItemTemplate)){
			return form(oaEosProItemTemplate, model);
		}
		oaEosProItemTemplateService.save(oaEosProItemTemplate);
		addMessage(redirectAttributes, "保存销售立项子项模板成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProItemTemplate/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosProItemTemplate:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProItemTemplate oaEosProItemTemplate, RedirectAttributes redirectAttributes) {
		oaEosProItemTemplateService.delete(oaEosProItemTemplate);
		addMessage(redirectAttributes, "删除销售立项子项模板成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProItemTemplate/?repage";
	}

}