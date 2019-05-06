/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
import com.mingcai.edu.modules.sys.utils.UserUtils;
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
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItem;
import com.mingcai.edu.modules.oa.service.eos.OaEosProItemService;

import java.util.List;

/**
 * 销售项目子项Controller
 * @author kun
 * @version 2019-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProItem")
public class OaEosProItemController extends BaseController {

	@Autowired
	private OaEosProItemService oaEosProItemService;
	@Autowired
	private SystemService systemService;
	@ModelAttribute
	public OaEosProItem get(@RequestParam(required=false) String id) {
		OaEosProItem entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProItemService.get(id);
		}
		if (entity == null){
			entity = new OaEosProItem();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosProItem:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosProItem oaEosProItem, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosProItem> page = oaEosProItemService.findPage(new Page<OaEosProItem>(request, response), oaEosProItem); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProItemList";
	}

	@RequiresPermissions("oa:eos:oaEosPro:edit")
	@RequestMapping(value = "form")
	public String form(OaEosProItem oaEosProItem, HttpServletRequest request, Model model) {
		if(oaEosProItem.getPersonLiableUser()==null)
			oaEosProItem.setPersonLiableUser(UserUtils.getUser());
		if(StringUtils.isNotEmpty(oaEosProItem.getUserIds())) {
			String[] s=oaEosProItem.getUserIds().split(",");
			String ss="";
			for(String sd:s){
				ss+="'"+sd+"'"+",";
			}
			if(ss.substring(ss.length()-1,ss.length()).equals(","))
				ss = ss.substring(0, ss.length() - 1);
			User user=new User();
			user.setId(ss);
			List<User> users = systemService.getByUserIds(user);
			String name = "";
			for (User u : users) {
				name += u.getName() + ",";
			}
			if (name.length() > 0) {
				name = name.substring(0, name.length() - 1);
			}
			oaEosProItem.setUsers_names(name);
		}
		if(request.getParameter("eosid")!=null)
		oaEosProItem.setEosId(request.getParameter("eosid"));
		model.addAttribute("oaEosProItem", oaEosProItem);
		return "modules/oa/eos/oaEosProItemForm";
	}

	@RequiresPermissions("oa:eos:oaEosPro:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProItem oaEosProItem, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProItem)){
			return form(oaEosProItem,request, model);
		}
		oaEosProItemService.save(oaEosProItem);
		addMessage(redirectAttributes, "保存销售项目子项成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProItem/form?id="+oaEosProItem.getId();
	}
	
	@RequiresPermissions("oa:eos:oaEosPro:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProItem oaEosProItem, RedirectAttributes redirectAttributes) {
		oaEosProItemService.delete(oaEosProItem);
		addMessage(redirectAttributes, "删除销售项目子项成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProItem/form?repage";
	}

}