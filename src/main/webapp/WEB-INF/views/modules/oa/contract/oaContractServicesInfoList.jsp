<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>劳务合同管理</title>
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
		<li class="active"><a href="${ctx}/oa/contract/oaContractServicesInfo/">劳务合同列表</a></li>
		<shiro:hasPermission name="oa:contract:oaContractServicesInfo:edit"><li><a href="${ctx}/oa/contract/oaContractServicesInfo/form">劳务合同添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaContractServicesInfo" action="${ctx}/oa/contract/oaContractServicesInfo/" method="post" class="breadcrumb form-search">
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
				<th>项目名称</th>
				<th>合同人员</th>
				<th>联系电话</th>
				<th>银行账号</th>
				<th>税号</th>
				<th>验收时间节点</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>采购合同合同项id</th>
				<shiro:hasPermission name="oa:contract:oaContractServicesInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaContractServicesInfo">
			<tr>
				<td><a href="${ctx}/oa/contract/oaContractServicesInfo/form?id=${oaContractServicesInfo.id}">
					${oaContractServicesInfo.name}
				</a></td>
				<td>
					${oaContractServicesInfo.productName}
				</td>
				<td>
					${oaContractServicesInfo.}
				</td>
				<td>
					${oaContractServicesInfo.contactNumber}
				</td>
				<td>
					${oaContractServicesInfo.bankAccount}
				</td>
				<td>
					${oaContractServicesInfo.dutyParagraph}
				</td>
				<td>
					<fmt:formatDate value="${oaContractServicesInfo.timeNode}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${oaContractServicesInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaContractServicesInfo.remarks}
				</td>
				<td>
					${oaContractServicesInfo.termId}
				</td>
				<shiro:hasPermission name="oa:contract:oaContractServicesInfo:edit"><td>
    				<a href="${ctx}/oa/contract/oaContractServicesInfo/form?id=${oaContractServicesInfo.id}">修改</a>
					<a href="${ctx}/oa/contract/oaContractServicesInfo/delete?id=${oaContractServicesInfo.id}" onclick="return confirmx('确认要删除该劳务合同吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>