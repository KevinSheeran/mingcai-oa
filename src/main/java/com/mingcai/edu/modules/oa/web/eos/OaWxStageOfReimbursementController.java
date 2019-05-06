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
import com.mingcai.edu.modules.oa.entity.eos.OaWxStageOfReimbursement;
import com.mingcai.edu.modules.oa.service.eos.OaWxStageOfReimbursementService;

/**
 * 报销阶段Controller
 * @author 李艺杰
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaWxStageOfReimbursement")
public class OaWxStageOfReimbursementController extends BaseController {

	@Autowired
	private OaWxStageOfReimbursementService oaWxStageOfReimbursementService;
	
	@ModelAttribute
	public OaWxStageOfReimbursement get(@RequestParam(required=false) String id) {
		OaWxStageOfReimbursement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWxStageOfReimbursementService.get(id);
		}
		if (entity == null){
			entity = new OaWxStageOfReimbursement();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaWxStageOfReimbursement:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWxStageOfReimbursement oaWxStageOfReimbursement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaWxStageOfReimbursement> page = oaWxStageOfReimbursementService.findPage(new Page<OaWxStageOfReimbursement>(request, response), oaWxStageOfReimbursement); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaWxStageOfReimbursementList";
	}

	@RequiresPermissions("oa:eos:oaWxStageOfReimbursement:view")
	@RequestMapping(value = "form")
	public String form(OaWxStageOfReimbursement oaWxStageOfReimbursement, Model model) {
		model.addAttribute("oaWxStageOfReimbursement", oaWxStageOfReimbursement);
		return "modules/oa/eos/oaWxStageOfReimbursementForm";
	}

	@RequiresPermissions("oa:eos:oaWxStageOfReimbursement:edit")
	@RequestMapping(value = "save")
	public String save(OaWxStageOfReimbursement oaWxStageOfReimbursement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaWxStageOfReimbursement)){
			return form(oaWxStageOfReimbursement, model);
		}
		oaWxStageOfReimbursementService.save(oaWxStageOfReimbursement);
		addMessage(redirectAttributes, "保存阶段保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxStageOfReimbursement/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaWxStageOfReimbursement:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWxStageOfReimbursement oaWxStageOfReimbursement, RedirectAttributes redirectAttributes) {
		oaWxStageOfReimbursementService.delete(oaWxStageOfReimbursement);
		addMessage(redirectAttributes, "删除阶段保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxStageOfReimbursement/?repage";
	}

}