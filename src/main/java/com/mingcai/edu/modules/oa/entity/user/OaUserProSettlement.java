/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.user;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 项目费用结算Entity
 * @author kun
 * @version 2019-04-18
 */
public class OaUserProSettlement extends DataEntity<OaUserProSettlement> {
	
	private static final long serialVersionUID = 1L;
	private String pro;		// 项目id
	private String personLiableUser;		// 销售
	private Double settlement;		// 结算费用
	private Double expenditure;		// 支出费用
	public OaUserProSettlement() {
		super();
	}

	public OaUserProSettlement(String id){
		super(id);
	}

	@Length(min=1, max=64, message="项目id长度必须介于 1 和 64 之间")
	public String getPro() {
		return pro;
	}

	public void setPro(String pro) {
		this.pro = pro;
	}
	
	@Length(min=1, max=64, message="销售长度必须介于 1 和 64 之间")
	public String getPersonLiableUser() {
		return personLiableUser;
	}

	public void setPersonLiableUser(String personLiableUser) {
		this.personLiableUser = personLiableUser;
	}
	
	@NotNull(message="结算费用不能为空")
	public Double getSettlement() {
		return settlement;
	}

	public void setSettlement(Double settlement) {
		this.settlement = settlement;
	}
	
	@NotNull(message="支出费用不能为空")
	public Double getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(Double expenditure) {
		this.expenditure = expenditure;
	}
	
}