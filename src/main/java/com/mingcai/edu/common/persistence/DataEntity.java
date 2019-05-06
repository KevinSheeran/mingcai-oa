/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.mingcai.edu.common.persistence;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mingcai.edu.common.utils.TableUpdateLog.LogCompar;
import com.mingcai.edu.common.utils.TableUpdateLog.UnityLog;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mingcai.edu.common.utils.IdGen;
import com.mingcai.edu.modules.sys.entity.User;
import com.mingcai.edu.modules.sys.utils.UserUtils;

/**
 * 数据Entity类
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

	private static final long serialVersionUID = 1L;
	
	protected String remarks;	// 备注
	protected User createBy;	// 创建者
	protected Date createDate;	// 创建日期
	@LogCompar(name = "updateBy")
	protected User updateBy;	// 更新者
	protected Date updateDate;	// 更新日期
	protected String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
	private boolean leader; //是否是领导


	public DataEntity() {
		super();
		this.delFlag = DEL_FLAG_NORMAL;
	}
	
	public DataEntity(String id) {
		super(id);
	}
	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	@Override
	public void preInsert(){
		// 不限制ID为UUID，调用setIsNewRecord()使用自定义ID
		if (!this.isNewRecord){
			setId(IdGen.uuid());
		}
		User user = UserUtils.getUser();
		if (user!=null&&StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	@Override
	public void preUpdate(){
		User user = UserUtils.getUser();
		if (user!=null&&StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
		}
		this.updateDate = new Date();
	}
	
	@Length(min=0, max=2000)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@JsonIgnore
	public User getCreateBy() {
		return createBy;
	}

	public void setCreateBy(User createBy) {
		this.createBy = createBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@JsonIgnore
	public User getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(User updateBy) {
		this.updateBy = updateBy;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@JsonIgnore
	@Length(min=1, max=1)
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}




	//下面记录类修改日志
	public String tableName;

	//region 生成数据修改日志
	public final static String ADD = "新增";
	public final static String UPDATE = "更新";
	public final static String DELETE = "删除";


	public <T extends DataEntity> UnityLog createLog(String unityOperate, T oldObj) throws IllegalAccessException {
		//保存操作日志
		UnityLog unityLog = comparatorObject(unityOperate, oldObj);
		unityLog.setUnityTagId(getId());
		unityLog.setUnityOperateTime(new Date());
		unityLog.setUnityOperator(UserUtils.getUser().getName());
		unityLog.setId(IdGen.uuid());
		return unityLog;
	}

	/**
	 * 比较新老对象的差别
	 *
	 * @param unityOperate
	 * @param oldObj
	 * @return
	 * @throws IllegalAccessException
	 */
	private UnityLog comparatorObject(String unityOperate, Object oldObj) throws IllegalAccessException {
		StringBuilder matter = new StringBuilder();
		StringBuilder content = new StringBuilder();

		if (oldObj != null && UPDATE.equals(unityOperate)) {
			Map<String, Object> oldMap = changeValueToMap(oldObj);
			Map<String, Object> newMap = changeValueToMap(this);
			if (oldMap != null && !oldMap.isEmpty()) {
				for (Map.Entry<String, Object> entry : oldMap.entrySet()) {
					Object oldValue = entry.getValue();
					Object newValue = newMap.get(entry.getKey());
					if (oldValue!=null&&!oldValue.equals(newValue)) {
						matter.append("[").append(entry.getKey()).append("]");
						content.append("[").append(oldValue).append("->").append(newValue).append("]");
					}
				}
			}
		}else if (oldObj != null && DELETE.equals(unityOperate)) {
			Map<String, Object> oldMap = changeValueToMap(oldObj);
			for (Map.Entry<String, Object> entry : oldMap.entrySet()) {
				Object oldValue = entry.getValue();
					matter.append("[").append(entry.getKey()).append("]");
					content.append("[").append(oldValue).append("]");
			}
		} else {
			matter.append("-");
			content.append("-");
		}
		return UnityLog.builder()
				.unityOperate(unityOperate)
				.unityTag(tableName)
				.unityMatter(String.valueOf(matter))
				.unityContent(String.valueOf(content))
				.build();
	}

	/**
	 * 将类对象转换成Map
	 *
	 * @param entity 原对象
	 * @return Map
	 * @throws IllegalAccessException 类型转换时报错
	 */
	private static Map<String, Object> changeValueToMap(Object entity) throws IllegalAccessException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();
			if (PropertyUtils.isReadable(entity, name) && PropertyUtils.isWriteable(entity, name)) {
				if (field.isAnnotationPresent(LogCompar.class)) {
					LogCompar anno = field.getAnnotation(LogCompar.class);
					//获取private对象字段值
					field.setAccessible(true);
					resultMap.put(anno.name(), field.get(entity));
				}
			}
		}
		return resultMap;
	}
}
