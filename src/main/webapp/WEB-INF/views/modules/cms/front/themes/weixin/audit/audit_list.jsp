<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>明材办公-预立项审批</title>
    <meta name="decorator" content="cms_default_${site.theme}" />
    <meta name="description" content="JeeSite ${site.description}" />
    <meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>
<div class="page">

    <c:if test="${list.size()==0}">
        <div class="page__hd">
            <br/>
            <p class="page__desc" style="text-align: center">暂无数据</p>
            <p class="page__desc" style="text-align: center"><a href="${oa}/weixin">返回首页</a></p>
        </div>
    </c:if>
    <div class="page__bd" style="height: 100%;" id="body">
        <c:forEach items="${list}"  var="item">
            <div class="weui-form-preview" >
                <div class="weui-form-preview__hd">
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label a">${item.name}</label>

                    </div>
                </div>
                <div class="weui-form-preview__bd">
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">预估金额</label>
                        <span class="weui-form-preview__value b">${item.estimatedAmount}元</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">责任销售</label>
                        <span class="weui-form-preview__value c">${item.personLiableUser.name}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">客户名称</label>
                        <span class="weui-form-preview__value d">${item.customerName}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">客户方负责人</label>
                        <span class="weui-form-preview__value e">${item.customerUser}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">申请日期</label>
                        <span class="weui-form-preview__value b"><fmt:formatDate value="${item.flow.createDate}" pattern="yyyy-MM-dd"/></span>
                    </div>
                </div>
                <div class="weui-form-preview__ft">
                    <a class="weui-form-preview__btn weui-form-preview__btn_default" href="${oa}/weixin/auditpro?id=${item.id}">去审批</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<script type="text/javascript">

    <%--$.get("${oa}/weixin/auditList",{},function(data){--%>
    <%--alert(data);--%>
    <%--var data=JSON.parse(data);--%>
    <%--var model=$("#model").clone();--%>
    <%--for(var i=0;i<data.length;i++){--%>
    <%--var item=data[i];--%>
    <%--model.find(".a").html(item.name);--%>
    <%--model.find(".b").html(item.estimatedAmount+"元");--%>
    <%--model.find(".c").html(item.personLiableUser.name);--%>
    <%--model.find(".d").html(item.customerName);--%>
    <%--model.find(".e").html(item.customerUser);--%>
    <%--$("#body").append(model.fadeIn(200));--%>
    <%--}--%>
    <%--})--%>
</script>
</body>
</html>