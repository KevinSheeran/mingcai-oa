<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<html>
<head>
    <title>项目列表</title>
    <meta name="decorator" content="cms_default_${site.theme}"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
</head>
<body>
<style>
    .weui-grid{
        width: 33.3%;
        border:0px;
        padding: 5px 0px;
    }
    .mui-search .mui-placeholder{
        line-height: 40px;
    }
    .mui-search .mui-placeholder{
        height: 40px;
    }
    .mui-table-view-chevron .mui-table-view-cell{
        padding-right: 65px;
    }
    .mui-table-view-cell:after{
        left:0px;
    }
    h4{
        font-weight: 500;
        font-size: 16px;
    }
    .mui-navigate-right:after, .mui-push-right:after{
        font-size:24px;
    }
</style>
<div class="mui-content">
    <div id='list' class="mui-indexed-list">
        <div class="mui-indexed-list-search mui-input-row mui-search">
            <input type="search" style="height: 40px;" onclick="getblur(this)" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索项目">
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
                <c:forEach items="${map}" var="item">
                    <c:set var="type" value=""/>
                    <c:choose> <c:when test="${item.type==1}"><c:set var="type" value="ylx"/></c:when><c:when test="${item.type==2}"><c:set var="type" value="lx"/></c:when><c:when test="${item.type==3}"><c:set var="type" value="flx"/></c:when>  </c:choose>
                    <li  data-value="<c:choose> <c:when test="${item.type==1}">${item.paNumber}</c:when><c:when test="${item.type==2||item.type==3}">${item.proNumber}</c:when> </c:choose>"
                        data-tags="<c:choose> <c:when test="${item.type==1}">预立项</c:when><c:when test="${item.type==2}">已立项</c:when> <c:when test="${item.type==3}">非销售</c:when></c:choose>"
                        class="mui-table-view-cell   mui-media" >
                                <c:if test="${summary!=null}">
                                <a  class="mui-navigate-right" href="${oa}/weixin/summaryList?proid=${item.id}&type=${type}">
                                </c:if>
                                <c:if test="${summary==null}">
                                    <c:choose> <c:when test="${item.type==1}"> <a class="mui-navigate-right" href="${oa}/weixin/auditpro?id=${item.id}"></c:when>
                                    <c:when test="${item.type==2}"> <a class="mui-navigate-right" href="${oa}/weixin/auditprostart?id=${item.id}"></c:when>
                                    <c:when test="${item.type==3}"> <a class="mui-navigate-right" href="${oa}/weixin/auditproinfo?id=${item.id}"></c:when>
                                    </c:choose>
                                </c:if>
                                <div class="mui-media-body">
                                        <h4>${item.name}</h4>
                                        <p class='mui-ellipsis'>编号:<c:choose> <c:when test="${item.type==1}">${item.paNumber}</c:when><c:when test="${item.type==2||item.type==3}">${item.proNumber}</c:when>  </c:choose></p>
                                        <p class='mui-ellipsis'> 类型:<c:choose> <c:when test="${item.type==1}">预立项项目</c:when><c:when test="${item.type==2}">立项项目</c:when>  <c:when test="${item.type==3}">非销售项目</c:when> </c:choose></p>
                                        <c:if test="${summary!=null&&item.oaEosProTimetotal.id!=null}">
                                                <div class="weui-cells" style="margin-top:10px;">
                                                    <div class="weui-grids">
                                                            <div  class="weui-grid" style="width: 25%">
                                                                <div class="weui-grid__label">售前</div>
                                                                <p class="weui-grid__label">${item.oaEosProTimetotal.psTotal}小时</p>
                                                            </div>
                                                            <div  class="weui-grid" style="width: 25%">
                                                                <div class="weui-grid__label">实施</div>
                                                                <p class="weui-grid__label">${item.oaEosProTimetotal.saleTotal}小时</p>
                                                            </div>
                                                            <div  class="weui-grid" style="width: 25%">
                                                                <div class="weui-grid__label">研发</div>
                                                                <p class="weui-grid__label">${item.oaEosProTimetotal.rdTotal}小时</p>
                                                            </div>
                                                        <div  class="weui-grid" style="width: 25%">
                                                            <div class="weui-grid__label">总时</div>
                                                            <p class="weui-grid__label">${item.total}小时</p>
                                                        </div>
                                                    </div>
                                                </div>
                                        </c:if>
                                </div>
                                </a>
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
function getblur(obj){
    $(obj).focus();
}

</script>
</body>
</html>
