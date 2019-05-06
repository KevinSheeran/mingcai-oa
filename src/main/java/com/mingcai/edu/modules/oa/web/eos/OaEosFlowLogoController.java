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
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowLogo;
import com.mingcai.edu.modules.oa.service.eos.OaEosFlowLogoService;

/**
 * 操作日志Controller
 * @author 坤
 * @version 2019-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosFlowLogo")
public class OaEosFlowLogoController extends BaseController {

	@Autowired
	private OaEosFlowLogoService oaEosFlowLogoService;
	
	@ModelAttribute
	public OaEosFlowLogo get(@RequestParam(required=false) String id) {
		OaEosFlowLogo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosFlowLogoService.get(id);
		}
		if (entity == null){
			entity = new OaEosFlowLogo();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosFlowLogo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosFlowLogo oaEosFlowLogo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosFlowLogo> page = oaEosFlowLogoService.findPage(new Page<OaEosFlowLogo>(request, response), oaEosFlowLogo); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosFlowLogoList";
	}

	@RequiresPermissions("oa:eos:oaEosFlowLogo:view")
	@RequestMapping(value = "form")
	public String form(OaEosFlowLogo oaEosFlowLogo, Model model) {
		model.addAttribute("oaEosFlowLogo", oaEosFlowLogo);
		return "modules/oa/eos/oaEosFlowLogoForm";
	}

	@RequiresPermissions("oa:eos:oaEosFlowLogo:edit")
	@RequestMapping(value = "save")
	public String save(OaEosFlowLogo oaEosFlowLogo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosFlowLogo)){
			return form(oaEosFlowLogo, model);
		}
		oaEosFlowLogoService.save(oaEosFlowLogo);
		addMessage(redirectAttributes, "保存操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosFlowLogo/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosFlowLogo:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosFlowLogo oaEosFlowLogo, RedirectAttributes redirectAttributes) {
		oaEosFlowLogoService.delete(oaEosFlowLogo);
		addMessage(redirectAttributes, "删除操作日志成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosFlowLogo/?repage";
	}

}