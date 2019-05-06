/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.mingcai.edu.common.persistence.DataEntity;
import com.mingcai.edu.common.utils.DateUtils;
import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.TableUpdateLog.UnityLog;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowDao;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowsDao;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProDao;
import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.service.user.OaUserAccountService;
import com.mingcai.edu.modules.sys.dao.LogDao;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.dao.eos.OaEosProStartDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 立项启动Service
 * @author 坤
 * @version 2019-03-21
 */
@Service
@Transactional(readOnly = true)
public class OaEosProStartService extends CrudService<OaEosProStartDao, OaEosProStart> {
	@Autowired
	OaEosProCplanService oepcs;
	@Autowired
	OaEosProDao prodao;
    @Autowired
    OaEosProStartDao startDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    OaEosFlowsDao oefDao;
    @Autowired
    OaEosFlowItemService oefiService;
    @Autowired
    OaUserAccountService oaUserAccountService;
	public OaEosProStart get(String id) {
		return super.get(id);
	}
	
	public List<OaEosProStart> findList(OaEosProStart oaEosProStart) {
		return super.findList(oaEosProStart);
	}
    public List<OaEosProStart> findListByUser(OaEosProStart oaEosProStart) {
        return dao.findListByUser(oaEosProStart);
    }
    public Page<OaEosProStart> findListByFlowFinishUser(Page<OaEosProStart> page,OaEosProStart oaEosProStart){
        oaEosProStart.setPage(page);
        page.setList(dao.findListByFlowFinishUser(oaEosProStart));
        return page;
    }
	public Page<OaEosProStart> findPage(Page<OaEosProStart> page, OaEosProStart oaEosProStart) {
		return super.findPage(page, oaEosProStart);
	}
	public Page<OaEosProStart> findPcList(Page<OaEosProStart> page, OaEosProStart oaEosProStart) {
		oaEosProStart.setPage(page);
		page.setList(dao.findPcList(oaEosProStart));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(OaEosProStart oaEosProStart, HttpServletRequest request) {
		String[] cplanContent=request.getParameterValues("cplanContent");
		String[] receivablesDate=request.getParameterValues("receivablesDate");
		String[] finishDate=request.getParameterValues("finishDate");
		String[] proportion=request.getParameterValues("proportion");
		String[] planstatus=request.getParameterValues("planstatus");
		OaEosProCplan cplan=new OaEosProCplan();
		cplan.setEosId(oaEosProStart.getId());
		OaEosProStart pstart=dao.get(oaEosProStart.getId());
		oepcs.delete(cplan);
		super.save(oaEosProStart);
		String code=request.getParameter("savecode");
		if("yes".equals(code)) {
			try {
				UnityLog log = oaEosProStart.createLog(DataEntity.UPDATE, pstart);
				logdao.unityinsert(log);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		if(cplanContent!=null)
		for(int i=0;i<cplanContent.length;i++){
			OaEosProCplan plan=new OaEosProCplan();
			plan.setEosId(oaEosProStart.getId());
			plan.setStage(i+"");
			plan.setCplanContent(cplanContent[i]);
			if(finishDate[i]!=null)
			plan.setFinishDate(DateUtils.parseDate(finishDate[i]));
			plan.setReceivablesDate(DateUtils.parseDate(receivablesDate[i]));
			plan.setProportion(Integer.parseInt(proportion[i]));
			plan.setStatus(Integer.parseInt(planstatus[i]));
			oepcs.save(plan);
		}

	}
	@Transactional(readOnly = false)
	public String saveAudit(OaEosProStart oaEosProStart){
		String userid=oaEosProStart.getUserIds();
		String tuserid=oaEosProStart.getTuserIds();
		if(StringUtils.isEmpty(tuserid)||StringUtils.isEmpty(userid)){
			return "请选择审批人员";
		}
		oaEosProStart.setStatus(1);
		OaEosPro pro= prodao.get(oaEosProStart.getId());
		pro.setStatus(4);

        TreeMap<Integer,String> flowmap=new TreeMap<Integer, String>();
        flowmap.put(0,oaEosProStart.getUserIds());
        flowmap.put(1,oaEosProStart.getTuserIds());
        Map<String,Object> paramMap=new HashMap<String, Object>();
        paramMap.put("type",3);
        String content="您有新的立项项目需要审批，请到OA应用确认审批。\n项目："+oaEosProStart.getPro().getName()+"\n申请人："+UserUtils.getUser().getName()+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditprostart?id="+oaEosProStart.getId()+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
        paramMap.put("flowName","立项启动流程");
        Map<String,String> returnMap=new HashMap<String, String>();
        returnMap=oefiService.createFlow(flowmap,paramMap);
        if("false".equals(returnMap.get("success"))){
            return returnMap.get("message");
        }else{
            String flowid=returnMap.get("flowId");
            OaEosFlowItem flows=new OaEosFlowItem();
            flows.setId(flowid);
            oaEosProStart.setFlow(flows);
            returnMap=oefiService.startFlow(flowid,true,content);
            if("false".equals(returnMap.get("success"))){
                return returnMap.get("message");
            }else{
                try{
                    dao.updateAudit(oaEosProStart);
                    prodao.updateAudit(pro);
                }catch (Exception e){
                    System.out.println(e.toString());
                }

            }
        }
		return "success";
	}

	/***
	 * 结算项目
	 */
	@Transactional(readOnly = false)
	public void proEnd(OaEosProStart oaEosProStart){
		try {
			OaEosProStart pstart=dao.get(oaEosProStart.getId());
			UnityLog log = oaEosProStart.createLog(DataEntity.UPDATE, pstart);
			logdao.unityinsert(log);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		oaEosProStart.setSettlement("1");
		super.save(oaEosProStart);
		oaUserAccountService.proEnd(oaEosProStart);
	}
	@Transactional(readOnly = false)
	public boolean AuditPro(String proid,String flowid,String content){
		OaEosProStart oeprostart = super.get(proid);
		String message="您有新的立项项目需要审批，请到OA应用确认审批。\n项目："+oeprostart.getPro().getName()+"\n申请人："+UserUtils.getUser().getName()+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditprostart?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		String finishmessage="项目启动审批完成。\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditprostart?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		Map<String,String> returnmap=oefiService.auditFlow(flowid,true,message,finishmessage,content);
		OaEosPro oepro = prodao.get(proid);
		if(!"true".equals(returnmap.get("success"))) {
			return false;
		}else{
			if("true".equals(returnmap.get("pass"))) {
				oepro.setStatus(5);
				oepro.setProNumber(IdGen.uuid());
				prodao.updateAudit(oepro);
				//预立项完成 添加立项启动数据
				OaEosProStart prostart=oeprostart;
				oeprostart.setStatus(2);
				oeprostart.setCreateDate(new java.util.Date());
				dao.updateAudit(prostart);
				OaEosFlows flow= oefDao.get(prostart.getFlow().getId());
				flow.setStatus(2);
				oefDao.update(flow);
				User u=userDao.get(oeprostart.getPersonLiableUser().getId());
				if(u.getWxUsers()!=null)
				oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户

			}
		}
		return true;
	}
	@Transactional(readOnly = false)
	public boolean AuditProBack(String proid,String flowid,String content){
		User user= UserUtils.getUser();
		if(StringUtils.isEmpty(content)){
			content="无";
		}
		String finishmessage="您的项目启动申请被\""+user.getName()+"\"驳回。\n驳回原因:"+content+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditprostart?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		Map<String,String> returnmap=oefiService.rejectFlow(flowid,content);
		OaEosProStart oeprostart = super.get(proid);
		if(!"true".equals(returnmap.get("success"))) {
			return false;
		}else{
				//立项驳回 添加立项启动数据
				OaEosProStart prostart=oeprostart;
				oeprostart.setStatus(-1);
				dao.updateAudit(prostart);
				User u=userDao.get(oeprostart.getPersonLiableUser().getId());
				if(u.getWxUsers()!=null)
					oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户
		}
		return true;
	}

	public void delete(OaEosProStart oaEosProStart) {
		super.delete(oaEosProStart);
	}
    @Transactional(readOnly = false)
    public void Cancel(OaEosProStart oaEosProStart){
        OaEosFlows flow= oefDao.get(oaEosProStart.getFlow().getId());
        flow.setStatus(-1);
        oefDao.update(flow);
        oaEosProStart.setStatus(0);
        oaEosProStart.setFlow(new OaEosFlowItem());
        dao.updateAudit(oaEosProStart);
        OaEosPro pro= prodao.get(oaEosProStart.getId());
        pro.setStatus(3);
        prodao.updateAudit(pro);

    }

	public void updatamoney(OaEosProStart oaEosPro){
		dao.updatamoney(oaEosPro);
	}
}