<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息管理</title>
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
		<li class="active"><a href="${ctx}/oa/contract/oaContractSupplier/">供应商信息列表</a></li>
		<shiro:hasPermission name="oa:contract:oaContractSupplier:edit"><li><a href="${ctx}/oa/contract/oaContractSupplier/form">供应商信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaContractSupplier" action="${ctx}/oa/contract/oaContractSupplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
				<th>单位名称</th>
				<th>联系人</th>
				<th>联系电话</th>
				<th>联系地址</th>
				<th>银行账号</th>
				<th>开户行</th>
				<th>税号</th>
				<th>可开票点数</th>
				<th>更新者</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oa:contract:oaContractSupplier:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaContractSupplier">
			<tr>
				<td><a href="${ctx}/oa/contract/oaContractSupplier/form?id=${oaContractSupplier.id}">
					${oaContractSupplier.name}
				</a></td>
				<td>
					${oaContractSupplier.contacts}
				</td>
				<td>
					${oaContractSupplier.contactNumber}
				</td>
				<td>
					${oaContractSupplier.address}
				</td>
				<td>
					${oaContractSupplier.bankAccount}
				</td>
				<td>
					${oaContractSupplier.openingBank}
				</td>
				<td>
					${oaContractSupplier.dutyParagraph}
				</td>
				<td>
					${oaContractSupplier.taxPoint}
				</td>
				<td>
					${oaContractSupplier.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${oaContractSupplier.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="oa:contract:oaContractSupplier:edit"><td>
    				<a href="${ctx}/oa/contract/oaContractSupplier/form?id=${oaContractSupplier.id}">修改</a>
					<a href="${ctx}/oa/contract/oaContractSupplier/delete?id=${oaContractSupplier.id}" onclick="return confirmx('确认要删除该供应商信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>