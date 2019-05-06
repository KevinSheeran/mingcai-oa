/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity;

import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 项目资源Entity
 * @author 江坤
 * @version 2017-12-04
 */
public class OaProductResources extends DataEntity<OaProductResources> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 文件名称
	private String oldPath;		// 文件名称
	private String parentId;		// 所属文件id集合
	private String productId;		// 项目id
	private String pIds;		// 父节点集合
	private String path;		// 文件路径
	private String type;		// 文件类型
	private String format;		// 文件格式
	private String filesize;		// 文件大小
	private String pathName;//路径名称
	private String pathUrl;//资源真实地址
	private boolean image; //是否是图片
	public OaProductResources() {
		super();
	}

	public OaProductResources(String id){
		super(id);
	}

	@Length(min=1, max=200, message="文件名称长度必须介于 1 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getOldPath() {
		return oldPath;
	}

	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}

	@Length(min=1, max=200, message="所属文件id集合长度必须介于 1 和 200 之间")
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Length(min=1, max=64, message="项目id长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getpIds() {
		return pIds;
	}

	public void setpIds(String pIds) {
		this.pIds = pIds;
	}

	@Length(min=1, max=200, message="文件路径长度必须介于 1 和 200 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=1, max=64, message="文件类型长度必须介于 1 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=64, message="文件格式长度必须介于 1 和 64 之间")
	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	@Length(min=1, max=64, message="文件大小长度必须介于 1 和 64 之间")
	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public String getPathUrl() {
		return pathUrl;
	}

	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	public boolean isImage() {
		return image;
	}

	public void setImage(boolean image) {
		this.image = image;
	}
}