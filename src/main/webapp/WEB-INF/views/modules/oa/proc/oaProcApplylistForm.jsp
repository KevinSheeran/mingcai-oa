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
            $("#btnImport").click(function(){
                $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
                    bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
            });
		});
        function getProName(obj){
            $.get('${ctx}/oa/proc/oaProcApplylist/getInfo',{"proId":$(obj).val()},function(data){
                var data=JSON.parse(data);
                $("input[name='oaEosPro.paNumber']").val(data.paNumber);
            })
           }


	</script>
</head>
<body>
    <%--导入清单Start--%>
    <div id="importBox" class="hide">
        <form id="importForm" action="${ctx}/oa/proc/oaProcInventory/import" method="post" enctype="multipart/form-data"
              class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');">
            <br/>
            <input type="hidden" value="${oaProcApplylist.id}" name="applyId">
            <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
            <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
            <a href="${ctx}/oa/proc/oaProcInventory/import/template">下载模板</a>
        </form>
    </div>
    <%--导入清单  End--%>
    <%-- 清单列表 Start--%>
    <form:form id="inputForm" modelAttribute="oaProcApplylist" action="${ctx}/oa/proc/oaProcApplylist/save" method="post" class="form-horizontal">
        <form:hidden path="id"/>
    <legend><div style="padding: 0px 10px; text-align: left ">
        <a href="${ctx}/oa/proc/oaProcApplylist/">采购申请列表</a> >>采购清单信息</div>
    </legend>
    <table class="table-form">
        <tr>
            <td class="tit">项目名称：</td>
            <td>
            <form:select path="proId" class="input-xlarge" onchange="getProName(this)">
                <form:options items="${eosProlist}" itemLabel="name" itemValue="id"  htmlEscape="false"/>
            </form:select>
            </td>
           <td class="tit">项目编号：</td>
            <td>
                <form:input path="oaEosPro.paNumber" value="${eosProlist.get(0).paNumber}"  readonly="true"  htmlEscape="false" maxlength="100" class="input-xlarge "></form:input>
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
            <td><form:textarea path="remarks"></form:textarea></td>
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
        <c:if test="${oaProcApplylist.id!=null}">
            <tr>
                <td colspan="4" >
                    <h4 style="padding: 10px 0; padding-left:10px; color: #666;">清单管理&nbsp;&nbsp;
                        <a class="btn " onclick="addProcInventory('${oaProcApplylist.id}')"><i class="icon-plus"></i>&nbsp;添加采购项</a>
                        <input id="btnImport" class="btn btn-primary" type="button" value="导入清单"/>
                    <%--<a class="btn"  id="btnImport">
                        <i class="icon-plus"></i>&nbsp;
                    </a>--%></h4>
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
                    <h4 style="padding: 10px 0; padding-left:10px; color: #666;">${item.name}
                    <a class="btn " onclick="addInventoryItems('${oaProcApplylist.id}','${oaProcApplylist.proId}','${item.id}')">
                        <i class="icon-plus"></i>&nbsp;添加清单项</a>
                    </h4>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="padding-left:15px;" >
                    <iframe name="inventorylist" id="inventorylist" src="${ctx}/oa/proc/oaProcInventory?applyId=${oaProcApplylist.id}&proItemId=${item.id}"
                            style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="250">
                    </iframe>
                </td>
            </tr>
        </c:forEach>

        </c:if>
    </table>
    </form:form>
<script type="application/javascript">
    /**
     *  添加清单项（多选）
     * */
    function addInventoryItems(id,proId,proItemId){
            var width=$(window).width()/2;
            var height=500;
            var title="添加清单项";
            top.$.jBox.open("iframe:${ctx}/oa/proc/oaProcInventory/selectlist?applyId="+id+"&proId="+proId+'&proItemId='+proItemId, title, width,height, {
                buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
                        var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                        if(formdata!='undefined'){
                            loading('正在生成清单，请稍等...');
                            $.post("${ctx}/oa/proc/oaProcInventory/saveInventory",formdata.serialize(),function(data){
                                if(data==null||data==""||data=="null"||data==undefined){
                                    loading("信息输入有误!");
                                    closeLoading();
                                    return ;
                                }
                                if(data=='success'){
                                    loading("生成完成!");
                                    /*closeLoading();
                                    document.getElementById('inventorylist').contentWindow.location.reload(true);*/
                                    self.location.reload();
                                }
                            })
                        }
                    }
                }, loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });
    }
    /**
     * 添加采购项
     * @param applyId
     * @param id
     */
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
            var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
            if(formdata!='undefined'){
                loading('正在提交，请稍等...');
                $.post("${ctx}/oa/proc/oaProcInventory/save",formdata.serialize(),function(data){
                    if(data=="success"){
                        loading("提交完成!");
                        self.location.reload();
                    }else{
                        loading(data);
                    }
                    closeLoading();
                });
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