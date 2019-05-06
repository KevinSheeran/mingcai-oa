/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mingcai.edu.common.utils.weixinApi.Token;
import com.mingcai.edu.common.utils.weixinApi.TokenService;
import com.mingcai.edu.common.utils.weixinApi.WxUsers;
import com.mingcai.edu.common.wx.wxApi;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.service.wx.OaWxDepartmentService;
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
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import com.mingcai.edu.modules.oa.service.wx.OaWxUsersService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信用户Controller
 * @author kun
 * @version 2019-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/wx/oaWxUsers")
public class OaWxUsersController extends BaseController {

	@Autowired
	private OaWxUsersService oaWxUsersService;
	@Autowired
	private OaWxDepartmentService oaWxDepartmentService;

	@ModelAttribute
	public OaWxUsers get(@RequestParam(required=false) String id) {
		OaWxUsers entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWxUsersService.get(id);
		}
		if (entity == null){
			entity = new OaWxUsers();
		}
		return entity;
	}
	@RequiresPermissions("oa:wx:oaWxUsers:view")
	@RequestMapping(value = {"index"})
	public String index(OaWxUsers oaWxUsers, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/oa/wx/wxuserIndex";
	}
	@RequiresPermissions("oa:wx:oaWxUsers:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWxUsers oaWxUsers,@RequestParam(required=false) String did, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaWxUsers.setDepartmentId(did);
		Page<OaWxUsers> page = oaWxUsersService.findPage(new Page<OaWxUsers>(request, response), oaWxUsers);
		List<OaWxDepartment> owdlist=oaWxDepartmentService.findList(new OaWxDepartment());
		Map<String,OaWxDepartment> owdmap=new HashMap<String, OaWxDepartment>();
		for(OaWxDepartment owd:owdlist){
			owdmap.put(owd.getId(),owd);
		}
		for(OaWxUsers user:page.getList()){
			String[] deps=user.getDepartmentId().split(",");
			String depnames="";
			for(String depid:deps){
				if(StringUtils.isNotEmpty(depid)){
					depnames+=owdmap.get(depid).getName()+",";
				}
			}
			if(depnames.length()>0){
				depnames=depnames.substring(0,depnames.length()-1);
			}
			user.setDepartmentId(depnames);
		}

		model.addAttribute("page", page);
		return "modules/oa/wx/oaWxUsersList";
	}

	@RequiresPermissions("oa:wx:oaWxUsers:view")
	@RequestMapping(value = "form")
	public String form(OaWxUsers oaWxUsers, Model model) {
		model.addAttribute("oaWxUsers", oaWxUsers);
		return "modules/oa/wx/oaWxUsersForm";
	}
	@RequestMapping(value = "synchronizationUser", method = RequestMethod.POST)
	@ResponseBody
	public String token(HttpServletRequest request) {
		Gson gson = new Gson();
		String res= TokenService.SeendHttp(wxApi.getMessage(wxApi.DepartmentList,""),"GET","");//获取所有部门
		Token reurntoken = gson.fromJson(res, Token.class);
		for(OaWxDepartment owd:reurntoken.getDepartment()){
			logger.info(owd.getName());
			String gsono=TokenService.SeendHttp(wxApi.getMessage(wxApi.DepartmentUserList,"department_id="+owd.getId()+"&fetch_child=0"),"GET","");//获取部门用户列表
			Token reurntokenuser= gson.fromJson(gsono, Token.class);

			List<OaWxUsers> userslist=new ArrayList<OaWxUsers>();
			if(reurntokenuser.getUserlist().size()>0) {
				for (WxUsers owu : reurntokenuser.getUserlist()) {
					String gsonuser = TokenService.SeendHttp(wxApi.getMessage(wxApi.GETUserInfo, "userid=" + owu.getUserid()), "GET","");//获取部门人员详情信息 ，这里可能会出现一个人在多个部门重复数据问题，保存时做过处理
					OaWxUsers gsonuserobj= gson.fromJson(gsonuser, OaWxUsers.class);
					logger.info(gsonuserobj.toString());
					userslist.add(gsonuserobj);
				}
				owd.setUsers(userslist);
			}
		}
		oaWxDepartmentService.save(reurntoken.getDepartment());
		return "success";
	}
	@RequiresPermissions("oa:wx:oaWxUsers:edit")
	@RequestMapping(value = "save")
	public String save(OaWxUsers oaWxUsers, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaWxUsers)){
			return form(oaWxUsers, model);
		}
		oaWxUsersService.save(oaWxUsers);
		addMessage(redirectAttributes, "保存微信用户成功");
		return "redirect:"+Global.getAdminPath()+"/oa/wx/oaWxUsers/?repage";
	}
	
	@RequiresPermissions("oa:wx:oaWxUsers:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWxUsers oaWxUsers, RedirectAttributes redirectAttributes) {
		oaWxUsersService.delete(oaWxUsers);
		addMessage(redirectAttributes, "删除微信用户成功");
		return "redirect:"+Global.getAdminPath()+"/oa/wx/oaWxUsers/?repage";
	}

}