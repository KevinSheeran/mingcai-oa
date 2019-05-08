/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlow;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccountLog;
import com.mingcai.edu.modules.oa.entity.user.OaUserSalesVolume;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import com.mingcai.edu.modules.oa.service.eos.OaEosFlowItemService;
import com.mingcai.edu.modules.oa.service.eos.OaEosProService;
import com.mingcai.edu.modules.oa.service.user.OaUserAccountLogService;
import com.mingcai.edu.modules.oa.service.user.OaUserSalesVolumeService;
import com.mingcai.edu.modules.oa.service.wx.OaWxUsersService;
import com.mingcai.edu.modules.sys.entity.Role;
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
import com.mingcai.edu.modules.oa.entity.user.OaUserAccount;
import com.mingcai.edu.modules.oa.service.user.OaUserAccountService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 用户账户Controller
 * @author kun
 * @version 2019-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/user/oaUserAccount")
public class OaUserAccountController extends BaseController {

	@Autowired
	private OaUserAccountService oaUserAccountService;
	@Autowired
	private OaEosFlowItemService oaEosFlowItemService;
	@Autowired
	private OaWxUsersService oaWxUsersService;
	@Autowired
	private OaEosProService oaEosProService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private OaUserAccountLogService oaUserAccountLogService;
	@ModelAttribute
	public OaUserAccount get(@RequestParam(required=false) String id) {
		OaUserAccount entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaUserAccountService.get(id);
		}
		if (entity == null){
			entity = new OaUserAccount();
		}
		return entity;
	}
	@RequiresPermissions("oa:user:oaUserAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaUserAccount oaUserAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaUserAccount> page = oaUserAccountService.findPage(new Page<OaUserAccount>(request, response), oaUserAccount);
		model.addAttribute("page", page);
		return "modules/oa/user/oaUserAccountList";
	}

	@RequestMapping(value = {"userindex"})
	public String userindex(OaUserAccount oaUserAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isEmpty(oaUserAccount.getId())) {
			User user = UserUtils.getUser();
			oaUserAccount = oaUserAccountService.get(user.getId());
		}
		model.addAttribute("oaUserAccount", oaUserAccount);
		OaUserSalesVolume oaUserSalesVolume=new OaUserSalesVolume();
		oaUserSalesVolume.setCreateBy(UserUtils.getUser());
		if(oaUserAccount!=null&&oaUserAccount.getId()!=null) {
			OaEosFlowItem item=new OaEosFlowItem();
			if(oaUserAccount.getFlowId()!=null)
				item.setFlowId(oaUserAccount.getFlowId());
			TreeMap<Integer,List<OaEosFlowItem>> map=oaEosFlowItemService.findListByFlowId(item);
			model.addAttribute("flowmap",map);
		}else{
			oaUserAccount=new OaUserAccount();
		}
		List<Role> rols= UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(gsAdmin)){//项目管理
				oaUserAccount.setLeader(true);
			}
		}
		OaEosPro pro=new OaEosPro();
		pro.setPersonLiableUser(systemService.getUser(oaUserAccount.getId()));
		List<OaEosPro> prolist=oaEosProService.findListByPersonLiableUser(pro);
		model.addAttribute("prolist",prolist);
		OaUserAccountLog accountlog=new OaUserAccountLog();
		accountlog.setUserId(oaUserAccount.getId());
		List<OaUserAccountLog> userlogList=oaUserAccountLogService.findList(accountlog);
		model.addAttribute("userlogList",userlogList);
		return "modules/oa/user/oaUserAccountIndex";
	}
	@RequiresPermissions("oa:user:oaUserAccount:view")
	@RequestMapping(value = {"Salelist"})
	public String Salelist(OaUserAccount oaUserAccount, HttpServletRequest request, HttpServletResponse response, Model model) {
		OaWxUsers oaWxUsers=new OaWxUsers();
		oaWxUsers.setName(request.getParameter("name"));
		oaWxUsers.setDepartmentId(SaleDepartmentId);
		List<OaWxUsers> page = oaWxUsersService.findList(oaWxUsers);
		for(OaWxUsers user:page){
			if(user.getUser()!=null) {
				OaUserAccount account = oaUserAccountService.get(user.getUser().getId());
				user.setAccount(account);
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("oaWxUsers", oaWxUsers);
		return "modules/oa/user/SaleList";
	}
	@RequiresPermissions("oa:user:oaUserAccount:view")
	@RequestMapping(value = {"SalesSave"})
	public String SalesSave(OaUserAccount oaUserAccount, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		OaWxUsers oaWxUsers=new OaWxUsers();
		oaWxUsers.setName(request.getParameter("name"));
		oaWxUsers.setDepartmentId(SaleDepartmentId);

		oaUserAccountService.saveAccount(oaWxUsers,request);
		addMessage(redirectAttributes, "保存用户账户成功");
		model.addAttribute("oaWxUsers", oaWxUsers);
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccount/Salelist";
	}
	@RequiresPermissions("oa:user:oaUserAccount:edit")
	@RequestMapping(value = {"unfrozenAccount"})
	public String unfrozenAccount(OaUserAccount oaUserAccount, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		List<Role> rols= UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(gsAdmin)){//项目管理
				oaUserAccount.setLeader(true);
			}
		}
		if(!oaUserAccount.isLeader()){
			addMessage(redirectAttributes, "失败,您没有操作权限!");
			return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccount/userindex?id="+oaUserAccount.getId();
		}
		oaUserAccount.setFlag(0);
		oaUserAccountService.save(oaUserAccount);
		addMessage(redirectAttributes, "解除账户成功！");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccount/userindex?id="+oaUserAccount.getId();
	}
	@RequiresPermissions("oa:user:oaUserAccount:edit")
	@RequestMapping(value = {"frozenAccount"})
	public String frozenAccount(OaUserAccount oaUserAccount, HttpServletRequest request, HttpServletResponse response, Model model,RedirectAttributes redirectAttributes) {
		List<Role> rols= UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(gsAdmin)){//项目管理
				oaUserAccount.setLeader(true);
			}
		}
		if(!oaUserAccount.isLeader()){
			addMessage(redirectAttributes, "失败,您没有操作权限!");
			return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccount/userindex?id="+oaUserAccount.getId();
		}
		oaUserAccount.setFlag(1);
		oaUserAccountService.save(oaUserAccount);
		addMessage(redirectAttributes, "冻结账户成功！");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccount/userindex?id="+oaUserAccount.getId();
	}
	@RequiresPermissions("oa:user:oaUserAccount:view")
	@RequestMapping(value = "form")
	public String form(OaUserAccount oaUserAccount, Model model) {
		model.addAttribute("oaUserAccount", oaUserAccount);
		return "modules/oa/user/oaUserAccountForm";
	}
	@RequiresPermissions("oa:eos:oaUserAccount:edit")
	@RequestMapping(value = "cancel")
	public String cancel(OaUserAccount oaUserAccount, HttpServletRequest request, HttpServletResponse response, Model model){
		oaUserAccountService.Cancel(oaUserAccount);
		return userindex(oaUserAccount,request,response,model);
	}
	@RequiresPermissions("oa:user:oaUserAccount:edit")
	@RequestMapping(value = "save")
	public String save(OaUserAccount oaUserAccount, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaUserAccount)){
			return form(oaUserAccount, model);
		}
		oaUserAccountService.save(oaUserAccount);
		addMessage(redirectAttributes, "保存用户账户成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccount/?repage";
	}
	
	@RequiresPermissions("oa:user:oaUserAccount:edit")
	@RequestMapping(value = "delete")
	public String delete(OaUserAccount oaUserAccount, RedirectAttributes redirectAttributes) {
		oaUserAccountService.delete(oaUserAccount);
		addMessage(redirectAttributes, "删除用户账户成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserAccount/?repage";
	}

}