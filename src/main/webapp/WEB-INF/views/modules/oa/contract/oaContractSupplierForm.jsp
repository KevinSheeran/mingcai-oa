<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商信息管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/contract/oaContractSupplier/">供应商信息列表</a></li>
		<li class="active"><a href="${ctx}/oa/contract/oaContractSupplier/form?id=${oaContractSupplier.id}">供应商信息<shiro:hasPermission name="oa:contract:oaContractSupplier:edit">${not empty oaContractSupplier.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oa:contract:oaContractSupplier:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="oaContractSupplier" action="${ctx}/oa/contract/oaContractSupplier/save" enctype="multipart/form-data" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">单位名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">银行账号：</label>
			<div class="controls">
				<form:input path="bankAccount" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开户行：</label>
			<div class="controls">
				<form:input path="openingBank" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">税号：</label>
			<div class="controls">
				<form:input path="dutyParagraph" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可开票点数，多个用逗号隔开：</label>
			<div class="controls">
				<form:input path="taxPoint" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系人：</label>
			<div class="controls">
				<form:input path="contacts" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系电话：</label>
			<div class="controls">
				<form:input path="contactNumber" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">联系地址：</label>
			<div class="controls">
				<form:input path="address" htmlEscape="false" maxlength="100" class="input-xlarge "/>
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
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="oa:contract:oaContractSupplier:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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