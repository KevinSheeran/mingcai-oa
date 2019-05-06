<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	request.setAttribute("vEnter", "\n");
	//奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
	//都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<title>采购合同关联管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.items{
			width: 50%;
			float: left;
		}
		.items label{
			margin: 5px 10px;
		}
		.warp{
			padding: 5px 5px 5px 10px;
			color:#a5360f;

		}
	</style>
</head>
<body>

<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>编号</th>
		<th>项（类型）</th>
		<th>规格</th>
		<th>剩余采购量</th>
		<c:if  test="${hide}">
		<th>采购产品</th>
		</c:if>
		<th>采购单价</th>
		<th>采购数量</th>
		<th>总价</th>
        <shiro:hasPermission name="oa:contract:oaContractTerms:pay"><th>操作</th></shiro:hasPermission>
	</tr>
	</thead>
	<c:set var="total" value="0"/>
	<c:forEach items="${page}" var="item" >
		<c:set var="total"  value="${item.number*item.unitPrice+total}"/>
		<tr >
			<td class="tit" style="text-align: left">${item.code}</td>
			<td class="tit" style="text-align: left">${item.name}(${fns:getDictLabel(item.type,"pro_type", "")})</td>
			<td  ><div style="height:50px; line-height:25px; overflow:hidden" title="${item.specifications}">${fn:replace(item.specifications,vEnter,"<br>")}</div></td>
			<td><span id="divnumber_${item.id}">${item.pnumber-item.countNumber-item.number}</span>${item.unit}</td>
			<c:if  test="${hide}">
			<td>
				<select class="select2" name="product_${item.id}" onchange="selectItem(this)" itemid="${item.id}" style="min-width: 100px; max-width: 200px;">
						<option value=""></option>
						<c:forEach items="${productList}" var="option">
							<option value="${option.id}" price="${option.unitPrice}"  <c:if test="${option.id==item.productId}">selected</c:if>>${option.name}</option>
						</c:forEach>
				</select>
			</td>
		</c:if>
			<td class="tit">￥<input name="money_${item.id}"  value="${item.unitPrice}" <c:if  test="${hide}"> readonly </c:if> placeholder="￥&#62;=0" class="input-text" style="width: 60px">元</td>
			<td><input name="number_${item.id}" value="${item.number}" class="input-text" placeholder="n&#60;=${item.pnumber-item.countNumber}" style="width: 60px">${item.unit}</td>
			<td>￥<span id="total_${item.id}" class="totals">${item.number*item.unitPrice}</span>元</td>
            <shiro:hasPermission name="oa:contract:oaContractTerms:pay"><td><a class="btn" onclick="parent.saveTrem('${item.id}',this)" thisnumber="${item.number==null?0:item.number}" maxnumber="${item.pnumber-item.countNumber}">保存</a><a class="btn" onclick="parent.delTrem('${item.id}',this)">删除</a></td></shiro:hasPermission>
		</tr>
	</c:forEach>
</table>
<input type="hidden" id="total" value="<c:out value="${total}"/>">
<select id="select" style="display: none;">
	<option value=""></option>
	<c:forEach items="${productList}" var="option">
		<option value="${option.id}" price="${option.unitPrice}" >${option.name}</option>
	</c:forEach>
</select>
<script type="text/javascript">
    parent.setTotalMoney($("#total").val());
    $(document).ready(function() {
        initHeight();
        $(".select2").select2();
    });
    function selectItem(obj){
     	$("input[name='money_"+$(obj).attr("itemid")+"']").val($(obj).find("option:selected").attr("price"));
	}
    function initHeight() {//调整内容高度
        if($(document).height()>300){
            $(parent.document.getElementsByName(window.name)[0]).height($("#contentTable").height()+20);
        }else{
            $(parent.document.getElementsByName(window.name)[0]).height(300);
        }
    }
</script>
</body>
</html>