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
import com.mingcai.edu.modules.oa.entity.eos.OaWxBxCorrelationSuper;
import com.mingcai.edu.modules.oa.service.eos.OaWxBxCorrelationSuperService;

/**
 * 报销主表Controller
 * @author 李艺杰
 * @version 2019-04-11
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaWxBxCorrelationSuper")
public class OaWxBxCorrelationSuperController extends BaseController {

	@Autowired
	private OaWxBxCorrelationSuperService oaWxBxCorrelationSuperService;
	
	@ModelAttribute
	public OaWxBxCorrelationSuper get(@RequestParam(required=false) String id) {
		OaWxBxCorrelationSuper entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWxBxCorrelationSuperService.get(id);
		}
		if (entity == null){
			entity = new OaWxBxCorrelationSuper();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaWxBxCorrelationSuper:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWxBxCorrelationSuper oaWxBxCorrelationSuper, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaWxBxCorrelationSuper> page = oaWxBxCorrelationSuperService.findPage(new Page<OaWxBxCorrelationSuper>(request, response), oaWxBxCorrelationSuper); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaWxBxCorrelationSuperList";
	}

	@RequiresPermissions("oa:eos:oaWxBxCorrelationSuper:view")
	@RequestMapping(value = "form")
	public String form(OaWxBxCorrelationSuper oaWxBxCorrelationSuper, Model model) {
		model.addAttribute("oaWxBxCorrelationSuper", oaWxBxCorrelationSuper);
		return "modules/oa/eos/oaWxBxCorrelationSuperForm";
	}

	@RequiresPermissions("oa:eos:oaWxBxCorrelationSuper:edit")
	@RequestMapping(value = "save")
	public String save(OaWxBxCorrelationSuper oaWxBxCorrelationSuper, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaWxBxCorrelationSuper)){
			return form(oaWxBxCorrelationSuper, model);
		}
		oaWxBxCorrelationSuperService.save(oaWxBxCorrelationSuper);
		addMessage(redirectAttributes, "保存oa_wx_extended_super成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxBxCorrelationSuper/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaWxBxCorrelationSuper:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWxBxCorrelationSuper oaWxBxCorrelationSuper, RedirectAttributes redirectAttributes) {
		oaWxBxCorrelationSuperService.delete(oaWxBxCorrelationSuper);
		addMessage(redirectAttributes, "删除oa_wx_extended_super成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxBxCorrelationSuper/?repage";
	}

}