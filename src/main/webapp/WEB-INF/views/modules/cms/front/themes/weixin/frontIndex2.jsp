<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>明材办公-审批</title>
	<meta name="decorator" content="cms_default_${site.theme}" />
	<meta name="description" content="JeeSite ${site.description}" />
	<meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>

<nav class="mui-bar mui-bar-tab">
	<a class="mui-tab-item mui-active" href="#tabbar">
		<span class="mui-icon mui-icon-home"></span>
		<span class="mui-tab-label">首页</span>
	</a>
	<a class="mui-tab-item" href="#tabbar-with-chat">
		<span class="mui-icon-extra mui-icon-extra-prech"><span class="mui-badge">9</span></span>
		<span class="mui-tab-label">个人报销</span>
	</a>
	<a class="mui-tab-item" href="#tabbar-with-contact" >
		<span class="mui-icon-extra mui-icon-extra-dictionary"></span>
		<span class="mui-tab-label">项目</span>
	</a>
	<a class="mui-tab-item" href="#tabbar-with-map">
		<span class="mui-icon mui-icon-gear"></span>
		<span class="mui-tab-label">设置</span>
	</a>
</nav>
<div class="mui-content">
	<div id="tabbar" class="mui-control-content mui-active">
        <div class="page__bd" style="height: 100%;">
            <div class="weui-tab">
                <div class="weui-navbar">
                    <div class="weui-navbar__item weui-bar__item_on" onclick="changeTab(this)" toid="tab1">
                        待审核
                    </div>
                    <div class="weui-navbar__item" onclick="changeTab(this)" toid="tab2">
                        已审核
                    </div>
                </div>
                <div class="weui-tab__panel">
                    <section class="aui-flexView on" id="tab1">
                        <section class="aui-scrollView">
                            <div class="aui-nav-title">项目 </div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/proauditList" class="aui-class-list-item">
                                    <div class="aui-cell-fl">
                                        <img src="${ctxStaticTheme}/images/icon-item001.png" alt="">
                                    </div>
                                    <div class="aui-cell-fr">预立项</div>
                                    <div class="aui-cell-tr"></div>
                                    <span class="weui-badge" style="position: absolute;top: 5px;right: 13px;">8</span>
                                </a>
                                <a href="${oa}/weixin/proauditstartList" class="aui-class-list-item">
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
                            <div class="aui-nav-title">销售类报销 </div>
                            <div class="aui-class-list-box">
                                <a href="javascript:;" class="aui-class-list-item">
                                    <div class="aui-cell-fl">
                                        <img src="${ctxStaticTheme}/images/icon-item003.png" alt="">
                                    </div>
                                    <div class="aui-cell-fr">立项报销</div>
                                    <div class="aui-cell-tr"></div>
                                </a>
                                <a href="javascript:;" class="aui-class-list-item" onclick="addbx()">
                                    <div class="aui-cell-fl">
                                        <img src="${ctxStaticTheme}/images/icon-item004.png" alt="">
                                    </div>
                                    <div class="aui-cell-fr">预立项报销</div>
                                    <div class="aui-cell-tr"></div>
                                </a>
                            </div>
                            <div class="aui-nav-title">非销售类报销 </div>
                            <div class="aui-class-list-box">
                                <a href="javascript:;" class="aui-class-list-item">
                                    <div class="aui-cell-fl">
                                        <img src="${ctxStaticTheme}/images/icon-item003.png" alt="">
                                    </div>
                                    <div class="aui-cell-fr">立项报销</div>
                                    <div class="aui-cell-tr"></div>
                                </a>
                            </div>
                        </section>
                    </section>
                    <section class="aui-flexView "  id="tab2">
                        <section class="aui-scrollView">
                            <div class="aui-nav-title">项目 </div>
                            <div class="aui-class-list-box">
                                <a href="${oa}/weixin/proauditfinishList" class="aui-class-list-item">
                                    <div class="aui-cell-fl">
                                        <img src="${ctxStaticTheme}/images/icon-item001.png" alt="">
                                    </div>
                                    <div class="aui-cell-fr">预立项</div>
                                    <div class="aui-cell-tr"></div>
                                </a>
                                <a href="${oa}/weixin/proauditstartfinishList" class="aui-class-list-item">
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
                            <div class="aui-nav-title">销售类报销 </div>
                            <div class="aui-class-list-box">
                                <a href="javascript:;" class="aui-class-list-item">
                                    <div class="aui-cell-fl">
                                        <img src="${ctxStaticTheme}/images/icon-item003.png" alt="">
                                    </div>
                                    <div class="aui-cell-fr">立项报销</div>
                                    <div class="aui-cell-tr"></div>
                                </a>
                                <a href="javascript:;" class="aui-class-list-item" onclick="addbx()" >
                                    <div class="aui-cell-fl">
                                        <img src="${ctxStaticTheme}/images/icon-item004.png" alt="">
                                    </div>
                                    <div class="aui-cell-fr">预立项报销</div>
                                    <div class="aui-cell-tr">

                                    </div>
                                </a>
                            </div>
                            <div class="aui-nav-title">非销售类报销 </div>
                            <div class="aui-class-list-box">
                                <a href="javascript:;" class="aui-class-list-item">
                                    <div class="aui-cell-fl">
                                        <img src="${ctxStaticTheme}/images/icon-item003.png" alt="">
                                    </div>
                                    <div class="aui-cell-fr">立项报销</div>
                                    <div class="aui-cell-tr"></div>
                                </a>
                            </div>
                        </section>

                    </section>
                </div>
            </div>
        </div>
	</div>
	<div id="tabbar-with-chat" class="mui-control-content"  style="position: relative;">
        <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
            <div class="mui-scroll">
                <ul class="mui-table-view mui-table-view-chevron uls">

                </ul>
            </div>
        </div>
	</div>

	<div id="tabbar-with-contact" class="mui-control-content">
        <div class="mui-card">
            <div class="mui-indexed-list-search mui-input-row mui-search">
                <input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="输入项目编号或名称查询">
            </div>
        </div>
        <div id="proList">
            <div class="mui-card">
                <div class="mui-card-header">页眉</div>
                <div class="mui-card-content">
                    <div class="mui-card-content-inner">
                        <p>Posted on January 18, 2016</p>
                        <p style="color: #333;">这里显示文章摘要，让读者对文章内容有个粗略的概念...</p>
                    </div>
                </div>
                <div class="mui-card-footer">
                    <a class="mui-card-link">Like</a>
                    <a class="mui-card-link">Read more</a>
                </div>
            </div>
        </div>
    </div>
	<div id="tabbar-with-map" class="mui-control-content">
		<div class="title">这是div模式选项卡中的第4个子页面，该页面展示一个常见的设置示例.</div>
		<ul class="mui-table-view">
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right">
					新消息通知
				</a>
			</li>
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right">
					隐私
				</a>
			</li>
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right">
					通用
				</a>
			</li>
		</ul>
		<ul class="mui-table-view" style="margin-top: 25px;">
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right">
					关于mui
				</a>
			</li>
		</ul>
		<ul class="mui-table-view" style="margin-top: 25px;">
			<li class="mui-table-view-cell">
				<a style="text-align: center;color: #FF3B30;">
					退出登录
				</a>
			</li>
		</ul>
	</div>
</div>

<div class="page" id="model" style="display: none" >
    <div class="page__bd">
        <div class="weui-form-preview">
            <div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label ">报销金额</label>
                    <em class="weui-form-preview__value a"> </em>
                </div>
            </div>
            <div class="weui-form-preview__bd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">项目编号</label>
                    <span class="weui-form-preview__value b"></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">项目名字</label>
                    <span class="weui-form-preview__value c"></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">报销类型</label>
                    <span class="weui-form-preview__value d"></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">报销原因</label>
                    <span class="weui-form-preview__value e"></span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">申请日期</label>
                    <span class="weui-form-preview__value f"> </span>
                </div>
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label">状态</label>
                    <span class="weui-form-preview__value g"></span>
                </div>
            </div>
            <div class="weui-form-preview__ft">
                <button class="weui-form-preview__btn weui-form-preview__btn_default h " path="" onclick="delbx(this)">撤销申请</button>
                <button  path="" onclick="deteils(this)" class="weui-form-preview__btn weui-form-preview__btn_primary i">查看详情</button>
            </div>
        </div>
        <br>
    </div>
</div>

<script>
    $(".mui-scroll-wrapper").height($("body").height()-$("nav.mui-bar").height());
    mui.init({
        pullRefresh: {
            container: '#pullrefresh',
             up: {
                auto:true,
                contentrefresh: '正在加载...',
                callback: pullupRefresh
            }
        }
    });
    function changeTab(obj){
        var $this=$(obj);
        $("#"+$this.attr("toid")).addClass('on').siblings('.aui-flexView').removeClass('on');
        $this.addClass('weui-bar__item_on').siblings('.weui-bar__item_on').removeClass('weui-bar__item_on');
    }
    var count=0;
    fimdlist=undefined;
    //pullupRefresh();
    function pullupRefresh(){
        $.get("${oa}/weixin/bx/tablejson",{},function(data){
            data=JSON.parse(data);
            data.count
            fimdlist=data.mapList;
        })
        setTimeout(function() {
            mui('#pullrefresh').pullRefresh().endPullupToRefresh((++count > 2)); //参数为true代表没有更多数据了。
            var table = document.body.querySelector('.mui-table-view');
            var cells = document.body.querySelectorAll('.mui-table-view-cell1');
            /* var newCount = cells.length>从0开始?一次加载几条:第一次加载多少条;//首次加载20条，满屏 */
            var newCount = cells.length>0?1:2;//首次加载20条，满屏
            var model=$("#model").clone();
            for (var i = cells.length, len = i + newCount; i < len; i++) {
                item= fimdlist[i];
                model.find(".a").html("￥"+item.cost);
                model.find(".b").html(item.PaNumber);
                model.find(".c").html(item.proName);
                model.find(".d").html(item.proitemname);
                model.find(".e").html(item.content);
                model.find(".f").html(item.crdate);
                model.find(".g").html(item.state);
                if (item.state != "待审核"){
                    model.find(".h").remove();
                }else {
                    model.find(".h").attr("path",item.id);
                }
                model.find(".i").attr("path","/oa/weixin/bx/Details?id="+item.id)
                $(".uls").append("<li class='mui-table-view-cell1'>"+model.html()+"</li>");
            }
        }, 1500);
    }

    function addbx() {
        location.href="${oa}/weixin/bx/baoxiaoadd";
    }
    function  deteils(obj) {
        $this=$(obj);
      location.href=$this.attr("path")
    }
    function delbx(obj) {
        $this=$(obj);
        var btnArray = ['否', '是'];
        mui.confirm('确认撤销报销申请吗？', '系统提示', btnArray, function(e){
            if (e.index==1) {

           $.get("${oa}/weixin/bx/delbx/","eosid="+$this.attr("path"),function (data) {
               data=JSON.parse(data);
               if(data.success){
                   mui.toast('撤销成功！');
                   setTimeout(function () {
                       location.reload();
                   }, 2000);
               }else{
                   $("#showIOSDialog").removeClass("disabled")
                   mui.alert(data.message, '系统提示', function() {
                   });
               }
           })
            }else {
                $($this).removeClass("disabled")
            }
        })
    }
</script>
</body>
</html>