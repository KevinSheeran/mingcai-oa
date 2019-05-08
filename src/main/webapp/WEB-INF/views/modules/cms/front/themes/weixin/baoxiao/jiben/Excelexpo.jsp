<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<html>
<head>
    <title>明材办公-审批</title>
    <meta name="decorator" content="cms_default_${site.theme}"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
</head>
<body>
<style>
    html,
    body,
    .mui-content {
        height: 0px;
        margin: 0px;
        background-color: #efeff4;
    }
    h5.mui-content-padded {
        margin-left: 3px;
        margin-top: 20px !important;
    }
    h5.mui-content-padded:first-child {
        margin-top: 12px !important;
    }
    .mui-btn {
        font-size: 16px;
        padding: 8px;
        margin: 3px;
    }
    .ui-alert {
        text-align: center;
        padding: 20px 10px;
        font-size: 16px;
    }
    * {
        -webkit-touch-callout: none;
        -webkit-user-select: none;
    }
</style>

 <form:form id="searchForm" modelAttribute="OaWxBxCorrelationSuper" action="/oa/weixin/two/export" method="get" class="breadcrumb form-search ">

<div class="mui-card">
    <div class="mui-card-content">
        <div class="mui-input-row">
            <div class="weui-cell weui-cell_access" onclick="findid(this)">
                <div class="weui-cell__bd">
                    <span style="vertical-align: middle" class="proname">开始时间</span>
                </div>
                <div class="weui-cell__ft f">请选择</div>
                <input type="hidden" value="" name="ks" class="ks">
            </div>
        </div>

        <div class="mui-input-row">
        <div class="weui-cell weui-cell_access" onclick="findid(this)">
            <div class="weui-cell__bd">
                <span style="vertical-align: middle" class="proname">结束时间</span>
            </div>
            <div class="weui-cell__ft f">请选择</div>
            <input type="hidden" value="" name="js" class="ks">
        </div>
    </div>
</div>
    <div class="mui-card-footer">
        <button type="button" id="btnExport" class="mui-btn mui-btn-primary mui-btn-block" >导出
        </button>
    </div>
</div>
 </form:form>
<script>
 function findid(obj){
     $this=$(obj);
     weui.datePicker({
         start: 2010,
         end: new Date().getFullYear(),
         onChange: function (result) {
         },
         onConfirm: function (result) {
             $this.children().next().text(result);
             $this.children().next().next().val(result)
         }
     });
 }

 function findid(obj){
     $this=$(obj);
     weui.datePicker({
         start: 2010,
         end: new Date().getFullYear(),
         onChange: function (result) {
         },
         onConfirm: function (result) {
             $this.children().next().text(result);
             $this.children().next().next().val(result)

         }
     });
 }
    $(document).ready(function() {
        $("#btnExport").click(function(){
            //  top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
            //   if(v=="ok"){
            $("#searchForm").attr("action","/oa/weixin/two/export");
            $("#searchForm").submit();
            //  }
            //  },{buttonsFocus:1});
            //top.$('.jbox-body .jbox-icon').css('top','55px');
        });
    });
</script>
</body>
</html>
