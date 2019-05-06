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
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlows;
import com.mingcai.edu.modules.oa.service.eos.OaEosFlowsService;

/**
 * 流程Controller
 * @author 坤
 * @version 2019-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosFlows")
public class OaEosFlowsController extends BaseController {

	@Autowired
	private OaEosFlowsService oaEosFlowsService;
	
	@ModelAttribute
	public OaEosFlows get(@RequestParam(required=false) String id) {
		OaEosFlows entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosFlowsService.get(id);
		}
		if (entity == null){
			entity = new OaEosFlows();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosFlows:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosFlows oaEosFlows, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosFlows> page = oaEosFlowsService.findPage(new Page<OaEosFlows>(request, response), oaEosFlows); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosFlowsList";
	}

	@RequiresPermissions("oa:eos:oaEosFlows:view")
	@RequestMapping(value = "form")
	public String form(OaEosFlows oaEosFlows, Model model) {
		model.addAttribute("oaEosFlows", oaEosFlows);
		return "modules/oa/eos/oaEosFlowsForm";
	}

	@RequiresPermissions("oa:eos:oaEosFlows:edit")
	@RequestMapping(value = "save")
	public String save(OaEosFlows oaEosFlows, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosFlows)){
			return form(oaEosFlows, model);
		}
		oaEosFlowsService.save(oaEosFlows);
		addMessage(redirectAttributes, "保存流程成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosFlows/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosFlows:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosFlows oaEosFlows, RedirectAttributes redirectAttributes) {
		oaEosFlowsService.delete(oaEosFlows);
		addMessage(redirectAttributes, "删除流程成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosFlows/?repage";
	}

}