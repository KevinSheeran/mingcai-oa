/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.OaProductUsers;
import com.mingcai.edu.modules.sys.entity.Role;
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
import com.mingcai.edu.modules.oa.entity.OaProduct;
import com.mingcai.edu.modules.oa.service.OaProductService;

import java.util.List;

/**
 * 项目信息Controller
 * @author 江坤
 * @version 2017-11-29
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaProduct")
public class OaProductController extends BaseController {

	@Autowired
	private OaProductService oaProductService;
	@Autowired
	private SystemService systemService;
	@ModelAttribute
	public OaProduct get(@RequestParam(required=false) String id) {
		OaProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaProductService.get(id);
		}
		if (entity == null){
			entity = new OaProduct();
			entity.setUpdateBy(UserUtils.getUser());
		}
		entity.setUserId(UserUtils.getUser().getId());
		return entity;
	}
	
	@RequiresPermissions("oa:oaProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProduct oaProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Role> rols=UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(gsAdmin)){//公司领导
				oaProduct.setLeader(true);
			}
		}
		List<OaProduct> page = oaProductService.findList(oaProduct);


		model.addAttribute("page", page);
		return "modules/oa/oaProductList";
	}

	@RequiresPermissions("oa:oaProduct:view")
	@RequestMapping(value = "form")
	public String form(OaProduct oaProduct, Model model) {
		OaProductUsers puser =new OaProductUsers();
		puser.setProductId(oaProduct.getId());
		List<OaProductUsers> pusers=oaProductService.findListByproductId(puser);
		if(pusers.size()>0){
			String sid="";
			String sname="";
			for(OaProductUsers pu:pusers){
				sid+=pu.getUser().getId()+",";
				sname+=pu.getUser().getName()+",";
			}
			if(sid.length()>0){
				oaProduct.setUsers_ids(sid.substring(0,sid.length()-1));
				oaProduct.setUsers_names(sname.substring(0,sname.length()-1));
			}
		}
		model.addAttribute("oaProduct", oaProduct);
		return "modules/oa/oaProductForm";
	}

	@RequiresPermissions("oa:oaProduct:edit")
	@RequestMapping(value = "save")
	public String save(OaProduct oaProduct,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaProduct)){
			return form(oaProduct, model);
		}
		oaProduct.setUsers_ids(request.getParameter("oaProduct.users_ids"));
		oaProductService.save(oaProduct,request);
		addMessage(redirectAttributes, "保存项目信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProduct/?repage";
	}
	
	@RequiresPermissions("oa:oaProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProduct oaProduct, RedirectAttributes redirectAttributes) {
		oaProductService.delete(oaProduct);
		addMessage(redirectAttributes, "删除项目信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProduct/?repage";
	}

}