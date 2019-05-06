<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报销管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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
			
			$(".select2-chosen").each(function(){
			})
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaFinanceEa/">待办任务</a></li>
		<li><a href="${ctx}/oa/oaFinanceEa/list">报销列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaFinanceEa/form/?procInsId=${oaFinanceEa.procInsId}">报销详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}"/>
		<fieldset>
			<legend>报销详情</legend>
			<table class="table-form">
				<tr>
					<td class="tit">申请人</td><td>${oaFinanceEa.createBy.name}</td>
					<td class="tit">部门</td><td>${oaFinanceEa.office.name}</td>
				</tr>
				<tr>
					<td class="tit">报销单编号</td><td>${oaFinanceEa.eaNo}</td>
					<td class="tit">报销时间</td><td><fmt:formatDate value="${oaFinanceEa.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<c:forEach items="${oaFinanceEa.detailList}" var="detail">
					<tr">
						<td class="tit"><b>报销详细</b></td>
					</tr>
					<tr>
						<td class="tit">项目类别</td><td>${detail.oaProduct.name}</td>
						<td class="tit">费用分类</td><td>${detail.category.name}</td>
					</tr>
					<tr>
						<td class="tit">单据数量</td><td>${detail.billNumber}</td>
						<td class="tit">支付方式</td><td>${detail.payType}</td>
					</tr>
					<tr>
						<td class="tit">报销金额</td><td>${detail.money}</td>
					</tr>
					<tr>
						<td class="tit">报销详情说明</td>
						<td colspan="5">${detail.remarks}</td>
					</tr>
				</c:forEach>
				<tr>
					<td class="tit">部门经理审批</td><td>${oaFinanceEa.userFirstApprover.name}</td>
					<td class="tit">审批状态</td><td>${oaFinanceEa.firstApproveStatus}</td>
				</tr>
				<tr>
					<td class="tit">总经理审批</td><td>${oaFinanceEa.userSecondApprover.name}</td>
					<td class="tit">审批状态</td><td>${oaFinanceEa.secondApproveStatus}</td>
				</tr>
				<tr>
					<td class="tit">票据核对</td><td>${oaFinanceEa.userThirdApprover.name}</td> 
					<td class="tit">审批状态</td><td>${oaFinanceEa.thirdApproveStatus}</td>
				</tr>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${oaFinanceEa.proInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
