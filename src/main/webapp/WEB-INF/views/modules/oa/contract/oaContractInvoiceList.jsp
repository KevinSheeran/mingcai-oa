<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	request.setAttribute("vEnter", "\n");
	//奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
	//都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<title>开票信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            initHeight();
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function initHeight() {//调整内容高度
            $(parent.document.getElementsByName(window.name)[0]).height($(document).height());
        }
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="oaContractInvoice" action="${ctx}/oa/contract/oaContractInvoice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${pageSize}"/>
		<form:hidden path="contractId"/>
		<ul class="ul-form">
			<li><label>起始时间：</label>
				<input name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${oaContractInvoice.beginDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
				<label>结束时间：</label>
				<input name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${oaContractInvoice.endDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li style="font-size: 18px;font-weight: bold;">&nbsp;&nbsp;开票总金额:￥${oaContractInvoice.total}元</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>开票单位</th>
				<th>银行账户</th>
				<th>开户行</th>
				<th>纳税人识别号</th>
				<th>发票类型</th>
				<th>税点</th>
				<th>开票金额</th>
				<th>创建者</th>
				<th>开票时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:contract:oaContractInvoice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list}" var="oaContractInvoice">
			<tr>
				<td>
						${oaContractInvoice.companyName}
				</td>
				<td>
					${oaContractInvoice.openingBank}
				</td>
				<td>
					${oaContractInvoice.bankAccount}
				</td>
				<td>
						${oaContractInvoice.dutyParagraph}
				</td>
				<td>
					${oaContractInvoice.type}
				</td>
				<td>
					${oaContractInvoice.taxPoint}
				</td>
				<td>
						￥${oaContractInvoice.money}元
				</td>
				<td>
					${oaContractInvoice.createBy.name}
				</td>

				<td>
					<fmt:formatDate value="${oaContractInvoice.invoiceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${fn:replace(oaContractInvoice.remarks,vEnter,"<br>")}
				</td>
				<shiro:hasPermission name="oa:contract:oaContractInvoice:edit"><td>
    				<a class="btn" href="javascript:void(0)" onclick="parent.addnvoice('${oaContractInvoice.contractId}','${oaContractInvoice.id}');">修改</a>
					<a class="btn" href="${ctx}/oa/contract/oaContractInvoice/delete?id=${oaContractInvoice.id}" onclick="return confirmx('确认要删除该开票信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>