/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.collect.Lists;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.FileUtils;
import com.mingcai.edu.common.utils.UploadUtils;
import com.mingcai.edu.modules.oa.entity.OaProduct;
import com.mingcai.edu.modules.oa.service.OaProductService;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
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
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.OaProductResources;
import com.mingcai.edu.modules.oa.service.OaProductResourcesService;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;

/**
 * 项目资源Controller
 * @author 江坤
 * @version 2017-12-04
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaProductResources")
public class OaProductResourcesController extends BaseController {

	@Autowired
	private OaProductResourcesService oaProductResourcesService;
	@Autowired
	private OaProductService oaProductService;
	@ModelAttribute
	public OaProductResources get(@RequestParam(required=false) String id) {
		OaProductResources entity = null;
		if (StringUtils.isNotEmpty(id)){
			entity = oaProductResourcesService.get(id);
		}
		if (entity == null){
			entity = new OaProductResources();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:oaProductResources:view")
	@RequestMapping(value = {"list", ""})
	public String index(OaProductResources oaProductResources, HttpServletRequest request, HttpServletResponse response, Model model) {
		return "modules/oa/oaProductResources";
	}

	/***
	 * 查询项目资源文件
	 * @param oaProductResources
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:oaProductResources:view")
	@RequestMapping(value = {"search"})
	public String searchlist(OaProductResources oaProductResources, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaProductResources.setName(URLDecoder.decode(request.getParameter("name")));
		List<OaProductResources> list = oaProductResourcesService.findList(oaProductResources);
		for(OaProductResources res:list){
			String[] ids=res.getpIds().split(",");
			String path="";
			String relpath="";
			for(int i=1;i<ids.length;i++){
				String sp=ids[i];
				OaProductResources resources=oaProductResourcesService.get(sp);
				path+=resources.getName()+'/';
				relpath+=resources.getPath()+"/";
			}
			if(Arrays.<String> asList(new UploadUtils().extMap.get(new UploadUtils().img).split(",")).contains(res.getFormat())){
				res.setImage(true);
			}
			res.setPathName(path+res.getName());
			res.setPathUrl(relpath+res.getPath());
		}
		System.out.println(list.toString());
		model.addAttribute("list", list);
		return "modules/oa/oaProductResourcesList1";
	}
	/***
	 * 项目资源文件
	 * @param oaProductResources
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:oaProductResources:view")
	@RequestMapping(value = {"resources"})
	public String list(OaProductResources oaProductResources, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaProductResources.setId(request.getParameter("resid"));
		oaProductResources.setName(request.getParameter("name"));
		List<OaProductResources> list = oaProductResourcesService.findList(oaProductResources);
		for(OaProductResources opr:list){
			if(Arrays.<String> asList(new UploadUtils().extMap.get(new UploadUtils().img).split(",")).contains(opr.getFormat())){
				opr.setImage(true);
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("path", URLDecoder.decode(request.getParameter("path")) );
		model.addAttribute("respath", URLDecoder.decode(request.getParameter("respath")) );
		model.addAttribute("oaProductResources", oaProductResourcesService.get(oaProductResources.getId()));
		return "modules/oa/oaProductResourcesList";
	}
	/***
	 * 项目信息
	 * @param oaProductResources
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("oa:oaProductResources:view")
	@RequestMapping(value = {"product"})
	public String prolist(OaProductResources oaProductResources, HttpServletRequest request, HttpServletResponse response, Model model) {
			OaProduct oaProduct=new OaProduct();
			oaProduct.setUserId(UserUtils.getUser().getId());
			List<Role> rols=UserUtils.getUser().getRoleList();
			for(Role role:rols){
				if(role.getName().equals(gsAdmin)){//公司领导
					oaProduct.setLeader(true);
				}

			}
			List<OaProduct> products=oaProductService.findList(oaProduct);
			model.addAttribute("products", products);

			return "modules/oa/oaProductRList";
	}
	@RequiresPermissions("oa:oaProductResources:view")
	@RequestMapping(value = "form")
	public String form(OaProductResources oaProductResources, Model model) {
		model.addAttribute("oaProductResources", oaProductResources);
		return "modules/oa/oaProductResourcesForm";
	}

	@RequiresPermissions("oa:oaProductResources:edit")
	@RequestMapping(value = "save")
	public String save(OaProductResources oaProductResources, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaProductResources)){
			return form(oaProductResources, model);
		}
		oaProductResourcesService.save(oaProductResources);
		addMessage(redirectAttributes, "保存项目资源成功");
		return "redirect:"+Global.getAdminPath()+"/oa/oaProductResources/?repage";
	}
	
	@RequiresPermissions("oa:oaProductResources:view")
	@ResponseBody
	@RequestMapping(value = "delete")
	public String delete(OaProductResources oaProductResources, RedirectAttributes redirectAttributes) {
		oaProductResources=oaProductResourcesService.get(oaProductResources.getId());
		OaProduct pro=oaProductService.get(oaProductResources.getProductId());
		JSONObject obj=new JSONObject();
		if(oaProductResources.getCreateBy().getId().equals(UserUtils.getUser().getId())||pro.getUpdateBy().getId().equals(UserUtils.getUser().getId())){
			oaProductResourcesService.delete(oaProductResources);
			obj.put("status","success");
			obj.put("msg","文件删除成功");
		}else{
			obj.put("status","error");
			obj.put("msg","没有权限删除");
		}
		return obj.toString();
	}
	/**
	 * 获取项目文件结构JSON数据。
	 *
	 * @param extId    排除的ID
	 * @param grade    显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("oa:oaProductResources:view")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,@RequestParam(required = false) Long grade, @RequestParam(required = false) String productId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		return mapList;
	}
	/**
	 * 获取项目文件结构JSON数据。
	 *
	 * @return
	 */
	@RequiresPermissions("oa:oaProductResources:view")
	@RequestMapping(value = "dir")
	public String dir(OaProductResources oaProductResources, Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("oaProductResources", oaProductResources);
		return "modules/oa/oaProductDir";
	}

	/**
	 * 获取项目文件结构JSON数据。
	 *
	 * @return
	 */
	@RequiresPermissions("oa:oaProductResources:view")
	@RequestMapping(value = "touploader")
	public String touploader(OaProductResources oaProductResources,HttpServletRequest request, Model model) {
		model.addAttribute("respath", request.getParameter("respath"));
		model.addAttribute("userId", request.getParameter("userid"));
		model.addAttribute("parentId", request.getParameter("parentId"));
		model.addAttribute("pIds", request.getParameter("pIds"));
		model.addAttribute("productId", request.getParameter("productId"));
		return "modules/oa/oaProductUploder";
	}
	/**
	 * 获取项目文件结构JSON数据。
	 *
	 * @return
	 */
	@RequiresPermissions("oa:oaProductResources:view")
	@ResponseBody
	@RequestMapping(value = "savedir")
	public String savedir(OaProductResources oaProductResources,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		oaProductResources.setName(request.getParameter("name"));
		oaProductResources.setPath(new Date().getTime()+"");
		oaProductResources.setParentId(request.getParameter("parentId"));
		oaProductResources.setProductId(request.getParameter("productId"));
		oaProductResources.setpIds(request.getParameter("pIds")+","+request.getParameter("parentId"));
		oaProductResources.setType("1");
		oaProductResources.setFormat("dir");
		String respath=request.getParameter("respath");
		oaProductResourcesService.save(oaProductResources);
		FileUtils.updateDirectory("",oaProductResources.getPath(),request,respath);
		return "success";
	}
	/**
	 * 获取项目文件结构JSON数据。
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "uploader")
	public String uploader(OaProductResources oaProductResources,HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,@RequestParam(required = false) MultipartFile file) {
		UploadUtils upload=new UploadUtils();
		String[] imgval=upload.uploadFile(request,file);
		if (imgval[0].equals("true")) {
			oaProductResources.setId(null);
			oaProductResources.setName(imgval[4]);
			oaProductResources.setPath(new Date().getTime()+"");
			oaProductResources.setpIds(request.getParameter("pIds")+","+request.getParameter("parentId"));
			oaProductResources.setType("2");
			oaProductResources.setPath(imgval[7]);
			oaProductResources.setFilesize(imgval[6]);
			oaProductResources.setFormat(imgval[5]);
			oaProductResourcesService.save(oaProductResources);
		}
		return imgval[0];
	}

	/**
	 * 获取项目文件结构JSON数据。
	 *
	 * @return
	 */
	@RequestMapping(value = "download")
	public void download(OaProductResources oaProductResources,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		if (oaProductResources.getPath() != null) {
			String realPath = request.getSession().getServletContext().getRealPath("/uploadFile");
			File file = new File(realPath, oaProductResources.getPath());
			if (file.exists()) {
				response.setHeader("Content-Disposition","attachment;fileName=" +new String(URLDecoder.decode(oaProductResources.getName()).replaceAll(" ","").replaceAll(" ","").getBytes("utf-8"),"ISO-8859-1"));// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}