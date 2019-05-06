<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品报价版本管理</title>
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
		<li class="active"><a href="${ctx}/oa/oaProductPrice/">产品报价版本列表</a></li>
		<shiro:hasPermission name="oa:oaProductPrice:edit"><li><a href="${ctx}/oa/oaProductPrice/form">产品报价版本添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaProductPrice" action="${ctx}/oa/oaProductPrice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件或版本：</label>
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
				<th>版本</th>
				<th>文件</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:oaProductPrice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaProductPrice">
			<tr>
				<td><a href="${ctx}/oa/oaProductPrice/form?id=${oaProductPrice.id}">
					${oaProductPrice.name}
				</a></td>
				<td><a href="${ctx}/oa/oaProductResources/download?path=FILES/${oaProductPrice.path}&name=${oaProductPrice.filename}.${oaProductPrice.format}" title="下载">
						${oaProductPrice.filename}
				</a></td>
				<td>
					${oaProductPrice.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${oaProductPrice.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaProductPrice.remarks}
				</td>
				<shiro:hasPermission name="oa:oaProductPrice:edit"><td>
    				<a href="${ctx}/oa/oaProductPrice/form?id=${oaProductPrice.id}">修改</a>
					<a href="${ctx}/oa/oaProductPrice/delete?id=${oaProductPrice.id}" onclick="return confirmx('确认要删除该产品报价版本吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>