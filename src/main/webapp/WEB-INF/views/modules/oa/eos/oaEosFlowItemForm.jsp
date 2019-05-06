<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>流程项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/eos/oaEosFlowItem/">流程项列表</a></li>
		<li class="active"><a href="${ctx}/oa/eos/oaEosFlowItem/form?id=${oaEosFlowItem.id}">流程项<shiro:hasPermission name="oa:eos:oaEosFlowItem:edit">${not empty oaEosFlowItem.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:eos:oaEosFlowItem:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaEosFlowItem" action="${ctx}/oa/eos/oaEosFlowItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">创建项id：</label>
			<div class="controls">
				<form:input path="flowId" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批者id：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id" value="${oaEosFlowItem.user.id}" labelName="user.name" labelValue="${oaEosFlowItem.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批状态(0,1完成,-1驳回)：</label>
			<div class="controls">
				<form:input path="status" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批者意见，建议：</label>
			<div class="controls">
				<form:textarea path="content" htmlEscape="false" rows="4" maxlength="3000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程排序：</label>
			<div class="controls">
				<form:input path="order" htmlEscape="false" maxlength="6" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程序号：</label>
			<div class="controls">
				<form:input path="serialNumber" htmlEscape="false" maxlength="6" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消息推送状态 0未推送，1送达：</label>
			<div class="controls">
				<form:input path="sendStatus" htmlEscape="false" maxlength="10" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推送文本内容：</label>
			<div class="controls">
				<form:input path="sendContent" htmlEscape="false" maxlength="5000" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:eos:oaEosFlowItem:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>