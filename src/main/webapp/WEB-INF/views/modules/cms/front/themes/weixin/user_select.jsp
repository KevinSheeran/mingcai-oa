<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/modules/cms/front/include/taglib.jsp"%>
<!--页面主结构结束-->
<!--单页面开始-->
<div id="selectuser" class="mui-page">
	<div class="mui-navbar-inner mui-bar mui-bar-nav" id="header">
		<button type="button" class="mui-left mui-action-back mui-btn  mui-btn-link mui-btn-nav mui-pull-left">
			<span class="mui-icon mui-icon-left-nav"></span>
		</button>
		<h1 class="mui-center mui-title">选择用户</h1>
		<a onclick="selectValues()" id="done" class="mui-btn mui-btn-link mui-pull-right mui-btn-blue mui-disabled">完成</a>
	</div>
	<!--页面主内容区开始-->
	<div class="mui-page-content">
				<div class="mui-content">
					<div id='userlist' class="mui-indexed-list">
						<div class="mui-indexed-list-search mui-input-row mui-search">
							<input type="search" class="mui-input-clear mui-indexed-list-search-input" placeholder="搜索用户">
						</div>
						<div class="mui-indexed-list-bar">
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
						<div class="mui-indexed-list-inner a">
							<div class="mui-indexed-list-empty-alert">没有数据</div>
							<ul class="mui-table-view">
								<c:forEach items="${gsons}" var="user">
									<li data-group="${user.key}" class="mui-table-view-divider mui-indexed-list-group">${user.key}</li>
									<c:forEach items="${user.value}" var="item">
										<c:if test="${item.user.id!=null}">
											<li data-value="${item.pinyin}" data-tags="${item.pinyin}"  class="mui-table-view-cell mui-indexed-list-item mui-checkbox mui-left"><img src="${item.avatar}/100" class="mui-left" width="40" height="40"><input type="checkbox" avatar="${item.avatar}" userid="${item.user.id}" username="${item.name}"/>&nbsp;&nbsp;${item.name}</li>
										</c:if>
									</c:forEach>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
	</div>

	<!--页面主内容区结束-->
</div>
<script src="${ctxStaticTheme}/mui/js/mui.view.js"></script>
<script type="application/javascript">
    var user;
    var header = document.getElementById('header');
    var list = document.getElementById('userlist');
    var done = document.getElementById('done');
    function inituser(){

        //calc hieght
        list.style.height = (document.body.offsetHeight - header.offsetHeight) + 'px';
        //create
        window.indexedList = new mui.IndexedList(list);
        //done event
        mui('.a').on('change', 'input', function() {
            var count = list.querySelectorAll('input[type="checkbox"]:checked').length;
            var value = count ? "完成(" + count + ")" : "完成";
            done.innerHTML = value;
            if (count) {
                if (done.classList.contains("mui-disabled")) {
                    done.classList.remove("mui-disabled");
                }
            } else {
                if (!done.classList.contains("mui-disabled")) {
                    done.classList.add("mui-disabled");
                }
            }
        });
    }
    function selectValues(){
        user=[];

        var checkboxArray = [].slice.call(list.querySelectorAll('input[type="checkbox"]'));
        var checkedValues = [];
        checkboxArray.forEach(function(box) {
            if (box.checked) {
                user.push({userid:$(box).attr("userid"),avatar:$(box).attr("avatar"),username:$(box).attr("username")});
                checkedValues.push(box.parentNode.innerText);
            }
        });
        if (checkedValues.length > 0) {
            viewApi.back();
            addUser();
            $("input[type='checkbox']").prop("checked",false);
            //mui.alert('你选择了: ' + checkedValues);
        } else {
            mui.alert('你没选择任何用户');
        }
    }
</script>