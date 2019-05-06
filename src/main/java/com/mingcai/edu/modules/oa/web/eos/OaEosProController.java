/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Lists;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.service.eos.*;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 销售项目立项Controller
 * @author kun
 * @version 2019-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosPro")
public class OaEosProController extends BaseController {

	@Autowired
	private OaEosProService oaEosProService;
	@Autowired
	private OaEosFlowItemService oaEosFlowItemService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OaEosProItemService oaEosProItemService;

	@Autowired
	private  OaEosProStartItemService oaEosProStartItemService;

	@Autowired
	private OaEosProItemTemplateService oaEosProItemTemplateService;
	@ModelAttribute
	public OaEosPro get(@RequestParam(required=false) String id) {
		OaEosPro entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProService.get(id);
		}
		if (entity == null){
			entity = new OaEosPro();
		}
		entity.setUserId(UserUtils.getUser().getId());
		return entity;
	}
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = {"index"})
	public String index(OaEosPro oaEosPro, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Role> rols= UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(proAdmin)){//项目管理
				oaEosPro.setLeader(true);
				model.addAttribute("procount", oaEosProService.procount(new PageData()));
			}
		}
		if(StringUtils.isNotEmpty(request.getParameter("status"))){
			oaEosPro.setStatus(Integer.parseInt(request.getParameter("status")));
		}else{
			oaEosPro.setStatus(null);
		}
		Page<OaEosPro> page = oaEosProService.findPage(new Page<OaEosPro>(request, response), oaEosPro);
		model.addAttribute("page", page);
		model.addAttribute("oaEosPro", oaEosPro);

		return "modules/oa/eos/oaEosProList";
	}
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosPro oaEosPro, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosPro> page = oaEosProService.findPage(new Page<OaEosPro>(request, response), oaEosPro); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProList";
	}
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = "audit")
	public String audit(OaEosPro oaEosPro, Model model) {

		model.addAttribute("oaEosPro", oaEosPro);
		return "modules/oa/eos/oaEosProAudit";
	}
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = "touser")
	public String touser(OaEosPro oaEosPro, Model model) {
		model.addAttribute("oaEosPro", oaEosPro);
		return "modules/oa/eos/oaEosProTouser";
	}
	@ResponseBody
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = "tousersave",method = RequestMethod.POST)
	public String tousersave(OaEosPro oaEosPro, Model model) {
		try{
			return oaEosProService.saveToUser(oaEosPro);
		}catch (Exception e){
			return "error";
		}
	}
	@ResponseBody
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = "auditsave",method = RequestMethod.POST)
	public String auditsave(OaEosPro oaEosPro, Model model) {
		try{
			return oaEosProService.saveAudit(oaEosPro);

		}catch (Exception e){
			return "error";
		}
	}
	@RequiresPermissions("oa:eos:oaEosPro:edit")
	@RequestMapping(value = "cancel")
	public String cancel(OaEosPro oaEosPro, Model model) {
		oaEosProService.Cancel(oaEosPro);
		return form( oaEosPro,  model);
	}
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = "form")
	public String form(OaEosPro oaEosPro, Model model) {
		if(oaEosPro.getPersonLiableUser()==null)
		oaEosPro.setPersonLiableUser(UserUtils.getUser());
		if(StringUtils.isNotEmpty(oaEosPro.getUserIds())) {
			List<User> users = UserUtils.getUsersByIds(oaEosPro.getUserIds());
			oaEosPro.setUsers_names((UserUtils.getUsersByIds(users)));
		}
		List<Role> rols= UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(proAdmin)){//公司领导
				model.addAttribute("proedit",true);
			}
		}
		if(oaEosPro.getId()!=null) {
			OaEosFlow oeflow=new OaEosFlow();
			oeflow.setEosId(oaEosPro.getId());
			OaEosFlowItem item=new OaEosFlowItem();
			if(oaEosPro.getFlow()!=null)
			item.setFlowId(oaEosPro.getFlow().getId());
			TreeMap<Integer,List<OaEosFlowItem>> map=oaEosFlowItemService.findListByFlowId(item);
			model.addAttribute("flowmap",map);
		}
		model.addAttribute("oaEosPro", oaEosPro);
		if(oaEosPro.getId()!=null) {
			OaEosProItem item = new OaEosProItem();
			item.setEosId(oaEosPro.getId());
			List<OaEosProItem> page = oaEosProItemService.findList(item);
			model.addAttribute("page", page);
		}
		return "modules/oa/eos/oaEosProForm";
	}

	@RequestMapping(value = "save")
	public String save(OaEosPro oaEosPro,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosPro)){
			return form(oaEosPro, model);
		}
		oaEosProService.save(oaEosPro,request);
		addMessage(redirectAttributes, "保存销售项目立项成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosPro/form?id="+oaEosPro.getId();
	}
	
	@RequestMapping(value = "delete")
	public String delete(OaEosPro oaEosPro, RedirectAttributes redirectAttributes) {
		oaEosProService.delete(oaEosPro);
		addMessage(redirectAttributes, "删除销售项目立项成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosPro/index?repage";
	}

	@RequiresPermissions("oa:eos:oaEosPro:view")
	@ResponseBody
	@RequestMapping(value = "itemThmplate")
	public List<PageData> treeData(@RequestParam(required = false) String extId,String id, @RequestParam(required = false) Long grade, @RequestParam(required = false) String productId, HttpServletResponse response) {
		List<PageData> list = oaEosProItemTemplateService.findMap(new PageData());
		return list;
	}
	@RequestMapping(value = "tz")
	public String tz() {
		return "modules/oa/eos/oaProItemTemplate";
	}

	@RequiresPermissions("oa:eos:oaEosPro:view")
	@ResponseBody
	@RequestMapping("itemsession")
	public  Object session(String ids,String eosid){
		String[] parts = ids.split(",");
		List<OaEosProItemTemplate> list=new ArrayList<OaEosProItemTemplate>();
		for (String item: parts){
			list.add(oaEosProItemTemplateService.get(item));
		}
		for (OaEosProItemTemplate  item:list){
		OaEosProStartItem oaEosProItem=new OaEosProStartItem();
		oaEosProItem.setName(item.getName());
		oaEosProItem.setEosId(eosid);
			oaEosProStartItemService.save(oaEosProItem);
		}
	return "";
	}

	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = "flowPage")
	public String flowPage(String id, Model model) {
		model.addAttribute("oaEosPro", oaEosProService.get(id));
		return "modules/oa/eos/oaEosProFlowPageYlx";
	}
}