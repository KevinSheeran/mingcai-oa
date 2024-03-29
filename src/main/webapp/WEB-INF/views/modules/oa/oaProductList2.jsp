<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
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
	<li class="active"><a href="${ctx}/oa/oaProduct/">项目信息列表</a></li>
	<shiro:hasPermission name="oa:oaProduct:edit"><li><a href="${ctx}/oa/oaProduct/form">项目信息添加</a></li></shiro:hasPermission>
</ul>
<form:form id="searchForm" modelAttribute="oaProduct" action="${ctx}/oa/oaProduct/" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">
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
		<th>项目名称</th>
		<th>更新时间</th>
		<th>负责人</th>
		<th>备注信息</th>
		<shiro:hasPermission name="oa:oaProductTask:view"><th>项目任务</th></shiro:hasPermission>
		<c:if test="${fns:getUser().id==oaProduct.createBy.id||fns:getUser().id==oaProduct.createBy.updateBy.id}">
			<shiro:hasPermission name="oa:oaProduct:edit"><th>操作</th></shiro:hasPermission>
		</c:if>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="oaProduct">
		<tr>
			<td><a href="${ctx}/oa/oaProduct/form?id=${oaProduct.id}">
					${oaProduct.name}
			</a></td>
			<td>
				<fmt:formatDate value="${oaProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
					${oaProduct.updateBy.name}
			</td>
			<td>
					${oaProduct.remarks}
			</td>

			<shiro:hasPermission name="oa:oaProductTask:view">
				<td>
					<a href="${ctx}/oa/oaProductTask?product_id=${oaProduct.id}">查看任务</a>
				</td>
			</shiro:hasPermission>
			<c:if test="${fns:getUser().id==oaProduct.createBy.id||fns:getUser().id==oaProduct.createBy.updateBy.id}">
				<shiro:hasPermission name="oa:oaProduct:edit"><td>
					<a href="${ctx}/oa/oaProduct/form?id=${oaProduct.id}">修改</a>
					<a href="${ctx}/oa/oaProduct/delete?id=${oaProduct.id}" onclick="return confirmx('确认要删除该项目信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</c:if>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>