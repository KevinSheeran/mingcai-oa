<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同项管理</title>
	<meta name="decorator" content="default"/>
</head>
<script>

</script>
<body>
<form:form id="inputForm" modelAttribute="oaContractTerms" action="${ctx}/oa/contract/oaContractTerm/save" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<form:hidden path="contractId"/>
	<form:hidden path="indexs"/>
	<sys:message content="${message}"/>
	<br/>
	<div class="control-group">
		<div class="control-group">
			<label class="control-label">项编号：</label>
			<div class="controls">
				<form:input path="code" readonly="true" htmlEscape="false" maxlength="64" class="input-xlarge "/>采购合同关联项请使用此编号
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge required "/><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">产品规格：</label>
		<div class="controls">
			<form:textarea path="specifications" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">类型：</label>
		<div class="controls">
			<form:radiobuttons path="type" items="${fns:getDictList('pro_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/> <span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">数量：</label>
		<div class="controls">
			<form:input path="number" htmlEscape="false" maxlength="64" class="input-xlarge required"/> <span class="help-inline"><font color="red">*</font> </span>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">单位：</label>
		<div class="controls">
			<form:input path="unit" htmlEscape="false" maxlength="10" class="input-xlarge"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">报价单价：</label>
		<div class="controls">
			<form:input path="unitPrice" htmlEscape="false" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">备注信息：</label>
		<div class="controls">
			<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
		</div>
	</div>
</form:form>
</body>
</html>