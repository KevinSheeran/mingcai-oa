/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.user;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 记录用户账户操作信息Entity
 * @author 坤
 * @version 2019-04-30
 */
public class OaUserAccountUserLog extends DataEntity<OaUserAccountUserLog> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// user_id
	private String extendedId; //报销单id
	private String salesVolumeId;		// 当前申请销售额id
	private Double branchQuota;		// 每月可支配额
	private Double poolFunds;		// 资金池
	private Double costIncurred;		// 当月发生费用
	private Double money;		// 发生费用
	private Double spareA;		// 备用字段1
	private Double spareB;		// 备用字段2
	private Double spareC;		// 备用字段3
	private String stringA;		// 备用字符1
	private String stringB;		// 备用字符2
	private String stringC;		// 备用字符3
	private Integer flag;		// 是否可用(冻结)
	private Date beginEndMonth;		// 月结月份

	public String getExtendedId() {
		return extendedId;
	}

	public void setExtendedId(String extendedId) {
		this.extendedId = extendedId;
	}

	public OaUserAccountUserLog() {
		super();
	}

	public OaUserAccountUserLog(String id){
		super(id);
	}

	@Length(min=0, max=64, message="user_id长度必须介于 0 和 64 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=1, max=64, message="当前申请销售额id长度必须介于 1 和 64 之间")
	public String getSalesVolumeId() {
		return salesVolumeId;
	}

	public void setSalesVolumeId(String salesVolumeId) {
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
	
	@NotNull(message="发生费用不能为空")
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginEndMonth() {
		return beginEndMonth;
	}

	public void setBeginEndMonth(Date beginEndMonth) {
		this.beginEndMonth = beginEndMonth;
	}
	
}