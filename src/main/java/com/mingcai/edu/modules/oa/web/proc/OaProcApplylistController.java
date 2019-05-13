/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.proc;

import com.mingcai.edu.common.beanvalidator.BeanValidators;
import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.excel.ImportExcel;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStart;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import com.mingcai.edu.modules.oa.entity.proc.OaProcApplylist;
import com.mingcai.edu.modules.oa.service.eos.OaEosProItemService;
import com.mingcai.edu.modules.oa.service.eos.OaEosProService;
import com.mingcai.edu.modules.oa.service.eos.OaEosProStartItemService;
import com.mingcai.edu.modules.oa.service.eos.OaEosProStartService;
import com.mingcai.edu.modules.oa.service.proc.OaProcApplylistService;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.List;

/**
 * 采购申请单Controller
 * @author 谢一郡
 * @version 2019-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/proc/oaProcApplylist")
public class OaProcApplylistController extends BaseController {

	@Autowired
	private OaProcApplylistService oaProcApplylistService;
	@Autowired
	private OaEosProService oaEosProService;
	@Autowired
	private OaEosProStartItemService oaEosProStartItemService;
	@Autowired
	private OaEosProItemService oaEosProItemService;

	@ModelAttribute
	public OaProcApplylist get(@RequestParam(required=false) String id) {
		OaProcApplylist entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaProcApplylistService.get(id);
		}
		if (entity == null){
			entity = new OaProcApplylist();
			//初始化当前日期
			Date currentDate =new Date();
			entity.setUpdateDate(currentDate);
		}
		return entity;
	}
	
	@RequiresPermissions("oa:proc:oaProcApplylist:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProcApplylist oaProcApplylist, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaProcApplylist> page = oaProcApplylistService.findPage(new Page<OaProcApplylist>(request, response), oaProcApplylist); 
		model.addAttribute("page", page);
		return "modules/oa/proc/oaProcApplylistList";
	}

	/**
	 * 获取项目编号
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:proc:oaProcApplylist:view")
	@RequestMapping(value = "getInfo")
	@ResponseBody
	public String getInfo(String proId,Model model){
		JSONObject obj=new JSONObject();
		obj.put("paNumber",oaEosProService.get(proId).getPaNumber());
		return obj.toString();
	}
	/**
	 * 查询采购申请信息
	 * @param oaProcApplylist
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:proc:oaProcApplylist:view")
	@RequestMapping(value = "form")
	public String form(OaProcApplylist oaProcApplylist,Model model) {
		model.addAttribute("oaProcApplylist", oaProcApplylist);
		//查询所有的预立项和立项项目
		List<OaEosPro> list=oaEosProService.findEosPro(new OaEosPro());
		model.addAttribute("eosProlist", list);
		//查询所有的项目的子项目
		OaEosProStartItem item=new OaEosProStartItem();
		if(StringUtils.isNotEmpty(oaProcApplylist.getProId())){
			item.setEosId(oaProcApplylist.getProId());
		}
		List<OaEosProStartItem> page = oaEosProStartItemService.findList(item);
		model.addAttribute("page",page);
		return "modules/oa/proc/oaProcApplylistForm";
	}

	/**
	 * 导入清单信息
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:proc:oaProcApplylist:edit")
	@RequestMapping(value = "import", method= RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/oa/proc/oaProcApplylist?repage";
		}
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);

		}catch(Exception e){
			addMessage(redirectAttributes,"导入清单信息失败！"+e.getMessage());
		}
		return "redirect:" + adminPath + "/oa/proc/oaProcApplylist/?repage";
	}

	@RequiresPermissions("oa:proc:oaProcApplylist:edit")
	@RequestMapping(value = "save")
	public String save(OaProcApplylist oaProcApplylist, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaProcApplylist)){
			return form(oaProcApplylist, model);
		}
		oaProcApplylistService.save(oaProcApplylist);
		addMessage(redirectAttributes, "保存采购申请单成功！");
		return "redirect:"+Global.getAdminPath()+"/oa/proc/oaProcApplylist/?repage";
	}

	
	@RequiresPermissions("oa:proc:oaProcApplylist:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProcApplylist oaProcApplylist, RedirectAttributes redirectAttributes) {
		oaProcApplylistService.delete(oaProcApplylist);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/oa/proc/oaProcApplylist/?repage";
	}

}