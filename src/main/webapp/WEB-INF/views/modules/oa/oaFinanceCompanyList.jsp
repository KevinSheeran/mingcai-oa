<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单位管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaFinanceCompany/">单位管理列表</a></li>
		<shiro:hasPermission name="oa:oaFinanceCompany:edit"><li><a href="${ctx}/oa/oaFinanceCompany/form">单位管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaFinanceCompany" action="${ctx}/oa/oaFinanceCompany/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>单位编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>单位名称：</label>
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
				<th>单位编号</th>
				<th>单位名称</th>
				<th>区域</th>
				<th>单位描述信息</th>
				<shiro:hasPermission name="oa:oaFinanceCompany:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaFinanceCompany">
			<tr>
				<td><a href="${ctx}/oa/oaFinanceCompany/form?id=${oaFinanceCompany.id}">
					${oaFinanceCompany.code}
				</a></td>
				<td>
					${oaFinanceCompany.name}
				</td>
				<td>
						${oaFinanceCompany.area.name}
				</td>
				<td>
					${oaFinanceCompany.remarks}
				</td>
				<shiro:hasPermission name="oa:oaFinanceCompany:edit"><td>
    				<a href="${ctx}/oa/oaFinanceCompany/form?id=${oaFinanceCompany.id}">修改</a>
					<a href="${ctx}/oa/oaFinanceCompany/delete?id=${oaFinanceCompany.id}" onclick="return confirmx('确认要删除该报销单位管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>