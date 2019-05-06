<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>oa_wx_extended_super管理</title>
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
		<li class="active"><a href="${ctx}/oa/eos/oaWxBxCorrelationSuper/">oa_wx_extended_super列表</a></li>
		<shiro:hasPermission name="oa:eos:oaWxBxCorrelationSuper:edit"><li><a href="${ctx}/oa/eos/oaWxBxCorrelationSuper/form">oa_wx_extended_super添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaWxBxCorrelationSuper" action="${ctx}/oa/eos/oaWxBxCorrelationSuper/" method="post" class="breadcrumb form-search">
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
				<th>描述信息</th>
				<shiro:hasPermission name="oa:eos:oaWxBxCorrelationSuper:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaWxBxCorrelationSuper">
			<tr>
				<td><a href="${ctx}/oa/eos/oaWxBxCorrelationSuper/form?id=${oaWxBxCorrelationSuper.id}">
					<fmt:formatDate value="${oaWxBxCorrelationSuper.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${oaWxBxCorrelationSuper.remarks}
				</td>
				<shiro:hasPermission name="oa:eos:oaWxBxCorrelationSuper:edit"><td>
    				<a href="${ctx}/oa/eos/oaWxBxCorrelationSuper/form?id=${oaWxBxCorrelationSuper.id}">修改</a>
					<a href="${ctx}/oa/eos/oaWxBxCorrelationSuper/delete?id=${oaWxBxCorrelationSuper.id}" onclick="return confirmx('确认要删除该oa_wx_extended_super吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>