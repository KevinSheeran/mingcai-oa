<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售项目子项管理</title>
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
	<form:form id="inputForm" modelAttribute="oaEosProItem" action="${ctx}/oa/eos/oaEosProItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<form:hidden path="eosId" htmlEscape="false" maxlength="200" class="input-xlarge "/>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">编号：</label>
			<div class="controls">
				<form:input path="eosZId" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label">周期：</label>
			<div class="controls">
				<form:input path="proCycle" htmlEscape="false" maxlength="64" class="input-xlarge "/>天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<sys:treeselect id="personLiableUser"  name="personLiableUser.id" value="${oaEosProItem.personLiableUser.id}" labelName="oaEosPro.personLiableUser.name" labelValue="${oaEosProItem.personLiableUser.name}"
								title="负责人" url="/sys/office/treeData?type=3" notAllowSelectRoot="true" notAllowSelectParent="true" /><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预估金额：</label>
			<div class="controls">
				<form:input path="estimatedAmount" htmlEscape="false" maxlength="64" class="input-xlarge "/>元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">毛利率百分比：</label>
			<div class="controls">
				<form:input path="grossProfitMargin" htmlEscape="false" maxlength="64" class="input-xlarge "/>%
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预估完成时间：</label>
			<div class="controls">
				<input name="workloadTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${oaEosProItem.workloadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支持人员：</label>
			<div class="controls">
				<sys:treeselect id="userIds" checked="true" name="userIds" value="${oaEosProItem.userIds}" labelName="users_names" labelValue="${oaEosProItem.users_names}"
								title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass=""/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目主要交付内容：</label>
			<div class="controls">
				<form:textarea path="proContent" htmlEscape="false" rows="4" cssStyle="width: 70%;height: 300px;" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:eos:oaEosPro:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
		</div>
	</form:form>
</body>
</html>