/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.sys.entity.User;
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
import com.mingcai.edu.modules.oa.entity.eos.OaEosNumber;
import com.mingcai.edu.modules.oa.service.eos.OaEosNumberService;

/**
 * 项目编号Controller
 * @author 李艺杰
 * @version 2019-04-03
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosNumber")
public class OaEosNumberController extends BaseController {

	@Autowired
	private OaEosNumberService oaEosNumberService;
	
	@ModelAttribute
	public OaEosNumber get(@RequestParam(required=false) String id) {
		OaEosNumber entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosNumberService.get(id);
		}
		if (entity == null){
			entity = new OaEosNumber();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:eos:oaEosNumber:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosNumber oaEosNumber, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosNumber> page = oaEosNumberService.findPage(new Page<OaEosNumber>(request, response), oaEosNumber); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosNumberList";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:view")
	@RequestMapping(value = "form")
	public String form(OaEosNumber oaEosNumber, Model model) {
		model.addAttribute("oaEosNumber", oaEosNumber);
		return "modules/oa/eos/oaEosNumberForm";
	}

	@RequiresPermissions("oa:eos:oaEosNumber:edit")
	@RequestMapping(value = "save")
	public String save(OaEosNumber oaEosNumber, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosNumber)){
			return form(oaEosNumber, model);
		}


		oaEosNumber.setItemNumberType(""+oaEosNumber.getItemNumberType());
		oaEosNumberService.save(oaEosNumber);
		addMessage(redirectAttributes, "保存项目编号成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosNumber/?repage";
	}
	
	@RequiresPermissions("oa:eos:oaEosNumber:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosNumber oaEosNumber, RedirectAttributes redirectAttributes) {
		oaEosNumberService.delete(oaEosNumber);
		addMessage(redirectAttributes, "删除项目编号成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosNumber/?repage";
	}

	/**
	 * 验证手机是否有效
	 * @param itemNumber
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkMobile")
	public String checkMobile(String oldMobile,String itemNumber) {
		System.err.println("+++++++++++++++++++++++++++++++++++++");
		OaEosNumber oaEosNumber=new OaEosNumber();
		oaEosNumber.setItemNumber(itemNumber);
		if (itemNumber !=null && itemNumber.equals(oldMobile)) {
			return "true";
		} else if(itemNumber!=null&&oaEosNumberService.getNumberByMobile(oaEosNumber) == null){
			return "true";
		}
		return "false";
	}

}