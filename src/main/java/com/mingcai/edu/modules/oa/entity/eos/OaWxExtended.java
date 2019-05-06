/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import com.mingcai.edu.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;

/**
 * 微信报销详情Entity
 * @author 李艺杰
 * @version 2019-03-11
 */
public class OaWxExtended extends DataEntity<OaWxExtended> {
	
	private static final long serialVersionUID = 1L;
	private Double cost;		// 报销金额
	private String state;		// 报销状态
	private String content;	// 报销内容
	private String proId;     //主表id
    private List<User> users;
    private  OaWxExtendedSuper oaWxExtendedSuper;
    private OaEosFlowItem flow;
	private  OaEosProStartItem oaEosProStartItem;
	private  OaEosProItem oaEosProItem;
	private OaWxDepartment oaWxDepartment;
	private  OaEosProUn oaEosProUn;

	private  OaWxBasicClassificationOfReimbursement lx;
	private  OaWxStageOfReimbursement jd;
	private  OaWxBxCorrelationSuper oaWxBxCorrelationSuper;
	private String settlement="0";//是否结算
	public OaWxBasicClassificationOfReimbursement getLx() {
		return lx;
	}

	public void setLx(OaWxBasicClassificationOfReimbursement lx) {
		this.lx = lx;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public OaWxStageOfReimbursement getJd() {
		return jd;
	}

	public void setJd(OaWxStageOfReimbursement jd) {
		this.jd = jd;
	}

	public OaWxBxCorrelationSuper getOaWxBxCorrelationSuper() {
		return oaWxBxCorrelationSuper;
	}

	public void setOaWxBxCorrelationSuper(OaWxBxCorrelationSuper oaWxBxCorrelationSuper) {
		this.oaWxBxCorrelationSuper = oaWxBxCorrelationSuper;
	}

	public OaEosProUn getOaEosProUn() {
		return oaEosProUn;
	}

	public void setOaEosProUn(OaEosProUn oaEosProUn) {
		this.oaEosProUn = oaEosProUn;
	}

	public OaWxDepartment getOaWxDepartment() {
		return oaWxDepartment;
	}

	public void setOaWxDepartment(OaWxDepartment oaWxDepartment) {
		this.oaWxDepartment = oaWxDepartment;
	}

	public OaEosProStartItem getOaEosProStartItem() {
		return oaEosProStartItem;
	}

	public void setOaEosProStartItem(OaEosProStartItem oaEosProStartItem) {
		this.oaEosProStartItem = oaEosProStartItem;
	}

	public OaEosProItem getOaEosProItem() {
		return oaEosProItem;
	}

	public void setOaEosProItem(OaEosProItem oaEosProItem) {
		this.oaEosProItem = oaEosProItem;
	}

	public OaWxExtendedSuper getOaWxExtendedSuper() {
		return oaWxExtendedSuper;
	}

	public void setOaWxExtendedSuper(OaWxExtendedSuper oaWxExtendedSuper) {
		this.oaWxExtendedSuper = oaWxExtendedSuper;
	}

	public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public OaEosFlowItem getFlow() {
        return flow;
    }

    public void setFlow(OaEosFlowItem flow) {
        this.flow = flow;
    }


    @Override
	public String toString() {
		return "OaWxExtended{" +
				"cost=" + cost +
				", state='" + state + '\'' +
				", content='" + content + '\'' +
				", proId='" + proId + '\'' +
				'}';
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public OaWxExtended() {
		super();
	}

	public OaWxExtended(String id){
		super(id);
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}
	
	@Length(min=0, max=100, message="报销类型长度必须介于 0 和 100 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=1000, message="报销内容长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

}