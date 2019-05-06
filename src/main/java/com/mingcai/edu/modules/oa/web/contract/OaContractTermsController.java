/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.oa.entity.contract.OaContractProduct;
import com.mingcai.edu.modules.oa.entity.contract.OaContractPurchaseTerms;
import com.mingcai.edu.modules.oa.service.contract.OaContractInfoService;
import com.mingcai.edu.modules.oa.service.contract.OaContractProductService;
import com.mingcai.edu.modules.oa.service.contract.OaContractPurchaseTermsService;
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
import com.mingcai.edu.modules.oa.entity.contract.OaContractTerms;
import com.mingcai.edu.modules.oa.service.contract.OaContractTermsService;

import java.util.List;

/**
 * 合同项Controller
 * @author 坤
 * @version 2018-01-16
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractTerms")
public class OaContractTermsController extends BaseController {

	@Autowired
	private OaContractTermsService oaContractTermsService;
	@Autowired
	private OaContractPurchaseTermsService oaContractPurchaseTermsService;
	@Autowired
	private OaContractInfoService oaContractInfoService;
	@ModelAttribute
	public OaContractTerms get(@RequestParam(required=false) String id) {
		OaContractTerms entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractTermsService.get(id);
		}
		if (entity == null){
			entity = new OaContractTerms();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractTerms:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractTerms oaContractTerms, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaContractTerms.setContractId(request.getParameter("contractId"));

		Page<OaContractTerms> page = oaContractTermsService.findPage(new Page<OaContractTerms>(request, response), oaContractTerms);
		for(OaContractTerms trem:page.getList()){
			List<OaContractPurchaseTerms> terms=oaContractPurchaseTermsService.getTremList(trem.getId());
			for(OaContractPurchaseTerms pterms:terms){
				trem.setSumPrice(trem.getSumPrice()+pterms.getUnitPrice()*pterms.getNumber());
				trem.setDivnumber(pterms.getNumber()+trem.getDivnumber());
			}
			trem.setTermsList(terms);
		}
		model.addAttribute("oaContractTerms",oaContractTerms);
		OaContractPurchaseTerms pterms=new OaContractPurchaseTerms();
		pterms.setFinProductId(oaContractInfoService.get(request.getParameter("contractId")).getFinProductId());
		pterms=oaContractPurchaseTermsService.sumMoney(pterms);
		model.addAttribute("purchase_total_money", pterms!=null?pterms.getMoney():"0");
		oaContractTerms=oaContractTermsService.sumPrice(oaContractTerms);
		model.addAttribute("trem_total_money", oaContractTerms!=null?oaContractTerms.getSumPrice():"0");
		String pageNo=request.getParameter("pageNo");
		if(!StringUtils.isNotEmpty(pageNo)){
			pageNo="1";
		}
		page.setPageNo(Integer.parseInt(pageNo));
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractTermsList";
	}

	@RequiresPermissions("oa:contract:oaContractTerms:view")
	@RequestMapping(value = "form")
	public String form(OaContractTerms oaContractTerms, Model model,HttpServletRequest request) {
		OaContractTerms newoaContractTerms=oaContractTermsService.getLast(oaContractTerms);
		if (!StringUtils.isNotBlank(oaContractTerms.getId())){
			if(newoaContractTerms==null){
				oaContractTerms.setCode("TREM_1");
				oaContractTerms.setIndexs(1);
			}else{
				oaContractTerms.setCode("TREM_"+(newoaContractTerms.getIndexs()+1));
				oaContractTerms.setIndexs(newoaContractTerms.getIndexs()+1);
			}
		}
		oaContractTerms.setContractId(request.getParameter("contractId"));
		model.addAttribute("oaContractTerms", oaContractTerms);
		return "modules/oa/contract/oaContractTermsForm";
	}

	@RequiresPermissions("oa:contract:oaContractTerms:edit")
	@RequestMapping(value = "save")
	@ResponseBody
	public String save(OaContractTerms oaContractTerms, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request) {
		oaContractTermsService.save(oaContractTerms);
		addMessage(redirectAttributes, "保存合同项成功");
		JSONObject obj=new JSONObject();
		obj.put("id",oaContractTermsService.getLast(oaContractTerms).getId());
		obj.put("code",oaContractTerms.getCode());
		obj.put("name",oaContractTerms.getName());
		obj.put("contractId",oaContractTerms.getContractId());
		obj.put("unit",oaContractTerms.getUnit());
		obj.put("type", DictUtils.getDictLabel(oaContractTerms.getType(),"pro_type",""));
		obj.put("number",oaContractTerms.getNumber());
		obj.put("specifications",oaContractTerms.getSpecifications());
		obj.put("unitPrice",oaContractTerms.getUnitPrice());
		obj.put("remarks",oaContractTerms.getRemarks());
		return obj.toString();
	}
	
	@RequiresPermissions("oa:contract:oaContractTerms:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(OaContractTerms oaContractTerms, RedirectAttributes redirectAttributes) {
		oaContractTermsService.delete(oaContractTerms);
		addMessage(redirectAttributes, "删除合同项成功");
		return "success";
	}

}