<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>明材办公-工时列表</title>
    <meta name="decorator" content="cms_default_${site.theme}" />
    <meta name="description" content="JeeSite ${site.description}" />
    <meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>
<style>
    .mui-icon .mui-badge{
        margin-left: -25px;
    }

</style>
<input type="hidden" value="${date}" id="date">
<div class="page">
<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
    <div class="mui-scroll ">
        <div class="weui-form-preview"  style="position: relative; z-index: 999999">
            <div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">项目</label>
                        <span class="weui-form-preview__value b">${pro.name}</span>
                    </div>
                    <div class="weui-form-preview__item">
                        <label class="weui-form-preview__label">编号</label>
                        <span class="weui-form-preview__value b"><c:if test="${pro.proNumber!=null&&pro.proNumber!=''}">${pro.proNumber}</c:if><c:if test="${pro.proNumber==null||pro.proNumber==''}">${pro.paNumber}</c:if></span>
                    </div>
                    <c:if test="${summaryedit}">
                    <div class="weui-form-preview__item">
                        <ul class="mui-table-view mui-grid-view mui-grid-9" id="adds" style="background-color: transparent;border:0px;">
                            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="background-color: transparent;border:0px;" id="psTotal" type="1">
                                <span class="mui-icon mui-icon-plus"><span class="mui-badge">${total.psTotal==null?0:total.psTotal}</span></span>
                                <div class="mui-media-body">售前</div>
                            </li>

                            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="background-color: transparent;border:0px;" id="saleTotal" type="1">
                                <span class="mui-icon mui-icon-plus"><span class="mui-badge">${total.saleTotal==null?0:total.saleTotal}</span></span>
                                <div class="mui-media-body">实施</div>
                            </li>

                            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3" style="background-color: transparent;border:0px;"  id="rdTotal" onclick="tofrom(3)">
                                <span class="mui-icon mui-icon-plus"><span class="mui-badge">${total.rdTotal==null?0:total.rdTotal}</span></span>
                                <div class="mui-media-body">研发</div>
                            </li>
                        </ul>
                    </div>
                    </c:if>
                </div>
            </div>
        </div>
            <div class=" page__bd  mui-table-view mui-table-view-chevron" style="height: 100%;background: #fff;" id="body">
            </div>
    </div>
</div>
</div>
<div id="model" style="display: none">
    <div class="weui-form-preview" >
        <div class="weui-form-preview__hd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label a">2019-02-19至2019-02-26</label>
            </div>
        </div>
        <div class="weui-form-preview__bd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">售前</label>
                <span class="weui-form-preview__value b">40小时</span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">实施</label>
                <span class="weui-form-preview__value c">40小时</span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">研发</label>
                <span class="weui-form-preview__value d">44小时</span>
            </div>
        </div>
    </div>

</div>
<script >

    var obj = document.getElementById("psTotal");
    obj.addEventListener("touchstart", showMsg);
    function showMsg(ev) {
        location.href="${oa}/weixin/summaryfrom?proid=${proid}&type=${type}&userType=1";
    }
    var obj1 = document.getElementById("saleTotal");
    obj1.addEventListener("touchstart", showMsg1);
    function showMsg1(ev) {
        location.href="${oa}/weixin/summaryfrom?proid=${proid}&type=${type}&userType=2";
    }
    var obj2 = document.getElementById("rdTotal");
    obj2.addEventListener("touchstart", showMsg2);
    function showMsg2(ev) {
        location.href="${oa}/weixin/summaryfrom?proid=${proid}&type=${type}&userType=3";
    }
    mui.init({
        pullRefresh: {
            container: '#pullrefresh',
            up: {
                auto:true,
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });
    var pageNo=1;
    var pageSize=5;
    function pullupRefresh(){
        $.get("${oa}/weixin/summaryListJson",{proid:"${proid}",type:"${type}",pageNo:pageNo,pageSize:pageSize},function(data){
            var fimdlist=data.list;
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((pageNo*pageSize >=data.count)); //参数为true代表没有更多数据了。
            pageNo++;
            for(var i=0;i<fimdlist.length;i++){
                var item=fimdlist[i];
                var model=$($("#model").html());
                var title="";
                if($("#date").val()==item.times){
                    title="本周：";
                }
                model.find(".a").html(title+item.times);
                model.find(".b").html(item.psTotal+"小时");
                model.find(".c").html(item.saleTotal+"小时");
                model.find(".d").html(item.rdTotal+"小时");
                $("#body").append(model.fadeIn(500));
            }
        })
    }
</script>
</body>
</html>