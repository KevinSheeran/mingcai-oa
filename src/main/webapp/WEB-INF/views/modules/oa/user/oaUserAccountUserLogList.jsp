<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>记录用户账户操作信息管理</title>
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
		<li class="active"><a href="${ctx}/oa/user/oaUserAccountUserLog/">记录用户账户操作信息列表</a></li>
		<shiro:hasPermission name="oa:user:oaUserAccountUserLog:edit"><li><a href="${ctx}/oa/user/oaUserAccountUserLog/form">记录用户账户操作信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaUserAccountUserLog" action="${ctx}/oa/user/oaUserAccountUserLog/" method="post" class="breadcrumb form-search">
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
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:user:oaUserAccountUserLog:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaUserAccountUserLog">
			<tr>
				<td><a href="${ctx}/oa/user/oaUserAccountUserLog/form?id=${oaUserAccountUserLog.id}">
					<fmt:formatDate value="${oaUserAccountUserLog.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${oaUserAccountUserLog.remarks}
				</td>
				<shiro:hasPermission name="oa:user:oaUserAccountUserLog:edit"><td>
    				<a href="${ctx}/oa/user/oaUserAccountUserLog/form?id=${oaUserAccountUserLog.id}">修改</a>
					<a href="${ctx}/oa/user/oaUserAccountUserLog/delete?id=${oaUserAccountUserLog.id}" onclick="return confirmx('确认要删除该记录用户账户操作信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>