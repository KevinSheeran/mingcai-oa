/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.sys.security;
import com.google.gson.Gson;
import com.mingcai.edu.common.utils.SpringContextHolder;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.weixinApi.Token;
import com.mingcai.edu.common.utils.weixinApi.TokenService;
import com.mingcai.edu.common.wx.wxApi;
import com.mingcai.edu.modules.oa.dao.wx.OaWxDepartmentDao;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import com.mingcai.edu.modules.sys.dao.RoleDao;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.Role;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 表单验证（包含验证码）过滤类
 * @author ThinkGem
 * @version 2014-5-19
 */
public class WeiXinFilter  extends HttpServlet implements Filter {
	private static final long serialVersionUID = 1L;
	private  UserDao userDao = SpringContextHolder.getBean(UserDao.class);
	private  RoleDao roleDao = SpringContextHolder.getBean(RoleDao.class);
	private OaWxDepartmentDao departmentDao= SpringContextHolder.getBean(OaWxDepartmentDao.class);
	/***
	 * 销售部门id
	 */
	protected String SaleDepartmentId="2";
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response  =(HttpServletResponse) arg1;
		Object obj=UserUtils.getCache("user");
		String code=request.getParameter("code");
		if(obj==null) {
			if (StringUtils.isNotBlank(code)) {
				Gson gson = new Gson();
				User user = null;
				if (StringUtils.isNotBlank(code)) {
					String gsono = TokenService.SeendHttp(wxApi.getMessage(wxApi.GetUserByCode, "code=" + code), "GET", "");//获取部门用户列表
					Token reurntoken = gson.fromJson(gsono, Token.class);
					if (reurntoken.UserId != null) {
						user=new User();
						OaWxUsers owuser = new OaWxUsers();
						owuser.setUserid(reurntoken.UserId);
						user.setWxUsers(owuser);
						user.setUserId(user.getId());
						user=userDao.getUserByWxUserId(user);
						if(user==null||user.getWxUsers()==null||user.getWxUsers().getUserid()==null){//通过微信 没有找到当前访问用户code信息
							response.sendRedirect("/404.jsp");
							return;
						}
						user.setUserId(user.getId());
						OaWxUsers owu=user.getWxUsers();
						String deps=owu.getDepartmentId();
						if(StringUtils.isNotEmpty(deps)) {//将登录用户所有部门列出
							String[] dep = deps.split(",");
							String depnames = "";
							if (dep.length > 0) {
								for (String de : dep) {
									if (StringUtils.isNotEmpty(de)) {
										if(de.equals(SaleDepartmentId)){//当前登录是否是销售
											user.setSale(true);
										}
										depnames += departmentDao.get(de).getName() + "/";
									}
								}
							}
							if (depnames.length() > 0) {
								depnames = depnames.substring(0, depnames.length() - 1);
							}
							owu.setDepartment_s(depnames);
							user.setWxUsers(owu);
						}
						user.setRoleList(roleDao.findList(new Role(user)));
						UserUtils.putCache("user",user);
					}else{
						System.out.println(request.getContextPath());
						response.sendRedirect("/404.jsp");
						return;
					}
				}
			}else{
				System.out.println(request.getContextPath());
				response.sendRedirect("/404.jsp");
				return;
			}
		}else{
			UserUtils.putCache("user",obj);
		}
		arg2.doFilter(arg0, arg1);
		return;
	}
	public void init(FilterConfig arg0) throws ServletException {
	}
}