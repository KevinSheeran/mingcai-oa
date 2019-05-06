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
		function agree(){
			var taskId = $("#tid").val();
			var oaFinanceEaId = $("#eaid").val();
			complete(taskId, [{
				key: 'pass',
				value: true,
				type: 'B'
			},{
				key: 'id',
				value: oaFinanceEaId,
				type: 'S'
			}]);
		}
		
		function reject(){
			var taskId = $("#tid").val();
			var oaFinanceEaId = $("#eaid").val();
			$.jBox("<div style='padding:10px;'><textarea id='financeBackReason' style='width: 250px; height: 60px;'></textarea></div>", { title: "请填写驳回理由", submit: function () {
				 var financeBackReason=$("#financeBackReason").val();
				 //必须填写驳回理由
				 if($.trim(financeBackReason)=="") {
					 $.jBox.error('请填写驳回理由', '错误');
					 return false;
				 } else {
					complete(taskId, [{
						key: 'pass',
						value: false,
						type: 'B'
					},{
						key: 'reason',
						value: financeBackReason,
						type: 'S'
					},{
						key: 'id',
						value: oaFinanceEaId,
						type: 'S'
					}]);
				 }
			   }
			});
		}
		
		function cashier(){
			var taskId = $("#tid").val();
			var oaFinanceEaId = $("#eaid").val();
			complete(taskId);
		}
		
		/**
		 * 完成任务
		 * @param {Object} taskId
		 */
		function complete(taskId, variables) {
			// 转换JSON为字符串
		    var keys = "", values = "", types = "";
			if (variables) {
				$.each(variables, function(idx) {
					if (keys != "") {
						keys += ",";
						values += ",";
						types += ",";
					}
					keys += this.key;
					values += this.value;
					types += this.type;
				});
			}
			// 发送任务完成请求
		    $.post('${ctx}/oa/oaFinanceEa/complete/', {
		    	taskId: taskId,
		        "vars.keys": keys,
		        "vars.values": values,
		        "vars.types": types
		    }, function(data) {
		        $.jBox.tip('任务完成');
		        location.href="${ctx}/oa/oaFinanceEa/";
		    });
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaFinanceEa/">待办任务</a></li>
		<li><a href="${ctx}/oa/oaFinanceEa/list">报销列表</a></li>
		<li class="active"><a href="javascript:void(0)">报销办理</a></li>
	</ul>
	<input id="tid" type="hidden" value="${tid}">
	<input id="tkey" type="hidden" value="${tkey}">
	<input id="eaid" type="hidden" value="${id}">
	<form:form class="form-horizontal">
		<sys:message content="${message}"/>
		<fieldset>
			<legend>报销办理</legend>
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
					<c:if test="${approver==1}">
						<td class="tit">审批操作</td>
						<td>
							<input class="btn" type="button" value="同意" onclick="agree()">
							<input class="btn" type="button" value="驳回" onclick="reject()">
						</td>
					</c:if>
					<c:if test="${approver!=1}">
						<td class="tit">审批状态</td><td>${oaFinanceEa.firstApproveStatus}</td>
					</c:if>
				</tr>
				<tr>
					<td class="tit">总经理审批</td><td>${oaFinanceEa.userSecondApprover.name}</td>
					<c:if test="${approver==2}">
						<td class="tit">审批操作</td>
						<td>
							<input class="btn" type="button" value="同意" onclick="agree()">
							<input class="btn" type="button" value="驳回" onclick="reject()">
						</td>
					</c:if>
					<c:if test="${approver!=2}">
						<td class="tit">审批状态</td><td>${oaFinanceEa.secondApproveStatus}</td>
					</c:if>
				</tr>
				<tr>
					<td class="tit">票据核对</td><td>${oaFinanceEa.userThirdApprover.name}</td>
					<c:if test="${approver==3}">
						<td class="tit">审批操作</td>
						<td>
							<input class="btn" type="button" value="同意" onclick="agree()">
							<input class="btn" type="button" value="驳回" onclick="reject()">
						</td>
					</c:if>
					<c:if test="${approver!=3}"> 
						<td class="tit">审批状态</td><td>${oaFinanceEa.thirdApproveStatus}</td>
					</c:if>
				</tr>
				<c:if test="${approver==4}">
					<tr>
						<td class="tit">出纳者</td><td>${oaFinanceEa.userThirdApprover.name}</td>
						<td class="tit">出纳支付</td><td><input class="btn" type="button" value="出纳" onclick="cashier()"></td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${oaFinanceEa.proInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
