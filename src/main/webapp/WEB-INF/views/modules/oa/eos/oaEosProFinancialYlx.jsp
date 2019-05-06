<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目统计</title>
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


    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th><h4>预立项</h4></th>
            <th><h4><span style="color: #929627">总花费金额：</span><fmt:formatNumber value="${oaEosPro.totalSum}" type="currency"/> </h4></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                <h5 style="color: #000000;">子项</h5>

            </th>
            <th>
                <h5 style="color: #000000;">花费金额</h5>

            </th>
        </tr>
        <c:forEach items="${oaEosPro.oaEosProItemList}" var="items">
            <tr>
                <th><a id="${item.id}">${items.name}</a></th>
                <td>${items.inputEstimation ==null?0.0:items.inputEstimation }</td>
            </tr>
        </c:forEach>
        <c:if test="${oaEosPro.realInputEstimation>0}">
        <tr>
            <th><a id="0" onclick="clicks(this)">其他</a></th>
            <td>${oaEosPro.realInputEstimation}</td>
        </tr>
        </c:if>
        </tbody>
    </table>
</fieldset>
<script>
    id='${oaEosPro.id}'
    function clicks(obj) {
        location.href="${ctx}/oa/eos/sta/classjd?id="+id;
    }
</script>
</body>
</html>