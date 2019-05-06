<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>立项子项目管理</title>
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
		<li class="active"><a href="${ctx}/oa/eos/oaEosProStartItem/">立项子项目列表</a></li>
		<shiro:hasPermission name="oa:eos:oaEosProStartItem:edit"><li><a href="${ctx}/oa/eos/oaEosProStartItem/form">立项子项目添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaEosProStartItem" action="${ctx}/oa/eos/oaEosProStartItem/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oa:eos:oaEosProStartItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaEosProStartItem">
			<tr>
				<td><a href="${ctx}/oa/eos/oaEosProStartItem/form?id=${oaEosProStartItem.id}">
					${oaEosProStartItem.name}
				</a></td>
				<td>
					<fmt:formatDate value="${oaEosProStartItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="oa:eos:oaEosProStartItem:edit"><td>
    				<a href="${ctx}/oa/eos/oaEosProStartItem/form?id=${oaEosProStartItem.id}">修改</a>
					<a href="${ctx}/oa/eos/oaEosProStartItem/delete?id=${oaEosProStartItem.id}" onclick="return confirmx('确认要删除该立项子项目吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>