/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.eos;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;

import com.mingcai.edu.common.config.Global;
import com.mingcai.edu.common.persistence.DataEntity;
import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.common.utils.PageData;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.common.utils.TableUpdateLog.UnityLog;
import com.mingcai.edu.modules.oa.dao.eos.*;
import com.mingcai.edu.modules.oa.entity.eos.*;
import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.oa.service.wx.OaWxDepartmentService;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;

import javax.servlet.http.HttpServletRequest;

/**
 * 销售项目立项Service
 * @author kun
 * @version 2019-03-08
 */
@Service
@Transactional(readOnly = true)
public class OaEosProService extends CrudService<OaEosProDao, OaEosPro> {
    @Autowired
	OaEosFlowsDao oefDao;
	@Autowired
	OaEosFlowItemService oefiService;
	@Autowired
	OaEosProStartDao startDao;
	@Autowired
	private UserDao userDao;
    @Autowired
    private OaEosProStartItemDao oaEosProStartItemDao;
    @Autowired
    private OaEosProItemService oaEosProItemService;
    @Autowired
    private OaWxDepartmentService oaWxDepartmentService;
    @Autowired
    private OaEosProUnService oaEosProUnService;
	public OaEosPro get(String id) {
		return super.get(id);
	}
	
	public List<OaEosPro> findList(OaEosPro oaEosPro) {
		return super.findList(oaEosPro);
	}
	public List<OaEosPro> findListByUser(OaEosPro oaEosPro) {
		return dao.findListByUser(oaEosPro);
	}
	 public Page<OaEosPro> findListByFlowFinishUser(Page<OaEosPro> page,OaEosPro oaEosPro){
			 oaEosPro.setPage(page);
			 page.setList(dao.findListByFlowFinishUser(oaEosPro));
			return page;
	 }
	public List<OaEosPro> findListByPersonLiableUser(OaEosPro oaEosPro) {
		return dao.findListByPersonLiableUser(oaEosPro);
	}

	public Page<OaEosPro> findPage(Page<OaEosPro> page, OaEosPro oaEosPro) {
		return super.findPage(page, oaEosPro);
	}
	public PageData index(PageData pd){
		return dao.index(pd);
	}
	public PageData procount(PageData pd){
		return dao.procount(pd);
	}

	//保存流程
	@Transactional(readOnly = false)
	public String saveAudit(OaEosPro oaEosPro) {
		oaEosPro.setStatus(2);
		if(StringUtils.isNotEmpty(oaEosPro.getUserIds())){
			TreeMap<Integer,String> flowmap=new TreeMap<Integer, String>();
			flowmap.put(0,oaEosPro.getUserIds());
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("type",1);
			String content="您有新的预立项项目需要审批，请到OA应用确认审批。\n项目："+oaEosPro.getName()+"\n申请人："+UserUtils.getUser().getName()+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditpro?id="+oaEosPro.getId()+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
			paramMap.put("flowName","预立项流程");
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
	@Transactional(readOnly = false)
	public String saveToUser(OaEosPro oaEosPro){
		OaEosProStart start=startDao.get(oaEosPro.getId());
		start.setPersonLiableUser(oaEosPro.getPersonLiableUser());
		startDao.update(start);
		return "success";
	}
	@Transactional(readOnly = false)
	public void Cancel(OaEosPro oaEosPro){
		OaEosFlows flow= oefDao.get(oaEosPro.getFlow().getId());
		flow.setStatus(-1);
		oefDao.update(flow);
		oaEosPro.setStatus(0);
		oaEosPro.setFlow(new OaEosFlowItem());
		dao.updateAudit(oaEosPro);
	}
	@Transactional(readOnly = false)
	public boolean AuditPro(String proid,String flowid,String content){
		OaEosPro oepro = super.get(proid);
		String message="您有新的预立项项目需要审批，请到OA应用确认审批。\n项目："+oepro.getName()+"\n申请人："+UserUtils.getUser().getName()+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditpro?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		String finishmessage="您的预立项项目审批已通过。\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditpro?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		Map<String,String> returnmap=oefiService.auditFlow(flowid,true,message,finishmessage,content);
		if(!"true".equals(returnmap.get("success"))) {
			return false;
		}else{
			if("true".equals(returnmap.get("pass"))) {
				oepro.setStatus(3);
				oepro.setPaNumber(IdGen.uuid());
				dao.updateAudit(oepro);
				//预立项完成 添加立项启动数据
				OaEosProStart prostart=new OaEosProStart();
				prostart.setId(oepro.getId());
				startDao.insert(prostart);
				User u=userDao.get(oepro.getPersonLiableUser().getId());
				if(u.getWxUsers()!=null)
				oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户
				this.getAllPro();
			}
		}
		return true;
	}
	@Transactional(readOnly = false)
	public boolean AuditProBack(String proid,String flowid,String content){
		User user=UserUtils.getUser();
		if(StringUtils.isEmpty(content)){
			content="无";
		}
		String finishmessage="您的预立项项目被\""+user.getName()+"\"驳回。\n驳回原因:"+content+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2fauditpro?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		Map<String,String> returnmap=oefiService.rejectFlow(flowid,content);
		OaEosPro oepro = super.get(proid);
		if(!"true".equals(returnmap.get("success"))) {
			return false;
		}else{
			oepro.setStatus(-2);
			dao.updateAudit(oepro);
			User u=userDao.get(oepro.getPersonLiableUser().getId());
			if(u.getWxUsers()!=null)
				oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程驳回完成通知用户
		}
		return true;
	}

	public String SeendMsg(String id){

	    return "";
    }
	@Transactional(readOnly = false)
	public void save(OaEosPro oaEosPro, HttpServletRequest request) {
		String code=request.getParameter("savecode");
		if("yes".equals(code)){
			OaEosPro oldpro=this.get(oaEosPro.getId());
			try {
				UnityLog log = oaEosPro.createLog(DataEntity.UPDATE, oldpro);
				logdao.unityinsert(log);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			String number=oaEosPro.getPaNumber();
			String pronumber=oaEosPro.getProNumber();
			oaEosPro=this.get(oaEosPro.getId());
			oaEosPro.setPaNumber(number);
			oaEosPro.setProNumber(pronumber);
			dao.updateAudit(oaEosPro);

		}else {
			super.save(oaEosPro);
		}
		this.getAllPro();
	}
	
	@Transactional(readOnly = false)
	public void delete(OaEosPro oaEosPro) {
		super.delete(oaEosPro);
	}
	public  List<OaEosPro> findAllList(){
		return dao.findAllList();
	}
	public OaEosPro  selByprozid(OaEosPro oaEosPro){
		return dao.selByprozid(oaEosPro);
	}

	public void getAllPro(){
        TreeMap<String, OaEosPro> map = new TreeMap<String, OaEosPro>();
          List<OaEosPro>  arraylist=this.findAllList();
        if(arraylist.size()>0) {
            for (OaEosPro pro : arraylist) {
                if (StringUtils.isNotEmpty(pro.getPaNumber()) && StringUtils.isEmpty(pro.getProNumber())) {
                    OaEosProItem item = new OaEosProItem();
                    item.setEosId(pro.getId());
                    pro.setType(1);
                    List<OaEosProItem> list = oaEosProItemService.findList(item);
                    pro.setOaEosProItemList(list);
                }
                if (StringUtils.isNotEmpty(pro.getProNumber())) {
                    OaEosProStartItem startiemt = new OaEosProStartItem();
                    startiemt.setEosId(pro.getId());
                    pro.setType(2);
                    List<OaEosProStartItem> list = oaEosProStartItemDao.findByIdList(startiemt);
                    pro.setOaEosProStartItemList(list);
                }
                map.put(pro.getId(), pro);
                if(pro.getOaEosProTimetotal()!=null)
                pro.setTotal(pro.getOaEosProTimetotal().getPsTotal()+pro.getOaEosProTimetotal().getSaleTotal()+pro.getOaEosProTimetotal().getRdTotal());
            }
        }
        OaEosProUn proun=new OaEosProUn();
        proun.setLeader(true);
		proun.setStatus(2);
        List<OaEosProUn> prounlist=oaEosProUnService.findList(proun);
        for(OaEosProUn un:prounlist){
            if(StringUtils.isNotEmpty(un.getProNumber())){
                OaEosPro pro=new OaEosPro();
                pro.setId(un.getId());
                pro.setType(3);
                pro.setProNumber(un.getProNumber());
                pro.setName(un.getName());
                pro.setOaEosProTimetotal(un.getOaEosProTimetotal());
                arraylist.add(pro);
                map.put(pro.getId(), pro);
				if(pro.getOaEosProTimetotal()!=null)
				pro.setTotal(un.getOaEosProTimetotal().getPsTotal()+un.getOaEosProTimetotal().getSaleTotal()+un.getOaEosProTimetotal().getRdTotal());
            }
        }
		//排序
		Collections.sort(arraylist);
		Global.setArraylist(arraylist);
        Global.setTreeMap(map);
        TreeMap<String,OaWxDepartment> map1=new TreeMap<String, OaWxDepartment>();
        List<OaWxDepartment> list = oaWxDepartmentService.findList(null);
        if (list.size()>0){
            for (OaWxDepartment item : list) {
                map1.put(item.getId(),item);
            }
        }
        Global.setDepartmentMap(map1);
        TreeMap<String, OaEosProUn> oaEosProUnMap=new TreeMap<String, OaEosProUn>();
        List<OaEosProUn> list1 = prounlist;
        for (OaEosProUn itemss:list1){
            OaEosProStartItem startiemt = new OaEosProStartItem();
            startiemt.setEosId(itemss.getId());
            itemss.setOaEosProStartItemList(oaEosProStartItemDao.findByIdList(startiemt));
            oaEosProUnMap.put(itemss.getId(),itemss);
        }
        Global.setOaEosProUnMap(oaEosProUnMap);
    }
	public void updatamoney(OaEosPro oaEosPro){
		dao.updatamoney(oaEosPro);
	}
}