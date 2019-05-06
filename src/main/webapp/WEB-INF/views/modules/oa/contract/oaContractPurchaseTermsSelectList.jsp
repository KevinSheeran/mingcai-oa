<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购合同关联管理</title>
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
		<input type="text" id="searchVal"/><button onclick="search();" class="btn ">搜索</button>
	</div>
	<div class="warp">注意：已经生成的采购清单不可取消，如需取消请在清单列表中直接删除</div>
	<form:form id="inputForm" modelAttribute="oaContractPurchaseTerms" action="${ctx}/oa/contract/oaContractPurchaseInfo/save" method="post" class="form-horizontal">
		<form:hidden path="purchaseId"/>
		<form:hidden path="finProductId"/>
		<c:forEach items="${page}" var="item">
			<div class="items"><label><input type="checkbox" name="trem" value="${item.tremId}"  <c:if test="${item.id!=null&&item.id!=''}">checked </c:if><c:if test="${item.number-item.countNumber==0||item.id!=null&&item.id!=''}">disabled</c:if> />编号：<span>${item.code}</span>&nbsp;产品:<span>${item.name}</span>&nbsp;剩余采购：${item.number-item.countNumber}${item.unit}</label></div>
		</c:forEach>
	</form:form>
<script>
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