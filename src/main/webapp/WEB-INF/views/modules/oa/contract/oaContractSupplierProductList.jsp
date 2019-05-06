<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	request.setAttribute("vEnter", "\n");
	//奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
	//都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<title>供应商产品信息管理</title>
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
		<li class="active"><a href="${ctx}/oa/contract/oaContractSupplierProduct/">供应商产品信息列表</a></li>
		<shiro:hasPermission name="oa:contract:oaContractSupplierProduct:edit"><li><a href="${ctx}/oa/contract/oaContractSupplierProduct/form">供应商产品信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaContractSupplierProduct" action="${ctx}/oa/contract/oaContractSupplierProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>供应商：</label><form:select path="supplierId"  cssStyle="min-width: 100px;">
				<form:option value="" label=""/>
				<form:options items="${fns:getSupplr()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
			</form:select></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>标签分类</th>
				<th>规格</th>
				<th>采购单价（税点）</th>
				<th>供应商</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:contract:oaContractSupplierProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaContractSupplierProduct">
			<tr>
				<td><a href="${ctx}/oa/contract/oaContractSupplierProduct/form?id=${oaContractSupplierProduct.id}">
					${oaContractSupplierProduct.name}
				</a></td>
				<Td>${oaContractSupplierProduct.labels}</Td>
				<td  ><div style="height:50px; line-height:25px; overflow:hidden" title="${oaContractSupplierProduct.specifications}">${fn:replace(oaContractSupplierProduct.specifications,vEnter,"<br>")}</div></td>
				<td>
					￥${oaContractSupplierProduct.unitPrice}元(${oaContractSupplierProduct.taxPoint}%)
				</td>
				<td>
					${oaContractSupplierProduct.supplierName}
				</td>
				<td>
					${oaContractSupplierProduct.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${oaContractSupplierProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaContractSupplierProduct.remarks}
				</td>
				<shiro:hasPermission name="oa:contract:oaContractSupplierProduct:edit"><td>
    				<a href="${ctx}/oa/contract/oaContractSupplierProduct/form?id=${oaContractSupplierProduct.id}">修改</a>
					<a href="${ctx}/oa/contract/oaContractSupplierProduct/delete?id=${oaContractSupplierProduct.id}" onclick="return confirmx('确认要删除该供应商产品信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>