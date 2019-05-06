<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目任务管理</title>
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
	<style>
		.btn-small{
			padding: 3px 8px;
			border-radius: 10px;
			font-size:11px;
			border: 1px solid #DDDDDD;
			margin-left: 10px;
			cursor: pointer;
		}
		.line_a{
			font-size: 16px;
			font-weight: bold;
			color: #333;
		}
		#contentTable th{
				font-size: 18px;
				font-weight: bold;
				padding:15px;
		}
		#contentTable tr td{
			padding: 10px 20px;

		}
	</style>
</head>
<body>
	<form:form id="searchForm" modelAttribute="oaProductTask" action="${ctx}/oa/oaProductTask/toUser" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="taskType" name="taskType" type="hidden" value="task"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li ><label style="margin-left: 0px;text-align: left;width:auto;">名称：</label>
				<div class="input-append">
					<input id="companyName" name="name"  placeholder="任务名称" type="text"  value="${oaProductTask.name}" data-msg-required="" class="input-small" style="">
					<a href="javascript:" class="btn" style="">&nbsp;<i class="icon-search" onclick="$('#searchForm').submit();"></i>&nbsp;</a>&nbsp;&nbsp;
				</div>
				<label style="margin-left: 0px;text-align: left;width:auto;">任务状态：</label>
				<form:select path="taskStatus"  cssStyle="min-width: 100px;" onchange="$('#searchForm').submit();">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('task_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			<li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table  table-bordered table-condensed">
		<thead>
		<tr>
			<th>任务列表&nbsp;&nbsp;&nbsp;&nbsp;
			</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaProductTask">
			<tr>
				<td>
					<a  class="line_a" href="${ctx}/oa/oaProductTask/form?id=${oaProductTask.id}&taskType=task" title="查看任务详情">
						${oaProductTask.name}
					</a>
					<span class=" btn-small " title=""${oaProductTask.productName}">${oaProductTask.productName}</span>
					<c:if test="${oaProductTask.taskStatus==0}"><span class=" btn-small btn-warding">未确认&nbsp;&nbsp;截止日期：<fmt:formatDate value="${oaProductTask.prodcutEndDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></c:if>
					<c:if test="${oaProductTask.taskStatus==1}"><span class=" btn-small btn-primary">已确认&nbsp;&nbsp;<fmt:formatDate value="${oaProductTask.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></c:if>
					<c:if test="${oaProductTask.taskStatus==2}"><span class=" btn-small btn-success">完成&nbsp;&nbsp;<fmt:formatDate value="${oaProductTask.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>