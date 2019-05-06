/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.web.week;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.web.BaseController;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.week.OaWeekWork;
import com.mingcai.edu.modules.oa.service.week.OaWeekWorkService;

import java.util.*;

/**
 * 周任务Controller
 * @author 坤
 * @version 2018-01-24
 */
@Controller
@RequestMapping(value = "${adminPath}/oa/week/oaWeekWork")
public class OaWeekWorkController extends BaseController {

	@Autowired
	private OaWeekWorkService oaWeekWorkService;

	@Autowired
	private SystemService systemService;
	@ModelAttribute
	public OaWeekWork get(@RequestParam(required=false) String id) {
		OaWeekWork entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaWeekWorkService.get(id);
		}
		if (entity == null){
			entity = new OaWeekWork();
		}
		return entity;
	}
	
	@RequiresPermissions("oa:week:oaWeekWork:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaWeekWork oaWeekWork, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("userList",systemService.findAllList(new User()));
		return "modules/oa/week/oaWeekWorkListMain";
	}
	@RequiresPermissions("oa:week:oaWeekWork:view")
	@RequestMapping(value = {"Userlist"})
	public String Userlist(OaWeekWork oaWeekWork, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(StringUtils.isNotEmpty(request.getParameter("userId"))) {
			oaWeekWork.setCreateBy(new User(request.getParameter("userId")));
		}else{oaWeekWork.setCreateBy(UserUtils.getUser());}
		disPlay(oaWeekWork,model);
		//d.get()
		//Page<OaWeekWork> page = oaWeekWorkService.findPage(new Page<OaWeekWork>(request, response), oaWeekWork);
		//model.addAttribute("page", page);
		return "modules/oa/week/oaWeekWorkList";
	}
	//画出时间
	public  void disPlay(OaWeekWork oaWeekWork,Model model){
		Calendar ca = Calendar.getInstance();
		String[] mon = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
		String[] week = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
		ca.set(Calendar.DAY_OF_WEEK, 2);
		ca.add(Calendar.WEEK_OF_YEAR,1);
		Date startd = ca.getTime();
		ca.add(Calendar.DATE, 6);
		Date endD = ca.getTime();
		List<OaWeekWork> weeklist=new ArrayList<OaWeekWork>();
		for (int i = 0; i < 30; i++) {
			String startdate=ca.get(Calendar.YEAR)+"."+(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
			String date1=(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
			String endDate=(ca.get(Calendar.YEAR)+"."+ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
			String date2=(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
			int year=ca.get(Calendar.YEAR);
			Map<String,String> dates=new HashMap<String, String>();
			for (int j = 6; j >= 0; j--) {
				System.out.print(ca.get(Calendar.YEAR) + "年");
				System.out.print(mon[ca.get(Calendar.MONTH)]);
				System.out.print(ca.get(Calendar.DAY_OF_MONTH) + "日");
				System.out.print(week[ca.get(Calendar.DAY_OF_WEEK)] + "\t");
				endDate=ca.get(Calendar.YEAR)+"."+(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
				date2=(ca.get(Calendar.MONTH)+1)+"."+ca.get(Calendar.DAY_OF_MONTH);
				dates.put(j+"",ca.get(Calendar.DAY_OF_MONTH)+"");
				ca.add(Calendar.DATE, -1);
			}
			oaWeekWork.setStartEnd(endDate+"-"+startdate);
			OaWeekWork weekobj =oaWeekWorkService.getByDate(oaWeekWork);
			if(weekobj==null){
				 weekobj=new OaWeekWork();
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
			weekobj.setTitles(year+"年第"+ca.get(Calendar.WEEK_OF_YEAR)+"周("+date2+"-"+date1+"&nbsp;"+title+")周计划");
			if(i<3&&oaWeekWork.getCreateBy().getId().equals(UserUtils.getUser().getId())){
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

	@RequiresPermissions("oa:week:oaWeekWork:view")
	@RequestMapping(value = "form")
	public String form(OaWeekWork oaWeekWork, Model model) {
		model.addAttribute("oaWeekWork", oaWeekWork);
		return "redirect:"+Global.getAdminPath()+"/oa/week/oaWeekWork/Userlist?repage";
	}

	@RequiresPermissions("oa:week:oaWeekWork:edit")
	@RequestMapping(value = "save")
	public String save(OaWeekWork oaWeekWork, Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {

		oaWeekWork.setStartEnd(request.getParameter("startEnd"));
		OaWeekWork weekobj =oaWeekWorkService.getByDate(oaWeekWork);
		if (!beanValidator(model, oaWeekWork)||weekobj!=null&&!StringUtils.isNotEmpty(oaWeekWork.getId())){
			return form(oaWeekWork, model);
		}
		oaWeekWorkService.save(oaWeekWork);
		addMessage(redirectAttributes, "保存周任务成功");
		return "redirect:"+Global.getAdminPath()+"/oa/week/oaWeekWork/Userlist?repage";
	}
	
	@RequiresPermissions("oa:week:oaWeekWork:edit")
	@RequestMapping(value = "delete")
	public String delete(OaWeekWork oaWeekWork, RedirectAttributes redirectAttributes) {
		oaWeekWorkService.delete(oaWeekWork);
		addMessage(redirectAttributes, "删除周任务成功");
		return "redirect:"+Global.getAdminPath()+"/oa/week/oaWeekWork/?repage";
	}

}