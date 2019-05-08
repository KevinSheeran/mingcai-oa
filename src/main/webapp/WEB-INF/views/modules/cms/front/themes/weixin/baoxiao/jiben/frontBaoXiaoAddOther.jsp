<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<html>
<head>
    <title>明材办公</title>
    <meta name="decorator" content="cms_default_weixin"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
</head>
<body class="mui-fullscreen">
<style>
    img{
        border-radius: 5px;
    }
    .weui-cell__bd span,label{
    }
    .weui-uploader__title{
        color: #000000;
    }
    .weui-uploader__input-box{
        width: 60px;
        height: 60px;
        margin-right: 10px;
    }
    .weui-uploader_box{
        width: 60px;
        height: 80px;
        border: 0px;
        position: relative;
    }
    .weui-uploader_box .remove{
        position: absolute;
        top: -5px;
        right: -5px;
        color:#fff;
        font-weight: bold;
        z-index: 99999;
        width: 15px;
        height: 15px;
    }
    .success{
        color:#00b700;
    }
    .warning{
        color: #c44c00;
    }
    .error{
        color: #ff0000;
    }
    .weui-uploader_box .remove span{
        font-weight: bold;
        font-size: 14px;
        background: #000;
        border-radius:50% ;
    }
    .weui-uploader_box img{
        width: 90%;
        margin: 5px auto 0 auto;
        border-radius:10px ;
    }
    .weui-uploader_box .name{
        line-height: 25px;
        font-size: 12px;
        text-align: center;
    }
</style>
<!--页面主结构开始-->
<div id="app" class="mui-views">
    <div class="mui-view">
        <div class="mui-navbar">
        </div>
        <div class="mui-pages">
        </div>
    </div>
</div>
<!--页面主结构结束-->
<!--单页面开始-->
<div id="setting" class="mui-page">
    <div class="mui-navbar-inner mui-bar mui-bar-nav">
        <button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
        </button>
        <h1 class="mui-center mui-title"><c:if test="${types =='ylx'}">预立项报销申请</c:if><c:if test="${types =='lx'}">立项报销申请</c:if><c:if test="${types =='bm'}">部门报销申请</c:if><c:if test="${types =='fxs'}">非销售立项报销申请</c:if></h1>
    </div>
    <form class="frm" action="<c:choose> <c:when test="${types =='bm'}">${oa}/weixin/two/bmadd</c:when><c:when test="${types =='fxs'}">${oa}/weixin/two/fxsadd</c:when><c:otherwise>${oa}/weixin/two/add</c:otherwise> </c:choose> " method="post" enctype="multipart/form-data">
        <input type="hidden" name="proItemType" value="${types}">
        <input type="hidden" name="types" value="${types}">
        <input type="hidden" name="code" value="${code}">
        <!--页面主内容区开始-->
        <div class="mui-page-content">
            <div class="mui-scroll-wrapper">
                <div class="mui-scroll">
                    <div class="page__bd">
                        <div class="mui-content">
                            <c:if test="${fns:getUser().sale&&fns:getAccount(fns:getUser().id)!=null}">
                            <div class="title">
                                销售个人报销账户详情
                            </div>
                            <div class="mui-card">
                                <div class="mui-card-content">
                                    <div class="mui-input-row">
                                        <div class="weui-cell weui-cell_access" >
                                            <div class="weui-cell__bd">
                                                <span style="vertical-align: middle" >年销售额</span>
                                            </div>
                                            <div class="weui-cell__ft">￥${fns:formatNumber(fns:getAccount(fns:getUser().id).salesVolumeId.salesVolume)}元</div>
                                        </div>
                                        <div class="weui-cell weui-cell_access" >
                                            <div class="weui-cell__bd">
                                                <span style="vertical-align: middle" >每月额</span>
                                            </div>
                                            <div class="weui-cell__ft">￥${fns:getAccount(fns:getUser().id).branchQuota}元</div>
                                        </div>
                                        <div class="weui-cell weui-cell_access" >
                                            <div class="weui-cell__bd">
                                                <span style="vertical-align: middle" >本月已用</span>
                                            </div>
                                            <div class="weui-cell__ft">￥${fns:getAccount(fns:getUser().id).costIncurred}元</div>
                                        </div>
                                        <div class="weui-cell weui-cell_access" >
                                            <div class="weui-cell__bd">
                                                <span style="vertical-align: middle" >资金池</span>
                                            </div>
                                            <div class="weui-cell__ft">￥${fns:getAccount(fns:getUser().id).poolFunds}元</div>
                                        </div>
                                        <div class="weui-cell weui-cell_access" >
                                            <div class="weui-cell__bd">
                                                <span style="vertical-align: middle" >账户状态</span>
                                            </div>
                                            <div class="weui-cell__ft">
                                                <c:choose>
                                                    <c:when test="${fns:getAccount(fns:getUser().id).flag=='0'&&fns:getAccount(fns:getUser().id).costIncurred>=fns:getAccount(fns:getUser().id).branchQuota}">
                                                        <span class="warning">超出本月报销额度将使用资金池</span>
                                                    </c:when>
                                                    <c:when test="${fns:getAccount(fns:getUser().id).flag=='0'}">
                                                        <span class="success">正常</span>
                                                    </c:when>
                                                    <c:when test="${fns:getAccount(fns:getUser().id).flag=='1'}">
                                                        <span class="error">账户冻结不可以报销</span>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="weui-cell weui-cell_access" >
                                            <span class="warning">提示：资金池可溢出,溢出一定额度账户将被冻结，不能再报销！</span>
                                        </div>
                                    </div>
                            </div>
                            </div>
                            </c:if>
                            <c:if test="${fns:getUser().sale&&fns:getAccount(fns:getUser().id)==null}">
                                <div class="title">
                                    销售个人报销账户详情
                                </div>
                                <div class="mui-card">
                                    <div class="mui-card-content">
                                        <div class="mui-input-row">
                                            <div class="weui-cell weui-cell_access" >
                                                <span class="warning">您的年销售额未设定，请与公司销售总经理联系，设定年销售额！</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <%@include file="../fromOther.jsp" %>
                            <div class="title">
                                审核
                            </div>
                            <div class="mui-card">
                                <div class="mui-card-content">
                                    <div class="weui-cells weui-cells_form">
                                        <div class="weui-cell">
                                            <div class="weui-cell__bd">
                                                <div class="weui-uploader">
                                                    <div class="weui-uploader__hd">
                                                        <p class="weui-uploader__title ss"><c:choose><c:when test="${types=='bm'}">部门经理</c:when><c:when test="${types=='lx'}">项目经理</c:when><c:when test="${types=='fxs'  or types=='ylx'}">项目负责人</c:when></c:choose> </p>
                                                    </div>
                                                    <div class="weui-uploader__bd">

                                                        <div class="weui-uploader__input-box" style=" border-radius: 50%;">
                                                            <a href="#selectuser" id="group_one"
                                                               class="weui-uploader__input shr"></a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <c:if test="${cmh!=null}">
                                                    <div class="weui-uploader" id="cmh" <c:if test="${types=='bm'}">style="display: none;" </c:if>>
                                                        <div class="weui-uploader__hd">
                                                            <p class="weui-uploader__title">管理中心</p>
                                                        </div>
                                                        <div class="weui-uploader__bd">
                                                            <div class="weui-uploader_box">
                                                                <img src="${cmh.wxUsers.avatar}/100"/>
                                                                <div class="name">
                                                                        ${cmh.wxUsers.name}
                                                                </div>
                                                                <input type="hidden" value="${cmh.id}" name="group_two">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            <c:if test="${pz!=null}">
                                                <div class="weui-uploader">
                                                    <div class="weui-uploader__hd">
                                                        <p class="weui-uploader__title">总经理</p>
                                                    </div>
                                                    <div class="weui-uploader__bd">
                                                        <div class="weui-uploader_box">
                                                            <img src="${pz.wxUsers.avatar}/100"/>
                                                            <div class="name">
                                                                ${pz.wxUsers.name}
                                                            </div>
                                                            <input type="hidden" value="${pz.id}" name="group_three">
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:if test="${fz!=null}">
                                                <div class="weui-uploader">
                                                    <div class="weui-uploader__hd">
                                                        <p class="weui-uploader__title">总经理</p>
                                                    </div>
                                                    <div class="weui-uploader__bd">
                                                        <div class="weui-uploader_box">
                                                            <img src="${fz.wxUsers.avatar}/100"/>
                                                            <div class="name">
                                                                    ${fz.wxUsers.name}
                                                            </div>
                                                            <input type="hidden" value="${fz.id}" name="group_three">
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${!fns:getUser().sale || fns:getUser().sale&&fns:getAccount(fns:getUser())!=null&&fns:getAccount(fns:getUser().id).flag=='0'}">
                                    <div class="mui-card-footer">
                                        <button type="button" class="mui-btn mui-btn-primary mui-btn-block"
                                                onclick="sub();">提交
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!--页面主内容区结束-->
</div>
<div id="model" style="display: none;">
    <div>
        <div class="mui-input-row">
            <div class="weui-cell weui-cell_access" onclick="findjd(this)">
                <div class="weui-cell__bd">
                    <span style="vertical-align: middle">报销阶段</span>
                </div>
                <div class="weui-cell__ft z1">请选择</div>
                <input type="hidden" name="jd" value="0" class="zid">
            </div>
        </div>
        <div class="mui-input-row">
            <div class="weui-cell weui-cell_access" onclick="findlx(this)">
                <div class="weui-cell__bd">
                    <span style="vertical-align: middle" class="timeName">报销类型</span>
                </div>
                <div class="weui-cell__ft z">请选择</div>
                <input type="hidden" name="lx" value="0" class="zid">
            </div>
        </div>
        <div class="mui-input-row">
            <div class="mui-input-row weui-cell__bd">
                <label>报销金额</label>
                <input type="text" name="cost" class="mui-input-clear" onblur="numbers(this)" placeholder="请输入报销金额">
            </div>

        </div>
        <div class="mui-input-row">
            <div class="mui-input-row weui-cell__bd">
                <label>费用明细</label>
                <textarea class="weui-textarea fymx"  style="height: 100px;" placeholder="例如XXXX" name="fymx" cols="18" rows="2" ></textarea>
            </div>
        </div>
        <%--<div class="mui-card">
                               <div class="weui-cells">
                                   <div class="weui-cells weui-cells_form">
                                       <div class="weui-cell">
                                           <div class="weui-cell__bd">
                                               <div class="weui-uploader">
                                                   <div class="weui-uploader__hd">
                                                       <p class="weui-uploader__title">附件</p>
                                                   </div>
                                                   <div class="weui-uploader__bd">
                                                       <div class="weui-uploader__input-box">
                                                           <input class="weui-uploader__input uploaderInput"
                                                                  type="file" name="files"
                                                                 />
                                                       </div>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                           </div>--%>
        <div class="weui-cell weui-cell_access">
            <button type="button" class="mui-btn mui-btn-danger mui-btn-block" onclick="del(this)">删除</button>
        </div>
    </div>
</div>
<span id="err" style="display: none">${error}</span>
<%@include file="../../user_select.jsp" %>
<%--<%@include file="pro_select.jsp"%>--%>
<script type="text/javascript">
    $(document).keydown(function(event){
        if(event.enterkey == 1){
            alert('你按下了Enter');
        }
    })


    mui.init();
    if ($("#err").text() != '') {
        mui.toast($("#err").text());
    }
    //初始化单页view
    var viewApi = mui('#app').view({
        defaultPage: '#setting'
    });
    //初始化单页的区域滚动
    mui('.mui-scroll-wrapper').scroll();
    var view = viewApi.view;
    (function ($) {
        //处理view的后退与webview后退
        var oldBack = $.back;
        $.back = function () {
            if (viewApi.canBack()) { //如果view可以后退，则执行view的后退
                viewApi.back();
            } else { //执行webview后退
                oldBack();
            }
        };
        //监听页面切换事件方案1,通过view元素监听所有页面切换事件，目前提供pageBeforeShow|pageShow|pageBeforeBack|pageBack四种事件(before事件为动画开始前触发)
        //第一个参数为事件名称，第二个参数为事件回调，其中e.detail.page为当前页面的html对象
        view.addEventListener('pageBeforeShow', function (e) {
            inituser();
            //				console.log(e.detail.page.id + ' beforeShow');
        });
        view.addEventListener('pageShow', function (e) {

            //				console.log(e.detail.page.id + ' show');
        });
        view.addEventListener('pageBeforeBack', function (e) {
            //				console.log(e.detail.page.id + ' beforeBack');
        });
        view.addEventListener('pageBack', function (e) {
            //				console.log(e.detail.page.id + ' back');

        });
    })(mui);

    function addUser() {
        for (var i = 0; i < user.length; i++) {
            var b = true;
            $("#" + viewApi.nodeId).parent().parent().find("input[name='" + viewApi.nodeId + "']").each(function () {
                if($(this).val()==user[i].userid ){
                    b=false;
                }
            })
            if (b) {
                var obj = $('<div class="weui-uploader_box"><div class="remove"><span class="mui-icon mui-icon-closeempty" onclick="removeuser(this)"></span></div><img src="' + user[i].avatar + '/100"/><div class="name">' + user[i].username + '</div><input type="hidden" value="' + user[i].userid + '" name="' + viewApi.nodeId + '"></div>');
                $("#" + viewApi.nodeId).parent().before(obj.fadeIn(500));
            }
        }
    }
    function removeuser(obj){
        $(obj).fadeOut(300,function(){$(obj).parent().parent().remove()});
    }
    jsonzpro = [];

    type='${types}';
    var SaleDepartmentId='${SaleDepartmentId}';
    var $valuesjson = [];
    var resultMap;
    jsonzpro = [];
    var jd=[];
    var lx=[];
    if (type=='bm'){
        $(".proname").text("部门名字");
        $(".z1").parent().remove();
    }
    if (type=='fxs'){
        $(".timeName").text("子项目");
        $(".z1").parent().parent().remove();
    }
    $.get("/oa/weixin/bx/json?types=${types}", {}, function (result) {
        resultMap=result;
        for (var item in result) {
            $valuesjson.push({label: resultMap[item].name, value: resultMap[item].id});
        }
    })
    function findid(obj) {
        $this = $(obj);

        var s= $this.next().children().html();
        //$this.next().children().next().next().val("请选择");"请选择"
        weui.picker(
            $valuesjson
            , {
                onChange: function (result) {
                },
                onConfirm: function (result1) {
                    jd=[];
                    lx=[];
                    $(".zid").val("")
                    $(".z,.z1").html("请选择")
                    $this.children().next().html(result1[0].label);
                    $this.children().next().next().val(result1[0].value);
                   // var result=resultMap[result1[0].value];
                    if (type == 'bm'&&result1[0].value==SaleDepartmentId) {
                            $("#cmh").show();
                    }else {
                        if ($("#cmh")) {
                             $("#cmh").hide();
                        }
                    }
                    jsonzpro=[];
                    if (type == 'fxs') {
                        var result = resultMap[result1[0].value];
                        if (result.oaEosProStartItemList == undefined) {
                            mui.toast('该项目没有相关子项！');
                        }else  if ( result.oaEosProStartItemList.length>0) {
                            for (var i = 0; i < result.oaEosProStartItemList.length; i++) {
                                lx.push({
                                    label: result.oaEosProStartItemList[i].name,
                                    value: result.oaEosProStartItemList[i].id
                                });
                            }
                        }
                    }else{
                        for(var a in JSON.parse('${fns:findlistAll()}')){
                        lx.push({
                            label:  JSON.parse('${fns:findlistAll()}')[a],
                            value: a
                        })
                        }
                    for(var a in JSON.parse('${fns:findlist()}')){
                        jd.push({
                            label:  JSON.parse('${fns:findlist()}')[a],
                            value: a
                        })
                    }
                    }
                }
            });
    }
    function findjd(obj) {
        var $this = $(obj);
        weui.picker(jd, {
            onChange: function (result) {
            },
            onConfirm: function (result) {
                $this.children().next().html(result[0].label);
                $this.children().next().next().val(result[0].value)
            }
        });
    }

    function findlx(obj) {
        var $this = $(obj);
        weui.picker(lx, {
            onChange: function (result) {
            },
            onConfirm: function (result) {
                $this.children().next().html(result[0].label);
                $this.children().next().next().val(result[0].value)
            }
        });
    }

    var bool=true;
    function numbers(obj) {
        $this = $(obj);
        var reg = /(^([1-9]\d{0,9}|0)([.]?|(\.\d{1,2})?)$)/;
        var money = $this.val();
        if (money != null && money != '') {
            if (!reg.test(money)) {
                $this.focus();
                mui.toast('报销金额格式不正确！');
                bool= false;
            }else {
                bool=true;
            }
        }

    }




    function sub() {
        var flogf=true;
        $("form").find("input[name='fid']").each(function(index,obj){
            if($(obj).val()==''){
                flogf=false;
            }
        })


        if(!flogf){
            if (type=='bm'){
                mui.toast('请选择部门！');
            }else {
            mui.toast('请选择报销项目！');
            }
            return false;
        }
        var flogf=true;
        $("form").find("input[name='jd']").each(function(index,obj){
            if($(obj).val()==0){
                flogf=false;
            }
        })
        if(!flogf){
            mui.toast('请选择项目阶段！');
            return false;
        }
        var flogf=true;
        $("form").find("input[name='lx']").each(function(index,obj){
            if($(obj).val()==0){
                flogf=false;
            }
        })
        if(!flogf){
            mui.toast('请选择报销类型！');
            return false;
        }

        var flogf=true;
        $("form").find("input[name='cost']").each(function(index,obj){
            if($(obj).val()==0){
                flogf=false;
            }
        })
        if(!flogf){
            mui.toast('请输入报销金额！');
            return false;
        }
        if($("form").find("input[name='group_one']").length==0){
            mui.toast('请选择'+$(".ss").text()+'！');
            return false;
        }
        if (type=='bm'|| type=='fxs'){

        } else{
            if($("form").find("input[name='group_two']").length==0){
                mui.toast('请选择管理中心！');
                return false;
            }
        }

        if($("form").find("input[name='group_three']").length==0){
            mui.toast('请选择总经理！');
            return false;
        }

        if (!bool){
            mui.toast('报销金额格式不正确！');
            return false;
        }

        var btnArray = ['否', '是'];
        mui.confirm('是否提交报销单？', '系统提示', btnArray, function (e) {
            if (e.index == 1) {
                $(".frm").submit();
            } else {
            }
        })

    }

    $(".tj").click(function () {
        var html = $($("#model").html());
        $("#backItems").append(html.fadeIn(500));
    });

    function del(obj) {
        $(obj).parent().parent().remove();
    }



</script>
</body>
<script type="text/javascript" charset="UTF-8">
    function add(obj) {
    }
</script>
</html>
