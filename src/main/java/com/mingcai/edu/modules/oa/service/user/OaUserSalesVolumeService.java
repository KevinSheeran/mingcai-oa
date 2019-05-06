/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowsDao;
import com.mingcai.edu.modules.oa.dao.user.OaUserAccountDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowItem;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccount;
import com.mingcai.edu.modules.oa.service.eos.OaEosFlowItemService;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.user.OaUserSalesVolume;
import com.mingcai.edu.modules.oa.dao.user.OaUserSalesVolumeDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 申请销售额Service
 * @author kun
 * @version 2019-04-18
 */
@Service
@Transactional(readOnly = true)
public class OaUserSalesVolumeService extends CrudService<OaUserSalesVolumeDao, OaUserSalesVolume> {
	@Autowired
	private UserDao userDao;
	@Autowired
	OaEosFlowsDao oefDao;
	@Autowired
	OaUserAccountDao accountDao;
	@Autowired
	OaEosFlowItemService oefiService;
	public OaUserSalesVolume get(String id) {
		return super.get(id);
	}
	
	public List<OaUserSalesVolume> findList(OaUserSalesVolume oaUserSalesVolume) {
		return super.findList(oaUserSalesVolume);
	}
	
	public Page<OaUserSalesVolume> findPage(Page<OaUserSalesVolume> page, OaUserSalesVolume oaUserSalesVolume) {
		return super.findPage(page, oaUserSalesVolume);
	}
	public OaUserSalesVolume getByUser(OaUserSalesVolume oaUserSalesVolume){

		return dao.getByUser(oaUserSalesVolume);
	}

	@Transactional(readOnly = false)
	public String save(OaUserSalesVolume oaUserSalesVolume,User user) {
		oaUserSalesVolume.preInsert();
		if(user==null){
			return "请选择审批人员";
		}
		OaUserAccount account=new OaUserAccount();
		OaUserAccount getaccount=accountDao.get(UserUtils.getUser().getId());
		if(getaccount==null){
			account.preInsert();
			account.setId(UserUtils.getUser().getId());
		}else{
			account=getaccount;
		}
		account.setSalesVolumeId(oaUserSalesVolume);
		account.setStatus(1);
		oaUserSalesVolume.setStatus(1);
		TreeMap<Integer,String> flowmap=new TreeMap<Integer, String>();
		flowmap.put(0,user.getId());
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("type",5);
		String content="您有新的销售额申请需要审批，请到OA应用确认审批。\n申请人:"+UserUtils.getUser().getName()+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2faccountApply?id="+account.getId()+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		paramMap.put("flowName","销售额申请流程");
		Map<String,String> returnMap=new HashMap<String, String>();
		returnMap=oefiService.createFlow(flowmap,paramMap);
		if("false".equals(returnMap.get("success"))){
			return returnMap.get("message");
		}else{
			String flowid=returnMap.get("flowId");
			oaUserSalesVolume.setFlowId(flowid);
			account.setFlowId(flowid);
			returnMap=oefiService.startFlow(flowid,true,content);
			if("false".equals(returnMap.get("success"))){
				return returnMap.get("message");
			}else{
					dao.insert(oaUserSalesVolume);
					if(getaccount!=null) {
						Double money = Double.parseDouble(oaUserSalesVolume.getSalesVolume());
						account.setBranchQuota((money * Double.parseDouble(oaUserSalesVolume.getUotaRatio())) / 100);
						account.preUpdate();
						accountDao.update(account);
					}else{
						Double money = Double.parseDouble(oaUserSalesVolume.getSalesVolume());
						account.setBranchQuota((money * Double.parseDouble(oaUserSalesVolume.getUotaRatio())) / 100);
						accountDao.insert(account);
					}
			}
		}
		return "申请已经提交";
	}
	
	@Transactional(readOnly = false)
	public void delete(OaUserSalesVolume oaUserSalesVolume) {
		super.delete(oaUserSalesVolume);
	}
	
}