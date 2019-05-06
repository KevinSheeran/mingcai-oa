<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp" %>
<style>
    .weui-cell{
        padding: 13px 10px;
    }
    .mui-input-row label {
        font-family: 'Helvetica Neue', Helvetica, sans-serif;
        line-height: 1.1;
        float: left;
        width: 35%;
        padding: 10px 10px;
    }
</style>
<div class="title">
    采购详情
</div>
    <div class="mui-card">
            <div class="mui-card-content">
                <a class="weui-cell weui-cell_access" onclick="findid(this)" >
                    <div class="weui-cell__bd">
                        <span style="vertical-align: middle" class="proname">项目名字</span>
                    </div>
                    <div class="weui-cell__ft f" >请选择</div>
                    <input type="hidden" value=""  name="fid" class="fid">
                </a>
                <div class="mui-input-row">
                    <div class="weui-cell weui-cell_access" onclick="findzid(this)" >
                        <div class="weui-cell__bd">
                            <span style="vertical-align: middle">子项目名</span>
                        </div>
                        <div class="weui-cell__ft z" >请选择</div>
                        <input type="hidden"  name="zid" class="zid">
                    </div>
                </div>
                <div class="mui-input-row">
                        <div class="mui-input-row weui-cell__bd">
                            <label>采购金额</label>
                            <input type="text"  name="cost"  class="mui-input-clear " style="" onblur="numbers(this)" placeholder="请输入报销金额">
                        </div>
                </div>
                <div class="mui-input-row">
                    <div class="mui-input-row weui-cell__bd">
                        <label>费用明细</label>
                        <textarea class="weui-textarea fymx"  style="height: 100px;" placeholder="例如XXXX" name="fymx" cols="18" rows="2" ></textarea>
                    </div>
                </div>
                <div id="backItems">

                </div>
                <a href="javascript:void(0);" class="weui-cell weui-cell_link">
                    <div class="weui-cell__bd tj">添加采购信息</div>
                </a>
        </div>
    </div>
