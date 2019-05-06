/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mingcai.edu.common.utils.UploadUtils;
import com.mingcai.edu.modules.oa.entity.*;
import com.mingcai.edu.modules.oa.service.OaProductResourcesService;
import com.mingcai.edu.modules.oa.service.OaProductService;
import com.mingcai.edu.modules.sys.entity.Office;
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
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.service.OaProductTaskService;

import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 项目任务Controller
 * @author 江坤
 * @version 2017-11-30
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/oaProductTask")
public class OaProductTaskController extends BaseController {

	@Autowired
	private OaProductTaskService oaProductTaskService;
	@Autowired
	private OaProductService oaProductService;
	@Autowired
	private OaProductResourcesService oaProductResourcesService;
	@ModelAttribute
	public OaProductTask get(@RequestParam(required = false) String id, HttpServletRequest request) {
		OaProductTask entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = oaProductTaskService.get(id);
		}
		if (entity == null) {
			entity = new OaProductTask();
			entity.setProductId(request.getParameter("product_id"));
		}
		return entity;
	}

	@RequiresPermissions("oa:oaProductTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProductTask oaProductTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaProductTask.setProductId(request.getParameter("product_id"));
		OaProductTask thist=new OaProductTask();
		thist.setCreateBy(UserUtils.getUser());
		thist.setProductId(request.getParameter("product_id"));
		thist.setPage(null);
		List<OaProductTask> thisTask=oaProductTaskService.findList(thist);
		model.addAttribute("thisTask", thisTask);
		oaProductTask.setCreateBy(UserUtils.getUser());
		if(oaProductTask.getTaskStatus()!=null){
			oaProductTask.setCreateBy(null);
		}
		Page<OaProductTask> page = oaProductTaskService.findPage(new Page<OaProductTask>(request, response), oaProductTask);
		model.addAttribute("page", page);
		model.addAttribute("oaProductTask", oaProductTask);
		model.addAttribute("user", UserUtils.getUser());
		OaProductUsers puser =new OaProductUsers();
		OaProduct oaProduct=oaProductService.get(oaProductTask.getProductId());
		puser.setProductId(oaProductTask.getProductId());
		List<OaProductUsers> pusers=oaProductService.findListByproductId(puser);
		if(pusers.size()>0){
			String sid="";
			String sname="";
			for(OaProductUsers pu:pusers){
				sid+=pu.getUser().getId()+",";
				sname+=pu.getUser().getName()+",";
			}
			if(sid.length()>0){
				oaProduct.setUsers_ids(sid.substring(0,sid.length()-1));
				oaProduct.setUsers_names(sname.substring(0,sname.length()-1));
			}
			oaProduct.setPusers(pusers);
		}
		model.addAttribute("oaProduct", oaProduct);
		OaProductUsers users=new OaProductUsers();
		users.setProductId(oaProductTask.getProductId());
		model.addAttribute("userCount", oaProductTaskService.countProduct(users));
		model.addAttribute("taskCount", oaProductTaskService.countProduct(oaProductTask.getProductId()));
		return "modules/oa/oaProductTaskList";
	}

	@RequiresPermissions("oa:oaProductTask:view")
	@RequestMapping(value = {"users"})
	public String users(OaProductTask oaProductTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		OaProductUsers puser =new OaProductUsers();
		puser.setProductId(request.getParameter("product_id"));
		List<OaProductUsers> pusers=oaProductService.findListByproductId(puser);
		model.addAttribute("pusers", pusers);
		return "modules/oa/oaProudcitUserList";
	}
	@RequiresPermissions("oa:oaProductTask:view")
	@RequestMapping(value = {"toUser"})
	public String toUser(OaProductTask oaProductTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaProductTask.setTaskUser(UserUtils.getUser());
		Page<OaProductTask> page = oaProductTaskService.findListByUser(new Page<OaProductTask>(request, response), oaProductTask);
		model.addAttribute("page", page);
		model.addAttribute("oaProductTask", oaProductTask);
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("oaProduct", oaProductService.get(oaProductTask.getProductId()));
		OaProductUsers users=new OaProductUsers();
		users.setProductId(oaProductTask.getProductId());
		model.addAttribute("taskCount", oaProductTaskService.countUserTask(UserUtils.getUser().getId()));
		return "modules/oa/oaProductToUserTaskList";
	}
	@RequiresPermissions("oa:oaProductTask:view")
	@RequestMapping(value = "form")
	public String form(OaProductTask oaProductTask, HttpServletRequest request, Model model) {
		if(oaProductTask.getTaskStatus()==null){ // 任务指定默认为未完成
			oaProductTask.setTaskStatus("0");
		}
		model.addAttribute("oaProductTask", oaProductTask);
		model.addAttribute("user", UserUtils.getUser());
		model.addAttribute("taskType", request.getParameter("taskType"));
		model.addAttribute("oaProduct", oaProductService.get(oaProductTask.getProductId()));
		if(oaProductTask.getId()!=null) {
			OaProductResources reso = new OaProductResources();
			reso.setId(oaProductTask.getId());
			List<OaProductResources> resslist = oaProductResourcesService.findList(reso);
			for (OaProductResources res : resslist) {
				if ("1".equals(res.getType())) {
					continue;
				}
				String[] ids = res.getpIds().split(",");
				String relpath = "";
				for (int i = 1; i < ids.length; i++) {
					String sp = ids[i];
					OaProductResources resources = oaProductResourcesService.get(sp);
					relpath += resources.getPath() + "/";
				}
				res.setPathUrl(URLEncoder.encode(relpath + res.getPath()));
			}
			model.addAttribute("list", resslist);
			OaProductTaskLog log=new OaProductTaskLog();
			log.setTaskId(oaProductTask.getId());
			List<OaProductTaskLog> tlogs=oaProductTaskService.findList(log);
			for(OaProductTaskLog logo:tlogs){
				OaProductResources logres=new OaProductResources();
				logres.setId(logo.getId());
				List<OaProductResources> reslist = oaProductResourcesService.findList(logres);
				for (OaProductResources res : reslist) {
					if ("1".equals(res.getType())) {
						continue;
					}
					String[] ids = res.getpIds().split(",");
					String relpath = "";
					for (int i = 1; i < ids.length; i++) {
						String sp = ids[i];
						OaProductResources resources = oaProductResourcesService.get(sp);
						relpath += resources.getPath() + "/";
					}
					res.setPathUrl(URLEncoder.encode(relpath + res.getPath()));
				}
				logo.setReslist(reslist);
			}
			model.addAttribute("loglist", tlogs);

		}


		return "modules/oa/oaProductTaskForm";
	}
	@RequiresPermissions("oa:oaProductTask:view")
	@RequestMapping(value = "actionform")
	public String actionform(OaProductTask oaProductTask, HttpServletRequest request, Model model) {
		model.addAttribute("oaProductTask", oaProductTask);
		return "modules/oa/oaProductTaskActionForm";
	}
	@RequiresPermissions("oa:oaProductTask:edit")
	@ResponseBody
	@RequestMapping(value = "actionSave")
	public String actionSave(OaProductTask oaProductTask, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,@RequestParam(required = false) MultipartFile[] files) {
		oaProductTask.setTaskUser(new User(request.getParameter("taskToUserId")));
		if (!beanValidator(model, oaProductTask)) {
			return form(oaProductTask, request, model);
		}
		oaProductTask.setProductName(oaProductService.get(oaProductTask.getProductId()).getName());
		oaProductTaskService.save(oaProductTask);
		OaProductTaskLog tasklog=new OaProductTaskLog();
		tasklog.setCreateBy(oaProductTask.getCreateBy());
		tasklog.setCreateDate(oaProductTask.getCreateDate());
		tasklog.setProdcutEndDate(oaProductTask.getProdcutEndDate());
		tasklog.setTaskId(oaProductTask.getId());
		tasklog.setTaskStatus(oaProductTask.getTaskStatus());
		tasklog.setTaskToUserId(oaProductTask.getTaskUser().getId());
		tasklog.setTaskType("2");
		tasklog.setRemarks(request.getParameter("desc"));
		oaProductTaskService.insertlog(tasklog);
		//以下判断上传文件
		if(files.length>0){
			OaProductResources prodir=oaProductResourcesService.get(oaProductTask.getProductId());//项目文件是否存在
			OaProductResources taskdir=oaProductResourcesService.get(oaProductTask.getId());//任务文件是否存在
			OaProductResources actiondir=oaProductResourcesService.get(tasklog.getId());//任务节点是否存在
			String path="";
			if(prodir==null){
				prodir=new OaProductResources();
				prodir.setId(oaProductTask.getProductId());
				prodir.setParentId("0");
				prodir.setName(oaProductService.get(oaProductTask.getProductId()).getName());
				prodir.setPath(new Date().getTime()+"");
				prodir.setType("1");
				prodir.setFormat("dir");
				prodir.setCreateBy(UserUtils.getUser());
				prodir.setCreateDate(new Date());
				prodir.setpIds("");
				prodir.setProductId(oaProductTask.getProductId());
				prodir.setDelFlag("0");
				oaProductResourcesService.insert(prodir);
			}
			path+=prodir.getPath()+"/";
			if(taskdir==null){
				taskdir=new OaProductResources();
				taskdir.setId(oaProductTask.getId());
				taskdir.setParentId(prodir.getId());
				taskdir.setName(oaProductTask.getName());
				taskdir.setPath(new Date().getTime()+"");
				taskdir.setType("1");
				taskdir.setFormat("dir");
				taskdir.setCreateBy(UserUtils.getUser());
				taskdir.setCreateDate(new Date());
				taskdir.setpIds(prodir.getpIds()+","+oaProductTask.getProductId());
				taskdir.setProductId(oaProductTask.getProductId());
				taskdir.setDelFlag("0");
				oaProductResourcesService.insert(taskdir);
			}
			path+=taskdir.getPath()+"/";
			if(actiondir==null){
				actiondir=new OaProductResources();
				actiondir.setId(tasklog.getId());
				actiondir.setParentId(taskdir.getId());
				actiondir.setName(oaProductTask.getName()+tasklog.getCode());
				actiondir.setPath(new Date().getTime()+"");
				actiondir.setType("1");
				actiondir.setFormat("dir");
				actiondir.setCreateBy(UserUtils.getUser());
				actiondir.setCreateDate(new Date());
				actiondir.setpIds(prodir.getpIds()+","+oaProductTask.getProductId());
				actiondir.setRemarks(tasklog.getRemarks());
				actiondir.setProductId(oaProductTask.getProductId());
				actiondir.setDelFlag("0");
				oaProductResourcesService.insert(actiondir);
			}
			path+=actiondir.getPath()+"/";
			request.setAttribute("respath",path);
			for(MultipartFile file:files){
				if(file.getSize()>0) {
					System.out.println(file.getSize());
					UploadUtils upload = new UploadUtils();
					OaProductResources oaProductResources = new OaProductResources();
					String[] imgval = upload.uploadFile( request, file);
					if (imgval[0].equals("true")) {
						oaProductResources.setId(null);
						oaProductResources.setName(imgval[4]);
						oaProductResources.setpIds(taskdir.getpIds() + "," + taskdir.getId() + "," + actiondir.getId());
						oaProductResources.setType("2");
						oaProductResources.setRemarks(actiondir.getRemarks());
						oaProductResources.setParentId(actiondir.getId());
						oaProductResources.setProductId(oaProductTask.getProductId());
						oaProductResources.setPath(imgval[7]);
						oaProductResources.setFilesize(imgval[6]);
						oaProductResources.setFormat(imgval[5]);
						oaProductResourcesService.save(oaProductResources);
					}
				}
			}
		}
		JSONObject obj=new JSONObject();
		return obj.toString();
	}
	@RequiresPermissions("oa:oaProductTask:edit")
	@RequestMapping(value = "save")
	public String save(OaProductTask oaProductTask, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes,@RequestParam(required = false) MultipartFile[] files) {
		oaProductTask.setTaskUser(new User(request.getParameter("taskToUserId")));
		if (!beanValidator(model, oaProductTask)) {
			return form(oaProductTask, request, model);
		}
		oaProductTask.setProductName(oaProductService.get(oaProductTask.getProductId()).getName());
		oaProductTaskService.save(oaProductTask);
		//以下判断上传文件
		if(files.length>0){
			OaProductResources prodir=oaProductResourcesService.get(oaProductTask.getProductId());//项目文件是否存在
			OaProductResources taskdir=oaProductResourcesService.get(oaProductTask.getId());//任务文件是否存在
			String path="";
			if(prodir==null){
				prodir=new OaProductResources();
				prodir.setId(oaProductTask.getProductId());
				prodir.setParentId("0");
				prodir.setName(oaProductService.get(oaProductTask.getProductId()).getName());
				prodir.setPath(new Date().getTime()+"");
				prodir.setType("1");
				prodir.setFormat("dir");
				prodir.setCreateBy(UserUtils.getUser());
				prodir.setCreateDate(new Date());
				prodir.setpIds("");
				prodir.setProductId(oaProductTask.getProductId());
				prodir.setDelFlag("0");
				oaProductResourcesService.insert(prodir);
			}
			path+=prodir.getPath()+"/";
			if(taskdir==null){
				taskdir=new OaProductResources();
				taskdir.setId(oaProductTask.getId());
				taskdir.setParentId(prodir.getId());
				taskdir.setName(oaProductTask.getName());
				taskdir.setPath(new Date().getTime()+"");
				taskdir.setType("1");
				taskdir.setFormat("dir");
				taskdir.setCreateBy(UserUtils.getUser());
				taskdir.setCreateDate(new Date());
				taskdir.setpIds(prodir.getpIds()+","+oaProductTask.getProductId());
				taskdir.setProductId(oaProductTask.getProductId());
				taskdir.setDelFlag("0");
				oaProductResourcesService.insert(taskdir);
			}
			path+=taskdir.getPath()+"/";
			request.setAttribute("respath",path);
			for(MultipartFile file:files){
				System.out.println(file.getSize());
				if(file.getSize()>0) {
					UploadUtils upload = new UploadUtils();
					OaProductResources oaProductResources = new OaProductResources();
					String[] imgval = upload.uploadFile( request, file);
					if (imgval[0].equals("true")) {
						oaProductResources.setId(null);
						oaProductResources.setName(imgval[4]);
						oaProductResources.setpIds(taskdir.getpIds() + "," + taskdir.getId());
						oaProductResources.setType("2");
						oaProductResources.setRemarks(oaProductTask.getRemarks());
						oaProductResources.setParentId(taskdir.getId());
						oaProductResources.setProductId(oaProductTask.getProductId());
						oaProductResources.setPath(imgval[7]);
						oaProductResources.setFilesize(imgval[6]);
						oaProductResources.setFormat(imgval[5]);
						oaProductResourcesService.save(oaProductResources);
					}
				}
			}

		}

		addMessage(redirectAttributes, "保存项目任务成功");
		if(StringUtils.isNotEmpty(request.getParameter("taskType")) ){
			return "redirect:" + Global.getAdminPath() + "/oa/oaProductTask/toUser";
		}else{
		return "redirect:" + Global.getAdminPath() + "/oa/oaProductTask/?product_id=" + oaProductTask.getProductId();}
	}

	@RequiresPermissions("oa:oaProductTask:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProductTask oaProductTask, RedirectAttributes redirectAttributes) {
		oaProductTaskService.delete(oaProductTask);
		addMessage(redirectAttributes, "删除项目任务成功");
		return "redirect:" + Global.getAdminPath() + "/oa/oaProductTask/?product_id=" + oaProductTask.getProductId();
	}

	/**
	 * 获取机构JSON数据。
	 *
	 * @param extId    排除的ID
	 * @param type     类型（1：公司；2：部门/小组/其它：3：用户）
	 * @param grade    显示级别
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId, @RequestParam(required = false) String type,
											  @RequestParam(required = false) Long grade, @RequestParam(required = false) String productId, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		OaProductUsers user = new OaProductUsers();
		user.setProductId(productId);
		List<OaProductUsers> list = oaProductTaskService.findListByproductId(user);
		for (int i = 0; i < list.size(); i++) {
			OaProductUsers e = list.get(i);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", e.getUser().getId());
			map.put("name", e.getUser().getName());
			if (type != null && "3".equals(type)) {
				map.put("isParent", true);
			}
			mapList.add(map);
		}
		return mapList;
	}
}