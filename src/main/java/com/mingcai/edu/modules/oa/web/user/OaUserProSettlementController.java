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
import com.mingcai.edu.modules.oa.entity.user.OaUserProSettlement;
import com.mingcai.edu.modules.oa.service.user.OaUserProSettlementService;

/**
 * 项目费用结算Controller
 * @author kun
 * @version 2019-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/user/oaUserProSettlement")
public class OaUserProSettlementController extends BaseController {

	@Autowired
	private OaUserProSettlementService oaUserProSettlementService;
	
	@ModelAttribute
	public OaUserProSettlement get(@RequestParam(required=false) String id) {
		OaUserProSettlement entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaUserProSettlementService.get(id);
		}
		if (entity == null){
			entity = new OaUserProSettlement();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:user:oaUserProSettlement:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaUserProSettlement oaUserProSettlement, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaUserProSettlement> page = oaUserProSettlementService.findPage(new Page<OaUserProSettlement>(request, response), oaUserProSettlement); 
		model.addAttribute("page", page);
		return "modules/oa/user/oaUserProSettlementList";
	}

	@RequiresPermissions("oa:user:oaUserProSettlement:view")
	@RequestMapping(value = "form")
	public String form(OaUserProSettlement oaUserProSettlement, Model model) {
		model.addAttribute("oaUserProSettlement", oaUserProSettlement);
		return "modules/oa/user/oaUserProSettlementForm";
	}

	@RequiresPermissions("oa:user:oaUserProSettlement:edit")
	@RequestMapping(value = "save")
	public String save(OaUserProSettlement oaUserProSettlement, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaUserProSettlement)){
			return form(oaUserProSettlement, model);
		}
		oaUserProSettlementService.save(oaUserProSettlement);
		addMessage(redirectAttributes, "保存项目费用结算成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserProSettlement/?repage";
	}
	
	@RequiresPermissions("oa:user:oaUserProSettlement:edit")
	@RequestMapping(value = "delete")
	public String delete(OaUserProSettlement oaUserProSettlement, RedirectAttributes redirectAttributes) {
		oaUserProSettlementService.delete(oaUserProSettlement);
		addMessage(redirectAttributes, "删除项目费用结算成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserProSettlement/?repage";
	}

}