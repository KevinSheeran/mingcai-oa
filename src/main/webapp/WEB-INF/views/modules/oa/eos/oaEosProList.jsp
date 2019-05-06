<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售项目立项管理</title>
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
	<legend><div style="padding: 10px 10px; text-align: left "><a >项目列表</a>&nbsp;&nbsp;<a class="btn" href="${ctx}/oa/eos/oaEosPro/form"><i class="icon-plus"></i>&nbsp;预立项添加</a>
		<c:if test="${oaEosPro.leader}">
			<div class="pull-right" style="font-size: 14px;">
			预立项完成数：<span class="money">${procount.pacount}个</span>&nbsp;预立项总估算：<span class="money">￥${fns:formatNumber(procount.pasum)}元</span>&nbsp;&nbsp;立项完成数：<span class="money">${procount.procount}个</span>&nbsp;&nbsp;立项合同总金额：<span class="money">￥${fns:formatNumber(procount.prosum)}元</span>
			</div>
		</c:if>
	</div></legend>
	<form:form id="searchForm" modelAttribute="oaEosPro" action="${ctx}/oa/eos/oaEosPro/index" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称或编号：</label>
				<form:input path="name" htmlEscape="false" maxlength="200" cssStyle="width: 100px;" class="input-medium"/>
			</li>
			<li>
				<label>状态：</label>
				<form:select path="status" class="input-xlarge required" cssStyle="width: 150px;">
					<option value="">全部</option>
					<form:options items="${fns:getDictList('oa_eos_pro_status')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>项目类型</th>
				<th>项目预估(万元)</th>
				<th>毛利率</th>
				<th>前期投入预估(万元)</th>
				<th>已发生费用(元)</th>
				<th>销售</th>
				<th>立项状态</th>
				<th>创建日期</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr>
					<td><a href="${ctx}/oa/eos/oaEosPro/form?id=${item.id}">
							${item.name}
					</a></td>
					<td>
						${item.paNumber}
					</td>
					<td>
							${item.proNumber}
					</td>
					<td>
							${fns:getDictLabel(item.proType,'eos_type' , null)}
					</td>
					<td>
						<span class="money">￥${item.estimatedAmount}</span>
					</td>
					<td>
							${item.grossProfitMargin}%
					</td>
					<td>
						<span class="money">￥${item.inputEstimation==''?0:item.inputEstimation}</span>
					</td>
					<td>
						<span class="money">￥${item.realInputEstimation}</span>
					</td>
					<td><img  width="40px;" src="${item.personLiableUser.wxUsers.avatar}/100" alt="">&nbsp;&nbsp;${item.personLiableUser.name}</td>
					<td>
						<div class="<c:if test="${item.status==1||item.status==2}">dir_info</c:if><c:if test="${item.status==-1||item.status==0||item.status==-2||item.status==4}">dir_warning</c:if><c:if test="${item.status==3||item.status==5}">dir_success</c:if>">
							${fns:getDictLabel(item.status,'oa_eos_pro_status' , '')}
						</div>
					</td>
					<td>
						<fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/>
					</td>
					<td>
						<a href="${ctx}/oa/eos/oaEosPro/form?id=${item.id}">
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
<script>
    function openWin(url){
        self.location=url;
    }
    function flowPro(id){
        // 正常打开
        top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosPro/flowPage?id="+id, "项目跟踪", $(window).width()*4/5,$(window).height(), {
            buttons:{"关闭":true}, submit:function(v, h, f){
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });

    }
</script>
</body>
</html>