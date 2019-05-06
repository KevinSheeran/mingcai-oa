/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.io.IOException;
import java.util.*;

import com.google.gson.Gson;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.weixinApi.Token;
import com.mingcai.edu.common.utils.weixinApi.TokenService;
import com.mingcai.edu.common.wx.SendMsg;
import com.mingcai.edu.common.wx.Text;
import com.mingcai.edu.common.wx.wxApi;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlow;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowLogo;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlows;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowItem;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowItemDao;

/**
 * 流程项Service
 * @author 坤
 * @version 2019-03-18
 */
@Service
@Transactional(readOnly = true)
public class OaEosFlowItemService extends CrudService<OaEosFlowItemDao, OaEosFlowItem> {
	@Autowired
	OaEosFlowsService oaEosFlowsService;
	@Autowired
	private UserDao userDao;
	@Autowired
	OaEosFlowLogoService oaEosFlowLogoService;
	public OaEosFlowItem get(String id) {
		return super.get(id);
	}

	public List<OaEosFlowItem> findList(OaEosFlowItem oaEosFlowItem) {
		return super.findList(oaEosFlowItem);
	}

	public Page<OaEosFlowItem> findPage(Page<OaEosFlowItem> page, OaEosFlowItem oaEosFlowItem) {
		return super.findPage(page, oaEosFlowItem);
	}

	/***
	 * 创建流程
	 * @param processmap 流程项 map > Integer 流程执行序号，map > List 执行用户组
	 * @paramMap type 流程类型 看OaEosFlowItem 属性注解 ,flowName 流程名称 ,isSendUser 是否发送微信通知 ,message 通知消息内容
	 */
	@Transactional(readOnly = false)
	public Map<String,String> createFlow(TreeMap<Integer,String> processmap,Map<String,Object> paramMap){
		Map<String,String> obj=new HashMap<String, String>();
		Object type=paramMap.get("type");
		Object flowName= paramMap.get("flowName");
		if(processmap.size()==0){
			obj.put("success","false");
			obj.put("message","执行用户组不能为空");
			return obj;
		}
		if(type==null){
			obj.put("success","false");
			obj.put("message","流程类型不能为空");
			return obj;
		}
		OaEosFlows flows=new OaEosFlows();
		flows.setName(flowName.toString());
		flows.setSerialNumber(processmap.size());
		flows.setStatus(0);
		flows.setType((Integer)type);

		oaEosFlowsService.save(flows);
		String flowid=flows.getId();
		//创建流程
		for(Integer fnumber:processmap.keySet()){
			String[] userstring=processmap.get(fnumber).split(",");
			int order=0;
			for(String userid:userstring){
				OaEosFlowItem oefi=new OaEosFlowItem();
				oefi.setFlowId(flowid);
				oefi.setOrder(order);
				oefi.setSerialNumber(fnumber);
				oefi.setStatus(0);
				User user=new User();
				user.setId(userid);
				oefi.setUser(user);
				this.save(oefi);
				order++;
				obj.put("success","true");
				obj.put("flowId",flowid);
				obj.put("message","流程创建完成!");
				OaEosFlowLogo logo=new OaEosFlowLogo();
				logo.setItemId(oefi.getId());
				logo.setStatus(oefi.getStatus());
				logo.setUser(user);
				logo.setContent("创建");
				oaEosFlowLogoService.save(logo);
			}
		}

		return obj;
	}
	@Transactional(readOnly = false)
	public Map<String,String> startFlow(String flowId,boolean isSendUser,String message){
		Map<String,String> obj=new HashMap<String, String>();
		if(flowId==null){
			obj.put("success","false");
			obj.put("message","流程id不能为空!");
			return obj;
		}
		OaEosFlowItem item=new OaEosFlowItem();
		item.setFlowId(flowId);
		List<OaEosFlowItem> itemlist=FlowUsers(findListByFlowId(item));
		if(isSendUser){
			String touser="";
			for (OaEosFlowItem oe : itemlist) {
				User user = userDao.get(oe.getUser().getId());
				if (user.getWxUsers() != null) {
					touser += user.getWxUsers().getUserid() + "|";
					oe.setSendStatus(1);
					oe.setSendContent(message);
					super.save(oe);
					OaEosFlowLogo logo=new OaEosFlowLogo();
					logo.setItemId(oe.getId());
					logo.setStatus(oe.getStatus());
					logo.setUser(oe.getUser());
					logo.setContent("开始");
					oaEosFlowLogoService.save(logo);
				}
			}
			if(StringUtils.isNotEmpty(touser)) {
				sendMsg(touser, message);
			}
		}

		obj.put("success","true");
		obj.put("message","流程执行完成!");
		return obj;
	}

	/***
	 * 执行流程
	 * @param isSendUser
	 * @param message
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String,String> auditFlow(String itemId,boolean isSendUser,String message,String finishmessage,String content){
		Map<String,String> obj=new HashMap<String, String>();
		if(itemId==null){
			obj.put("success","false");
			obj.put("message","流程项id不能为空!");
			return obj;
		}
		OaEosFlowItem finditem=get(itemId);
		finditem.setStatus(1);
		finditem.setContent(content);
		super.save(finditem);
		OaEosFlowItem item=new OaEosFlowItem();
		item.setFlowId(finditem.getFlowId());
		List<OaEosFlowItem> itemlist=FlowUsers(findListByFlowId(item));
		if(itemlist.size()==0){
			OaEosFlows flows= oaEosFlowsService.get(finditem.getFlowId());
			flows.setStatus(1);//执行完成
			oaEosFlowsService.save(flows);
			obj.put("success","true");
			obj.put("pass","true");
			obj.put("message","流程任务结束!");
			//sendMsg(flows.getCreateBy().getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户
			OaEosFlowLogo logo=new OaEosFlowLogo();
			logo.setItemId(finditem.getId());
			logo.setStatus(finditem.getStatus());
			logo.setUser(finditem.getUser());
			logo.setContent("流程任务结束");
			oaEosFlowLogoService.save(logo);
			return obj;
		}

		if(isSendUser){
			String touser="";
			boolean send=true;//是否发送过
			for (OaEosFlowItem oe : itemlist) {
				if(oe.getSerialNumber()==item.getSerialNumber()){
					send=false;
					break;
				}
				User user = userDao.get(oe.getUser().getId());
				if (user.getWxUsers() != null) {
					touser += user.getWxUsers().getUserid() + "|";
					oe.setSendStatus(1);
					oe.setSendContent(message);
					super.save(oe);
					OaEosFlowLogo logo=new OaEosFlowLogo();
					logo.setItemId(oe.getId());
					logo.setStatus(oe.getStatus());
					logo.setUser(oe.getUser());
					logo.setContent("执行");
					oaEosFlowLogoService.save(logo);
				}
			}
			if(StringUtils.isNotEmpty(touser)&&send) {
				sendMsg(touser, message);
			}
		}
		obj.put("success","true");
		obj.put("message","流程执行完成!");
		return obj;
	}
	/***
	 * 驳回流程
	 * @param itemId
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String,String> rejectFlow(String itemId,String count){
		Map<String,String> obj=new HashMap<String, String>();
		if(itemId==null){
			obj.put("success","false");
			obj.put("message","流程项id不能为空!");
			return obj;
		}
		OaEosFlowItem item= this.get(itemId);
		OaEosFlows flow=oaEosFlowsService.get(item.getFlowId());
		item.setStatus(-1);
		item.setContent(count);
		flow.setStatus(-1);
		oaEosFlowsService.save(flow);
		this.save(item);
		obj.put("success","true");
		obj.put("message","流程驳回执行完成!");
		return obj;
	}
	/***
	 * 驳回流程
	 * @param itemId
	 * @return
	 */
	@Transactional(readOnly = false)
	public Map<String,String> rejectFlow(String itemId){
		Map<String,String> obj=new HashMap<String, String>();
		if(itemId==null){
			obj.put("success","false");
			obj.put("message","流程项id不能为空!");
			return obj;
		}
		OaEosFlowItem item= this.get(itemId);
		OaEosFlows flow=oaEosFlowsService.get(item.getFlowId());
		item.setStatus(-1);
		flow.setStatus(-1);
		oaEosFlowsService.save(flow);
		this.save(item);
		obj.put("success","true");
		obj.put("message","流程驳回执行完成!");
		return obj;
	}
	/***
	 * 获取请求流程任务map
	 */
	public TreeMap<Integer,List<OaEosFlowItem>> findListByFlowId(OaEosFlowItem oef){
		TreeMap<Integer,List<OaEosFlowItem>> map=new TreeMap<Integer, List<OaEosFlowItem>>();
		List<OaEosFlowItem> itemlist=dao.findListByFlowId(oef);
		for(OaEosFlowItem oeflow:itemlist){
			List<OaEosFlowItem> olist=map.get(oeflow.getSerialNumber());
			if(olist==null){
				List<OaEosFlowItem> olists=new ArrayList<OaEosFlowItem>();
				olists.add(oeflow);
				map.put(oeflow.getSerialNumber(),olists);
			}else{
				olist.add(oeflow);
			}
		}
		return map;
	}

	//找到要处理流程
	public List<OaEosFlowItem> FlowUsers(TreeMap<Integer,List<OaEosFlowItem>> flowmap){
		List<OaEosFlowItem> sendlist=new ArrayList<OaEosFlowItem>();
		if(flowmap!=null){
			boolean issend=false;//找到要处理流程
			for(Integer mg:flowmap.keySet()){
				if(!issend) {
					List<OaEosFlowItem> oeflist = flowmap.get(mg);
					if (oeflist!=null) {
						for (OaEosFlowItem oe : oeflist) {
							if(oe.getStatus()==0){
								sendlist.add(oe);
								issend=true;
							}
						}
					}

				}
			}
		}
		return sendlist;
	}
	/**
	 * 发送微信通知
	 * @param tousers 微信用户id  多个用 \ 隔开
	 * @param msg
	 * @return
	 */
	public boolean sendMsg(String tousers,String msg){
		if(StringUtils.isNotEmpty(tousers)) {
			SendMsg sendmsg = new SendMsg();
			sendmsg.setMsgtype("text");
			sendmsg.setSafe(0);
			Text text = new Text();
			text.setContent(msg);
			sendmsg.setText(text);
			sendmsg.setTouser(tousers);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(sendmsg);
			String gsonuser = null;//推送消息
			try {
				gsonuser = TokenService.post(wxApi.getMessage(wxApi.SendMsgToUser, ""), jsonStr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Token reurntoken = gson.fromJson(gsonuser, Token.class);
			if (reurntoken.getErrcode() != 0) {
				return false;
			}
		}
		return true;
	}
	@Transactional(readOnly = false)
	public void save(OaEosFlowItem oaEosFlowItem) {
		super.save(oaEosFlowItem);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosFlowItem oaEosFlowItem) {
		super.delete(oaEosFlowItem);
	}
	
}