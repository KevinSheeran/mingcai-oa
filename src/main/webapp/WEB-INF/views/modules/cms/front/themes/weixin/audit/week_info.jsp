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
    <div class="page__bd">
        <div class="weui-cells__title">项目信息</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>负责人</p>
                </div>
                <div class="weui-cell__ft">${oaEosProPresentation.createBy.name}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>名称</p>
                </div>
                <div class="weui-cell__ft">${oaEosProPresentation.pro.name}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>编号</p>
                </div>
                <div class="weui-cell__ft">${oaEosProPresentation.pro.proNumber}</div>
            </div>
        </div>
        <div class="weui-cells__title">周报详情</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>时间</p>
                </div>
                <div class="weui-cell__ft">${oaEosProPresentation.startEnd}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>总结</p>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>${fn:replace(oaEosProPresentation.summary,vEnter,"<br>")}</p>
                </div>
            </div>
        </div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>下周计划</p>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>${fn:replace(oaEosProPresentation.nextPlan,vEnter,"<br>")}</p>
                </div>
            </div>
        </div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>问题处理</p>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>${fn:replace(oaEosProPresentation.problemHandle,vEnter,"<br>")}</p>
                </div>
            </div>
        </div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>意见建议</p>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>${fn:replace(oaEosProPresentation.proposal,vEnter,"<br>")}</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
</script>
</body>
</html>