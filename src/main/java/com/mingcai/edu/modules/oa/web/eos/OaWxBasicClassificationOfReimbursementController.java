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
import com.mingcai.edu.modules.oa.entity.eos.OaWxBasicClassificationOfReimbursement;
import com.mingcai.edu.modules.oa.service.eos.OaWxBasicClassificationOfReimbursementService;

/**
 * 报销分类Controller
 * @author 李艺杰
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaWxBasicClassificationOfReimbursement")
public class OaWxBasicClassificationOfReimbursementController extends BaseController {

	@Autowired
	private OaWxBasicClassificationOfReimbursementService oaWxBasicClassificationOfReimbursementService;
	
	@ModelAttribute
	public OaWxBasicClassificationOfReimbursement get(@RequestParam(required=false) String id) {
		OaWxBasicClassificationOfReimbursement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWxBasicClassificationOfReimbursementService.get(id);
		}
		if (entity == null){
			entity = new OaWxBasicClassificationOfReimbursement();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaWxBasicClassificationOfReimbursement:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaWxBasicClassificationOfReimbursement> page = oaWxBasicClassificationOfReimbursementService.findPage(new Page<OaWxBasicClassificationOfReimbursement>(request, response), oaWxBasicClassificationOfReimbursement); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaWxBasicClassificationOfReimbursementList";
	}

	@RequiresPermissions("oa:eos:oaWxBasicClassificationOfReimbursement:view")
	@RequestMapping(value = "form")
	public String form(OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursement, Model model) {
		model.addAttribute("oaWxBasicClassificationOfReimbursement", oaWxBasicClassificationOfReimbursement);
		return "modules/oa/eos/oaWxBasicClassificationOfReimbursementForm";
	}

	@RequiresPermissions("oa:eos:oaWxBasicClassificationOfReimbursement:edit")
	@RequestMapping(value = "save")
	public String save(OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaWxBasicClassificationOfReimbursement)){
			return form(oaWxBasicClassificationOfReimbursement, model);
		}
		oaWxBasicClassificationOfReimbursementService.save(oaWxBasicClassificationOfReimbursement);
		addMessage(redirectAttributes, "保存报销分类保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxBasicClassificationOfReimbursement/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaWxBasicClassificationOfReimbursement:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWxBasicClassificationOfReimbursement oaWxBasicClassificationOfReimbursement, RedirectAttributes redirectAttributes) {
		oaWxBasicClassificationOfReimbursementService.delete(oaWxBasicClassificationOfReimbursement);
		addMessage(redirectAttributes, "删除报销分类保存成功成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaWxBasicClassificationOfReimbursement/?repage";
	}

}