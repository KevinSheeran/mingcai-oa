/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mingcai.edu.modules.oa.entity.crm.OaCrmCustomer;
import com.mingcai.edu.modules.oa.service.crm.OaCrmCustomerService;
import com.mingcai.edu.modules.sys.entity.Area;
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
import com.mingcai.edu.modules.oa.entity.OaFinanceCompany;
import com.mingcai.edu.modules.oa.service.OaFinanceCompanyService;

import java.util.List;
import java.util.Map;

/**
 * 报销单位管理Controller
 * @author 江坤
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaFinanceCompany")
public class OaFinanceCompanyController extends BaseController {

	@Autowired
	private OaFinanceCompanyService oaFinanceCompanyService;
	@Autowired
	private OaCrmCustomerService oaCrmCustomerService;
	@ModelAttribute
	public OaFinanceCompany get(@RequestParam(required=false) String id) {
		OaFinanceCompany entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaFinanceCompanyService.get(id);
		}
		if (entity == null){
			entity = new OaFinanceCompany();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaFinanceCompany:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaFinanceCompany oaFinanceCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaFinanceCompany> page = oaFinanceCompanyService.findPage(new Page<OaFinanceCompany>(request, response), oaFinanceCompany); 
		model.addAttribute("page", page);
		return "modules/oa/oaFinanceCompanyList";
	}

	@RequiresPermissions("oa:oaFinanceCompany:view")
	@RequestMapping(value = "form")
	public String form(OaFinanceCompany oaFinanceCompany, Model model) {
		model.addAttribute("oaFinanceCompany", oaFinanceCompany);
		return "modules/oa/oaFinanceCompanyForm";
	}

	@RequiresPermissions("oa:oaFinanceCompany:edit")
	@RequestMapping(value = "save")
	public String save(OaFinanceCompany oaFinanceCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaFinanceCompany)){
			return form(oaFinanceCompany, model);
		}
		oaFinanceCompanyService.save(oaFinanceCompany);
		addMessage(redirectAttributes, "保存单位管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaFinanceCompany/?repage";
	}
	
	@RequiresPermissions("oa:oaFinanceCompany:edit")
	@RequestMapping(value = "delete")
	public String delete(OaFinanceCompany oaFinanceCompany, RedirectAttributes redirectAttributes) {
		oaFinanceCompanyService.delete(oaFinanceCompany);
		addMessage(redirectAttributes, "删除单位管理成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaFinanceCompany/?repage";
	}
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<OaFinanceCompany> list = oaFinanceCompanyService.findList(new OaFinanceCompany());
		for (int i=0; i<list.size(); i++){
			OaFinanceCompany e = list.get(i);
			if (StringUtils.isBlank(extId) || extId!=null && !extId.equals(e.getId())){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", 0);
				map.put("name", e.getName());
				mapList.add(map);
				OaCrmCustomer occ=new OaCrmCustomer();
				occ.setOaFinanceCompany(e);
				List<OaCrmCustomer> occs=oaCrmCustomerService.findList(occ);
				for(OaCrmCustomer oc:occs){
					Map<String, Object> map1 = Maps.newHashMap();
					map1.put("id", oc.getId());
					map1.put("pId", e.getId());
					map1.put("name", oc.getName());
					mapList.add(map1);
				}
			}
		}
		return mapList;
	}
}