<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目跟进管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
		<li class="active"><a href="${ctx}/oa/crm/oaErmProductIng/">项目跟进列表</a></li>
		<shiro:hasPermission name="oa:crm:oaErmProductIng:edit"><li><a href="${ctx}/oa/crm/oaErmProductIng/form">项目跟进添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaErmProductIng" action="${ctx}/oa/crm/oaErmProductIng/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>拜访时间</th>
				<th>拜访内容</th>
				<shiro:hasPermission name="oa:crm:oaErmProductIng:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaErmProductIng">
			<tr>
				<td><a href="${ctx}/oa/crm/oaErmProductIng/form?id=${oaErmProductIng.id}">
					<fmt:formatDate value="${oaErmProductIng.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${oaErmProductIng.remarks}
				</td>
				<shiro:hasPermission name="oa:crm:oaErmProductIng:edit"><td>
    				<a href="${ctx}/oa/crm/oaErmProductIng/form?id=${oaErmProductIng.id}">修改</a>
					<a href="${ctx}/oa/crm/oaErmProductIng/delete?id=${oaErmProductIng.id}" onclick="return confirmx('确认要删除该项目跟进吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>