<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>周任务详情管理</title>
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
		<li class="active"><a href="${ctx}/oa/week/oaWeekWorkInfo/">周任务详情列表</a></li>
		<shiro:hasPermission name="oa:week:oaWeekWorkInfo:edit"><li><a href="${ctx}/oa/week/oaWeekWorkInfo/form">周任务详情添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaWeekWorkInfo" action="${ctx}/oa/week/oaWeekWorkInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>计划目标：</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>计划目标</th>
				<th>更新时间</th>
				<th>备注总结</th>
				<shiro:hasPermission name="oa:week:oaWeekWorkInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaWeekWorkInfo">
			<tr>
				<td><a href="${ctx}/oa/week/oaWeekWorkInfo/form?id=${oaWeekWorkInfo.id}">
					${oaWeekWorkInfo.title}
				</a></td>
				<td>
					<fmt:formatDate value="${oaWeekWorkInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaWeekWorkInfo.remarks}
				</td>
				<shiro:hasPermission name="oa:week:oaWeekWorkInfo:edit"><td>
    				<a href="${ctx}/oa/week/oaWeekWorkInfo/form?id=${oaWeekWorkInfo.id}">修改</a>
					<a href="${ctx}/oa/week/oaWeekWorkInfo/delete?id=${oaWeekWorkInfo.id}" onclick="return confirmx('确认要删除该周任务详情吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>