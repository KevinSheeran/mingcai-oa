<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>明材办公-审批</title>
    <meta name="decorator" content="cms_default_${site.theme}"/>
    <meta name="description" content="JeeSite ${site.description}"/>
    <meta name="keywords" content="JeeSite ${site.keywords}"/>
</head>
<body>
    <div id="pullrefresh" class="mui-content mui-scroll-wrapper">
        <div class="mui-scroll ">
            <div class="page">

                <div class=" page__bd  mui-table-view mui-table-view-chevron uls" style="height: 100%;background: #fff;" >
                </div>
            </div>
        </div>
    </div>
    <div id="model">
    <div class="weui-form-preview"  style="display: none">
        <div class="weui-form-preview__bd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">报销金额</label>
                <span class="weui-form-preview__value a"></span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label c1">项目名字</label>
                <span class="weui-form-preview__value c"></span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">报销人</label>
                <span class="weui-form-preview__value d"></span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">申请日期</label>
                <span class="weui-form-preview__value f"> </span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">状态</label>
                <span class="weui-form-preview__value g"></span>
            </div>
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">报销类型</label>
                <span class="weui-form-preview__value type"></span>
            </div>
        </div>
        <div class="weui-form-preview__ft">
            <button class="weui-form-preview__btn weui-form-preview__btn_default h " path="" onclick="delbx(this)">
                撤销申请
            </button>
            <button path="" onclick="deteils(this)"
                    class="weui-form-preview__btn weui-form-preview__btn_primary i">查看
            </button>
        </div>
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
        var pageNo=1;

        var  pageSize=5;
        var types ="${type}";
        //pullupRefresh();
        function pullupRefresh(){
            $.get("${oa}/weixin/two/selby",{"pageNo":pageNo,"pageSize":pageSize,"types":types},function(data){
                var fimdlist=data.list;
               var  total=data.count;
                mui('#pullrefresh').pullRefresh().endPullupToRefresh((pageNo*pageSize >=data.count)); //参数为true代表没有更多数据了。
                pageNo++;
                for(var j=0;j<fimdlist.length;j++){
                    var item= fimdlist[j].map;
                    for (var i=0;i<item.length;i++) {
                        var model=$($("#model").html());
                        model.find(".a").html("￥"+item[i].cost);
                        model.find(".c").html(item[i].proName);
                        if(item[i].type=='bm'){
                            model.find(".c1").html("部门名")
                            model.find(".type").html("部门报销")
                        }else if(item[i].type=='ylx'){
                            model.find(".type").html("预立项报销")
                        }else if(item[i].type=='lx'){
                            model.find(".type").html("立项报销")
                        }else if(item[i].type=='fxs'){
                            model.find(".type").html("非销售立项报销")
                        }
                        model.find(".d").html(item[i].user.name);
                        model.find(".f").html(item[i].crdate);
                        model.find(".g").html(item[i].state);
                        model.find(".i").attr("path","/oa/weixin/two/Details?idss="+item[i].id+"&type="+item[i].type+"&sh=1")
                        model.find(".i").text("查看详情")
                        if (item[i].state != "待审核") {
                            model.find(".h").remove();
                        } else {
                            model.find(".h").attr("path", item[i].id);
                        }
                        $(".uls").append(model.fadeIn(500));
                    }
                }
            })
        }

        function  deteils(obj) {
            $this=$(obj);
            location.href=$this.attr("path")
        }
        function delbx(obj) {
            $this = $(obj);
            var btnArray = ['否', '是'];
            mui.confirm('确认撤销报销申请吗？', '系统提示', btnArray, function (e) {
                if (e.index == 1) {

                    $.get("${oa}/weixin/two/delbx/", "eosid=" + $this.attr("path"), function (data) {
                        data = JSON.parse(data);
                        if (data.success) {
                            mui.toast('撤销成功！');
                            setTimeout(function () {
                                location.reload();
                            }, 2000);
                        } else {
                            $("#showIOSDialog").removeClass("disabled")
                            mui.alert(data.message, '系统提示', function () {
                            });
                        }
                    })
                } else {
                    $($this).removeClass("disabled")
                }
            })
        }
    </script>
</body>
</html>
