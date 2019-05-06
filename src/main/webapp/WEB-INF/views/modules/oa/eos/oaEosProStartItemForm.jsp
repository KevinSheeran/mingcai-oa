<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>立项子项目管理</title>
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
	<form:form id="inputForm" modelAttribute="oaEosProStartItem" action="${ctx}/oa/eos/oaEosProStartItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="eosId"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">编号：</label>
			<div class="controls">
				<form:input path="code" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">负责人：</label>
			<div class="controls">
				<sys:treeselect id="personLiableUser" name="personLiableUser.id" value="${oaEosProStartItem.personLiableUser.id}" labelName="personLiableUser.name" labelValue="${oaEosProStartItem.personLiableUser.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="" allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目主要交付内容：</label>
			<div class="controls">
				<form:textarea path="proContent" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预算金额：</label>
			<div class="controls">
				<form:input path="estimatedAmount" htmlEscape="false" class="input-xlarge  number"/> <span class="help-inline"><font color="red">*</font>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预估完成时间：</label>
			<div class="controls">
				<input   name="workloadTime"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " style="width:163px;"
						 value="<fmt:formatDate value="${oaEosProStartItem.workloadTime}" pattern="yyyy-MM-dd"/>"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">预估偏差时间(天)：</label>
			<div class="controls">
				<form:input path="deviationTime" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际完成时间：</label>
			<div class="controls">
				<input   name="actualTime"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " style="width:163px;"
						 value="<fmt:formatDate value="${oaEosProStartItem.actualTime}" pattern="yyyy-MM-dd"/>"
						 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否完成：</label>
			<div class="controls">
				<form:radiobuttons path="status" items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"></form:radiobuttons>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:eos:oaEosProStart:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>