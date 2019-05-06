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

</head>
<body>
<ul id="deskIcon">
	<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
		<c:if test="${not empty menu.href}">
		<li class="desktop_icon" id="win5" path="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}"><span class="icon"><img src="${ctxStatic}/windowsstyle/icon/icon4.png"/></span>
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
		<li id="app0"><img src="${ctxStatic}/windowsstyle/icon/icon1.png" title="Jquery API" path="http://www.jq22.com/?api/api.html"/></li>
		<li  id="app2"><img src="${ctxStatic}/windowsstyle/icon/icon0.png" title="学习资料库" path="http://www.jq22.com/?Material.aspx"/></li>
		<li id="app3"><img src="${ctxStatic}/windowsstyle/icon/icon2.png" title="QQ群堂" path="http://www.jq22.com/?QQGroup.aspx"/></li>
		<li id="app4"> <img src="${ctxStatic}/windowsstyle/icon/icon3.png" title="在线留言" path="http://www.jq22.com/?Feedback.aspx"/></li>
		<li id="app1"><img src="${ctxStatic}/windowsstyle/icon/icon11.png" title="Jquery学堂" path="http://www.jq22.com/?Jquery.aspx"/></li>
	</ul>
	<div id="default_tools"> <span id="showZm_btn" title="显示桌面"></span><span id="shizhong_btn" title="时钟"></span><span id="weather_btn" title="天气"></span> <span id="them_btn" title="主题"></span></div>
	<div id="start_block"> <a title="开始" id="start_btn"></a>
		<div id="start_item">
			<ul class="item admin">
				<li><span class="adminImg"></span> Admin</li>
			</ul>
			<ul class="item">
				<li id="set0" path="${ctx}/sys/user/info" title="系统设置"><span class="sitting_btn"></span>系统设置</li>
				<li id="set1"><span class="help_btn"></span>使用指南 <b></b></li>
				<li id="set2"><span class="about_btn"></span>关于我们</li>
				<li id="set3"><span class="logout_btn"></span>退出系统</li>
			</ul>
		</div>
	</div>
</div>
</div>
</body>
</html>