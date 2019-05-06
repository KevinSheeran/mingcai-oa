<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<html>
<head>
    <title>项目详细信息</title>
    <meta name="decorator" content="cms_default_${site.theme}"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
</head>
<body>
<div class="mui-content">
    <div class="weui-cells__title">项目详情</div>
<div class="weui-cells">
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>
                <h5>项目编号</h5>
            </p>
        </div>
        <div class="weui-cell__ft lx">
            <c:choose>
                <c:when test="${type=='flx'}">
                    ${oaEosPro.proNumber}
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${oaEosPro.paNumber !=null and oaEosPro.proNumber == null  or oaEosPro.proNumber == ''}">
                            ${oaEosPro.paNumber}
                        </c:when>
                        <c:when test="${oaEosPro.proNumber !=null ||oaEosPro.proNumber !=''}">
                            ${oaEosPro.proNumber}
                        </c:when>
                    </c:choose>
                </c:otherwise>
            </c:choose>

        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>
                <h5>项目名</h5>
            </p>
        </div>
        <div class="weui-cell__ft">
            ${oaEosPro.name}
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__bd">
            <p>
                <h5>项目类型</h5>
            </p>
        </div>
        <div class="weui-cell__ft">
            <c:if test="${type == 'lx' }">
                立项
            </c:if>
            <c:if test="${type == 'ylx' }">
                预立项
            </c:if>
            <c:if test="${type == 'flx' }">
                非销售立项
            </c:if>
        </div>
    </div>
</div>
    <div class="weui-cells__title">子项目详情</div>
<c:forEach items="${list}" var="oaWxExtended1" varStatus="sta">
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>
                    <h5>子项编号</h5>
                </p>
            </div>
            <div class="weui-cell__ft zid">
                <c:if test="${type == 'lx' || type == 'flx' }">
                    ${oaWxExtended1.code}
                </c:if>
                <c:if test="${type == 'ylx' }">
                    ${oaWxExtended1.eosZId}
                </c:if>
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
                <p>
                    <h5>子项名</h5>
                </p>
            </div>
            <div class="weui-cell__ft">
                    ${oaWxExtended1.name}
            </div>
        </div>
    </div>
</c:forEach>
</div>
</body>
</html>
