/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.OaProduct;
import com.mingcai.edu.modules.oa.entity.OaProductResources;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.activiti.engine.impl.util.json.JSONObject;
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
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierFiles;
import com.mingcai.edu.modules.oa.service.contract.OaContractSupplierFilesService;

/**
 * 供应商产品附件Controller
 * @author 坤
 * @version 2018-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractSupplierFiles")
public class OaContractSupplierFilesController extends BaseController {

	@Autowired
	private OaContractSupplierFilesService oaContractSupplierFilesService;

	
	@ModelAttribute
	public OaContractSupplierFiles get(@RequestParam(required=false) String id) {
		OaContractSupplierFiles entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractSupplierFilesService.get(id);
		}
		if (entity == null){
			entity = new OaContractSupplierFiles();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractSupplierFiles:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractSupplierFiles oaContractSupplierFiles, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractSupplierFiles> page = oaContractSupplierFilesService.findPage(new Page<OaContractSupplierFiles>(request, response), oaContractSupplierFiles); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractSupplierFilesList";
	}

	@RequiresPermissions("oa:contract:oaContractSupplierFiles:view")
	@RequestMapping(value = "form")
	public String form(OaContractSupplierFiles oaContractSupplierFiles, Model model) {
		model.addAttribute("oaContractSupplierFiles", oaContractSupplierFiles);
		return "modules/oa/contract/oaContractSupplierFilesForm";
	}

	@RequiresPermissions("oa:contract:oaContractSupplierFiles:edit")
	@RequestMapping(value = "save")
	public String save(OaContractSupplierFiles oaContractSupplierFiles, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaContractSupplierFiles)){
			return form(oaContractSupplierFiles, model);
		}
		oaContractSupplierFilesService.save(oaContractSupplierFiles);
		addMessage(redirectAttributes, "保存供应商产品附件成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractSupplierFiles/?repage";
	}
	@RequiresPermissions("oa:oaProductResources:view")
	@ResponseBody
	@RequestMapping(value = "deletefiles")
	public String deletefiles(OaContractSupplierFiles oaContractSupplierFiles, RedirectAttributes redirectAttributes) {
		JSONObject obj=new JSONObject();
		oaContractSupplierFilesService.delete(oaContractSupplierFiles);
			obj.put("status","success");
			obj.put("msg","文件删除成功");
		return obj.toString();
	}
	@RequiresPermissions("oa:contract:oaContractSupplierFiles:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractSupplierFiles oaContractSupplierFiles, RedirectAttributes redirectAttributes) {
		oaContractSupplierFilesService.delete(oaContractSupplierFiles);
		addMessage(redirectAttributes, "删除供应商产品附件成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractSupplierFiles/?repage";
	}


}