/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.common.utils.UploadUtils;
import com.mingcai.edu.modules.oa.entity.OaProductResources;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierFiles;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierProductType;
import com.mingcai.edu.modules.oa.service.contract.OaContractSupplierFilesService;
import com.mingcai.edu.modules.oa.service.contract.OaContractSupplierProductTypeService;
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
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierProduct;
import com.mingcai.edu.modules.oa.service.contract.OaContractSupplierProductService;

import java.net.URLEncoder;
import java.util.List;

/**
 * 供应商产品信息Controller
 * @author 坤
 * @version 2018-01-09
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractSupplierProduct")
public class OaContractSupplierProductController extends BaseController {

	@Autowired
	private OaContractSupplierProductService oaContractSupplierProductService;
	@Autowired
	private OaContractSupplierProductTypeService oaContractSupplierProductTypeService;
	@Autowired
	private OaContractSupplierFilesService oaContractSupplierFilesService;

	@ModelAttribute
	public OaContractSupplierProduct get(@RequestParam(required=false) String id) {
		OaContractSupplierProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractSupplierProductService.get(id);
		}
		if (entity == null){
			entity = new OaContractSupplierProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractSupplierProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractSupplierProduct oaContractSupplierProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractSupplierProduct> page = oaContractSupplierProductService.findPage(new Page<OaContractSupplierProduct>(request, response), oaContractSupplierProduct); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractSupplierProductList";
	}

	@RequiresPermissions("oa:contract:oaContractSupplierProduct:view")
	@RequestMapping(value = "form")
	public String form(OaContractSupplierProduct oaContractSupplierProduct, Model model) {
		List<OaContractSupplierProductType> types=oaContractSupplierProductTypeService.findList(new OaContractSupplierProductType());
		model.addAttribute("oaContractSupplierProduct", oaContractSupplierProduct);
		model.addAttribute("types", types);
		OaContractSupplierFiles oaContractSupplierFiles=new OaContractSupplierFiles();
		if(oaContractSupplierProduct.getId()!=null) {
			oaContractSupplierFiles.setParentId(oaContractSupplierProduct.getId());
			List<OaContractSupplierFiles> list=oaContractSupplierFilesService.findList(oaContractSupplierFiles);
			for(OaContractSupplierFiles op:list){
				op.setPath(URLEncoder.encode("/SupplierFiles/"+oaContractSupplierProduct.getId()+"/"+op.getPath()));

			}
			model.addAttribute("list", list);
		}
		return "modules/oa/contract/oaContractSupplierProductForm";
	}

	@RequiresPermissions("oa:contract:oaContractSupplierProduct:edit")
	@RequestMapping(value = "save")
	public String save(OaContractSupplierProduct oaContractSupplierProduct,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,@RequestParam(required = false) MultipartFile[] files) {
		if (!beanValidator(model, oaContractSupplierProduct)){
			return form(oaContractSupplierProduct, model);
		}
		oaContractSupplierProductService.save(oaContractSupplierProduct);
		if(files.length>0){
			request.setAttribute("respath","SupplierFiles/"+oaContractSupplierProduct.getId()+"/");
			for(MultipartFile file:files){
				if(file.getSize()>0) {
					System.out.println(file.getSize());
					UploadUtils upload = new UploadUtils();
					OaContractSupplierFiles oaContractSupplierFiles = new OaContractSupplierFiles();
					String[] imgval = upload.uploadFile(request, file);
					oaContractSupplierFiles.setName(imgval[4]);
					oaContractSupplierFiles.setType("2");
					oaContractSupplierFiles.setParentId(oaContractSupplierProduct.getId());
					oaContractSupplierFiles.setPath(imgval[7]);
					oaContractSupplierFiles.setFilesize(imgval[6]);
					oaContractSupplierFiles.setFormat(imgval[5]);
					oaContractSupplierFilesService.save(oaContractSupplierFiles);
				}
			}
		}
		addMessage(redirectAttributes, "保存供应商产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractSupplierProduct/?repage";
	}
	
	@RequiresPermissions("oa:contract:oaContractSupplierProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractSupplierProduct oaContractSupplierProduct, RedirectAttributes redirectAttributes) {
		oaContractSupplierProductService.delete(oaContractSupplierProduct);
		addMessage(redirectAttributes, "删除供应商产品信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractSupplierProduct/?repage";
	}

}