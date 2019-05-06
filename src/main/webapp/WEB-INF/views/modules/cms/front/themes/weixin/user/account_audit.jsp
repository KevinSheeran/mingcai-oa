<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>明材办公-申请销售额审批</title>
    <meta name="decorator" content="cms_default_${site.theme}" />
    <meta name="description" content="JeeSite ${site.description}" />
    <meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body >
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
<div class="page">
    <div class="page__bd">
        <div class="weui-cells__title">申请单详情</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>申请人</p>
                </div>
                <div class="weui-cell__ft"><img src="${oaUserAccount.wxUsers.avatar}/100" width="30">&nbsp;&nbsp;${oaUserAccount.wxUsers.name}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>申请年销售额</p>
                </div>
                <div class="weui-cell__ft">￥${fns:formatNumber(oaUserAccount.salesVolumeId.salesVolume)}元</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>没月可支配金额比例</p>
                </div>
                <div class="weui-cell__ft">${oaUserAccount.salesVolumeId.uotaRatio}%</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>申请日期</p>
                </div>
                <div class="weui-cell__ft"><fmt:formatDate value="${oaUserAccount.createDate}" pattern="yyyy-MM-dd"/></div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>申请描述</p>
                </div>
                <div class="weui-cell__ft">${oaUserAccount.salesVolumeId.remarks}</div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>处理状态</p>
                </div>
                <div class="weui-cell__ft"><c:if test="${oaUserAccount.status==-1}">驳回</c:if><c:if test="${oaUserAccount.status==0}">未提交</c:if><c:if test="${oaUserAccount.status==1}">审批中</c:if><c:if test="${oaUserAccount.status==2}">申请通过</c:if></div>
            </div>
        </div>
        <c:forEach items="${flowmap}" var="flow" varStatus="index">
            <div class="weui-cells__title"><c:if test="${index.index==0}">总经理审批</c:if></div>
            <div class="weui-panel weui-panel_access">
                <div class="weui-panel__bd">
                    <c:forEach items="${flow.value}" var="item">
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
                <a href="javascript:;" class="weui-btn <c:if test="${oaUserAccount.status!=1}"> disabled</c:if> weui-btn_primary" oeproid="${oaUserAccount.id}" flowid="${flow.id}"  id="showIOSDialog">同意</a>
                <a href="javascript:;" class="weui-btn <c:if test="${oaUserAccount.status!=1}"> disabled</c:if> weui-btn_default" oeproid="${oaUserAccount.id}" flowid="${flow.id}"  id="backIOSDialog">驳回</a>
            </div>
        </c:if>
    </div>
</div>
<script type="application/javascript">
    $('#showIOSDialog').on('click', function(){
        $(this).addClass("disabled");
        var eosid=$(this).attr("oeproid");
        var flowid=$(this).attr("flowid");
        var btnArray = ['否', '是'];
        mui.confirm('确认通过申请吗？', '系统提示', btnArray, function(e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/accountaudit",{eosid:eosid,flowid:flowid,content:$("#content").val()},function(data){
                    data=JSON.parse(data);
                    if(data.success){
                        mui.toast('审核完成！');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    }else{
                        $("#showIOSDialog").removeClass("disabled")
                        mui.alert(data.message, '系统提示', function() {
                        });
                    }
                })
            } else {
                $("#showIOSDialog").removeClass("disabled")
            }
        })
    });
    $('#backIOSDialog').on('click', function(){
        $(this).addClass("disabled");
        var eosid=$(this).attr("oeproid");
        var flowid=$(this).attr("flowid");
        var btnArray = ['否', '是'];
        mui.confirm('确认驳回申请吗？', '系统提示', btnArray, function(e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/accountauditback",{eosid:eosid,flowid:flowid,content:$("#content").val()},function(data){
                    data=JSON.parse(data);
                    if(data.success){
                        mui.toast('驳回完成！');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    }else{
                        $("#backIOSDialog").removeClass("disabled")
                        mui.alert(data.message, '系统提示', function() {
                        });
                    }
                })
            } else {
                $("#backIOSDialog").removeClass("disabled")
            }
        })
    });
</script>
</body>
</html>