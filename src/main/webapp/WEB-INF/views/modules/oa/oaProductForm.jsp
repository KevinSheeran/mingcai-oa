<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
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
	<fieldset>
		<legend><div style="padding: 10px 10px; text-align: left "><a href="${ctx}/oa/oaProduct/form?id=${oaProduct.id}">项目信息<shiro:hasPermission name="oa:oaProduct:edit">${not empty oaProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:oaProduct:edit">查看</shiro:lacksPermission></a></div></legend>
	<br/>
	<form:form id="inputForm" modelAttribute="oaProduct" action="${ctx}/oa/oaProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目组用户:</label>
			<div class="controls">
				<sys:treeselect id="oaProductUsers" checked="true" name="oaProduct.users_ids" value="${oaProduct.users_ids}" labelName="oaProduct.users_names" labelValue="${oaProduct.users_names}"
								title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人:</label>
			<div class="controls">
				<sys:treeselect id="updateBy"  name="updateById" value="${oaProduct.updateBy.id}" labelName="oaProduct.updateBy.name" labelValue="${oaProduct.updateBy.name}"
								title="负责人" url="/sys/office/treeData?type=3" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>

	</fieldset>
</body>
</html>