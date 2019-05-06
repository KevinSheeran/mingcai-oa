<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息管理</title>
	<meta name="decorator" content="default"/>
	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/context.standalone.css" />
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/context.js"></script>
	<style>
		html,body{
			height:100%;
			width: 100%;
		<c:if test="${list.size()==0}">
			background:url("${ctxStatic}/windowsstyle/icon/nofile.png") no-repeat center;
			background-size: 10%;
		</c:if>
			background-color:#f5f5f5;
		}
		#deskIcon{
			margin: 0px;
			padding: 0px;
			width: 100%;
			height: auto;
		}
		#deskIcon li{
			display: block;
			float: left;
			width: 150px;
			height: 170px;
			text-align: center;
			padding: 10px;
			cursor: pointer;

		}
		#deskIcon li img{
			margin: 10px auto 0px;
			width: 80%;
		}
		#deskIcon li.dir img{
			margin: 10px auto 0px;
			width: 80%;
		}
		#deskIcon li:hover{
			background:url("/static/windowsstyle/images/icon_over.png") no-repeat center center;
			background-size: 100%;
		}

		#deskIcon li .text{
			margin-top:5px ;
			text-align: center;
			line-height: 18px;
			height: 36px;
			width: 100%;
			font-size: 16px;
			display: -webkit-box;
			display: -moz-box;
			-moz-box-orient: vertical;
			-moz-line-clamp: 2;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 2;
			overflow: hidden;
		}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	<br/>
	<ul id="deskIcon">
		<li title="新加项目">
			<a href="${ctx}/oa/oaProduct/form">
				<span class="icon"><img src="/static/windowsstyle/icon/ooopic_1517821507.png"/></span>
			</a>
		</li>
		<c:forEach items="${page}" var="oaProduct">
					<li class="dir" tid="${oaProduct.id}" onclick="openWin('${ctx}/oa/oaProductTask?product_id=${oaProduct.id}');" title="${oaProduct.name}&#10;创建日期:<fmt:formatDate value="${oaProduct.createDate}" pattern="yyyy年MM月dd日"/>&#10;创建人:${oaProduct.createBy.name}&#10;负责人:${oaProduct.updateBy.name}">
						<span class="icon"><img src="/static/windowsstyle/icon/ooopic_1517821175.png"/></span>
						<div class="text">
								${oaProduct.name}
						</div>
					</li>
		</c:forEach>
	</ul>
	<script type="text/javascript">
        $(document).ready(function() {

            var clickEle = "";
            function getClickEle(){ // 自定义方法给 调用者使用
                return clickEle;
            }
            $(document).on('contextmenu', '.dir,.file', function (e) {
                clickEle = $(this);//记录当前点击的element
            });
        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
        function openWin(url){
			self.location=url;
        }


	</script>
</body>
</html>