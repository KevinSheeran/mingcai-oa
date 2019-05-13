<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.items{
			width: 50%;
			float: left;
		}
		.items label{
			margin: 5px 10px;
		}
		.warp{
			padding: 5px 5px 5px 10px;
			color:#a5360f;

		}
		.search{
			padding:10px 15px ;
			overflow: hidden;
		}
		.search input{
			width: 300px;
			line-height: 25px;
			float: left;
		}
		.search button{
			float: left;
			margin-left: 15px;
		}
	</style>
</head>
<body>
	<div class="search">
		<input type="text" id="searchVal"/>
		<button onclick="search();" class="btn ">搜索</button>
	</div>
	<form:form id="inputForm" modelAttribute="oaProcInventory" action="${ctx}/oa/proc/oaProcInventoryFormSelectList" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="proItemId" />
		<form:hidden path="proId"/>
		<c:forEach items="${page}" var="item">
			<div class="items">
				<label>
					<input type="checkbox" name="item" value="${item.id}"
							<%--<c:if test="${item.proId!=null&&item.proId!=''}">
						checked </c:if>--%> />
					名称：<span>${item.name}</span>&nbsp;规格:<span>${item.specifications}</span>
				</label>
			</div>
		</c:forEach>
	</form:form>
<script type="application/javascript">
	function search(){
		var text=$("#searchVal").val();
		$(".items").each(function(){
		    var isshow=false;
		    $(this).find("span").each(function(){
		        if($(this).html().indexOf(text)!=-1){
                    isshow=true;
				}
			});
		    if(!isshow){
		        $(this).hide();
			}
            if(text==""){
                $(this).show();
			}

		})
	}
</script>
</body>
</html>