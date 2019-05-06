/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.common.utils.UploadUtils;
import com.mingcai.edu.modules.oa.entity.OaProductResources;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.OaProductPrice;
import com.mingcai.edu.modules.oa.service.OaProductPriceService;

/**
 * 产品报价版本Controller
 * @author 坤
 * @version 2018-05-07
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaProductPrice")
public class OaProductPriceController extends BaseController {

	@Autowired
	private OaProductPriceService oaProductPriceService;
	
	@ModelAttribute
	public OaProductPrice get(@RequestParam(required=false) String id) {
		OaProductPrice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaProductPriceService.get(id);
		}
		if (entity == null){
			entity = new OaProductPrice();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaProductPrice:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProductPrice oaProductPrice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaProductPrice> page = oaProductPriceService.findPage(new Page<OaProductPrice>(request, response), oaProductPrice); 
		model.addAttribute("page", page);
		return "modules/oa/oaProductPriceList";
	}

	@RequiresPermissions("oa:oaProductPrice:view")
	@RequestMapping(value = "form")
	public String form(OaProductPrice oaProductPrice, Model model) {
		model.addAttribute("oaProductPrice", oaProductPrice);
		return "modules/oa/oaProductPriceForm";
	}

	@RequiresPermissions("oa:oaProductPrice:edit")
	@RequestMapping(value = "save")
	public String save(OaProductPrice oaProductPrice, Model model,HttpServletRequest request,  RedirectAttributes redirectAttributes,@RequestParam(required = false) MultipartFile[] files) {
		if (!beanValidator(model, oaProductPrice)){
			return form(oaProductPrice, model);
		}
		if(files.length>0){
			for(MultipartFile file:files){
				if(file.getSize()>0) {
					System.out.println(file.getSize());
					UploadUtils upload = new UploadUtils();
					request.setAttribute("respath","FILES");
					String[] imgval = upload.uploadFile( request, file);
					if (imgval[0].equals("true")) {
						oaProductPrice.setFilename(imgval[4]);
						oaProductPrice.setPath(imgval[7]);
						oaProductPrice.setFilesize(imgval[6]);
						oaProductPrice.setFormat(imgval[5]);
					}
				}
			}
		}
		oaProductPriceService.save(oaProductPrice);
		addMessage(redirectAttributes, "保存产品报价版本成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProductPrice/?repage";
	}
	
	@RequiresPermissions("oa:oaProductPrice:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProductPrice oaProductPrice, RedirectAttributes redirectAttributes) {
		oaProductPriceService.delete(oaProductPrice);
		addMessage(redirectAttributes, "删除产品报价版本成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProductPrice/?repage";
	}

}