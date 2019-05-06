<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目资源</title>
	<meta name="decorator" content="default"/>

	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/context.standalone.css" />
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/context.js"></script>
	<script type="text/javascript" src="${ctxStatic}/windowsstyle/highslide/js/highslide-with-gallery.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctxStatic}/windowsstyle/highslide/js/highslide.css" />
	<style>
		html,body{
			height:100%;
			width: 100%;
		<c:if test="${list.size()==0}">
			background:url("${ctxStatic}/windowsstyle/icon/nofile.png") no-repeat center;
			background-size: 10%;
		</c:if>
			background-color:#f5f5f5;
			overflow: hidden;
		}
		#deskIcon{
			margin: 0px;
			padding: 0px;
			width: 100%;
			overflow: hidden;
			
		}
		#deskIcon li{
			display: block;
			float: left;
			width: 150px;
			height: 160px;
			text-align: center;
			overflow: hidden;
			z-index: 9999;
			padding: 10px;
			cursor: pointer;

		}
		#deskIcon li img{
			width: 80%;
		}
		#deskIcon li:hover{
			background:url("/static/windowsstyle/images/icon_over.png") no-repeat center center;
			background-size: 100% 100%;
		}
		#deskIcon li .text{
			text-align: center;
			line-height: 16px;
			height: 32px;
			display: -webkit-box;
			display: -moz-box;
			-moz-box-orient: vertical;
			-moz-line-clamp: 2;
			-webkit-box-orient: vertical;
			-webkit-line-clamp: 2;
			overflow: hidden;
		}
	</style>

</head>
<body>

		<sys:message content="${message}"/>
		<ul id="deskIcon">
			<c:forEach items="${list}" var="res">
				<li class="<c:if test="${res.type==1}">dir</c:if><c:if test="${res.type==2}">file</c:if>" format=".${res.format}" fname="${res.name}" url="${respath}/${res.path}" type="${res.format}" fid="${res.id}" <c:if test="${res.type==1}">onclick="openFile('${res.id}','${res.name}','${res.path}')" </c:if> title="<c:if test="${res.type==2}">${res.name}&#10类型:${res.format}&#10大小:${res.filesize}&#10</c:if>创建时间:<fmt:formatDate value="${res.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>&#10创建人:${res.createBy.name}<c:if test="${res.type==2}">&#10信息描述:${res.remarks}</c:if>">
					<span class="icon">
						<c:if test="${res.image}">
							<a class="highslide" onclick="return hs.expand(this)" href="/uploadFile/${respath}/${res.path}">
								<img class="img" src="/uploadFile/${respath}/${res.path}"/>
							</a>
						</c:if>
						<c:if test="${!res.image}">
							<img src="/static/windowsstyle/icon/${res.format}.png"/>
						</c:if>

					</span>
					<div class="text">
							${res.name}<c:if test="${res.type==2}">.${res.format}</c:if>
					</div>
				</li>
			</c:forEach>
		</ul>

		<script type="text/javascript">
		parent.relPath("${path}");
		parent.showIframe();
        hs.graphicsDir = '${ctxStatic}/windowsstyle/highslide/js/graphics/';
        hs.align = 'center';
        if (hs.addSlideshow) hs.addSlideshow({
            //interval: 10000,
        });
		function openFile(id,path,respath){
            parent.hideIframe();
            self.location.href="${ctx}/oa/oaProductResources/resources?resid="+id+"&path="+encodeURI("${path}/"+path)+"&respath="+encodeURI("${respath}/"+respath);
		}
        $(document).ready(function(){
			function initClick(){
					context.init({preventDoubleContext: false});
					context.settings({compress: true});
					context.attach('.dir', [
						{text: '修改文件名', action: function(e){
							e.preventDefault();
							openWin(getClickEle().attr("fid"));
						}},

						{text: '删除文件', action: function(e){
							e.preventDefault();
							deleteR(getClickEle().attr("fid"));
						}}

					]);
					context.attach('body', [
						{text: '后退', action: function(e){
							e.preventDefault();

							back();
						}},
						{text: '新建文件夹', action: function(e){
							e.preventDefault();
							openWin();
						}},
						{text: '上传文件', action: function(e){
							e.preventDefault();
							openUploadWin();
						}},{text: '刷新', action: function(e){
						e.preventDefault();
						parent.hideIframe();
						self.location.reload();
					}}
					]);
					context.attach('.file', [
						{text: '下载文件', action: function(e){
								try{
									var elemIF = document.createElement("iframe");
									elemIF.src = "${ctx}/oa/oaProductResources/download?path="+encodeURI(getClickEle().attr("url"))+"&name="+encodeURI(getClickEle().attr("fname")+getClickEle().attr("format"));
									elemIF.style.display = "none";
									document.body.appendChild(elemIF);
								}catch(e){

								}
								e.preventDefault();
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
		function back(){
            parent.hideIframe();
            if("${oaProductResources.parentId}"=="0"){
                self.location.href="${ctx}/oa/oaProductResources/product";
            }else{
                self.location.href="${ctx}/oa/oaProductResources/resources?resid=${oaProductResources.parentId}"+"&path="+encodeURI("${path}".substring(0,"${path}".lastIndexOf("/")))+"&respath="+encodeURI("${respath}".substring(0,"${respath}".lastIndexOf("/")));
            }

		}
		function openWin(id){
            parent.$.jBox.open("iframe:${ctx}/oa/oaProductResources/dir?id="+id, "新建文件夹", 300, 200, {
                buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
                        var name = h.find("iframe")[0].contentWindow.$("#name").val();
                        var id=h.find("iframe")[0].contentWindow.$("#id").val();
                        if(id=='undefined'){
                            id="";
						}
                        parent.hideIframe();
                        $.post("${ctx}/oa/oaProductResources/savedir",{"id":id,"name":name,"parentId":"${oaProductResources.id}","pIds":"${oaProductResources.pIds}","productId":"${oaProductResources.productId}","respath":"${respath}"},function(data){
                            if(data=="success"){
                                parent.hideIframe();
                                self.location.reload();
                            }else{
                                alert("添加失败！");
							}
						});
                    }
                }, loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });
		}
        function openUploadWin(){
            parent.$.jBox.open("iframe:${ctx}/oa/oaProductResources/touploader?parentId=${oaProductResources.id}&respath="+encodeURI("${respath}")+"&userid=${fns:getUser().getId()}&pIds=${oaProductResources.pIds}&productId=${oaProductResources.productId}", "上传文件", 600, $(window).height()-150, {
                buttons:{"确定":"ok", "关闭":true}, submit:function(v, h, f){
                    if (v=="ok"){
						 	parent.hideIframe();
                            self.location.reload();
                    }
                }, loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });
        }

	</script>
</body>
</html>