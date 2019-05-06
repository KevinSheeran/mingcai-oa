<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销项目管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaFinanceProduct/">报销项目管理列表</a></li>
		<shiro:hasPermission name="oa:oaFinanceProduct:edit"><li><a href="${ctx}/oa/oaFinanceProduct/form">报销项目管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaFinanceProduct" action="${ctx}/oa/oaFinanceProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>项目名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目编号</th>
				<th>单位名称</th>
				<th>项目名称</th>
				<th>项目描述信息</th>
				<shiro:hasPermission name="oa:oaFinanceProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaFinanceProduct">
			<tr>
				<td><a href="${ctx}/oa/oaFinanceProduct/form?id=${oaFinanceProduct.id}">
					${oaFinanceProduct.code}
				</a></td>
				<td>
					${oaFinanceProduct.company.name}
				</td>
				<td>
					${oaFinanceProduct.company.name}${oaFinanceProduct.name}
				</td>
				<td>
					${oaFinanceProduct.remarks}
				</td>
				<shiro:hasPermission name="oa:oaFinanceProduct:edit"><td>
    				<a href="${ctx}/oa/oaFinanceProduct/form?id=${oaFinanceProduct.id}">修改</a>
					<a href="${ctx}/oa/oaFinanceProduct/delete?id=${oaFinanceProduct.id}" onclick="return confirmx('确认要删除该报销项目管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>