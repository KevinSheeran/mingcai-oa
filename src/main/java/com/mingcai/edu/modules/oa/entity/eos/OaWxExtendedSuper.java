/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.modules.oa.entity.wx.OaWxDepartment;
import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.*;

/**
 * 报销主表Entity
 * @author 李艺杰
 * @version 2019-03-23
 */
public class OaWxExtendedSuper extends DataEntity<OaWxExtendedSuper> {
	
	private static final long serialVersionUID = 1L;
	private String state;		// 状态(-1已驳回，0默认(未审核)，1审核通过)
	private OaEosFlowItem flow;		// 流程id
	private String proId;		// 项目id
	private String appropriation;		// 确定财务是否拨款（-1审核未通过，0未拨款,1已拨款）
	private  String rbsType;
	private String enclosure;		// 附件
	private OaEosPro pro;
	private List<Map> mapList=new ArrayList<Map>();
	private List<OaWxExtended> list=new ArrayList<OaWxExtended>();
	public List<Map> getMapList() {
		return mapList;
	}
	private String proItemType;//0.预立项项目，1.立项项目,2.非销售项目
	private OaWxDepartment oaWxDepartment;
	private OaEosProUn oaEosProUn;
	private String userId;
	private Map<String,Object> objmap= new HashMap<String, Object>();
	private boolean cw=false; //是否为财务查询
	private String starttime;
	private String endtime;
	public String getUserId() {
		return userId;
	}

	public Map<String, Object> getObjmap() {
		return objmap;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public boolean isCw() {
		return cw;
	}

	public void setCw(boolean cw) {
		this.cw = cw;
	}

	public void setObjmap(Map<String, Object> objmap) {
		this.objmap = objmap;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRbsType() {
		return rbsType;
	}

	public void setRbsType(String rbsType) {
		this.rbsType = rbsType;
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

	public String getProItemType() {
		return proItemType;
	}

	public void setProItemType(String proItemType) {
		this.proItemType = proItemType;
	}

	public void setMapList(List<Map> mapList) {
		this.mapList = mapList;
	}

	public OaWxExtendedSuper() {
		super();
	}

	public OaWxExtendedSuper(String id){
		super(id);
	}

	public List<OaWxExtended> getList() {
		return list;
	}

	public void setList(List<OaWxExtended> list) {
		this.list = list;
	}

	@Length(min=0, max=100, message="状态(-1已驳回，0默认(未审核)，1审核通过)长度必须介于 0 和 100 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public OaEosFlowItem getFlow() {
		return flow;
	}

	public void setFlow(OaEosFlowItem flow) {
		this.flow = flow;
	}

	@Length(min=0, max=64, message="子项目长度必须介于 0 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Length(min=0, max=100, message="确定财务是否拨款（-1审核未通过，0未拨款,1已拨款）长度必须介于 0 和 100 之间")
	public String getAppropriation() {
		return appropriation;
	}

	public void setAppropriation(String appropriation) {
		this.appropriation = appropriation;
	}
	
	@Length(min=0, max=255, message="附件长度必须介于 0 和 255 之间")
	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public OaEosPro getPro() {
		return pro;
	}

	public void setPro(OaEosPro pro) {
		this.pro = pro;
	}
}