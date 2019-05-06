<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
                rules: {
                    mobile: {remote: "${ctx}/sys/user/checkMobile?oldMobile=${user.mobile}"}
                },
                messages: {
                    mobile: {remote: "手机号已存在"}
                },
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
		top.$("#user_image").attr("src",'<c:if test="${user.wxUsers==null||user.wxUsers.avatar==''}">/static/windowsstyle/icon/user-male-icon.png</c:if><c:if test="${user.wxUsers!=null&&user.wxUsers.avatar!=''}">${user.wxUsers.avatar}/100</c:if>');
	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/info">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd">修改密码</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="user" action="${ctx}/sys/user/info" method="post" class="form-horizontal"><%--
		<form:hidden path="email" htmlEscape="false" maxlength="255" class="input-xlarge"/>
		<sys:ckfinder input="email" type="files" uploadPath="/mytask" selectMultiple="false"/> --%>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">头像:</label>
			<div class="controls">
				<img src="<c:if test="${user.wxUsers==null||user.wxUsers.avatar==''}">/static/windowsstyle/icon/user-male-icon.png</c:if><c:if test="${user.wxUsers!=null&&user.wxUsers.avatar!=''}">${user.wxUsers.avatar}/100</c:if>" id="img" width="90px;"/>
				<%--<form:hidden id="nameImage" path="photo" htmlEscape="false" class="input-xlarge"/>&nbsp;&nbsp;--%>
				<%--<a  href="javascript:void(0);" onclick="showDialog()">选择</a>--%>
			</div>
		</div>
		<div class="control-group">
			<h4>
				<label class="control-label">企业微信用户信息同步:</label>
				<div class="controls">
					<c:if test="${user.wxUsers!=null}"><span class="">已绑定</span></c:if><c:if test="${user.wxUsers==null}"><span class="error">未绑定</span></c:if>
				</div>
			</h4>
		</div>
        <div class="control-group">
            <label class="control-label">用户名:</label>
            <div class="controls">
                <label class="lbl">${user.loginName}</label>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label">归属公司:</label>
			<div class="controls">
				<label class="lbl">${user.company.name}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">归属部门:</label>
			<div class="controls">
				<label class="lbl">${user.office.name}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<form:input path="email" htmlEscape="false" maxlength="50" class="email"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">手机:</label>
			<div class="controls">
				<form:input path="mobile" htmlEscape="false" maxlength="50"  class="required"/><span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">电话:</label>
			<div class="controls">
				<form:input path="phone" htmlEscape="false" maxlength="50"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户类型:</label>
			<div class="controls">
				<label class="lbl">${fns:getDictLabel(user.userType, 'sys_user_type', '无')}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户角色:</label>
			<div class="controls">
				<label class="lbl">${user.roleNames}</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上次登录:</label>
			<div class="controls">
				<label class="lbl">IP: ${user.oldLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.oldLoginDate}" type="both" dateStyle="full"/></label>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
		</div>
	</form:form>
	<script type="application/javascript">
		<%--function getImage(){--%>
		    <%--if($("#nameImage")!=''){--%>
		        <%--return $("#nameImage").val();--%>
			<%--}--%>
		<%--return "${ctxStatic}/windowsstyle/cropper/picture.jpg";--%>
		<%--}--%>
        <%--function showDialog(){--%>
            <%--var width=$(window).width()/1.5;--%>
            <%--var height=$(window).height()/1.3;--%>
            <%--var title="选择图像";--%>
            <%--$.jBox.open("iframe:${ctx}/sys/user/win", title, width,height, {--%>
                <%--buttons:{"保存":"ok", "关闭":true}, submit:function(v, h, f){--%>
                    <%--if (v=="ok"){--%>
                        <%--h.find("iframe")[0].contentWindow.$("#Images").click();--%>
                        <%--var image = h.find("iframe")[0].contentWindow.$("input[name='images']").val();--%>
                       <%--if(image){--%>
							<%--$("#img").attr("src",image);--%>
							<%--$("#nameImage").val(image);--%>
					   <%--}--%>
                        <%--//return false;--%>
                    <%--}--%>
                <%--}, loaded:function(h){--%>
                    <%--$(".jbox-content", document).css("overflow-y","hidden");--%>
                <%--}--%>
            <%--});--%>

        <%--}--%>

	</script>
</body>
</html>