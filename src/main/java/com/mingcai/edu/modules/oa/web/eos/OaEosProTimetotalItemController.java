/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProTimetotal;
import com.mingcai.edu.modules.oa.service.eos.OaEosProTimetotalService;
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
import com.mingcai.edu.modules.oa.entity.eos.OaEosProTimetotalItem;
import com.mingcai.edu.modules.oa.service.eos.OaEosProTimetotalItemService;

/**
 * 汇总详情Controller
 * @author kun
 * @version 2019-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProTimetotalItem")
public class OaEosProTimetotalItemController extends BaseController {

	@Autowired
	private OaEosProTimetotalItemService oaEosProTimetotalItemService;
	@Autowired
	private OaEosProTimetotalService oaEosProTimetotalService;
	@ModelAttribute
	public OaEosProTimetotalItem get(@RequestParam(required=false) String id) {
		OaEosProTimetotalItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProTimetotalItemService.get(id);
		}
		if (entity == null){
			entity = new OaEosProTimetotalItem();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosProTimetotalItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosProTimetotalItem oaEosProTimetotalItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosProTimetotalItem> page = oaEosProTimetotalItemService.findPage(new Page<OaEosProTimetotalItem>(request, response), oaEosProTimetotalItem); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProTimetotalItemList";
	}

	@RequiresPermissions("oa:eos:oaEosProTimetotalItem:view")
	@RequestMapping(value = "form")
	public String form(OaEosProTimetotalItem oaEosProTimetotalItem, Model model) {
		model.addAttribute("oaEosProTimetotalItem", oaEosProTimetotalItem);
		return "modules/oa/eos/oaEosProTimetotalItemForm";
	}
	@RequiresPermissions("oa:eos:oaEosProPresentation:view")
	@RequestMapping(value = {"page"})
	public String page(OaEosProTimetotalItem oaEosProTimetotalItem,@RequestParam(required=false) String proid ,HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("proid",proid);
		OaEosProTimetotal total=oaEosProTimetotalService.get(proid);
		model.addAttribute("total",total);
		return "modules/oa/eos/oaEosProSummaryPage";
	}

	@RequestMapping(value = {"pageJson"})
	@ResponseBody
	public String pageJson(OaEosProTimetotalItem OaEosProTimetotalItem, @RequestParam(required=false) String proid, HttpServletRequest request, HttpServletResponse response, Model model) {
		OaEosProTimetotalItem.setProId(proid);
		Page<OaEosProTimetotalItem> page = oaEosProTimetotalItemService.findPage(new Page<OaEosProTimetotalItem>(request, response), OaEosProTimetotalItem);
		Gson g=new Gson();
		return g.toJson(page);
	}
	@RequiresPermissions("oa:eos:oaEosProTimetotalItem:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProTimetotalItem oaEosProTimetotalItem, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProTimetotalItem)){
			return form(oaEosProTimetotalItem, model);
		}
		oaEosProTimetotalItemService.save(oaEosProTimetotalItem);
		addMessage(redirectAttributes, "保存汇总详情成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProTimetotalItem/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosProTimetotalItem:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProTimetotalItem oaEosProTimetotalItem, RedirectAttributes redirectAttributes) {
		oaEosProTimetotalItemService.delete(oaEosProTimetotalItem);
		addMessage(redirectAttributes, "删除汇总详情成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProTimetotalItem/?repage";
	}

}