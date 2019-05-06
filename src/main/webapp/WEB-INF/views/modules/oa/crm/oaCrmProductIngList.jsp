<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目报备管理</title>
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
		<li class="active"><a href="${ctx}/oa/crm/oaCrmProductIng/">项目报备列表</a></li>
		<shiro:hasPermission name="oa:crm:oaCrmProductIng:edit"><li><a href="${ctx}/oa/crm/oaCrmProductIng/form">项目报备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaCrmProductIng" action="${ctx}/oa/crm/oaCrmProductIng/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="oa:crm:oaCrmProductIng:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaCrmProductIng">
			<tr>
				<td><a href="${ctx}/oa/crm/oaCrmProductIng/form?id=${oaCrmProductIng.id}">
					<fmt:formatDate value="${oaCrmProductIng.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${oaCrmProductIng.remarks}
				</td>
				<shiro:hasPermission name="oa:crm:oaCrmProductIng:edit"><td>
    				<a href="${ctx}/oa/crm/oaCrmProductIng/form?id=${oaCrmProductIng.id}">修改</a>
					<a href="${ctx}/oa/crm/oaCrmProductIng/delete?id=${oaCrmProductIng.id}" onclick="return confirmx('确认要删除该项目报备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>