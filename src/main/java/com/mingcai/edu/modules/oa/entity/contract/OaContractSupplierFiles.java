/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.contract;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 供应商产品附件Entity
 * @author 坤
 * @version 2018-03-05
 */
public class OaContractSupplierFiles extends DataEntity<OaContractSupplierFiles> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 文件名称
	private String  parentId;		// 父节点
	private String path;		// 文件路径
	private String type;		// 文件类型 （供应商1，产品2）
	private String format;		// 文件格式
	private String filesize;		// 文件大小
	
	public OaContractSupplierFiles() {
		super();
	}

	public OaContractSupplierFiles(String id){
		super(id);
	}

	@Length(min=1, max=200, message="文件名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Length(min=1, max=200, message="文件路径长度必须介于 1 和 200 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=1, max=64, message="文件类型 （供应商1，产品2）长度必须介于 1 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=64, message="文件格式长度必须介于 0 和 64 之间")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	@Length(min=0, max=64, message="文件大小长度必须介于 0 和 64 之间")
	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	
}