/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.user;

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
import com.mingcai.edu.modules.oa.entity.user.OaUserAccountLog;
import com.mingcai.edu.modules.oa.service.user.OaUserAccountLogService;

/**
 * 月结记录Controller
 * @author kun
 * @version 2019-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/user/oaUserAccountLog")
public class OaUserAccountLogController extends BaseController {

	@Autowired
	private OaUserAccountLogService oaUserAccountLogService;
	
	@ModelAttribute
	public OaUserAccountLog get(@RequestParam(required=false) String id) {
		OaUserAccountLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaUserAccountLogService.get(id);
		}
		if (entity == null){
			entity = new OaUserAccountLog();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:user:oaUserAccountLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaUserAccountLog oaUserAccountLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaUserAccountLog> page = oaUserAccountLogService.findPage(new Page<OaUserAccountLog>(request, response), oaUserAccountLog); 
		model.addAttribute("page", page);
		return "modules/oa/user/oaUserAccountLogList";
	}

	@RequiresPermissions("oa:user:oaUserAccountLog:view")
	@RequestMapping(value = "form")
	public String form(OaUserAccountLog oaUserAccountLog, Model model) {
		model.addAttribute("oaUserAccountLog", oaUserAccountLog);
		return "modules/oa/user/oaUserAccountLogForm";
	}

	@RequiresPermissions("oa:user:oaUserAccountLog:edit")
	@RequestMapping(value = "save")
	public String save(OaUserAccountLog oaUserAccountLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaUserAccountLog)){
			return form(oaUserAccountLog, model);
		}
		oaUserAccountLogService.save(oaUserAccountLog);
		addMessage(redirectAttributes, "保存月结记录成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccountLog/?repage";
	}
	
	@RequiresPermissions("oa:user:oaUserAccountLog:edit")
	@RequestMapping(value = "delete")
	public String delete(OaUserAccountLog oaUserAccountLog, RedirectAttributes redirectAttributes) {
		oaUserAccountLogService.delete(oaUserAccountLog);
		addMessage(redirectAttributes, "删除月结记录成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccountLog/?repage";
	}

}