/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.service.eos.OaEosFlowItemService;
import com.mingcai.edu.modules.oa.service.eos.OaEosProStartItemService;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.service.eos.OaEosProUnService;

import java.util.List;
import java.util.TreeMap;

/**
 * 非销售项目立项Controller
 * @author kun
 * @version 2019-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProUn")
public class OaEosProUnController extends BaseController {

	@Autowired
	private OaEosProUnService oaEosProUnService;
	@Autowired
	private OaEosFlowItemService oaEosFlowItemService;
	@Autowired
	private OaEosProStartItemService oaEosProStartItemService;
	@ModelAttribute
	public OaEosProUn get(@RequestParam(required=false) String id) {
		OaEosProUn entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProUnService.get(id);
		}
		if (entity == null){
			entity = new OaEosProUn();
		}
		entity.setUserId(UserUtils.getUser().getId());
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosProUn:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosProUn oaEosProUn, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Role> rols= UserUtils.getUser().getRoleList();
		OaEosProUn proUn=new OaEosProUn();
		for(Role role:rols){
			if(role.getName().equals(proAdmin)){
				oaEosProUn.setLeader(true);
				proUn.setLeader(true);
			}
		}
		if(StringUtils.isNotEmpty(request.getParameter("status"))){
			oaEosProUn.setStatus(Integer.parseInt(request.getParameter("status")));
		}else{
			oaEosProUn.setStatus(null);
		}
		Page<OaEosProUn> page = oaEosProUnService.findPage(new Page<OaEosProUn>(request, response), oaEosProUn); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProUnList";
	}

	@RequiresPermissions("oa:eos:oaEosProUn:view")
	@RequestMapping(value = "form")
	public String form(OaEosProUn oaEosProUn, Model model) {
		if(oaEosProUn.getPersonLiableUser()==null)
			oaEosProUn.setPersonLiableUser(UserUtils.getUser());
			if(StringUtils.isNotEmpty(oaEosProUn.getUserIds())) {
				List<User> users = UserUtils.getUsersByIds(oaEosProUn.getUserIds());
				oaEosProUn.setUsers_names(UserUtils.getUsersByIds(users));
			}
		List<Role> rols= UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(proAdmin)){//公司领导
				model.addAttribute("proedit",true);
			}
		}
		if(oaEosProUn.getId()!=null) {
			OaEosFlow oeflow=new OaEosFlow();
			oeflow.setEosId(oaEosProUn.getId());
			OaEosFlowItem item=new OaEosFlowItem();
			if(oaEosProUn.getFlow()!=null)
				item.setFlowId(oaEosProUn.getFlow().getId());
			TreeMap<Integer,List<OaEosFlowItem>> map=oaEosFlowItemService.findListByFlowId(item);
			model.addAttribute("flowmap",map);
		}
		model.addAttribute("oaEosProUn", oaEosProUn);
		if(oaEosProUn.getId()!=null) {
			OaEosProStartItem item=new OaEosProStartItem();
			item.setEosId(oaEosProUn.getId());
			model.addAttribute("page",oaEosProStartItemService.findList(item));
		}
		return "modules/oa/eos/oaEosProUnForm";
	}
	@ResponseBody
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = "auditsave",method = RequestMethod.POST)
	public String auditsave(OaEosProUn oaEosPro, Model model) {
		try{
			return oaEosProUnService.saveAudit(oaEosPro);
		}catch (Exception e){
			return "error";
		}
	}
	@RequiresPermissions("oa:eos:oaEosProUn:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProUn oaEosProUn, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProUn)){
			return form(oaEosProUn, model);
		}
		oaEosProUnService.save(oaEosProUn,request);
		addMessage(redirectAttributes, "保存非销售项目立项成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProUn/form?id="+oaEosProUn.getId();
	}
	@RequiresPermissions("oa:eos:oaEosProUn:edit")
	@RequestMapping(value = "cancel")
	public String cancel(OaEosProUn oaEosPro, Model model) {
		oaEosProUnService.Cancel(oaEosPro);
		return form( oaEosPro,  model);
	}
	@RequiresPermissions("oa:eos:oaEosProUn:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProUn oaEosProUn, RedirectAttributes redirectAttributes) {
		oaEosProUnService.delete(oaEosProUn);
		addMessage(redirectAttributes, "删除非销售项目立项成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProUn/?repage";
	}

	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = "flowPage")
	public String flowPage(String id, Model model) {
		model.addAttribute("oaEosProUn", oaEosProUnService.get(id));
		return "modules/oa/eos/oaEosProFlowPageFxs";
	}
}