<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销项目管理</title>
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
		<li class="active"><a>项目列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="oaFinanceProduct" action="${ctx}/oa/oaFinanceProduct/proList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>项目编号：</label>
				<form:input path="code" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
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
				<th>项目编号</th>
				<th>单位名称</th>
				<th>项目名称</th>
				<th>项目描述信息</th>
				<shiro:hasPermission name="ooa:contract:oaContractInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaFinanceProduct">
			<tr>
				<td>
					${oaFinanceProduct.code}
				</td>
				<td>
					${oaFinanceProduct.company.name}
				</td>
				<td>
					${oaFinanceProduct.company.name}${oaFinanceProduct.name}
				</td>
				<td>
					${oaFinanceProduct.remarks}
				</td>
				<shiro:hasPermission name="oa:contract:oaContractInfo:edit"><td>
    				<a href="${ctx}/oa/contract/oaContractInfo/mainForm?finProductId=${oaFinanceProduct.id}">合同管理</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>