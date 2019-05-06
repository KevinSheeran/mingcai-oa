<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人账户</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        function goApplyAccount(id){
            if(!id){
                id="";
			}
            // 正常打开
			top.$.jBox.open("iframe:${ctx}/oa/user/oaUserSalesVolume/form?id="+id, "额度申请",700,600, {
                buttons:{}, submit:function(v, h, f){
                    if (v=="ok"){
						return false;
                    }
                }, closed:function(){
                    location.reload();
                },loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });
        }
        function cancelApplyAccount(id){
            // 正常打开
            confirmx("确定要取消审核吗？","${ctx}/oa/user/oaUserAccount/cancel?id="+id,"");
        }
        function frozenAccount(id){
            // 正常打开
            confirmx("您确定要冻结此账户吗？","${ctx}/oa/user/oaUserAccount/frozenAccount?id="+id,"");
		}
        function unfrozenAccount(id){
            // 正常打开
            confirmx("您确定要解除冻结此账户吗？","${ctx}/oa/user/oaUserAccount/unfrozenAccount?id="+id,"");
        }
	</script>
</head>
<body>
<sys:message content="${message}" type="${type}"/>
<table  class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th colspan="6">销售额信息</th>
	</tr>
	</thead>
	<tbody>
		<tr>
			<c:if test="${oaUserAccount.id==null}">
				<tr >
					<td colspan="6">您的年销售额度未设定，请与公司销售总经理联系，设定年销售额！</td>
				</tr>
			</c:if>
			<c:if test="${oaUserAccount!=null&&oaUserAccount.id!=null}">
				<td>年销售额度：￥${fns:formatNumber(oaUserAccount.salesVolumeId!=null?oaUserAccount.salesVolumeId.salesVolume:0)}元</td>
				<td>每月可用额度：￥${fns:formatNumber(oaUserAccount.branchQuota)}元(使用销售额比例 ${oaUserAccount.salesVolumeId.uotaRatio}%)</td>
				<td>本月已用额度：￥${fns:formatNumber(oaUserAccount.costIncurred)}元</td>
				<td>资金池额度：￥${fns:formatNumber(oaUserAccount.poolFunds)}元</td>
			</c:if>
			<c:if test="${oaUserAccount!=null&&oaUserAccount.leader&&oaUserAccount.salesVolumeId!=null&&oaUserAccount.flag=='0'}">
				<td><button type="button" class="btn btn-primary" onclick="frozenAccount('${oaUserAccount.id}');">冻结账户</button></td>
			</c:if>
			<c:if test="${oaUserAccount!=null&&oaUserAccount.leader&&oaUserAccount.salesVolumeId!=null&&oaUserAccount.flag=='1'}">
				<td><button type="button" class="btn btn-danger" onclick="unfrozenAccount('${oaUserAccount.id}');">解除冻结</button></td>
			</c:if>
			<%--<c:if test="${oaUserAccount!=null&&oaUserAccount.status==0}">--%>
				<%--<td>申请已撤销&nbsp;&nbsp;<button type="button" class="btn btn-primary" onclick="goApplyAccount('${oaUserAccount.salesVolumeId.id}');">重新申请</button></td>--%>
			<%--</c:if>--%>
			<%--<c:if test="${oaUserAccount!=null&&oaUserAccount.status==2}">--%>
				<%--<td>正常使用中&nbsp;&nbsp;<button type="button" class="btn btn-primary" onclick="goApplyAccount();">申请新销售额</button></td>--%>
			<%--</c:if>--%>
			<%--<c:if test="${oaUserAccount!=null&&oaUserAccount.status==1}">--%>
				<%--<td>申请中&nbsp;&nbsp;<button type="button" class="btn btn-primary" onclick="cancelApplyAccount('${oaUserAccount.id}');">撤销申请</button></td>--%>
			<%--</c:if>--%>
			<%--<c:if test="${oaUserAccount!=null&&oaUserAccount.status==-1}">--%>
				<%--<td>申请驳回&nbsp;&nbsp;<button type="button" class="btn btn-primary" onclick="goApplyAccount('${oaUserAccount.salesVolumeId.id}');">重新申请</button></td>--%>
			<%--</c:if>--%>
		</tr>
	</tbody>
</table>
<table  class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th colspan="6">我的项目结算情况</th>
	</tr>
	</thead>
	<thead>
	<tr>
		<th>项目</th>
		<th>创建时间</th>
		<th>合同金额</th>
		<th>项目总支出</th>
		<th>项目状态</th>
		<th>资金结算</th>
		<th>个人项目支出</th>
	</tr>
	</thead>
	<tbody>
	<c:if test="${prolist.size()==0}">
		<tr >
			<td colspan="9">暂无数据</td>
		</tr>
	</c:if>
	<c:forEach var="pro" items="${prolist}">
		<tr>
			<td>${pro.name}</td>
			<td><fmt:formatDate value="${pro.createDate}" pattern="yyyy年MM月dd日"/></td>
			<td><c:if test="${pro.start.contractAmount!=null}">￥${fns:formatNumber(pro.start.contractAmount)}元</c:if><c:if test="${pro.start.contractAmount==null}">未定义</c:if></td>
			<td>￥${fns:formatNumber(pro.realInputEstimation+pro.start.realInputEstimation)}元</td>
			<td>${fns:getDictLabel(pro.status,'oa_eos_pro_status' , '')}</td>
			<td><c:if test="${pro.oaUserProSettlement==null}">未结算</c:if><c:if test="${pro.oaUserProSettlement!=null}">￥${fns:formatNumber(pro.oaUserProSettlement.expenditure)}元</c:if></td>
			<td>￥${fns:sumMoney(pro.id,pro.personLiableUser.id)}元</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<table  class="table table-striped table-bordered table-condensed">
	<thead>
	<tr>
		<th colspan="5">历史额度使用情况</th>
	</tr>
	</thead>
	<tbody>
	<c:if test="${userlogList.size()==0}">
		<tr >
			<td colspan="9">暂无数据</td>
		</tr>
	</c:if>
	<c:forEach items="${userlogList}" var="item">
		<tr>
			<td><fmt:formatDate value="${item.beginEndMonth}" pattern="yyyy-MM"/></td>
			<td>使用销售额额度：￥${fns:formatNumber(item.salesVolumeId.salesVolume)}元</td>
			<td>每月可用额度：￥${fns:formatNumber(item.branchQuota)}元</td>
			<td>当月使用额度：￥${fns:formatNumber(item.costIncurred)}元</td>
			<td>当月资金池额度：￥${fns:formatNumber(item.poolFunds)}元</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
</body>
</html>