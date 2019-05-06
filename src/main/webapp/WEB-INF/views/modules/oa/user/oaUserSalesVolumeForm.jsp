<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申请销售额管理</title>
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
<br/>
	<form:form id="inputForm" modelAttribute="oaUserSalesVolume" action="${ctx}/oa/user/oaUserSalesVolume/saveAudit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">申请年销售额(元)：</label>
			<div class="controls">
				<form:input path="salesVolume" htmlEscape="false"   class="input-xlarge  required number"/>
				<span class="help-inline"><font color="red">*可用额度将以销售额的(${oaUserSalesVolume.uotaRatio}%)设为每月可用额度</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请描述：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" cssStyle="height: 200px;" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审批人员：</label>
			<div class="controls">
				<img src="${user.wxUsers.avatar}/100" width="60"/>&nbsp;&nbsp;${user.name}
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交申请"/>
		</div>
	</form:form>
</body>
</html>