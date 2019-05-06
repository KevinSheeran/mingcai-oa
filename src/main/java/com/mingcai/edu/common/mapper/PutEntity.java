package com.mingcai.edu.common.mapper;

public class PutEntity {
    private String pid; //研发商给运营商分配的唯一标
    private String title; // 邮件标题
    private String content; // 邮件内容
    private String timestamp; // 服务请求时间戳，格式为 13 位毫秒数，比如  1413448045271
    private String taskId; // 任务编号
    private String replay; // 邮件回复地址
    private String displayName; // 发件人
    private String fromEmail; // 发件人邮箱
    private String recipient; // 收件人邮箱
    private String statClick; // 是否统计任务点击
    private String sign; // MD5  签名 long  sign=md5(pid+taskId+ timestamp + key)

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getStatClick() {
        return statClick;
    }

    public void setStatClick(String statClick) {
        this.statClick = statClick;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
