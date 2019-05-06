<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>汇总详情管理</title>
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
		<li class="active"><a href="${ctx}/oa/eos/oaEosProTimetotalItem/">汇总详情列表</a></li>
		<shiro:hasPermission name="oa:eos:oaEosProTimetotalItem:edit"><li><a href="${ctx}/oa/eos/oaEosProTimetotalItem/form">汇总详情添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaEosProTimetotalItem" action="${ctx}/oa/eos/oaEosProTimetotalItem/" method="post" class="breadcrumb form-search">
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
				<shiro:hasPermission name="oa:eos:oaEosProTimetotalItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaEosProTimetotalItem">
			<tr>
				<td><a href="${ctx}/oa/eos/oaEosProTimetotalItem/form?id=${oaEosProTimetotalItem.id}">
					<fmt:formatDate value="${oaEosProTimetotalItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${oaEosProTimetotalItem.remarks}
				</td>
				<shiro:hasPermission name="oa:eos:oaEosProTimetotalItem:edit"><td>
    				<a href="${ctx}/oa/eos/oaEosProTimetotalItem/form?id=${oaEosProTimetotalItem.id}">修改</a>
					<a href="${ctx}/oa/eos/oaEosProTimetotalItem/delete?id=${oaEosProTimetotalItem.id}" onclick="return confirmx('确认要删除该汇总详情吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>