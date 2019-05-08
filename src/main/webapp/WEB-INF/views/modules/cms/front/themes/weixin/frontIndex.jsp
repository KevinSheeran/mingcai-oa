<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>明材办公</title>
    <meta name="decorator" content="cms_default_audit"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
</head>
<body>
<style>
    .mui-bar-tab .mui-tab-item .mui-icon-extra~.mui-tab-label{
        font-size: 14px;
    }
    .aui-nav-title{
        font-size: 16px;
    }
    .aui-nav-title{
        padding: 15px 10px 5px;
    }
    .mui-icon-extra .mui-badge{
        margin-left: 0px;
        font-size: 9px;
    }
    .mui-table-view {
        margin-top: 20px;
    }
    .mui-tab-item .home{
        width: 18px;
        height: 18px;
        margin: 2px auto;
        background: url("${ctxStatic}/modules/cms/front/themes/weixin/icon/菜单管理(1).png");
        background-size:100% ;
        background-repeat: no-repeat;
        background-position: left;
    }
    .mui-tab-item.mui-active .home{
        background: url("${ctxStatic}/modules/cms/front/themes/weixin/icon/菜单管理.png");
        background-size:100% ;
        background-repeat: no-repeat;
        background-position: left;
    }
    .mui-tab-item .rsb{
        width: 20px;
        height: 20px;
        margin: 0 auto;
        background: url("${ctxStatic}/modules/cms/front/themes/weixin/icon/借款审批(1).png");
        background-repeat: no-repeat;
        background-size:100%  ;
        background-position: left;
    }
    .mui-tab-item.mui-active .rsb{
        background: url("${ctxStatic}/modules/cms/front/themes/weixin/icon/借款审批.png");
        background-size:100%  ;
        background-repeat: no-repeat;
        background-position: left;
    }
    .mui-tab-item .audit{
        width: 20px;
        height: 20px;
        margin: 0 auto;
        background: url("${ctxStatic}/modules/cms/front/themes/weixin/icon/部门审批(1).png");
        background-repeat: no-repeat;
        background-size:100% ;
        background-position: left;
    }
    .mui-tab-item.mui-active .audit{
        background: url("${ctxStatic}/modules/cms/front/themes/weixin/icon/部门审批.png");
        background-size:100% ;
        background-repeat: no-repeat;
        background-position: left;
    }
    .mui-tab-item .setting{
        width: 20px;
        height: 20px;
        margin: 0 auto;
        background: url("${ctxStatic}/modules/cms/front/themes/weixin/icon/服务集成(1).png");
        background-repeat: no-repeat;
        background-size:100% ;
        background-position: left;
    }
    .mui-tab-item.mui-active .setting{
        background: url("${ctxStatic}/modules/cms/front/themes/weixin/icon/服务集成.png");
        background-size:100% ;
        background-repeat: no-repeat;
        background-position: left;
    }
    .aui-class-list-box .aui-box{
            width:25%;
            float: left;
            position: relative;
            text-align: center;
            overflow: hidden;
            padding: 15px 0 15px 0;
    }
    .aui-class-list-box .aui-box img{
        margin:5px auto 5px;
        width: 35%;
    }
    .aui-class-list-box .aui-box .name{
        text-align: center;
        font-size: 12px;
        color: #333;
    }
</style>
<script src="${ctxStaticTheme}/js/clipboard.min.js" type="text/javascript"></script>
<nav class="mui-bar mui-bar-tab">
    <a class="mui-tab-item mui-active" href="#tabbar">
       <div class="home mui-icon-extra"></div>
        <span class="mui-tab-label">首页</span>
    </a>
    <a class="mui-tab-item " href="#tabbar-with-chat">
        <div class="rsb mui-icon-extra"></div>
        <span class="mui-tab-label">个人申请</span>
    </a>
    <a class="mui-tab-item" href="#tabbar-with-contact">
        <div class="audit mui-icon-extra">
            <c:if test="${financeAuditRole}">
                <c:if test="${pd.prostartcount+pd.procount+pd.extendedstartcount+pd.extendedcount+pd.extendedcount1+pd.prouncount+pd.appropriationcount+pd.appropriationcount1>0}">
                    <span class="mui-badge">${pd.prostartcount+pd.procount+pd.extendedstartcount+pd.extendedcount+pd.extendedcount1+pd.prouncount+pd.appropriationcount+pd.appropriationcount1}</span>
                </c:if>
            </c:if>
               <c:if test="${!financeAuditRole}">
                   <c:if test="${pd.prostartcount+pd.procount+pd.extendedstartcount+pd.extendedcount+pd.extendedcount1+pd.prouncount>0}">
                       <span class="mui-badge">${pd.prostartcount+pd.procount+pd.extendedstartcount+pd.extendedcount1+pd.extendedcount+pd.prouncount}</span>
                   </c:if>
               </c:if>
        </div>
        <span class="mui-tab-label">审批管理</span>
    </a>
    <a class="mui-tab-item" href="#tabbar-with-map">
        <div class="setting mui-icon-extra"></div>
        <span class="mui-tab-label">设置</span>
    </a>
</nav>
<div class="mui-content">
    <div id="tabbar" class="mui-control-content mui-active">
        <section class="aui-flexView on">
            <section class="aui-scrollView">
                <div class="aui-nav-title">项目</div>
                <div class="aui-class-list-box">
                    <a href="javascript:;" class="disabled">
                        <div class="aui-box">
                            <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
                            <div class="name">基本变更</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/bx/front_pro" >
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据查询.png" alt="">
                            <div class="name">项目查询</div>
                        </div>
                    </a>
                    <a href="javascript:;" class="disabled">
                        <div class="aui-box">
                            <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
                            <div class="name">延期变更</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/presentationList" class="<c:if test="${!lookweek}">disabled</c:if>">
                        <div class="aui-box">
                            <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
                            <div class="name">项目周报</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/bx/front_pro?summary=true" >
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/日报工时.png" alt="">
                            <div class="name">工时汇总</div>
                        </div>
                    </a>
                </div>
                <div class="aui-nav-title">采购</div>
                <div class="aui-class-list-box">
                    <a href="${oa}/weixin/bx/caigou?types=ylx" class="disabled">
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/供应商预付款.png" alt="">
                            <div class="name">销售预立项</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/bx/caigou?types=lx" class="disabled">
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据申请.png" alt="">
                            <div class="name">销售立项</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/bx/caigou?types=fxs" class="disabled">
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据申请.png" alt="">
                            <div class="name">非销售立项</div>
                        </div>
                    </a>
                </div>
                <div class="aui-nav-title">基本报销</div>
                <div class="aui-class-list-box">
                    <a href="${oa}/weixin/two/other?types=ylx" class="">
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/供应商预付款.png" alt="">
                            <div class="name">销售预立项</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/two/other?types=lx" class="">
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/申请单.png" alt="">
                            <div class="name">销售立项</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/two/other?types=fxs" class="">
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/申请单.png" alt="">
                            <div class="name">非销售立项</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/two/other?types=bm" class="">
                        <div class="aui-box">
                            <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/部门管理.png" alt="">
                            <div class="name">部门</div>
                        </div>
                    </a>
                </div>
            </section>
        </section>
    </div>
    <div id="tabbar-with-chat" class="mui-control-content" style="position: relative;">
        <section class="aui-flexView on">
            <section class="aui-scrollView">
                <div class="aui-nav-title">个人申请</div>
                <div class="aui-class-list-box">
                    <a href="${oa}/weixin/bx/tz?types=jb" >
                        <div class="aui-box">
                            <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
                            <div class="name">采购</div>
                        </div>
                    </a>
                    <a href="${oa}/weixin/two/tz?types=jb" class="">
                        <div class="aui-box">
                            <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
                            <div class="name">基本报销</div>
                        </div>
                    </a>
                </div>
            </section>
        </section>
    </div>

    <div id="tabbar-with-contact" class="mui-control-content">
        <div class="page__bd" style="height: 100%;">
            <div class="weui-tab">
                <div class="weui-navbar">
                    <div class="weui-navbar__item weui-bar__item_on" onclick="changeTab(this)" toid="tab1">
                        待审核
                    </div>
                    <div class="weui-navbar__item" onclick="changeTab(this)" toid="tab2">
                        已审核
                    </div>
                </div>
                <div class="weui-tab__panel">
                    <section class="aui-flexView on" id="tab1">
                        <section class="aui-scrollView">
                            <div class="aui-nav-title">立项类</div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/proauditList" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">预立项审批</div>
                                        <c:if test="${pd.procount>0}"> <span class="weui-badge"
                                                                             style="position: absolute;top: 5px;right: 13px;">${pd.procount}</span></c:if>
                                    </div>
                                </a>
                                <a href="${oa}/weixin/proauditstartList" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">立项审批</div>
                                        <c:if test="${pd.prostartcount>0}"> <span class="weui-badge"
                                                                                  style="position: absolute;top: 5px;right: 13px;">${pd.prostartcount}</span></c:if>
                                    </div>
                                </a>
                                <a href="${oa}/weixin/prounauditList" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">非销售立项审批</div>
                                        <c:if test="${pd.prouncount>0}"> <span class="weui-badge" style="position: absolute;top: 5px;right: 13px;">${pd.prouncount}</span></c:if>
                                    </div>
                                </a>
                                <a href="javascript:;" class="disabled">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">基本变更</div>
                                    </div>
                                </a>
                                <a href="javascript:;" class="disabled">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">延期变更</div>
                                    </div>
                                </a>
                            </div>
                            <div class="aui-nav-title">采购处理</div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/bx/tz?types=sh" class="disabled">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">采购审批</div>
                                        <c:if test="${pd.extendedcount1>0}"> <span class="weui-badge"
                                                                                   style="position: absolute;top: 5px;right: 13px;">${pd.extendedcount1}</span></c:if>
                                    </div>
                                </a>
                            </div>
                            <div class="aui-nav-title">基本</div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/two/tz?types=sh" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">报销审批</div>
                                        <c:if test="${pd.extendedcount>0}"> <span class="weui-badge"
                                                                                  style="position: absolute;top: 5px;right: 13px;">${pd.extendedcount}</span></c:if>
                                    </div>
                                </a>
                                <!--
                                <a href="${oa}/weixin/userAccountApplyList" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">销售额审批</div>
                                        <c:if test="${pd.accountcount>0}"> <span class="weui-badge"
                                                                                  style="position: absolute;top: 5px;right: 13px;">${pd.accountcount}</span></c:if>
                                    </div>
                                </a>
                                -->
                            </div>
                            <c:if test="${financeRole}">
                            <div class="aui-nav-title">财务</div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/two/tz?types=cw" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据支付.png" alt="">
                                        <div class="name">基本报销审批</div>
                                        <c:if test="${financeAuditRole}">
                                            <c:if test="${pd.appropriationcount>0}"> <span class="weui-badge"  style="position: absolute;top: 5px;right: 13px;">${pd.appropriationcount}</span></c:if>
                                        </c:if>
                                    </div>
                                </a>
                                <a href="${oa}/weixin/bx/tz?types=cw" class="disabled">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据支付.png" alt="">
                                        <div class="name">采购审批</div>
                                        <c:if test="${financeAuditRole}">
                                            <c:if test="${pd.appropriationcount1>0}"> <span class="weui-badge"  style="position: absolute;top: 5px;right: 13px;">${pd.appropriationcount1}</span></c:if>
                                        </c:if>
                                    </div>
                                </a>
                            </div>
                            </c:if>
                        </section>
                    </section>
                    <section class="aui-flexView " id="tab2">
                        <section class="aui-scrollView">
                            <div class="aui-nav-title">立项类</div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/proauditfinishList" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/请假单确认.png" alt="">
                                        <div class="name">预立项</div>
                                    </div>
                                </a>
                                <a href="${oa}/weixin/proauditstartfinishList" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/请假单确认.png" alt="">
                                        <div class="name">立项</div>
                                    </div>
                                </a>
                                <a href="${oa}/weixin/prounauditFinishList" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/请假单确认.png" alt="">
                                        <div class="name">非销售立项</div>
                                    </div>
                                </a>
                                <a href="javascript:;" class="disabled">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/请假单确认.png" alt="">
                                        <div class="name">基本变更</div>
                                    </div>
                                </a>
                                <a href="javascript:;" class="disabled">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/请假单确认.png" alt="">
                                        <div class="name">延期变更</div>
                                    </div>
                                </a>
                            </div>
                            <div class="aui-nav-title">采购</div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/bx/tz?types=ysh" class="disabled">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/请假单确认.png" alt="">
                                        <div class="name">处理记录</div>
                                    </div>
                                </a>
                            </div>
                            <div class="aui-nav-title">报销</div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/two/tz?types=ysh" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/请假单确认.png" alt="">
                                        <div class="name">处理记录</div>
                                    </div>
                                </a>
                                <!--
                                <a href="${oa}/weixin/userAccountApplyfinishList" class="">
                                    <div class="aui-box">
                                        <img src="${ctxStatic}/modules/cms/front/themes/weixin/icon/单据签收.png" alt="">
                                        <div class="name">销售额审批</div>
                                    </div>
                                </a>
                                -->
                            </div>
                        </section>
                    </section>
                </div>
            </div>
        </div>
    </div>
    <div id="tabbar-with-map" class="mui-control-content">
        <ul class="mui-table-view mui-table-view-chevron">
            <li class="mui-table-view-cell mui-media">
                <a class="mui-navigate-right" href="#account">
                    <img class="mui-media-object mui-pull-left head-img" id="head-img" src="${fns:getUser().wxUsers.avatar}/100">
                    <div class="mui-media-body">
                        ${fns:getUser().wxUsers.name}
                        <p class="mui-ellipsis"> ${fns:getUser().wxUsers.department_s}</p>
                    </div>
                </a>
            </li>
        </ul>
        <c:if test="${fns:getUser().sale}">
                <ul class="mui-table-view mui-table-view-chevron">
                    <li class="mui-table-view-cell">
                        <a href="#account" class="mui-navigate-right">销售账户</a>
                    </li>
                </ul>
        </c:if>
        <ul class="mui-table-view">
            <li class="mui-table-view-cell">
                <button class="mui-navigate-righ code" onclick="" type="button">
                    code copy
                </button>
            </li>
        </ul>
    </div>
</div>
<script>
    $(".mui-scroll-wrapper").height($("body").height() - $("nav.mui-bar").height());
    var clipboard = new ClipboardJS('.code', {
        text: function () {
            return '${code}';
        }
    });
    clipboard.on('success', function (e) {
        console.log(e);
    });

    clipboard.on('error', function (e) {
        console.log(e);
    });

    function changeTab(obj) {
        var $this = $(obj);
        $("#" + $this.attr("toid")).addClass('on').siblings('.aui-flexView').removeClass('on');
        $this.addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
    }

    function addbx(obj) {
        $this = $(obj)
        location.href = "${oa}/weixin/bx/baoxiaoadd?types=" + $this.attr("path");
    }
</script>
</body>
</html>