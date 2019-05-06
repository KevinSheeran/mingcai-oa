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
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
        function editInfo(obj){
            $(".content-body").hide();
            $(".input-body").show();
            $(obj).hide();
        }
        function cancelInfo(){
            $(".content-body").show();
            $(".input-body").hide();
            $("#edit").show();
        }
	</script>
	<link href="${ctxStatic}/bootstrap/bootstrap.css" rel="stylesheet" />
	<style>
		.content-body{
			display: <c:if test="${not empty oaCrmProduct.id}">show</c:if>;
		}
		.input-body{
			display: <c:if test="${not empty oaCrmProduct.id}">none</c:if>;
		}

	</style>

</head>
<body>
<fieldset>
	<legend>
		<div class="pull-left" style="padding: 10px 10px; text-align: left ">
			<span ><a href="${ctx}/oa/crm/oaCrmProduct/">项目报备列表</a>>>项目报备<shiro:hasPermission name="oa:crm:oaCrmProduct:edit">${not empty oaCrmProduct.id?'详情':'添加'}</shiro:hasPermission></span>
		</div>
		<div class="pull-right" style="padding: 10px 10px; text-align: left ">
			<c:if test="${oaCrmProduct.status==2}">
				<a   href="${ctx}/oa/crm/oaCrmProduct/updateSetUp?id=${oaCrmProduct.id}" onclick="return confirmx('确认要申请立项吗？', this.href)"><i class="icon-edit"></i>&nbsp;立项申请</a>&nbsp;
			</c:if>
			<c:if test="${oaCrmProduct.status==3}">
				<shiro:hasPermission name="oa:crm:oaCrmProduct:edit">
						<a href="${ctx}/oa/crm/oaCrmProduct/updateIng?id=${oaCrmProduct.id}" onclick="return confirmx('确认要申请项目跟进吗？', this.href)"><i class="icon-edit"></i>&nbsp;申请跟进</a>
				</shiro:hasPermission>
			</c:if>
			<c:if test="${oaCrmProduct.status!=3&&oaCrmProduct.status!=4||oaCrmProduct.leader}">
				<shiro:hasPermission name="oa:crm:oaCrmProduct:edit">
					<c:if test="${not empty oaCrmProduct.id }">
						<a href="javascript:void(0);" id="edit" onclick="editInfo(this);"><i class="icon-edit"></i>&nbsp;编辑</a>
					</c:if>
				</shiro:hasPermission>
			</c:if>
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
				<div class="input-body">
					<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
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
				<div class="input-body">
				<sys:treeselect id="customerIds" checked="true" name="customerIds" value="${oaCrmProduct.customerIds}" labelName="oaCrmProduct.customerNames" labelValue="${oaCrmProduct.customerNames}"
								title="客户联系人" url="/oa/oaFinanceCompany/treeData" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">描述信息：</label>
			<div class="controls">
				<div class="content-body">${oaCrmProduct.remarks}</div>
				<div class="input-body">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态：</label>
			<div class="controls">
				<form:hidden path="status" />
					${fns:getDictLabel(oaCrmProduct.status,'erm_p_status' , '')}
			</div>
		</div>
		<div class="input-body">
		<div class="form-actions">
			<shiro:hasPermission name="oa:crm:oaCrmProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;

			</shiro:hasPermission>

			<c:if test="${not empty oaCrmProduct.id}">
				<c:if test="${oaCrmProduct.status!=6&&oaCrmProduct.status!=5}">
					<a  class="btn btn-danger"  href="${ctx}/oa/crm/oaCrmProduct/updateClose?id=${oaCrmProduct.id}" onclick="return confirmx('确认要将项目置为公开状态吗？', this.href)">公开项目</a>&nbsp;
					<c:if test="${oaCrmProduct.leader}">
						<a  class="btn btn-danger"  href="${ctx}/oa/crm/oaCrmProduct/delete?id=${oaCrmProduct.id}" onclick="return confirmx('确认要删除该项目信息吗？', this.href)">删除</a>&nbsp;
					</c:if>
				</c:if>
				<input  class="btn btn-info" onclick="cancelInfo()" type="button" value="取消"/>&nbsp;
			</c:if>
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
					跟进情况&nbsp;&nbsp;
						<shiro:hasPermission name="oa:crm:oaCrmProduct:edit">
							<c:if test="${oaCrmProduct.status!=3&&oaCrmProduct.status!=4}">
							<a class="btn " style="" onclick="addProductIng()"><i class="icon-plus"></i>&nbsp;添加跟进信息</a>
							</c:if>
						</shiro:hasPermission>
					</h4>
					<c:forEach items="${inglist}" var="log" varStatus="index">
						<!-- .comment-list -->
						<section class="comment-list block">
							<article id="comment-id-1" class="comment-item media arrow arrow-left">

								<section class="media-body panel">
									<header class="panel-heading clearfix">
										<span class="pull-left thumb-small avatar number">${inglist.size()-index.index}</span>.&nbsp;&nbsp;<span>拜访时间：<fmt:formatDate value="${log.visitTime}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;|&nbsp;&nbsp;<span href="javascirpt:void(0)">拜访方式：${fns:getDictLabel(log.type,'ing_type' ,'' )}</span>&nbsp;&nbsp;|&nbsp;&nbsp;负责人：${log.createBy.name}<span class="text-muted m-l-small pull-right"><i class="icon-time"></i>创建时间：<fmt:formatDate value="${log.createDate}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;<c:if test="${oaCrmProduct.status!=3&&oaCrmProduct.status!=4}"><a href="javascript:void(0)" onclick="addProductIng('${log.id}')"><i class="icon-edit"></i>修改</a></c:if></span>
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
<script type="application/javascript">

    function addProductIng(id){
        if(id==undefined){
            id='';
		}
        $.jBox.open("iframe:${ctx}/oa/crm/oaCrmProductIng/form?pid=${oaCrmProduct.id}&id="+id, "项目跟进", 800, $(window).height()-150, {
            buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                    loading('正在提交，请稍等...');
						$.ajax({
							url: '${ctx}/oa/crm/oaCrmProductIng/save',
							type: 'POST',
							cache: false,
							data: new FormData( h.find("iframe")[0].contentWindow.$("#inputForm")[0]),
							processData: false,
							contentType: false
						}).done(function(res) {
                            loading('保存成功！');
                            setTimeout(function(){window.location.reload();},1000);
                   	 }).fail(function(res) {});
                }
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });

    }

</script>
</body>
</html>