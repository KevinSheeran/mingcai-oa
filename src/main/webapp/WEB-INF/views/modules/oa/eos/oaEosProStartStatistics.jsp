<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目统计</title>
	<meta name="decorator" content="default"/>
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
			width: 120px;
			height: 170px;
			text-align: center;
			padding: 10px;
			margin-left: 10px;
			cursor: pointer;
			position: relative;
		}
		.dir_info{
			color: #5bc0de;
		}
		.dir_success{
			color: #5cb85c;
		}
		.dir_warning{
			color: #f0ad4e;
		}
		#deskIcon li .status{
			position: absolute;
			top:12px;
			right: -8px;
			font-size: 12px;
			color: #fff;
			padding: 3px 5px;

		}
		#deskIcon li img{
			margin: 10px auto 0px;
			width: 100%;
		}
		#deskIcon li.dir img{
			margin: 10px auto 0px;
			width: 100%;
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
	<script type="text/javascript">
        $(document).ready(function() {

        });
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
	</script>
</head>
<body>
<sys:message content="${message}"/>
<fieldset>
<h1><a href="${ctx}/oa/eos/sta/getbycw?id=${id}">财务统计</a></h1>
</fieldset>
</body>
</html>