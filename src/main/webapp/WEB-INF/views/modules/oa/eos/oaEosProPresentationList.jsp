<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	request.setAttribute("vEnter", "\n");
	//奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
	//都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<meta name="decorator" content="default"/>
	<style type="text/css">
        h1, h2, h3, h4, h5, h6{
            color: #5b5b5b;
        }
		<!--
		.STYLE1 {
			font-size: 20px;
			font-weight: bold;
		}
		.STYLE2 {font-size: 14px}
		-->
        .input-xxlarge{
            width: 100%;
        }
        .form-horizontal .form-actions{
            padding-left: 0px;
        }
	</style>
	<script type="text/javascript">
        $(document).ready(function() {
            initHeight();
        });
        function initHeight() {//调整内容高度
            $(parent.document.getElementsByName(window.name)[0]).height($(document).height());
        }
        function disableForm(obj){
            $(obj).attr("disabled",true);
            $(obj).parent().parent().submit();
        }
        function showEdit(obj){
            $("form[name='"+$(obj).attr("sid")+"']").find(".input-content").show();
            $("form[name='"+$(obj).attr("sid")+"']").find(".content").hide();
            $(obj).hide();
        }
	</script>
</head>
<body>
<sys:message content="${message}"/>
<c:if test="${pro.id==null}">
    <div style="text-align: center;padding-top: 20px">
        <h3>
            没有项目
        </h3>
    </div>
</c:if>
<c:if test="${pro.id!=null}">
<c:forEach items="${weekList}" var="items" varStatus="index">
	<br/>
	<table  border="0" class="table table-striped table-bordered table-condensed" style="max-width: 800px; margin: 0 auto;">
        <c:if test="${index.index==0}">
        <tr height="44"  align="center"  style="text-align: center;position:relative;">
            <td >
                <span class="STYLE1">项目：${pro.pro.name}</span>
            </td>
            <td >
                <span class="STYLE1">项目编号：${pro.pro.proNumber}</span>
            </td>
        </tr>
        </c:if>
		<tr>
			<td height="44" colspan="2" align="center" style="text-align: center;position:relative;" >
				<span class="STYLE1">${items.titles}</span>
				<span style="position: absolute; right:10px; bottom:5px;">${items.createBy.name}&nbsp;&nbsp;<fmt:formatDate value="${items.createDate}" pattern="yyyy.MM.dd"/>&nbsp;&nbsp;<c:if test="${items.getCreateBy().getId()==fns:getUser().id&&items.id!=null}"><a onclick="showEdit(this);" href="javascript:void(0);" sid="${items.id}">修改计划</a></c:if></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<form  action="${ctx}/oa/eos/oaEosProPresentation/save" method="post" name="${items.id}"  class="inputForm form-horizontal">
					<input type="hidden" name="id" value="${items.id}">
                    <input type="hidden" name="eosId" value="${pro.id}">
					<input type="hidden" name="startEnd" value="${items.startEnd}" />
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                            <th colspan="4" align="left"><h5>本周项目总结</h5></th>
                        </thead>
                        <tr>
                            <td class="tit">
                                <div class="content"  <c:if test="${items.isshow}">style="display: none;"</c:if>>${fn:replace(items.summary,vEnter,"<br>")}</div>
                                <textarea name="summary" rows="8"  <c:if test="${!items.isshow}">style="display: none;"</c:if> maxlength="2000" class="input-xxlarge input-content">${items.summary}</textarea>
                            </td>
                        </tr>
                    </table>
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th colspan="4" align="left"><h5>下周项目工作计划</h5></th>
                        </thead>
                        <tr>
                            <td class="tit">
                                <div class="content"  <c:if test="${items.isshow}">style="display: none;"</c:if>>${fn:replace(items.nextPlan,vEnter,"<br>")}</div>
                                <textarea name="nextPlan" rows="8"  <c:if test="${!items.isshow}">style="display: none;"</c:if> maxlength="2000" class="input-xxlarge input-content">${items.nextPlan}</textarea>
                            </td>
                        </tr>
                    </table>
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th colspan="4" align="left"><h5>上期遗留问题的处理（说明上一个汇报周期内问题的处理意见和处理结果）</h5></th>
                        </thead>
                        <tr>
                            <td class="tit">
                                <div class="content"  <c:if test="${items.isshow}">style="display: none;"</c:if>>${fn:replace(items.problemHandle,vEnter,"<br>")}</div>
                                <textarea name="problemHandle"  <c:if test="${!items.isshow}">style="display: none;"</c:if> rows="8" maxlength="2000" class="input-xxlarge input-content">${items.problemHandle}</textarea>
                            </td>
                        </tr>
                    </table>
                    <table class="table table-striped table-bordered table-condensed">
                        <thead>
                        <th colspan="4" align="left"><h5>本期问题与建议（说明本次汇报周期内需要解决的问题、潜在的风险、建议解决办法或应对措施、寻求的帮助）</h5></th>
                        </thead>
                        <tr>
                            <td class="tit">
                                <div class="content"  <c:if test="${items.isshow}">style="display: none;"</c:if>>${fn:replace(items.proposal,vEnter,"<br>")}</div>
                                <textarea name="proposal"  <c:if test="${!items.isshow}">style="display: none;"</c:if> rows="8" maxlength="2000" class="input-xxlarge input-content">${items.proposal}</textarea>
                            </td>
                        </tr>
                    </table>
					<c:if test="${items.getCreateBy().getId()==fns:getUser().id||items.id==null}">
						<div class="form-actions">
							<shiro:hasPermission name="oa:week:oaWeekWork:edit"><input <c:if test="${!items.isshow}">style="display: none;"</c:if>  class="btn btn-primary input-content" type="button" onclick=" disableForm(this)" value="保 存"/>&nbsp;</shiro:hasPermission>
						</div>
					</c:if>
				</form>
			</td>
		</tr>
	</table>
</c:forEach>
</c:if>
</body>
</html>