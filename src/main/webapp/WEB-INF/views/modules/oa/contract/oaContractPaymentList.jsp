<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>付款情况管理</title>
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
		<li class="active"><a href="${ctx}/oa/contract/oaContractPayment/">付款情况列表</a></li>
		<shiro:hasPermission name="oa:contract:oaContractPayment:edit"><li><a href="${ctx}/oa/contract/oaContractPayment/form">付款情况添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaContractPayment" action="${ctx}/oa/contract/oaContractPayment/" method="post" class="breadcrumb form-search">
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
				<th>合同id</th>
				<th>付款金额</th>
				<th>付款批次</th>
				<th>创建者</th>
				<th>创建时间</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:contract:oaContractPayment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaContractPayment">
			<tr>
				<td><a href="${ctx}/oa/contract/oaContractPayment/form?id=${oaContractPayment.id}">
					${oaContractPayment.contractId}
				</a></td>
				<td>
					${oaContractPayment.money}
				</td>
				<td>
					${fns:getDictLabel(oaContractPayment.batchId, '', '')}
				</td>
				<td>
					${oaContractPayment.createBy.id}
				</td>
				<td>
					<fmt:formatDate value="${oaContractPayment.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaContractPayment.updateBy.id}
				</td>
				<td>
					<fmt:formatDate value="${oaContractPayment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaContractPayment.remarks}
				</td>
				<shiro:hasPermission name="oa:contract:oaContractPayment:edit"><td>
    				<a href="${ctx}/oa/contract/oaContractPayment/form?id=${oaContractPayment.id}">修改</a>
					<a href="${ctx}/oa/contract/oaContractPayment/delete?id=${oaContractPayment.id}" onclick="return confirmx('确认要删除该付款情况吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>