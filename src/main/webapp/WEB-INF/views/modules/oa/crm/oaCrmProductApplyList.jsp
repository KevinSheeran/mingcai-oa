<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目报备申请管理</title>
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
        function applyPro(obj){
            var status=$(obj).attr("status");
			if(status==4){
				confirmx('确认通过审核吗？', "${ctx}/oa/crm/oaCrmProduct/updateAdopt?id="+$(obj).attr("pid"));
			}else{
                $.jBox.open("iframe:${ctx}/oa/crm/oaCrmProduct/addform?id="+$(obj).attr("pid"), "项目立项", 800, $(window).height()-150, {
                    buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                        if (v=="ok"){
                            loading('正在提交，请稍等...');
                            var win=h.find("iframe")[0].contentWindow;
                            $.ajax({
                                url: '${ctx}/oa/crm/oaCrmProduct/updateAdoptE',
                                type: 'POST',
                                cache: false,
                                data: new FormData( h.find("iframe")[0].contentWindow.$("#inputForm")[0]),
                                processData: false,
                                contentType: false
                            }).done(function(res) {
                                loading('立项审核成功');
                                setTimeout(function(){window.location.reload();},3000);
                            }).fail(function(res) {});
                        }
                    }, loaded:function(h){
                        $(".jbox-content", document).css("overflow-y","hidden");
                    }
                });
			}
            return false;
		}
	</script>
</head>
<body>
<form:form id="searchForm" modelAttribute="oaCrmProduct" action="${ctx}/oa/crm/oaCrmProduct/applyList" method="post" class="breadcrumb form-search">
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<ul class="ul-form">
		<li><label>名称：</label>
			<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
		</li>
		<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
		<li class="clearfix"></li>
	</ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th>项目名称</th>
		<th>申请人</th>
		<th>申请时间</th>
		<th>状态</th>
		<th>描述信息</th>
		<th>操作</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${page.list}" var="oaCrmProduct">
		<tr>
			<td>
				${oaCrmProduct.name}
			</td>
			<td>
					${oaCrmProduct.updateBy.name}
			</td>
			<td>
				<fmt:formatDate value="${oaCrmProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>
					${fns:getDictLabel(oaCrmProduct.status,'erm_p_status' , '')}
			</td>
			<td>
					${oaCrmProduct.remarks}
			</td>
			<td>
				<a href="${ctx}/oa/crm/oaCrmProduct/applyform?id=${oaCrmProduct.id}">查看项目信息</a>
				<a href="javascript:void(0)" status="${oaCrmProduct.status}" pid="${oaCrmProduct.id}" onclick=" applyPro(this);">${fns:getDictLabel(oaCrmProduct.status,'erm_p_status' , '')}通过</a>
				<a href="${ctx}/oa/crm/oaCrmProduct/unAdopt?id=${oaCrmProduct.id}" onclick="return confirmx('确认要撤回申请吗？', this.href)">撤回</a>
			</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>