<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售产品信息管理</title>
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
		<li class="active"><a href="${ctx}/oa/contract/oaContractProduct/">销售产品信息列表</a></li>
		<shiro:hasPermission name="oa:contract:oaContractProduct:edit"><li><a href="${ctx}/oa/contract/oaContractProduct/form">销售产品信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaContractProduct" action="${ctx}/oa/contract/oaContractProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
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
				<th>名称</th>
				<th>规格</th>
				<th>产品类型</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:contract:oaContractProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaContractProduct">
			<tr>
				<td><a href="${ctx}/oa/contract/oaContractProduct/form?id=${oaContractProduct.id}">
					${oaContractProduct.name}
				</a></td>
				<td>
					${oaContractProduct.specifications}
				</td>
				<td>
					${fns:getDictLabel(oaContractProduct.type, 'pro_type', '')}
				</td>
				<td>
					${oaContractProduct.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${oaContractProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaContractProduct.remarks}
				</td>
				<shiro:hasPermission name="oa:contract:oaContractProduct:edit"><td>
    				<a href="${ctx}/oa/contract/oaContractProduct/form?id=${oaContractProduct.id}">修改</a>
					<a href="${ctx}/oa/contract/oaContractProduct/delete?id=${oaContractProduct.id}" onclick="return confirmx('确认要删除该销售产品信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>