<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>新建文件夹</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<tbody>
			<tr>
				<td>名称</td>
				<td>
					<input type="hidden"  name="id" id="id" value="${oaProductResources.id}">
					<input name="name" id="name" value="${oaProductResources.name}">
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>