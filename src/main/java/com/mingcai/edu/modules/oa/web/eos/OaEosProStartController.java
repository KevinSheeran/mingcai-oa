/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.service.eos.*;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;

import java.util.List;
import java.util.TreeMap;

/**
 * 立项启动Controller
 * @author 坤
 * @version 2019-03-21
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProStart")
public class OaEosProStartController extends BaseController {

	@Autowired
	private OaEosProStartService oaEosProStartService;
	@Autowired
	private OaEosProStartItemService oaEosProStartItemService;
	@Autowired
	OaEosProCplanService oaEosProCplanService;
	@Autowired
	private OaEosProService oaEosProService;
	@Autowired
	private SystemService systemService;
    @Autowired
    private OaEosFlowItemService oaEosFlowItemService;
	@Autowired
	private OaEosProUnService oaEosProUnService;
	@ModelAttribute
	public OaEosProStart get(@RequestParam(required=false) String id) {
		OaEosProStart entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProStartService.get(id);
		}
		if (entity == null){
			entity = new OaEosProStart();
		}
		entity.setUserId(UserUtils.getUser().getId());
		return entity;
	}
	@RequiresPermissions("oa:eos:oaEosPro:view")
	@RequestMapping(value = {"index"})
	public String index(OaEosProStart oaEosProStart, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Role> rols= UserUtils.getUser().getRoleList();
		OaEosProUn proUn=new OaEosProUn();
		for(Role role:rols){
			if(role.getName().equals(proAdmin)){
				oaEosProStart.setLeader(true);
				proUn.setLeader(true);
			}
		}
		if(StringUtils.isNotEmpty(request.getParameter("status"))){
			oaEosProStart.setStatus(Integer.parseInt(request.getParameter("status")));
		}else{
			oaEosProStart.setStatus(null);
		}
		Page<OaEosProStart> page = oaEosProStartService.findPcList(new Page<OaEosProStart>(request, response), oaEosProStart);
		model.addAttribute("page", page);
		model.addAttribute("proUn", proUn);
		return "modules/oa/eos/oaEosProStartList";
	}
	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaEosProStart oaEosProStart, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaEosProStart> page = oaEosProStartService.findPage(new Page<OaEosProStart>(request, response), oaEosProStart); 
		model.addAttribute("page", page);
		return "modules/oa/eos/oaEosProStartList";
	}

	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = "form")
	public String form(OaEosProStart oaEosProStart, Model model) {
		if(StringUtils.isNotEmpty(oaEosProStart.getUserIds())) {
			List<User> users = UserUtils.getUsersByIds(oaEosProStart.getUserIds());
			oaEosProStart.setUsers_names((UserUtils.getUsersByIds(users)));
		}
		List<Role> rols= UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(proAdmin)){//公司领导
				model.addAttribute("proedit",true);
			}
		}
        if(oaEosProStart.getId()!=null) {
            OaEosFlow oeflow=new OaEosFlow();
            oeflow.setEosId(oaEosProStart.getId());
            OaEosFlowItem item=new OaEosFlowItem();
            if(oaEosProStart.getFlow()!=null)
                item.setFlowId(oaEosProStart.getFlow().getId());
            TreeMap<Integer,List<OaEosFlowItem>> map=oaEosFlowItemService.findListByFlowId(item);
            model.addAttribute("flowmap",map);
        }
		model.addAttribute("oaEosProStart", oaEosProStart);
		OaEosPro pro=oaEosProService.get(oaEosProStart.getId());
		model.addAttribute("oaEosPro", pro);
		OaEosProCplan cplan=new OaEosProCplan();
		cplan.setEosId(oaEosProStart.getId());
		model.addAttribute("planlist", oaEosProCplanService.findList(cplan));
		OaEosProStartItem item=new OaEosProStartItem();
		item.setEosId(pro.getId());
		model.addAttribute("page",oaEosProStartItemService.findList(item));
		return "modules/oa/eos/oaEosProStartForm";
	}
	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = "audit")
	public String audit(OaEosProStart oaEosProStart, Model model) {
		model.addAttribute("oaEosPro", oaEosProStart);
		return "modules/oa/eos/oaEosProStartAudit";
	}
    @RequiresPermissions("oa:eos:oaEosProStart:edit")
    @RequestMapping(value = "cancel")
    public String cancel(OaEosProStart oaEosProStart, Model model) {
        oaEosProStartService.Cancel(oaEosProStart);
        return form( oaEosProStart,  model);
    }
	@RequiresPermissions("oa:eos:oaEosProStart:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProStart oaEosProStart,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProStart)){
			return form(oaEosProStart, model);
		}

		oaEosProStartService.save(oaEosProStart, request);
		addMessage(redirectAttributes, "保存立项启动成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProStart/form?id="+oaEosProStart.getId();
	}
	@ResponseBody
	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = "auditsave",method = RequestMethod.POST)
	public String auditsave(OaEosProStart oaEosProStart, Model model) {
		try{
			return oaEosProStartService.saveAudit(oaEosProStart);
		}catch (Exception e){
			return "error";
		}
	}
	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = "proEnd",method = RequestMethod.GET)
	public String proEnd(OaEosProStart oaEosProStart, Model model,RedirectAttributes redirectAttributes) {
			if("1".equals(oaEosProStart.getSettlement())){
				addMessage(redirectAttributes, "项目结算失败！"+oaEosProStart.getPro().getName()+"已结算");
				return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProStart/index";
			}
		oaEosProStartService.proEnd(oaEosProStart);
		addMessage(redirectAttributes, "项目结算完成");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProStart/index";
	}

	@RequiresPermissions("oa:eos:oaEosProStart:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProStart oaEosProStart, RedirectAttributes redirectAttributes) {
		oaEosProStartService.delete(oaEosProStart);
		addMessage(redirectAttributes, "删除立项启动成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProStart/?repage";
	}
	@RequiresPermissions("oa:eos:oaEosProStart:view")
	@RequestMapping(value = "flowPage")
	public String flowPage(OaEosProStart oaEosProStart, Model model) {
		model.addAttribute("oaEosPro", oaEosProStart);
		return "modules/oa/eos/oaEosProFlowPage";
	}
}