<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目报备管理</title>
	<meta name="decorator" content="default"/>
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
			color: #fff;
			background-color: #5bc0de;
			border-color: #46b8da;
		}
		.dir_success{
			color: #fff;
			background-color: #5cb85c;
			border-color: #4cae4c;
		}
		.dir_warning{
			color: #fff;
			background-color: #f0ad4e;
			border-color: #eea236;
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

</head>
<body>
<sys:message content="${message}"/>
<fieldset>
	<legend>
		<form:form id="searchForm" modelAttribute="oaCrmProduct" action="${ctx}/oa/crm/oaCrmProduct" method="post" class=" form-search">
		<div style="padding: 15px 10px; line-height: 0px;">
			<label>按名称搜索：</label>
			<div class="input-append " style="margin-bottom: 0px;">
				<input id="name" name="name"  placeholder="项目名称" type="text"  value="${oaCrmProduct.name}" data-msg-required="" class="input-small" >
				<a href="javascript:" class="btn" style="">&nbsp;<i class="icon-search" onclick="$('#searchForm').submit();"></i>&nbsp;</a>&nbsp;&nbsp;
			</div>
		</div>
		</form:form>
	</legend>
	<legend>
		<div style="padding: 15px 10px; line-height: 0px;">
			我的项目
		</div>
	</legend>
	<legend style="position: relative; overflow: hidden">
		<ul id="deskIcon">
			<li title="新加项目">
				<a href="${ctx}/oa/crm/oaCrmProduct/form">
					<span class="icon"><img src="/static/windowsstyle/icon/ooopic_1517821507.png"/></span>
				</a>
			</li>
			<c:forEach items="${page}" var="item">
				<li class="dir" tid="${oaProduct.id}" title="${item.name}&#10;创建日期:<fmt:formatDate value="${item.createDate}" pattern="yyyy年MM月dd日"/>&#10;负责人:${item.createBy.name}">

					<div class="status  <c:if test="${item.status==1||item.status==2}">dir_info</c:if><c:if test="${item.status==3||item.status==4||item.status==5}">dir_warning</c:if><c:if test="${item.status==6}">dir_success</c:if>">${fns:getDictLabel(item.status,'erm_p_status' , '')}</div>
					<a href="${ctx}/oa/crm/oaCrmProduct/form?id=${item.id}">
					<span class="icon"><img src="/static/windowsstyle/icon/ooopic_1517821175.png"/></span>
					<div class="text">
							${item.name}
					</div>
					</a>
				</li>
			</c:forEach>
		</ul>
	</legend>
	<legend>
		<div style="padding: 25px 10px 15px; line-height: 0px;">
			公共项目池
		</div>
	</legend>
	<legend style="position: relative; overflow: hidden">
		<c:if test="${openPage.size()==0}"><span style="color: #7b7b7b;margin-left:10px;font-size:14px;  ">暂无公开项目</span></c:if>

		<ul id="deskIcon">
			<c:forEach items="${openPage}" var="item">
				<li class="dir" tid="${oaProduct.id}" title="${item.name}&#10;创建日期:<fmt:formatDate value="${item.createDate}" pattern="yyyy年MM月dd日"/>&#10;负责人:${item.createBy.name}">
					<div class="status dir_warning">${fns:getDictLabel(item.status,'erm_p_status' , '')}</div>
					<a href="${ctx}/oa/crm/oaCrmProduct/form?id=${item.id}">
						<span class="icon"><img src="/static/windowsstyle/icon/ooopic_1517821175.png"/></span>
						<div class="text">
								${item.name}
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>

	</legend>
</fieldset>
</body>
</html>