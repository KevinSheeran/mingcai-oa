/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.crm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.OaFinanceCompany;
import com.mingcai.edu.modules.oa.entity.OaFinanceProduct;
import com.mingcai.edu.modules.oa.entity.crm.OaCrmCustomer;
import com.mingcai.edu.modules.oa.entity.crm.OaCrmProductIng;
import com.mingcai.edu.modules.oa.service.OaFinanceCompanyService;
import com.mingcai.edu.modules.oa.service.OaFinanceProductService;
import com.mingcai.edu.modules.oa.service.crm.OaCrmCustomerService;
import com.mingcai.edu.modules.oa.service.crm.OaCrmProductIngService;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.utils.DictUtils;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import netscape.javascript.JSObject;
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
import com.mingcai.edu.modules.oa.entity.crm.OaCrmProduct;
import com.mingcai.edu.modules.oa.service.crm.OaCrmProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目报备Controller
 * @author 坤
 * @version 2018-02-07
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/crm/oaCrmProduct")
public class OaCrmProductController extends BaseController {

	@Autowired
	private OaCrmProductService oaCrmProductService;
	@Autowired
	private OaCrmCustomerService oaCrmCustomerService;
	@Autowired
	private OaCrmProductIngService oaCrmProductIngService;
	@Autowired
	private OaFinanceProductService oaFinanceProductService;
	@ModelAttribute
	public OaCrmProduct get(@RequestParam(required=false) String id) {
		OaCrmProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaCrmProductService.get(id);
		}
		if (entity == null){
			entity = new OaCrmProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:crm:oaCrmProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaCrmProduct oaCrmProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaCrmProduct.setCreateBy(UserUtils.getUser());
		List<Role> rols=UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(gsAdmin)){//公司领导
				oaCrmProduct.setLeader(true);
			}
		}
		List<OaCrmProduct> page = oaCrmProductService.findList(oaCrmProduct);
		List<OaCrmProduct> openPage = oaCrmProductService.findOpenList(oaCrmProduct);
		model.addAttribute("page", page);
		model.addAttribute("openPage", openPage);
		model.addAttribute("oaCrmProduct", oaCrmProduct);
		return "modules/oa/crm/oaCrmProductList";
	}
	@RequiresPermissions("oa:crm:oaCrmProduct:view")
	@RequestMapping(value = "applyList")
	public String applyList(OaCrmProduct oaCrmProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaCrmProduct.setCreateBy(UserUtils.getUser());
		Page<OaCrmProduct> page = oaCrmProductService.applyList(new Page<OaCrmProduct>(request, response),oaCrmProduct);
		model.addAttribute("page", page);
		model.addAttribute("oaCrmProduct", oaCrmProduct);
		return "modules/oa/crm/oaCrmProductApplyList";
	}
	/***
	 * 公开项目
	 * @param oaCrmProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:crm:oaCrmProduct:edit")
	@RequestMapping(value = "updateClose")
	public String updateClose(OaCrmProduct oaCrmProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmProduct)){
			return form(oaCrmProduct, model);
		}
		oaCrmProduct.setStatus("3");
		oaCrmProductService.save(oaCrmProduct);
		addMessage(redirectAttributes, "状态修改成功");
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmProduct/form?id="+oaCrmProduct.getId();
	}
	/***
	 * 申请跟进项目
	 * @param oaCrmProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:crm:oaCrmProduct:edit")
	@RequestMapping(value = "updateIng")
	public String updateIng(OaCrmProduct oaCrmProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmProduct)){
			return form(oaCrmProduct, model);
		}
		oaCrmProduct.setStatus("4");
		oaCrmProductService.save(oaCrmProduct);
		addMessage(redirectAttributes, "申请成功");
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmProduct/form?id="+oaCrmProduct.getId();
	}
	/***
	 * 申请立项
	 * @param oaCrmProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:crm:oaCrmProduct:edit")
	@RequestMapping(value = "updateSetUp")
	public String updateSetUp(OaCrmProduct oaCrmProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmProduct)){
			return form(oaCrmProduct, model);
		}
		oaCrmProduct.setStatus("5");
		oaCrmProductService.save(oaCrmProduct);
		addMessage(redirectAttributes, "立项申请成功");
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmProduct/form?id="+oaCrmProduct.getId();
	}
	/***
	 * 审核跟进通过
	 * @param oaCrmProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:crm:oaCrmProduct:apply")
	@RequestMapping(value = "updateAdopt")
	public String updateAdopt(OaCrmProduct oaCrmProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmProduct)){
			return form(oaCrmProduct, model);
		}
		if(oaCrmProduct.getStatus().equals("4")){
			oaCrmProduct.setCreateBy(oaCrmProduct.getUpdateBy());
			oaCrmProduct.setStatus("2");
			addMessage(redirectAttributes, "跟进申请通过!");
		}
		oaCrmProductService.save(oaCrmProduct);
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmProduct/applyList";
	}
	/***
	 * 审核立项通过
	 * @param oaCrmProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:crm:oaCrmProduct:apply")
	@RequestMapping(value = "updateAdoptE")
	@ResponseBody
	public String updateAdoptE(OaCrmProduct oaCrmProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmProduct)){
			return form(oaCrmProduct, model);
		}
		if(oaCrmProduct.getStatus().equals("5")){
			oaCrmProduct.setStatus("6");
			addMessage(redirectAttributes, "立项申请通过!");
			OaFinanceProduct oaFinanceProduct=new OaFinanceProduct();
			oaFinanceProduct.setName(oaCrmProduct.getName());
			oaFinanceProduct.setCode(oaCrmProduct.getCode());
			oaFinanceProduct.setCompany(oaCrmProduct.getOaFinanceCompany());
			oaFinanceProduct.setRemarks(oaCrmProduct.getRemarks());
			oaFinanceProductService.save(oaFinanceProduct);
			oaCrmProductService.save(oaCrmProduct);
		}
		JSONObject obj=new JSONObject();
		obj.put("success",true);
		return obj.toString();
	}
	/***
	 * 审核撤回
	 * @param oaCrmProduct
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("oa:crm:oaCrmProduct:apply")
	@RequestMapping(value = "unAdopt")
	public String unAdopt(OaCrmProduct oaCrmProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmProduct)){
			return form(oaCrmProduct, model);
		}
		if(oaCrmProduct.getStatus().equals("4")){
			oaCrmProduct.setStatus("3");
			addMessage(redirectAttributes, "跟进申请撤回!");
		}
		if(oaCrmProduct.getStatus().equals("5")){
			oaCrmProduct.setStatus("2");
			addMessage(redirectAttributes, "立项申请撤回!");
		}
		oaCrmProductService.save(oaCrmProduct);
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmProduct/applyList";
	}
	@RequiresPermissions("oa:crm:oaCrmProduct:view")
	@RequestMapping(value = "form")
	public String form(OaCrmProduct oaCrmProduct, Model model) {
		if(!StringUtils.isNotEmpty(oaCrmProduct.getStatus())){
			oaCrmProduct.setStatus("1");
		}
		List<Role> rols=UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(gsAdmin)){//公司领导
				oaCrmProduct.setLeader(true);
			}
		}
		if(StringUtils.isNotEmpty(oaCrmProduct.getCustomerIds())){
			List<OaCrmCustomer> occs=new ArrayList<OaCrmCustomer>();
			String[] cci=oaCrmProduct.getCustomerIds().split(",");
			for(String cc:cci) {
				OaCrmCustomer occ=(oaCrmCustomerService.get(cc));
				oaCrmProduct.setCustomerNames(occ.getName()+","+oaCrmProduct.getCustomerNames());
				occs.add(occ);
			}
			oaCrmProduct.setCustomerNames(oaCrmProduct.getCustomerNames().substring(0,oaCrmProduct.getCustomerNames().length()-1));
			oaCrmProduct.setOaCrmCustomers(occs);
		}
		if(StringUtils.isNotEmpty(oaCrmProduct.getId())) {
			OaCrmProductIng ing = new OaCrmProductIng();
			ing.setPid(oaCrmProduct.getId());
			List<OaCrmProductIng> page = oaCrmProductIngService.findList(ing);
			model.addAttribute("inglist", page);
		}
		model.addAttribute("oaCrmProduct", oaCrmProduct);
		return "modules/oa/crm/oaCrmProductForm";
	}
	@RequiresPermissions("oa:crm:oaCrmProduct:view")
	@RequestMapping(value = "addform")
	public String addform(OaCrmProduct oaCrmProduct, Model model) {
		model.addAttribute("oaCrmProduct", oaCrmProduct);
		return "modules/oa/crm/oaCrmProductAddForm";
	}

	@RequiresPermissions("oa:crm:oaCrmProduct:view")
	@RequestMapping(value = "applyform")
	public String applyform(OaCrmProduct oaCrmProduct, Model model) {
		if(StringUtils.isNotEmpty(oaCrmProduct.getCustomerIds())){
			List<OaCrmCustomer> occs=new ArrayList<OaCrmCustomer>();
			String[] cci=oaCrmProduct.getCustomerIds().split(",");
			for(String cc:cci) {
				OaCrmCustomer occ=(oaCrmCustomerService.get(cc));
				oaCrmProduct.setCustomerNames(occ.getName()+","+oaCrmProduct.getCustomerNames());
				occs.add(occ);
			}
			oaCrmProduct.setCustomerNames(oaCrmProduct.getCustomerNames().substring(0,oaCrmProduct.getCustomerNames().length()-1));
			oaCrmProduct.setOaCrmCustomers(occs);
		}
		if(StringUtils.isNotEmpty(oaCrmProduct.getId())) {
			OaCrmProductIng ing = new OaCrmProductIng();
			ing.setPid(oaCrmProduct.getId());
			List<OaCrmProductIng> page = oaCrmProductIngService.findList(ing);
			model.addAttribute("inglist", page);
		}
		model.addAttribute("oaCrmProduct", oaCrmProduct);
		return "modules/oa/crm/oaCrmProductFormInfo";
	}
	@RequiresPermissions("oa:crm:oaCrmProduct:edit")
	@RequestMapping(value = "save")
	public String save(OaCrmProduct oaCrmProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaCrmProduct)){
			return form(oaCrmProduct, model);
		}
		oaCrmProductService.save(oaCrmProduct);
		addMessage(redirectAttributes, "保存项目报备成功");
		return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmProduct/?repage";
	}
	
	@RequiresPermissions("oa:crm:oaCrmProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(OaCrmProduct oaCrmProduct, RedirectAttributes redirectAttributes) {
		if(oaCrmProduct.getStatus().equals("1")||oaCrmProduct.getStatus().equals("2")) {
			oaCrmProductService.delete(oaCrmProduct);
			addMessage(redirectAttributes, "删除项目报备成功");
		}else{
			addMessage(redirectAttributes, DictUtils.getDictLabel(oaCrmProduct.getStatus(),"erm_p_status","")+"的项目不可以删除！");
		}
        return "redirect:"+Global.getAdminPath()+"/oa/crm/oaCrmProduct/?repage";
	}

}