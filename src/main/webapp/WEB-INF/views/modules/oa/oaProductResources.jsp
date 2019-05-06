<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目资源</title>
	<meta name="decorator" content="default"/>

	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		html,body{
			height:100%;
			background: rgba(0,0,0,0);
		}
	</style>
</head>
<body>
	<sys:message content="${message}"/>
		<form:form id="searchForm" modelAttribute="oaProductTask" action="${ctx}/oa/oaProductResources/search" onsubmit="return search(this);" method="post" cssStyle="margin-bottom:1px;" class="breadcrumb form-search">
			<ul class="ul-form" style="">
				<li style="float: inherit;height: auto;">
						<label style="margin-left: 0px;text-align: left;width:auto;">路径：</label>
						<input id="path" name="path" readonly="readonly" maxlength="100" value="/" style="width: 40%;" class="required"/>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<label style="margin-left: 0px;text-align: left;width:auto;">查询信息：</label>
						<input id="name" name="name" maxlength="100" class="input-medium"/>
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="搜索"/>
					</div>
				</li>
			</ul>
		</form:form>
		<iframe id="officeContent" src="${ctx}/oa/oaProductResources/product"  width="100%" height="91%" frameborder="0"></iframe>
	<script type="text/javascript">
	</script>
	<script type="text/javascript">
		function relPath(path){
		    $("#path").val(path);
		}
		function hideIframe(){
		    $("#officeContent").hide();
		}
        function showIframe(){
            $("#officeContent").show();
        }
        function search(obj) {
            if ($.trim($("input[name='name']").val()) == '') {
                $("#officeContent").attr("src", '${ctx}/oa/oaProductResources/product');
            } else {
            	$("#officeContent").attr("src", $(obj).attr("action") + "?name=" + encodeURI($("input[name='name']").val()));
       		 }
            return false;
		}
	</script>
</body>
</html>