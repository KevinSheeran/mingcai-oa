/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.crm;

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
import com.mingcai.edu.modules.oa.entity.crm.OaCrmCustomer;
import com.mingcai.edu.modules.oa.service.crm.OaCrmCustomerService;

/**
 * 联系人Controller
 * @author 坤
 * @version 2018-02-07
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/crm/oaCrmCustomer")
public class OaCrmCustomerController extends BaseController {

	@Autowired
	private OaCrmCustomerService oaCrmCustomerService;
	
	@ModelAttribute
	public OaCrmCustomer get(@RequestParam(required=false) String id) {
		OaCrmCustomer entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaCrmCustomerService.get(id);
		}
		if (entity == null){
			entity = new OaCrmCustomer();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:crm:oaCrmCustomer:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaCrmCustomer oaCrmCustomer, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaCrmCustomer> page = oaCrmCustomerService.findPage(new Page<OaCrmCustomer>(request, response), oaCrmCustomer); 
		model.addAttribute("page", page);
		return "modules/oa/crm/oaCrmCustomerList";
	}

	@RequiresPermissions("oa:crm:oaCrmCustomer:view")
	@RequestMapping(value = "form")
	public String form(OaCrmCustomer oaCrmCustomer, Model model) {
		model.addAttribute("oaCrmCustomer", oaCrmCustomer);
		return "modules/oa/crm/oaCrmCustomerForm";
	}

	@RequiresPermissions("oa:crm:oaCrmCustomer:edit")
	@RequestMapping(value = "save")
	public String save(OaCrmCustomer oaCrmCustomer, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmCustomer)){
			return form(oaCrmCustomer, model);
		}
		oaCrmCustomerService.save(oaCrmCustomer);
		addMessage(redirectAttributes, "保存联系人成功");
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmCustomer/?repage";
	}
	
	@RequiresPermissions("oa:crm:oaCrmCustomer:edit")
	@RequestMapping(value = "delete")
	public String delete(OaCrmCustomer oaCrmCustomer, RedirectAttributes redirectAttributes) {
		oaCrmCustomerService.delete(oaCrmCustomer);
		addMessage(redirectAttributes, "删除联系人成功");
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmCustomer/?repage";
	}

}