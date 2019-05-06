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
import com.mingcai.edu.modules.oa.entity.eos.OaWxBxCorrelation;
import com.mingcai.edu.modules.oa.service.eos.OaWxBxCorrelationService;

/**
 * 基本报销关联Controller
 * @author 李艺杰
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaWxBxCorrelation")
public class OaWxBxCorrelationController extends BaseController {

	@Autowired
	private OaWxBxCorrelationService oaWxBxCorrelationService;
	
	@ModelAttribute
	public OaWxBxCorrelation get(@RequestParam(required=false) String id) {
		OaWxBxCorrelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWxBxCorrelationService.get(id);
		}
		if (entity == null){
			entity = new OaWxBxCorrelation();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaWxBxCorrelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWxBxCorrelation oaWxBxCorrelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaWxBxCorrelation> page = oaWxBxCorrelationService.findPage(new Page<OaWxBxCorrelation>(request, response), oaWxBxCorrelation); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaWxBxCorrelationList";
	}

	@RequiresPermissions("oa:eos:oaWxBxCorrelation:view")
	@RequestMapping(value = "form")
	public String form(OaWxBxCorrelation oaWxBxCorrelation, Model model) {
		model.addAttribute("oaWxBxCorrelation", oaWxBxCorrelation);
		return "modules/oa/eos/oaWxBxCorrelationForm";
	}

	@RequiresPermissions("oa:eos:oaWxBxCorrelation:edit")
	@RequestMapping(value = "save")
	public String save(OaWxBxCorrelation oaWxBxCorrelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaWxBxCorrelation)){
			return form(oaWxBxCorrelation, model);
		}
		oaWxBxCorrelationService.save(oaWxBxCorrelation);
		addMessage(redirectAttributes, "保存基本报销关联成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxBxCorrelation/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaWxBxCorrelation:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWxBxCorrelation oaWxBxCorrelation, RedirectAttributes redirectAttributes) {
		oaWxBxCorrelationService.delete(oaWxBxCorrelation);
		addMessage(redirectAttributes, "删除基本报销关联成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxBxCorrelation/?repage";
	}

}