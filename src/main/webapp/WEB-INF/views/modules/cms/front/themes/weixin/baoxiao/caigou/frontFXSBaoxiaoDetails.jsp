<%--
  Created by IntelliJ IDEA.
  User: le
  Date: 2019/3/19
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<html>
<head>
    <title>明材办公</title>
    <meta name="decorator" content="cms_default_${site.theme}"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
    <%@ include file="/WEB-INF/views/modules/cms/front/include/head.jsp" %>
</head>
<body>
<style>
    .weui-btn-area{
        width: 90%;
        margin: 0 auto;
        text-align: center;
    }
    .weui-btn-area a{
        width: 40%;
        float: left;
        margin: 15px 5%;
    }
    p{
        color: #333;
    }
</style>
<header class="mui-bar mui-bar-nav">
    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
    <h1 class="mui-title">采购单详情</h1>
</header>
<div class="mui-content">
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__bd">
               <p>申请人</p>
            </div>
            <div class="weui-cell__ft lx" path="bm">
                ${user.name}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
               <p>项目编号</p>
            </div>
            <div class="weui-cell__ft lx" path="fxs">
                        ${map.oaEosProUn.proNumber}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
               <p>项目名</p>
            </div>
            <div class="weui-cell__ft">${map.oaEosProUn.name}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
               <p>财务部门</p>
            </div>
            <div class="weui-cell__ft">${map.appropriation eq 1 ?"已拨款":map.appropriation eq 0 ?"未拨款":map.appropriation eq 2?"审核未通过":"系统异常请联系系统管理员"}</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
               <p>项目类型</p>
            </div>
            <div class="weui-cell__ft">非销售立项</div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__bd">
               <p>申请时间</p>
            </div>
            <div class="weui-cell__ft"><fmt:formatDate value="${map.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
        </div>

    </div>
    <c:forEach items="${map.list}" var="oaWxExtended1" varStatus="sta">
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                   <p>子项编号</p>
                </div>
                <div class="weui-cell__ft zid">${oaWxExtended1.oaEosProStartItem.code}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                   <p>子项名</p>
                </div>
                <div class="weui-cell__ft">${oaWxExtended1.oaEosProStartItem.name}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                   <p>采购金额</p>
                </div>
                <div class="weui-cell__ft"><fmt:formatNumber value="${oaWxExtended1.cost}"
                                                             type="currency"/></div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                   <p>明细</p>
                </div>
                <div class="weui-cell__ft">${oaWxExtended1.content}</div>
            </div>
        </div>
    </c:forEach>
    <div class="weui-cells__title">审批人员</div>
    <div class="weui-cells">
        <c:forEach items="${flowmap}" var="flow1" varStatus="index">
            <div class="weui-cells__title"><c:if test="${index.index==0}">项目经理</c:if><c:if
                    test="${index.index==1}">管理中心</c:if><c:if test="${index.index==2}">总经理</c:if></div>
            <div class="weui-panel weui-panel_access">
                <div class="weui-panel__bd">

                    <c:forEach items="${flow1.value}" var="item">
                        <a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
                            <c:if test="${item.status==1}"><i class="badge weui-icon-success"></i></c:if>
                            <c:if test="${item.status==-1}"><i class="weui-icon-cancel"></i></c:if>
                            <div class="weui-media-box__hd">
                                <img class="weui-media-box__thumb" src="${item.user.wxUsers.avatar}/100" alt="">
                            </div>
                            <div class="weui-media-box__bd">
                                <h4 class="weui-media-box__title">${item.user.wxUsers.name}</h4>
                                <p class="weui-media-box__desc">${item.content}</p>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
    <c:if test="${flow.status!=null}">
        <div class="weui-cells__title">意见建议</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <textarea class="weui-textarea" id="content" placeholder="请输入文本" rows="3"></textarea>
                    <div class="weui-textarea-counter"><span>0</span>/200</div>
                </div>
            </div>
        </div>
        <div class="weui-btn-area">
            <a href="javascript:;"
               class="mui-btn mui-btn-primary mui-btn-block <c:if test="${map.state!=0}"> disabled</c:if>"
               oeproid="${map.id}" flowid="${flow.id}" id="showIOSDialog">同意</a>
            <a href="javascript:;"
               class="mui-btn mui-btn-warning mui-btn-block <c:if test="${map.state!=0}"> disabled</c:if>"
               oeproid="${map.id}" flowid="${flow.id}" id="stopIOSDialog">驳回</a>
        </div>
    </c:if>
    <c:if test="${map.appropriation eq 0}">
        <div class="weui-btn-area">
            <c:if test="${ ! empty success and map.state==1 }">
                <a href="javascript:;" class="mui-btn mui-btn-primary mui-btn-block" oeproid="${map.id}"
                   id="showIOSDialogs">同意</a>
                <a href="javascript:;" class="mui-btn mui-btn-warning mui-btn-block" oeproid="${map.id}"
                   flowid="${flow.id}"
                   id="showIOSDialogs1" >驳回</a>

            </c:if>
        </div>
    </c:if>
</div>
<script>
    $('#showIOSDialog').on('click', function () {
        $(this).addClass("disabled");
        var eosid = $(this).attr("oeproid");
        var flowid = $(this).attr("flowid");
        var btnArray = ['否', '是'];
        mui.confirm('确认通过报销审核吗？', '系统提示', btnArray, function (e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/bx/audit", {
                    eosid: eosid,
                    flowid: flowid,
                    content: $("#content").val(),
                    stop:"ty"
                }, function (data) {
                    data = JSON.parse(data);
                    if (data.success) {
                        mui.toast('审核完成！');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    } else {
                        $("#showIOSDialog").removeClass("disabled")
                        mui.alert(data.message, '系统提示', function () {
                        });
                    }
                })
            } else {
                $("#showIOSDialog").removeClass("disabled")
            }
        })
    });


    $('#stopIOSDialog').on('click', function () {
        $(this).addClass("disabled");
        var eosid = $(this).attr("oeproid");
        var flowid = $(this).attr("flowid");
        var btnArray = ['否', '是'];
        mui.confirm('确认驳回此次申请吗？', '系统提示', btnArray, function (e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/bx/audit", {
                    eosid: eosid,
                    flowid: flowid,
                    content: $("#content").val(),
                    stop:"bh"
                }, function (data) {
                    data = JSON.parse(data);
                    if (data.success) {
                        mui.toast('处理完成！');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    } else {
                        $("#showIOSDialog").removeClass("disabled")
                        mui.alert(data.message, '系统提示', function () {
                        });
                    }
                })
            } else {
                $("#stopIOSDialog").removeClass("disabled")
            }
        })
    });

    $('#showIOSDialogs').on('click', function () {
        $(this).addClass("disabled");
        var eosid = $(this).attr("oeproid");
        var flowid = $(this).attr("flowid");
        var cost = ${cost}
            btnArray = ['否', '是'];
        mui.confirm('确认通过审核吗？', '系统提示', btnArray, function (e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/bx/appropriation", {
                    eosid: eosid,
                    flowid: flowid,
                    content: $("#content").val(),
                    type: $(".lx").attr("path"),
                    stop:"ty"
                }, function (data) {
                    data = JSON.parse(data);
                    if (data.success) {
                        mui.toast('处理完成！');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    } else {
                        $("#showIOSDialog").removeClass("disabled")
                        mui.alert(data.message, '系统提示', function () {
                        });
                    }
                })
            } else {
                $("#showIOSDialogs").removeClass("disabled")
            }
        })

    });

    $('#showIOSDialogs1').on('click', function () {
        $(this).addClass("disabled");
        var eosid = $(this).attr("oeproid");
        var flowid = $(this).attr("flowid");
        var cost = ${cost}
            btnArray = ['否', '是'];
        mui.confirm('确认驳回申请吗？', '系统提示', btnArray, function (e) {
            if (e.index == 1) {
                alert(flowid)
                $.post("${oa}/weixin/bx/appropriation", {
                    eosid: eosid,
                    flowid: flowid,
                    content: $("#content").val(),
                    type: $(".lx").attr("path"),
                    stop:"bh"
                }, function (data) {
                    data = JSON.parse(data);
                    if (data.success) {
                        mui.toast('处理完成！');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    } else {
                        $("#showIOSDialog").removeClass("disabled")
                        mui.alert(data.message, '系统提示', function () {
                        });
                    }
                })
            } else {
                $("#showIOSDialogs1").removeClass("disabled")
            }
        })

    });
</script>
</body>
</html>
