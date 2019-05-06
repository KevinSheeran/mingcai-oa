<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目立项管理</title>
	<meta name="decorator" content="default"/>
	<style>
		html,body{
			height:100%;
			width: 100%;
		<c:if test="${list.size()==0}">
			background:url("${ctxStatic}/windowsstyle/icon/nofile.png") no-repeat center;
			background-size: 10%;
		</c:if>
			background-color:#f5f5f5;
		}
		#deskIcon{
			margin: 0px;
			padding: 0px;
			width: 100%;
			height: auto;
		}
		.dir_info{
			color: #5bc0de;
		}
		.dir_success{
			color: #5cb85c;
		}
		.dir_warning{
			color: #f0ad4e;
		}

	</style>
	<script type="text/javascript">
        function flowPro(id){
            // 正常打开
           top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosProStart/flowPage?id="+id, "项目跟踪", $(window).width()*4/5,$(window).height(), {
                buttons:{"关闭":true}, submit:function(v, h, f){
                }, loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });

        }
        function ProEnd(id,name){
            // 正常打开
            confirmx("确定要结算\""+name+"\"吗？","${ctx}/oa/eos/oaEosProStart/proEnd?id="+id,"");
		}
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
	</script>
</head>
<body>
<sys:message content="${message}"/>
<fieldset>
	<legend><div style="padding: 10px 10px; text-align: left "><a >销售项目立项</a></div></legend>
	<form:form id="searchForm" modelAttribute="oaEosProStart" action="${ctx}/oa/eos/oaEosProStart/index" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称或编号：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li>
				<label>状态：</label>
				<form:select path="status" class="input-xlarge required">
					<option value="">全部</option>
					<form:options items="${fns:getDictList('pro_start_status')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
			<th>项目名</th>
			<th>预立项编号</th>
			<th>立项编号</th>
			<th>合同金额(万元)</th>
			<th>预算金额(万元)</th>
			<th>毛利率</th>
			<th>投入金额(元)</th>
			<th>销售</th>
			<th>项目经理</th>
			<th>立项状态</th>
			<th>结算状态</th>
			<th>创建时间</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td><a href="${ctx}/oa/eos/oaEosProStart/form?id=${item.id}">
						${item.pro.name}
				</a></td>
				<td>
						${item.pro.paNumber}
				</td>
				<td>
						${item.pro.proNumber}
				</td>
				<td>
					<span class="money">￥${item.contractAmount/10000}</span>
				</td>
				<td>
					<span class="money">￥${item.estimatedAmount/10000}</span>
				</td>
				<td>
						${item.grossProfitMargin}%
				</td>
				<td>
					<span class="money">￥${item.realInputEstimation==''?0:item.realInputEstimation}</span>
				</td>
				<td><img  width="40px;" src="${item.pro.personLiableUser.wxUsers.avatar}/100" alt="">&nbsp;&nbsp;${item.pro.personLiableUser.name}</td>
				<td><c:if test="${item.personLiableUser!=null}"><img  width="40px;" src="${item.personLiableUser.wxUsers.avatar}/100" alt="">&nbsp;&nbsp;${item.personLiableUser.name}</c:if><c:if test="${item.personLiableUser==null}">未指定</c:if></td>
				<td>
					<div class="<c:if test="${item.status==0}">dir_info</c:if><c:if test="${item.status==-1||item.status==1}">dir_warning</c:if><c:if test="${item.status==2}">dir_success</c:if>">
							${fns:getDictLabel(item.status,'pro_start_status' , '')}
					</div>
				</td>
				<td>
					<c:if test="${item.settlement==0||item.settlement==null}">
						<div class="dir_info">
							未结算
						</div>
					</c:if>
					<c:if test="${item.settlement==1}">
						<div class="dir_success">
							已结算
						</div>
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${item.createDate==null?item.updateDate:item.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<a href="${ctx}/oa/eos/oaEosProStart/form?id=${item.id}">
						编辑
					</a>
					<a href="javascript:flowPro('${item.id}');">
						项目跟踪
					</a>
					<c:if test="${proUn.leader&&item.status==2&&(item.settlement==0||item.settlement==null)}">
						<a href="javascript:ProEnd('${item.id}','${item.pro.name}');">
							项目结算
						</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</fieldset>
</body>
</html>