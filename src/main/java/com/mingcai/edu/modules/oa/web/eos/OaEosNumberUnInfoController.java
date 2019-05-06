/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.eos.OaEosNumberUn;
import com.mingcai.edu.modules.oa.service.eos.OaEosNumberUnService;
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
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumberUnInfo;
import com.mingcai.edu.modules.oa.service.eos.OaEosNumberUnInfoService;

/**
 * 非销售自编号Controller
 * @author 坤
 * @version 2019-04-10
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosNumberUnInfo")
public class OaEosNumberUnInfoController extends BaseController {

	@Autowired
	private OaEosNumberUnInfoService oaEosNumberUnInfoService;
	@Autowired
	private OaEosNumberUnService oaEosNumberUnService;
	@ModelAttribute
	public OaEosNumberUnInfo get(@RequestParam(required=false) String id) {
		OaEosNumberUnInfo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosNumberUnInfoService.get(id);
		}
		if (entity == null){
			entity = new OaEosNumberUnInfo();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosNumber:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosNumberUnInfo oaEosNumberUnInfo, HttpServletRequest request, HttpServletResponse response, Model model) {
		OaEosNumberUn un= oaEosNumberUnService.getChecked();
		model.addAttribute("oaEosNumberUn", un);
		if(un!=null) {
			oaEosNumberUnInfo.setUnNumberId(un.getId());
		}else{
			oaEosNumberUnInfo.setUnNumberId("-1");
		}
		Page<OaEosNumberUnInfo> page = oaEosNumberUnInfoService.findPage(new Page<OaEosNumberUnInfo>(request, response), oaEosNumberUnInfo);
		model.addAttribute("page", page);


		return "modules/oa/eos/oaEosNumberUnInfoList";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:view")
	@RequestMapping(value = "form")
	public String form(OaEosNumberUnInfo oaEosNumberUnInfo, Model model) {
		model.addAttribute("oaEosNumberUnInfo", oaEosNumberUnInfo);
		return "modules/oa/eos/oaEosNumberUnInfoForm";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:edit")
	@RequestMapping(value = "save")
	public String save(OaEosNumberUnInfo oaEosNumberUnInfo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosNumberUnInfo)){
			return form(oaEosNumberUnInfo, model);
		}
		oaEosNumberUnInfoService.save(oaEosNumberUnInfo);
		addMessage(redirectAttributes, "保存非销售自编号成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosNumberUnInfo/?repage";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:edit")
	@RequestMapping(value = "addCode")
	@ResponseBody
	public String addCode(OaEosNumberUnInfo oaEosNumberUnInfo,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if(!StringUtils.isNotEmpty(request.getParameter("nuid"))){
			return "error";
		}
		oaEosNumberUnInfo.setUnNumberId(request.getParameter("nuid"));
		oaEosNumberUnInfoService.save(oaEosNumberUnInfo);
		return "success";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosNumberUnInfo oaEosNumberUnInfo, RedirectAttributes redirectAttributes) {
		oaEosNumberUnInfoService.delete(oaEosNumberUnInfo);
		addMessage(redirectAttributes, "删除非销售自编号成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosNumberUnInfo/?repage";
	}

}