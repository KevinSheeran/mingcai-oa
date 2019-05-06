<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
    request.setAttribute("vEnter", "\n");
    //奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
    //都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<title>周任务管理</title>
	<meta name="decorator" content="default"/>
    <style type="text/css">
        <!--
        .STYLE1 {
            font-size: 20px;
            font-weight: bold;
        }
        .STYLE2 {font-size: 14px}
        -->
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
<c:forEach items="${weekList}" var="items">
    <br/>
<table  border="1" class="table table-striped table-bordered table-condensed" style="max-width: 800px; margin: 0 auto;">
	<tr>
		<td height="44" colspan="10" align="center" bgcolor="#FFFF00" style="text-align: center;position:relative;">
            <span class="STYLE1">${items.titles}</span>
            <span style="position: absolute; right:10px; bottom:5px;">${items.createBy.name}&nbsp;&nbsp;<fmt:formatDate value="${items.createDate}" pattern="yyyy.MM.dd"/>&nbsp;&nbsp;<c:if test="${items.getCreateBy().getId()==fns:getUser().id&&items.id!=null}"><a onclick="showEdit(this);" href="javascript:void(0);" sid="${items.id}">修改计划</a></c:if></span>
        </td>
	</tr>
    <tr>
        <td colspan="10">
            <form  action="${ctx}/oa/week/oaWeekWork/save" method="post" name="${items.id}"  class="inputForm form-horizontal">
                <input type="hidden" name="id" value="${items.id}">
                <input type="hidden" name="startEnd" value="${items.startEnd}" />
                <div class="control-group">
                    <label class="control-label">周目标：</label>
                    <div class="controls">
                        <div class="content"  <c:if test="${items.isshow}">style="display: none;"</c:if>>${items.title}</div>
                        <input type="text" name="title"  <c:if test="${!items.isshow}">style="display: none;"</c:if> value="${items.title}" maxlength="2000" class="input-xlarge input-content"/>
                    </div>
                </div>
                <div class="control-group">
                <label class="control-label">任务详情：</label>
                <div class="controls">
                    <div class="content"  <c:if test="${items.isshow}">style="display: none;"</c:if>>${fn:replace(items.remarks,vEnter,"<br>")}</div>
                    <textarea name="remarks" rows="8"  <c:if test="${!items.isshow}">style="display: none;"</c:if> maxlength="2000" class="input-xxlarge input-content">${items.remarks}</textarea>
                </div>
                </div>
                <div class="control-group">
                    <label class="control-label">周总结：</label>
                    <div class="controls">
                        <div class="content"  <c:if test="${items.isshow}">style="display: none;"</c:if>>${fn:replace(items.summary,vEnter,"<br>")}</div>
                         <textarea name="summary"  <c:if test="${!items.isshow}">style="display: none;"</c:if> rows="8" maxlength="2000" class="input-xxlarge input-content">${items.summary}</textarea>
                    </div>
                </div>
                <c:if test="${items.getCreateBy().getId()==fns:getUser().id||items.id==null}">
                    <div class="form-actions">
                        <shiro:hasPermission name="oa:week:oaWeekWork:edit"><input <c:if test="${!items.isshow}">style="display: none;"</c:if>  class="btn btn-primary input-content" type="button" onclick=" disableForm(this)" value="保 存"/>&nbsp;</shiro:hasPermission>
                    </div>
                </c:if>
            </form>
        </td>
    </tr>
    <!--
	<tr>
		<td align="center" bgcolor="#FF9900"><strong>计划目标</strong></td>
		<td align="center" bgcolor="#FF9900"><strong>星期一</strong><div>${items.weeksDate.get("0")}</div></td>
		<td align="center" bgcolor="#FF9900"><strong>星期二</strong><div>${items.weeksDate.get("1")}</div></td>
		<td align="center" bgcolor="#FF9900"><strong>星期三</strong><div>${items.weeksDate.get("2")}</div></td>
		<td align="center" bgcolor="#FF9900"><strong>星期四</strong><div>${items.weeksDate.get("3")}</div></td>
		<td align="center" bgcolor="#FF9900"><strong>星期五</strong><div>${items.weeksDate.get("4")}</div></td>
		<td align="center" bgcolor="#FF9900"><strong>星期六</strong><div>${items.weeksDate.get("5")}</div></td>
		<td align="center" bgcolor="#FF9900"><strong>星期日</strong><div>${items.weeksDate.get("6")}</div></td>
		<td align="center" bgcolor="#FF9900"><strong>意见或补充建议</strong></td>
		<td align="center" bgcolor="#FF9900"><strong>是否跟进</strong></td>
	</tr>
	<tr>

		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	-->

</table>
</c:forEach>

</body>
</html>