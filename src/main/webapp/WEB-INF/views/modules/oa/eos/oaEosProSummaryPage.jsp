<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目工时</title>
	<meta name="decorator" content="cms_default_audit" />
</head>
<body>
<style>
	h4{
		font-size: 14px;
	}
	#more{padding: 10px;}
	#more:hover{
		color: #157ab5;
		cursor: pointer;
	}
    .mui-grid-view.mui-grid-9 .mui-media .mui-icon{
        font-size: 24px;
    }
</style>

<div class="page"  style="margin: 0 auto;">
	<div class="weui-form-preview"  style="position: relative; z-index: 999999">
		<div class="weui-form-preview__bd">
			<div class="weui-form-preview__item">
				<div class="weui-form-preview__item">
					<ul class="mui-table-view mui-grid-view mui-grid-9" id="adds" style="background-color: transparent;border:0px;">
						<li class="mui-table-view-cell mui-media mui-col-xs-4 " style="background-color: transparent;border:0px;" id="psTotal" type="1">
							<span class="mui-icon ">${total.psTotal==null?0:total.psTotal}小时</span>
							<div class="mui-media-body">售前</div>
						</li>

						<li class="mui-table-view-cell mui-media mui-col-xs-4 " style="background-color: transparent;border:0px;" id="saleTotal" type="1">
							<span class="mui-icon">${total.saleTotal==null?0:total.saleTotal}小时</span>
							<div class="mui-media-body">实施</div>
						</li>

						<li class="mui-table-view-cell mui-media mui-col-xs-4 " style="background-color: transparent;border:0px;"  id="rdTotal" onclick="tofrom(3)">
							<span class="mui-icon ">${total.rdTotal==null?0:total.rdTotal}小时</span>
							<div class="mui-media-body">研发</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
		<div id="body"></div>
		<div onclick="pullupRefresh()" id="more" style="text-align: center;display: none;">点击加载更多</div>
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
<script type="text/javascript">
    var pageNo=1;
    var pageSize=2;
    pullupRefresh();
    function search(){
        pageNo=1;
        $("#body").html("");
        $("#more").attr("onclick","pullupRefresh()");
        $("#more").html("点击加载更多");
        $("#more").removeClass("disabled")
        pullupRefresh();
	}
    function pullupRefresh(){
        $.get("${ctx}/oa/eos/oaEosProTimetotalItem/pageJson",{proid:'${proid}',pageNo:pageNo,pageSize:pageSize,"uername":$("input[name='createBy.name']").val()},function(data){
            data=JSON.parse(data);
            var fimdlist=data.list;
            if(pageNo*pageSize >=data.count){
                $("#more").attr("onclick","");
                $("#more").html("没有数据");
                $("#more").addClass("disabled")
            }
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
            $("#more").show();
        })
    }
</script>
</body>
</html>