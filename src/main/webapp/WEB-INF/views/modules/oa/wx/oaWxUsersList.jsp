<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信用户管理</title>
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
        hs.graphicsDir = '${ctxStatic}/highslide/js/graphics/';
        hs.align = 'center';
        if (hs.addSlideshow) hs.addSlideshow({
            //interval: 10000,
        });
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/oa/wx/oaWxUsers/">微信用户列表</a></li>
		<shiro:hasPermission name="oa:wx:oaWxUsers:edit"><li><a href="${ctx}/oa/wx/oaWxUsers/form">微信用户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="oaWxUsers" action="${ctx}/oa/wx/oaWxUsers/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>成员名称：</label>
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
				<th>成员</th>
				<th>手机号码</th>
				<th>成员所属部门</th>
				<th>职务信息</th>
				<th>性别</th>
				<th>邮箱</th>
				<th>二维码</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaWxUsers">
			<tr>
				<td>
					<img src="${oaWxUsers.avatar}/100" width="50px">&nbsp;&nbsp;
					${oaWxUsers.name}
				</td>
				<td>
					${oaWxUsers.mobile}
				</td>
				<td>
					${oaWxUsers.departmentId}
				</td>
				<td>
					${oaWxUsers.position}
				</td>
				<td>
					<c:if test="${oaWxUsers.gender==1}">男</c:if>
					<c:if test="${oaWxUsers.gender==2}">女</c:if>
				</td>
				<td>
					${oaWxUsers.email}
				</td>

				<td>
					<a href="${oaWxUsers.qrCode}" class="highslide"  onclick="return hs.expand(this)">
						<img src="${oaWxUsers.qrCode}" width="50px">
					</a>
				</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>