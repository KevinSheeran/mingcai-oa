<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	request.setAttribute("vEnter", "\n");
	//奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
	//都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<title>项目任务管理</title>
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
	<link href="${ctxStatic}/bootstrap/bootstrap.css" rel="stylesheet" />
	<style>
		.number{
			border:1px solid #DDDDDD;
			width:35px;
			height：35px;
			border-radius:25px;
			text-align: center;
			line-height: 35px;
		}
		#inputForm input{
			height: auto;

		}
		.bg-info{
			background: #0bbbee;

		}
		.bg-success{
			background: #00b700;

		}
	</style>
</head>
<body>
<fieldset>
	<legend>
		<div style="padding: 10px 10px; text-align: left "><c:if test="${taskType==null||taskType==''}">
		<a   href="${ctx}/oa/oaProductTask?product_id=${oaProductTask.productId}" >${oaProduct.name}</a>
		</c:if>
		<c:if test="${taskType!=null}">
		<a   href="${ctx}/oa/oaProductTask/toUser" >${oaProduct.name}</a>
		</c:if>>>任务详情
	</div>
	</legend>
	<div class="col-lg-12">
		<section class="comment-list block">
			<article id="" class="comment-item media arrow arrow-left">
				<a class="pull-left thumb-small avatar">${index.index}</a>
				<section class="media-body panel">
	<form:form id="inputForm" modelAttribute="oaProductTask" enctype="multipart/form-data" action="${ctx}/oa/oaProductTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="productId"/>
		<input type="hidden" name="taskType" value="${taskType}">
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">任务名称：</label>
			<div class="controls">
				<c:if test="${oaProductTask.createBy.id!=''&&oaProductTask.createBy.id!=null&&oaProductTask.createBy.id!=user.id}">
					<form:input path="name" readonly="true" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				</c:if>
				<c:if test="${oaProductTask.createBy.id==''||oaProductTask.createBy.id==null||oaProductTask.createBy.id==user.id}">
					<form:input path="name"  htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				</c:if>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务指派给：</label>
			<div class="controls">
				<sys:treeselect id="taskToUserId" allowClear="true"  name="taskToUserId" value="${oaProductTask.taskUser.id}" labelName="oaProductTaskUsername" labelValue="${oaProductTask.taskUser.name}"
								title="任务人" url="/oa/oaProductTask/treeData?productId=${oaProductTask.productId}"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务完成状态：</label>
			<div class="controls">
				<form:radiobuttons  path="taskStatus" items="${fns:getDictList('task_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务截止时间：</label>
			<div class="controls">
				<input name="prodcutEndDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${oaProductTask.prodcutEndDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">任务描述信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传附件：</label>
			<div class="controls">
				<c:forEach items="${list}" var="item">
					<c:if test="${item.type==2}">
					<a href="javascript:void(0)" onclick="downloads('path=${item.pathUrl}&name=${item.name}.${item.format}');" title="下载">${item.name}.${item.format}(${item.filesize})</a>
					<c:if test="${fns:getUser().id==oaProductTask.createBy.id}">
						<a href='javascript:void(0);' onclick="removeFile(this,'${item.id}')">删除</a>&nbsp;
					</c:if>
					</c:if>
				</c:forEach>
				<c:if test="${fns:getUser().id==oaProductTask.createBy.id||oaProductTask.id==null}">
					<label id="addFile" class="btn">添加</label>
				</c:if>
			</div>
		</div>
		<div class="form-actions">
			<c:if test="${fns:getUser().id==oaProductTask.createBy.id||oaProductTask.id==null}">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</c:if>
			<c:if test="${taskType==null||taskType==''}">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/oa/oaProductTask?product_id=${oaProductTask.productId}'"/>&nbsp;
			</c:if>
			<c:if test="${taskType!=null}">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.location.href='${ctx}/oa/oaProductTask/toUser'"/>&nbsp;
			</c:if>
			<c:if test="${oaProductTask.taskStatus!=2&&oaProductTask.id!=null}">
			<input id="btnTask" class="btn btn-primary" type="button" onclick="openTaskWin()" value="任务跟进"/>
			</c:if>
			<c:if test="${fns:getUser().id!=oaProductTask.createBy.id&&oaProductTask.id!=null}">
				&nbsp;&nbsp;<span class="red"> 调整或上传附件请进入跟进任务进行处理！</span>
			</c:if>
		</div>
	</form:form>
				</section>
			</article>
		</section>
	</div>
</fieldset>
	<br/>
	<c:if test="${oaProductTask.id!=null}">
	<div class="col-lg-12">
		<section class="comment-list block">
			<article  class="comment-item media arrow arrow-left">
				<section class="media-body panel">
					<header class="panel-heading clearfix">进度详情</header>
						<c:forEach items="${loglist}" var="log" varStatus="index">
							<!-- .comment-list -->
							<section class="comment-list block">
								<article id="comment-id-1" class="comment-item media arrow arrow-left">
									<a class="pull-left thumb-small avatar number">${loglist.size()-index.index}</a>
									<section class="media-body panel">
										<header class="panel-heading clearfix">
											<a href="javascirpt:void(0)">${log.createBy.name}</a>&nbsp;&nbsp;<label class="label <c:if test="${log.taskStatus==0}">bg-defule</c:if><c:if test="${log.taskStatus==1}">bg-info</c:if><c:if test="${log.taskStatus==2}">bg-success</c:if> m-l-mini">${fns:getDictLabel(log.taskStatus, 'task_status', '')}</label><span class="text-muted m-l-small pull-right"><i class="icon-time"></i><fmt:formatDate value="${log.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
										</header>
										<div>${fn:replace(log.remarks,vEnter,"<br>")}</div>
										<div class="comment-action m-t-small">
											<c:if test="${log.reslist.size()>0}">
											附件:<c:forEach items="${log.reslist}" var="item">
												<c:if test="${item.type==2}">
													<a href="javascript:void(0)" onclick="downloads('path=${item.pathUrl}&name=${item.name}.${item.format}');" title="下载">${item.name}.${item.format}(${item.filesize})</a>
													<c:if test="${fns:getUser().id==oaProductTask.createBy.id}">
														<a href='javascript:void(0);' onclick="removeFile(this,'${item.id}')">删除</a>&nbsp;
													</c:if>
												</c:if>
											</c:forEach>
											</c:if>
										</div>
									</section>
								</article>
							</section>
						</c:forEach>
					<c:if test="${loglist.size()==0}">
						暂无进度信息
					</c:if>
				</section>
			</article>
		</section>
	</div>
	</c:if>

<script type="text/javascript">
	$("#addFile").click(function(){
	   $(this).before("<input type=\"file\" name='files'><a href='javascript:void(0);' onclick='remove(this)'>删除</a>&nbsp;");
	});
	function remove(obj){
        $(obj).prev().remove();
	    $(obj).remove();
	}
	function removeFile(obj,id){
        $.jBox.confirm("确认要删除资源吗？",'系统提示',function(v,h,f){
            if(v=='ok'){
                $.post("${ctx}/oa/oaProductResources/delete",{"id":id},function(data){
                    var data=JSON.parse(data);
                    if(data.status=="success"){
                        $(obj).prev().remove();
                        $(obj).remove();
                    }else{

                        alert(data.msg);
                    }
                })
            }
        },{buttonsFocus:1, closed:function(){
        }});
	}
	function downloads(url){
        try{
            var elemIF = document.createElement("iframe");
            elemIF.src ="${ctx}/oa/oaProductResources/download?"+url ;
            elemIF.style.display = "none";
            document.body.appendChild(elemIF);
        }catch(e){

        }
	}
	function openTaskWin(){
        $.jBox.open("iframe:${ctx}/oa/oaProductTask/actionform?id=${oaProductTask.id}", "任务跟进", 800, $(window).height()-150, {
            buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                    loading('正在提交，请稍等...');
                    $.ajax({
                        url: '${ctx}/oa/oaProductTask/actionSave',
                        type: 'POST',
                        cache: false,
                        data: new FormData( h.find("iframe")[0].contentWindow.$("#inputForm")[0]),
                        processData: false,
                        contentType: false
                    }).done(function(res) {

                        window.location.reload();
                    }).fail(function(res) {});
                }
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });

	}
</script>
</body>
</html>