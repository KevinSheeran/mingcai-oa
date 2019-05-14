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


	</script>
</head>
<body>
    <div id="importBox" class="hide">
        <form id="importForm" action="${ctx}/oa/proc/oaProcApplylist/import" method="post" enctype="multipart/form-data"
              class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');">
            <br/>
            <input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
            <input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
           <%-- <a href="${ctx}/sys/user/import/template">下载模板</a>--%>
        </form>
    </div>
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
                        <a class="btn " onclick="addProcInventory('${oaProcApplylist.proId}')"><i class="icon-plus"></i>&nbsp;添加采购项</a>
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
                    <a class="btn " onclick="addList('${oaProcApplylist.id}')"><i class="icon-plus"></i>&nbsp;添加清单</a>
                    </h4>
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
    /*
    * 添加清单
    * */
    function addList(applyId,id){
        var width=$(window).width()/2;
        var height=500;
        var title="添加清单信息";
        if(id){
            title="调整清单信息";
        }else{
            id="";
        }
        top.$.jBox.open("iframe:${ctx}/oa/proc/oaProcApplylist?id="+id, title, width,height, {
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
                        })
                    }
                }
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });
    }


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