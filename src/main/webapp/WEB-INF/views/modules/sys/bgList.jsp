<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>图标选择</title>
	<meta name="decorator" content="blank"/>
    <style type="text/css">
		html,body{
			text-align: center;
		}
		#imageList{
			width: 70%;
			min-height: 50%;
			margin: 15px auto 0;
			overflow: hidden;
		}
		#image_black{
			width: 50%;
			margin: 0 auto;
			background: url(/static/windowsstyle/images/loading.gif) no-repeat center center;
			background-color: #f5f5f5;
		}
		#image_black img{
			width: 100%;
			height: 100%;
		}
		.title{
			width: 70%;
			margin: 15px auto 0;
			font-size: 18px;
			text-align: left;
			overflow: hidden;
		}
		#icons{
			 	overflow-x: auto;
				height: 100px;
		 }
		.the-icons {padding:10px 0px 15px;margin:0px; list-style:none; }
		.the-icons li {float:left;width:100px; height:59px;  padding:5px 5px;cursor:pointer; }
		.the-icons li img{
			width: 100%;
			height: 100%;
			background-color: #f5f5f5;
		}
		.the-icons li.active {background-color:#0088CC;color:#ffffff;}
    </style>
    <script type="text/javascript">
	    $(document).ready(function(){
	    	$("#icons li").click(function(){
	    		$("#icons li").removeClass("active");
	    		$(this).addClass("active");
                $("#web_bg").hide();
                $("#web_bg").attr("src","/static/windowsstyle/wallpapers/"+$(this).attr("val"));
                $("#web_bg").load(function(){
                    $(this).fadeIn(1000);
                })
	    	});
	    	$("#icons ul li img").each(function(){
	    	    $(this).load(function(){
                    $(this).fadeIn(1000);
                })
			})
            $("#web_bg").show();
            $("#web_bg").load(function(){
                $(this).fadeIn(1000);
            })
	    });

	    function setBg(){
            loading('正在提交，请稍等...');
            $.post("${ctx}/sys/user/updateBG",{"bg":$("#web_bg").attr("src")},function(data){
                var data=JSON.parse(data);
              if(data.success){
                  loading('设置成功...');
                  top.$("#bgImage").attr("src",$("#web_bg").attr("src"));
                  setTimeout(function(){closeLoading();},1000);
			  }else{
                  loading('设置识别...');
                  setTimeout(function(){closeLoading();},1000);
			  }
			})

		}
    </script>
</head>
<body>
<br/>
<div id="image_black">
	<img style="display: none;" src="<c:if test="${fns:getUser().userBackground!=null&&fns:getUser().userBackground!=''}">${fns:getUser().userBackground}</c:if> <c:if test="${fns:getUser().userBackground==null||fns:getUser().userBackground==''}">/static/windowsstyle/wallpapers/9968383.jpg</c:if>" id="web_bg">
</div>
	<div class="title"><span class="pull-left">应用背景</span> <span class="pull-right" style="color: #0B61A4; cursor: pointer;" onclick="setBg();"><i class="icon-ok"></i>&nbsp;设置为背景</span></div>
	<div id="imageList">
		<div id="icons">
			<ul class="the-icons">
				<c:forEach items="${bgList}" var="item" varStatus="var">
					<li <c:if test="${(fns:getUser().userBackground==null||fns:getUser().userBackground=='')&&var.index==0}"> class="active" </c:if> <c:if test="${fns:getUser().userBackground.indexOf(item)!=-1}"> class="active" </c:if> val="${item}"><img src="/static/windowsstyle/wallpapers/bg150-85/${item}"></li>
				</c:forEach>
			</ul>
		</div>
	</div>
<script type="application/javascript">
    $(".the-icons").css({"width":"${bgList.size()*110}px"});
	$(function(){
        resBg();
        $(window).resize(function() {
            resBg();
        });

	})
function resBg(){
    $("#image_black").height($("#image_black").width()/1.77);
}
</script>
</body>