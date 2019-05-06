<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>销售项目预立项管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	</script>
	<link rel="stylesheet" href="${ctxStatic}/windowsstyle/style-c.css?time=<%=new Date().getTime()%>" />
	<style>
			body{
				background: #fff;
				position: relative;
				overflow: hidden;
			}
			.oa_name{
				float: left;
				line-height: 50px;
				font-size: 40px;
				margin: 3% 0 0 5%;
			}
		.layout-middle{
			width: 100%;
			min-height: auto;
			min-width: auto;
		}
	</style>
</head>
<body>
<ul id="deskIcon" style="overflow: hidden;position: relative;z-index: 99;">
	<li style="width: 100%;position: relative; clear: left;position: relative;overflow: hidden;">
		<div class="oa_name" style="color: #333;">
			${oaEosPro.name}
		</div>
	</li>
	<li  style="position: relative;" id="grids">
		<div class="layout-middle" id="layout-middle" style="width: 100%">
			<div id="channel-list">
				<div id="home" class="channel " style="width: 40%;margin: 0 auto;float: none;">
					<a href="javascript:goWeekPage()">
						<div data-wow-delay="0.1s" class="wow-hide wow fadeIn grid g70x70 bg1  animated"  title="">
							<img src="/static/windowsstyle/menuIcon/icon9.png">
							<div class="menu-title">项目周报</div>
						</div>
					</a>
					<a href="javascript:goCountPage()">
						<div data-wow-delay="0.1s" class="wow-hide wow fadeIn grid g150x75 bg5  animated"  title="">
							<img src="/static/windowsstyle/menuIcon/icon9.png">
							<div class="menu-title">费用统计</div>
						</div>
					</a>
					<a href="javascript:goTimesPage()">
						<div data-wow-delay="0.1s" class="wow-hide wow fadeIn grid g70x70 bg3  animated"  title="">
							<img src="/static/windowsstyle/menuIcon/icon9.png">
							<div class="menu-title">项目工时</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</li>
</ul>
<script type="application/javascript">


	function goWeekPage(){
            // 正常打开
            top.top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosProPresentation/page?proid=${oaEosPro.id}", "项目周报",700,$(window).height(), {
                buttons:{}, submit:function(v, h, f){
                }, loaded:function(h){
                    $(".jbox-content", document).css("overflow-y","hidden");
                }
            });
	}
    function goTimesPage(){
        // 正常打开
        top.$.jBox.open("iframe:${ctx}/oa/eos/oaEosProTimetotalItem/page?proid=${oaEosPro.id}", "项目工时",700,$(window).height(), {
            buttons:{}, submit:function(v, h, f){
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });
    }
    function goCountPage(){
        // 正常打开
        top.$.jBox.open("iframe: ${ctx}/oa/eos/sta/getylxbycw/${oaEosPro.id}", "统计",1000,$(window).height(), {
            buttons:{}, submit:function(v, h, f){
            }, loaded:function(h){
                $(".jbox-content", document).css("overflow-y","hidden");
            }
        });

	}
</script>
</body>
</html>