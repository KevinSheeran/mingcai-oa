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
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlow;
import com.mingcai.edu.modules.oa.service.eos.OaEosFlowService;

/**
 * 流程Controller
 * @author kun
 * @version 2019-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosFlow")
public class OaEosFlowController extends BaseController {

	@Autowired
	private OaEosFlowService oaEosFlowService;
	
	@ModelAttribute
	public OaEosFlow get(@RequestParam(required=false) String id) {
		OaEosFlow entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosFlowService.get(id);
		}
		if (entity == null){
			entity = new OaEosFlow();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosFlow:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosFlow oaEosFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosFlow> page = oaEosFlowService.findPage(new Page<OaEosFlow>(request, response), oaEosFlow); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosFlowList";
	}

	@RequiresPermissions("oa:eos:oaEosFlow:view")
	@RequestMapping(value = "form")
	public String form(OaEosFlow oaEosFlow, Model model) {
		model.addAttribute("oaEosFlow", oaEosFlow);
		return "modules/oa/eos/oaEosFlowForm";
	}

	@RequiresPermissions("oa:eos:oaEosFlow:edit")
	@RequestMapping(value = "save")
	public String save(OaEosFlow oaEosFlow, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosFlow)){
			return form(oaEosFlow, model);
		}
		oaEosFlowService.save(oaEosFlow);
		addMessage(redirectAttributes, "保存流程成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosFlow/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosFlow:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosFlow oaEosFlow, RedirectAttributes redirectAttributes) {
		oaEosFlowService.delete(oaEosFlow);
		addMessage(redirectAttributes, "删除流程成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosFlow/?repage";
	}

}