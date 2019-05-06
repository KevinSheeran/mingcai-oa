/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.cms.web;

import com.google.gson.Gson;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.weixinApi.GlobalService;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.modules.cms.entity.Site;
import com.mingcai.edu.modules.cms.utils.CmsUtils;
import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccount;
import com.mingcai.edu.modules.oa.service.eos.*;
import com.mingcai.edu.modules.oa.service.user.OaUserAccountService;
import com.mingcai.edu.modules.oa.service.wx.OaWxDepartmentService;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
import com.mingcai.edu.modules.sys.utils.DictUtils;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.activiti.engine.impl.util.json.JSONObject;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

/**
 * 测试Controller
 * @author ThinkGem
 * @version 2014-02-28
 */
@Controller
@RequestMapping(value = "${OaPath}/weixin")
public class WeixinController extends BaseController {
	@Autowired
	private OaWxDepartmentService oaWxDepartmentService;
	@Autowired
	private OaEosProService oaEosProService;
	@Autowired
	private OaEosProItemService oaEosProItemService;
	@Autowired
	private OaEosProStartItemService oaEosProStartItemService;
	@Autowired
	private OaEosProStartService oaEosProStartService;
    @Autowired
    private OaEosFlowItemService oaEosFlowItemService;
	@Autowired
	private SystemService systemService;
	@Autowired
	OaEosProCplanService oaEosProCplanService;
	@Autowired
	private OaEosProPresentationService oaEosProPresentationService;
	@Autowired
	private OaEosProUnService oaEosProUnService;
	@Autowired
	private OaEosProTimetotalService oaEosProTimetotalService;
	@Autowired
	private OaEosProTimetotalItemService oaEosProTimetotalItemService;
	@Autowired
	private OaUserAccountService oaUserAccountService;

	/**
	 * 网站首页
	 */
	@RequestMapping
	public String index(@RequestParam(required=false) String code,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		model.addAttribute("code",code);
		model.addAttribute("pd",oaEosProService.index(new PageData()));
		model.addAttribute("proAdmin",false);
		model.addAttribute("financeRole",false);
		model.addAttribute("financeAuditRole",false);
		model.addAttribute("summaryview",false);
		List<Role> rols= UserUtils.getUser().getRoleList();
		for(Role role:rols){
			if(role.getName().equals(financeRole)){//财务
				model.addAttribute("financeRole",true);
			}
			if(role.getName().equals(financeAuditRole)){//财务审批
				model.addAttribute("financeAuditRole",true);
			}
			if(role.getName().equals(proAdmin)){//项目管理
				model.addAttribute("proAdmin",true);
			}
			if(role.getName().equals(summaryview)){//工时汇总
				model.addAttribute("summaryview",true);
			}
			if(role.getName().equals(lookweek)){//查看周报
				model.addAttribute("lookweek",true);
			}
		}
		return "modules/cms/front/themes/" + site.getTheme() + "/frontIndex";
	}
	/**
		 * 审批首页
	 */
	@ResponseBody
	@RequestMapping(value = "auditProList", method = RequestMethod.GET)
	public String auditProList(User user,@RequestParam(required=false) String code,Model model) {
		Gson gson=new Gson();
		OaEosPro pro=new OaEosPro();
		pro.setUserId(user.getId());
		Page page=new Page<OaEosPro>();
		Page<OaEosPro> list=oaEosProService.findPage(page,pro);
		return gson.toJson(list);
	}
    /**
     * 项目预立list
     */
    @RequestMapping(value = "proauditList")
    public String auditList(Model model) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		OaEosPro oepro=new OaEosPro();
		oepro.setUserId(user.getId());
		List<OaEosPro> oeplist=oaEosProService.findListByUser(oepro);
		model.addAttribute("list", oeplist);
		model.addAttribute("user",user);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_list";
    }

	/***
	 * 项目周报
	 * @return
	 */
	@RequestMapping(value = "presentationList")
    public String PresentationList(Integer index,Model model){
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);

        String week="";
        if(index==null||index==0) {
            index=0;
            week="本周:";
        }
        List<Date> datas=DateUtils.getDataWeek(index);
        Date date1=datas.get(0);
        Date date2=datas.get(datas.size()-1);
        JSONObject obj=new JSONObject();
        String date=DateUtils.formatDate(date1,"yyyy.M.d")+"-"+DateUtils.formatDate(date2,"yyyy.M.d");
        OaEosProPresentation pres=new OaEosProPresentation();
        pres.setStartEnd(date);
        List<OaEosProPresentation> oepp=oaEosProPresentationService.getListAllByDate(pres);
        model.addAttribute("list", oepp);
        model.addAttribute("date", week+date);
        model.addAttribute("index", index);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/week_list";
	}
    /***
     * 周报详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "user_weekInfo")
    public String PresentationInfo(String id,Model model){
        Site site = CmsUtils.getSite(Site.defaultSiteId());
        site.setTheme("weixin");
        model.addAttribute("site", site);
        OaEosProPresentation oepp=oaEosProPresentationService.get(id);
        model.addAttribute("oaEosProPresentation",oepp);
        return "modules/cms/front/themes/" + site.getTheme() + "/audit/week_info";
    }
	@ResponseBody
	@RequestMapping(value = "presentationListJson")
	public JSONObject PresentationListJson(String index,Model model){
		List<Date> datas=DateUtils.getDataWeek(0);
		Date date1=datas.get(0);
		Date date2=datas.get(datas.size()-1);
		JSONObject obj=new JSONObject();
		String date=DateUtils.formatDate(date1,"yyyy.M.dd")+"-"+DateUtils.formatDate(date2,"yyyy.M.dd");
		OaEosProPresentation pres=new OaEosProPresentation();
		pres.setStartEnd(date);
        List<OaEosProPresentation> oepp=oaEosProPresentationService.getListAllByDate(pres);
        obj.put("list",oepp);
		return obj;
	}

	@Test
	public void test(){
		List<Date> datas=DateUtils.getDataWeek(0);
		Date date1=datas.get(0);
		Date date2=datas.get(datas.size()-1);
		System.out.println(DateUtils.formatDate(date1,"yyyy.M.dd")+"-"+DateUtils.formatDate(date2,"yyyy.M.dd"));
	}

	/**
	 * 项目已处理审核list
	 */
	@RequestMapping(value = "proauditfinishList")
	public String auditfinishList(Model model, HttpServletRequest request, HttpServletResponse response) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		model.addAttribute("user",user);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_finish_list";
	}
	@ResponseBody
	@RequestMapping(value = "auditFinishListJson")
	public Object auditFinishListJson(HttpServletRequest request, HttpServletResponse response){
		Gson gson=new Gson();
		User user=UserUtils.getUser();
		OaEosPro oepro=new OaEosPro();
		oepro.setUserId(user.getId());
		Page<OaEosPro> oeplist=oaEosProService.findListByFlowFinishUser(new Page<OaEosPro>(request, response), oepro);
		for(OaEosPro pro:oeplist.getList()){
			pro.str_status=DictUtils.getDictLabel(pro.getStatus().toString(),"oa_eos_pro_status",null);
			pro.apple_date=DateUtils.formatDate(pro.getFlow().getCreateDate());
		}
		return oeplist;
	}
	/***
	 * 预立项详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "auditpro", method = RequestMethod.GET)
	public String auditpro(@RequestParam(required=false) String id,Model model) {
		User user=UserUtils.getUser();
        //public String auditpro(@RequestParam(required=false) String id,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		OaEosPro oepro=oaEosProService.get(id);
		oepro.setUsers(UserUtils.getUsersByIds(oepro.getUserIds()));
		model.addAttribute("oepro",oepro);
		OaEosFlowItem item = new OaEosFlowItem();
		if(oepro.getFlow()!=null)
		item.setFlowId(oepro.getFlow().getId());
		TreeMap<Integer, List<OaEosFlowItem>> map = oaEosFlowItemService.findListByFlowId(item);
		model.addAttribute("flowmap", map);
        model.addAttribute("flow", new OaEosFlows());
        if (user != null) {
            List<OaEosFlowItem> oeflsit = oaEosFlowItemService.FlowUsers(map);//找到要处理的流程
            for (OaEosFlowItem oflow : oeflsit) {
                if (oflow.getUser().getId().equals(user.getId())) {
                    model.addAttribute("flow", oflow);
                }
            }
        }
        OaEosProItem proitem=new OaEosProItem();
		proitem.setEosId(id);
		List<OaEosProItem> itmelist=oaEosProItemService.findList(proitem);
		model.addAttribute("itmelist", itmelist);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_pro";
	}

	/**
	 * 项目预立list
	 */
	@RequestMapping(value = "proauditstartList")
	public String auditstartList(Model model) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		OaEosProStart oepro=new OaEosProStart();
		oepro.setUserId(user.getId());
		List<OaEosProStart> oeplist=oaEosProStartService.findListByUser(oepro);
		model.addAttribute("list", oeplist);
		model.addAttribute("user",user);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_start_list";
	}
	/**
	 * 项目已处理审核list
	 */
	@RequestMapping(value = "proauditstartfinishList")
	public String auditstartfinishList(Model model, HttpServletRequest request, HttpServletResponse response) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		OaEosProStart oepro=new OaEosProStart();
		oepro.setUserId(user.getId());
		Page<OaEosProStart> oeplist=oaEosProStartService.findListByFlowFinishUser(new Page<OaEosProStart>(request, response), oepro);
		model.addAttribute("list", oeplist);
		model.addAttribute("user",user);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_start_finish_list";
	}
	@ResponseBody
	@RequestMapping(value = "auditStartFinishListJson")
	public Object auditStartFinishListJson(HttpServletRequest request, HttpServletResponse response){
		Gson gson=new Gson();
		User user=UserUtils.getUser();
		OaEosProStart oepro=new OaEosProStart();
		oepro.setUserId(user.getId());
		Page<OaEosProStart> oeplist=oaEosProStartService.findListByFlowFinishUser(new Page<OaEosProStart>(request, response), oepro);
		for(OaEosProStart pro:oeplist.getList()){
			pro.str_status=DictUtils.getDictLabel(pro.getStatus().toString(),"pro_start_status",null);
			pro.apple_date=DateUtils.formatDate(pro.getFlow().getCreateDate());
		}
		return oeplist;
	}
	/***
	 * 立项详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "auditprostart", method = RequestMethod.GET)
	public String auditprostart(@RequestParam(required=false) String id,Model model) {
		User user=UserUtils.getUser();
		//public String auditpro(@RequestParam(required=false) String id,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		OaEosPro oepro=oaEosProService.get(id);
		oepro.setUsers(UserUtils.getUsersByIds(oepro.getUserIds()));
		model.addAttribute("oepro",oepro);
		OaEosProStart oeprostart=oaEosProStartService.get(id);
		oeprostart.setUsers(UserUtils.getUsersByIds(oepro.getUserIds()));
		model.addAttribute("oeprostart",oeprostart);
		OaEosProCplan cplan=new OaEosProCplan();
		cplan.setEosId(oeprostart.getId());
		model.addAttribute("planlist", oaEosProCplanService.findList(cplan));
		OaEosFlowItem item = new OaEosFlowItem();
		if(oeprostart.getFlow()!=null)
			item.setFlowId(oeprostart.getFlow().getId());
		TreeMap<Integer, List<OaEosFlowItem>> map = oaEosFlowItemService.findListByFlowId(item);
		model.addAttribute("flowmap", map);
		model.addAttribute("flow", new OaEosFlows());
		if (user != null) {
			List<OaEosFlowItem> oeflsit = oaEosFlowItemService.FlowUsers(map);//找到要处理的流程
			for (OaEosFlowItem oflow : oeflsit) {
				if (oflow.getUser().getId().equals(user.getId())) {
					model.addAttribute("flow", oflow);
				}
			}
		}
		OaEosProStartItem proitem=new OaEosProStartItem();
		proitem.setEosId(id);
		List<OaEosProStartItem> itmelist=oaEosProStartItemService.findList(proitem);
		model.addAttribute("itmelist", itmelist);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_pro_start";
	}

	/***
	 * 预立项审批
	 * @param eosid
	 * @param flowid
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "audit", method = RequestMethod.POST)
	@ResponseBody
	public String auditpro(@RequestParam(required=false) String eosid,@RequestParam(required=false) String flowid,@RequestParam(required=false) String content) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(eosid)||StringUtils.isEmpty(flowid)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			if(oaEosProService.AuditPro(eosid,flowid,content)) {
                json.put("success", true);
                json.put("message", "审核通过！");
            }else{
                json.put("success",false);
                json.put("message","审核异常！请联系管理员");
            }
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}
	/***
	 * 预立项驳回
	 * @param eosid
	 * @param flowid
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "auditback", method = RequestMethod.POST)
	@ResponseBody
	public String auditback(@RequestParam(required=false) String eosid,@RequestParam(required=false) String flowid,@RequestParam(required=false) String content) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(eosid)||StringUtils.isEmpty(flowid)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			if(oaEosProService.AuditProBack(eosid,flowid,content)) {
				json.put("success", true);
				json.put("message", "已驳回！");
			}else{
				json.put("success",false);
				json.put("message","驳回异常！请联系管理员");
			}
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}

	/**
	 * 销售项目启动审批
	 * @param eosid
	 * @param flowid
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "auditstart", method = RequestMethod.POST)
	@ResponseBody
	public String auditprostart(@RequestParam(required=false) String eosid,@RequestParam(required=false) String flowid,@RequestParam(required=false) String content) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(eosid)||StringUtils.isEmpty(flowid)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			if(oaEosProStartService.AuditPro(eosid,flowid,content)) {
				json.put("success", true);
				json.put("message", "审核通过！");
			}else{
				json.put("success",false);
				json.put("message","审核异常！请联系管理员");
			}
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}
	/***
	 * 销售项目启动驳回
	 * @param eosid
	 * @param flowid
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "auditstartback", method = RequestMethod.POST)
	@ResponseBody
	public String auditstartback(@RequestParam(required=false) String eosid,@RequestParam(required=false) String flowid,@RequestParam(required=false) String content) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(eosid)||StringUtils.isEmpty(flowid)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			if(oaEosProStartService.AuditProBack(eosid,flowid,content)) {
				json.put("success", true);
				json.put("message", "已驳回！");
			}else{
				json.put("success",false);
				json.put("message","驳回异常！请联系管理员");
			}
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}
	/**
	 * 非销售项目审批list
	 */
	@RequestMapping(value = "prounauditList")
	public String unProauditList(Model model) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		OaEosProUn oepro=new OaEosProUn();
		oepro.setUserId(user.getId());
		List<OaEosProUn> oeplist=oaEosProUnService.findListByUser(oepro);
		model.addAttribute("list", oeplist);
		model.addAttribute("user",user);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_un_list";
	}
	/**
	 * 非销售项目审批完成list
	 */
	@RequestMapping(value = "prounauditFinishList")
	public String prounauditFinishList(Model model, HttpServletRequest request, HttpServletResponse response) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		model.addAttribute("user",user);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_finish_un_list";
	}
	@ResponseBody
	@RequestMapping(value = "auditUnFinishListJson")
	public Object auditUnFinishListJson(HttpServletRequest request, HttpServletResponse response){
		User user=UserUtils.getUser();
		OaEosProUn oepro=new OaEosProUn();
		oepro.setUserId(user.getId());
		Page<OaEosProUn> oeplist=oaEosProUnService.findListByFlowFinishUser(new Page<OaEosProUn>(request, response), oepro);
		for(OaEosProUn pro:oeplist.getList()){
			pro.str_status=DictUtils.getDictLabel(pro.getStatus().toString(),"pro_start_status",null);
			pro.apple_date=DateUtils.formatDate(pro.getFlow().getCreateDate());
		}
		return oeplist;
	}
	/***
	 * 非销售 详情
	 */
	@RequestMapping(value = "auditproinfo", method = RequestMethod.GET)
	public String auditprouninfo(@RequestParam(required=false) String id,Model model) {
		User user=UserUtils.getUser();
		//public String auditpro(@RequestParam(required=false) String id,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		OaEosProUn oepro=oaEosProUnService.get(id);
		oepro.setUsers(UserUtils.getUsersByIds(oepro.getUserIds()));
		model.addAttribute("oepro",oepro);
		OaEosFlowItem item = new OaEosFlowItem();
		if(oepro.getFlow()!=null)
			item.setFlowId(oepro.getFlow().getId());
		TreeMap<Integer, List<OaEosFlowItem>> map = oaEosFlowItemService.findListByFlowId(item);
		model.addAttribute("flowmap", map);
		model.addAttribute("flow", new OaEosFlows());
		if (user != null) {
			List<OaEosFlowItem> oeflsit = oaEosFlowItemService.FlowUsers(map);//找到要处理的流程
			for (OaEosFlowItem oflow : oeflsit) {
				if (oflow.getUser().getId().equals(user.getId())) {
					model.addAttribute("flow", oflow);
				}
			}
		}
		OaEosProStartItem proitem=new OaEosProStartItem();
		proitem.setEosId(id);
		List<OaEosProStartItem> itmelist=oaEosProStartItemService.findList(proitem);
		model.addAttribute("itmelist", itmelist);
		return "modules/cms/front/themes/" + site.getTheme() + "/audit/audit_pro_un";
	}

	 /*** 非销售项目审批
	 * @param eosid
	 * @param flowid
	 * @param content
	 * @return
			 */
	@RequestMapping(value = "auditun", method = RequestMethod.POST)
	@ResponseBody
	public String auditun(@RequestParam(required=false) String eosid,@RequestParam(required=false) String flowid,@RequestParam(required=false) String content) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(eosid)||StringUtils.isEmpty(flowid)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			if(oaEosProUnService.AuditPro(eosid,flowid,content)) {
				json.put("success", true);
				json.put("message", "审核通过！");
			}else{
				json.put("success",false);
				json.put("message","审核异常！请联系管理员");
			}
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}
	/***
	 * 非销售项目启动驳回
	 * @param eosid
	 * @param flowid
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "auditunback", method = RequestMethod.POST)
	@ResponseBody
	public String auditunback(@RequestParam(required=false) String eosid,@RequestParam(required=false) String flowid,@RequestParam(required=false) String content) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(eosid)||StringUtils.isEmpty(flowid)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			if(oaEosProUnService.AuditProBack(eosid,flowid,content)) {
				json.put("success", true);
				json.put("message", "已驳回！");
			}else{
				json.put("success",false);
				json.put("message","驳回异常！请联系管理员");
			}
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}
	/***
	 * 项目工时汇总列表
	 */
	@RequestMapping(value = "summaryList", method = RequestMethod.GET)
	public String summaryList(@RequestParam(required=false) String proid,@RequestParam(required=false) String type,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		model.addAttribute("proid",proid);
		model.addAttribute("type",type);
		if("ylx".equals(type)||"lx".equals(type)) {
			OaEosPro pro = oaEosProService.get(proid);
			model.addAttribute("pro",pro);
		}
		if("flx".equals(type)) {
			OaEosProUn pro = oaEosProUnService.get(proid);
			model.addAttribute("pro",pro);
		}
		OaEosProTimetotal total=oaEosProTimetotalService.get(proid);
		model.addAttribute("total",total);
		List<Date> datas=DateUtils.getDataWeek(0);
		Date date1=datas.get(0);
		Date date2=datas.get(datas.size()-1);
		String date=DateUtils.formatDate(date1,"yyyy-MM-dd")+"至"+DateUtils.formatDate(date2,"yyyy-MM-dd");
		model.addAttribute("date",date);
		List<Role> rols= UserUtils.getUser().getRoleList();
		model.addAttribute("summaryedit",false);
		for(Role role:rols){
			if(role.getName().equals(summaryedit)){//编辑
				model.addAttribute("summaryedit",true);
			}
		}

		return "modules/cms/front/themes/" + site.getTheme() + "/summary/summarylist";
	}
	@ResponseBody
	@RequestMapping(value = "summaryListJson", method = RequestMethod.GET)
	public Object getSummaryListJson(@RequestParam(required=false) String proid,@RequestParam(required=false) String type){
		OaEosProTimetotalItem oeptt=new OaEosProTimetotalItem();
		oeptt.setProId(proid);
		Page<OaEosProTimetotalItem> page=oaEosProTimetotalItemService.findPage(new Page<OaEosProTimetotalItem>(),oeptt);
		return page;
	}
	/***
	 * 项目工时汇总表单
	 */
	@RequestMapping(value = "summaryfrom", method = RequestMethod.GET)
	public String summaryfrom(@RequestParam(required=false) String proid,@RequestParam(required=false) String type,@RequestParam(required=false) String userType,Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		model.addAttribute("proid",proid);
		model.addAttribute("type",type);
		if("ylx".equals(type)||"lx".equals(type)) {
			OaEosPro pro = oaEosProService.get(proid);
			model.addAttribute("pro",pro);
		}
		if("flx".equals(type)) {
			OaEosProUn pro = oaEosProUnService.get(proid);
			model.addAttribute("pro",pro);
		}
		List<Date> datas=DateUtils.getDataWeek(0);
		Date date1=datas.get(0);
		Date date2=datas.get(datas.size()-1);
		String date=DateUtils.formatDate(date1,"yyyy-MM-dd")+"至"+DateUtils.formatDate(date2,"yyyy-MM-dd");
		model.addAttribute("time",date);
		OaEosProTimetotalItem items=new OaEosProTimetotalItem();
		items.setProId(proid);
		items.setTimes(date);
		items=oaEosProTimetotalItemService.getByTimes(items);
		model.addAttribute("item",items);
		model.addAttribute("userType",userType);
		return "modules/cms/front/themes/" + site.getTheme() + "/summary/summaryfrom";
	}
	/***
	 * 项目工时汇总保存
	 */
	@RequestMapping(value = "summarysave", method = RequestMethod.POST)
	@ResponseBody
	public String summarysave(OaEosProTimetotalItem item,@RequestParam(required=false) String type,@RequestParam(required=false) String userType,Model model) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(item.getProId())||StringUtils.isEmpty(type)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			List<Date> datas= DateUtils.getDataWeek(0);
			Date date1=datas.get(0);
			Date date2=datas.get(datas.size()-1);
			String date=DateUtils.formatDate(date1,"yyyy-MM-dd")+"至"+DateUtils.formatDate(date2,"yyyy-MM-dd");
			OaEosProTimetotalItem items=new OaEosProTimetotalItem();
			items.setProId(item.getProId());
			items.setTimes(date);
			items=oaEosProTimetotalItemService.getByTimes(items);
			if(items!=null){
				item.setId(items.getId());
			}
			if(oaEosProTimetotalItemService.itemSave(item,userType)) {
				json.put("success", true);
				oaEosProService.getAllPro();
				json.put("message", "保存完成！");
			}else{
				json.put("success",false);
				json.put("message","保存异常！请联系管理员");
			}
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}
	/**
	 * 销售额申请审批list
	 */
	@RequestMapping(value = "userAccountApplyList")
	public String userAccountApplyList(Model model) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		model.addAttribute("user",user);
        OaUserAccount account=new OaUserAccount();
        account.setUserId(user.getId());
        List<OaUserAccount> oeplist=oaUserAccountService.findListByUser(account);
        model.addAttribute("list", oeplist);
		return "modules/cms/front/themes/" + site.getTheme() + "/user/account_list";
	}
	/**
	 * 销售额申请审批详情
	 */
	@RequestMapping(value = "accountApply")
	public String accountApply(@RequestParam(required=false) String id,Model model) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		model.addAttribute("user",user);
		OaUserAccount account=oaUserAccountService.get(id);
		model.addAttribute("oaUserAccount", account);
        OaEosFlowItem item = new OaEosFlowItem();
        if(account.getFlowId()!=null)
            item.setFlowId(account.getFlowId());
            TreeMap<Integer, List<OaEosFlowItem>> map = oaEosFlowItemService.findListByFlowId(item);
            model.addAttribute("flowmap", map);
            model.addAttribute("flow", new OaEosFlows());
            if (user != null) {
                List<OaEosFlowItem> oeflsit = oaEosFlowItemService.FlowUsers(map);//找到要处理的流程
                for (OaEosFlowItem oflow : oeflsit) {
                    if (oflow.getUser().getId().equals(user.getId())) {
                        model.addAttribute("flow", oflow);
                    }
                }
            }
		return "modules/cms/front/themes/" + site.getTheme() + "/user/account_audit";
	}
	/**
	 * 销售额申请审批list
	 */
	@RequestMapping(value = "userAccountApplyfinishList")
	public String userAccountApplyfinishList(Model model) {
		User user=UserUtils.getUser();
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		site.setTheme("weixin");
		model.addAttribute("site", site);
		model.addAttribute("user",user);
		OaUserAccount account=new OaUserAccount();
		account.setUserId(user.getId());
		return "modules/cms/front/themes/" + site.getTheme() + "/user/account_audit_finish_list";
	}
	/**
	 * 销售额申请审批完成json
	 */
	@RequestMapping(value = "userAccountApplyListJSON")
    @ResponseBody
	public Object userAccountApplyListJSON(HttpServletRequest request, HttpServletResponse response) {
		User user=UserUtils.getUser();
		OaUserAccount account=new OaUserAccount();
		account.setUserId(user.getId());
		Page<OaUserAccount> oeplist=oaUserAccountService.findListByFlowFinishUser(new Page<OaUserAccount>(request,response),account);
		return oeplist;
	}
	/***
	 * 销售额申请审批
	 * @param eosid
	 * @param flowid
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "accountaudit", method = RequestMethod.POST)
	@ResponseBody
	public String accountaudit(@RequestParam(required=false) String eosid,@RequestParam(required=false) String flowid,@RequestParam(required=false) String content) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(eosid)||StringUtils.isEmpty(flowid)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			if(oaUserAccountService.AuditPro(eosid,flowid,content)) {
				json.put("success", true);
				json.put("message", "审核通过！");
			}else{
				json.put("success",false);
				json.put("message","审核异常！请联系管理员");
			}
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}
	/***
	 * 销售额申请驳回
	 * @param eosid
	 * @param flowid
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "accountauditback", method = RequestMethod.POST)
	@ResponseBody
	public String accountauditback(@RequestParam(required=false) String eosid,@RequestParam(required=false) String flowid,@RequestParam(required=false) String content) {
		User user=UserUtils.getUser();
		JSONObject json=new JSONObject();
		if(user==null){
			json.put("success",false);
			json.put("message","用户过期，请重新访问！");
			return json.toString();
		}
		if(StringUtils.isEmpty(eosid)||StringUtils.isEmpty(flowid)){
			json.put("success",false);
			json.put("message","请求参数错误！");
			return json.toString();
		}
		try{
			if(oaUserAccountService.AuditBack(eosid,flowid,content)) {
				json.put("success", true);
				json.put("message", "已驳回！");
			}else{
				json.put("success",false);
				json.put("message","驳回异常！请联系管理员");
			}
		}catch (Exception e){
			json.put("success",false);
			json.put("message","系统异常！");
		}
		return json.toString();
	}
}
