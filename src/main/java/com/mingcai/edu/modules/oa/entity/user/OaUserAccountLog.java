/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.user;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.mingcai.edu.common.persistence.DataEntity;
import java.util.Date;
/**
 * 月结记录Entity
 * @author kun
 * @version 2019-04-18
 */
public class OaUserAccountLog extends DataEntity<OaUserAccountLog> {
	
	private static final long serialVersionUID = 1L;
	private OaUserSalesVolume salesVolumeId;		// 当前申请销售额id
	private Double branchQuota;		// 每月可支配额
	private Double poolFunds;		// 资金池
	private Double costIncurred;		// 当月发生费用
	private Double spareA;		// 备用字段1
	private Double spareB;		// 备用字段2
	private Double spareC;		// 备用字段3
	private String stringA;		// 备用字符1
	private String stringB;		// 备用字符2
	private String stringC;		// 备用字符3
	private Integer flag;		// 是否可用(冻结)
	private Date beginEndMonth; //结算月份
	private String userId; //useraccountid;
	public OaUserAccountLog() {
		super();
	}

	public OaUserAccountLog(String id){
		super(id);
	}

	public Date getBeginEndMonth() {
		return beginEndMonth;
	}

	public void setBeginEndMonth(Date beginEndMonth) {
		this.beginEndMonth = beginEndMonth;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
	
	@Length(min=1, max=1, message="是否可用(冻结)长度必须介于 1 和 1 之间")
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}