/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.modules.oa.entity.wx;

import com.mingcai.edu.common.persistence.DataEntity;
import com.mingcai.edu.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

/**
 * 微信用户Entity
 * @author kun
 * @version 2019-03-05
 */
public class OaWxUsers extends DataEntity<OaWxUsers> {
	private Integer errcode;
	private String errmsg;
	private static final long serialVersionUID = 1L;
	private String userid;
	private String name;		// 成员名称
	private String mobile;		// 手机号码
	private Integer[] department;		// 成员所属部门id列表
	private Integer[] order;		// 部门内的排序值，默认为0。数量必须和department一致
	private String position;		// 职务信息
	private Integer gender;		// 性别
	private String email;		// 邮箱
	private Integer[] is_leader_in_dept;		// 是否为上级
	private String is_leader_in_dept_s;		// 是否为上级
	private String avatar;		// 头像url
	private String telephone;		// 座机
	private String enable;		// 状态
	private String alias;		// 别名
	private String status;		// 激活状态:
	private String qr_code;		// 二维码
	private String external_position;		// 对外职务
	private String address;		// 排序
	private String department_s;
	private String order_s;
	private String departmentId;
	private String qrCode;
	private String pinyin;//全拼
	private User user;
	/*private OaUserAccount account;*/
	/*public OaUserAccount getAccount() {
		return account;
	}

	public void setAccount(OaUserAccount account) {
		this.account = account;
	}*/
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}



	public OaWxUsers() {
		super();
	}


	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public OaWxUsers(String id){
		super(id);
	}

	public String getDepartment_s() {
		return department_s;
	}

	public void setDepartment_s(String department_s) {
		this.department_s = department_s;
	}

	public String getOrder_s() {
		return order_s;
	}

	public void setOrder_s(String order_s) {
		this.order_s = order_s;
	}


	@Length(min=0, max=100, message="成员名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="手机号码长度必须介于 0 和 64 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer[] getDepartment() {
		return department;
	}

	public void setDepartment(Integer[] department) {
		this.department = department;
	}

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Integer[] getOrder() {
		return order;
	}

	public void setOrder(Integer[] order) {
		this.order = order;
	}

	public Integer[] getIs_leader_in_dept() {
		return is_leader_in_dept;
	}

	public void setIs_leader_in_dept(Integer[] is_leader_in_dept) {
		this.is_leader_in_dept = is_leader_in_dept;
	}

	public String getIs_leader_in_dept_s() {
		return is_leader_in_dept_s;
	}

	public void setIs_leader_in_dept_s(String is_leader_in_dept_s) {
		this.is_leader_in_dept_s = is_leader_in_dept_s;
	}

	@Length(min=0, max=64, message="职务信息长度必须介于 0 和 64 之间")
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	@Length(min=0, max=64, message="性别长度必须介于 0 和 64 之间")
	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	@Length(min=0, max=64, message="邮箱长度必须介于 0 和 64 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	@Length(min=0, max=1000, message="头像url长度必须介于 0 和 1000 之间")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Length(min=0, max=64, message="座机长度必须介于 0 和 64 之间")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=64, message="状态长度必须介于 0 和 64 之间")
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	@Length(min=0, max=64, message="别名长度必须介于 0 和 64 之间")
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	@Length(min=0, max=6, message="激活状态:长度必须介于 0 和 6 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQr_code() {
		return qr_code;
	}

	public void setQr_code(String qr_code) {
		this.qr_code = qr_code;
	}

	@Length(min=0, max=300, message="排序长度必须介于 0 和 300 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getExternal_position() {
		return external_position;
	}

	public void setExternal_position(String external_position) {
		this.external_position = external_position;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
}