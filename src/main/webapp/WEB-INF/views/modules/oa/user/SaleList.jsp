<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信用户管理</title>
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
        hs.graphicsDir = '${ctxStatic}/highslide/js/graphics/';
        hs.align = 'center';
        if (hs.addSlideshow) hs.addSlideshow({
            //interval: 10000,
        });
        function save(){
			$("#form").submit();
		}
		function showUserAccount(userid,name){

            // 正常打开
            top.$.jBox.open("iframe: ${ctx}/oa/user/oaUserAccount/userindex?id="+userid, name+"个人费用情况",1020,$(window).height(), {
                buttons:{}, submit:function(v, h, f){
                }, closed:function(){
                  //location.reload();
				},loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });
		}
	</script>
</head>

<body>
<style>
	.success{
		color: #00b83f;
	}
	.error{
		color: #ff0000;
	}
</style>
<fieldset>
	<sys:message content="${message}"/>
	<legend><div style="padding: 10px 10px; text-align: left "><a >公司销售销售额管理</a> <a class="btn pull-right btn-primary" onclick="save()"><i class="icon-plus"></i>&nbsp;保存信息</a></div></legend>
	<form id="form" method="post" action="${ctx}/oa/user/oaUserAccount/SalesSave">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>成员</th>
				<th>手机号码</th>
				<th>年销售额(元)</th>
				<th>账户状态</th>
				<th>查看详情</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page}" var="oaWxUsers">
			<tr>
				<td>
					<img src="${oaWxUsers.avatar}/100" width="50px">&nbsp;&nbsp;
					${oaWxUsers.name}
				</td>
				<td>
					${oaWxUsers.mobile}
				</td>
				<c:if test="${oaWxUsers.user!=null}">
				<td>
						<input type="number" name="${oaWxUsers.user.id}" value="${oaWxUsers.account!=null?fns:gnumberFarmatTwo(oaWxUsers.account.salesVolumeId.salesVolume):""}" class="numbers">元
				</td>
					<td>
						<c:if test="${oaWxUsers.account==null}">未设置销售额</c:if>
						<c:if test="${oaWxUsers.account.flag=='0'}"><span class="success">正常</span></c:if>
						<c:if test="${oaWxUsers.account.flag=='1'}"><span class="error">冻结</span></c:if>
					</td>
				<td>
					<a href="javascript:void(0);" onclick="showUserAccount('${oaWxUsers.user.id}','${oaWxUsers.name}')">个人详情</a>
				</td>
				</c:if>
				<c:if test="${oaWxUsers.user==null}">
					<td colspan="3">
						微信账户未与oa系统绑定，请让用户在oa系统中绑定手机号
					</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form>
</fieldset>
</body>
</html>