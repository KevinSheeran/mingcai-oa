<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	request.setAttribute("vEnter", "\n");
	//奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
	//都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<title>项目报备管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
	<link href="${ctxStatic}/bootstrap/bootstrap.css" rel="stylesheet" />
	<style>
		.content-body{
			display: block;
		}
	</style>
<script type="application/javascript">
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
<fieldset>
	<legend>
		<div class="pull-left" style="padding: 10px 10px; text-align: left ">
			<span ><a href="${ctx}/oa/crm/oaCrmProduct/applyList">申请审批列表</a>>>项目报备详情</span>
		</div>
		<div class="pull-right" style="padding: 10px 10px; text-align: left ">
						<a href="javascript:void(0)" status="${oaCrmProduct.status}" pid="${oaCrmProduct.id}" onclick=" applyPro(this);"><i class="icon-ok"></i>&nbsp;审批通过</a>
						<a href="${ctx}/oa/crm/oaCrmProduct/unAdopt?id=${oaCrmProduct.id}" onclick="return confirmx('确认要撤回申请吗？', this.href)"><i class="icon-reply"></i>&nbsp;撤回</a>
		</div>
	</legend>
</fieldset>
	<form:form id="inputForm" modelAttribute="oaCrmProduct" action="${ctx}/oa/crm/oaCrmProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<div class="content-body">${oaCrmProduct.name}</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户联系人：</label>
			<div class="controls">
				<div class="content-body">
						<c:forEach items="${oaCrmProduct.oaCrmCustomers}" var="item">
							联系人：${item.name}&nbsp;&nbsp;联系电话：${item.phone}
							<br/>
						</c:forEach>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<div class="content-body">${oaCrmProduct.remarks}</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
					${fns:getDictLabel(oaCrmProduct.status,'erm_p_status' , '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请人：</label>
			<div class="controls">
					${oaCrmProduct.updateBy.name}
			</div>
		</div>
	</form:form>
<br/>
<c:if test="${oaCrmProduct.id!=null}">
	<div class="col-lg-12">
		<section class="comment-list block">
			<article  class="comment-item media arrow arrow-left">
				<section class="media-body panel">
					<h4 style="padding: 10px ;padding-left:0px; color: #666;margin-bottom: 10px; border-bottom: 1px solid #eee;">
					跟进情况
					</h4>
					<c:forEach items="${inglist}" var="log" varStatus="index">
						<!-- .comment-list -->
						<section class="comment-list block">
							<article id="comment-id-1" class="comment-item media arrow arrow-left">

								<section class="media-body panel">
									<header class="panel-heading clearfix">
                                        <span class="pull-left thumb-small avatar number">${inglist.size()-index.index}</span>.&nbsp;&nbsp;<span>拜访时间：<fmt:formatDate value="${log.visitTime}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span href="javascirpt:void(0)">拜访方式：${fns:getDictLabel(log.type,'ing_type' ,'' )}</span>&nbsp;&nbsp;|&nbsp;&nbsp;负责人：${log.createBy.name}<span class="text-muted m-l-small pull-right"><i class="icon-time"></i>创建时间：<fmt:formatDate value="${log.createDate}" pattern="yyyy-MM-dd"/></span>
									</header>
									<div>${fn:replace(log.remarks,vEnter,"<br/>")}</div>
								</section>
							</article>
						</section>
					</c:forEach>
					<c:if test="${inglist.size()==0}">
						暂无进度信息
					</c:if>
				</section>
			</article>
		</section>
	</div>
</c:if>
</body>
</html>