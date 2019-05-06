<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<head>
    <title>项目报销</title>
    <meta name="decorator" content="cms_default_${site.theme}"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
    <%@ include file="/WEB-INF/views/modules/cms/front/include/head.jsp" %>
    <title>Title</title>
    <style>
        html,
        body {
            height: 100%;
            overflow: hidden;
        }
        .mui-bar {
            -webkit-box-shadow: none;
            box-shadow: none;
        }
    </style>
</head>
<c:forEach items="${mapList}" var="map">
    <div class="page">
        <div class="page__bd">
            <div class="weui-form-preview">
                <div class="weui-form-preview__hd">
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">报销金额</label>
                        <em class="weui-form-preview__value"><fmt:formatNumber value="${map.cost}"
                                                                               type="currency"/></em>
                    </div>
                </div>
                <div class="weui-form-preview__bd">
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">项目编号</label>
                        <span class="weui-form-preview__value">${map.PaNumber}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">项目名字</label>
                        <span class="weui-form-preview__value">${map.proName}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">报销类型</label>
                        <span class="weui-form-preview__value">${map.proitemname}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">报销原因</label>
                        <span class="weui-form-preview__value">${map.content}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">申请日期</label>
                        <span class="weui-form-preview__value"> <fmt:formatDate value="${map.crdate}"
                                                                                pattern="yyyy-MM-dd HH:mm:ss"/> </span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">状态</label>
                        <span class="weui-form-preview__value">${map.state eq 1 ? "已审核" : map.state eq 0 ? "未审核": map.state eq -1 ? "已驳回": "请修改申请单"}</span>
                    </div>
                </div>
                <div class="weui-form-preview__ft">
                    <c:if test="${map.state eq 0}">
                        <a class="weui-form-preview__btn weui-form-preview__btn_default" id="upd" href="javascript:">修改申请单</a>
                    </c:if>
                    <a href="Details?id=${map.id}" class="weui-form-preview__btn weui-form-preview__btn_primary">
                        查看详情
                    </a>
                </div>
            </div>
            <br>
        </div>
    </div>
</c:forEach>
    <script type="text/javascript" charset="utf-8">
        mui.init();
    mui.ready(function() {
        var header = document.querySelector('header.mui-bar');
        var list = document.getElementById('list');
        //calc hieght
        list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
        //create
        window.indexedList = new mui.IndexedList(list);
    });

</script>
