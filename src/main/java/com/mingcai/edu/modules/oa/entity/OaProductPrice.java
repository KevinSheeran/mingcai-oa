/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 产品报价版本Entity
 * @author 坤
 * @version 2018-05-07
 */
public class OaProductPrice extends DataEntity<OaProductPrice> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 文件名称
	private String path;		// 文件路径
	private String format;		// 文件格式
	private String filesize;		// 文件大小
	private String filename;
	public OaProductPrice() {
		super();
	}

	public OaProductPrice(String id){
		super(id);
	}

	@Length(min=1, max=200, message="文件名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}