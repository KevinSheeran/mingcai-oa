/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.common.utils.UploadUtils;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplierFiles;
import com.mingcai.edu.modules.oa.service.contract.OaContractSupplierFilesService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.contract.OaContractSupplier;
import com.mingcai.edu.modules.oa.service.contract.OaContractSupplierService;

import java.net.URLEncoder;
import java.util.List;

/**
 * 供应商信息Controller
 * @author 坤
 * @version 2018-01-09
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/contract/oaContractSupplier")
public class OaContractSupplierController extends BaseController {

	@Autowired
	private OaContractSupplierService oaContractSupplierService;
	@Autowired
	private OaContractSupplierFilesService oaContractSupplierFilesService;
	@ModelAttribute
	public OaContractSupplier get(@RequestParam(required=false) String id) {
		OaContractSupplier entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaContractSupplierService.get(id);
		}
		if (entity == null){
			entity = new OaContractSupplier();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:contract:oaContractSupplier:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaContractSupplier oaContractSupplier, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaContractSupplier> page = oaContractSupplierService.findPage(new Page<OaContractSupplier>(request, response), oaContractSupplier); 
		model.addAttribute("page", page);
		return "modules/oa/contract/oaContractSupplierList";
	}

	@RequiresPermissions("oa:contract:oaContractSupplier:view")
	@RequestMapping(value = "form")
	public String form(OaContractSupplier oaContractSupplier, Model model) {
		model.addAttribute("oaContractSupplier", oaContractSupplier);
		OaContractSupplierFiles oaContractSupplierFiles=new OaContractSupplierFiles();
		if(oaContractSupplier.getId()!=null) {
			oaContractSupplierFiles.setParentId(oaContractSupplier.getId());
			List<OaContractSupplierFiles> list=oaContractSupplierFilesService.findList(oaContractSupplierFiles);
			for(OaContractSupplierFiles op:list){
				op.setPath(URLEncoder.encode("/SupplierFiles/"+oaContractSupplier.getId()+"/"+op.getPath()));

			}
			model.addAttribute("list", list);
		}
		return "modules/oa/contract/oaContractSupplierForm";
	}
	@RequiresPermissions("oa:contract:oaContractSupplier:view")
	@RequestMapping(value = "getInfo")
	@ResponseBody
	public String getInfo(OaContractSupplier oaContractSupplier, Model model) {
		model.addAttribute("oaContractSupplier", oaContractSupplier);
		JSONObject obj=new JSONObject();
		obj.put("bankAccount", oaContractSupplier.getBankAccount());
		obj.put("dutyParagraph",oaContractSupplier.getDutyParagraph());
		obj.put("contactNumber",oaContractSupplier.getContactNumber());
		obj.put("openingBank",oaContractSupplier.getOpeningBank());

		return obj.toString();
	}
	@RequiresPermissions("oa:contract:oaContractSupplier:edit")
	@RequestMapping(value = "save")
	public String save(OaContractSupplier oaContractSupplier, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,@RequestParam(required = false) MultipartFile[] files) {
		if (!beanValidator(model, oaContractSupplier)){
			return form(oaContractSupplier, model);
		}
		oaContractSupplierService.save(oaContractSupplier);
		if(files.length>0){
			request.setAttribute("respath","SupplierFiles/"+oaContractSupplier.getId()+"/");
			for(MultipartFile file:files){
				if(file.getSize()>0) {
					System.out.println(file.getSize());
					UploadUtils upload = new UploadUtils();
					OaContractSupplierFiles oaContractSupplierFiles = new OaContractSupplierFiles();
					String[] imgval = upload.uploadFile(request, file);
					oaContractSupplierFiles.setName(imgval[4]);
					oaContractSupplierFiles.setType("2");
					oaContractSupplierFiles.setParentId(oaContractSupplier.getId());
					oaContractSupplierFiles.setPath(imgval[7]);
					oaContractSupplierFiles.setFilesize(imgval[6]);
					oaContractSupplierFiles.setFormat(imgval[5]);
					oaContractSupplierFilesService.save(oaContractSupplierFiles);
				}
			}
		}
		addMessage(redirectAttributes, "保存供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractSupplier/?repage";
	}
	
	@RequiresPermissions("oa:contract:oaContractSupplier:edit")
	@RequestMapping(value = "delete")
	public String delete(OaContractSupplier oaContractSupplier, RedirectAttributes redirectAttributes) {
		oaContractSupplierService.delete(oaContractSupplier);
		addMessage(redirectAttributes, "删除供应商信息成功");
		return "redirect:"+Global.getAdminPath()+"/oa/contract/oaContractSupplier/?repage";
	}

}