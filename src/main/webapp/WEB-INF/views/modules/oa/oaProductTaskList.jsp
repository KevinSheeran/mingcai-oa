<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目任务管理</title>
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
	</script>
	<style>
		.btn-small{
			padding: 3px 3px;
			border-radius: 5px;
			font-size:11px;
			border: 1px solid #DDDDDD;
			margin-left: 0px;
		}
		.line_a{
			font-size: 16px;
			font-weight: bold;
			color: #333;
		}
		.contentTable th{
				font-size: 18px;
				font-weight: inherit;
				padding:10px;
		}
		.contentTable tr td{
			padding: 5px 10px;

		}

		.input-body{
			display: none;
		}
		.users{
			padding: 0px;
			margin: 0px;
		}
		.users li{
			display: block;
			float: left;
			margin: 5px;
			text-align: center;
		}
		.users li img{
			margin: 0 auto;
			width: 40px;
			border-radius: 50%;

		}
		.users li .user-name{
			width: 100%;
			text-align: center;
			line-height: 25px;
			font-size: 9px;
		}
		.form-horizontal .control-label{
			width: 80px;
		}
		.form-horizontal .controls{
			margin-left: 120px;

		}
		.breadcrumb{
			padding: 0px;
			 background: inherit;
		}
		.task_title{
			float: left;
			padding: 5px;
			width: 100%;
		}
		.task_status{
			float: left;
			padding: 5px;
			width: 100%;
		}
	</style>
</head>
<body>
<sys:message content="${message}"/>
<fieldset>
	<legend>
		<div class="pull-left" style="padding: 10px 10px; text-align: left ">
			<span ><a   href="${ctx}/oa/oaProduct" >${oaProduct.name}</a>>>任务详情</span>
		</div>
		<div class="pull-right" style="padding: 10px 10px; text-align: left ">
			<a><i class="icon-random"></i>&nbsp;待处理任务${taskCount}个</a>&nbsp;&nbsp;
			<c:if test="${fns:getUser().id==oaProduct.createBy.id||fns:getUser().id==oaProduct.updateBy.id}">
				<a href="javascript:void(0);" id="edit" onclick="editInfo(this);"><i class="icon-edit"></i>&nbsp;编辑</a>
			</c:if>

		</div>
	</legend>
</fieldset>
	<form:form id="inputForm" modelAttribute="oaProduct" action="${ctx}/oa/oaProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">项目名称:</label>
			<div class="controls">
				<div class="content-body">
						&nbsp;&nbsp;${oaProduct.name}
				</div>
				<div class="input-body">
					<form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
					<span class="help-inline"><font color="red">*</font> </span>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目组用户:</label>
			<div class="controls">
				<div class="content-body">
					<ul class="users">
						<c:forEach items="${oaProduct.pusers}" var="user">
							<li>
								<img src="<c:if test="${user.user.photo==''||user.user.photo==null}">/static/windowsstyle/icon/user-male-icon.png</c:if><c:if test="${user.user.photo!=''&&user.user.photo!=null}">${user.user.photo}</c:if>" />
								<div class="user-name">${user.user.name}</div>
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="input-body">
				<sys:treeselect id="oaProductUsers" checked="true" name="oaProduct.users_ids" value="${oaProduct.users_ids}" labelName="oaProduct.users_names" labelValue="${oaProduct.users_names}"
								title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
				</div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人:</label>
			<div class="controls">
				<div class="content-body">
					<ul class="users">
							<li>
								<img src="<c:if test="${oaProduct.updateBy.photo==''||oaProduct.updateBy.photo==null}">/static/windowsstyle/icon/user-male-icon.png</c:if><c:if test="${oaProduct.updateBy.photo!=''&&oaProduct.updateBy.photo!=null}">${oaProduct.updateBy.photo}</c:if>" />
								<div class="user-name">${oaProduct.updateBy.name}</div>
							</li>
					</ul>
				</div>
				<div class="input-body">
				<sys:treeselect id="updateBy"  name="updateById" value="${oaProduct.updateBy.id}" labelName="oaProduct.updateBy.name" labelValue="${oaProduct.updateBy.name}"
								title="负责人" url="/sys/office/treeData?type=3" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
				</div>
			</div>
		</div>
		<div class="control-group <c:if test="${oaProduct.remarks==''||oaProduct.remarks==null}">input-body</c:if>">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<div class="content-body">
						${oaProduct.remarks}
				</div>
				<div class="input-body">
					<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
				</div>
			</div>
		</div>
		<div class="input-body">
			<div class="form-actions">
				<shiro:hasPermission name="oa:oaProduct:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
					<a  class="btn btn-danger"  href="${ctx}/oa/oaProduct/delete?id=${oaProduct.id}" onclick="return confirmx('确认要删除该项目信息吗？', this.href)">删除</a>&nbsp;
					<input  class="btn btn-info" onclick="cancelInfo()" type="button" value="取消"/>&nbsp;
				</shiro:hasPermission>
			</div>
		</div>
	</form:form>
	<table  class="contentTable table  table-bordered table-condensed">
		<thead>
		<tr>
			<th>我的任务
			</th>
		</tr>
		</thead>
		<tbody>
        <c:if test="${thisTask.size()==0}">
        <tr>
            <td>
                暂无任务
            </td>
        </tr>
        </c:if>
		<c:forEach items="${thisTask}" var="oaProductTask">
			<tr>
				<td>
					<a  class="line_a" href="${ctx}/oa/oaProductTask/form?id=${oaProductTask.id}" title="查看任务详情">
							${oaProductTask.name}
					</a>
					<span class=" btn-small <c:if test="${oaProductTask.taskUser.id==fns:getUser().id}">btn-warning</c:if>" title='<c:if test="${oaProductTask.taskUser.id==null||oaProductTask.taskUser.id==''}">未指派</c:if><c:if test="${oaProductTask.taskUser.id!=null&&oaProductTask.taskUser.id!=''}">指派给：${oaProductTask.taskUser.name}&nbsp;&nbsp;截止时间：<fmt:formatDate value="${oaProductTask.prodcutEndDate}" pattern="yyyy-MM-dd"/></c:if>'>
						<c:if test="${oaProductTask.taskUser.id==null||oaProductTask.taskUser.id==''}">未指派</c:if><c:if test="${oaProductTask.taskUser.id!=null&&oaProductTask.taskUser.id!=''}">${oaProductTask.taskUser.name}&nbsp;&nbsp;<fmt:formatDate value="${oaProductTask.prodcutEndDate}" pattern="yyyy-MM-dd"/></c:if></span>
					<c:if test="${oaProductTask.taskStatus==1}"><span class=" btn-small btn-primary">已确认&nbsp;&nbsp;<fmt:formatDate value="${oaProductTask.updateDate}" pattern="yyyy-MM-dd"/></span></c:if>
					<c:if test="${oaProductTask.taskStatus==2}"><span class=" btn-small btn-success">完成&nbsp;&nbsp;<fmt:formatDate value="${oaProductTask.updateDate}" pattern="yyyy-MM-dd"/></span></c:if>
					<span class="btn-small" title='' >
							指派人：${oaProductTask.createBy.name}
						</span>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<table  class="contentTable table  table-bordered table-condensed">
		<thead>
		<tr>
			<th colspan="2">任务列表&nbsp;&nbsp;&nbsp;&nbsp;
				<a class="btn " style="font-weight: 300;" href="${ctx}/oa/oaProductTask/form?product_id=${oaProductTask.productId}"><i class="icon-plus"></i>&nbsp;新增任务</a>
				<div style="float: right;">
				<form:form id="searchForm" modelAttribute="oaProductTask" action="${ctx}/oa/oaProductTask/" method="post" class="breadcrumb form-search" cssStyle="padding: 0px; font-size: 14px;margin: 0px;">
					<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
					<input id="product_id" name="product_id" type="hidden" value="${oaProductTask.productId}"/>
					<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
					<ul class="ul-form">
						<li ><label style="margin-left: 0px;text-align: left;width:auto;">名称：</label>
							<div class="input-append">
								<input id="companyName" name="name"  placeholder="任务名称" type="text"  value="${oaProductTask.name}" data-msg-required="" class="input-small" style="">
								<a href="javascript:" class="btn" style="">&nbsp;<i class="icon-search" onclick="$('#searchForm').submit();"></i>&nbsp;</a>&nbsp;&nbsp;
							</div>
							<label style="margin-left: 0px;text-align: left;width:auto;">任务状态：</label>
							<form:select path="taskStatus"  cssStyle="min-width: 100px;" onchange="$('#searchForm').submit();">
								<form:option value="" label="全部"/>
								<form:options items="${fns:getDictList('task_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
							</form:select>
						<li>
					</ul>
				</form:form>
				</div>
		</tr>
		</thead>
		<tbody>
        <c:if test="${page.list.size()==0}">
            <tr>
                <td colspan="2">
                    暂无任务
                </td>
            </tr>
        </c:if>
		<c:forEach items="${page.list}" var="oaProductTask">
			<tr>
				<td style="width: 50px">
					<c:if test="${oaProductTask.taskUser.id==null||oaProductTask.taskUser.id==''}">
						未指派
					</c:if>
					<ul class="users">
						<li>
							<img src="<c:if test="${oaProductTask.taskUser.photo==''||oaProductTask.taskUser.photo==null}">/static/windowsstyle/icon/user-male-icon.png</c:if><c:if test="${oaProductTask.taskUser.photo!=''&&oaProductTask.taskUser.photo!=null}">${oaProductTask.taskUser.photo}</c:if>" />
							<div class="user-name">
								<c:if test="${oaProductTask.taskUser.id!=null&&oaProductTask.taskUser.id!=''}">${oaProductTask.taskUser.name}</c:if>
							</div>
						</li>
					</ul>

				</td>
				<td style="border-left: 0px;">
					<div class="task_title">
						<a  class="line_a" href="${ctx}/oa/oaProductTask/form?id=${oaProductTask.id}" title="查看任务详情">
								${oaProductTask.name}
						</a>
						&nbsp;&nbsp;
						<c:if test="${oaProductTask.taskStatus==1}"><span class=" btn-small btn-primary">已确认&nbsp;&nbsp;<fmt:formatDate value="${oaProductTask.updateDate}" pattern="yyyy-MM-dd"/></span></c:if>
						<c:if test="${oaProductTask.taskStatus==2}"><span class=" btn-small btn-success">完成&nbsp;&nbsp;<fmt:formatDate value="${oaProductTask.updateDate}" pattern="yyyy-MM-dd"/></span></c:if>
					</div>
					<div class="task_status">
						<span class="btn-small <c:if test="${oaProductTask.taskUser.id==fns:getUser().id}">btn-warning</c:if>" >
							截止时间：<fmt:formatDate value="${oaProductTask.prodcutEndDate}" pattern="yyyy-MM-dd"/>
						</span>&nbsp;&nbsp;
						<span class="btn-small" title='' >
							指派人：${oaProductTask.createBy.name}
						</span>
					</div>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>

<script type="application/javascript">
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
</body>
</html>