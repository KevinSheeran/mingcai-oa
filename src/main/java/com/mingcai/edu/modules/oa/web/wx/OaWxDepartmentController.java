/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.service.wx.OaWxDepartmentService;

import java.util.List;
import java.util.Map;

/**
 * 微信部门Controller
 * @author kun
 * @version 2019-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/wx/oaWxDepartment")
public class OaWxDepartmentController extends BaseController {

	@Autowired
	private OaWxDepartmentService oaWxDepartmentService;
	
	@ModelAttribute
	public OaWxDepartment get(@RequestParam(required=false) String id) {
		OaWxDepartment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWxDepartmentService.get(id);
		}
		if (entity == null){
			entity = new OaWxDepartment();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:wx:oaWxDepartment:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWxDepartment oaWxDepartment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaWxDepartment> page = oaWxDepartmentService.findPage(new Page<OaWxDepartment>(request, response), oaWxDepartment); 
		model.addAttribute("page", page);
		return "modules/oa/wx/oaWxDepartmentList";
	}

	@RequiresPermissions("oa:wx:oaWxDepartment:view")
	@RequestMapping(value = "form")
	public String form(OaWxDepartment oaWxDepartment, Model model) {
		model.addAttribute("oaWxDepartment", oaWxDepartment);
		return "modules/oa/wx/oaWxDepartmentForm";
	}

	@RequiresPermissions("oa:wx:oaWxDepartment:edit")
	@RequestMapping(value = "save")
	public String save(OaWxDepartment oaWxDepartment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaWxDepartment)){
			return form(oaWxDepartment, model);
		}
		oaWxDepartmentService.save(oaWxDepartment);
		addMessage(redirectAttributes, "保存微信部门成功");
		return "redirect:"+Global.getAdminPath()+"/oa/wx/oaWxDepartment/?repage";
	}
	
	@RequiresPermissions("oa:wx:oaWxDepartment:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWxDepartment oaWxDepartment, RedirectAttributes redirectAttributes) {
		oaWxDepartmentService.delete(oaWxDepartment);
		addMessage(redirectAttributes, "删除微信部门成功");
		return "redirect:"+Global.getAdminPath()+"/oa/wx/oaWxDepartment/?repage";
	}
	/**
	 * 获取机构JSON数据。
	 * @param extId 排除的ID
	 * @param type	类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade 显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("oa:wx:oaWxDepartment:view")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId, @RequestParam(required=false) String type,
											  @RequestParam(required=false) Long grade, @RequestParam(required=false) Boolean isAll, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<OaWxDepartment> list = oaWxDepartmentService.findList(new OaWxDepartment());
		for (int i=0; i<list.size(); i++){
			OaWxDepartment e = list.get(i);
			if ((StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId())))){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentid());
				map.put("name", e.getName());
				map.put("open", true);
				if (type != null && "3".equals(type)){
					map.put("isParent", true);
				}
				mapList.add(map);
			}
		}
		return mapList;
	}
}