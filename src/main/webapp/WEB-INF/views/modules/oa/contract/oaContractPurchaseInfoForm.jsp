<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>采购合同管理</title>
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

        function getSupplierInfo(obj){
            $.get("${ctx}/oa/contract/oaContractSupplier/getInfo",{"id":$(obj).val()},function(data){
                var data=JSON.parse(data);
                $("input[name='bankAccount']").val(data.bankAccount);
                $("input[name='dutyParagraph']").val(data.dutyParagraph);
                $("input[name='contactNumber']").val(data.contactNumber);
                $("input[name='openingBank']").val(data.openingBank);
            })
        }
	</script>
</head>
<body>

	<form:form id="inputForm" modelAttribute="oaContractPurchaseInfo" action="${ctx}/oa/contract/oaContractPurchaseInfo/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<form:hidden path="finProductId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
	<fieldset>
			<legend><div style="padding: 0px 10px; text-align: left "><a href="${ctx}/oa/contract/oaContractInfo/mainForm?finProductId=${oaFinanceProduct.id}">${oaFinanceProduct.name}</a>>>采购合同</div> </legend>
		<table class="table-form">
			<tr>
				<td class="tit">合同别名</td>
				<td><form:input path="name" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
				<td class="tit">采购单位：</td><td>
				<form:select path="supplierId" class="input-xlarge required" onchange="getSupplierInfo(this)">
					<form:option value="" label=""/>
					<form:options items="${fns:getSupplr()}" itemLabel="name" itemValue="id" htmlEscape="false"/>
				</form:select>
			</td>
			</tr>
			<tr>
				<td class="tit">采购人员：</td><td>
				<sys:treeselect id="contacts"  name="contacts" value="${oaContractPurchaseInfo.contacts}" labelName="${oaContractPurchaseInfo.userName}" labelValue="${oaContractPurchaseInfo.userName}"
								title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</td>
				<td class="tit">开户行：</td><td><form:input path="openingBank" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
			</tr>
			<tr>
				<td class="tit">验收时间节点：</td><td><input name="timeNode" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
													   value="<fmt:formatDate value="${oaContractPurchaseInfo.timeNode}" pattern="yyyy-MM-dd HH:mm:ss"/>"
													   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>


				<td class="tit">银行账号：</td><td><form:input path="bankAccount" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
			</tr>
			<tr>
				<td class="tit">付款完成状态：</td><td><span id="pay_status">0</span>%</td>
				<td class="tit">纳税识别号：</td><td><form:input path="dutyParagraph" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
			</tr>
			<tr>
				<td  colspan="2"></td>
				<td class="tit">联系电话：</td><td colspan="3"><form:input path="contactNumber" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
			</tr>
			<tr>

			</tr>
			<tr><td colspan="4">
				<div class="form-actions">
					<shiro:hasPermission name="oa:contract:oaContractInfo:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保存采购合同信息"/>&nbsp;</shiro:hasPermission>
				</div>
			</td></tr>
			<tr><td colspan="4">
				<h4 style="padding: 10px 0;padding-left:10px; color: #666;">
					付款条约
					<shiro:hasPermission name="oa:contract:oaContractPayment:edit">
					<c:if test="${oaContractPurchaseInfo.id!=null&&oaContractPurchaseInfo.id!=''}">&nbsp;&nbsp;<a class="btn " style="" onclick="addPayment('${oaContractPurchaseInfo.id}')"><i class="icon-plus"></i>&nbsp;添加付款信息</a></c:if>
					</shiro:hasPermission>
				</h4>
			</td></tr>
			<tr>
				<td colspan="4" style="padding-left:15px;">
					<c:if test="${oaContractPurchaseInfo.id!=null&&oaContractPurchaseInfo.id!=''}">
						<table class="table-form" id="payment">
							<c:forEach items="${OaContractPaymentList}" var="item" varStatus="index">
								<tr >
									<td class="tit">${item.batchId}</td>
									<td>￥${item.money}元</td>
									<td class="tit">付款日期</td>
									<td><fmt:formatDate value="${item.paymentDate}" pattern="yyyy年MM月dd日"/></td>
									<td><span>${item.proportion}%<c:if test="${item.status=='1'}"> <span class="pay_status" val="${item.proportion}"  style="color:#228B22"><i class="icon-ok color-bar"></i>支付完成</span></c:if></td>
									<shiro:hasPermission name="oa:contract:oaContractPayment:edit">
									<td><a class="btn" onclick="updatePayment('${item.contractId}','${item.id}',this)">调整</a><a class="btn" onclick="delPayment('${item.id}',this)">删除</a></td>
									</shiro:hasPermission>
								</tr>
							</c:forEach>
						</table>
					</c:if>
					<c:if test="${oaContractPurchaseInfo.id==null||oaContractPurchaseInfo.id==''}">
						请保存基本信息后操作此项
					</c:if>
				</td>
			</tr>
			<tr><td colspan="4">
				<h4 style="padding: 10px 0; padding-left:10px; color: #666;">
					开票信息<shiro:hasPermission name="oa:contract:oaContractInvoice:edit">&nbsp;&nbsp;<c:if test="${oaContractPurchaseInfo.id!=null&&oaContractPurchaseInfo.id!=''}"><a class="btn " style="" onclick="addnvoice('${oaContractPurchaseInfo.id}')"><i class="icon-plus"></i>&nbsp;添加开票</a></c:if></shiro:hasPermission>
				</h4>
			</tr>
			<tr>
				<td colspan="4" style="padding-left:15px;">
					<c:if test="${oaContractPurchaseInfo.id!=null&&oaContractPurchaseInfo.id!=''}">
						<iframe src="${ctx}/oa/contract/oaContractInvoice?contractId=${oaContractPurchaseInfo.id}" name="invoice" id="invoice" 
						style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="250">

						</iframe>
					</c:if>
					<c:if test="${oaContractPurchaseInfo.id==null||oaContractPurchaseInfo.id==''}">
						请保存基本信息后操作此项
					</c:if>
				</td>
			</tr>
			<tr><td colspan="4">
				<h4 style="padding: 10px 0; padding-left:10px; color: #666;">
					采购清单
					<shiro:hasPermission name="oa:contract:oaContractTerms:pay">
					<c:if test="${oaContractPurchaseInfo.id!=null&&oaContractPurchaseInfo.id!=''}">
					&nbsp;&nbsp;
					<a class="btn " style="" onclick="addTrem('${oaContractPurchaseInfo.id}')">
						<i class="icon-plus"></i>&nbsp;添加采购项</a>
					</c:if></shiro:hasPermission>
					&nbsp;&nbsp;<span id="totalMoney"></span>

				</h4>
			</tr>
			<tr>
				<td colspan="4" style="padding-left:15px;">
					<c:if test="${oaContractPurchaseInfo.id!=null&&oaContractPurchaseInfo.id!=''}">
						<iframe src="${ctx}/oa/contract/oaContractPurchaseTerms/getTremlist?purchaseId=${oaContractPurchaseInfo.id}&supplierId=${oaContractPurchaseInfo.supplierId}" name="trem" id="trem" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="250">

						</iframe>
					</c:if>
					<c:if test="${oaContractPurchaseInfo.id==null||oaContractPurchaseInfo.id==''}">
						请保存基本信息后操作此项
					</c:if>
				</td>
			</tr>


		</table>
	</fieldset>
	</form:form>
	<!--清单项-->
	<script type="application/javascript">
		function setTotalMoney(m){
            $("#totalMoney").html("合计总金额：￥"+m+"元");
		}
		function addTrem(id){
            var width=$(window).width()/2;
            var height=500;
            var title="添加采购项";
            $.jBox.open("iframe:${ctx}/oa/contract/oaContractPurchaseTerms?purchaseId="+id, title, width,height, {
                buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
                        var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                        if(formdata!='undefined'){
                            loading('正在生成采购清单，请稍等...');
                            $.post("${ctx}/oa/contract/oaContractPurchaseTerms/save",formdata.serialize(),function(data){
                                if(data==null||data==""||data=="null"||data==undefined){
                                    loading("信息输入有误!");
                                    closeLoading();
                                    return ;
                                }
                                loading("生成完成!");
                                closeLoading();
                                document.getElementById('trem').contentWindow.location.reload(true);
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
                $.post("${ctx}/oa/contract/oaContractPurchaseTerms/delete",{"id":id},function(data){
                    if(data=="success"){
                        loading("删除成功完成!");
                        closeLoading();
						$(obj).parent().parent().remove();
                        var total=0;
                        document.getElementById('trem').contentWindow.$(".totals").each(function(index,obj){
                            total+=parseFloat($(obj).html());
                        })
                        setTotalMoney(total.toFixed(2));
                    }
                })
            })
        }
        function saveTrem(id,obj){
            var number= document.getElementById('trem').contentWindow.$("input[name='number_"+id+"']").val();
            var unitPrice= document.getElementById('trem').contentWindow.$("input[name='money_"+id+"']").val();
            var productId=document.getElementById('trem').contentWindow.$("select[name='product_"+id+"']").val();
            var numReg=/^[0-9]+([.]{1}[0-9]+){0,1}$/;
            if(!numReg.test(number)){
                loading("个数填写错误!");
                setTimeout(function(){ closeLoading();},1000);
                return;
			}
			var moneyReg=/^\d{0,8}\.{0,1}(\d{1,2})?$/;
            if(!moneyReg.test($.trim(unitPrice))||$.trim(unitPrice)==""){
                loading("单价填写错误!");
                setTimeout(function(){ closeLoading();},1000);
                return;
            }
            if($.trim(productId)==""){
                loading("请选择供应商产品!");
                setTimeout(function(){ closeLoading();},1000);
                return;
			}
            if(parseInt($(obj).attr("maxnumber"))<parseInt(number)){
                loading("数量超过剩余数量，保存失败!");
                setTimeout(function(){ closeLoading();},1000);
                return;
			}
            loading("保存中!");
            $.post("${ctx}/oa/contract/oaContractPurchaseTerms/edit",{"id":id,"number":number,"unitPrice":unitPrice,"productId":productId},function(data){
                if(data!=null&&data!=""&&data!="null"&&data!=undefined){
                    loading("保存完成!");
                    closeLoading();
                    document.getElementById('trem').contentWindow.$("#total_"+id).html(parseFloat(number)*parseFloat(unitPrice).toFixed(2));
                    document.getElementById('trem').contentWindow.$("#divnumber_"+id).html((parseFloat($(obj).attr("maxnumber"))-number).toFixed(2));
					$(obj).attr("thisnumber",number);
					var total=0;
                    document.getElementById('trem').contentWindow.$(".totals").each(function(index,obj){
                        total+=parseFloat($(obj).html());
					})
                    setTotalMoney(total.toFixed(2));
                }
            })

		}
<!--付款条约-->
     var total=0;
     $(".pay_status").each(function(index,obj){
         total+=parseFloat($(obj).attr("val"));
     })
     sumPayTatol(total.toFixed(0));
        function sumPayTatol(m){
            $("#pay_status").html(m);
        }
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
         $.jBox.open("iframe:${ctx}/oa/contract/oaContractPayment/form?contractId="+contractId+"&id="+id+"&type=2", title, width,height, {
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
                                 '<td>'+data.proportion+'%</td>'+
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
         $.jBox.open("iframe:${ctx}/oa/contract/oaContractPayment/form?contractId="+contractId+"&id="+id+"&type=2", title, width,height, {
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
                                 '<td>'+data.proportion+'%</td>'+
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
     //删除付款
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
        $.jBox.open("iframe:${ctx}/oa/contract/oaContractInvoice/form?contractId="+contractId+"&types=2&id="+id, title, width,height, {
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