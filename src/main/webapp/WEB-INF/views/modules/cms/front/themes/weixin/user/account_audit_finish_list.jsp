<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>明材办公-立项审批</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>
<div id="pullrefresh" class="mui-content mui-scroll-wrapper">
    <div class="mui-scroll ">
        <div class=" page__bd  mui-table-view mui-table-view-chevron" style="height: 100%;background: #fff;" id="body">
        </div>
    </div>
</div>
<div id="model" style="display: none">
<div class="weui-form-preview" >
    <div class="weui-form-preview__bd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label a"></label>
            </div>
            <div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">申请人</label>
                    <span class="weui-form-preview__value b"></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">使用金额比例</label>
                    <span class="weui-form-preview__value c"></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">申请日期</label>
                    <span class="weui-form-preview__value d"></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">处理状态</label>
                    <span class="weui-form-preview__value e"></span>
                </div>
            </div>
    </div>
    <div class="weui-form-preview__ft btn">
    </div>
</div>
</div>
<script type="text/javascript">
    function url(obj){
        var url=$(obj).attr("path");
        location.href=url;
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
        $.get("${oa}/weixin/userAccountApplyListJSON",{pageNo:pageNo,pageSize:pageSize},function(data){
            var fimdlist=data.list;
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((pageNo*pageSize >=data.count)); //参数为true代表没有更多数据了。
            pageNo++;
            for(var i=0;i<fimdlist.length;i++){
                var item=fimdlist[i];
                var status="";
                if(item.status==-1){status="驳回"};
                if(item.status==0){
                    status="申请未提交"
                };
                if(item.status==1){
                    status="申请中"
                };
                if(item.status==2){
                    status="申请完成"
                };
                var model=$($("#model").html());
                model.find(".a").html("年销售额￥"+formatMoney(item.salesVolumeId.salesVolume)+"元");
                model.find(".b").html('<img width="40" src="'+item.wxUsers.avatar+'/100"/>&nbsp;&nbsp;'+item.wxUsers.name);
                model.find(".c").html(item.salesVolumeId.uotaRatio+"%");
                model.find(".d").html(item.createDate);
                model.find(".e").html(status);
                model.find(".btn").html("<button class=\"weui-form-preview__btn weui-form-preview__btn_default\" type=\"button\" onclick=\"url(this)\" path=\"${oa}/weixin/accountApply?id="+item.id+"\">查看详情</button>");
                $("#body").append(model.fadeIn(500));
            }
        })
    }

</script>
</body>
</html>