<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存采购申请单成功管理</title>
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
</head>
<body>
	<fieldset>
	<legend>
		<div style="padding: 10px 10px; text-align: left ">
			<a href="${ctx}/oa/proc/oaProcApplylist/">采购申请列表</a>&nbsp;&nbsp;
			<shiro:hasPermission name="sys:menu:edit">
			<a class="btn" href="${ctx}/oa/proc/oaProcApplylist/form"><i class="icon-plus"></i>&nbsp;添加请购单</a>
		</shiro:hasPermission></div>
	</legend>
		<form:form id="searchForm" modelAttribute="oaProcApplylist" action="${ctx}/oa/proc/oaProcApplylist/" method="post" class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
			<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<ul class="ul-form">
				<li><label>项目名称：</label>
					<input id="name" name="name" class="input-medium" type="text" value="" maxlength="100">
				</li>
				<li><label>审批状态：</label>
					<select name="fstatus" id="fstatus">
						<option name="" value="">全部</option>
						<option name="" value="0">未提交</option>
						<option name="" value="1">未审批</option>
						<option name="" value="2">已通过</option>
						<option name="" value="-1">未通过</option>
					</select>
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
				<li class="clearfix"></li>
			</ul>
		</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目编号</th>
				<th>项目名称</th>
				<th>请购人</th>
				<th>请购日期</th>
				<th>审批日期</th>
				<th>审批状态1</th>
				<th>审批状态2</th>
				<th>财务审批状态</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="oa:proc:oaProcApplylist:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaProcApplylist">
			<tr>
				<td><a href="${ctx}/oa/proc/oaProcApplylist/form?id=${oaProcApplylist.id}">
					<c:if test="${oaProcApplylist.oaEosPro.proNumber==null||oaProcApplylist.oaEosPro.proNumber==''}">
						${oaProcApplylist.oaEosPro.paNumber}
					</c:if>
					<c:if test="${oaProcApplylist.oaEosPro.proNumber!=null||oaProcApplylist.oaEosPro.proNumber!=''}">
						${oaProcApplylist.oaEosPro.proNumber}
					</c:if>
				</a></td>
				<td>
						${oaProcApplylist.oaEosPro.name}
				</td>
                <td>
                        ${fns:getUser().name}
                </td>
				<td>
					<fmt:formatDate value="${oaProcApplylist.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${oaProcApplylist.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${oaProcApplylist.fstatus=='0'}">
						未提交
					</c:if>
					<c:if test="${oaProcApplylist.fstatus=='1'}">
						申请中
					</c:if>
					<c:if test="${oaProcApplylist.fstatus=='2'}">
						已通过
					</c:if>
					<c:if test="${oaProcApplylist.fstatus=='-1'}">
						驳回
					</c:if>
				</td>
				<td>
					<c:if test="${oaProcApplylist.sstatus=='0'}">
						未提交
					</c:if>
					<c:if test="${oaProcApplylist.sstatus=='1'}">
						申请中
					</c:if>
					<c:if test="${oaProcApplylist.sstatus=='2'}">
						已通过
					</c:if>
					<c:if test="${oaProcApplylist.sstatus=='-1'}">
						驳回
					</c:if>
				</td>
				<td>
					<c:if test="${oaProcApplylist.financeStatus==0||oaProcApplylist.financeStatus==0}">
						未提交
					</c:if>
					<c:if test="${oaProcApplylist.financeStatus==1&&oaProcApplylist.financeStatus==1}">
						申请中
					</c:if>
					<c:if test="${oaProcApplylist.financeStatus==2||oaProcApplylist.financeStatus==2}">
						已通过
					</c:if>
					<c:if test="${oaProcApplylist.financeStatus==-1||oaProcApplylist.financeStatus==-1}">
						驳回
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${oaProcApplylist.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${oaProcApplylist.remarks}
				</td>
				<shiro:hasPermission name="oa:proc:oaProcApplylist:edit">
                    <td>
                        <a href="${ctx}/oa/proc/oaProcApplylist/form?id=${oaProcApplylist.id}">修改</a>
                        <a href="${ctx}/oa/proc/oaProcApplylist/delete?id=${oaProcApplylist.id}" onclick="return confirmx('确认要删除该保存采购申请单成功吗？', this.href)">删除</a>
				    </td>
                </shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>


	</fieldset>
</body>
</html>