/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProPresentation;
import com.mingcai.edu.modules.oa.service.eos.OaEosProTimetotalItemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProTimetotal;
import com.mingcai.edu.modules.oa.service.eos.OaEosProTimetotalService;

/**
 * 项目工时汇总Controller
 * @author kun
 * @version 2019-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProTimetotal")
public class OaEosProTimetotalController extends BaseController {

	@Autowired
	private OaEosProTimetotalService oaEosProTimetotalService;
	@ModelAttribute
	public OaEosProTimetotal get(@RequestParam(required=false) String id) {
		OaEosProTimetotal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProTimetotalService.get(id);
		}
		if (entity == null){
			entity = new OaEosProTimetotal();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosProTimetotal:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosProTimetotal oaEosProTimetotal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosProTimetotal> page = oaEosProTimetotalService.findPage(new Page<OaEosProTimetotal>(request, response), oaEosProTimetotal); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProTimetotalList";
	}

	@RequiresPermissions("oa:eos:oaEosProTimetotal:view")
	@RequestMapping(value = "form")
	public String form(OaEosProTimetotal oaEosProTimetotal, Model model) {
		model.addAttribute("oaEosProTimetotal", oaEosProTimetotal);
		return "modules/oa/eos/oaEosProTimetotalForm";
	}

	@RequiresPermissions("oa:eos:oaEosProTimetotal:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProTimetotal oaEosProTimetotal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProTimetotal)){
			return form(oaEosProTimetotal, model);
		}
		oaEosProTimetotalService.save(oaEosProTimetotal);
		addMessage(redirectAttributes, "保存项目工时汇总成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProTimetotal/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosProTimetotal:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProTimetotal oaEosProTimetotal, RedirectAttributes redirectAttributes) {
		oaEosProTimetotalService.delete(oaEosProTimetotal);
		addMessage(redirectAttributes, "删除项目工时汇总成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProTimetotal/?repage";
	}

}