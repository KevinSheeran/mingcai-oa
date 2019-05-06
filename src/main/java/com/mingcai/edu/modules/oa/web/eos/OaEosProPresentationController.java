/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.eos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStart;
import com.mingcai.edu.modules.oa.service.eos.OaEosProService;
import com.mingcai.edu.modules.oa.service.eos.OaEosProStartService;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
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
import com.mingcai.edu.modules.oa.entity.eos.OaEosProPresentation;
import com.mingcai.edu.modules.oa.service.eos.OaEosProPresentationService;

import java.util.*;

/**
 * 项目周报Controller
 * @author kun
 * @version 2019-03-25
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/eos/oaEosProPresentation")
public class OaEosProPresentationController extends BaseController {

	@Autowired
	private OaEosProPresentationService oaEosProPresentationService;
	@Autowired
	private OaEosProStartService oaEosProStartService;
	@ModelAttribute
	public OaEosProPresentation get(@RequestParam(required=false) String id) {
		OaEosProPresentation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaEosProPresentationService.get(id);
		}
		if (entity == null){
			entity = new OaEosProPresentation();
		}
		return entity;
	}
	@RequiresPermissions("oa:eos:oaEosProPresentation:view")
	@RequestMapping(value = {"index"})
	public String index(OaEosProPresentation oaEosProPresentation, HttpServletRequest request, HttpServletResponse response, Model model) {

		return "modules/oa/eos/oaProWeekMain";
	}
    @RequiresPermissions("user")
    @ResponseBody
    @RequestMapping(value = "treeData")
	public List<Map<String, Object>> userPro(){
        List<Map<String, Object>> mapList = Lists.newArrayList();
        OaEosProStart pro=new OaEosProStart();
        List<Role> rols= UserUtils.getUser().getRoleList();
        for(Role role:rols){
            if(role.getName().equals(proAdmin)){
                pro.setLeader(true);
            }
        }
        pro.setStatus(2);
        pro.setUserId(UserUtils.getUser().getId());
        List<OaEosProStart> prolist=oaEosProStartService.findList(pro);
        Map<String, Object> maps = Maps.newHashMap();
        maps.put("id", 1);
        maps.put("pId", 0);
        maps.put("open", true);
        maps.put("name", "上海明材教育立项项目");
        mapList.add(maps);
        for (int i=0; i<prolist.size(); i++){
            OaEosProStart e = prolist.get(i);
            Map<String, Object> map = Maps.newHashMap();
            map.put("id", e.getId());
            map.put("pId", 1);
            map.put("open", true);
            map.put("name", e.getPro().getName());
            mapList.add(map);
        }
        return mapList;
    }
	@RequiresPermissions("oa:eos:oaEosProPresentation:view")
	@RequestMapping(value = {"list",""})
	public String list(OaEosProPresentation oaEosProPresentation, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaEosProPresentation.setCreateBy(UserUtils.getUser());
		OaEosProStart pro=null;
		String eosid=request.getParameter("eosId");
		if(StringUtils.isNotEmpty(eosid)) {
			pro=oaEosProStartService.get(eosid);
			oaEosProPresentation.setEosId(eosid);
		}else{
			pro=new OaEosProStart();
			pro.setStatus(2);
			pro.setUserId(UserUtils.getUser().getId());
			List<OaEosProStart> prolist=oaEosProStartService.findList(pro);
			if(prolist.size()>0){
				pro=prolist.get(0);
			}
		}
		oaEosProPresentation.setEosId(pro.getId());
		model.addAttribute("pro",pro);
		disPlay(oaEosProPresentation,model);
		return "modules/oa/eos/oaEosProPresentationList";
	}
	@RequiresPermissions("oa:eos:oaEosProPresentation:view")
	@RequestMapping(value = {"page"})
	public String page(OaEosProPresentation oaEosProPresentation,@RequestParam(required=false) String proid ,HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("proid",proid);
		return "modules/oa/eos/oaEosProPresentationPage";
	}
	@RequestMapping(value = {"pageJson"})
	@ResponseBody
	public String pageJson(OaEosProPresentation oaEosProPresentation,@RequestParam(required=false) String proid, HttpServletRequest request, HttpServletResponse response, Model model) {
		oaEosProPresentation.setEosId(proid);
		Page<OaEosProPresentation> page = oaEosProPresentationService.findPage(new Page<OaEosProPresentation>(request, response), oaEosProPresentation);
		Gson g=new Gson();
		return g.toJson(page);
	}
	//画出时间
	public  void disPlay(OaEosProPresentation oaEosProPresentation,Model model){
		Calendar ca = Calendar.getInstance();
		String[] mon = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
		String[] week = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		ca.set(Calendar.DAY_OF_WEEK, 2);
		ca.add(Calendar.WEEK_OF_YEAR,1);
		Date startd = ca.getTime();
		ca.add(Calendar.DATE, 4);
		Date endD = ca.getTime();
		List<OaEosProPresentation> weeklist=new ArrayList<OaEosProPresentation>();
		for (int i = 0; i < 30; i++) {
			String startdate=ca.get(Calendar.YEAR)+"."+(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
			String date1=(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
			String endDate=(ca.get(Calendar.YEAR)+"."+ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
			String date2=(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
			int year=ca.get(Calendar.YEAR);
			Map<String,String> dates=new HashMap<String, String>();
			for (int j = 6; j >= 0; j--) {
//				System.out.print(ca.get(Calendar.YEAR) + "年");
//				System.out.print(mon[ca.get(Calendar.MONTH)]);
//				System.out.print(ca.get(Calendar.DAY_OF_MONTH) + "日");
//				System.out.print(week[ca.get(Calendar.DAY_OF_WEEK)] + "\t");
				endDate=ca.get(Calendar.YEAR)+"."+(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
				date2=(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
				dates.put(j+"",ca.get(Calendar.DAY_OF_MONTH)+"");
				ca.add(Calendar.DATE, -1);
			}
			oaEosProPresentation.setStartEnd(endDate+"-"+startdate);
			OaEosProPresentation weekobj =oaEosProPresentationService.getByDate(oaEosProPresentation);
			if(weekobj==null){
				weekobj=new OaEosProPresentation();
			}
			weekobj.setWeeksDate(dates);
			weekobj.setStartEnd(endDate+"-"+startdate);
			String title="";
			if(i==0){
				title="下周";
			}
			if(i==1){

				title="本周";
			}
			if(i==2){
				title="上周";

			}
			weekobj.setTitles(year+"年第"+ca.get(Calendar.WEEK_OF_YEAR)+"周("+date2+"-"+date1+"&nbsp;"+title+")项目周报");
			if(i<3&&oaEosProPresentation.getCreateBy().getId().equals(UserUtils.getUser().getId())){
				weekobj.setIsshow(true);
				if(weekobj.getId()!=null){
					weekobj.setIsshow(false);
				}
			}

			weeklist.add(weekobj);
			System.out.println();
		}
		model.addAttribute("weekList",weeklist);
	}
	@RequiresPermissions("oa:eos:oaEosProPresentation:view")
	@RequestMapping(value = "form")
	public String form(OaEosProPresentation oaEosProPresentation, Model model) {
		model.addAttribute("oaEosProPresentation", oaEosProPresentation);
		return "modules/oa/eos/oaEosProPresentationForm";
	}

	@RequiresPermissions("oa:eos:oaEosProPresentation:edit")
	@RequestMapping(value = "save")
	public String save(OaEosProPresentation oaEosProPresentation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaEosProPresentation)){
			return form(oaEosProPresentation, model);
		}
		oaEosProPresentationService.save(oaEosProPresentation);
		addMessage(redirectAttributes, "保存项目周报成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProPresentation/?eosId="+oaEosProPresentation.getEosId();
	}
	
	@RequiresPermissions("oa:eos:oaEosProPresentation:edit")
	@RequestMapping(value = "delete")
	public String delete(OaEosProPresentation oaEosProPresentation, RedirectAttributes redirectAttributes) {
		oaEosProPresentationService.delete(oaEosProPresentation);
		addMessage(redirectAttributes, "删除项目周报成功");
		return "redirect:"+Global.getAdminPath()+"/oa/eos/oaEosProPresentation/?repage";
	}

}