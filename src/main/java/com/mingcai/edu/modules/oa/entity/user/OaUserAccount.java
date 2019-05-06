/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.user;

import javax.validation.constraints.NotNull;

import com.mingcai.edu.common.utils.weixinApi.WxUsers;
import com.mingcai.edu.modules.oa.entity.eos.OaEosFlowItem;
import com.mingcai.edu.modules.oa.entity.wx.OaWxUsers;
import com.mingcai.edu.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.Date;

/**
 * 用户账户Entity
 * @author kun
 * @version 2019-04-18
 */
public class OaUserAccount extends DataEntity<OaUserAccount> {
	
	private static final long serialVersionUID = 1L;
	private OaUserSalesVolume salesVolumeId;		// 当前申请销售额id
	private Double branchQuota=0.0;		// 每月可支配额
	private Double poolFunds=0.0;		// 资金池
	private Double costIncurred=0.0;		// 当月发生费用
	private Double spareA=0.0;		// 备用字段1
	private Double spareB=0.0;		// 备用字段2
	private Double spareC=0.0;		// 备用字段3
	private String stringA;		// 备用字符1
	private String stringB;		// 备用字符2
	private String stringC;		// 备用字符3
	private Integer flag=0;		// 是否可用(冻结)
	private String flowId;		// 流程id
	private Integer status=0;		// 状态(默认0,1.申请中，2.通过.-1驳回)
	private User user;//当前用户
	private OaWxUsers wxUsers;//当前用户微信
	private OaEosFlowItem flow;
	public String userId;
	private Date beginEndMonth; //结算月份
	public OaUserAccount() {
		super();
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public Date getBeginEndMonth() {
		return beginEndMonth;
	}

	public void setBeginEndMonth(Date beginEndMonth) {
		this.beginEndMonth = beginEndMonth;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public OaEosFlowItem getFlow() {
		return flow;
	}

	public void setFlow(OaEosFlowItem flow) {
		this.flow = flow;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public OaUserAccount(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OaWxUsers getWxUsers() {
		return wxUsers;
	}

	public void setWxUsers(OaWxUsers wxUsers) {
		this.wxUsers = wxUsers;
	}

	public OaUserSalesVolume getSalesVolumeId() {
		return salesVolumeId;
	}

	public void setSalesVolumeId(OaUserSalesVolume salesVolumeId) {
		this.salesVolumeId = salesVolumeId;
	}

	@NotNull(message="每月可支配额不能为空")
	public Double getBranchQuota() {
		return branchQuota;
	}

	public void setBranchQuota(Double branchQuota) {
		this.branchQuota = branchQuota;
	}

	@NotNull(message="资金池不能为空")
	public Double getPoolFunds() {
		return poolFunds;
	}

	public void setPoolFunds(Double poolFunds) {
		this.poolFunds = poolFunds;
	}

	@NotNull(message="当月发生费用不能为空")
	public Double getCostIncurred() {
		return costIncurred;
	}

	public void setCostIncurred(Double costIncurred) {
		this.costIncurred = costIncurred;
	}

	@NotNull(message="备用字段1不能为空")
	public Double getSpareA() {
		return spareA;
	}

	public void setSpareA(Double spareA) {
		this.spareA = spareA;
	}

	@NotNull(message="备用字段2不能为空")
	public Double getSpareB() {
		return spareB;
	}

	public void setSpareB(Double spareB) {
		this.spareB = spareB;
	}

	@NotNull(message="备用字段3不能为空")
	public Double getSpareC() {
		return spareC;
	}

	public void setSpareC(Double spareC) {
		this.spareC = spareC;
	}
	
	@Length(min=0, max=64, message="备用字符1长度必须介于 0 和 64 之间")
	public String getStringA() {
		return stringA;
	}

	public void setStringA(String stringA) {
		this.stringA = stringA;
	}
	
	@Length(min=0, max=64, message="备用字符2长度必须介于 0 和 64 之间")
	public String getStringB() {
		return stringB;
	}

	public void setStringB(String stringB) {
		this.stringB = stringB;
	}
	
	@Length(min=0, max=64, message="备用字符3长度必须介于 0 和 64 之间")
	public String getStringC() {
		return stringC;
	}

	public void setStringC(String stringC) {
		this.stringC = stringC;
	}
	
	@NotNull(message="是否可用(冻结)不能为空")
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}