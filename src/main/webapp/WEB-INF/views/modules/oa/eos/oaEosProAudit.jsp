<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售项目立项管理</title>
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
	<style>
		.table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
			background-color: transparent;
		}
	</style>
</head>
<body>
	<form:form id="inputForm" modelAttribute="oaEosPro" action="${ctx}/oa/eos/oaEosPro/audit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
			<fieldset>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
					<th colspan="4" align="left"><h5>请选择审批人员</h5></th>
					</thead>
					<tr>
						<td class="tit">预立项审批人员：</td><td><sys:treeselect id="userIds" checked="true" name="userIds" value="${oaEosPro.userIds}" labelName="oaEosPro.users_names" labelValue="${oaEosPro.users_names}"
																		title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
						</td>
					</tr>

				</table>
			</fieldset>
	</form:form>
</body>
</html>