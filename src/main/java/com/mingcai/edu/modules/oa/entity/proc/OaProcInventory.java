/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.proc;

import com.mingcai.edu.common.utils.excel.annotation.ExcelField;
import com.mingcai.edu.modules.oa.entity.eos.OaEosProStartItem;
import org.hibernate.validator.constraints.Length;

import com.mingcai.edu.common.persistence.DataEntity;

/**
 * 采购清单Entity
 * @author 谢一郡
 * @version 2019-05-06
 */
public class OaProcInventory extends DataEntity<OaProcInventory> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String specifications;		// 规格
	private String type;		// 产品类型，自有，开发，采购
	private String applyId;		// 申请单ID
	private String price;		// 单价
	private String num;		// 数量
	private String proId;		// 项目ID
	private String proItemId;		// 子项目ID
	private OaEosProStartItem oaEosProStartItem;
	private Boolean flag = false;      //默认全查，否则查子项目为空的项目

	public OaProcInventory() {
		super();
	}
	public OaProcInventory(String id){
		super(id);
	}

	public OaEosProStartItem getOaEosProStartItem() {
		return oaEosProStartItem;
	}

	public void setOaEosProStartItem(OaEosProStartItem oaEosProStartItem) {
		this.oaEosProStartItem = oaEosProStartItem;
	}
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Length(min=1, max=100, message="名称长度必须介于 1 和 100 之间")
	@ExcelField(title="名称",align=2,sort=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1000, message="规格长度必须介于 0 和 1000 之间")
	@ExcelField(title="规格",align=2,sort=30)
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Length(min=1, max=64, message="产品类型，自有，开发，采购长度必须介于 1 和 64 之间")
	@ExcelField(title="产品类型",align=2,sort=40)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=64, message="申请单ID长度必须介于 1 和 64 之间")
	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	@ExcelField(title="单价",align=2,sort=50)
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@ExcelField(title="数量",align=2,sort=60)
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	@Length(min=1, max=64, message="项目ID长度必须介于 1 和 64 之间")
	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}
	
	@Length(min=1, max=64, message="子项目ID长度必须介于 1 和 64 之间")
	public String getProItemId() {
		return proItemId;
	}

	public void setProItemId(String proItemId) {
		this.proItemId = proItemId;
	}
	
}