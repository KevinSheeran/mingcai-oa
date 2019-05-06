<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
    request.setAttribute("vEnter", "\n");
    //奇怪的是这一行我用 <c:set var="vEnter" value="\n" scope="request"/>
    //都没用,估计是set标签里对value值处理了一下,没jstl的源码,不清楚JSTL是怎么处理的.
%>
<html>
<head>
	<title>周任务管理</title>
	<meta name="decorator" content="default"/>
    <%@include file="/WEB-INF/views/include/treeview.jsp" %>
    <style type="text/css">
        .ztree {overflow:auto;margin:0;_margin-top:10px;padding:10px 0 0 10px;}
    </style>
    <style>
        .STYLE1 {
            font-size: 20px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<div id="content" class="row-fluid">
    <div id="left" class="accordion-group" >
        <div class="accordion-heading">
            <a class="accordion-toggle">立项项目<i class="icon-refresh pull-right" onclick="refreshTree();"></i></a>
        </div>
        <div id="ztree" class="ztree"></div>
    </div>
    <div id="openClose" class="close">&nbsp;</div>
    <div id="right">
        <iframe id="officeContent" src="${ctx}/oa/eos/oaEosProPresentation" width="100%" height="91%" frameborder="0"></iframe>
    </div>
</div>
<script type="text/javascript">
    var setting = {view:{selectedMulti:false,dblClickExpand:false},check:{enable:"",nocheckInherit:true},
        async:{enable:true,url:"${ctx}/oa/eos/oaEosProPresentation/treeData",autoParam:["id=officeId"]},
        data:{simpleData:{enable:true}},callback:{
            onClick:function(event, treeId, treeNode){
                var id = treeNode.id == '0' ? '' :treeNode.id;
                id=id.replace("u_","");
                if (treeNode.isParent){
                    return;
                }
                $('#officeContent').attr("src","${ctx}/oa/eos/oaEosProPresentation?eosId="+id);
            },onCheck: function(e, treeId, treeNode){
                return false;
            },onAsyncSuccess: function(event, treeId, treeNode, msg){
                // var nodes = tree.getNodesByParam("pId", treeNode.id, null);
                // for (var i=0, l=nodes.length; i<l; i++) {
                //     try{tree.checkNode(nodes[i], treeNode.checked, true);}catch(e){}
                //     //tree.selectNode(nodes[i], false);
                // }
                // selectCheckNode();
            },onDblClick: function(){//<c:if test="${!checked}">
                //top.$.jBox.getBox().find("button[value='ok']").trigger("click");
                //$("input[type='text']", top.mainFrame.document).focus();//</c:if>
            }
        }
    };
    function refreshTree(){
        $.getJSON("${ctx}/oa/eos/oaEosProPresentation/treeData?isAll=false&type=3",function(data){
            $.fn.zTree.init($("#ztree"), setting, data);
        });
    }
    refreshTree();
    var leftWidth = 400; // 左侧窗口大小
    var htmlObj = $("html"), mainObj = $("#main");
    var frameObj = $("#left, #openClose, #right, #right iframe");
    function wSize(){
        var strs = getWindowSize().toString().split(",");
        htmlObj.css({"overflow-x":"hidden", "overflow-y":"hidden"});
        mainObj.css("width","auto");
        frameObj.height(strs[0] - 5);
        var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
        $("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
        $(".ztree").width(leftWidth - 10).height(frameObj.height() - 46);
    }
</script>
<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>