<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}"/>
	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/style-c.css?time=<%=new Date().getTime()%>" />
	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/jsLib/themes/base/jquery.ui.all.css?time=<%=new Date().getTime()%>" />
	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/jsLib/jquery-smartMenu/css/smartMenu.css?time=<%=new Date().getTime()%>" />
    <link rel="stylesheet" href="${ctxStatic}/windowsstyle/animate.min.css?time=<%=new Date().getTime()%>" />
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/jquery-1.6.2.js?time=<%=new Date().getTime()%>"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/myLib.js?time=<%=new Date().getTime()%>"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/jquery-ui-1.8.16.custom.min.js?time=<%=new Date().getTime()%>"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/jquery.winResize.js?time=<%=new Date().getTime()%>"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/jquery-smartMenu/js/mini/jquery-smartMenu-min.js?time=<%=new Date().getTime()%>"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/myjs.js?time=<%=new Date().getTime()%>"></script>
	<script type="text/javascript">var ctx = '${ctx}', ctxStatic='${ctxStatic}';</script>
	<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
	<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
	<style>
		.animated {
			animation-duration: 1.5s; /*动画时间*/
			animation-fill-mode: both; /*播放后的状态*/
		}
		@keyframes container{
			0%,
			100%,
			20%,
			50%,
			80% {
				transition-timing-function: cubic-bezier(0.215,.61,.355,1); /*贝塞尔曲线 ： X1 Y1 X2 Y2*/
				transform: translate3d(0,0,0); /*设置只在Z轴上移动*/
			}
			40%,
			43%{
				transition-timing-function: cubic-bezier(0.755,0.50,0.855,0.060);
				transform: translate3d(0,-20px,0);
			}
			70%{
				transition-timing-function: cubic-bezier(0.755,0.050,0.855,0.060);
				transform: translate3d(0,-10px,0);
			}
			90%{
				transform: translate3d(0,-4px,0);
			}
		}
		.red-point{
			display: none;
			position: absolute;
			border-radius: 50%;
			width: 20px;
			height: 20px;
			line-height: 18px;
			text-align: center;
			color: white;
			background: red;
			right: 0px;
			top:0px;
			animation-iteration-count: infinite;
			animation-name:container; /*动画的名称*/
			transform-origin: center bottom; /*设置动画旋转元素的基点为：居中靠下*/
		}

		.oa_name{
			float: left;
			line-height: 50px;
			font-size: 40px;
			margin: 3% 0 0 5%;
		}
		.oa_user_info img{
			border-radius: 50%;
		}
		.oa_user_info{
				float: right;
				text-align: right;
				font-size:20px ;
				position: fixed;
				cursor: pointer;
				color: #fff;
				z-index: 100;
		}
		.display-model{
			background: -webkit-linear-gradient(top center, #391a6c , #032d7b); /* Safari 5.1 - 6.0 */
			background: -o-linear-gradient(top center, #391a6c, #032d7b); /* Opera 11.1 - 12.0 */
			background: -moz-linear-gradient(top center, #391a6c, #032d7b); /* Firefox 3.6 - 15 */
			background: linear-gradient(to bottom, #000 , #fff); /* 标准的语法 */
			background: -ms-linear-gradient(bottom,  #391a6c 0%,#032d7b 100%);
			FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#391a6c,endColorStr=#032d7b); /*IE 6 7 8*/
			background-position: 100% ;
			position: fixed;
			z-index:1;
			width:100%;
			height: 100%;
			overflow: hidden;
			top:0px;
			left:0px;
			filter:alpha(opacity=30); /*IE滤镜，透明度50%*/
			-moz-opacity:0.3; /*Firefox私有，透明度50%*/
			opacity:0.3;/*其他，透明度50%*/
		}
		body,html{
			overflow: hidden;

		}
		.message_alert{
			position: fixed;
			right:5%;
			top:6%;
            z-index: 100;
            display: none;
            width:50px;
            cursor: pointer;
		}
		.message_money{
			position: fixed;
			right:20%;
			top:6%;
			z-index: 100;
			display: block;
			width:50px;
			cursor: pointer;
		}
		.wow-hide{
			display: none;

		}
		video{
			object-fit:fill;
		}

		#bgVideo,#bgImage{
			display: none;
			top:0px;left:0px;
			z-index: -2;
			position:absolute;
			overflow: hidden;
			width: 100%; height: 100%;
		}
		#bgImage img,#bgVideo video{
			width: 100%; height: 100%;
		}

	</style>

</head>
<body  style="position: relative; display: none;">
<div id="bgVideo" style="display: block;">
	<video id="video" controls autoplay loop>
		<source src="/static/video/video1.mov">
	</video>
</div>
<div id="bgImage">
	<img src="${fns:getUser().userBackground}?time=<%=new Date().getTime()%>">
</div>

<div class="display-model"></div>
<c:if test="${finance}">
<div class="message_money grid"  id="win_uid"  title="账户信息" path="${ctx}/oa/user/oaUserAccount/userindex"><img src="${ctxStatic}/windowsstyle/icon/23.png" /></div>
</c:if>
<div class="message_alert grid"  id="win_sid"  title="我的任务" path="${ctx}/oa/oaProductTask/toUser"><img src="${ctxStatic}/windowsstyle/icon/icon3.png" /><span class="red-point animated" id="taskCount">0</span></div>
<div class="oa_user_info" id="start_block">
	<div  id="start_btn">
		<span>${fns:getUser().name}</span>
		<img id="user_image" src="<c:if test="${fns:getUser().wxUsers==null||fns:getUser().wxUsers.avatar==''}">/static/windowsstyle/icon/user-male-icon.png</c:if><c:if test="${fns:getUser().wxUsers!=null&&fns:getUser().wxUsers.avatar!=''}">${fns:getUser().wxUsers.avatar}/100</c:if>" width="50px">
	</div>
	<div id="start_item">
		<ul class="item" id="user_item">
			<li id="set0" path="${ctx}/sys/user/info" title="个人信息"><span class="sitting_btn"></span><span class="title">个人信息</span></li>
			<li id="set1" path="${ctx}/sys/user/modifyPwd" title="修改密码"><span class="help_btn"></span><span class="title">修改密码</span></li>
			<li class="loginout" onclick="return location.href='${ctx}/logout';" title="退出系统"><span class="logout_btn"></span><span class="title">退出系统</span></li>
		</ul>
	</div>
</div>
<ul id="deskIcon" style="overflow: hidden;position: relative;z-index: 99;">
	<li style="width: 100%;color:#fff;position: relative; clear: left;position: relative;overflow: hidden;">
			<div class="oa_name">
				${fns:getConfig('productName')}
			</div>
	</li>
	<li  style="position: relative;overflow-x: auto;" id="grids">
		<div class="layout-middle" id="layout-middle" >
			<div id="channel-list">
				<c:forEach items="${menus}" var="menu" varStatus="index">
					<div id="home" class="channel ">
						<div class="channel-title wow-hide wow fadeInLeft" data-wow-delay="0.${index.index }s"><h2>${menu.label}</h2></div>
						<c:forEach items="${menu.meus}" var="item" varStatus="odb">
							<c:if test="${item.isShow=='1'}">
								<div data-wow-delay="0.${odb.index }s" class="wow-hide wow fadeIn grid ${item.cssStyle} ${item.cssColor} <c:if test="${empty item.href}">disabled</c:if>" id="win_${item.id}" title="${item.name}" path="${ctx}${item.href}">
									<img src="${item.icon}">
									<div class="menu-title">${item.name}</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="pre" onclick="scroll_To(0,1)"></div>
		<div id="next" onclick="scroll_To(0,2)"></div>
	</li>
</ul>

<div id="taskBar" style="z-index: 999;">
	<div id="leftBtn"><a href="#" class="upBtn"></a></div>
	<div id="rightBtn"><a href="#" class="downBtn"></a> </div>
	<div id="task_lb_wrap">
		<div id="task_lb"></div>
	</div>
</div>

<!--推送-->
<script type="text/javascript" src="static/windowsstyle/ajax-pushlet-client.js?time=<%=new Date().getTime()%>"></script>
<script type="text/javascript" src="static/windowsstyle/jquery.mousewheel.min.js?time=<%=new Date().getTime()%>"></script>
<script type="text/javascript" src="static/windowsstyle/wow.min.js?time=<%=new Date().getTime()%>"></script>
<script type="text/javascript">
    new WOW().init();
    $(".wow-hide").fadeIn();
    var isscoll=true;
    $(function(){
        var video=document.getElementById("video");
        video.controls=false;
        alert_position();
        $(window).resize(function() {
            alert_position();
        });
        $('body').mousewheel(function(event, delta) {
            var dir = delta > 0 ? 'Up' : 'Down';
            if (isscoll) {
                if (dir == 'Up') {
                    scroll_To(0, 1);
                } else {
                    scroll_To(0, 2);
                }
             }
            return false;

        });
$("#bgImage img").load(function(){
    //$("#bgImage").fadeIn(1000);
})
    })
   function alert_position(){
       $(".message_alert").css({"left":($("#start_block").offset().left-60)+"px","top":($("#start_block").offset().top+15)+"px"})
   }

    PL.joinListen('myevent1');
    var userno="key${fns:getUser().id}";
    function onData(event){
        var count=event.get(userno);
        if(count>0){
            $("#taskCount").show();
            $("#taskCount").html(count);
            $(".message_alert").show();
        }else
        {
            $("#taskCount").hide();
            $(".message_alert").hide();
        }
    }
    //滚动条函数封装
    function scroll_To(tar_y,t){ //tar_y 即滑动条顶端 距离页面最上面的距离
		if(t==1){
				$("#pre").fadeOut(800);
           		 $("#next").fadeIn(800);
		}
		if(t==2){
				$("#next").fadeOut(800);
            	$("#pre").fadeIn(800);
                tar_y= $("#layout-middle").width()*1.03-$(window).width();
		}
        isscoll=false;
        $("#grids").animate({scrollLeft:tar_y+'px'},800,function(){
            isscoll=true;
        });
    }
</script>
</body>

</html>