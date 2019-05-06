/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service;

import java.util.List;

import com.mingcai.edu.common.emailfire.api.test.SendMail;
import com.mingcai.edu.common.mapper.PutEntity;
import com.mingcai.edu.modules.oa.dao.OaProductTaskLogDao;
import com.mingcai.edu.modules.oa.dao.OaProductUsersDao;
import com.mingcai.edu.modules.oa.entity.OaProductTaskLog;
import com.mingcai.edu.modules.oa.entity.OaProductUsers;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.DictUtils;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.entity.OaProductTask;
import com.mingcai.edu.modules.oa.dao.OaProductTaskDao;

/**
 * 项目任务Service
 * @author 江坤
 * @version 2017-11-30
 */
@Service
@Transactional(readOnly = true)
public class OaProductTaskService extends CrudService<OaProductTaskDao, OaProductTask> {

	@Autowired
	private OaProductTaskDao dao;
	@Autowired
	private OaProductUsersDao oaProductUsersDao;
	@Autowired
	private OaProductTaskLogDao oaProductTaskLogDao;
	@Autowired
	private UserDao userDao;

	@Value("${oaIp}")
	protected String oaIp;
	public OaProductTask get(String id) {
		OaProductTask oaProductTask = super.get(id);
		return oaProductTask;
	}
	
	public List<OaProductTask> findList(OaProductTask oaProductTask) {
		return dao.findAllList(oaProductTask);
	}
	public Page<OaProductTask> findListByUser(Page<OaProductTask> page,OaProductTask oaProductTask) {
		oaProductTask.setPage(page);
		page.setList(dao.findListByUser(oaProductTask));
		return page;
	}
	public Page<OaProductTask> findPage(Page<OaProductTask> page, OaProductTask oaProductTask) {
		return super.findPage(page, oaProductTask);
	}
	public OaProductUsers countProduct(OaProductUsers users){

		return oaProductUsersDao.countProductId(users);
	}
	public int countProduct(String users){

		return dao.countUnTask(users);
	}
	public int countUserTask(String users){

		return dao.countUserUnTask(users);
	}
	/***
	 * 找到项目的用户集合
	 * @param users
	 * @return
	 */
	public List<OaProductUsers> findListByproductId(OaProductUsers users){
		return oaProductUsersDao.findListByproductId(users);
	}
	/***
	 * 找到任务跟进项集合
	 * @param users
	 * @return
	 */
	@Transactional(readOnly = false)
	public List<OaProductTaskLog> findList(OaProductTaskLog users){
		return oaProductTaskLogDao.findList(users);
	}
	@Transactional(readOnly = false)
	public void save(OaProductTask oaProductTask) {
		boolean isnew= true;
		if(!oaProductTask.getIsNewRecord()){
			isnew=false;
		}
		super.save(oaProductTask);
		PutEntity putmail=new PutEntity();
		if(oaProductTask.getTaskStatus().equals("0")&&isnew) {
			putmail.setTitle("新建任务通知");
			putmail.setContent("您有一项新任务通知：<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目："+oaProductTask.getProductName()+"&nbsp;&nbsp;任务："+oaProductTask.getName()+",请前往oa系统"+oaIp+"确认！<br/><br/>发件人："+UserUtils.getUser().getName()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱地址："+UserUtils.getUser().getEmail());
		}
		if(!isnew) {
			putmail.setTitle("任务调整通知");
			putmail.setContent("您有一项任务调整通知：<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;项目："+oaProductTask.getProductName()+"&nbsp;&nbsp;任务："+oaProductTask.getName()+"&nbsp;&nbsp;状态调整为："+ DictUtils.getDictLabel(oaProductTask.getTaskStatus(),"task_status","")+",请前往oa系统"+oaIp+"确认！<br/><br/>发件人:"+UserUtils.getUser().getName()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;邮箱地址："+UserUtils.getUser().getEmail());
		}
		if(oaProductTask.getUpdateBy().getId().equals(oaProductTask.getTaskUser().getId())&&!oaProductTask.getTaskUser().getId().equals(oaProductTask.getCreateBy().getId())) {//更新用户等于任务指派人，任务指派人不是创建人
			User users=userDao.get(oaProductTask.getCreateBy().getId());
			putmail.setRecipient(users.getEmail());
			putmail.setDisplayName( UserUtils.getUser().getName());
			putmail.setReplay(users.getEmail());
			SendMail.singleSender(putmail);
		}
		if(oaProductTask.getUpdateBy().getId().equals(oaProductTask.getCreateBy().getId())&&!oaProductTask.getTaskUser().getId().equals(oaProductTask.getCreateBy().getId())) {//更新用户等于创建人，任务指派人不是创建人
			User users=userDao.get(oaProductTask.getTaskUser().getId());
			putmail.setRecipient(users.getEmail());
			putmail.setDisplayName( UserUtils.getUser().getName());
			putmail.setReplay(users.getEmail());
			SendMail.singleSender(putmail);
		}
		if(!oaProductTask.getUpdateBy().getId().equals(oaProductTask.getCreateBy().getId())&&!oaProductTask.getTaskUser().getId().equals(oaProductTask.getUpdateBy().getId())) {//更新用户等于创建人，任务指派人不是创建人
			User users=userDao.get(oaProductTask.getTaskUser().getId());
			putmail.setRecipient(users.getEmail());
			putmail.setDisplayName( UserUtils.getUser().getName());
			putmail.setReplay(users.getEmail());
			SendMail.singleSender(putmail);
			User userss=userDao.get(oaProductTask.getCreateBy().getId());
			putmail.setRecipient(userss.getEmail());
			putmail.setDisplayName( UserUtils.getUser().getName());
			putmail.setReplay(userss.getEmail());
			SendMail.singleSender(putmail);
		}
		OaProductTaskLog tasklog=new OaProductTaskLog();
		tasklog.setCreateBy(oaProductTask.getCreateBy());
		tasklog.setCreateDate(oaProductTask.getCreateDate());
		tasklog.setProdcutEndDate(oaProductTask.getProdcutEndDate());
		tasklog.setTaskId(oaProductTask.getId());
		tasklog.setTaskStatus(oaProductTask.getTaskStatus());
		tasklog.setTaskToUserId(oaProductTask.getTaskUser().getId());
		tasklog.setRemarks(oaProductTask.getRemarks());
		tasklog.setTaskType("1");
		tasklog.setCode("0");
		tasklog.preInsert();
		oaProductTaskLogDao.insert(tasklog);
	}
	@Transactional(readOnly = false)
	public void insertlog(OaProductTaskLog tasklog) {
		OaProductTaskLog log=oaProductTaskLogDao.getLast(tasklog);
		if(log==null){
			tasklog.setCode("1");
		}
		else {
			tasklog.setCode((Integer.parseInt(log.getCode()) + 1) + "");
		}
		tasklog.preInsert();
		oaProductTaskLogDao.insert(tasklog);
	}


	@Transactional(readOnly = false)
	public void delete(OaProductTask oaProductTask) {
		super.delete(oaProductTask);
	}
	
}