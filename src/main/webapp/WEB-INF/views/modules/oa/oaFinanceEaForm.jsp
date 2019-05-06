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
		// 添加新元素
		function addInfo(){
			var size = $("#detailSize").val();
			var next = parseInt(size)+1;
			var dataproduct = '';
			var datacategory = '';
			var datapaytype = '';
			$(document.getElementById('detailList0.oaProduct')).find('option').each(function(i){	//取项目
				dataproduct = dataproduct+'<option value='+$(this).val()+'>'+$(this).html()+'</option>\n';
			})
			$(document.getElementById('detailList0.category')).find('option').each(function(i){ //取费用类型
				datacategory = datacategory+'<option value='+$(this).val()+'>'+$(this).html()+'</option>\n';
			})
			$(document.getElementById('detailList0.payType')).find('option').each(function(i){ //取支付类型
				datapaytype = datapaytype+'<option value='+$(this).val()+'>'+$(this).html()+'</option>\n';
			})
			// 复制报销单详情
			var clonenode = $("[name='detail"+size+"']").clone();
			clonenode.attr('name','detail'+next);
			clonenode.find('[id="detailList'+size+'.oaProduct"]').attr({id:'detailList'+next+'.oaProduct',name:'detailList['+next+'].oaProduct'});
			clonenode.find('[id="detailList'+size+'.category"]').attr({id:'detailList'+next+'.category',name:'detailList['+next+'].category'});
			clonenode.find('[id="detailList'+size+'.billNumber"]').attr({id:'detailList'+next+'.billNumber',name:'detailList['+next+'].billNumber'});
			clonenode.find('[id="detailList'+size+'.payType"]').attr({id:'detailList'+next+'.payType',name:'detailList['+next+'].payType'});
			clonenode.find('[id="detailList'+size+'.money"]').attr({id:'detailList'+next+'.money',name:'detailList['+next+'].money'});
			clonenode.find('[id="detailList'+size+'.remarks"]').attr({id:'detailList'+next+'.remarks',name:'detailList['+next+'].remarks'});
			$("#addInfoBtn").parent().parent().before(clonenode); 
			// 设定项目选项
			var htmloaProduct='<select id="detailList'+next+'.oaProduct" name= "detailList['+next+'].oaProduct" class="input-xlarge required">'
								+ dataproduct +
								+'</select>';
			$(document.getElementById('detailList'+next+'.oaProduct')).parent().html(htmloaProduct);
			// 设定费用选项
			var htmlCategory='<select id="detailList'+next+'.category" name= "detailList['+next+'].category" class="input-xlarge required">'
								+ datacategory +
								+'</select>';
			$(document.getElementById('detailList'+next+'.category')).parent().html(htmlCategory);
			// 设定支付类型
			var htmlpaytype='<select id="detailList'+next+'.payType" name= "detailList['+next+'].payType" class="input-xlarge required">'
								+ datapaytype +
								+'</select>';
			$(document.getElementById('detailList'+next+'.payType')).parent().html(htmlpaytype);
			$("#detailSize").val(next);
			if(size!=0){
				$("[name='detail"+size+"']").eq(0).append('<td><input id="delInfoBtn" class="btn btn-danger" type="button" value="删除此表单" onclick="delInfo(this)"></td>');  
			}
		}
		
		// 删除详细表单
		function delInfo(obj){
			$.jBox.confirm("确认要删除资源吗？",'系统提示',function(v,h,f){
	            if(v=='ok'){
	            	var name = $(obj).parent().parent().attr('name');
	            	$("[name='"+name+"']").each(function(){
	            		$(this).remove();
	            	})
	            }
	        },{buttonsFocus:1, closed:function(){
	        }});
		}
		
		// 删除详细表单
		function delInfo2(obj,index){
			$.jBox.confirm("确认要删除资源吗？",'系统提示',function(v,h,f){
	            if(v=='ok'){
	            	var name = $(obj).parent().parent().attr('name');
	            	$("[name='"+name+"']").each(function(){
	            		$(this).remove();
	            	})
	            	document.getElementById("detailList"+index+".eaId").value="";
	            }
	        },{buttonsFocus:1, closed:function(){
	        }});
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/oaFinanceEa/">待办任务</a></li>
		<li><a href="${ctx}/oa/oaFinanceEa/list">报销列表</a></li>
		<li class="active"><a href="${ctx}/oa/oaFinanceEa/form?id=${oaFinanceEa.id}"><shiro:hasPermission name="oa:oaFinanceEa:edit">报销${not empty oaFinanceEa.id?'修改':'申请'}单</shiro:hasPermission><shiro:lacksPermission name="oa:oaFinanceEa:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<input type="hidden" id="detailSize" name="detailSize" value="0" />
	<input type="hidden" id="productList" value="${productList}" />
	<input type="hidden" id="categoryList" value="${categoryList}"  />
	<form:form id="inputForm" modelAttribute="oaFinanceEa" action="${ctx}/oa/oaFinanceEa/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<c:if test="${tid!=null}">
			<form:hidden path="act.taskId" value="${tid}"/>
		</c:if>
		<c:if test="${tid==null}">
			<form:hidden path="act.taskId"/>
		</c:if>
		<form:hidden path="act.taskName"/>
		<form:hidden path="act.taskDefKey"/>
		<form:hidden path="act.procInsId"/>
		<form:hidden path="act.procDefId"/>
		<form:hidden id="flag" path="act.flag"/>
		<form:hidden path="eaNo"/>
		<form:hidden path="office.id"/>
		<fieldset>
			<legend>报销申请</legend>
			<table class="table-form">
				<tr>
					<td class="tit">申请人</td><td>${oaFinanceEa.createBy.name}</td>
					</td><td class="tit">部门</td><td>${oaFinanceEa.office.name}</td>
				</tr>
				<tr>
					<td class="tit">报销单编号</td><td>${oaFinanceEa.eaNo}</td>
					<td class="tit">报销时间</td><td><fmt:formatDate value="${oaFinanceEa.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				<c:if test="${empty eaDetailList}">
					<tr name="detail0">
						<td class="tit"><b>报销详细</b></td>
					</tr>
					<tr name="detail0">
						<td class="tit">项目名称</td><td>
							<select id="detailList0.oaProduct" name="detailList[0].oaProduct" path="detailList[0].oaProduct"
								class="input-xlarge required">
								<c:forEach items="${productList}" var="oaProduct">
									<option value="${oaProduct.id}">${oaProduct.company.name}${oaProduct.name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="tit">费用类别</td><td>
							<form:select id="detailList0.category" path="detailList[0].category"
								class="input-xlarge required">
								
								<c:forEach items="${categoryList}" var="category">
									<option value="${category.id}">${category.name}</option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr name="detail0">
						<td class="tit">单据数量</td>
						<td><form:input type="number" placeholder="请输入数字"  path="detailList[0].billNumber" htmlEscape="false" maxlength="50" class="input-xlarge required"/></td>
						<td class="tit">支付方式</td>
						<td>
							<form:select path="detailList[0].payType" class="input-xlarge required">
								<form:option value="现金" label="现金"/>
								<form:option value="网上支付" label="网上支付"/>
							</form:select>
						</td>
					</tr>
					<tr name="detail0">
						<td class="tit">报销金额</td>
						<td><form:input type="text" path="detailList[0].money" placeholder="请输入报销金额" htmlEscape="false" maxlength="50" class="input-xlarge required"/></td>
					</tr>
					<tr name="detail0">
						<td class="tit">报销详情说明</td>
						<td colspan="5">
							<form:textarea path="detailList[0].remarks" class="required" rows="5" maxlength="200" cssStyle="width:500px"/>
						</td>
					</tr>
				</c:if>
				<c:if test="${not empty eaDetailList}">
					<c:forEach items="${eaDetailList}" var="detail" varStatus="vs">
						<c:if test="${vs.index!=0}">
								<input id="detailList${vs.index}.eaId" name="detailList[${vs.index}].eaId" type="hidden" value="${detail.eaId}"></td>
						</c:if>
						<tr name="detail${vs.index}">
							<td class="tit"><b>报销详细</b></td>
							<c:if test="${vs.index!=0}">
								<td><input id="delInfoBtn" class="btn btn-danger" type="button" value="删除此表单" onclick="delInfo2(this,'${vs.index}')"></td>
							</c:if>
						</tr>
						<tr name="detail${vs.index}">
							<td class="tit">项目名称</td><td>
								<select id="detailList${vs.index}.oaProduct" name="detailList[${vs.index}].oaProduct" path="detailList[${vs.index}].oaProduct"
									class="input-xlarge required">
									<c:forEach items="${productList}" var="oaProduct">
										<c:if test="${detail.oaProduct.id==oaProduct.id}">
											<option value="${oaProduct.id}" selected="selected">${oaProduct.name}</option>
										</c:if>
										<c:if test="${detail.oaProduct.id!=oaProduct.id}">
											<option value="${oaProduct.id}">${oaProduct.name}</option>
										</c:if>
									</c:forEach>
								</select>
							</td>
							<td class="tit">费用类别</td><td>
								<form:select id="detailList${vs.index}.category" path="detailList[${vs.index}].category"
									class="input-xlarge required">
									<c:forEach items="${categoryList}" var="category">
										<c:if test="${detail.category.id==category.id}">
											<option value="${category.id}" selected="selected">${category.name}</option>
										</c:if>
										<c:if test="${detail.category.id!=category.id}">
											<option value="${category.id}">${category.name}</option>
										</c:if>
									</c:forEach>
								</form:select>
							</td>
						</tr>
						<tr name="detail${vs.index}">
							<td class="tit">单据数量</td>
							<td><form:input type="number" path="detailList[${vs.index}].billNumber"  placeholder="请输入数字"  htmlEscape="false" maxlength="50" class="input-xlarge  required"/></td>
							<td class="tit">支付方式</td>
							<td>
								<form:select path="detailList[${vs.index}].payType" class="input-xlarge required">
									<form:option value="现金" label="现金"/>
									<c:if test="${detail.payType!='网上支付'}">
									<form:option value="网上支付" label="网上支付"/>
									</c:if>
									<c:if test="${detail.payType=='网上支付'}">
									<form:option value="网上支付" label="网上支付"  selected="selected"/>
									</c:if>
								</form:select>
							</td>
						</tr>
						<tr name="detail${vs.index}">
							<td class="tit">报销金额</td>
							<td><form:input type="text" placeholder="请输入金额" path="detailList[${vs.index}].money" htmlEscape="false" maxlength="50" class="input-xlarge required"/></td>
						</tr>
						<tr name="detail${vs.index}">
							<td class="tit">报销详情说明</td>
							<td colspan="5">
								<form:textarea path="detailList[${vs.index}].remarks" class="required" rows="5" maxlength="200" cssStyle="width:500px"/>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<tr>
					<td><input id="addInfoBtn" class="btn" type="button" value="添加表单" onclick="addInfo()"></td>
				</tr>
				<tr>
					<td class="tit">部门经理审批</td><td>
						<sys:treeselect id="userFirstApprover" name="userFirstApprover.id" value="${oaFinanceEa.userFirstApprover.id}" labelName="userFirstApprover.name" labelValue="${oaFinanceEa.userFirstApprover.name}" 
							title="部门经理" url="/oa/oaFinanceEa/treeData?type=3" cssClass="required recipient" cssStyle="width:150px"
							allowClear="true" notAllowSelectParent="true" smallBtn="false"/>
					</td>
					<td class="tit">总经理审批</td><td>
					<sys:treeselect id="userSecondApprover" name="userSecondApprover.id" value="${oaFinanceEa.userSecondApprover.id}" labelName="userSecondApprover.name" labelValue="${oaFinanceEa.userSecondApprover.name}" 
							title="总经理" url="/oa/oaFinanceEa/treeData?type=3" cssClass="required recipient" cssStyle="width:150px"
							allowClear="true" notAllowSelectParent="true" smallBtn="false"/>
					</td>
					<td class="tit">票据审核</td><td>
						<sys:treeselect id="userThirdApprover" name="userThirdApprover.id" value="${oaFinanceEa.userThirdApprover.id}" labelName="userThirdApprover.name" labelValue="${oaFinanceEa.userThirdApprover.name}" 
							title="财务" url="/oa/oaFinanceEa/treeData?type=3" cssClass="required recipient" cssStyle="width:150px"
							allowClear="true" notAllowSelectParent="true" smallBtn="false"/>
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<shiro:hasPermission name="oa:oaFinanceEa:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="提交申请" onclick="$('#flag').val('yes')"/>&nbsp;
				<c:if test="${not empty oaFinanceEa.id}">
					<input id="btnSubmit2" class="btn btn-inverse" type="submit" value="销毁申请" onclick="$('#flag').val('no')"/>&nbsp;
				</c:if>
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
		<c:if test="${not empty oaFinanceEa.id}">
			<act:histoicFlow procInsId="${oaFinanceEa.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>
