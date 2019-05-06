<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目资源</title>
	<meta name="decorator" content="default"/>

	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/context.standalone.css" />
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/context.js"></script>
	<style>
		html,body{
			height:100%;
			width: 100%;
		<c:if test="${list.size()==0}">
			background:url("${ctxStatic}/windowsstyle/icon/nofile.png") no-repeat center;
			background-size: 10%;
		</c:if>
			background-color:#f5f5f5;
		}
		#deskIcon{
			margin: 0px;
			width: 100%;
			height: 100%;
			padding: 0px;
		}
		#deskIcon li{
			display:block;
			float: left;
			text-align: left;
			width: 100%;
			cursor: pointer;
			border-bottom: 1px solid #DDDDDD;
		}
		#deskIcon li .name{
			font-size: 14px;
			padding-left: 10px;
			margin-top: 10px;
		}
		#deskIcon li .name .icon{

			position: relative;
		 }
		#deskIcon li .name .icon img{
			width: 18px;
			height: 18px;

		}
		#deskIcon li .desc{
			font-size: 10px;
			padding-left: 10px;
		}
		#deskIcon li .desc span{
			margin-right: 5px;
		}
		#deskIcon li img{
			margin: 0 auto;
			width: 70%;

		}
		#deskIcon li:hover{
			background:#f2f2f2;
		}

		#deskIcon li .text{
			text-align: center;
		}
	</style>

</head>
<body>
		<sys:message content="${message}"/>
		<ul id="deskIcon">
			<c:forEach items="${list}" var="res">
				<li class="<c:if test="${res.type==2}">file</c:if>" fname="${res.name}" url="${res.pathUrl}" format=".${res.format}" type="${res.format}" fid="${res.id}" title="${res.name}&#10信息描述:${res.remarks}">
				<div class="name"><span class="icon">
						<c:if test="${res.image}">
							<img src="/uploadFile/${res.pathUrl}"/>
						</c:if>
					<c:if test="${!res.image}">
					<img src="/static/windowsstyle/icon/${res.format}.png"/>
					</c:if>
				</span>&nbsp;&nbsp;${res.name}</div>
				<div class="desc">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>文件路径:${res.pathName}.${res.format}</span><span>文件类型:${res.format}</span><span>文件大小:${res.filesize}</span><span>创建人:${res.createBy.name}</span><span>创建时间:<fmt:formatDate value="${res.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
				</li>
			</c:forEach>
		</ul>
	<script type="text/javascript">
		parent.relPath("${path}");
		parent.showIframe();
        $(document).ready(function(){
            function initClick(){
            context.init({preventDoubleContext: false});
            context.settings({compress: true});
            context.attach('.file', [
                {text: '下载文件', action: function(e){
                    e.preventDefault();
                    try{
                        var elemIF = document.createElement("iframe");
                        elemIF.src = "${ctx}/oa/oaProductResources/download?path="+encodeURI(getClickEle().attr("url"))+"&name="+encodeURI(getClickEle().attr("fname")+getClickEle().attr("format"));
                        elemIF.style.display = "none";
                        document.body.appendChild(elemIF);
                    }catch(e){

                    }
                }},
                {text: '删除文件', action: function(e){
                    e.preventDefault();
                    deleteR(getClickEle().attr("fid"));
                }}

            ]);
            var clickEle = "";
            function getClickEle(){ // 自定义方法给 调用者使用
                return clickEle;
            }
            $(document).on('contextmenu', '.dir,.file', function (e) {
                clickEle = $(this);//记录当前点击的element
            });
		}
            initClick();
            $(window).resize(function() {
                initClick();
            });
        });
        function deleteR(id){
            parent.$.jBox.confirm("确认要删除资源吗？",'系统提示',function(v,h,f){
                if(v=='ok'){
                    $.post("${ctx}/oa/oaProductResources/delete",{"id":id},function(data){
                        var data=JSON.parse(data);
						if(data.status=="success"){
                            alert(data.msg);
                            parent.hideIframe();
                            self.location.reload();
						}else{

							alert(data.msg);
						}

                    })
                }
            },{buttonsFocus:1, closed:function(){
            }});


		}

	</script>
</body>
</html>