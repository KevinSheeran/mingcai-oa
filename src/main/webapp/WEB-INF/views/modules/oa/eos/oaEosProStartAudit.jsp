<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
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
	<form:form id="inputForm" modelAttribute="oaEosPro" action="${ctx}/oa/eos/oaEosProStart/audit" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
				<table class="table table-striped table-bordered table-condensed">
					<thead>
					<th colspan="4" align="left"><h5>项目管理中心</h5></th>
					</thead>
					<tr>
						<td class="tit">选择人员：</td><td><sys:treeselect id="userIds" checked="true" name="userIds" value="" labelName="oaEosPro.users_names" labelValue=""
																		title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
						</td>
					</tr>
				</table>
			<table class="table table-striped table-bordered table-condensed">
				<thead>
				<th colspan="4" align="left"><h5>总经理</h5></th>
				</thead>
				<tr>
					<td class="tit">选择人员：</td><td><sys:treeselect id="tuserIds" checked="true" name="tuserIds" value="" labelName="oaEosPro.users_names" labelValue=""
																  title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
				</td>
				</tr>
			</table>
	</form:form>
</body>
</html>