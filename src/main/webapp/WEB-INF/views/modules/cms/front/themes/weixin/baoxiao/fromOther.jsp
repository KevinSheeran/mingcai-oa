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
    报销详情
</div>
<div class="mui-card">
    <div class="mui-card-content">
        <div class="mui-input-row">
            <div class="weui-cell weui-cell_access" onclick="findid(this)">
                <div class="weui-cell__bd">
                    <span style="vertical-align: middle" class="proname">项目名字</span>
                </div>
                <div class="weui-cell__ft f">请选择</div>
                <input type="hidden" value="" name="fid" class="fid">
            </div>
        </div>
        <input type="hidden" name="zid" value="0" class="zid">
        <div class="mui-input-row">
            <div class="weui-cell weui-cell_access" onclick="findjd(this)">
                <div class="weui-cell__bd">
                    <span style="vertical-align: middle" >报销阶段</span>
                </div>
                <div class="weui-cell__ft z1">请选择</div>
                <input type="hidden" name="jd" value="0" class="zid">
            </div>
        </div>
        <div class="mui-input-row">
            <div class="weui-cell weui-cell_access" onclick="findlx(this)">
                <div class="weui-cell__bd">
                    <span style="vertical-align: middle" class="timeName">报销类型</span>
                </div>
                <div class="weui-cell__ft z">请选择</div>
                <input type="hidden" name="lx" value="0" class="zid">
            </div>
        </div>
        <div class="mui-input-row">
            <div class="mui-input-row weui-cell__bd">
                <label>报销金额</label>
                <input type="text" name="cost" class="mui-input-clear " style="" onblur="numbers(this)"
                       placeholder="请输入报销金额">
            </div>
        </div>
        <div class="mui-input-row">
            <div class="mui-input-row weui-cell__bd">
                <label>费用明细</label>
                <textarea class="weui-textarea fymx"  style="height: 100px;" placeholder="例如XXXX" name="fymx" cols="18" rows="2" ></textarea>
            </div>
        </div>
        <%--<div class="mui-card">
                               <div class="weui-cells">
                                   <div class="weui-cells weui-cells_form">
                                       <div class="weui-cell">
                                           <div class="weui-cell__bd">
                                               <div class="weui-uploader">
                                                   <div class="weui-uploader__hd">
                                                       <p class="weui-uploader__title">附件</p>
                                                   </div>
                                                   <div class="weui-uploader__bd">
                                                       <div class="weui-uploader__input-box">
                                                           <input class="weui-uploader__input uploaderInput"
                                                                  type="file" name="files" multiple="multiple"
                                                                  onchange="upload(this,this.files)"/>
                                                       </div>
                                                   </div>
                                               </div>
                                           </div>
                                       </div>
                                   </div>
                               </div>
                           </div>--%>
        <div id="backItems">

        </div>
        <a href="javascript:void(0);" class="weui-cell weui-cell_link">
            <div class="weui-cell__bd tj">添加报销信息</div>
        </a>
    </div>
</div>
