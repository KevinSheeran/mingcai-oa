<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}"/>
	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/style-c.css" />
	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/jsLib/themes/base/jquery.ui.all.css" />
	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/jsLib/jquery-smartMenu/css/smartMenu.css" />
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/jquery-1.6.2.js"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/myLib.js"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/jquery-ui-1.8.16.custom.min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/jquery.winResize.js"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/jsLib/jquery-smartMenu/js/mini/jquery-smartMenu-min.js"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/myjs.js"></script>
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
	</style>
</head>
<body>
<ul id="deskIcon">
	<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
		<c:if test="${not empty menu.href   &&menu.isShow=='1'}">
			<li class="desktop_icon" id="win${menu.id}" path="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}"><span class="icon"><img src="<c:if test="${menu.icon==null||menu.icon==null}">/static/windowsstyle/icon/add_icon.png</c:if><c:if test="${menu.icon!=null&&menu.icon!=null}">${menu.icon}</c:if>"/></span>
				<div class="text">${menu.name}
					<div class="right_cron"></div>
				</div>
			</li>
		</c:if>
	</c:forEach>
</ul>
<div id="taskBar">
	<div id="leftBtn"><a href="#" class="upBtn"></a></div>
	<div id="rightBtn"><a href="#" class="downBtn"></a> </div>
	<div id="task_lb_wrap">
		<div id="task_lb"></div>
	</div>
</div>
<div id="lr_bar">
	<ul id="default_app">
		<li id="win799c1f04c15a4b719ff3199c41505d5d"><img src="${ctxStatic}/windowsstyle/icon/icon8.png" title="工作任务" path="${ctx}/oa/oaProduct"/></li>
		<li id="win079a4ff0fd684080a25df50a86db1380"><img src="${ctxStatic}/windowsstyle/icon/icon10.png" title="文件管理" path="${ctx}/oa/oaProductResources"/></li>
		<li id="winsid" style="position: relative;"><img src="${ctxStatic}/windowsstyle/icon/icon3.png" title="我的任务" path="${ctx}/oa/oaProductTask/toUser"/><span class="red-point animated" id="taskCount">0</span></li>
	</ul>
	<div id="start_block"> <a title="开始" id="start_btn"></a>
		<div id="start_item">
			<ul class="item admin">
				<li><span class="adminImg"></span> ${fns:getUser().name}</li>
			</ul>
			<ul class="item" id="user_item">
				<li id="set0" path="${ctx}/sys/user/info" title="个人信息"><span class="sitting_btn"></span>个人信息</li>
				<li id="set1" path="${ctx}/sys/user/modifyPwd" title="修改密码"><span class="help_btn"></span>修改密码</li>
				<li class="loginout" onclick="return location.href='${ctx}/logout';" title="退出系统"><span class="logout_btn"></span>退出系统</li>
			</ul>
		</div>
	</div>
</div>
</div><!--推送-->
<script type="text/javascript" src="static/windowsstyle/ajax-pushlet-client.js"></script>
<script type="text/javascript">
    PL.joinListen('myevent1');
    var userno="key${fns:getUser().id}";
    function onData(event){
        var count=event.get(userno);
        if(count>0){
           $("#taskCount").show();
           $("#taskCount").html(count);
        }else
		{
            $("#taskCount").hide();
		}
    }
</script>
</body>
</html>