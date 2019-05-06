<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目资源</title>
	<meta name="decorator" content="default"/>

	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/context.standalone.css" />
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/context.js"></script>
	<style>
		html,body{
			height:100%;
			width: 100%;

		<c:if test="${products.size()==0}">
			background:url("${ctxStatic}/windowsstyle/icon/nofile.png") no-repeat center;
			background-size: 10%;
		</c:if>
			background-color:#f5f5f5;
		}
		#deskIcon{
			margin: 0px;
			padding: 0px;
			width: 100%;
			height: 100%;
		}
		#deskIcon li{
			display: block;
			float: left;
			width: 150px;
			height: 160px;
			text-align: center;
			margin: 10px;
			cursor: pointer;
		}
		#deskIcon li img{
				width: 80%;
		}
		#deskIcon li:hover{
			background:url("/static/windowsstyle/images/icon_over.png") no-repeat center center;
			background-size: 100% 100%;
		}

		#deskIcon li .text{
			text-align: center;
			line-height: 16px;
			font-size: 16px;
		}
	</style>

</head>
<body>
	<sys:message content="${message}"/>
		<ul id="deskIcon">
			<c:forEach items="${products}" var="pro">
				<li class="desktop_icon" onclick="openFile('${pro.id}','${pro.name}','${pro.dirPath}')"><span class="icon"><img src="/static/windowsstyle/icon/icon10.png"/></span>
					<div class="text">${pro.name}
						<div class="right_cron"></div>
					</div>
				</li>
			</c:forEach>
		</ul>
	<script type="text/javascript">
        parent.relPath("${path}");
        parent.showIframe();
        function openFile(id,path,respath){
            parent.hideIframe();
            location.href="${ctx}/oa/oaProductResources/resources?resid="+id+"&path="+encodeURI(path)+"&respath="+encodeURI(respath);
		}
	</script>
</body>
</html>