<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目编号管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#inputForm").validate({
                rules: {
                    itemNumber: {remote: "${ctx}/oa/eos/oaEosNumber/checkMobile?oldMobile=${checkMobile.itemNumber}"}
                },
                messages: {
                    itemNumber: {remote: "编号已存在"}
                },
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
	<li class="active"><a href="${ctx}/oa/eos/oaEosNumber">基本类型编号管理</a></li>
	<li ><a href="${ctx}/oa/eos/oaEosNumberUnInfo/">非销售类编号管理</a></li>
</ul>
<br/>
	<form:form id="inputForm" modelAttribute="oaEosNumber" action="${ctx}/oa/eos/oaEosNumber/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目编号：</label>
			<div class="controls">
				<form:input path="itemNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">编号类型：</label>
			<div class="controls">
				<form:select path="itemNumberType" htmlEscape="false" maxlength="64" class="input-xlarge ">
					<form:option value="0">预立项编号</form:option>
					<form:option value="1">立项编号</form:option>
					<form:option value="2">非销售立项编号</form:option>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:eos:oaEosNumber:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>