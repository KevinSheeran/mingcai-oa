<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>明材办公-工时汇总</title>
    <meta name="decorator" content="cms_default_${site.theme}" />
    <meta name="description" content="JeeSite ${site.description}" />
    <meta name="keywords" content="JeeSite ${site.keywords}" />
</head>
<body>
<div class="page">
    <div class=" page__bd  mui-table-view mui-table-view-chevron" style="height: 100%;background: #fff;" id="body">
        <div class="weui-form-preview" >
            <div class="weui-form-preview__hd">
                <div class="weui-form-preview__item">
                    <label class="weui-form-preview__label a">${pro.name}</label>
                </div>
            </div>
            <div class="weui-form-preview__bd">
            <div class="weui-form-preview__item">
                <label class="weui-form-preview__label">本周</label>
                <span class="weui-form-preview__value b"> ${time}</span>
            </div>
            </div>
            <form id="form" >
                <input type="hidden" name="times" value="${time}">
                <input type="hidden" name="id" value="${item.id}">
                <input type="hidden" name="proId" value="${proid}">
                <input type="hidden" name="type" value="${type}">
                <input type="hidden" name="userType" value="${userType}">
            <div class="mui-input-group">
                <c:if test="${userType==1}">
                <div class="mui-input-row">
                    <label>售前</label>
                    <input type="number" name="psTotal" placeholder="输入工时，小时"  value="${item.psTotal}" class="numbers">
                </div>
                </c:if>
                <c:if test="${userType==2}">
                <div class="mui-input-row">
                    <label>实施</label>
                    <input type="number" name="saleTotal" placeholder="输入工时，小时" value="${item.saleTotal}" class="numbers">
                </div>
                </c:if>
                <c:if test="${userType==3}">
                <div class="mui-input-row">
                    <label>研发</label>
                    <input type="number" name="rdTotal" placeholder="输入工时，小时" value="${item.rdTotal}" class="numbers">
                </div>
                </c:if>
            </div>
            </form>
        </div>
        <div class="weui-btn-area">
            <a href="javascript:;" class="weui-btn weui-btn_primary" onclick="submit(this)">保存</a>
        </div>
    </div>
</div>
<script type="application/javascript">
    var submit=function(obj){
        $(obj).addClass("disabled")
        var btnArray = ['否', '是'];
        mui.confirm('确认提交吗？', '系统提示', btnArray, function(e) {
            if (e.index == 1) {
                $.post("${oa}/weixin/summarysave",$("#form").serialize(),function(data){
                    data=JSON.parse(data);
                    if(data.success){
                        mui.toast('保存完成！');
                        setTimeout(function () {
                            location.href="${oa}/weixin/summaryList?proid=${proid}&type=${type}";
                        }, 2000);
                    }else{
                        $(obj).removeClass("disabled")
                        mui.alert(data.message, '系统提示', function() {
                        });
                    }
                })
            } else {
                $(obj).removeClass("disabled")
            }
        })


    }

</script>
</body>
</html>