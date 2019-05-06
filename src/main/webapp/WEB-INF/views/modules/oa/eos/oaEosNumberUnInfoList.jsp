<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>非销售自编号管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function settingNumber(){
            // 正常打开
            $.jBox.open("iframe:${ctx}/oa/eos/oaEosNumberUn", "主编号设置", $(window).width()*4/5,$(window).height()*4/5, {
                buttons:{"设定为主编号":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
                        var formdata = h.find("iframe")[0].contentWindow.$("input[type='radio']:checked");
                        if(formdata.val()!=undefined){
                            loading('正在提交，请稍等...');
                            $.post("${ctx}/oa/eos/oaEosNumberUn/savesetting",{id:formdata.val()},function(data){
                                if(data=="success"){
                                    loading("提交完成!");
                                    self.location.reload();
                                }else{
                                    loading(data);

                                }
                                closeLoading();
                            })
                        }else{
                            alertx('请选择主编号');
                            return false;
						}

                    }
                }, loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });

        }
        function addCode(){
            // 正常打开
            confirmx("确定要生成编号吗？",function(){
                $.post("${ctx}/oa/eos/oaEosNumberUnInfo/addCode",{nuid:"${oaEosNumberUn.id}"},function(data){
                    if(data=="success"){
						location.reload();
                    }
				})
			},"");
		}
	</script>
</head>
<body>
<ul class="nav nav-tabs">
	<li ><a href="${ctx}/oa/eos/oaEosNumber">基本类型编号管理</a></li>
	<li class="active"><a href="${ctx}/oa/eos/oaEosNumberUnInfo/">非销售类编号管理</a></li>
</ul>
<legend><div style="padding: 10px 10px; text-align: left ">
	<a >编号列表</a>
	<c:if test="${oaEosNumberUn.id!=null}">
	&nbsp;&nbsp;<shiro:hasPermission name="oa:eos:oaEosNumber:edit">
	<a class="btn " href="javascript:;" onclick="addCode()"><i class="icon-plus"></i>项目编号添加</a>
</shiro:hasPermission>
	</c:if>
	<c:if test="${oaEosNumberUn.id==null}"><span class="red">项目编号未设置主编号请先设置主编号后生成编号</span></c:if><c:if test="${oaEosNumberUn.id!=null}">当前使用项目主编号:<span class="red">&nbsp;&nbsp;${oaEosNumberUn.itemNumber}</span></c:if>&nbsp;&nbsp;<a href="javascript:;" onclick="settingNumber()">设置主编号</a>
</div></legend>
	<form:form id="searchForm" modelAttribute="oaEosNumberUnInfo" action="${ctx}/oa/eos/oaEosNumberUnInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li>
				<form:input path="itemNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>

	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>自编号</th>
				<th>完整编号</th>
				<th>添加时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaEosNumberUnInfo">
			<tr>
				<td>
					${oaEosNumberUnInfo.itemNumber}
				</td>
				<td>
					${oaEosNumberUn.itemNumber}-${oaEosNumberUnInfo.itemNumber}
				</td>
				<td>
					<fmt:formatDate value="${oaEosNumberUnInfo.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>