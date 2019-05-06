<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品报价版本管理</title>
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
		<li><a href="${ctx}/oa/oaProductPrice/">产品报价版本列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaProductPrice/form?id=${oaProductPrice.id}">产品报价版本<shiro:hasPermission name="oa:oaProductPrice:edit">${not empty oaProductPrice.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaProductPrice:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaProductPrice" enctype="multipart/form-data" action="${ctx}/oa/oaProductPrice/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="filename"/>
		<form:hidden path="path"/>
		<form:hidden path="format"/>
		<form:hidden path="filesize"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">版本：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件路径：</label>
			<div class="controls">
				<input type="file" name='files'><a href='javascript:void(0);' /> 不修改附件不用选择，空着
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaProductPrice:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>