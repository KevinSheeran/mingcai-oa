<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>合同管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
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
		});
	</script>
</head>
<body>
	<form:form id="inputForm" modelAttribute="oaContractInfo" action="${ctx}/oa/contract/oaContractInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" name="finProductId" value="${oaFinanceProduct.id}">
		<input type="hidden" name="productName" value="${oaFinanceProduct.name}">
		<input type="hidden" name="name" value="${oaFinanceProduct.company.name}">
	<fieldset>
		<legend><div style="padding: 0px 10px; text-align: left "><a href="${ctx}/oa/contract/oaContractInfo/mainForm?finProductId=${oaFinanceProduct.id}">${oaFinanceProduct.name}</a>>>销售合同信息</div> </legend>
<table class="table-form">
	<tr>
		<td class="tit">项目名称：</td><td>${oaFinanceProduct.name}</td>
		<td class="tit">单位名称：</td><td>${oaFinanceProduct.company.name}</td>
	</tr>
	<tr>
		<td class="tit" >合同主体：</td><td>
		<form:select path="subject" class="input-xlarge required">
			<form:options items="${fns:getDictList('subject')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
		</form:select>
	</td>
		<td class="tit">银行账号：</td><td><form:input path="bankAccount" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
	</tr>
	<tr>
	<td class="tit">销售人员：</td><td>
			<sys:treeselect id="contacts" parents="true" name="contacts" value="${oaContractInfo.contacts}" labelName="${oaContractInfo.userName}" labelValue="${oaContractInfo.userName}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
			<span class="help-inline"><font color="red">*</font> </span>
		</td>
		<td class="tit">开户行：</td><td><form:input path="openingBank" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>

</tr>
	<tr>
		<td class="tit">验收时间节点：</td><td><input name="timeNode" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
											   value="<fmt:formatDate value="${oaContractInfo.timeNode}" pattern="yyyy-MM-dd HH:mm:ss"/>"
											   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>
		<td class="tit">纳税人识别号：</td><td><form:input path="dutyParagraph" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
	</tr>
	<tr>
		<td class="tit">付款完成状态：</td><td><span id="pay_status">0</span>%</td>
		<td class="tit">联系电话：</td><td><form:input path="contactNumber" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
	</tr>
	<shiro:hasPermission name="oa:contract:oaContractInfo:edit">
	<tr><td colspan="4">
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary pull-left" type="submit" value="保存合同基本信息"/>&nbsp;
		</div>

	</td></tr>
	</shiro:hasPermission>
	<tr><td colspan="4">
		<h4 style="padding: 10px 0;padding-left:10px; color: #666;">
			付款条约&nbsp;&nbsp;
			<shiro:hasPermission name="oa:contract:oaContractPayment:edit">
			<c:if test="${oaContractInfo.id!=null&&oaContractInfo.id!=''}"><a class="btn " style="" onclick="addPayment('${oaContractInfo.id}')"><i class="icon-plus"></i>&nbsp;添加付款信息</a></c:if>
			</shiro:hasPermission>
		</h4>
	</td></tr>
	<tr>
		<td colspan="4" style="padding-left:15px;">
			<c:if test="${oaContractInfo.id!=null&&oaContractInfo.id!=''}">
				<table class="table-form" id="payment">
					<c:forEach items="${OaContractPaymentList}" var="item" varStatus="index">
						<tr >
							<td class="tit">${item.batchId}</td>
							<td>￥${item.money}元</td>
							<td class="tit">付款日期</td>
							<td><fmt:formatDate value="${item.paymentDate}" pattern="yyyy年MM月dd日"/></td>
							<td class="tit">支付比例</td>
							<td><span>${item.proportion}%<c:if test="${item.status=='1'}"> <span class="pay_status" val="${item.proportion}"  style="color:#228B22"><i class="icon-ok color-bar"></i>支付完成</span></c:if></td>
							<shiro:hasPermission name="oa:contract:oaContractPayment:edit">
								<td><a class="btn" onclick="updatePayment('${item.contractId}','${item.id}',this)">调整</a><a class="btn" onclick="delPayment('${item.id}',this)">删除</a></td>
							</shiro:hasPermission>
						</tr>
					</c:forEach>
				</table>

			</c:if>
			<c:if test="${oaContractInfo.id==null||oaContractInfo.id==''}">
				请保存基本信息后操作此项
			</c:if>
		</td>
	</tr>
	<tr><td colspan="4">
		<h4 style="padding: 10px 0; padding-left:10px; color: #666;">
			合同项&nbsp;&nbsp;
			<shiro:hasPermission name="oa:contract:oaContractTerms:edit">
			<c:if test="${oaContractInfo.id!=null&&oaContractInfo.id!=''}"><a class="btn " style="" onclick="addTrem('${oaContractInfo.id}')"><i class="icon-plus"></i>&nbsp;添加合同项目</a></c:if>
			</shiro:hasPermission>
			合同项总合计:￥<span id="trem_total_money">0</span>元&nbsp;&nbsp;采购合计:￥<span id="purchase_total_money">0</span>元
			<span class="pull-right " style="margin-right: 10px;cursor:pointer;" onclick="showIframe(this)"><i class="icon-chevron-up"></i>&nbsp;收起</span>
		</h4>
	</tr>
	<tr>
		<td colspan="4" style="padding-left:15px;">
			<c:if test="${oaContractInfo.id!=null&&oaContractInfo.id!=''}">
			<iframe src="${ctx}/oa/contract/oaContractTerms?contractId=${oaContractInfo.id}" name="trem" id="trem" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="250">

			</iframe>
			</c:if>
			<c:if test="${oaContractInfo.id==null||oaContractInfo.id==''}">
				请保存基本信息后操作此项
			</c:if>
	</td>
	</tr>
	<tr><td colspan="4">
		<h4 style="padding: 10px 0; padding-left:10px; color: #666;">
			开票信息<shiro:hasPermission name="oa:contract:oaContractInvoice:edit">&nbsp;&nbsp;<c:if test="${oaContractInfo.id!=null&&oaContractInfo.id!=''}"><a class="btn " style="" onclick="addnvoice('${oaContractInfo.id}')"><i class="icon-plus"></i>&nbsp;添加开票</a></c:if></shiro:hasPermission>
		</h4>
	</tr>
	<tr>
		<td colspan="4" style="padding-left:15px;">
			<c:if test="${oaContractInfo.id!=null&&oaContractInfo.id!=''}">
				<iframe src="${ctx}/oa/contract/oaContractInvoice?contractId=${oaContractInfo.id}" name="invoice" id="invoice" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="250">

				</iframe>
			</c:if>
			<c:if test="${oaContractInfo.id==null||oaContractInfo.id==''}">
				请保存基本信息后操作此项
			</c:if>
		</td>
	</tr>
</table>
	</fieldset>
</form:form>
<!-- 付款条约管理-->
<script type="application/javascript">
	function totalPrice(m1,m2){
	    $("#trem_total_money").html(m1);
	    $("#purchase_total_money").html(m2);

	}
    function sumPayTatol(m){
        $("#pay_status").html(m);
    }
    var total=0;
    $(".pay_status").each(function(index,obj){
        total+=parseFloat($(obj).attr("val"));
    })
    sumPayTatol(total.toFixed(0));
    //新增付款项
    function addPayment(contractId,id){
        var width=$(window).width()/2;
        var height=500;
        var title="添加付款信息";
        if(id){
            title="调整付款信息";
        }else{
            id="";

        }
        $.jBox.open("iframe:${ctx}/oa/contract/oaContractPayment/form?contractId="+contractId+"&id="+id+"&type=1", title, width,height, {
            buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                    var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                    if(formdata!='undefined'){
                        loading('正在提交，请稍等...');
                        $.post("${ctx}/oa/contract/oaContractPayment/save",formdata.serialize(),function(data){
                            if(data==null||data==""||data=="null"||data==undefined){
                                loading("信息输入有误!");
                                closeLoading();
                                return ;
                            }
                            var data=JSON.parse(data);
                            var display="";
                            if(data.status=="1"){
                                display='<span  class="pay_status" val="'+data.proportion+'" style="color:#228B22;"><i class="icon-ok color-bar"></i>支付完成</span>';
                            }
                            $("#payment").append('<tr >'+
                                '<td class="tit">'+data.type+'</td>'+
                                '<td>￥'+data.money+'元</td>'+
                                ' <td class="tit">付款日期</td>'+
                                ' <td>'+data.paymentDate+'</td>'+
                                ' <td class="tit">支付比例</td>'+
                                '<td>'+data.proportion+'%'+display+'</td>'+
                                '<td><a class="btn" onclick="updatePayment(\''+data.contractId+'\',\''+data.id+'\',this)">调整</a><a class="btn" onclick="delPayment(\''+data.id+'\',this)">删除</a></td>'+
                                '</tr>');
                            loading("添加完成!");
                            closeLoading();
                            var total=0;
                            $(".pay_status").each(function(index,obj){
                                total+=parseFloat($(obj).attr("val"));
                            })
                            sumPayTatol(total.toFixed(0));
                        })
                    }
                }
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });
    }
    //修改付款项
    function updatePayment(contractId,id,obj){
        var width=$(window).width()/2;
        var height=500;
        var title="添加付款信息";
        if(id){
            title="调整付款信息";
        }else{
            id="";

        }
        $.jBox.open("iframe:${ctx}/oa/contract/oaContractPayment/form?contractId="+contractId+"&id="+id+"&type=1", title, width,height, {
            buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                    var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                    if(formdata!='undefined'){
                        loading('正在提交，请稍等...');
                        $.post("${ctx}/oa/contract/oaContractPayment/save",formdata.serialize(),function(data){
                            if(data==null||data==""||data=="null"||data==undefined){
                                loading("信息输入有误!");
                                closeLoading();
                                return ;
                            }
                            var data=JSON.parse(data);
                            var display="";
                            if(data.status=="1"){
                                display='<span  class="pay_status" val="'+data.proportion+'" style="color:#228B22;"><i class="icon-ok color-bar"></i>支付完成</span>';
                            }
                            $(obj).parent().parent().after('<tr >'+
                                '<td class="tit">'+data.type+'</td>'+
                                '<td>￥'+data.money+'元</td>'+
                                ' <td class="tit">付款日期</td>'+
                                ' <td>'+data.paymentDate+'</td>'+
                                ' <td class="tit">支付比例</td>'+
                                '<td>'+data.proportion+'%'+display+'</td>'+
                                '<td><a class="btn" onclick="updatePayment(\''+data.contractId+'\',\''+data.id+'\',this)">调整</a><a class="btn" onclick="delPayment(\''+data.id+'\',this)">删除</a></td>'+
                                '</tr>');
                            loading("添加完成!");
                            closeLoading();
                            $(obj).parent().parent().remove();
                            var total=0;
                            $(".pay_status").each(function(index,obj){
                                total+=parseFloat($(obj).attr("val"));
                            })
                            sumPayTatol(total.toFixed(0));
                        })
                    }
                }
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });
    }
    //删除付款项
    function delPayment(id,obj){
        confirmx("确定要删除吗?",function(){
            $.post("${ctx}/oa/contract/oaContractPayment/delete",{"id":id},function(data){
                if(data=="success"){
                    loading("删除成功完成!");
                    closeLoading();
                    $(obj).parent().parent().remove();
                    var total=0;
                    $(".pay_status").each(function(index,obj){
                        total+=parseFloat($(obj).attr("val"));
                    })
                    sumPayTatol(total.toFixed(0));
                }
            })
        })
    }
<!--合同管理-->
	function showIframe(obj){
	    if($("#trem").is(":hidden")){
            $("#trem").slideDown();
            $(obj).html('<i class="icon-chevron-up"></i>&nbsp;收起');
		}else{
            $("#trem").slideUp();
            $(obj).html('<i class="icon-chevron-down"></i>&nbsp;展开');

		}

	}

	//添加合同项
    function addTrem(contractId,id){
        var width=$(window).width()/2;
        var height=500;
        var title="添加合同项";
        if(id){
            title="调整合同项";
		}else{
            id="";

		}
        $.jBox.open("iframe:${ctx}/oa/contract/oaContractTerms/form?contractId="+contractId+"&id="+id, title, width,height, {
            buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                    var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                    if(formdata!='undefined'){
                        loading('正在提交，请稍等...');
                        $.post("${ctx}/oa/contract/oaContractTerms/save",formdata.serialize(),function(data){
                            if(data==null||data==""||data=="null"||data==undefined){
                                loading("信息输入有误!");
                                closeLoading();
                                return ;
                            }
                            var data=JSON.parse(data);
                            document.getElementById('trem').contentWindow.location.reload(true);
                            loading("添加完成!");
                            closeLoading();
                        })
                    }
                }
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });
    }
    //删除项
    function delTrem(id,obj){
        confirmx("确定要删除吗?",function(){
            $.post("${ctx}/oa/contract/oaContractTerms/delete",{"id":id},function(data){
                if(data=="success"){
                    loading("删除成功完成!");
                    closeLoading();
                    document.getElementById('trem').contentWindow.location.reload(true);

                }
            })
        })
    }
    //更新项
	function updateTrem(contractId,id,obj){
        var width=$(window).width()/2;
        var height=500;
        var title="添加合同项";
        if(id){
            title="调整合同项";
        }else{
            id="";
        }
        $.jBox.open("iframe:${ctx}/oa/contract/oaContractTerms/form?contractId="+contractId+"&id="+id, title, width,height, {
            buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                    var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                    if(formdata!='undefined'){
                        loading('正在提交，请稍等...');
                        $.post("${ctx}/oa/contract/oaContractTerms/save",formdata.serialize(),function(data){
                            if(data==null||data==""||data=="null"||data==undefined){
                                loading("信息输入有误!");
                                closeLoading();
                                return ;
                            }
                            var data=JSON.parse(data);
                            document.getElementById('trem').contentWindow.location.reload(true);
                            loading("修改完成!");
                            closeLoading();
                        })
                    }
                }
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });
	}
<!--开票管理-->
    //添加修改开票
    function addnvoice(contractId,id){
        var width=$(window).width()/2;
        var height=500;
        var title="添加开票信息";
        if(id){
            title="调整开票信息";
        }else{
            id="";
        }
        $.jBox.open("iframe:${ctx}/oa/contract/oaContractInvoice/form?contractId="+contractId+"&types=1&id="+id, title, width,height, {
            buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                    var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                    if(formdata!='undefined'){
                        loading('正在提交，请稍等...');
                        $.post("${ctx}/oa/contract/oaContractInvoice/save",formdata.serialize(),function(data){
                            if(data!="success"){
                                loading("信息输入有误!");
                                closeLoading();
                                return ;
                            }
                            document.getElementById('invoice').contentWindow.location.reload(true);
                            loading("保存成功!");
                            closeLoading();
                        })
                    }
                }
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });
    }
</script>
</body>
</html>