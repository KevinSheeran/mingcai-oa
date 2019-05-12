<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购清单保存成功管理</title>
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

	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>规格</th>
				<th>产品类型</th>
				<th>单价</th>
				<th>数量</th>
				<shiro:hasPermission name="oa:proc:oaProcInventory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaProcInventory">
			<tr>
				<td><a href="${ctx}/oa/proc/oaProcInventory/form?id=${oaProcInventory.proId}">
					${oaProcInventory.name}
				</a></td>
				<td>
                    ${oaProcInventory.specifications}
				</td>
				<td>
					${oaProcInventory.type}
				</td>
                <td>
                    ${oaProcInventory.price}
                </td>
                <td>
                    ${oaProcInventory.num}
                </td>
				<shiro:hasPermission name="oa:proc:oaProcInventory:edit"><td>
    				<a href="${ctx}/oa/proc/oaProcInventory/form?id=${oaProcInventory.id}">修改</a>
					<a href="${ctx}/oa/proc/oaProcInventory/delete?id=${oaProcInventory.id}" onclick="return confirmx('确认要删除该采购清单保存成功吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>