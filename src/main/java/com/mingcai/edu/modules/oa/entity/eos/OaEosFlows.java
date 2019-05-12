/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.eos;

import com.mingcai.edu.common.persistence.DataEntity;

import java.util.List;
import java.util.TreeMap;

/**
 * 流程Entity
 * @author 坤
 * @version 2019-03-18
 */
public class OaEosFlows extends DataEntity<OaEosFlows> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 流程名
	private Integer type;		// 类型，1.预立项，2.报销。3.立项，4.
	private Integer status;		// 审批状态(0,1完成,-1驳回)
	private Integer serialNumber;		// 流程数
	private TreeMap<Integer,List<OaEosFlowItem>> treeMap;
	private boolean isSendUser;

	public boolean isSendUser() {
		return isSendUser;
	}

	public void setSendUser(boolean sendUser) {
		isSendUser = sendUser;
	}

	public TreeMap<Integer, List<OaEosFlowItem>> getTreeMap() {
		return treeMap;
	}

	public void setTreeMap(TreeMap<Integer, List<OaEosFlowItem>> treeMap) {
		this.treeMap = treeMap;
	}

	public OaEosFlows() {
		super();
	}

	public OaEosFlows(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}