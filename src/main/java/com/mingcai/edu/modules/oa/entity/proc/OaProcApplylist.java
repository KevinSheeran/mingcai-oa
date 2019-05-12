/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.proc;

import com.mingcai.edu.common.persistence.DataEntity;
import com.mingcai.edu.modules.oa.entity.eos.OaEosPro;
import com.mingcai.edu.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

/**
 * 采购申请单Entity
 * @author 谢一郡
 * @version 2019-05-06
 */
public class OaProcApplylist extends DataEntity<OaProcApplylist> {
	
	private static final long serialVersionUID = 1L;
	private String proId;		// 项目ID
    private String fflowId ;		// 第一个流程ID
	private String sflowId ;		// 第二个流程ID
	private String applyUserId;		// 请购人ID
	private String fStatus = "0";		// 第一个人审批状态(默认0,1.申请中，2.通过.-1驳回)
	private String sStatus = "0";		// 第二个人审批状态(默认0,1.申请中，2.通过.-1驳回)
	private String financeStatus = "0";		// 财务审批状态(默认0,1.申请中，2.通过.-1驳回)
	private User user;       //系统用户
	private OaEosPro oaEosPro; //项目

	public OaProcApplylist() {
		super();
	}

	public OaProcApplylist(String id){
		super(id);
	}

	public String getFflowId() {
        return fflowId;
    }

    public OaProcApplylist setFflowId(String fflowId) {
        this.fflowId = fflowId;
        return this;
    }

    public String getSflowId() {
        return sflowId;
    }

    public OaProcApplylist setSflowId(String sflowId) {
        this.sflowId = sflowId;
        return this;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OaEosPro getOaEosPro() {
		return oaEosPro;
	}

	public void setOaEosPro(OaEosPro oaEosPro) {
		this.oaEosPro = oaEosPro;
	}


	@Length(min=1, max=64, message="项目ID长度必须介于 1 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}


	@Length(min=0, max=64, message="请购人ID长度必须介于 0 和 64 之间")
	public String getApplyUserId() {
		return applyUserId;
	}

	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	
	public String getFStatus() {
		return fStatus;
	}

	public void setFStatus(String fStatus) {
		this.fStatus = fStatus;
	}
	
	public String getSStatus() {
		return sStatus;
	}

	public void setSStatus(String sStatus) {
		this.sStatus = sStatus;
	}
	
	public String getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(String financeStatus) {
		this.financeStatus = financeStatus;
	}
	
}