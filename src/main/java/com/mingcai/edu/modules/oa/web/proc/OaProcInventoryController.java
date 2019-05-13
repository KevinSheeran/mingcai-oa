/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.proc;

import com.google.common.collect.Lists;
import com.mingcai.edu.common.beanvalidator.BeanValidators;
import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.excel.ExportExcel;
import com.mingcai.edu.common.utils.excel.ImportExcel;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.modules.oa.entity.proc.OaProcInventory;
import com.mingcai.edu.modules.oa.service.eos.OaEosProStartItemService;
import com.mingcai.edu.modules.oa.service.proc.OaProcInventoryService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	/**
	 * 导入采购清单数据
	 * @param file
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:proc:oaProcInventory:edit")
	@RequestMapping(value = "import", method=RequestMethod.POST)
	public String importFile(MultipartFile file, RedirectAttributes redirectAttributes,HttpServletRequest request){
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:/oa/proc/oaProcInventory/list?repage";
		}
		try {
			int successNum = 0;//记录成功导入的数据条数
			int failureNum = 0;//记录失败时导入的条数
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<OaProcInventory> list = ei.getDataList(OaProcInventory.class);
			for (OaProcInventory oaProc : list){
				oaProc.setApplyId(request.getParameter("applyId"));
				oaProcInventoryService.save(oaProc);
				successNum++;
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条用户，导入信息如下：");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条用户"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入用户失败！失败信息："+e.getMessage());
		}
		return "redirect:"+adminPath+"/oa/proc/oaProcApplylist/form?id="+request.getParameter("applyId");
	}

	/**
	 * 下载导入的采购清单模板
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:proc:oaProcInventory:view")
	@RequestMapping(value="import/template")
	public String importFileTemplate(HttpServletRequest request,HttpServletResponse response, RedirectAttributes redirectAttributes){
		try {
			String fileName = "采购清单信息模板.xlsx";
			List<OaProcInventory> list = Lists.newArrayList();
			list.add(new OaProcInventory());
			new ExportExcel("采购清单列表", OaProcInventory.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+adminPath+"/oa/proc/oaProcApplylist/form?id="+request.getParameter("applyId");
	}


	/**
	 *	查询所有数据
	 * @param oaProcInventory
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:proc:oaProcInventory:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProcInventory oaProcInventory, HttpServletRequest request, HttpServletResponse response, Model model) {
		if (StringUtils.isNotEmpty(request.getParameter("proId"))){
			oaProcInventory.setProId(request.getParameter("proId"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("applyId"))){
			oaProcInventory.setApplyId(request.getParameter("applyId"));
		}
		if (StringUtils.isNotEmpty(request.getParameter("proItemId"))){
			oaProcInventory.setProItemId(request.getParameter("proItemId"));
		}
		/*
		if (StringUtils.isNotEmpty(request.getParameter("id"))){
        }*/
            Page<OaProcInventory> page = oaProcInventoryService.findPage(new Page<OaProcInventory>(request, response), oaProcInventory);
            model.addAttribute("page", page);

            return "modules/oa/proc/oaProcInventoryList";
        }

    /**
     * 添加清单项：采用复选框形式
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequiresPermissions("oa:proc:oaProcInventory:view")
    @RequestMapping(value = "selectlist")
    public String selectlist(HttpServletRequest request, HttpServletResponse response, Model model) {
		OaProcInventory oaProcInventory =new OaProcInventory();
		//默认全查
		oaProcInventory.setFlag(true);
		//判断传参：项目子项Id
		if (StringUtils.isNotEmpty(request.getParameter("applyId"))){
			oaProcInventory.setApplyId(request.getParameter("applyId"));
		}
		List<OaProcInventory> page = oaProcInventoryService.findList(oaProcInventory);
		model.addAttribute("page", page);
		//判断传参：项目id
		if (StringUtils.isNotEmpty(request.getParameter("proId"))){
			oaProcInventory.setProId(request.getParameter("proId"));
		}
		//判断传参：项目子项Id
		if (StringUtils.isNotEmpty(request.getParameter("proItemId"))){
			oaProcInventory.setProItemId(request.getParameter("proItemId"));
		}
		model.addAttribute("oaProcInventory", oaProcInventory);
        //返回新增的oaProcInventoryFormSelectList.jsp页面
        return "modules/oa/proc/oaProcInventoryFormSelectList";
    }


	@RequiresPermissions("oa:proc:oaProcInventory:view")
	@RequestMapping(value = "form")
	public String form(OaProcInventory oaProcInventory,Model model) {
		model.addAttribute("oaProcInventory",oaProcInventory);
		return "modules/oa/proc/oaProcInventoryForm";
	}

    /**
     * 子项目添加清单项
     * @return
     */
	@ResponseBody
    @RequiresPermissions("oa:proc:oaProcInventory:edit")
    @RequestMapping(value = "saveInventory",method = RequestMethod.POST)
    public String saveInventory(HttpServletRequest request){
        return oaProcInventoryService.saveInventory(request);
    }



	/*
	* 保存采购清单信息
	* */
	@ResponseBody
	@RequiresPermissions("oa:proc:oaProcInventory:edit")
	@RequestMapping(value = "save",method = RequestMethod.POST)
	public String save(OaProcInventory oaProcInventory, Model model) {
		if (!beanValidator(model, oaProcInventory)){
			return form(oaProcInventory, model);
		}
		oaProcInventoryService.save(oaProcInventory);
		return "success";
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