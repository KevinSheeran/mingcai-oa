<%--
  Created by IntelliJ IDEA.
  User: le
  Date: 2019/3/13
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<html>
<head>
    <title>明材办公</title>
    <meta name="decorator" content="cms_default_${site.theme}" />
    <meta name="description" content="JeeSite ${site.description}" />
    <meta name="keywords" content="JeeSite ${site.keywords}" />
    <%@ include file="/WEB-INF/views/modules/cms/front/include/head.jsp"%>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>


<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h2 style="">
                    我的报销
                </h2>
            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>
                        项目编号
                    </th>
                    <th>
                        子项目名
                    </th>
                    <th>
                        申请时间
                    </th>
                    <th>
                        报销金额
                    </th>

                    <th>
                        状态
                    </th>
                    <th>
                        查看详细信息
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listByf}" var="oaWxProReimbursement">
                    <tr>
                        <td>
                                ${oaWxProReimbursement.proId}
                        </td>
                        <td>
                                ${oaWxProReimbursement.subItem.proName}
                        </td>
                        <td>
                            <fmt:formatDate value="${oaWxProReimbursement.dateForGeneration}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            ${oaWxProReimbursement.extended.cost}
                        </td>
                        <td>
                                ${oaWxProReimbursement.remarks}
                        </td>
                        <shiro:hasPermission name="oa:oaWxProReimbursement:edit"><td>
                            <a href="${ctx}/oa/oaWxProReimbursement/form?id=${oaWxProReimbursement.id}">修改</a>
                            <a href="${ctx}/oa/oaWxProReimbursement/delete?id=${oaWxProReimbursement.id}" onclick="return confirmx('确认要删除该微信报销吗？', this.href)">删除</a>
                        </td></shiro:hasPermission>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
