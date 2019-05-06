/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.crm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.crm.OaCrmProduct;
import com.mingcai.edu.modules.oa.service.crm.OaCrmProductService;
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
import com.mingcai.edu.modules.oa.entity.crm.OaCrmProductIng;
import com.mingcai.edu.modules.oa.service.crm.OaCrmProductIngService;

import java.util.List;

/**
 * 项目报备Controller
 * @author 坤
 * @version 2018-02-07
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/crm/oaCrmProductIng")
public class OaCrmProductIngController extends BaseController {

	@Autowired
	private OaCrmProductIngService oaCrmProductIngService;
	@ModelAttribute
	public OaCrmProductIng get(@RequestParam(required=false) String id) {
		OaCrmProductIng entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaCrmProductIngService.get(id);
		}
		if (entity == null){
			entity = new OaCrmProductIng();
		}
		if(!StringUtils.isNotEmpty(entity.getType())){
			entity.setType("1");
		}
		return entity;
	}
	
	@RequiresPermissions("oa:crm:oaCrmProductIng:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaCrmProductIng oaCrmProductIng, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<OaCrmProductIng> page = oaCrmProductIngService.findList(oaCrmProductIng);
		model.addAttribute("page", page);
		return "modules/oa/crm/oaCrmProductIngList";
	}

	@RequiresPermissions("oa:crm:oaCrmProductIng:view")
	@RequestMapping(value = "form")
	public String form(OaCrmProductIng oaCrmProductIng,HttpServletRequest request, Model model) {
		oaCrmProductIng.setPid(request.getParameter("pid"));
		model.addAttribute("oaCrmProductIng", oaCrmProductIng);
		return "modules/oa/crm/oaCrmProductIngForm";
	}

	@RequiresPermissions("oa:crm:oaCrmProductIng:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(OaCrmProductIng oaCrmProductIng, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmProductIng)){
			return form(oaCrmProductIng,request, model);
		}

		oaCrmProductIngService.save(oaCrmProductIng);
		addMessage(redirectAttributes, "保存项目报备成功");
		JSONObject obj=new JSONObject();
		obj.put("success",true);
		return obj.toString();
	}
	
	@RequiresPermissions("oa:crm:oaCrmProductIng:edit")
	@RequestMapping(value = "delete")
	public String delete(OaCrmProductIng oaCrmProductIng, RedirectAttributes redirectAttributes) {
		oaCrmProductIngService.delete(oaCrmProductIng);
		addMessage(redirectAttributes, "删除项目报备成功");
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmProductIng/?repage";
	}

}