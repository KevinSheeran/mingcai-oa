<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
	<title>明材办公</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />

</head>
<body ontouchstart>
<div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

<div class="container" id="container"></div>

<script type="text/html" id="tpl_home">
	<div class="page">
		<div class="page__bd" style="height: 100%;">
			<div class="weui-tab">
				<div class="weui-tab__panel">
					<div class="weui-grids">
						<a href="javascript:;" class="weui-grid">
							<div class="weui-grid__icon">
								<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="">
							</div>
							<p class="weui-grid__label">Grid</p>
						</a>
						<a href="javascript:;" class="weui-grid">
							<div class="weui-grid__icon">
								<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="">
							</div>
							<p class="weui-grid__label">Grid</p>
						</a>
						<a href="javascript:;" class="weui-grid">
							<div class="weui-grid__icon">
								<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="">
							</div>
							<p class="weui-grid__label">Grid</p>
						</a>
						<a href="javascript:;" class="weui-grid">
							<div class="weui-grid__icon">
								<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="">
							</div>
							<p class="weui-grid__label">Grid</p>
						</a>
						<a href="javascript:;" class="weui-grid">
							<div class="weui-grid__icon">
								<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="">
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
								<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="">
							</div>
							<p class="weui-grid__label">Grid</p>
						</a>
						<a href="javascript:;" class="weui-grid">
							<div class="weui-grid__icon">
								<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="">
							</div>
							<p class="weui-grid__label">Grid</p>
						</a>
						<a href="javascript:;" class="weui-grid">
							<div class="weui-grid__icon">
								<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="">
							</div>
							<p class="weui-grid__label">Grid</p>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="weui-tabbar page__category-content">
		<a href="javascript:;" class="weui-tabbar__item weui-bar__item_on js_item" data-id="home">
                    <span style="display: inline-block;position: relative;">
                        <img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                        <span class="weui-badge" style="position: absolute;top: -2px;right: -13px;">8</span>
                    </span>
			<p class="weui-tabbar__label">首页</p>
		</a>
		<a href="javascript:;" class="weui-tabbar__item js_item" >
			<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
			<p class="weui-tabbar__label">报销</p>
		</a>
		<a href="javascript:;" class="weui-tabbar__item js_item" data-id="audit">
                    <span style="display: inline-block;position: relative;">
                        <img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
                        <span class="weui-badge weui-badge_dot" style="position: absolute;top: 0;right: -6px;"></span>
                    </span>
			<p class="weui-tabbar__label">审批</p>
		</a>
		<a href="javascript:;" class="weui-tabbar__item js_item" data-id="user">
			<img src="${ctxStaticTheme}/images/icon_tabbar.png" alt="" class="weui-tabbar__icon">
			<p class="weui-tabbar__label">我</p>
		</a>
	</div>
	<script type="text/javascript">
        $(function(){
            var winH = $(window).height();
            var categorySpace = 10;

            $('.js_item').on('click', function(){
                var id = $(this).data('id');
                window.pageManager.go(id);
            });
        });
			$('.weui-tabbar__item').on('click', function () {
				$(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
			});
	</script>
</script>
<script type="text/html" id="tpl_audit">
    <div class="page">
    <div class="page__bd" style="height: 100%;">
    <div class="weui-tab">
    <div class="weui-navbar">
    <div class="weui-navbar__item weui-bar__item_on" toid="tab1">
    待审核
    </div>
    <div class="weui-navbar__item" toid="tab2">
    已审核
    </div>
    </div>
    <div class="weui-tab__panel">
    <section class="aui-flexView on" id="tab1">
    <section class="aui-scrollView">
    <div class="aui-nav-title">项目 </div>
    <div class="aui-class-list-box">
    <a href="javascript:;" class="aui-class-list-item js_item" data-id="auditList">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item001.png" alt="">
    </div>
    <div class="aui-cell-fr">预立项</div>
    <div class="aui-cell-tr"></div>
    <span class="weui-badge" style="position: absolute;top: 5px;right: 13px;">8</span>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
    </div>
    <div class="aui-cell-fr">立项</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
    </div>
    <div class="aui-cell-fr">基本变更</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
    </div>
    <div class="aui-cell-fr">延期变更</div>
    <div class="aui-cell-tr"></div>
    </a>
    </div>

    <div class="aui-nav-title">采购 </div>
    <div class="aui-class-list-box">
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item005.png" alt="">
    </div>
    <div class="aui-cell-fr">采购一(非合同)</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item005.png" alt="">
    </div>
    <div class="aui-cell-fr">采购二(非合同)</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item005.png" alt="">
    </div>
    <div class="aui-cell-fr">合同采购</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    </div>
    <div class="aui-cell-fr"></div>
    <div class="aui-cell-tr"></div>
    </a>
    </div>
    <div class="aui-nav-title">其他 </div>
    <div class="aui-class-list-box">
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item003.png" alt="">
    </div>
    <div class="aui-cell-fr">报销</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item004.png" alt="">
    </div>
    <div class="aui-cell-fr">出差</div>
    <div class="aui-cell-tr"></div>
    </a>
    </div>
    </section>

    </section>
    <section class="aui-flexView "  id="tab2">
    <section class="aui-scrollView">
    <div class="aui-nav-title">项目 </div>
    <div class="aui-class-list-box">
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item001.png" alt="">
    </div>
    <div class="aui-cell-fr">预立项</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
    </div>
    <div class="aui-cell-fr">立项</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
    </div>
    <div class="aui-cell-fr">基本变更</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item002.png" alt="">
    </div>
    <div class="aui-cell-fr">延期变更</div>
    <div class="aui-cell-tr"></div>
    </a>
    </div>

    <div class="aui-nav-title">采购 </div>
    <div class="aui-class-list-box">
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item005.png" alt="">
    </div>
    <div class="aui-cell-fr">采购一(非合同)</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item005.png" alt="">
    </div>
    <div class="aui-cell-fr">采购二(非合同)</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item005.png" alt="">
    </div>
    <div class="aui-cell-fr">合同采购</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    </div>
    <div class="aui-cell-fr"></div>
    <div class="aui-cell-tr"></div>
    </a>
    </div>
    <div class="aui-nav-title">其他 </div>
    <div class="aui-class-list-box">
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item003.png" alt="">
    </div>
    <div class="aui-cell-fr">报销</div>
    <div class="aui-cell-tr"></div>
    </a>
    <a href="javascript:;" class="aui-class-list-item">
    <div class="aui-cell-fl">
    <img src="${ctxStaticTheme}/images/icon-item004.png" alt="">
    </div>
    <div class="aui-cell-fr">出差1</div>
    <div class="aui-cell-tr"></div>
    </a>
    </div>
    </section>

    </section>
    </div>
    </div>
    </div>
    </div>
    <script type="text/javascript">
    $(function(){
        $('.js_item').on('click', function(){
            var id = $(this).data('id');
            window.pageManager.go(id);
        });
        $('.weui-navbar__item').on('click', function () {
            $("#"+$(this).attr("toid")).addClass('on').siblings('.aui-flexView').removeClass('on');
            $(this).addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
        });


    });
</script>
</script>
<script type="text/html" id="tpl_auditList">
    <div class="page">
    <div class="page__bd" style="height: 100%;" id="body">

    </div>
    </div>
    <div class="weui-form-preview" style="display: none" id="model">
    <div class="weui-form-preview__hd">
    <div class="weui-form-preview__item">
    <label class="weui-form-preview__label a">${item.name}</label>

    </div>
    </div>
    <div class="weui-form-preview__bd">
    <div class="weui-form-preview__item">
    <label class="weui-form-preview__label">预估金额</label>
    <span class="weui-form-preview__value b">${item.estimatedAmount}元</span>
    </div>
    <div class="weui-form-preview__item">
    <label class="weui-form-preview__label">责任销售</label>
    <span class="weui-form-preview__value c">${item.personLiableUser.name}</span>
    </div>
    <div class="weui-form-preview__item">
    <label class="weui-form-preview__label">客户名称</label>
    <span class="weui-form-preview__value d">${item.customerName}</span>
    </div>
    <div class="weui-form-preview__item">
    <label class="weui-form-preview__label">客户方负责人</label>
    <span class="weui-form-preview__value e">${item.customerUser}</span>
    </div>

    </div>
    <div class="weui-form-preview__ft">
    <a class="weui-form-preview__btn weui-form-preview__btn_default" href="javascript:">查看详情</a>
    <button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">通过</button>
    </div>
    </div>
    <script type="text/javascript">
    $.get("${oa}//weixin/auditList",{},function(data){
        var data=JSON.parse(data);
        var model=$("#model").clone();
        for(var i=0;i<data.length;i++){
            var item=data[i];
            model.find(".a").html(item.name);
            model.find(".b").html(item.estimatedAmount+"元");
            model.find(".c").html(item.personLiableUser.name);
            model.find(".d").html(item.customerName);
            model.find(".e").html(item.customerUser);
            $("#body").append(model.fadeIn(200));
        }

    })
</script>
</script>
</body>
</html>