<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>非销售编号管理</title>
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
	<li class="active"><a href="${ctx}/oa/eos/oaEosNumberUn/">非销售编号列表</a></li>
	<li ><a href="${ctx}/oa/eos/oaEosNumberUn/form?id=${oaEosNumberUn.id}">非销售编号<shiro:hasPermission name="oa:eos:oaEosNumber:edit">${not empty oaEosNumberUn.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:eos:oaEosNumber:edit">查看</shiro:lacksPermission></a></li>
</ul><br/>
	<form:form id="searchForm" modelAttribute="oaEosNumberUn" action="${ctx}/oa/eos/oaEosNumberUn/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<form:input path="itemNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>(设定为主编号)项目编号</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:eos:oaEosNumber:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaEosNumberUn">
			<tr>
				<td>
					<label>
						<input type="radio" name="checked" <c:if test="${oaEosNumberUn.checked=='1'}">checked</c:if> value="${oaEosNumberUn.id}">${oaEosNumberUn.itemNumber}
					</label>
				</td>
				<td>
					<fmt:formatDate value="${oaEosNumberUn.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaEosNumberUn.remarks}
				</td>
				<shiro:hasPermission name="oa:eos:oaEosNumber:edit"><td>
    				<a href="${ctx}/oa/eos/oaEosNumberUn/form?id=${oaEosNumberUn.id}">修改</a>
					<a href="${ctx}/oa/eos/oaEosNumberUn/delete?id=${oaEosNumberUn.id}" onclick="return confirmx('确认要删除该非销售编号吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>