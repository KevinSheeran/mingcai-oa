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
import com.mingcai.edu.modules.oa.entity.eos.OaWxExtendedSuper;
import com.mingcai.edu.modules.oa.service.eos.OaWxExtendedSuperService;

/**
 * 报销主表Controller
 * @author 李艺杰
 * @version 2019-03-23
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaWxExtendedSuper")
public class OaWxExtendedSuperController extends BaseController {

	@Autowired
	private OaWxExtendedSuperService oaWxExtendedSuperService;
	
	@ModelAttribute
	public OaWxExtendedSuper get(@RequestParam(required=false) String id) {
		OaWxExtendedSuper entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWxExtendedSuperService.get(id);
		}
		if (entity == null){
			entity = new OaWxExtendedSuper();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaWxExtendedSuper:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWxExtendedSuper oaWxExtendedSuper, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaWxExtendedSuper> page = oaWxExtendedSuperService.findPage(new Page<OaWxExtendedSuper>(request, response), oaWxExtendedSuper); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaWxExtendedSuperList";
	}

	@RequiresPermissions("oa:eos:oaWxExtendedSuper:view")
	@RequestMapping(value = "form")
	public String form(OaWxExtendedSuper oaWxExtendedSuper, Model model) {
		model.addAttribute("oaWxExtendedSuper", oaWxExtendedSuper);
		return "modules/oa/eos/oaWxExtendedSuperForm";
	}

	@RequiresPermissions("oa:eos:oaWxExtendedSuper:edit")
	@RequestMapping(value = "save")
	public String save(OaWxExtendedSuper oaWxExtendedSuper, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaWxExtendedSuper)){
			return form(oaWxExtendedSuper, model);
		}
		oaWxExtendedSuperService.save(oaWxExtendedSuper);
		addMessage(redirectAttributes, "保存报销主表成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxExtendedSuper/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaWxExtendedSuper:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWxExtendedSuper oaWxExtendedSuper, RedirectAttributes redirectAttributes) {
		oaWxExtendedSuperService.delete(oaWxExtendedSuper);
		addMessage(redirectAttributes, "删除报销主表成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxExtendedSuper/?repage";
	}

}