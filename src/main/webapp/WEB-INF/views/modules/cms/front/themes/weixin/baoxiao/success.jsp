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
<body >
<link rel="stylesheet" href="${ctxStaticTheme}/weixin/css/weui.css"/>
<div class="page icons js_show" >
    <div class="page__bd page__bd_spacing">
        <div class="icon-box">
        </div>
        <div class="icon-box">
        </div>
        <div class="icon-box">
            <i class="weui-icon-success weui-icon_msg" style="font-size: 60px"></i>
            <div class="icon-box__ctn">
                <h3 class="icon-box__title">报销金额￥${cost}元</h3>
                <p class="icon-box__desc" style="font-size: 12px;">报销申请成功,请等待审批结果,结果会通过企业微信方式通知您！</p>
            </div>
        </div>
        <a href="${oa}/weixin" class="weui-btn weui-btn_primary">返回首页</a>
    </div>
</div>
</body>
