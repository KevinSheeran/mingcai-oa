<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目周报</title>
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
</style>

<div class="page"  style="margin: 0 auto;">
		<div id="form" style="width: 80%;margin: 0 auto;position: relative;overflow: hidden;padding: 20px 0px 10px;">
			<input type="text" name="createBy.name" style="width:70%;float: left;height: 35px;" placeholder="填报人"> <button type="button" class="btn " onclick="search();" style="width: 20%;float: left;margin-left: 3%;">搜索</button>
		</div>
		<div id="body"></div>
		<div onclick="pullupRefresh()" id="more" style="text-align: center;display: none;">点击加载更多</div>
</div>
<div id="model" style="display: none">
	<div class="mui-card">
		<div class="mui-card-header a"></div>
		<div class="mui-card-content">
			<div class="mui-card-content-inner">
				<div class="weui-form-preview" >
					<div class="weui-cells">
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<h4 >总结</h4>
							</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p class="b"></p>
							</div>
						</div>
					</div>
					<div class="weui-cells">
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<h4 >下周计划</h4>
							</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p class="c"></p>
							</div>
						</div>
					</div>
					<div class="weui-cells">
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<h4 >问题处理</h4>
							</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p class="d"></p>
							</div>
						</div>
					</div>
					<div class="weui-cells">
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<h4>意见建议</h4>
							</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p class="e"></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="mui-card-footer f"></div>
	</div>
</div>
<input type="hidden" value="${proid}" name="proid">
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
        $.get("${ctx}/oa/eos/oaEosProPresentation/pageJson",{proid:$("input[name='proid']").val(),pageNo:pageNo,pageSize:pageSize,"uername":$("input[name='createBy.name']").val()},function(data){
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
                model.find(".a").html(item.startEnd);
                model.find(".b").html(item.summary.replace(new RegExp("\n",'g'), " <br>"));
                model.find(".c").html(item.nextPlan.replace(new RegExp("\n",'g'), " <br>"));
                model.find(".d").html(item.problemHandle.replace(new RegExp("\n",'g'), " <br>"));
                model.find(".e").html(item.proposal.replace(new RegExp("\n",'g'), " <br>"));
                model.find(".f").html("填报人："+item.createBy.name);
                $("#body").append(model.fadeIn(500));
            }
            $("#more").show();
        })
    }
</script>
</body>
</html>