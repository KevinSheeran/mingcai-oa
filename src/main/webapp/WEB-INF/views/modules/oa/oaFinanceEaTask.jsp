<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销任务</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
			
			$(".select2-chosen").each(function(){
			})
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/oaFinanceEa/">待办任务</a></li>
		<li><a href="${ctx}/oa/oaFinanceEa/list">报销列表</a></li>
		<shiro:hasPermission name="oa:oaFinanceEa:edit"><li><a href="${ctx}/oa/oaFinanceEa/form">报销申请单</a></li></shiro:hasPermission>
	</ul>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th>申请人</th>
				<th>申请时间</th>
				<th>当前节点</th>
				<th>任务创建时间</th>
				<th>总金额</th>
				<th>流程状态</th>
				<th>操作</th>
			</tr>
		<tbody>
			<c:forEach items="${tasks}" var="task" varStatus="vs">
				<c:set var="pi" value="${oaFinanceEas[vs.index].processInstance}" />
				<tr id="${oaFinanceEas[vs.index].id }" tid="${task.id}">
					<td>${oaFinanceEas[vs.index].createBy.name}</td>
					<td><fmt:formatDate value="${oaFinanceEas[vs.index].createDate}" type="both"/></td>
					<td>${task.name}</td>
					<td><fmt:formatDate value="${task.createTime}" type="both"/></td>
					<td>${oaFinanceEas[vs.index].moneys}</td>
					<td>${pi.suspended ? "已挂起" : "正常" }；<b title='流程版本号'>V: ${oaFinanceEas[vs.index].processDefinition.version}</b></td>
					<td>
						<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">跟踪</a>
						<c:if test="${not empty task.assignee}">
							<a class="handle" href="${ctx}/oa/oaFinanceEa/detail?tkey=${task.taskDefinitionKey}&id=${oaFinanceEas[vs.index].id}&tid=${task.id}">办理</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
