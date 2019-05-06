<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售项目子项管理</title>
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
		<li class="active"><a href="${ctx}/oa/eos/oaEosProItem/">销售项目子项列表</a></li>
		<shiro:hasPermission name="oa:eos:oaEosProItem:edit"><li><a href="${ctx}/oa/eos/oaEosProItem/form">销售项目子项添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaEosProItem" action="${ctx}/oa/eos/oaEosProItem/" method="post" class="breadcrumb form-search">
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
				<th>负责人</th>
				<th>预估金额</th>
				<th>毛利率百分比</th>
				<th>投入估算</th>
				<th>预估完成时间</th>
				<th>支持人员ids</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oa:eos:oaEosProItem:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaEosProItem">
			<tr>
				<td><a href="${ctx}/oa/eos/oaEosProItem/form?id=${oaEosProItem.id}">
					${oaEosProItem.name}
				</a></td>
				<td>
					${oaEosProItem.}
				</td>
				<td>
					${oaEosProItem.estimatedAmount}
				</td>
				<td>
					${oaEosProItem.grossProfitMargin}
				</td>
				<td>
					${oaEosProItem.inputEstimation}
				</td>
				<td>
					<fmt:formatDate value="${oaEosProItem.workloadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaEosProItem.}
				</td>
				<td>
					<fmt:formatDate value="${oaEosProItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="oa:eos:oaEosProItem:edit"><td>
    				<a href="${ctx}/oa/eos/oaEosProItem/form?id=${oaEosProItem.id}">修改</a>
					<a href="${ctx}/oa/eos/oaEosProItem/delete?id=${oaEosProItem.id}" onclick="return confirmx('确认要删除该销售项目子项吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>