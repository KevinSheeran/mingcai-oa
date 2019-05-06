<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>明材办公-预立项审核</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body class="mui-fullscreen">
<style>
	.mui-views,
	.mui-view,
	.mui-pages,
	.mui-page,
	.mui-page-content {
		position: absolute;
		left: 0;
		right: 0;
		top: 0;
		bottom: 0;
		width: 100%;
		height: 100%;
		background-color: #efeff4;
	}
	.mui-pages {
		top: 46px;
		height: auto;
	}
	.mui-scroll-wrapper,
	.mui-scroll {
		background-color: #efeff4;
	}
	.mui-page.mui-transitioning {
		-webkit-transition: -webkit-transform 300ms ease;
		transition: transform 300ms ease;
	}
	.mui-page-left {
		-webkit-transform: translate3d(0, 0, 0);
		transform: translate3d(0, 0, 0);
	}
	.mui-ios .mui-page-left {
		-webkit-transform: translate3d(-20%, 0, 0);
		transform: translate3d(-20%, 0, 0);
	}
	.mui-navbar {
		position: fixed;
		right: 0;
		left: 0;
		z-index: 10;
		height: 44px;
		background-color: #f7f7f8;
	}
	.mui-navbar .mui-bar {
		position: absolute;
		background: transparent;
		text-align: center;
	}
	.mui-android .mui-navbar-inner.mui-navbar-left {
		opacity: 0;
	}
	.mui-ios .mui-navbar-left .mui-left,
	.mui-ios .mui-navbar-left .mui-center,
	.mui-ios .mui-navbar-left .mui-right {
		opacity: 0;
	}
	.mui-navbar .mui-btn-nav {
		-webkit-transition: none;
		transition: none;
		-webkit-transition-duration: .0s;
		transition-duration: .0s;
	}
	.mui-navbar .mui-bar .mui-title {
		display: inline-block;
		width: auto;
	}
	.mui-page-shadow {
		position: absolute;
		right: 100%;
		top: 0;
		width: 16px;
		height: 100%;
		z-index: -1;
		content: '';
	}
	.mui-page-shadow {
		background: -webkit-linear-gradient(left, rgba(0, 0, 0, 0) 0, rgba(0, 0, 0, 0) 10%, rgba(0, 0, 0, .01) 50%, rgba(0, 0, 0, .2) 100%);
		background: linear-gradient(to right, rgba(0, 0, 0, 0) 0, rgba(0, 0, 0, 0) 10%, rgba(0, 0, 0, .01) 50%, rgba(0, 0, 0, .2) 100%);
	}
	.mui-navbar-inner.mui-transitioning,
	.mui-navbar-inner .mui-transitioning {
		-webkit-transition: opacity 300ms ease, -webkit-transform 300ms ease;
		transition: opacity 300ms ease, transform 300ms ease;
	}
	.mui-page {
		display: none;
	}
	.mui-pages .mui-page {
		display: block;
	}
	.mui-page .mui-table-view:first-child {
		margin-top: 15px;
	}
	.mui-page .mui-table-view:last-child {
		margin-bottom: 30px;
	}
	.mui-table-view {
		margin-top: 20px;
	}

	.mui-table-view span.mui-pull-right {
		color: #999;
	}
	.mui-table-view-divider {
		background-color: #efeff4;
		font-size: 14px;
	}
	.mui-table-view-divider:before,
	.mui-table-view-divider:after {
		height: 0;
	}
	.head {
		height: 40px;
	}
	#head {
		line-height: 40px;
	}
	.head-img {
		width: 40px;
		height: 40px;
	}
	#head-img1 {
		position: absolute;
		bottom: 10px;
		right: 40px;
		width: 40px;
		height: 40px;
	}
	.update {
		font-style: normal;
		color: #999999;
		margin-right: -25px;
		font-size: 15px
	}
	.mui-fullscreen {
		position: fixed;
		z-index: 20;
		background-color: #000;
	}
	.mui-ios .mui-navbar .mui-bar .mui-title {
		position: static;
	}
	/*问题反馈在setting页面单独的css*/
	#feedback .mui-popover{
		position: fixed;
	}
	#feedback .mui-table-view:last-child {
		margin-bottom: 0px;
	}
	#feedback .mui-table-view:first-child {
		margin-top: 0px;
	}

	.mui-plus.mui-plus-stream .mui-stream-hidden{
		display: none !important;
	}
</style>
<!--页面主结构开始-->
<div id="app" class="mui-views">
	<div class="mui-view">
		<div class="mui-navbar">
		</div>
		<div class="mui-pages">
		</div>
	</div>
</div>
<!--页面主结构结束-->
<!--单页面开始-->
<div id="setting" class="mui-page">
	<div class="mui-navbar-inner mui-bar mui-bar-nav" >
		<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
		</button>
		<h1 class="mui-center mui-title">预立项</h1>
	</div>
	<!--页面主内容区开始-->
	<div class="mui-page-content">
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<div class="page__bd">
					<div class="weui-cells__title">项目基本信息</div>
					<div class="weui-cells">
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>项目</p>
							</div>
							<div class="weui-cell__ft">${oepro.name}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>处理状态</p>
							</div>
							<div class="weui-cell__ft">${fns:getDictLabel(oepro.status,'oa_eos_pro_status' , '')}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>预立项编号</p>
							</div>
							<div class="weui-cell__ft"><c:if test="${oepro.status<3}">未定义</c:if><c:if test="${oepro.status>=3}">${oepro.paNumber}</c:if></div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>责任销售</p>
							</div>
							<div class="weui-cell__ft">${oepro.personLiableUser.name}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>客户名称</p>
							</div>
							<div class="weui-cell__ft">${oepro.customerName}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>客户名称</p>
							</div>
							<div class="weui-cell__ft">${oepro.customerName}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>客户方负责人</p>
							</div>
							<div class="weui-cell__ft">${oepro.customerUser}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>联系方式</p>
							</div>
							<div class="weui-cell__ft">${oepro.customerContactInformation}</div>
						</div>
					</div>
					<div class="weui-cells__title">详细信息</div>
					<div class="weui-cells">
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>项目类型</p>
							</div>
							<div class="weui-cell__ft">${fns:getDictLabel(oepro.proType,'eos_type',"" )}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>定位</p>
							</div>
							<div class="weui-cell__ft">${oepro.proLocation}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>资金预算</p>
							</div>
							<div class="weui-cell__ft">${fns:getDictLabel(oepro.proCapitalSource,'eos_source',"" )}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>周期</p>
							</div>
							<div class="weui-cell__ft">${fns:getDictLabel(oepro.proCycle,'eos_cycle',"" )}</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>项目预估金额</p>
							</div>
							<div class="weui-cell__ft">${oepro.estimatedAmount}&nbsp;元</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>毛利率百分比</p>
							</div>
							<div class="weui-cell__ft">${oepro.customerContactInformation}%</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>前期投入估算</p>
							</div>
							<div class="weui-cell__ft">${oepro.inputEstimation}&nbsp;元</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>前期工作量估算</p>
							</div>
							<div class="weui-cell__ft">${oepro.workload}&nbsp;人/天</div>
						</div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<p>项目主要交付内容</p>
							</div>
							<div class="weui-cell__ft">${oepro.proContent}</div>
						</div>
					</div>
					<div class="weui-cells__title">项目支持人员</div>
					<div class="weui-cells">
						<div class="weui-grids">
							<c:forEach items="${oepro.users}" var="user">
								<a href="javascript:;" class="weui-grid">
									<div class="weui-grid__icon">
										<img src="${user.wxUsers.avatar}/100" alt="">
									</div>
									<p class="weui-grid__label">${user.wxUsers.name}</p>
								</a>
							</c:forEach>
						</div>
					</div>
					<a href="#selectuser">abcdd</a>
					<c:forEach items="${flowmap}" var="flow" varStatus="index">
						<div class="weui-cells__title"><c:if test="${index.index==0}">总经理审批</c:if></div>
						<div class="weui-panel weui-panel_access">
							<div class="weui-panel__bd">
								<c:forEach items="${flow.value}" var="item">
									<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
										<c:if test="${item.status==1}"><i class="badge weui-icon-success"></i></c:if>
										<c:if test="${item.status==-1}"><i class="weui-icon-cancel"></i></c:if>
										<div class="weui-media-box__hd">
											<img class="weui-media-box__thumb" src="${item.user.wxUsers.avatar}/100" alt="">
										</div>
										<div class="weui-media-box__bd">
											<h4 class="weui-media-box__title">${item.user.wxUsers.name}</h4>
											<p class="weui-media-box__desc">${item.content}</p>
										</div>
									</a>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
					<c:if test="${flow.status!=null}">
						<div class="weui-cells__title">意见建议</div>
						<div class="weui-cells">
							<div class="weui-cell">
								<div class="weui-cell__bd">
									<textarea class="weui-textarea" id="content" placeholder="请输入文本" rows="3"></textarea>
									<div class="weui-textarea-counter"><span>0</span>/200</div>
								</div>
							</div>
						</div>
						<div class="weui-btn-area">
							<a href="javascript:;" class="weui-btn <c:if test="${oepro.status!=2}"> disabled</c:if> weui-btn_primary" oeproid="${oepro.id}" flowid="${flow.id}"  id="showIOSDialog">同意</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!--页面主内容区结束-->
</div>
<!--页面主结构结束-->
<!--单页面开始-->
<div id="selectuser" class="mui-page">
	<div class="mui-navbar-inner mui-bar mui-bar-nav" id="header">
		<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
			<span class="mui-icon mui-icon-left-nav"></span>
		</button>
		<h1 class="mui-center mui-title">选择用户</h1>
		<a onclick="selectValues()" id="done" class="mui-btn mui-btn-link mui-pull-right mui-btn-blue mui-disabled">完成</a>
	</div>
	<!--页面主内容区开始-->
	<div class="mui-page-content">
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<div class="mui-content">
					<div id='userlist' class="mui-indexed-list">
						<div class="mui-indexed-list-search mui-input-row mui-search">
							<input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索用户">
						</div>
						<div class="mui-indexed-list-bar">
							<a>A</a>
							<a>B</a>
							<a>C</a>
							<a>D</a>
							<a>E</a>
							<a>F</a>
							<a>G</a>
							<a>H</a>
							<a>I</a>
							<a>J</a>
							<a>K</a>
							<a>L</a>
							<a>M</a>
							<a>N</a>
							<a>O</a>
							<a>P</a>
							<a>Q</a>
							<a>R</a>
							<a>S</a>
							<a>T</a>
							<a>U</a>
							<a>V</a>
							<a>W</a>
							<a>X</a>
							<a>Y</a>
							<a>Z</a>
						</div>
						<div class="mui-indexed-list-alert"></div>
						<div class="mui-indexed-list-inner">
							<div class="mui-indexed-list-empty-alert">没有数据</div>
							<ul class="mui-table-view">
								<c:forEach items="${gsons}" var="user">
									<li data-group="${user.key}" class="mui-table-view-divider mui-indexed-list-group">${user.key}</li>
									<c:forEach items="${user.value}" var="item">
										<c:if test="${item.user.id!=null}">
											<li data-value="${item.pinyin}" data-tags="${item.pinyin}"  class="mui-table-view-cell mui-indexed-list-item mui-checkbox mui-left"><img src="${item.avatar}/100" class="mui-left" width="40" height="40"><input type="checkbox" avatar="${item.avatar}" userid="${item.user.id}" username="${item.name}"/>&nbsp;&nbsp;${item.name}</li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--页面主内容区结束-->
</div>


<script src="${ctxStaticTheme}/mui/js/mui.view.js"></script>
<script type="application/javascript">
    mui.init();
    //初始化单页view
    var viewApi = mui('#app').view({
        defaultPage: '#setting'
    });
    //初始化单页的区域滚动
    mui('.mui-scroll-wrapper').scroll();
    var view = viewApi.view;
    (function($) {
        //处理view的后退与webview后退
        var oldBack = $.back;
        $.back = function() {
            if (viewApi.canBack()) { //如果view可以后退，则执行view的后退
                viewApi.back();
            } else { //执行webview后退
                oldBack();
            }
        };
        //监听页面切换事件方案1,通过view元素监听所有页面切换事件，目前提供pageBeforeShow|pageShow|pageBeforeBack|pageBack四种事件(before事件为动画开始前触发)
        //第一个参数为事件名称，第二个参数为事件回调，其中e.detail.page为当前页面的html对象
        view.addEventListener('pageBeforeShow', function(e) {
            inituser();
            //				console.log(e.detail.page.id + ' beforeShow');
        });
        view.addEventListener('pageShow', function(e) {
            //				console.log(e.detail.page.id + ' show');
        });
        view.addEventListener('pageBeforeBack', function(e) {
            //				console.log(e.detail.page.id + ' beforeBack');
        });
        view.addEventListener('pageBack', function(e) {
            //				console.log(e.detail.page.id + ' back');
        });
    })(mui);
    var header = document.getElementById('header');
    var list = document.getElementById('userlist');
    var done = document.getElementById('done');
    function inituser(){
        //calc hieght
        list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
        //create
        window.indexedList = new mui.IndexedList(list);
        //done event
        mui('.mui-indexed-list-inner').on('change', 'input', function() {
            var count = list.querySelectorAll('input[type="checkbox"]:checked').length;
            var value = count ? "完成(" + count + ")" : "完成";
            done.innerHTML = value;
            if (count) {
                if (done.classList.contains("mui-disabled")) {
                    done.classList.remove("mui-disabled");
                }
            } else {
                if (!done.classList.contains("mui-disabled")) {
                    done.classList.add("mui-disabled");
                }
            }
        });
    }
    var user;
    function selectValues(){
        user=[];
        var checkboxArray = [].slice.call(list.querySelectorAll('input[type="checkbox"]'));
        var checkedValues = [];
        checkboxArray.forEach(function(box) {
            if (box.checked) {
                user.push({userid:$(box).attr("userid"),avatar:$(box).attr("avatar"),username:$(box).attr("username")});
                checkedValues.push(box.parentNode.innerText);
            }
        });
        if (checkedValues.length > 0) {
            
            mui.alert('你选择了: ' + checkedValues);
        } else {
            mui.alert('你没选择任何用户');
        }
    }
    $('#showIOSDialog').on('click', function(){
        var eosid=$(this).attr("oeproid");
        var flowid=$(this).attr("flowid");
        var btnArray = ['否', '是'];
        mui.confirm('确认通过预立项审核吗？', '系统提示', btnArray, function(e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/audit",{eosid:eosid,flowid:flowid,content:$("#content").val()},function(data){
                    data=JSON.parse(data);
                    if(data.success){
                        var $toast = $('#toast');
                        $toast.fadeIn(100);
                        setTimeout(function () {
                            $toast.fadeOut(100);
                            location.reload();
                        }, 2000);
                    }else{
                        $("#showIOSDialog").removeClass("disabled")
                        $("#iosDialog2").find(".weui-dialog__bd").html(data.message);
                        $("#iosDialog2").fadeIn(200);
                    }
                })
            } else {
                info.innerText = 'MUI没有得到你的认可，继续加油'
            }
        })
    });
</script>
</body>
</html>