/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.mingcai.edu.common.persistence.DataEntity;
import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.TableUpdateLog.UnityLog;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowsDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlows;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProUn;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProUnDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 非销售项目立项Service
 * @author kun
 * @version 2019-03-27
 */
@Service
@Transactional(rollbackFor = Exception.class ,readOnly = true)
public class OaEosProUnService extends CrudService<OaEosProUnDao, OaEosProUn> {
	@Autowired
	OaEosFlowItemService oefiService;
	@Autowired
	private UserDao userDao;
	@Autowired
	OaEosFlowsDao oefDao;
	@Override
	public OaEosProUn get(String id) {
		return super.get(id);
	}
	
	@Override
	public List<OaEosProUn> findList(OaEosProUn oaEosProUn) {
		return super.findList(oaEosProUn);
	}
	
	@Override
	public Page<OaEosProUn> findPage(Page<OaEosProUn> page, OaEosProUn oaEosProUn) {
		return super.findPage(page, oaEosProUn);
	}
	public List<OaEosProUn> findListByUser(OaEosProUn oaEosProUn) {
		return dao.findListByUser(oaEosProUn);
	}
	public Page<OaEosProUn> findListByFlowFinishUser(Page<OaEosProUn> page,OaEosProUn oaEosPro){
		oaEosPro.setPage(page);
		page.setList(dao.findListByFlowFinishUser(oaEosPro));
		return page;
	}
	@Transactional(rollbackFor = Exception.class ,readOnly = false)
	public void save(OaEosProUn oaEosProUn,HttpServletRequest request) {
		String code=request.getParameter("savecode");
		if("yes".equals(code)) {
			try {
				OaEosProUn od=dao.get(oaEosProUn.getId());
				UnityLog log = oaEosProUn.createLog(DataEntity.UPDATE, od);
				logdao.unityinsert(log);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		super.save(oaEosProUn);
	}
	/**
	 * 保存流程
	 */

	@Transactional(rollbackFor = Exception.class ,readOnly = false)
	public String saveAudit(OaEosProUn oaEosPro) {
		oaEosPro.setStatus(1);
		if(StringUtils.isNotEmpty(oaEosPro.getUserIds())){
			TreeMap<Integer,String> flowmap=new TreeMap<Integer, String>();
			flowmap.put(0,oaEosPro.getUserIds());
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("type",4);
			String content="您有新的非销售立项项目需要审批，请到OA应用确认审批。\n项目："+oaEosPro.getName()+"\n申请人："+UserUtils.getUser().getName()+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditproinfo?id="+oaEosPro.getId()+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
			paramMap.put("flowName","非销售立项流程");
			Map<String,String> returnMap=new HashMap<String, String>();
			returnMap=oefiService.createFlow(flowmap,paramMap);
			if("false".equals(returnMap.get("success"))){
				return returnMap.get("message");
			}else{
				String flowid=returnMap.get("flowId");
				OaEosFlowItem flows=new OaEosFlowItem();
				flows.setId(flowid);
				oaEosPro.setFlow(flows);
				returnMap=oefiService.startFlow(flowid,true,content);
				if("false".equals(returnMap.get("success"))){
					return returnMap.get("message");
				}else{
					try{
						dao.updateAudit(oaEosPro);
					}catch (Exception e){
						System.out.println(e.toString());
					}

				}

			}


		}
		return "success";
	}
	@Transactional(rollbackFor = Exception.class ,readOnly = false)
	public boolean AuditPro(String proid,String flowid,String content){
		OaEosProUn oeprostart = super.get(proid);
		String message="您有新的非销售立项项目需要审批，请到OA应用确认审批。\n项目："+oeprostart.getName()+"\n申请人："+UserUtils.getUser().getName()+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditproinfo?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		String finishmessage="您的非销售项目申请已通过。\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditproinfo?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		Map<String,String> returnmap=oefiService.auditFlow(flowid,true,message,finishmessage,content);
		if(!"true".equals(returnmap.get("success"))) {
			return false;
		}else{
			if("true".equals(returnmap.get("pass"))) {
				oeprostart.setStatus(2);
				oeprostart.setProNumber(IdGen.uuid());
				dao.updateAudit(oeprostart);
				OaEosFlows flow= oefDao.get(oeprostart.getFlow().getId());
				flow.setStatus(2);
				oefDao.update(flow);
				User u=userDao.get(oeprostart.getPersonLiableUser().getId());
				if(u.getWxUsers()!=null) {
					oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户
				}
			}
		}
		return true;
	}
	@Transactional(rollbackFor = Exception.class ,readOnly = false)
	public boolean AuditProBack(String proid,String flowid,String content){
		User user= UserUtils.getUser();
		if(StringUtils.isEmpty(content)){
			content="无";
		}
		String finishmessage="您的非销售项目审批被\""+user.getName()+"\"驳回。\n驳回原因:"+content+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditproinfo?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		Map<String,String> returnmap=oefiService.rejectFlow(flowid,content);
		OaEosProUn oeprostart = super.get(proid);
		if(!"true".equals(returnmap.get("success"))) {
			return false;
		}else{
				oeprostart.setStatus(-1);
				dao.updateAudit(oeprostart);
				User u=userDao.get(oeprostart.getPersonLiableUser().getId());
				if(u.getWxUsers()!=null) {
					oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户
				}
		}
		return true;
	}

	@Transactional(rollbackFor = Exception.class ,readOnly = false)
	@Override
	public void delete(OaEosProUn oaEosProUn) {
		super.delete(oaEosProUn);
	}
	@Transactional(rollbackFor = Exception.class ,readOnly = false)
	public void Cancel(OaEosProUn oaEosProUn){
		OaEosFlows flow= oefDao.get(oaEosProUn.getFlow().getId());
		flow.setStatus(-1);
		oefDao.update(flow);
		oaEosProUn.setStatus(0);
		oaEosProUn.setFlow(new OaEosFlowItem());
		dao.updateAudit(oaEosProUn);
	}
	public OaEosProUn  selByprozid(OaEosProUn oaEosProUn){
		return dao.selByprozid(oaEosProUn);
	}

	public void updatamoney(OaEosProUn oaEosPro){
		dao.updatamoney(oaEosPro);
	}
}