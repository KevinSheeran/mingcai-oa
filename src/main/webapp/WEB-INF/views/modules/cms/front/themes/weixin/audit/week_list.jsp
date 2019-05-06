<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>明材办公-预立项审批</title>
    <meta name="decorator" content="cms_default_${site.theme}" />
    <meta name="description" content="JeeSite ${site.description}" />
    <meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>
<style>
    .back{
        width: 40px;
        height: 40px;
        display: inline-block;
        text-align: center;
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 25px;
        background-clip: padding-box;
        position: fixed;
        opacity: 0.8;
        right: 30px;
        z-index: 99999;
    }
    .back .icon{
        margin-top: 8px;
    }
    .back.proe{
        bottom: 200px;
    }
    .back.next{
        bottom: 150px;
    }
</style>
<a class="back proe" href="${oa}/weixin/presentationList?index=${index-1}">
    <span class="icon mui-icon mui-icon-arrowthinup"></span>
</a>
<a class="back next" href="${oa}/weixin/presentationList?index=${index+1}">
    <span class="icon mui-icon mui-icon-arrowthindown"></span>
</a>
<div class="mui-content">
    <div id='list' class="mui-indexed-list">
        <div class="mui-navbar-inner mui-bar mui-bar-nav" id="header" style="position: relative;">
            <h1 class="mui-center mui-title">${date}</h1>
        </div>
        <div class="mui-indexed-list-search mui-input-row mui-search">
            <input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索">
        </div>
        <div class="mui-indexed-list-bar" style="display: none">
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
            <ul class="mui-table-view mui-table-view-chevron" >
                <c:forEach items="${list}"  var="item">
                    <li  data-value="${item.pro.proNumber}"
                         data-tags="${item.createBy.name}${item.pro.name}"
                         class="mui-table-view-cell   mui-media" style="padding:0 10px 0 0">
                    <div class="weui-form-preview" >
                        <div class="weui-form-preview__hd">
                            <div class="weui-form-preview__item">
                                <label class="weui-form-preview__label a">${item.createBy.name}</label>
                            </div>
                        </div>
                        <div class="weui-form-preview__bd">
                            <div class="weui-form-preview__item">
                                <label class="weui-form-preview__label">项目</label>
                                <span class="weui-form-preview__value b">${item.pro.name}</span>
                            </div>
                            <div class="weui-form-preview__item">
                                <label class="weui-form-preview__label">编号</label>
                                <span class="weui-form-preview__value c">${item.pro.proNumber}</span>
                            </div>
                            <div class="weui-form-preview__item">
                                <label class="weui-form-preview__label">提交时间</label>
                                <span class="weui-form-preview__value b"><fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd"/></span>
                            </div>
                        </div>
                        <div class="weui-form-preview__ft">
                            <a class="weui-form-preview__btn weui-form-preview__btn_default" href="${oa}/weixin/user_weekInfo?id=${item.id}">查看详情</a>
                        </div>
                    </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript" charset="utf-8">
    mui.init();
    mui.ready(function () {
        var list = document.getElementById('list');
        //calc hieght
        list.style.height = (document.body.offsetHeight ) + 'px';
        //create
        window.indexedList = new mui.IndexedList(list);
    });


</script>
<script type="text/javascript">
</script>
</body>
</html>