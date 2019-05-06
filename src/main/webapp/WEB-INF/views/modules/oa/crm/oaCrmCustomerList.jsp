<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>联系人管理</title>
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
<fieldset>
	<legend><div style="padding: 10px 10px; text-align: left;position: relative;float: left; "><a href="${ctx}/oa/crm/oaCrmCustomer/">联系人列表</a>&nbsp;&nbsp;<shiro:hasPermission name="oa:crm:oaCrmCustomer:edit"><a class="btn" href="${ctx}/oa/crm/oaCrmCustomer/form"><i class="icon-plus"></i>联系人添加</a></li></shiro:hasPermission></div>
		<div style="float: right;">
		<form:form id="searchForm" modelAttribute="oaCrmCustomer" action="${ctx}/oa/crm/oaCrmCustomer/" method="post" class="breadcrumb form-search" cssStyle="background: transparent;padding-top: 13px;">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form" >
				<li>
					<div class="input-append">
						<input id="name" name="name"  placeholder="联系人姓名" type="text"  value="${oaCrmCustomer.name}" data-msg-required="" class="input-small" style="">
						<a href="javascript:" class="btn" style="">&nbsp;<i class="icon-search" onclick="$('#searchForm').submit();"></i>&nbsp;</a>&nbsp;&nbsp;
					</div>
				</li>
			</ul>
		</form:form>
		</div>
	</legend>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>联系人姓名</th>
				<th>电话</th>
				<th>单位</th>
				<th>更新时间</th>
				<th>描述信息</th>
				<shiro:hasPermission name="oa:crm:oaCrmCustomer:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaCrmCustomer">
			<tr>
				<td><a href="${ctx}/oa/crm/oaCrmCustomer/form?id=${oaCrmCustomer.id}">
					${oaCrmCustomer.name}
				</a></td>
				<td>
						${oaCrmCustomer.phone}
				</td>
				<td>
						${oaCrmCustomer.oaFinanceCompany.name}
				</td>
				<td>
					<fmt:formatDate value="${oaCrmCustomer.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaCrmCustomer.remarks}
				</td>
				<shiro:hasPermission name="oa:crm:oaCrmCustomer:edit"><td>
    				<a href="${ctx}/oa/crm/oaCrmCustomer/form?id=${oaCrmCustomer.id}">修改</a>
					<a href="${ctx}/oa/crm/oaCrmCustomer/delete?id=${oaCrmCustomer.id}" onclick="return confirmx('确认要删除该联系人吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</fieldset>
</body>
</html>