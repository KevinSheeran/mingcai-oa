<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目任务管理</title>
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
	<br/>
	<form:form id="inputForm" modelAttribute="oaProductTask" enctype="multipart/form-data" action="${ctx}/oa/oaProductTask/actionSave" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="productId"/>
		<form:hidden path="remarks"/>
		<input type="hidden" name="taskType" value="${taskType}">
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">任务名称：</label>
			<div class="controls">
				<form:input path="name" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务指派给：</label>
			<div class="controls">
				<sys:treeselect id="taskToUserId" allowClear="true"  name="taskToUserId" value="${oaProductTask.taskUser.id}" labelName="oaProductTaskUsername" labelValue="${oaProductTask.taskUser.name}"
								title="任务人" url="/oa/oaProductTask/treeData?productId=${oaProductTask.productId}"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务完成状态：</label>
			<div class="controls">
				<form:radiobuttons  path="taskStatus" items="${fns:getDictList('task_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务截止时间：</label>
			<div class="controls">
				<input name="prodcutEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${oaProductTask.prodcutEndDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">内容信息描述：</label>
			<div class="controls">
				<textarea name="desc" rows="4" maxlength="1000"></textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传附件：</label>
			<div class="controls">
					<label id="addFile" class="btn">添加</label>
			</div>
		</div>
	</form:form>
	<div id="task_items">


	</div>
<script type="text/javascript">
	$("#addFile").click(function(){
	   $(this).before("<input type=\"file\" name='files'><a href='javascript:void(0);' onclick='remove(this)'>删除</a>&nbsp;");
	});
	function remove(obj){
        $(obj).prev().remove();
	    $(obj).remove();
	}
</script>
</body>
</html>