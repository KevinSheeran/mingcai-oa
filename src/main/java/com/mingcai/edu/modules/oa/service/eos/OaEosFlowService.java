/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.common.utils.weixinApi.Token;
import com.mingcai.edu.common.utils.weixinApi.TokenService;
import com.mingcai.edu.common.wx.SendMsg;
import com.mingcai.edu.common.wx.Text;
import com.mingcai.edu.common.wx.wxApi;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlow;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowDao;

/**
 * 流程Service
 * @author kun
 * @version 2019-03-08
 */
@Service
@Transactional(readOnly = true)
public class OaEosFlowService extends CrudService<OaEosFlowDao, OaEosFlow> {
	@Autowired
	private UserDao userDao;
	@Autowired
	private OaEosProDao prodao;
	public OaEosFlow get(String id) {
		return super.get(id);
	}

	//获取请求流程任务map
	public TreeMap<Integer,List<OaEosFlow>> findListByEosId(OaEosFlow oef){
		TreeMap<Integer,List<OaEosFlow>> map=new TreeMap<Integer, List<OaEosFlow>>();
		List<OaEosFlow> oeflist=dao.findListByEosId(oef);
		for(OaEosFlow oeflow:oeflist){
			List<OaEosFlow> olist=map.get(oeflow.getSerialNumber());
			if(olist==null){
				List<OaEosFlow> olists=new ArrayList<OaEosFlow>();
				olists.add(oeflow);
				map.put(oeflow.getSerialNumber(),olists);
			}else{
				olist.add(oeflow);
			}
		}
		return map;
	}
	//找到要处理流程
	public List<OaEosFlow> FlowUsers(TreeMap<Integer,List<OaEosFlow>> flowmap){
        List<OaEosFlow> sendlist=new ArrayList<OaEosFlow>();
		if(flowmap!=null){
			boolean issend=false;//找到要处理流程
			for(Integer mg:flowmap.keySet()){
				if(!issend) {
					List<OaEosFlow> oeflist = flowmap.get(mg);
					if (oeflist!=null) {
						for (OaEosFlow oe : oeflist) {
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
    //执行流程 发送信息
    @Transactional(readOnly = false)
    public boolean sendMsg(List<OaEosFlow> sendlist,String msg){
        if(sendlist!=null) {
                String touser = "";
                String toparty = "";
                for (OaEosFlow oe : sendlist) {
                    User user = userDao.get(oe.getUser().getId());
                    if (user.getWxUsers() != null) {
                        touser += user.getWxUsers().getUserid() + "|";
                        oe.setSendStatus(1);
                        oe.setSendContent(msg);
                        super.save(oe);
                    }
                }
                SendMsg sendmsg = new SendMsg();
                sendmsg.setMsgtype("text");
                sendmsg.setSafe(0);
                Text text = new Text();
                text.setContent(msg);
                sendmsg.setText(text);
                sendmsg.setTouser(touser);
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
	public List<OaEosFlow> findList(OaEosFlow oaEosFlow) {
		return super.findList(oaEosFlow);
	}
	
	public Page<OaEosFlow> findPage(Page<OaEosFlow> page, OaEosFlow oaEosFlow) {
		return super.findPage(page, oaEosFlow);
	}


	@Transactional(readOnly = false)
	public void delete(OaEosFlow oaEosFlow) {
		super.delete(oaEosFlow);
	}
	
}