/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
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
import com.mingcai.edu.modules.oa.entity.user.OaUserSalesVolume;
import com.mingcai.edu.modules.oa.service.user.OaUserSalesVolumeService;

/**
 * 申请销售额Controller
 * @author kun
 * @version 2019-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/user/oaUserSalesVolume")
public class OaUserSalesVolumeController extends BaseController {

	@Autowired
	private OaUserSalesVolumeService oaUserSalesVolumeService;
	@Autowired
	private SystemService systemService;
	@ModelAttribute
	public OaUserSalesVolume get(@RequestParam(required=false) String id) {
		OaUserSalesVolume entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaUserSalesVolumeService.get(id);
		}
		if (entity == null){
			entity = new OaUserSalesVolume();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:user:oaUserSalesVolume:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaUserSalesVolume oaUserSalesVolume, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaUserSalesVolume> page = oaUserSalesVolumeService.findPage(new Page<OaUserSalesVolume>(request, response), oaUserSalesVolume); 
		model.addAttribute("page", page);
		return "modules/oa/user/oaUserSalesVolumeList";
	}
	@RequiresPermissions("oa:user:oaUserSalesVolume:view")
	@RequestMapping(value = "form")
	public String form(OaUserSalesVolume oaUserSalesVolume, Model model) {
		oaUserSalesVolume.setUotaRatio(uotaRatio);
		User user=systemService.getUserByLoginName(applyaccount);
		model.addAttribute("user",user);
		model.addAttribute("oaUserSalesVolume", oaUserSalesVolume);
		return "modules/oa/user/oaUserSalesVolumeForm";
	}

	@RequiresPermissions("oa:user:oaUserSalesVolume:edit")
	@RequestMapping(value = "saveAudit")
	public String save(OaUserSalesVolume oaUserSalesVolume, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaUserSalesVolume)){
			return form(oaUserSalesVolume, model);
		}
		User user=systemService.getUserByLoginName(applyaccount);
		oaUserSalesVolume.setUotaRatio(uotaRatio);
		addMessage(redirectAttributes, oaUserSalesVolumeService.save(oaUserSalesVolume,user));
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserSalesVolume/form?id="+oaUserSalesVolume.getId();
	}
	
	@RequiresPermissions("oa:user:oaUserSalesVolume:edit")
	@RequestMapping(value = "delete")
	public String delete(OaUserSalesVolume oaUserSalesVolume, RedirectAttributes redirectAttributes) {
		oaUserSalesVolumeService.delete(oaUserSalesVolume);
		addMessage(redirectAttributes, "删除申请销售额成功");
		return "redirect:"+Global.getAdminPath()+"/oa/user/oaUserSalesVolume/?repage";
	}

}