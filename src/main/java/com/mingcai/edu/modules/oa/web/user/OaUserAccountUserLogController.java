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
import com.mingcai.edu.modules.oa.entity.user.OaUserAccountUserLog;
import com.mingcai.edu.modules.oa.service.user.OaUserAccountUserLogService;

/**
 * 记录用户账户操作信息Controller
 * @author 坤
 * @version 2019-04-30
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/user/oaUserAccountUserLog")
public class OaUserAccountUserLogController extends BaseController {

	@Autowired
	private OaUserAccountUserLogService oaUserAccountUserLogService;
	
	@ModelAttribute
	public OaUserAccountUserLog get(@RequestParam(required=false) String id) {
		OaUserAccountUserLog entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaUserAccountUserLogService.get(id);
		}
		if (entity == null){
			entity = new OaUserAccountUserLog();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:user:oaUserAccountUserLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaUserAccountUserLog oaUserAccountUserLog, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaUserAccountUserLog> page = oaUserAccountUserLogService.findPage(new Page<OaUserAccountUserLog>(request, response), oaUserAccountUserLog); 
		model.addAttribute("page", page);
		return "modules/oa/user/oaUserAccountUserLogList";
	}

	@RequiresPermissions("oa:user:oaUserAccountUserLog:view")
	@RequestMapping(value = "form")
	public String form(OaUserAccountUserLog oaUserAccountUserLog, Model model) {
		model.addAttribute("oaUserAccountUserLog", oaUserAccountUserLog);
		return "modules/oa/user/oaUserAccountUserLogForm";
	}

	@RequiresPermissions("oa:user:oaUserAccountUserLog:edit")
	@RequestMapping(value = "save")
	public String save(OaUserAccountUserLog oaUserAccountUserLog, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaUserAccountUserLog)){
			return form(oaUserAccountUserLog, model);
		}
		oaUserAccountUserLogService.save(oaUserAccountUserLog);
		addMessage(redirectAttributes, "保存记录用户账户操作信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccountUserLog/?repage";
	}
	
	@RequiresPermissions("oa:user:oaUserAccountUserLog:edit")
	@RequestMapping(value = "delete")
	public String delete(OaUserAccountUserLog oaUserAccountUserLog, RedirectAttributes redirectAttributes) {
		oaUserAccountUserLogService.delete(oaUserAccountUserLog);
		addMessage(redirectAttributes, "删除记录用户账户操作信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccountUserLog/?repage";
	}

}