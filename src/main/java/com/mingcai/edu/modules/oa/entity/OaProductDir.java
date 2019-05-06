/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.validation.constraints.NotNull;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 项目文件夹Entity
 * @author 江坤
 * @version 2017-12-04
 */
public class OaProductDir extends DataEntity<OaProductDir> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 文件名称
	private String productId;		// 项目id
	private String dirPath;		// 文件夹路径
	private OaProductDir parent;		// 父节点
	
	public OaProductDir() {
		super();
	}

	public OaProductDir(String id){
		super(id);
	}

	@Length(min=1, max=200, message="文件名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=64, message="项目id长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=1, max=300, message="文件夹路径长度必须介于 1 和 300 之间")
	public String getDirPath() {
		return dirPath;
	}

	public void setDirPath(String dirPath) {
		this.dirPath = dirPath;
	}
	
	@JsonBackReference
	@NotNull(message="父节点不能为空")
	public OaProductDir getParent() {
		return parent;
	}

	public void setParent(OaProductDir parent) {
		this.parent = parent;
	}
	
}