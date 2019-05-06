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
import com.mingcai.edu.modules.oa.entity.eos.OaEosProCplan;
import com.mingcai.edu.modules.oa.service.eos.OaEosProCplanService;

/**
 * 计划收款Controller
 * @author kun
 * @version 2019-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProCplan")
public class OaEosProCplanController extends BaseController {

	@Autowired
	private OaEosProCplanService oaEosProCplanService;
	
	@ModelAttribute
	public OaEosProCplan get(@RequestParam(required=false) String id) {
		OaEosProCplan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProCplanService.get(id);
		}
		if (entity == null){
			entity = new OaEosProCplan();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosProCplan oaEosProCplan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosProCplan> page = oaEosProCplanService.findPage(new Page<OaEosProCplan>(request, response), oaEosProCplan); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProCplanList";
	}

	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = "form")
	public String form(OaEosProCplan oaEosProCplan, Model model) {
		model.addAttribute("oaEosProCplan", oaEosProCplan);
		return "modules/oa/eos/oaEosProCplanForm";
	}

	@RequiresPermissions("oa:eos:oaEosProStart:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProCplan oaEosProCplan, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProCplan)){
			return form(oaEosProCplan, model);
		}
		oaEosProCplanService.save(oaEosProCplan);
		addMessage(redirectAttributes, "保存计划收款成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProCplan/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosProStart:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProCplan oaEosProCplan, RedirectAttributes redirectAttributes) {
		oaEosProCplanService.delete(oaEosProCplan);
		addMessage(redirectAttributes, "删除计划收款成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProCplan/?repage";
	}

}