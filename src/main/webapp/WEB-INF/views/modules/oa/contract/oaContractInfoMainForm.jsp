<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>合同管理管理</title>
	<meta name="decorator" content="default"/>
	<style>
		html,body{
			height:100%;
			width: 100%;
			background-color:#f5f5f5;
		}
		.deskIcon{
			margin: 0px;
			padding: 0px;
			width: 100%;
			height: auto;
		}
		.deskIcon li{
			display: block;
			float: left;
			width: 86px;
			min-height: 100px;
			text-align: center;
			padding: 10px;
			cursor: pointer;
			border: 0.5px solid #f5f5f5;
			border-radius: 3px;

		}
		.deskIcon li img{
			margin: 10px auto 0px;
			width: 60%;
		}
		.deskIcon li.dir img{
			margin: 0px auto 0px;
			width: 90%;
		}
		.deskIcon li:hover{
			background: rgba(130,130,130,0.4);
			border: 0.5px solid #cccccc;
		}
		.deskIcon li .text{
			text-align: center;
			line-height: 16px;
			font-size: 14px;
			height: 32px;
			display: -webkit-box;
			display: -moz-box;
			-moz-box-orient: vertical;
			-moz-line-clamp: 2;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 2;
			overflow: hidden;
		}
		.title{
			margin-left:10px ;

		}
	</style>
</head>
<body>
<fieldset>
	<legend>
		<div style="padding: 0px 10px; text-align: left "><a   href="${ctx}/oa/oaFinanceProduct/proList" >${oaFinanceProduct.name}</a>>>合同详情</div>
	</legend>
</fieldset>
<sys:message content="${message}"/>
<fieldset>
	<legend>
		<fieldset>
			<legend><div class="title">销售合同</div></legend>
			<ul class="deskIcon">
				<li class="dir">
					<a href="${ctx}/oa/contract/oaContractInfo/form?finProductId=${oaFinanceProduct.id}&id=${oaContractInfo.id}">
						<span class="icon"><img src="/static/windowsstyle/icon/ooopic_1517905531.png"/></span>
						<div class="text">
							销售合同信息
						</div>
					</a>
				</li>
			</ul>
		</fieldset>
	</legend>
	<legend>
		<fieldset>
            <legend><div class="title">采购合同<c:if test="${oaContractInfo.id!=null&&oaContractInfo.id!=''}">&nbsp;&nbsp;<a class="btn" href="${ctx}/oa/contract/oaContractPurchaseInfo/form?finProductId=${oaFinanceProduct.id}"><i class="icon-plus"></i>&nbsp;新加采购合同</a></c:if></div></legend>

            <ul class="deskIcon">
                <c:if test="${oaContractInfo.id==null||oaContractInfo.id==''}"><span style="color: #7b7b7b;margin-left:10px;font-size:14px;  ">请添加完销售合同信息后添加采购合同!</span></c:if>
				<c:forEach items="${purchaseInfoList}" var="item">
				<li class="dir">
					<a href="${ctx}/oa/contract/oaContractPurchaseInfo/form?finProductId=${oaFinanceProduct.id}&&id=${item.id}">
						<span class="icon"><img src="/static/windowsstyle/icon/ooopic_1517905531.png"/></span>
						<div class="text">
							${item.name}
						</div>
					</a>
				</li>
				</c:forEach>
			</ul>
		</fieldset>
	</legend>
	<legend>
		<fieldset>
            <legend><div class="title">劳务合同<c:if test="${oaContractInfo.id!=null&&oaContractInfo.id!=''}">&nbsp;&nbsp;<a class="btn" href="${ctx}/oa/contract/oaContractServicesInfo/form?finProductId=${oaFinanceProduct.id}"><i class="icon-plus"></i>&nbsp;新加劳务合同</a></c:if></div></legend>
			<ul class="deskIcon">
				<c:if test="${oaContractInfo.id==null||oaContractInfo.id==''}"><span style="color: #7b7b7b;margin-left:10px;font-size:14px;  ">请添加完销售合同信息后添加劳务合同!</span></c:if>
				<c:forEach items="${servicesInfoList}" var="item">
					<li class="dir">
						<a href="${ctx}/oa/contract/oaContractServicesInfo/form?finProductId=${oaFinanceProduct.id}&&id=${item.id}">
							<span class="icon"><img src="/static/windowsstyle/icon/ooopic_1517905531.png"/></span>
							<div class="text">
									${item.name}
							</div>
						</a>
					</li>
				</c:forEach>
			</ul>
		</fieldset>
	</legend>
</fieldset>
</body>
</html>