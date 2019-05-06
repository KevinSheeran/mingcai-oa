<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商产品信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					var s="";
					$(".actives").each(function(){
					    s+=$(this).attr("tname")+",";
					})
					if(s.length>0){
					    s=s.substring(0,s.length-1);
					}
					$("#labels").val(s);
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
            $(".labels").click(function(){
                var $this=$(this);
                if($this.hasClass("actives")){
                    $this.removeClass("actives");
                }else{
                    $this.addClass("actives");
                }
            })
		});
	</script>
	<style>
		.labels{
			padding: 3px 8px;
			border: 1px solid #DDDDDD;
			margin: 5px;
			position: relative;
		}
		.labels i{
			display: none;
		}
		.labels.actives{
			border: 1px solid #00b700;
			color: #00b700;
		}
		.labels.actives i{
			display: block;
			position: absolute;
			bottom: 1px;
			right: 1px;
			color: #00b700;
		}

	</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/contract/oaContractSupplierProduct/">供应商产品信息列表</a></li>
		<li class="active"><a href="${ctx}/oa/contract/oaContractSupplierProduct/form?id=${oaContractSupplierProduct.id}">供应商产品信息<shiro:hasPermission name="oa:contract:oaContractSupplierProduct:edit">${not empty oaContractSupplierProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:contract:oaContractSupplierProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaContractSupplierProduct" action="${ctx}/oa/contract/oaContractSupplierProduct/save" method="post" class="form-horizontal" enctype="multipart/form-data">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<form:hidden path="labels"/>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">
				<form:textarea path="specifications" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">采购单价：</label>
			<div class="controls">
				<form:input path="unitPrice" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">税点%：</label>
			<div class="controls">
				<form:select path="taxPoint" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('taxPoint')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">供应商：</label>
			<div class="controls">
				<form:select path="supplierId" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getSupplr()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品标签分类：</label>
			<div class="controls">
				<c:forEach items="${types}" var="items">
					<label class="labels <c:if test="${oaContractSupplierProduct.labels!=null&&oaContractSupplierProduct.labels!='' && oaContractSupplierProduct.labels.indexOf(items.name)!=-1}">actives</c:if>" tname="${items.name}">${items.name}<i class="icon-ok"></i></label>
				</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传附件：</label>
			<div class="controls">
				<c:forEach items="${list}" var="item">
					<c:if test="${item.type==2}">
						<a href="javascript:void(0)" onclick="downloads('path=${item.path}&name=${item.name}.${item.format}');" title="下载">${item.name}.${item.format}(${item.filesize})</a>
							<a href='javascript:void(0);' onclick="removeFile(this,'${item.id}')">删除</a>&nbsp;
					</c:if>
				</c:forEach>
					<label id="addFile" class="btn">添加</label>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="8" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:contract:oaContractSupplierProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>
<script type="text/javascript">
    $("#addFile").click(function(){
        $(this).before("<input type=\"file\" name='files'><a href='javascript:void(0);' onclick='remove(this)'>删除</a>&nbsp;");
    });
    function remove(obj){
        $(obj).prev().remove();
        $(obj).remove();
    }
    function removeFile(obj,id){
        $.jBox.confirm("确认要删除附件吗？",'系统提示',function(v,h,f){
            if(v=='ok'){
                $.post("${ctx}/oa/contract/oaContractSupplierFiles/deletefiles",{"id":id},function(data){
                    var data=JSON.parse(data);
                    if(data.status=="success"){
                        $(obj).prev().remove();
                        $(obj).remove();
                    }else{

                        alert(data.msg);
                    }
                })
            }
        },{buttonsFocus:1, closed:function(){
        }});
    }
    function downloads(url){
        try{
            var elemIF = document.createElement("iframe");
            elemIF.src ="${ctx}/oa/oaProductResources/download?"+url ;
            elemIF.style.display = "none";
            document.body.appendChild(elemIF);
        }catch(e){

        }
    }

</script>
</body>
</html>