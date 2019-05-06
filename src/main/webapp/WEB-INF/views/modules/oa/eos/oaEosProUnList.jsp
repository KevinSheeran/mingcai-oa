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
		#deskIcon li{
			display: block;
			float: left;
			width: 120px;
			height: 170px;
			text-align: center;
			padding: 10px;
			margin-left: 10px;
			cursor: pointer;
			position: relative;
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
		#deskIcon li .status{
			position: absolute;
			top:12px;
			right: -8px;
			font-size: 12px;
			color: #fff;
			padding: 3px 5px;

		}
		#deskIcon li img{
			margin: 10px auto 0px;
			width: 100%;
		}
		#deskIcon li.dir img{
			margin: 10px auto 0px;
			width: 100%;
		}
		#deskIcon li:hover{
			background:url("/static/windowsstyle/images/icon_over.png") no-repeat center center;
			background-size: 100%;
		}

		#deskIcon li .text{
			margin-top:5px ;
			text-align: center;
			line-height: 18px;
			height: 36px;
			width: 100%;
			font-size: 16px;
			display: -webkit-box;
			display: -moz-box;
			-moz-box-orient: vertical;
			-moz-line-clamp: 2;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 2;
			overflow: hidden;
		}
	</style>
	<script type="text/javascript">
        $(document).ready(function() {

        });
        function flowPro(id){
            // 正常打开
            top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosProUn/flowPage?id="+id, "项目跟踪", $(window).width()*4/5,$(window).height(), {
                buttons:{"关闭":true}, submit:function(v, h, f){
                }, loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });
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
	<legend><div style="padding: 10px 10px; text-align: left "><a >非销售项目立项</a>&nbsp;&nbsp;<a class="btn" href="${ctx}/oa/eos/oaEosProUn/form"><i class="icon-plus"></i>&nbsp;立项添加</a></div></legend>
	<form:form id="searchForm" modelAttribute="oaEosProUn" action="${ctx}/oa/eos/oaEosProUn" method="post" class="breadcrumb form-search">
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
			<th>立项编号</th>
			<th>项目类型</th>
			<th>预算金额</th>
			<th>投入金额</th>
			<th>责任销售</th>
			<th>立项状态</th>
			<th>创建日期</th>
			<th>操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="item">
			<tr>
				<td><a href="${ctx}/oa/eos/oaEosProUn/form?id=${item.id}">
						${item.name}
				</a></td>
				<td>
						${item.proNumber}
				</td>
				<td>
						${fns:getDictLabel(item.proType,'pro_un_type' , null)}
				</td>
				<td>
					￥${item.estimation}元
				</td>
				<td>
					￥${item.realInputEstimation==''?0:item.realInputEstimation}元
				</td>
				<td><img  width="40px;" src="${item.personLiableUser.wxUsers.avatar}/100" alt="">&nbsp;&nbsp;${item.personLiableUser.name}</td>
				<td>
					<div class="<c:if test="${item.status==0}">dir_info</c:if><c:if test="${item.status==-1||oaProduct.status==1}">dir_warning</c:if><c:if test="${item.status==2}">dir_success</c:if>">
							${fns:getDictLabel(item.status,'pro_start_status' , '')}
					</div>
				</td>
				<td>
					<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<a href="${ctx}/oa/eos/oaEosProUn/form?id=${item.id}">
						编辑
					</a>
					<a href="javascript:flowPro('${item.id}');">
						项目跟踪
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</fieldset>
</body>
</html>