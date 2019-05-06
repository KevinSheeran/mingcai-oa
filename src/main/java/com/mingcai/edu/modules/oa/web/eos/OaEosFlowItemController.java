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
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowItem;
import com.mingcai.edu.modules.oa.service.eos.OaEosFlowItemService;

/**
 * 流程项Controller
 * @author 坤
 * @version 2019-03-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosFlowItem")
public class OaEosFlowItemController extends BaseController {

	@Autowired
	private OaEosFlowItemService oaEosFlowItemService;
	
	@ModelAttribute
	public OaEosFlowItem get(@RequestParam(required=false) String id) {
		OaEosFlowItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosFlowItemService.get(id);
		}
		if (entity == null){
			entity = new OaEosFlowItem();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosFlowItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosFlowItem oaEosFlowItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosFlowItem> page = oaEosFlowItemService.findPage(new Page<OaEosFlowItem>(request, response), oaEosFlowItem); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosFlowItemList";
	}

	@RequiresPermissions("oa:eos:oaEosFlowItem:view")
	@RequestMapping(value = "form")
	public String form(OaEosFlowItem oaEosFlowItem, Model model) {
		model.addAttribute("oaEosFlowItem", oaEosFlowItem);
		return "modules/oa/eos/oaEosFlowItemForm";
	}

	@RequiresPermissions("oa:eos:oaEosFlowItem:edit")
	@RequestMapping(value = "save")
	public String save(OaEosFlowItem oaEosFlowItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosFlowItem)){
			return form(oaEosFlowItem, model);
		}
		oaEosFlowItemService.save(oaEosFlowItem);
		addMessage(redirectAttributes, "保存流程项成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosFlowItem/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosFlowItem:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosFlowItem oaEosFlowItem, RedirectAttributes redirectAttributes) {
		oaEosFlowItemService.delete(oaEosFlowItem);
		addMessage(redirectAttributes, "删除流程项成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosFlowItem/?repage";
	}

}