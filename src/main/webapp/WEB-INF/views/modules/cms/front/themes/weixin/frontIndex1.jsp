<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>明材办公</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>
<div class="page">
	<div class="page__bd" style="height: 100%;">
		<div class="weui-tab">
			<div class="weui-tab__panel">
				<div class="weui-grids">
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
					<a href="javascript:;" class="weui-grid">
						<div class="weui-grid__icon">
							<img src="./images/icon_tabbar.png" alt="">
						</div>
						<p class="weui-grid__label">Grid</p>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="weui-tabbar">
	<a href="javascript:;" class="weui-tabbar__item weui-bar__item_on">
                    <span style="display: inline-block;position: relative;">
                        <img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                        <span class="weui-badge" style="position: absolute;top: -2px;right: -13px;">8</span>
                    </span>
		<p class="weui-tabbar__label">首页</p>
	</a>
	<a href="javascript:;" class="weui-tabbar__item">
		<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
		<p class="weui-tabbar__label">报销</p>
	</a>
	<a href="javascript:;" class="weui-tabbar__item">
                    <span style="display: inline-block;position: relative;">
                        <img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                        <span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>
                    </span>
		<p class="weui-tabbar__label">审批</p>
	</a>
	<a href="javascript:;" class="weui-tabbar__item">
		<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
		<p class="weui-tabbar__label">我</p>
	</a>
</div>
<script type="text/javascript">
    $('.weui-tabbar__item').on('click', function () {
        $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
    });
</script>

</body>
</html>