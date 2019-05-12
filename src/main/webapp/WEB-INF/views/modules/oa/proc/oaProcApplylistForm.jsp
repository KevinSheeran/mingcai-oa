<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存采购申请单成功管理</title>
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
        /*function getProName(obj){
            $.get("${ctx}/oa/proc/oaProcApplylist/getProName",{"proNumber":$(obj).val()},function(data){
                var data=JSON.parse(data);
                alert(data.name);
                $("input[name='proName']").val(data.name);
            })
        }*/
        /**
         *
         * @param applyId
         * @param id
         */

	</script>
</head>
<body>
    <form:form id="inputForm" modelAttribute="oaProcApplylist" action="${ctx}/oa/proc/oaProcApplylist/save" method="post" class="form-horizontal">
    <legend><div style="padding: 0px 10px; text-align: left ">
        <a href="${ctx}/oa/proc/oaProcApplylist/">采购列表</a> >>采购清单信息</div>
    </legend>
    <table class="table-form">
        <tr>
            <td class="tit">项目：</td>
            <td>
            <form:select path="proId" class="input-xlarge" onchange="getProName(this)">
                <form:options items="${proStartlist}" itemLabel="pro.name" itemValue="id"  htmlEscape="false"/>
            </form:select>
            </td>
           <td class="tit"></td>
            <td>
                <form:input path=""  value="" readonly="true"  htmlEscape="false" maxlength="100" class="input-xlarge "></form:input>
            </td>
        </tr>
        <tr>
            <td class="tit">请购人：</td>
            <td>
                <input value="${fns:getUser().name}" type="text" readonly="readonly" class="input-xlarge"/>
            </td>
            <td class="tit">请购日期</td>
            <shiro:hasPermission name="oa:proc:oaProcApplylist:edit">
            <td>
            <input name="procTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${oaProcApplylist.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            </td>
            </shiro:hasPermission>
        </tr>
        <tr>
            <td class="tit">审批人</td>
            <td>
                <input value="${fns:getUser().name}" type="text" readonly="readonly" class="input-xlarge"/>
            </td>
            <td class="tit">审批日期</td>
            <shiro:hasPermission name="oa:proc:oaProcApplylist:edit">
                <td>
                    <input name="procTime" type="text" maxlength="20" readonly="readonly" class="input-medium Wdate "
                           value="<fmt:formatDate value="${oaProcApplylist.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
                </td>
            </shiro:hasPermission>
        </tr>
        <tr>
            <td class="tit">备注</td>
            <td><form:textarea path=""></form:textarea></td>
            <td class="tit"></td>
            <td >
            </td>
        </tr>
        <shiro:hasPermission name="oa:proc:oaProcApplylist:edit">
            <tr>
                <td colspan="4">
                    <div class="form-actions">
                        <input id="btnSubmit" class="btn btn-primary pull-left" type="submit" value="保存采购清单信息"/>
                        <input id="btnSubmit" class="btn btn-primary " style="margin-left:420px;"  type="submit" value="提交审批"/>&nbsp;
                    </div>
                </td>
            </tr>
        </shiro:hasPermission>
        <%--子项管理：判断条件：修改时如果该项目存在子项就显示子项列--%>

        <c:if test="${oaProcApplylist.proId!=null}">
            <tr>
                <td colspan="4" >
                    <h4 style="padding: 10px 0; padding-left:10px; color: #666;">清单管理&nbsp;&nbsp;
                        <a class="btn " style="" onclick="addProcInventory('${oaProcApplylist.proId}')"><i class="icon-plus"></i>&nbsp;添加采购项</a>
                    <a class="btn" style="">
                        <i class="icon-plus"></i>&nbsp;添加采购项
                    </a></h4>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="padding-left:15px;" >
                    <iframe name="list" src="${ctx}/oa/proc/oaProcInventory?applyId=${oaProcApplylist.id}"
                            style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="250">
                    </iframe>
                </td>
            </tr>
            <c:forEach items="${page}" var="item">
            <tr>
                <td colspan="4" >
                    <h4 style="padding: 10px 0; padding-left:10px; color: #666;">${item.name}</h4>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="padding-left:15px;" >
                    <iframe name="list" src="${ctx}/oa/proc/oaProcInventory?applyId=${oaProcApplylist.id}&proItemId=${item.id}"
                            style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="250">
                    </iframe>
                </td>
            </tr>
        </c:forEach>

        </c:if>
    </table>
    </form:form>
<script type="application/javascript">
    function addProcInventory(applyId,id){
        var width=$(window).width()/2;
        var height=500;
        var title="添加采购清单信息";
        if(id){
        title="调整采购清单信息";
        }else{
        id="";
        }
        top.$.jBox.open("iframe:${ctx}/oa/proc/oaProcInventory/form?id="+id+"&applyId="+applyId, title, width,height, {
        buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
        if (v=="ok"){

        }

        }, loaded:function(h){
        $(".jbox-content", document).css("overflow-y","hidden");
        }
        });
        }
    </script>
</body>

</html>