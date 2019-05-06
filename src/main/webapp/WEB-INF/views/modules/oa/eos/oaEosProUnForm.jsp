<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>非销售立项管理</title>
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
        function Audit(id){
            // 正常打开
            top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosPro/audit?id="+id, "立项审核", $(window).width()*4/5,$(window).height()*4/5, {
                buttons:{"提交":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
                        var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                        if(formdata!='undefined'){
                            loading('正在提交，请稍等...');
                            $.post("${ctx}/oa/eos/oaEosProUn/auditsave",formdata.serialize(),function(data){
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
        function addProItem(eosid,itemid){
            var title="添加子项目";
            if(itemid){
                title="编辑子项目";
            }else{
                itemid="";
            }
            // 正常打开
            top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosProStartItem/form?eosid="+eosid+"&id="+itemid, title, $(window).width()*4/5,$(window).height()*4/5, {
                buttons:{"关闭":true}, submit:function(v, h, f){
                },
                closed:function(){
                    location.reload();
                },
                loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });

        }
        function Cancel(id){
            // 正常打开
            confirmx("确定要取消审核吗？","${ctx}/oa/eos/oaEosProUn/cancel?id="+id,"");

        }
        function editPro(){
            $("input[name='savecode']").val("yes");
            $("#inputForm").submit();
        }
	</script>
</head>
<body>
<fieldset>
	<legend><div style="padding: 0px 10px; text-align: left "><a href="${ctx}/oa/eos/oaEosProUn">项目首页</a>>>非销售立项</div> </legend>
	<form:form id="inputForm" modelAttribute="oaEosProUn"  action="${ctx}/oa/eos/oaEosProUn/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<input type="hidden" name="savecode" value="no">
		<c:if test="${oaEosProUn.status!=0}">
			<table class="table table-striped table-bordered table-condensed">
				<thead>
				<th colspan="4" align="left"><h5>流程状态</h5></th>
				</thead>
				<c:forEach items="${flowmap}" var="flow" varStatus="index">
					<tr>
						<td class="tit" colspan="3"><c:if test="${index.index==1}">总经理审批</c:if></td>
					</tr>
					<c:forEach items="${flow.value}" var="item">
						<tr>
							<td class="tit" width="200"><img  width="40px;" src="${item.user.wxUsers.avatar}/100" alt="">&nbsp;&nbsp;${item.user.name}</td>
							<td>${item.content}</td>
							<td><c:if test="${item.status==1}">审核通过</c:if><c:if test="${item.status==-1}">驳回</c:if><c:if test="${item.status==0}">未审核</c:if></td>
						</tr>
					</c:forEach>
				</c:forEach>
			</table>
		</c:if>
		<table class="table table-striped table-bordered table-condensed">
			<thead>
			<th colspan="4" align="left"><h5>基本信息<c:if test="${oaEosProUn.id!=null}"><div class="pull-right"><span>项目状态：${fns:getDictLabel(oaEosProUn.status,'pro_start_status' , '')}</span></div></c:if></h5></th>

			</thead>
			<tr>
				<td class="tit">项目名称：</td><td colspan="3"><form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required"/><span class="help-inline"><font color="red">*</font> </span></td>
			</tr>
			<tr>
				<td class="tit" >立项编号：</td><td colspan="3"><c:if test="${!proedit&&oaEosProUn.id!=null}"><form:input path="proNumber" readonly="true" htmlEscape="false" maxlength="200" class="input-xlarge required"/></c:if> <c:if test="${proedit&&oaEosProUn.id!=null&&oaEosProUn.proNumber!=null&&oaEosProUn.proNumber!=''}"><form:input path="proNumber"  htmlEscape="false" maxlength="200" class="input-xlarge required"/></c:if> 立项完成自动生成</td>
			</tr>
			<tr>
				<td class="tit">项目负责人：</td><td><sys:treeselect id="personLiableUser"  name="personLiableUser.id" value="${oaEosProUn.personLiableUser.id}" labelName="oaEosProUn.personLiableUser.name" labelValue="${oaEosProUn.personLiableUser.name}"
															  title="负责人" url="/sys/office/treeData?type=3" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/><span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit">对接单位：</td><td><form:input path="customerName" htmlEscape="false" maxlength="200" class="input-xlarge "/></td>
			</tr>
			<tr>
				<td class="tit">对接方负责人：</td><td><form:input path="customerUser" htmlEscape="false" maxlength="200" class="input-xlarge "/></td>
				<td class="tit">对接方负责人联系方式：</td><td><form:input path="customerContactInformation" htmlEscape="false" maxlength="200" class="input-xlarge "/></td>
			</tr>
		</table>
		<table class="table table-striped table-bordered table-condensed">
			<thead>
			<th colspan="4" align="left"><h5>详细信息</h5></th>
			</thead>
			<tr>
				<td class="tit">项目类型：</td><td><form:select path="proType" class="input-xlarge required">
				<form:options items="${fns:getDictList('pro_un_type')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select></td>
				<td class="tit">项目目标：</td><td><form:input path="proLocation" htmlEscape="false" maxlength="64" class="input-xlarge required"/></td>
			</tr>
			<tr>
				<td class="tit">费用部门：</td><td>
                <sys:treeselect id="departmentId" checked="false" name="departmentId" value="${oaEosProUn.departmentId}" labelName="oaEosProUn.company.name" labelValue="${oaEosProUn.company.name}"
                                title="用户" url="/sys/office/treeData?type=2" isAll="false"   notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
			    <span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit">费用预算(元)：</td><td><form:input path="estimation" htmlEscape="false" maxlength="64" class="input-xlarge required"/><span class="help-inline"><font color="red">*</font> </span>
			</tr>
			<tr>
				<td class="tit">周期：</td><td><form:input path="workDate" htmlEscape="false" maxlength="64" class="input-xlarge"/></td>
				<td class="tit">内部工作量估算（人天）：</td><td><form:input path="workload" htmlEscape="false" maxlength="64" class="input-xlarge "/></td>
			</tr>
			<tr>
			<td class="tit">支持人员：</td><td><sys:treeselect id="userIds" checked="true" name="userIds" value="${oaEosProStart.userIds}" labelName="oaEosProUn.users_names" labelValue="${oaEosProUn.users_names}"
														  title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass=""/>
			<span class="help-inline"><font color="red">*</font> </span></td>
			<td class="tit">项目进展：</td><td>
					<form:radiobuttons path="progress" items="${fns:getDictList('pro_start_progress')}" itemValue="value" itemLabel="label" htmlEscape="false"></form:radiobuttons>
			</td>
			</tr>
			<tr>
				<td class="tit">项目主要内容：</td><td colspan="3"><form:textarea path="proContent" htmlEscape="false" rows="4" cssStyle="width: 70%;height: 300px;" maxlength="2000" class="input-xxlarge "/></td>
			</tr>

			<tr >
				<td colspan="4">
                    <div class="form-actions">
                        <c:if test="${oaEosProUn.proNumber!=null&&proedit&&oaEosProUn.proNumber!=''}">
                            <input id="btnSubmit" class="btn btn-primary" type="button" onclick="editPro()" value="调整方案"/>
                        </c:if>
						<c:if test="${oaEosProUn.status==0||oaEosProUn.status==-1}">
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
							<c:if test="${oaEosProUn.id!=null&&oaEosProUn.id!=''&&oaEosProUn.status==0}">
								<input id="btnAudit" class="btn btn-success" type="button" onclick="Audit('${oaEosProUn.id}')" value="提交审核"/>
							</c:if>
							<c:if test="${oaEosProUn.id!=null&&oaEosProUn.id!=''&&oaEosProUn.status==-1}">
								<input id="btnAudit" class="btn btn-success" type="button" onclick="Audit('${oaEosProUn.id}')" value="重新提交审核"/>
							</c:if>
						</c:if>
						<c:if test="${oaEosProUn.status==1}">
							<input id="btnAudit" class="btn btn-primary" type="button" onclick="Cancel('${oaEosProUn.id}')" value="撤销审核"/>
						</c:if>
                        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/oa/eos/oaEosProUn'"/>
                    </div>
				</td>
			</tr>
		</table>
		</div>
		<c:if test="${oaEosProUn.id!=null}">
			<h4 style="padding: 10px 0">立项子项目&nbsp;&nbsp;<c:if test="${oaEosProUn.status==0||proedit}"><button class="btn " type="button" onclick="addProItem('${oaEosProUn.id}')"><i class="icon-plus"></i>&nbsp;&nbsp;添加子项目</button> </c:if></h4>
			<table id="contentTable" class="table table-striped table-bordered table-condensed">
				<thead>
				<tr>
					<th>名称</th>
					<th>编号</th>
					<th>负责人</th>
					<th>预算金额</th>
					<th>投入金额</th>
					<th>预估完成时间</th>
					<th>实际完成时间</th>
					<shiro:hasPermission name="oa:eos:oaEosProUn:edit"><th>操作</th></shiro:hasPermission>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${page}" var="oaEosProItem">
					<tr>
						<td><a href="${ctx}/oa/eos/oaEosProItem/form?id=${oaEosProItem.id}">
								${oaEosProItem.name}
						</a></td>
						<td>
								${oaEosProItem.code}
						</td>
						<td>
								${oaEosProItem.personLiableUser.name}
						</td>
						<td>
							￥${oaEosProItem.estimatedAmount}元
						</td>
						<td>
							￥${oaEosProItem.inputEstimation}元
						</td>
						<td>
							<fmt:formatDate value="${oaEosProItem.workloadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${oaEosProItem.actualTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<c:if test="${oaEosProUn.status==1||proedit}">
								<a href="javascript:addProItem('${oaEosProUn.id}','${oaEosProItem.id}')">修改</a>
								<a href="${ctx}/oa/eos/oaEosProUnItem/delete?id=${oaEosProItem.id}" onclick="return confirmx('确认要删除该销售项目子项吗？', this.href)">删除</a>
							</c:if>
						</td>

					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
	</form:form>
</fieldset>
</body>
</html>