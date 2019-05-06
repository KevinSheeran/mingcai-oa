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
import com.mingcai.edu.modules.oa.entity.OaProductDir;
import com.mingcai.edu.modules.oa.service.OaProductDirService;

/**
 * 项目文件夹Controller
 * @author 江坤
 * @version 2017-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaProductDir")
public class OaProductDirController extends BaseController {

	@Autowired
	private OaProductDirService oaProductDirService;
	
	@ModelAttribute
	public OaProductDir get(@RequestParam(required=false) String id) {
		OaProductDir entity = null;
		if (StringUtils.isNotEmpty(id)){
			entity = oaProductDirService.get(id);
		}
		if (entity == null){
			entity = new OaProductDir();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaProductDir:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProductDir oaProductDir, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaProductDir> page = oaProductDirService.findPage(new Page<OaProductDir>(request, response), oaProductDir); 
		model.addAttribute("page", page);
		return "modules/oa/oaProductDirList";
	}

	@RequiresPermissions("oa:oaProductDir:view")
	@RequestMapping(value = "form")
	public String form(OaProductDir oaProductDir, Model model) {
		model.addAttribute("oaProductDir", oaProductDir);
		return "modules/oa/oaProductDirForm";
	}

	@RequiresPermissions("oa:oaProductDir:edit")
	@RequestMapping(value = "save")
	public String save(OaProductDir oaProductDir, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaProductDir)){
			return form(oaProductDir, model);
		}
		oaProductDirService.save(oaProductDir);
		addMessage(redirectAttributes, "保存项目文件夹成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProductDir/?repage";
	}
	
	@RequiresPermissions("oa:oaProductDir:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProductDir oaProductDir, RedirectAttributes redirectAttributes) {
		oaProductDirService.delete(oaProductDir);
		addMessage(redirectAttributes, "删除项目文件夹成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProductDir/?repage";
	}

}