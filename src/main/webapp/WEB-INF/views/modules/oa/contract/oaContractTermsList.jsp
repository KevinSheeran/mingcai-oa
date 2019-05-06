<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	request.setAttribute("vEnter", "\n");
	//奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
	//都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<title>合同项管理</title>
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
	<form:form id="searchForm" modelAttribute="oaContractTerms" action="${ctx}/oa/contract/oaContractTerms/" method="post" class="breadcrumb form-search" cssStyle="display: none;">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="contractId" name="contractId" type="hidden" value="${oaContractTerms.contractId}"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>项（类型）</th>
				<th>规格</th>
				<th>数量(剩余采购量)</th>
				<th>报价单价</th>
				<th>采购合同(数量，单价,总价)</th>
				<th>预算合计（采购合计）</th>
				<shiro:hasPermission name="oa:contract:oaContractTerms:edit">
				<th>操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
		<c:forEach items="${page.list}" var="item" >
			<tr >
				<td class="tit" style="text-align: left">${item.code}<c:if test="${item.number-item.divnumber==0}"><span style="color:#228B22;"><i class="icon-ok color-bar"></i>采购完成</span></c:if></td>
				<td class="tit" style="text-align: left">${item.name}(${fns:getDictLabel(item.type,"pro_type", "")})</td>
				<td  ><div style="height:50px; line-height:25px; overflow:hidden" title="${item.specifications}">${fn:replace(item.specifications,vEnter,"<br>")}</div></td>
				<td>${item.number}${item.unit}(${item.number-item.divnumber}${item.unit})</td>
				<td class="tit">￥${item.unitPrice}元</td>
				<td class="tit">
					<c:if test="${item.termsList==null||item.termsList.size()==0}">
						暂无采购信息
					</c:if>
					<c:forEach items="${item.termsList}" var="pt">
							<div>${pt.pname}${pt.sname}(${pt.number}${item.unit},￥${pt.unitPrice}元,￥${pt.number*pt.unitPrice}元)</div>
					</c:forEach>
				</td>
				<td class="tit">预算￥${item.number*item.unitPrice}元(总计￥${item.sumPrice}元)</td>
				<shiro:hasPermission name="oa:contract:oaContractTerms:edit">
				<td><a class="btn" onclick="parent.updateTrem('${item.contractId}','${item.id}',this)">调整</a><a class="btn" onclick="parent.delTrem('${item.id}',this)">删除</a></td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
        $(document).ready(function() {
            initHeight();
        });
        function initHeight() {//调整内容高度
            $(parent.document.getElementsByName(window.name)[0]).height($(document).height());
        }
        parent.totalPrice("${trem_total_money}","${purchase_total_money}");
	</script>
</body>
</html>