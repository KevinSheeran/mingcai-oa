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
        <c:forEach items="${jd}" var="jd">
        <thead>
        <tr>
            <th><h4>${jd.name}阶段</h4></th>
            <th><h4><span style="color: #929627">总花费金额：</span><fmt:formatNumber value="${jd.cost}" type="currency"/> </h4></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <th>
                <h5 style="color: #000000;">报销类型</h5>
            </th>
            <th>
                <h5 style="color: #000000;">总花费金额</h5>
            </th>
        </tr>
        <c:forEach items="${jd.pds}" var="rei">
        <tr>
            <th><a id="0">${rei.nameb}</a></th>
            <td><fmt:formatNumber value="${rei.sums ==null ?0 :rei.sums }" type="currency"/></td>
        </tr>
        </c:forEach>
        </tbody>
        </c:forEach>
    </table>
</fieldset>
</body>
</html>