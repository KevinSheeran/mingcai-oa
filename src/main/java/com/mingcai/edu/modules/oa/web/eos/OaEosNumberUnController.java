/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumberUn;
import com.mingcai.edu.modules.oa.service.eos.OaEosNumberUnService;

/**
 * 非销售编号Controller
 * @author 坤
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosNumberUn")
public class OaEosNumberUnController extends BaseController {

	@Autowired
	private OaEosNumberUnService oaEosNumberUnService;
	
	@ModelAttribute
	public OaEosNumberUn get(@RequestParam(required=false) String id) {
		OaEosNumberUn entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosNumberUnService.get(id);
		}
		if (entity == null){
			entity = new OaEosNumberUn();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosNumber:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosNumberUn oaEosNumberUn, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosNumberUn> page = oaEosNumberUnService.findPage(new Page<OaEosNumberUn>(request, response), oaEosNumberUn); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosNumberUnList";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:view")
	@RequestMapping(value = "form")
	public String form(OaEosNumberUn oaEosNumberUn, Model model) {
		model.addAttribute("oaEosNumberUn", oaEosNumberUn);
		return "modules/oa/eos/oaEosNumberUnForm";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:edit")
	@RequestMapping(value = "save")
	public String save(OaEosNumberUn oaEosNumberUn, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosNumberUn)){
			return form(oaEosNumberUn, model);
		}
		oaEosNumberUnService.save(oaEosNumberUn);
		addMessage(redirectAttributes, "保存非销售编号成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosNumberUn/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosNumber:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosNumberUn oaEosNumberUn, RedirectAttributes redirectAttributes) {
		oaEosNumberUnService.delete(oaEosNumberUn);
		addMessage(redirectAttributes, "删除非销售编号成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosNumberUn/?repage";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:edit")
	@RequestMapping(value = "savesetting")
	@ResponseBody
	public String savesetting(OaEosNumberUn oaEosNumberUn, RedirectAttributes redirectAttributes){
		if(StringUtils.isNotEmpty(oaEosNumberUn.getId())){
				oaEosNumberUnService.updateChecked(oaEosNumberUn);
			return "success";
		}
		return "error";
	}

}