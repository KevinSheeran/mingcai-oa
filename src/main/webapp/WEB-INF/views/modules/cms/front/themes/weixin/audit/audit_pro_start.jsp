<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>明材办公-项目启动</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body >
<Style>
	.weui-btn-area{
		width: 90%;
		margin: 0 auto;
		text-align: center;
	}
	.weui-btn-area a{
		width: 40%;
		float: left;
		margin: 15px 5%;
	}
	p{
		color: #333;
	}
</Style>
<div class="page">
	<div class="page__bd">
		<div class="weui-cells__title">预立项信息</div>
		<div class="weui-cells">
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>项目</p>
				</div>
				<div class="weui-cell__ft">${oepro.name}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>处理状态</p>
				</div>
				<div class="weui-cell__ft">${fns:getDictLabel(oeprostart.status,'pro_start_status' , '')}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>预立项编号</p>
				</div>
				<div class="weui-cell__ft"><c:if test="${oepro.status<3}">未定义</c:if><c:if test="${oepro.status>=3}">${oepro.paNumber}</c:if></div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>责任销售</p>
				</div>
				<div class="weui-cell__ft">${oepro.personLiableUser.name}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>客户名称</p>
				</div>
				<div class="weui-cell__ft">${oepro.customerName}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>客户方负责人</p>
				</div>
				<div class="weui-cell__ft">${oepro.customerUser}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>项目类型</p>
				</div>
				<div class="weui-cell__ft">${fns:getDictLabel(oepro.proType,'eos_type',"" )}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>定位</p>
				</div>
				<div class="weui-cell__ft">${oepro.proLocation}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>资金预算</p>
				</div>
				<div class="weui-cell__ft">${fns:getDictLabel(oepro.proCapitalSource,'eos_source',"" )}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>周期</p>
				</div>
				<div class="weui-cell__ft">${fns:getDictLabel(oepro.proCycle,'eos_cycle',"" )}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>项目预估金额</p>
				</div>
				<div class="weui-cell__ft">${oepro.estimatedAmount}&nbsp;元</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>毛利率百分比</p>
				</div>
				<div class="weui-cell__ft">${oepro.grossProfitMargin}%</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>前期投入估算</p>
				</div>
				<div class="weui-cell__ft">${oepro.inputEstimation}&nbsp;元</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>前期工作量估算</p>
				</div>
				<div class="weui-cell__ft">${oepro.workload}&nbsp;人/天</div>
			</div>
		</div>
		<div class="weui-cells__title">项目启动信息</div>
		<div class="weui-cells">
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>项目编号</p>
				</div>
				<div class="weui-cell__ft"><c:if test="${oepro.proNumber==null||oepro.proNumber==''}">未定义</c:if><c:if test="${oepro.proNumber!=null&&oepro.proNumber!=''}">${oepro.proNumber}</c:if></div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>项目经理</p>
				</div>
				<div class="weui-cell__ft">${oeprostart.personLiableUser.name}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>合同金额</p>
				</div>
				<div class="weui-cell__ft">￥${oeprostart.contractAmount}&nbsp;元</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>预算金额</p>
				</div>
				<div class="weui-cell__ft">￥${oeprostart.estimatedAmount}&nbsp;元</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>利润</p>
				</div>
				<div class="weui-cell__ft">${oeprostart.grossProfitMargin}%</div>
			</div>

		</div>
		<div class="weui-cells__title">项目支持人员</div>
		<div class="weui-cells">
			<div class="weui-grids">
				<c:forEach items="${oeprostart.users}" var="user">
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="${user.wxUsers.avatar}/100" alt="">
						</div>
						<p class="weui-grid__label">${user.wxUsers.name}</p>
					</a>
				</c:forEach>
			</div>
		</div>
		<div class="weui-cells__title">项目收款计划</div>
		<c:forEach items="${planlist}" var="plan">
		<div class="weui-cells">
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>收款类型</p>
				</div>
				<div class="weui-cell__ft">${plan.cplanContent}</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>收款时间</p>
				</div>
				<div class="weui-cell__ft"><fmt:formatDate value="${plan.receivablesDate}" pattern="yyyy-MM-dd"/></div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>实际收款时间</p>
				</div>
				<div class="weui-cell__ft"><fmt:formatDate value="${plan.finishDate}" pattern="yyyy-MM-dd"/></div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>收款比例</p>
				</div>
				<div class="weui-cell__ft">${plan.proportion}%</div>
			</div>
			<div class="weui-cell">
				<div class="weui-cell__bd">
					<p>是否收款完成</p>
				</div>
				<div class="weui-cell__ft"><c:if test="${plan.status==0}">否</c:if><c:if test="${plan.status==1}">是</c:if></div>
			</div>
		</div>
		</c:forEach>
		<div class="weui-cells__title">子项目详情</div>
		<c:forEach items="${itmelist}" var="item">
			<div class="weui-cells">
				<div class="weui-cell">
					<div class="weui-cell__bd">
						<p>名称</p>
					</div>
					<div class="weui-cell__ft">${item.name}</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__bd">
						<p>编号</p>
					</div>
					<div class="weui-cell__ft">${item.code}</div>
				</div>
			</div>
		</c:forEach>
		<c:forEach items="${flowmap}" var="flow" varStatus="index">
			<div class="weui-cells__title"><c:if test="${index.index==0}">管理中心审批</c:if><c:if test="${index.index==1}">总经理审批</c:if></div>
			<div class="weui-panel weui-panel_access">
				<div class="weui-panel__bd">
					<c:forEach items="${flow.value}" var="item">
						<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
							<c:if test="${item.status==1}"><i class="badge weui-icon-success"></i></c:if>
							<c:if test="${item.status==-1}"><i class="weui-icon-cancel"></i></c:if>
							<div class="weui-media-box__hd">
								<img class="weui-media-box__thumb" src="${item.user.wxUsers.avatar}/100" alt="">
							</div>
							<div class="weui-media-box__bd">
								<h4 class="weui-media-box__title">${item.user.wxUsers.name}</h4>
								<p class="weui-media-box__desc">${item.content}</p>
							</div>
						</a>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
		<c:if test="${flow.status!=null}">
			<div class="weui-cells__title">意见建议</div>
			<div class="weui-cells">
				<div class="weui-cell">
					<div class="weui-cell__bd">
						<textarea class="weui-textarea" id="content" placeholder="请输入文本" rows="3"></textarea>
						<div class="weui-textarea-counter"><span>0</span>/200</div>
					</div>
				</div>
			</div>
			<div class="weui-btn-area">
				<a href="javascript:;" class="weui-btn <c:if test="${oeprostart.status!=1}"> disabled</c:if> weui-btn_primary" oeproid="${oepro.id}" flowid="${flow.id}"  id="showIOSDialog">同意</a>
				<a href="javascript:;" class="weui-btn <c:if test="${oeprostart.status!=1}"> disabled</c:if> weui-btn_default" oeproid="${oepro.id}" flowid="${flow.id}"  id="backIOSDialog">驳回</a>
			</div>
		</c:if>
	</div>
</div>
<script type="application/javascript">
    $('#showIOSDialog').on('click', function(){
        $(this).addClass("disabled");
        var eosid=$(this).attr("oeproid");
        var flowid=$(this).attr("flowid");
        var btnArray = ['否', '是'];
        mui.confirm('确认通过项目启动审核吗？', '系统提示', btnArray, function(e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/auditstart",{eosid:eosid,flowid:flowid,content:$("#content").val()},function(data){
                    data=JSON.parse(data);
                    if(data.success){
                        mui.toast('审核完成！');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    }else{
                        $("#showIOSDialog").removeClass("disabled")
                        mui.alert(data.message, '系统提示', function() {
                        });
                    }
                })
            } else {
                $("#showIOSDialog").removeClass("disabled")
            }
        })
    });
    $('#backIOSDialog').on('click', function(){
        $(this).addClass("disabled");
        var eosid=$(this).attr("oeproid");
        var flowid=$(this).attr("flowid");
        var btnArray = ['否', '是'];
        mui.confirm('确认驳回立项审核吗？', '系统提示', btnArray, function(e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/auditstartback",{eosid:eosid,flowid:flowid,content:$("#content").val()},function(data){
                    data=JSON.parse(data);
                    if(data.success){
                        mui.toast('驳回完成！');
                        setTimeout(function () {
                            location.reload();
                        }, 2000);
                    }else{
                        $("#backIOSDialog").removeClass("disabled")
                        mui.alert(data.message, '系统提示', function() {
                        });
                    }
                })
            } else {
                $("#backIOSDialog").removeClass("disabled")
            }
        })
    });
</script>
</body>
</html>