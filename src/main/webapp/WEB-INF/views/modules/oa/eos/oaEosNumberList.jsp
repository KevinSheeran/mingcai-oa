<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目编号管理</title>
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
	<li class="active"><a href="${ctx}/oa/eos/oaEosNumber">基本类型编号管理</a></li>
	<li ><a href="${ctx}/oa/eos/oaEosNumberUnInfo/">非销售类编号管理</a></li>
</ul>
<sys:message content="${message}"/>
<legend><div style="padding: 10px 10px; text-align: left "><a >编号列表</a>&nbsp;&nbsp;<shiro:hasPermission name="oa:eos:oaEosNumber:edit"><a class="btn " href="${ctx}/oa/eos/oaEosNumber/form"><i class="icon-plus"></i>项目编号添加</a></shiro:hasPermission></div></legend>
<form:form id="searchForm" modelAttribute="oaEosNumber" action="${ctx}/oa/eos/oaEosNumber" method="post" class="breadcrumb form-search">
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
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>项目编号</th>
		<th>编号类型</th>
		<th>关联项目</th>
		<shiro:hasPermission name="oa:eos:oaEosNumber:edit"><th>操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="oaEosNumber">
		<tr>
			<td>${oaEosNumber.itemNumber}</td>
			<td>
					${oaEosNumber.itemNumberType ==0?"预立项编号":oaEosNumber.itemNumberType ==1?"立项编号":oaEosNumber.itemNumberType ==2?"非销售立项编号":"非法编号"}
			</td>
			<c:choose>
				<c:when test="${oaEosNumber.itemNumberType ==0?  !empty oaEosNumber.pro :oaEosNumber.itemNumberType ==1?!empty oaEosNumber.pro:oaEosNumber.itemNumberType ==2? !empty oaEosNumber.un:''}">
					<td>
							${oaEosNumber.itemNumberType ==0? oaEosNumber.pro.name :oaEosNumber.itemNumberType ==1?oaEosNumber.pro.name:oaEosNumber.itemNumberType ==2? oaEosNumber.un.name:"系统异常"}
					</td>
				</c:when>
				<c:otherwise>
					<td>
						未关联项目
					</td>
				</c:otherwise>
			</c:choose>
			<shiro:hasPermission name="oa:eos:oaEosNumber:edit"><td>
				<a href="${ctx}/oa/eos/oaEosNumber/form?id=${oaEosNumber.id}">修改</a>
				<a href="${ctx}/oa/eos/oaEosNumber/delete?id=${oaEosNumber.id}" onclick="return confirmx('确认要删除该项目编号吗？', this.href)">删除</a>
			</td></shiro:hasPermission>

		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>
