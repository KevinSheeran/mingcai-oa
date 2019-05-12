/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.proc;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.modules.oa.entity.proc.OaProcInventory;
import com.mingcai.edu.modules.oa.service.eos.OaEosProStartItemService;
import com.mingcai.edu.modules.oa.service.proc.OaProcInventoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 采购清单Controller
 * @author 谢一郡
 * @version 2019-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/proc/oaProcInventory")
public class OaProcInventoryController extends BaseController {

	@Autowired
	private OaProcInventoryService oaProcInventoryService;
    @Autowired
    private OaEosProStartItemService oaEosProStartItemService;
	@ModelAttribute
	public OaProcInventory get(@RequestParam(required=false) String id) {
		OaProcInventory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaProcInventoryService.get(id);
		}
		if (entity == null){
			entity = new OaProcInventory();
		}
		return entity;
	}
	/*
	*
	* */
	@RequiresPermissions("oa:proc:oaProcInventory:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProcInventory oaProcInventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (StringUtils.isNotEmpty(request.getParameter("proId"))){
			oaProcInventory.setProId(request.getParameter("proId"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("applyId"))){
			oaProcInventory.setProId(request.getParameter("applyId"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("proItemId"))){
			oaProcInventory.setProItemId(request.getParameter("proItemId"));
		}/*
		if (StringUtils.isNotEmpty(request.getParameter(""))){

		}*/

        Page<OaProcInventory> page = oaProcInventoryService.findPage(new Page<OaProcInventory>(request, response), oaProcInventory);
		model.addAttribute("page", page);
		return "modules/oa/proc/oaProcInventoryList";
	}

	@RequiresPermissions("oa:proc:oaProcInventory:view")
	@RequestMapping(value = "form")
	public String form(OaProcInventory oaProcInventory,Model model) {

		model.addAttribute("oaProcInventory",oaProcInventory);
		return "modules/oa/proc/oaProcInventoryForm";
	}


	/*
	* 保存采购清单信息
	* */
	@RequiresPermissions("oa:proc:oaProcInventory:edit")
	@RequestMapping(value = "save")
	public String save(OaProcInventory oaProcInventory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaProcInventory)){
			return form(oaProcInventory, model);
		}
		oaProcInventoryService.save(oaProcInventory);
		addMessage(redirectAttributes, "采购清单保存成功");
		return "redirect:"+Global.getAdminPath()+"/oa/proc/oaProcInventory/?repage";
	}
	/*
	* 删除采购清单信息
	* */
	@RequiresPermissions("oa:proc:oaProcInventory:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProcInventory oaProcInventory, RedirectAttributes redirectAttributes) {
		oaProcInventoryService.delete(oaProcInventory);
		addMessage(redirectAttributes, "删除采购清单成功");
		return "redirect:"+Global.getAdminPath()+"/oa/proc/oaProcInventory/?repage";
	}

}