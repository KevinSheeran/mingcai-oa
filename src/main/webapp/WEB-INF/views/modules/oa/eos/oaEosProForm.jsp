<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售项目立项管理</title>
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
            top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosPro/audit?id="+id, "预立项审核", $(window).width()*4/5,$(window).height()*4/5, {
                buttons:{"提交":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
                        var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                        if(formdata!='undefined'){
                            loading('正在提交，请稍等...');
                            $.post("${ctx}/oa/eos/oaEosPro/auditsave",formdata.serialize(),function(data){
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
            top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosProItem/form?eosid="+eosid+"&id="+itemid, title, $(window).width()*4/5,$(window).height()*4/5, {
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
            confirmx("确定要取消审核吗？","${ctx}/oa/eos/oaEosPro/cancel?id="+id,"");

        }
        function saveCode(){
		    $("input[name='savecode']").val("yes");
		    $("#inputForm").submit();
		}
		//指定项目经理
		var RectAudit=function(id){
            // 正常打开
            top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosPro/touser?id="+id, "指派项目", $(window).width()*4/5,$(window).height()*4/5, {
                buttons:{"提交":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
                        var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                        if(formdata!='undefined'){
                            loading('正在提交，请稍等...');
                            $.post("${ctx}/oa/eos/oaEosPro/tousersave",formdata.serialize(),function(data){
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
	</script>
	<style>
		.table-striped tbody>tr:nth-child(odd)>td, .table-striped tbody>tr:nth-child(odd)>th{
			background-color: transparent;
		}
	</style>
</head>
<body>
	<form:form id="inputForm" modelAttribute="oaEosPro" action="${ctx}/oa/eos/oaEosPro/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="savecode" value="no">
		<sys:message content="${message}"/>
	<fieldset>
		<legend><div style="padding: 0px 10px; text-align: left;float:left;"><a href="${ctx}/oa/eos/oaEosPro/index">项目立项首页</a>>>销售项目立项信息</div> <c:if test="${oaEosPro.id!=null}"><div class="pull-right">创建时间：<fmt:formatDate value="${oaEosPro.createDate}" pattern="yyyy-MM-dd"/></div></c:if> </legend>
			<c:if test="${oaEosPro.status>1||oaEosPro.status==-2}">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
						<th colspan="4" align="left"><h5>流程状态</h5></th>
					</thead>
					<c:forEach items="${flowmap}" var="flow" varStatus="index">
						<tr>
							<td class="tit" colspan="3"><c:if test="${index.index==0}">总经理审批</c:if></td>
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
					<th colspan="4" align="left"><h5>基本信息<c:if test="${oaEosPro.id!=null}"><div class="pull-right"><span>项目状态：${fns:getDictLabel(oaEosPro.status,'oa_eos_pro_status' , '')}</span></div></c:if></h5></th>

				</thead>
				<tr>
					<td class="tit">项目名称：</td><td colspan="3"><form:input path="name" htmlEscape="false" maxlength="200" class="input-xlarge required"/><span class="help-inline"><font color="red">*</font> </span></td>
				</tr>
				<tr>
					<td class="tit">预立项编号：</td><td><c:if test="${!proedit&&oaEosPro.id!=null}"><form:input path="paNumber" readonly="true" htmlEscape="false" maxlength="200" class="input-xlarge required"/></c:if> <c:if test="${proedit&&oaEosPro.id!=null}"><form:input path="paNumber"  htmlEscape="false" maxlength="200" class="input-xlarge required"/></c:if>  预立项审核通过之后会自动生成(项目管理人员权限可进行调整)</td>
					<td class="tit">立项编号：</td><td><c:if test="${!proedit&&oaEosPro.id!=null}"><form:input path="proNumber" readonly="true" htmlEscape="false" maxlength="200" class="input-xlarge required"/></c:if> <c:if test="${proedit&&oaEosPro.id!=null&&oaEosPro.proNumber!=null&&oaEosPro.proNumber!=''}"><form:input path="proNumber"  htmlEscape="false" maxlength="200" class="input-xlarge required"/></c:if> 立项完成自动生成</td>
				</tr>
				<tr>
					<td class="tit">责任销售：</td><td><sys:treeselect id="personLiableUser"  name="personLiableUser.id" value="${oaEosPro.personLiableUser.id}" labelName="oaEosPro.personLiableUser.name" labelValue="${oaEosPro.personLiableUser.name}"
																  title="负责人" url="/sys/office/treeData?type=3" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/><span class="help-inline"><font color="red">*</font> </span></td>
					<td class="tit">客户名称：</td><td><form:input path="customerName" htmlEscape="false" maxlength="200" class="input-xlarge "/></td>
				</tr>
				<tr>
					<td class="tit">客户方负责人：</td><td><form:input path="customerUser" htmlEscape="false" maxlength="200" class="input-xlarge "/></td>
					<td class="tit">客户负责人联系方式：</td><td><form:input path="customerContactInformation" htmlEscape="false" maxlength="200" class="input-xlarge "/></td>
				</tr>
			</table>
		<table class="table table-striped table-bordered table-condensed">
			<thead>
			<th colspan="4" align="left"><h5>详细信息</h5></th>
			</thead>
			<tr>
				<td class="tit">项目类型：</td><td><form:select path="proType" class="input-xlarge required">
				<form:options items="${fns:getDictList('eos_type')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select></td>
				<td class="tit">客户/项目定位：</td><td><form:input path="proLocation" htmlEscape="false" maxlength="64" class="input-xlarge required"/>(客户是否为行业领先地位，项目是否为标杆项目）</td>
			</tr>
			<tr>
				<td class="tit">资金来源：</td><td><form:select path="proCapitalSource" class="input-xlarge required">
				<form:options items="${fns:getDictList('eos_source')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select></td>
				<td class="tit">资金预算：</td><td><form:select path="proBudget" class="input-xlarge number">
				<form:options items="${fns:getDictList('eos_budget')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select></td>
			</tr>
			<tr>
				<td class="tit">周期：</td><td><form:select path="proCycle" class="input-xlarge ">
				<form:options items="${fns:getDictList('eos_cycle')}"  itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select></td>
				<td class="tit">项目预估金额（万元）：</td><td><form:input path="estimatedAmount" htmlEscape="false" maxlength="64" class="input-xlarge number required"/><span class="help-inline"><font color="red">*</font> </span></td>
			</tr>
			<tr>
				<td class="tit">毛利率百分比：</td><td><form:input path="grossProfitMargin" htmlEscape="false" maxlength="64" class="input-xlarge required"/><span class="help-inline"><font color="red">*</font> </span></td>
				<td class="tit">项目支持人员：</td><td><sys:treeselect id="userIds" checked="true" name="userIds" value="${oaEosPro.userIds}" labelName="oaEosPro.users_names" labelValue="${oaEosPro.users_names}"
																title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" />
				</td>
			</tr>
			<tr>
				<td class="tit">前期投入估算（万元）：</td><td><form:input path="inputEstimation" htmlEscape="false" maxlength="64" class="input-xlarge "/></td>
				<td class="tit">前期工作量估算（人天）：</td><td><form:input path="workload" htmlEscape="false" maxlength="64" class="input-xlarge "/></td>
			</tr>
			<tr>
				<td class="tit">项目主要交付内容：</td><td colspan="3"><form:textarea path="proContent" htmlEscape="false" rows="4" cssStyle="width: 70%;height: 300px;" maxlength="2000" class="input-xxlarge "/></td>
			</tr>
			<tr >
				<td colspan="4">
					<div class="form-actions">
						<c:if test="${oaEosPro.id==null}">
							<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
						</c:if>
						<c:if test="${proedit&&oaEosPro.paNumber!=null&&oaEosPro.paNumber!=''}"><input  class="btn btn-primary" type="button" onclick="saveCode();" value="保存编号"/></c:if>
						<c:if test="${oaEosPro.id!=null}">
							<c:if test="${oaEosPro.status==1||oaEosPro.status==0||oaEosPro.status==-2}">
								<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
								<c:if test="${oaEosPro.status==-2}">
									<input id="btnAudit" class="btn btn-success" type="button" onclick="Audit('${oaEosPro.id}')" value="重新提交审核"/>
								</c:if>
								<c:if test="${oaEosPro.status!=-2}">
								<input id="btnAudit" class="btn btn-success" type="button" onclick="Audit('${oaEosPro.id}')" value="提交审核"/>
								</c:if>
							</c:if>
							<c:if test="${oaEosPro.status==2}">
								<input id="btnAudit" class="btn btn-primary" type="button" onclick="Cancel('${oaEosPro.id}')" value="撤销审核"/>
							</c:if>
						<c:if test="${oaEosPro.status==3}">
							<input id="btnAudit" class="btn btn-success" type="button" onclick="RectAudit('${oaEosPro.id}')" value="指派项目经理"/>
						</c:if>
						</c:if>
						<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/oa/eos/oaEosPro/index'"/>

					</div>

				</td>
			</tr>
		</table>

		<c:if test="${oaEosPro.id!=null}">
		<legend><div style="padding: 0px 10px; text-align: left ">预立项子项目&nbsp;&nbsp;<button class="btn btn-primary pull-right" type="button" onclick="addProItem('${oaEosPro.id}')">添加子项目</button></div>  </legend>
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
			<tr>
				<th>名称</th>
				<th>负责人</th>
				<th>预估金额</th>
				<th>投入金额</th>
				<th>预估完成时间</th>
				<th>更新时间</th>
				<shiro:hasPermission name="oa:eos:oaEosPro:edit"><th>操作</th></shiro:hasPermission>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page}" var="oaEosProItem">
				<tr>
					<td><a href="${ctx}/oa/eos/oaEosProItem/form?id=${oaEosProItem.id}">
							${oaEosProItem.name}
					</a></td>
					<td>
							${oaEosProItem.personLiableUser.name}
					</td>
					<td>
							${oaEosProItem.estimatedAmount}元
					</td>
					<td>
							${oaEosProItem.inputEstimation}元
					</td>
					<td>
						<fmt:formatDate value="${oaEosProItem.workloadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${oaEosProItem.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<shiro:hasPermission name="oa:eos:oaEosPro:edit"><td>
						<a href="javascript:addProItem('${oaEosPro.id}','${oaEosProItem.id}')">修改</a>
						<a href="${ctx}/oa/eos/oaEosProItem/delete?id=${oaEosProItem.id}" onclick="return confirmx('确认要删除该销售项目子项吗？', this.href)">删除</a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</c:if>
	</fieldset>
	</form:form>
<br/>
	<br/>
</body>
</html>