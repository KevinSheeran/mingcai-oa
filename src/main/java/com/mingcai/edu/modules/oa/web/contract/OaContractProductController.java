/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.sys.utils.DictUtils;
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
import com.mingcai.edu.modules.oa.entity.contract.OaContractProduct;
import com.mingcai.edu.modules.oa.service.contract.OaContractProductService;

/**
 * 销售产品信息Controller
 * @author 坤
 * @version 2018-01-09
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractProduct")
public class OaContractProductController extends BaseController {

	@Autowired
	private OaContractProductService oaContractProductService;
	
	@ModelAttribute
	public OaContractProduct get(@RequestParam(required=false) String id) {
		OaContractProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractProductService.get(id);
		}
		if (entity == null){
			entity = new OaContractProduct();
			entity.setType("1");
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractProduct oaContractProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractProduct> page = oaContractProductService.findPage(new Page<OaContractProduct>(request, response), oaContractProduct); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractProductList";
	}

	@RequiresPermissions("oa:contract:oaContractProduct:view")
	@RequestMapping(value = "form")
	public String form(OaContractProduct oaContractProduct, Model model) {
		if(oaContractProduct.getType().isEmpty()){
			oaContractProduct.setType("1");

		}
		model.addAttribute("oaContractProduct", oaContractProduct);
		return "modules/oa/contract/oaContractProductForm";
	}
	@RequiresPermissions("oa:contract:oaContractProduct:view")
	@RequestMapping(value = "getInfo")
	@ResponseBody
	public String getInfo(OaContractProduct oaContractProduct) {
		JSONObject obj=new JSONObject();
		obj.put("type", DictUtils.getDictLabel(oaContractProduct.getType(),"pro_type",""));
		obj.put("specifications",oaContractProduct.getSpecifications());
		return obj.toString();
	}

	@RequiresPermissions("oa:contract:oaContractProduct:edit")
	@RequestMapping(value = "save")
	public String save(OaContractProduct oaContractProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaContractProduct)){
			return form(oaContractProduct, model);
		}
		oaContractProductService.save(oaContractProduct);
		addMessage(redirectAttributes, "保存销售产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractProduct/?repage";
	}
	
	@RequiresPermissions("oa:contract:oaContractProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractProduct oaContractProduct, RedirectAttributes redirectAttributes) {
		oaContractProductService.delete(oaContractProduct);
		addMessage(redirectAttributes, "删除销售产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractProduct/?repage";
	}

}