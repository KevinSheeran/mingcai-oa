/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.week;

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
import com.mingcai.edu.modules.oa.entity.week.OaWeekWorkInfo;
import com.mingcai.edu.modules.oa.service.week.OaWeekWorkInfoService;

/**
 * 周任务详情Controller
 * @author 坤
 * @version 2018-01-24
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/week/oaWeekWorkInfo")
public class OaWeekWorkInfoController extends BaseController {

	@Autowired
	private OaWeekWorkInfoService oaWeekWorkInfoService;
	
	@ModelAttribute
	public OaWeekWorkInfo get(@RequestParam(required=false) String id) {
		OaWeekWorkInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWeekWorkInfoService.get(id);
		}
		if (entity == null){
			entity = new OaWeekWorkInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:week:oaWeekWorkInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWeekWorkInfo oaWeekWorkInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaWeekWorkInfo> page = oaWeekWorkInfoService.findPage(new Page<OaWeekWorkInfo>(request, response), oaWeekWorkInfo); 
		model.addAttribute("page", page);
		return "modules/oa/week/oaWeekWorkInfoList";
	}

	@RequiresPermissions("oa:week:oaWeekWorkInfo:view")
	@RequestMapping(value = "form")
	public String form(OaWeekWorkInfo oaWeekWorkInfo, Model model) {
		model.addAttribute("oaWeekWorkInfo", oaWeekWorkInfo);
		return "modules/oa/week/oaWeekWorkInfoForm";
	}

	@RequiresPermissions("oa:week:oaWeekWorkInfo:edit")
	@RequestMapping(value = "save")
	public String save(OaWeekWorkInfo oaWeekWorkInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaWeekWorkInfo)){
			return form(oaWeekWorkInfo, model);
		}
		oaWeekWorkInfoService.save(oaWeekWorkInfo);
		addMessage(redirectAttributes, "保存周任务详情成功");
		return "redirect:"+Global.getAdminPath()+"/oa/week/oaWeekWorkInfo/?repage";
	}
	
	@RequiresPermissions("oa:week:oaWeekWorkInfo:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWeekWorkInfo oaWeekWorkInfo, RedirectAttributes redirectAttributes) {
		oaWeekWorkInfoService.delete(oaWeekWorkInfo);
		addMessage(redirectAttributes, "删除周任务详情成功");
		return "redirect:"+Global.getAdminPath()+"/oa/week/oaWeekWorkInfo/?repage";
	}

}