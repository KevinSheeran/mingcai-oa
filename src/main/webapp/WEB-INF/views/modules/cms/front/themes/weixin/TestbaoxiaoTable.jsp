<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<head>
    <title>明材办公</title>
    <meta name="decorator" content="cms_default_${site.theme}"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
    <%@ include file="/WEB-INF/views/modules/cms/front/include/head.jsp" %>
    <title>Title</title>
</head>
<body>
    <div class="page">
        <div class="page__hd">
            <a href="/oa/weixin/bx/baoxiaoadd" style="position: relative ;top: 30px;left: 280px;" class="weui-btn weui-btn_mini weui-btn_default">申请报销</a>
            <h3 class="page__title" >我的申请单</h3>
            <div class="mui-indexed-list-search mui-input-row mui-search">
                <input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索我的报销单">
            </div>
        </div>

        <%@include file="baoxiao/table.jsp"%>
    </div>
</body>
</html>
