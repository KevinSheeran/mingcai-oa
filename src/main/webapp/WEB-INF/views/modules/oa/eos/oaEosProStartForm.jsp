<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>立项启动管理</title>
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
            orderplan();
		});
		function removenodel(obj){
		    var $this=$(obj).parent().parent().parent().parent().parent();
		    $this.fadeOut(500,function(){$this.remove();orderplan();});
		}
		function addplan(){
		    var model=$("#model").clone();
		    $("#cplan").append(model.fadeIn(500));
            orderplan();
		}
		function orderplan(){

		    $("#cplan").find(".plan_title").each(function(index,obj){
				$(this).html("第"+getNum(index)+"阶段");
			})

		}

		function getNum(n){
		    var sn="一";
		    switch (n){
				case 0:
				    sn="一"
				    break;
                case 1:
                    sn="二"
                    break;
                case 2:
                    sn="三"
                    break;
                case 3:
                    sn="四"
                    break;
                case 4:
                    sn="五"
                    break;
                case 5:
                    sn="六"
                    break;
                case 6:
                    sn="七"
                    break;
                case 7:
                    sn="八"
                    break;
                case 8:
                    sn="九"
                    break;
                case 9:
                    sn="十"
                    break;
			}
			return sn;
		}
        function Audit(id){
            // 正常打开
            top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosProStart/audit?id="+id, "立项审核", $(window).width()*4/5,$(window).height()*4/5, {
                buttons:{"提交":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
                        var formdata = h.find("iframe")[0].contentWindow.$("#inputForm");
                        if(formdata!='undefined'){
                            loading('正在提交，请稍等...');
                            $.post("${ctx}/oa/eos/oaEosProStart/auditsave",formdata.serialize(),function(data){
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


        function xzclick(){
            // 正常打开
           /* top.$.jBox.open("iframe:/oa/eos/oaProItemTemplat", $(window).width()*4/5,$(window).height()*4/5, {
                buttons:{"关闭":true}, submit:function(v, h, f){
                },
                closed:function(){
                    location.reload();
                },
                loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });*/
           location.href="${ctx}/oa/eos/oaEosPro/tz";
        }


        function Cancel(id){
            // 正常打开
            confirmx("确定要取消审核吗？","${ctx}/oa/eos/oaEosProStart/cancel?id="+id,"");

        }
        function editPro(){
            $("input[name='savecode']").val("yes");
            $("#inputForm").submit();
        }
	</script>
</head>
<body>
<fieldset>
	<legend><div style="padding: 0px 10px; text-align: left "><a href="${ctx}/oa/eos/oaEosProStart/index">项目首页</a>>>立项启动</div> </legend>
	<form id="inputForm"  action="${ctx}/oa/eos/oaEosProStart/save" method="post" class="form-horizontal">
		<form:hidden path="oaEosProStart.id"/>
		<sys:message content="${message}"/>
		<input type="hidden" name="savecode" value="no">
        <c:if test="${oaEosProStart.status!=0}">
        <table class="table table-striped table-bordered table-condensed">
            <thead>
            <th colspan="4" align="left"><h5>流程状态</h5></th>
            </thead>
            <c:forEach items="${flowmap}" var="flow" varStatus="index">
                <tr>
                    <td class="tit" colspan="3"><c:if test="${index.index==0}">管理中心审批</c:if><c:if test="${index.index==1}">总经理审批</c:if></td>
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
			<th colspan="4" align="left"><h5>基本信息</h5></th>

			</thead>
			<tr>
				<td class="tit">项目名称：${oaEosPro.name}</td><td class="tit">责任销售：${oaEosPro.personLiableUser.name}</td>
			</tr>
			<tr>
				<td class="tit">预立项编号：${oaEosPro.paNumber}</td>
				<td class="tit">立项编号：${oaEosPro.proNumber}</td>
			</tr>
		</table>
		<table class="table table-striped table-bordered table-condensed">
		<thead>
		<th colspan="4" align="left"><h5>启动信息</h5></th>
		</thead>
		<tr>
			<td class="tit">项目经理：</td><td><sys:treeselect id="personLiableUser" name="personLiableUser.id" value="${oaEosProStart.personLiableUser.id}" labelName="oaEosProStart.personLiableUser.name" labelValue="${oaEosProStart.personLiableUser.name}"
														  title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true" notAllowSelectParent="true"/><span class="help-inline"><font color="red">*</font> </span></td>
			<td class="tit">合同金额(元)：</td><td><form:input path="oaEosProStart.contractAmount" htmlEscape="false" class="input-xlarge  number required"/><span class="help-inline"><font color="red">*</font> </span></td>
		</tr>
		<tr>
			<td class="tit">预算金额(元)：</td><td><form:input path="oaEosProStart.estimatedAmount" htmlEscape="false" class="input-xlarge  number required"/><span class="help-inline"><font color="red">*</font> </span></td>
			<td class="tit">毛利润%：</td><td><form:input path="oaEosProStart.grossProfitMargin" htmlEscape="false" maxlength="64" class="input-xlarge required  digits"/><span class="help-inline"><font color="red">*</font> </span></td>
		</tr>
		<tr>
			<td class="tit">支持人员：</td><td><sys:treeselect id="userIds" checked="true" name="userIds" value="${oaEosProStart.userIds}" labelName="oaEosProStart.users_names" labelValue="${oaEosProStart.users_names}"
														  title="用户" url="/sys/office/treeData?type=3" isAll="true" notAllowSelectRoot="true" notAllowSelectParent="true" cssClass="required"/>
			<span class="help-inline"><font color="red">*</font> </span></td>
			<td class="tit">项目进展：</td><td>
			<form:radiobuttons path="oaEosProStart.progress" items="${fns:getDictList('pro_start_progress')}" itemValue="value" itemLabel="label" htmlEscape="false"></form:radiobuttons>
			</td>
		</tr>
	</table>
		<h4 style="padding: 10px 0">收款计划 <button class="btn" onclick="addplan()" type="button"><i class="icon-plus"></i>&nbsp;&nbsp;添加阶段</button></h4>
		<div id="cplan">
			<c:if test="${planlist.size()==0}">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
					<th colspan="4" align="left"><h5 ><span class="plan_title">第一阶段</span></h5></th>
					</thead>
					<tr>
						<td>收款计划：</td><td><input type="text" name="cplanContent" class="input-xlarge required"><span class="help-inline"><font color="red">*</font> </span></td>
						<td>收款时间：</td><td><input   name="receivablesDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" style="width:163px;"
												   value=""
												   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/><span class="help-inline"><font color="red">*</font> </span></td>
					</tr>
					<tr>
						<td>实际收款时间：</td><td><input   name="finishDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
													 value=""
													 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td>收款比例%：</td><td><input type="text" name="proportion" class="nput-xlarge digits required"><span class="help-inline"><font color="red">*</font> </span></td>
					</tr>
					<tr>
						<td >收款状态：</td><td colspan="3" >
						<select name="planstatus" class="input-xlarge required">>
							<c:forEach items="${fns:getDictList('pro_plan_start')}" var="option">
								<option value="${option.value}">${option.label}</option>
							</c:forEach>
						</select>
					</td>
					</tr>
				</table>
				</c:if>
			<c:forEach items="${planlist}" var="item" varStatus="oi">
				<table class="table table-striped table-bordered table-condensed">
					<thead>
					<th colspan="4" align="left"><h5><span class="plan_title">第一阶段</span> <c:if test="${oi.index!=0}"><button class="btn btn-small btn-danger pull-right" type="button" onclick="removenodel(this)"><i class="icon-trash"></i>&nbsp;&nbsp;删除</button></c:if></h5></th>
					</thead>
					<tr>
						<td>收款计划：</td><td><input type="text" name="cplanContent" value="${item.cplanContent}" class="input-xlarge required"><span class="help-inline"><font color="red">*</font> </span></td>
						<td>收款时间：</td><td><input   name="receivablesDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" style="width:163px;"
												   value="<fmt:formatDate value="${item.receivablesDate}" pattern="yyyy-MM-dd"/>"
												   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/><span class="help-inline"><font color="red">*</font> </span></td>
					</tr>
					<tr>
						<td>实际收款时间：</td><td><input   name="finishDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
													 value="<fmt:formatDate value="${item.finishDate}" pattern="yyyy-MM-dd"/>"
													 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
						<td>收款比例%：</td><td><input type="text" name="proportion" value="${item.proportion}" class="nput-xlarge digits required"><span class="help-inline"><font color="red">*</font> </span></td>
					</tr>
					<tr>
						<td >收款状态：</td>
						<td colspan="3">
							<select name="planstatus" class="input-xlarge required">>
								<c:forEach items="${fns:getDictList('pro_plan_start')}" var="option">
									<option value="${option.value}"  <c:if test="${option.value==item.status}">selected="selected"</c:if>>${option.label}</option>
								</c:forEach>
							</select>

						</td>
					</tr>
				</table>
			</c:forEach>
		</div>
		<div class="form-actions">
			<c:if test="${oaEosPro.proNumber!=null&&proedit&&oaEosPro.proNumber!=''}">
				<input id="btnSubmit" class="btn btn-primary" type="button" onclick="editPro()" value="调整方案"/>
			</c:if>
			<c:if test="${oaEosProStart.id!=null}">
				<c:if test="${oaEosProStart.status==0||oaEosProStart.status==-1}">
					<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
					<c:if test="${oaEosProStart.updateBy!=null&&oaEosProStart.updateBy!=''&&oaEosProStart.status==0}">
					<input id="btnAudit" class="btn btn-success" type="button" onclick="Audit('${oaEosProStart.id}')" value="提交审核"/>
					</c:if>
					<c:if test="${oaEosProStart.updateBy!=null&&oaEosProStart.updateBy!=''&&oaEosProStart.status==-1}">
						<input id="btnAudit" class="btn btn-success" type="button" onclick="Audit('${oaEosProStart.id}')" value="重新提交审核"/>
					</c:if>
				</c:if>
				<c:if test="${oaEosProStart.status==1}">
					<input id="btnAudit" class="btn btn-primary" type="button" onclick="Cancel('${oaEosProStart.id}')" value="撤销审核"/>
				</c:if>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/oa/eos/oaEosProStart/index'"/>
		</div>
		<c:if test="${oaEosPro.id!=null}">
			<h4 style="padding: 10px 0">立项子项目&nbsp;&nbsp;<c:if test="${oaEosProStart.status==0||proedit}"><button class="btn " type="button" onclick="addProItem('${oaEosPro.id}')"><i class="icon-plus"></i>&nbsp;&nbsp;添加子项目</button> <eos:treeselect id="xzItem" checked="true"  name="userIds" value="${oaEosProStart.userIds}"  labelName="oaEosProStart.users_names" labelValue="${oaEosProStart.users_names}" extId="${oaEosProStart.id}"
																																																																																								  title="子项目" url="/oa/eos/oaEosPro/itemThmplate" isAll="true" notAllowSelectRoot="true"  notAllowSelectParent="true" cssClass="required"/>   </c:if></h4>

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
					<shiro:hasPermission name="oa:eos:oaEosProStart:edit"><th>操作</th></shiro:hasPermission>
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
								￥${oaEosProItem.estimatedAmount ==null ? 0:oaEosProItem.estimatedAmount }元
						</td>
						<td>
								￥${oaEosProItem.inputEstimation ==null ? 0:oaEosProItem.inputEstimation }元
						</td>
						<td>
							<fmt:formatDate value="${oaEosProItem.workloadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<fmt:formatDate value="${oaEosProItem.actualTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>
						<td>
							<c:if test="${oaEosProStart.status==1||proedit}">
								<a href="javascript:addProItem('${oaEosPro.id}','${oaEosProItem.id}')">修改</a>
								<a href="${ctx}/oa/eos/oaEosProStartItem/delete?id=${oaEosProItem.id}" onclick="return confirmx('确认要删除该销售项目子项吗？', this.href)">删除</a>
							</c:if>
						</td>

					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
	</form>
	<table class="table table-striped table-bordered table-condensed" id="model" style="display: none;">
		<thead>
		<th colspan="4" align="left"><h5><span class="plan_title">第一阶段</span> <button class="btn btn-small btn-danger pull-right" type="button" onclick="removenodel(this)"><i class="icon-trash"></i>&nbsp;&nbsp;删除</button></h5></th>
		</thead>
		<tr>
			<td>收款计划：</td><td><input type="text" name="cplanContent" class="nput-xlarge required"><span class="help-inline"><font color="red">*</font> </span></td>
			<td>收款时间：</td><td><input   name="receivablesDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" style="width:163px;"
									 value=""
									 onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/><span class="help-inline"><font color="red">*</font> </span></td>
		</tr>
		<tr>
			<td>实际收款时间：</td><td><input   name="finishDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
									   value=""
									   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>
			<td>收款比例%：</td><td><input type="text" name="proportion" class="nput-xlarge digits required"><span class="help-inline"><font color="red">*</font> </span></td>
		</tr>
		<tr>
			<td >收款状态：</td><td colspan="3" >
			<select name="planstatus" class="input-xlarge required">>
				<c:forEach items="${fns:getDictList('pro_plan_start')}" var="option">
					<option value="${option.value}">${option.label}</option>
				</c:forEach>
			</select>
		</td>

		</td>
		</tr>
	</table>
</fieldset>
</body>
</html>