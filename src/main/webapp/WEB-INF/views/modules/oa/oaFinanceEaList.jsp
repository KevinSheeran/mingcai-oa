<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销管理</title>
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
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaFinanceEa/">待办任务</a></li>
		<li class="active"><a href="${ctx}/oa/oaFinanceEa/list">报销列表</a></li>
		<shiro:hasPermission name="oa:oaFinanceEa:edit"><li><a href="${ctx}/oa/oaFinanceEa/form">报销申请单</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaFinanceEa" action="${ctx}/oa/oaFinanceEa/list" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>报销单号：</label><form:input path="eaNo" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>报销编号</th><th>申请人</th><th>部门</th><th>报销总金额</th><th>申请时间</th><th>审核状态</th></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaFinanceEa">
			<tr>
				<td><a href="${ctx}/oa/oaFinanceEa/form?id=${oaFinanceEa.id}">${oaFinanceEa.eaNo}</a></td>
				<td>${oaFinanceEa.createBy.name}</td>
				<td>${oaFinanceEa.office.name}</td>
				<td>${oaFinanceEa.moneys}</td>
				<td><fmt:formatDate value="${oaFinanceEa.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${oaFinanceEa.status}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
