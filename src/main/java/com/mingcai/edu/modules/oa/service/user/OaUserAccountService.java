/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.service.user;

import java.util.List;
import java.util.Map;

import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.common.utils.StringUtils;
import com.mingcai.edu.modules.oa.dao.eos.OaEosFlowsDao;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowItem;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlows;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStart;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccountUserLog;
import com.mingcai.edu.modules.oa.entity.user.OaUserSalesVolume;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import com.mingcai.edu.modules.oa.service.eos.OaEosFlowItemService;
import com.mingcai.edu.modules.oa.service.wx.OaWxUsersService;
import com.mingcai.edu.modules.sys.dao.UserDao;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mingcai.edu.common.persistence.Page;
import com.mingcai.edu.common.service.CrudService;
import com.mingcai.edu.modules.oa.entity.user.OaUserAccount;
import com.mingcai.edu.modules.oa.dao.user.OaUserAccountDao;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户账户Service
 * @author kun
 * @version 2019-04-18
 */
@Service
@Transactional(readOnly = true)
public class OaUserAccountService extends CrudService<OaUserAccountDao, OaUserAccount> {
	@Autowired
	OaEosFlowsDao oefDao;
	@Autowired
	OaEosFlowItemService oefiService;
	@Autowired
	OaUserAccountDao accountDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private OaWxUsersService oaWxUsersService;
	@Autowired
	private OaUserSalesVolumeService osvservice;
	@Autowired
	private OaUserAccountUserLogService oaUserAccountUserLogService;
	/***
	 * 销售额比例
	 */
	@Value("${uotaRatio}")
	protected String uotaRatio;
	public OaUserAccount get(String id) {
		return super.get(id);
	}
	
	public List<OaUserAccount> findList(OaUserAccount oaUserAccount) {
		return super.findList(oaUserAccount);
	}
	
	public Page<OaUserAccount> findPage(Page<OaUserAccount> page, OaUserAccount oaUserAccount) {
		return super.findPage(page, oaUserAccount);
	}

	public List<OaUserAccount> findListByUser( OaUserAccount oaUserAccount){
        return dao.findListByUser(oaUserAccount);
    }
	public Page<OaUserAccount> findListByFlowFinishUser(Page<OaUserAccount> page,OaUserAccount oaUserAccount){
		oaUserAccount.setPage(page);
		page.setList(dao.findListByFlowFinishUser(oaUserAccount));
		return page;
	}

	@Transactional(readOnly = false)
	public void save(OaUserAccount oaUserAccount) {
		super.save(oaUserAccount);

	}
	@Transactional(readOnly = false)
	public void saveAccount(OaWxUsers oaWxUsers,HttpServletRequest request) {
		List<OaWxUsers> page = oaWxUsersService.findList(oaWxUsers);
		for(OaWxUsers user:page){
			if(user.getUser()!=null&&StringUtils.isNotEmpty(user.getUser().getId())){
				String userid=user.getUser().getId();
				String account=request.getParameter(userid);
				if(StringUtils.isNotEmpty(account)) {
					OaUserAccount useraccount = this.get(userid);
					OaUserSalesVolume oaUserSalesVolume = new OaUserSalesVolume();
					oaUserSalesVolume.setSalesVolume(account);
					oaUserSalesVolume.setUotaRatio(uotaRatio);
					osvservice.save(oaUserSalesVolume);
					if (useraccount != null) {
						useraccount.setSalesVolumeId(oaUserSalesVolume);
						Double money = Double.parseDouble(oaUserSalesVolume.getSalesVolume());
						useraccount.setBranchQuota((money * Double.parseDouble(oaUserSalesVolume.getUotaRatio())) / 100);
						useraccount.preUpdate();
						accountDao.update(useraccount);
					} else {
						useraccount = new OaUserAccount();
						useraccount.preInsert();
						useraccount.setId(userid);
						useraccount.setSalesVolumeId(oaUserSalesVolume);
						useraccount.setStatus(1);
						oaUserSalesVolume.setStatus(1);
						Double money = Double.parseDouble(oaUserSalesVolume.getSalesVolume());
						useraccount.setBranchQuota((money * Double.parseDouble(oaUserSalesVolume.getUotaRatio())) / 100);
						accountDao.insert(useraccount);
					}
				}
			}
		}
	}
	@Transactional(readOnly = false)
	public void Cancel(OaUserAccount oaUserAccount) {
		OaEosFlows flow= oefDao.get(oaUserAccount.getFlowId());
		if(flow!=null) {
			flow.setStatus(-1);
			oefDao.update(flow);
			oaUserAccount.setStatus(0);
			oaUserAccount.setFlowId(null);
			this.save(oaUserAccount);
		}
	}
	@Transactional(readOnly = false)
	public boolean AuditPro(String proid,String flowid,String content){
        OaUserAccount account = super.get(proid);
		String message="您有新的销售额申请需要审批，请到OA应用确认审批。\n申请人："+account.getUser().getWxUsers().getName()+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2faccountApply?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击这里</a>";
		String finishmessage="您的申请审批已通过。\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2faccountApply?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		Map<String,String> returnmap=oefiService.auditFlow(flowid,true,message,finishmessage,content);
		if(!"true".equals(returnmap.get("success"))) {
			return false;
		}else{
			if("true".equals(returnmap.get("pass"))) {
				account.setStatus(2);
				this.save(account);
				User u=userDao.get(account.getCreateBy().getId());
				if(u.getWxUsers()!=null)
					oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程执行完成通知用户
			}
		}
		return true;
	}
	@Transactional(readOnly = false)
	public boolean AuditBack(String proid,String flowid,String content){
		User user= UserUtils.getUser();
		if(StringUtils.isEmpty(content)){
            content="无";
        }
		String finishmessage="您的销售额申请被\""+user.getName()+"\"驳回。\n驳回原因:"+content+"\n<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww4ef5a724c1534989&redirect_uri=http%3a%2f%2fwww.mingcaiedu.com%3a8088%2foa%2fweixin%2faccountApply?id="+proid+"&response_type=code&scope=snsapi_base&state=#wechat_redirect\">点击查看</a>";
		Map<String,String> returnmap=oefiService.rejectFlow(flowid,content);
		OaUserAccount account = super.get(proid);
		if(!"true".equals(returnmap.get("success"))) {
			return false;
		}else{
			account.setStatus(-1);
			this.save(account);
			User u=userDao.get(account.getCreateBy().getId());
			if(u.getWxUsers()!=null)
				oefiService.sendMsg(u.getWxUsers().getUserid(), finishmessage);//流程驳回完成通知用户
		}
		return true;
	}
	@Transactional(readOnly = false)
	public void delete(OaUserAccount oaUserAccount) {
		super.delete(oaUserAccount);
	}

	/***
	 * 销售报销调整个人账户额度 新加报销额请传正数,取消类请传负数
	 * @param money
	 * @param userid
	 * @return
	 */
	@Transactional(readOnly = false)
	public boolean AccountAdd(Double money,String userid,String exid){
		OaUserAccount account = super.get(userid);
		if(account!=null){
			if(money>0) {//报销
				if (account.getCostIncurred() + money - account.getBranchQuota() >= 0) {//透支
					if (account.getCostIncurred() > account.getBranchQuota()) {//已经透支 直接从资金池扣除
						account.setPoolFunds(account.getPoolFunds() - money);
					} else {//透支溢出部分从资金池扣除
						Double ab=account.getBranchQuota()-account.getCostIncurred();
						Double sub=account.getPoolFunds() - (money - ab);
						account.setPoolFunds( sub);
					}
				}
			}else {//撤销
				if(account.getCostIncurred()>=account.getBranchQuota()) {//透支
					if(account.getCostIncurred()+money<account.getBranchQuota()){//透支溢出部分从资金池扣除
						Double ab=account.getBranchQuota()-account.getCostIncurred();
						Double sub=account.getPoolFunds() - ab;
						account.setPoolFunds(sub);
					}else{
						account.setPoolFunds(account.getPoolFunds() - money);
					}

				}
			}
			account.setCostIncurred(account.getCostIncurred() + money);
			super.save(account);
			OaUserAccountUserLog log=new OaUserAccountUserLog();
			log.setExtendedId(exid);
			log.setUserId(account.getId());
			log.setBeginEndMonth(account.getBeginEndMonth());
			log.setBranchQuota(account.getBranchQuota());
			log.setCostIncurred(account.getCostIncurred());
			log.setFlag(account.getFlag());
			log.setPoolFunds(account.getPoolFunds());
			log.setSalesVolumeId(account.getSalesVolumeId().getId());
			log.setSpareA(account.getSpareA());
			log.setSpareB(account.getSpareB());
			log.setSpareC(account.getSpareC());
			log.setStringA(account.getStringA());
			log.setStringB(account.getStringB());
			log.setStringC(account.getStringC());
			log.setMoney(money);
			oaUserAccountUserLogService.save(log);
		}
		return true;
	}

	/***
	 * 结算销售额
	 * @param start
	 * @return
	 */
	public boolean proEnd(OaEosProStart start){
		double money=start.getContractAmount()*Integer.parseInt(uotaRatio)/100;
		OaUserAccount account = super.get(start.getPro().getPersonLiableUser().getId());
		if(account==null){
			OaUserSalesVolume oaUserSalesVolume = new OaUserSalesVolume();
			oaUserSalesVolume.setSalesVolume("0");
			oaUserSalesVolume.setUotaRatio(uotaRatio);
			osvservice.save(oaUserSalesVolume);
			account=new OaUserAccount();
			account.preInsert();
			account.setId(start.getPro().getPersonLiableUser().getId());
			account.setStatus(1);
			account.setSalesVolumeId(oaUserSalesVolume);
			account.setPoolFunds(money);
			dao.insert(account);
		}else {
			account.setPoolFunds(account.getPoolFunds() + money);
			super.save(account);
		}
		return true;
	}
	
}