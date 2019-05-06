/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.service.eos.OaEosProService;
import com.mingcai.edu.modules.sys.service.SystemService;
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
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import com.mingcai.edu.modules.oa.service.eos.OaEosProStartItemService;

/**
 * 立项子项目Controller
 * @author kun
 * @version 2019-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProStartItem")
public class OaEosProStartItemController extends BaseController {

	@Autowired
	private OaEosProStartItemService oaEosProStartItemService;
	@Autowired
	private SystemService systemService;
	@ModelAttribute
	public OaEosProStartItem get(@RequestParam(required=false) String id) {
		OaEosProStartItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProStartItemService.get(id);
		}
		if (entity == null){
			entity = new OaEosProStartItem();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosProStartItem oaEosProStartItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosProStartItem> page = oaEosProStartItemService.findPage(new Page<OaEosProStartItem>(request, response), oaEosProStartItem); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProStartItemList";
	}

	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = "form")
	public String form(OaEosProStartItem oaEosProStartItem,HttpServletRequest request, Model model) {
		if(request.getParameter("eosid")!=null)
		oaEosProStartItem.setEosId(request.getParameter("eosid"));
		model.addAttribute("oaEosProStartItem", oaEosProStartItem);
		return "modules/oa/eos/oaEosProStartItemForm";
	}

	@RequiresPermissions("oa:eos:oaEosProStart:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProStartItem oaEosProStartItem, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProStartItem)){
			return form(oaEosProStartItem,request, model);
		}
		oaEosProStartItemService.save(oaEosProStartItem);
		addMessage(redirectAttributes, "保存立项子项目成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProStartItem/form?id="+oaEosProStartItem.getId();
	}
	
	@RequiresPermissions("oa:eos:oaEosProStart:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProStartItem oaEosProStartItem, RedirectAttributes redirectAttributes) {
		oaEosProStartItemService.delete(oaEosProStartItem);
		addMessage(redirectAttributes, "删除立项子项目成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProStart/form?id="+oaEosProStartItem.getEosId();
	}

}