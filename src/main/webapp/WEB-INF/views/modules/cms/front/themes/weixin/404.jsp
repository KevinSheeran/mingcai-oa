<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>明材办公-审批</title>
	<meta name="decorator" content="cms_default_audit" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>
<div class="page icons">
	<div class="page__bd" style="padding-top: 50%;">
		<div class="icon-box">
			<i class="weui-icon-warn weui-icon_msg-primary"></i>
			<div class="icon-box__ctn">
				<h3 class="icon-box__title">403 异常</h3>
				<p class="icon-box__desc">用户验证失效！请重新打开应用</p>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    $(function(){
        $('.weui-navbar__item').on('click', function () {
            $("#"+$(this).attr("toid")).addClass('on').siblings('.aui-flexView').removeClass('on');
            $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
        });
    });
</script>
</body>
</html>