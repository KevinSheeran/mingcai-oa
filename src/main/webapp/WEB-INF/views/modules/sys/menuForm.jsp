\<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>菜单管理</title>
	<meta name="decorator" content="default"/>
    <style type="text/css">
        .ul_color,.ul_style{
            margin: 0px;
            padding: 0px;
            overflow: hidden;
        }
        .ul_color li{
            float: left;
            margin: 10px 5px;
            display: block;
            width: 45px;
            height: 45px;
            cursor:pointer;
        }
        .ul_style li .grid.active,.ul_color li.active{
            border: 2px solid #333;
        }
        .ul_style li{
            display: block;
            width: 400px;
            position: relative;
        }
    </style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
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

            $(".ul_color li").click(function(){
                $(".ul_color li").removeClass("active");
                $(this).addClass("active");
                $("#cssColor").val($(this).attr(("val")));
                $("#iconIcon").attr("class",$(this).attr(("val")));
            })

            $(".grid").click(function(){
                $(".grid").removeClass("active");
                $(this).addClass("active");
                $("#cssStyle").val($(this).attr(("val")));
            })
		});
	</script>
</head>
<body>
<fieldset>
	<legend><div style="padding: 10px 10px; text-align: left "><shiro:hasPermission name="sys:menu:edit"><a  href="${ctx}/sys/menu/form">&nbsp;菜单添加</a></shiro:hasPermission></div></legend>
	<br/>
	<form:form id="inputForm" modelAttribute="menu" action="${ctx}/sys/menu/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">上级菜单:</label>
			<div class="controls">
                <sys:treeselect id="menu" name="parent.id" value="${menu.parent.id}" labelName="parent.name" labelValue="${menu.parent.name}"
					title="菜单" url="/sys/menu/treeData" extId="${menu.id}" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称:</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="50" class="required input-xlarge"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">链接:</label>
			<div class="controls">
				<form:input path="href" htmlEscape="false" maxlength="2000" class="input-xxlarge"/>
				<span class="help-inline">点击菜单跳转的页面</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">目标:</label>
			<div class="controls">
				<form:input path="target" htmlEscape="false" maxlength="10" class="input-small"/>
				<span class="help-inline">链接地址打开的目标窗口，默认：mainFrame</span>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label">所属菜单：</label>
            <div class="controls">
                <form:select path="menuType" class="input-xlarge required" >
                    <form:options items="${fns:getDictList('menuType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                </form:select>
            </div>
			<label class="control-label">图标:</label>
			<div class="controls">
				<sys:iconselect id="icon" name="icon" value="${menu.icon}"/>
			</div>
			<label class="control-label">背景:</label>
			<div class="controls">
				<form:hidden path="cssColor" htmlEscape="false" maxlength="2000" class="input-xxlarge"/>
				<ul class="ul_color"><li class="bg1" val="bg1"></li><li class="bg2" val="bg2"></li><li class="bg3" val="bg3"></li><li class="bg4" val="bg4"></li><li class="bg5" val="bg5"></li><li class="bg6" val="bg6"></li></ul>
			</div>
            <label class="control-label">样式:</label>
            <div class="controls">
                <form:hidden path="cssStyle" htmlEscape="false" maxlength="2000" class="input-xxlarge"/>
				<div id="channel-list" style="height: auto; width: 300px;">
                <ul class="ul_style"><li>
                            <div id="home" class="channel">
                                <div class="grid g150x75 bg1 desktop_icon" val="g150x75">
                                    <div class="menu-title">天意人间</div>
                                </div>
                                <div class="grid g70x70 bg1" val="g70x70">
                                    <div class="menu-title">天意人间</div>
                                </div>
                            </div>
                </li></ul>
				</div>
            </div>
		</div>

		<div class="control-group">
			<label class="control-label">排序:</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50" class="required digits input-small"/>
				<span class="help-inline">排列顺序，升序。</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">可见:</label>
			<div class="controls">
				<form:radiobuttons path="isShow" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" class="required"/>
				<span class="help-inline">该菜单或操作是否显示到系统菜单中</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">权限标识:</label>
			<div class="controls">
				<form:input path="permission" htmlEscape="false" maxlength="1000" class="input-xxlarge"/>
				<span class="help-inline">控制器中定义的权限标识，如：@RequiresPermissions("权限标识")</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:menu:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="window.history.go(-1)"/>
		</div>
	</form:form>
</fieldset>
</body>
<script type="application/javascript">


</script>
</html>