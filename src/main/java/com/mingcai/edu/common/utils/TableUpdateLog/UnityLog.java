package com.mingcai.edu.common.utils.TableUpdateLog;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
/**
 * @author printsky
 * @since 2018/08/28
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UnityLog {
    /**
     * id
     */
    private String id;

    /**
     * 数据库表名
     */
    private String unityTag;

    /**
     * 数据库表记录id对应的操作日志
     */
    private String unityTagId;

    /**
     * 操作人
     */
    private String unityOperator;

    /**
     * 操作时间
     */
    private Date unityOperateTime;

    /**
     * 操作类型
     */
    private String unityOperate;

    /**
     * 操作事项
     */
    private String unityMatter;

    /**
     * 操作内容
     */
    private String unityContent;

    public UnityLog(String id, String unityTag, String unityTagId, String unityOperator, Date unityOperateTime, String unityOperate, String unityMatter, String unityContent) {
        this.id = id;
        this.unityTag = unityTag;
        this.unityTagId = unityTagId;
        this.unityOperator = unityOperator;
        this.unityOperateTime = unityOperateTime;
        this.unityOperate = unityOperate;
        this.unityMatter = unityMatter;
        this.unityContent = unityContent;
    }

    public UnityLog(UnityLogBuilder builder) {
        this.unityTag = builder.unityTag;
        this.unityTagId = builder.unityTagId;
        this.unityOperator = builder.unityOperator;
        this.unityOperate = builder.unityOperate;
        this.unityMatter = builder.unityMatter;
        this.unityContent = builder.unityContent;
    }

    public UnityLog() {
        super();
    }

    /**
     * id
     *
     * @return id id
     */
    public String getId() {
        return id;
    }

    /**
     * id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 数据库表名
     *
     * @return unity_tag 数据库表名
     */
    public String getUnityTag() {
        return unityTag;
    }

    /**
     * 数据库表名
     *
     * @param unityTag 数据库表名
     */
    public void setUnityTag(String unityTag) {
        this.unityTag = unityTag == null ? null : unityTag.trim();
    }

    /**
     * 数据库表记录id对应的操作日志
     *
     * @return unity_tag_id 数据库表记录id对应的操作日志
     */
    public String getUnityTagId() {
        return unityTagId;
    }

    /**
     * 数据库表记录id对应的操作日志
     *
     * @param unityTagId 数据库表记录id对应的操作日志
     */
    public void setUnityTagId(String unityTagId) {
        this.unityTagId = unityTagId;
    }

    /**
     * 操作人
     *
     * @return unity_operator 操作人
     */
    public String getUnityOperator() {
        return unityOperator;
    }

    /**
     * 操作人
     *
     * @param unityOperator 操作人
     */
    public void setUnityOperator(String unityOperator) {
        this.unityOperator = unityOperator == null ? null : unityOperator.trim();
    }

    /**
     * 操作时间
     *
     * @return unity_operate_time 操作时间
     */
    public Date getUnityOperateTime() {
        return unityOperateTime;
    }

    /**
     * 操作时间
     *
     * @param unityOperateTime 操作时间
     */
    public void setUnityOperateTime(Date unityOperateTime) {
        this.unityOperateTime = unityOperateTime;
    }

    /**
     * 操作类型
     *
     * @return unity_operate 操作类型
     */
    public String getUnityOperate() {
        return unityOperate;
    }

    /**
     * 操作类型
     *
     * @param unityOperate 操作类型
     */
    public void setUnityOperate(String unityOperate) {
        this.unityOperate = unityOperate == null ? null : unityOperate.trim();
    }

    /**
     * 操作事项
     *
     * @return unity_matter 操作事项
     */
    public String getUnityMatter() {
        return unityMatter;
    }

    /**
     * 操作事项
     *
     * @param unityMatter 操作事项
     */
    public void setUnityMatter(String unityMatter) {
        this.unityMatter = unityMatter == null ? null : unityMatter.trim();
    }

    /**
     * 操作内容
     *
     * @return unity_content 操作内容
     */
    public String getUnityContent() {
        return unityContent;
    }

    /**
     * 操作内容
     *
     * @param unityContent 操作内容
     */
    public void setUnityContent(String unityContent) {
        this.unityContent = unityContent == null ? null : unityContent.trim();
    }

    public void setIdAndOperator(String unityTagId, String unityOperator) {
        this.unityTagId = unityTagId;
        this.unityOperator = unityOperator;
    }


    public static UnityLogBuilder builder() {
        return new UnityLogBuilder();
    }

    public static class UnityLogBuilder {
        private String unityTag;
        private String unityTagId;
        private String unityOperator;
        private String unityOperate;
        private String unityMatter;
        private String unityContent;

        public UnityLogBuilder unityTag(String unityTag) {
            this.unityTag = unityTag;
            return this;
        }

        public UnityLogBuilder unityTagId(String unityTagId) {
            this.unityTagId = unityTagId;
            return this;
        }

        public UnityLogBuilder unityOperator(String unityOperator) {
            this.unityOperator = unityOperator;
            return this;
        }

        public UnityLogBuilder unityOperate(String unityOperate) {
            this.unityOperate = unityOperate;
            return this;
        }

        public UnityLogBuilder unityMatter(String unityMatter) {
            this.unityMatter = unityMatter;
            return this;
        }

        public UnityLogBuilder unityContent(String unityContent) {
            this.unityContent = unityContent;
            return this;
        }

        public UnityLog build() {
            return new UnityLog(this);
        }
    }
}