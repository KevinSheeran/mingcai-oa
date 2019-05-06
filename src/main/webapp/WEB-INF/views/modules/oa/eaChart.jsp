<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <title>报销统计</title>
    <meta name="decorator" content="default"/>
    <script src="${ctxStatic}/echarts/echarts.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
        });
    </script>
</head>
<body>
<input type="hidden" id="hid-title" value="${hidTitle}" />
<input type="hidden" id="hid-total-num" value="${hidTotalNum}" />
<input type="hidden" id="hid-total-money" value="${hidTotalMoney}" />
<input type="hidden" id="hid-cate" value="${eaData.cate}" />
<input type="hidden" id="hid-type" value="${eaData.type}" />
<input id="mapList" type="hidden" value='${mapList}' />
<input id="chartType" type="hidden" value='${eaData.chartType}'/>
<div id="messageBox1" class="alert alert-error" style="display:none">取得统计数据为0</div>
<div id="paintbar" style="width: 950px;height:500px;display: inline-block;vertical-align: top;"></div>
<div id="paintpie" style="width: 950px;height:500px;vertical-align: top;display: none;"></div>
<div id="controlDiv" style="display: inline-block;">
    <div id="orderDiv" style="margin-top: 20px;">
        <a onclick="orderAsc()" class="btn btn-primary" style="text-decoration: none;">升序</a>
        <a onclick="orderDesc()" class="btn btn-primary" style="text-decoration: none;">降序</a>
        <a onclick="order()" class="btn btn-primary" style="text-decoration: none;">常序</a>
        <a id="btnExport" class="btn btn-success" style="text-decoration: none;">导出Excel</a>
    </div>
    <div id="changeDiv" style="margin-top: 5px;">
        <a onclick="pieChart()" class="btn btn-warning" style="text-decoration: none;">切换饼图</a>
        <a onclick="barChart()" class="btn btn-warning" style="text-decoration: none;">切换条形图</a>
        <input id="limitNum" type="text" style="width: 25px;margin-top: 10px;margin-left: 5px;" value="${eaData.limitNum}"/>
    </div>
    <div id="pageDiv" style="margin-top: 20px;">
        <ul id="myTab" class="nav nav-tabs">
            <li <c:if test="${eaData.type==1}">class="active"</c:if>>
                <a href="#tongji1" data-toggle="tab">公司</a>
            </li>
            <li <c:if test="${eaData.type==2}">class="active"</c:if>>
                <a href="#tongji2" data-toggle="tab">项目</a>
            </li>
            <li <c:if test="${eaData.type==3}">class="active"</c:if>>
                <a href="#tongji3" data-toggle="tab">部门</a>
            </li>
            <li <c:if test="${eaData.type==4}">class="active"</c:if>>
                <a href="#tongji4" data-toggle="tab">员工</a>
            </li>
        </ul>
        <div id="myTabContent" class="tab-content" style="font-weight: 500;font-size: 18px;">
            <div class="tab-pane fade<c:if test="${eaData.type==1}"> in active</c:if>" id="tongji1" style="vertical-align: top;">
                开始日期
                <input type="text" id="beginYear1" value="${beginYear}" style="width: 30px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">年
                <input type="text" id="beginMonth1" value="${beginMonth}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">月
                <br/>
                结束日期
                <input type="text" id="endYear1" value="${endYear}" style="width: 30px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">年
                <input type="text" id="endMonth1" value="${endMonth}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">月
                <br/>
                <a class="btn btn-primary" onclick="getChartByProType1()">按项目统计</a>
                <a class="btn btn-primary" onclick="getChartByCateType1()">按用途统计</a>
                <a class="btn btn-primary" onclick="getChartByDepartType1()">按部门统计</a>
                <br /><br />
                <a class="btn btn-danger" onclick="getChartByYearType1()">按年度统计</a>
                <a class="btn btn-danger" onclick="getChartByMonthType1()">按月份统计</a>
                <a class="btn btn-danger" onclick="getChartByDayType1()">按日期统计</a>
            </div>
            <div class="tab-pane fade<c:if test="${eaData.type==2}"> in active</c:if>" id="tongji2" style="vertical-align: top;">
                开始日期
                <input type="text" id="beginYear2" value="${beginYear}" style="width: 30px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">年
                <input type="text" id="beginMonth2" value="${beginMonth}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">月
                <input type="text" id="beginDay2" value="${beginDay}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">日
                <br/>
                结束日期
                <input type="text" id="endYear2" value="${endYear}" style="width: 30px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">年
                <input type="text" id="endMonth2" value="${endMonth}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">月
                <input type="text" id="endDay2" value="${endDay}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">日
                <br/>
                项目：
                <select style="font-size: 14px;width: 200px;" name="project" id="project">
                    <c:forEach items="${proList}" var="project">
                        <c:if test="${project.id==eaData.project}"><option value="${project.id}" selected="selected">${project.name}</option></c:if>
                        <c:if test="${project.id!=eaData.project}"><option value="${project.id}">${project.name}</option></c:if>
                    </c:forEach>
                </select>
                <br /><br />
                <a class="btn btn-primary" onclick="getChartByCateType2()">按用途统计</a>
                <a class="btn btn-primary" onclick="getChartByDepartType2()">按部门统计</a>
                <a class="btn btn-primary" onclick="getChartByStaffType2()">按人员统计</a>
                <br /><br />
                <a class="btn btn-danger" onclick="getChartByYearType2()">按年度统计</a>
                <a class="btn btn-danger" onclick="getChartByMonthType2()">按月份统计</a>
                <a class="btn btn-danger" onclick="getChartByDayType2()">按日期统计</a>
            </div>
            <div class="tab-pane fade<c:if test="${eaData.type==3}"> in active</c:if>" id="tongji3" style="vertical-align: top;">
                开始日期
                <input type="text" id="beginYear3" value="${beginYear}" style="width: 30px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">年
                <input type="text" id="beginMonth3" value="${beginMonth}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">月
                <input type="text" id="beginDay3" value="${beginDay}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">日
                <br/>
                结束日期
                <input type="text" id="endYear3" value="${endYear}" style="width: 30px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">年
                <input type="text" id="endMonth3" value="${endMonth}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">月
                <input type="text" id="endDay3" value="${endDay}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">日
                <br/>
                部门：
                <select style="font-size: 14px;width: 200px;" name="depart" id="depart">
                    <c:forEach items="${departList}" var="depart">
                        <c:if test="${depart.id==eaData.depart}"><option value="${depart.id}" selected="selected">${depart.name}</option></c:if>
                        <c:if test="${depart.id!=eaData.depart}"><option value="${depart.id}">${depart.name}</option></c:if>
                    </c:forEach>
                </select>
                <br /><br />
                <a class="btn btn-primary" onclick="getChartByProType3()">按项目统计</a>
                <a class="btn btn-primary" onclick="getChartByCateType3()">按用途统计</a>
                <a class="btn btn-primary" onclick="getChartByStaffType3()">按人员统计</a>
                <br /><br />
                <a class="btn btn-danger" onclick="getChartByYearType3()">按年度统计</a>
                <a class="btn btn-danger" onclick="getChartByMonthType3()">按月份统计</a>
                <a class="btn btn-danger" onclick="getChartByDayType3()">按日期统计</a>
            </div>
            <div class="tab-pane fade<c:if test="${eaData.type==4}"> in active</c:if>" id="tongji4" style="vertical-align: top;">
                开始日期
                <input type="text" id="beginYear4" value="${beginYear}" style="width: 30px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">年
                <input type="text" id="beginMonth4" value="${beginMonth}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">月
                <input type="text" id="beginDay4" value="${beginDay}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">日
                <br/>
                结束日期
                <input type="text" id="endYear4" value="${endYear}" style="width: 30px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">年
                <input type="text" id="endMonth4" value="${endMonth}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">月
                <input type="text" id="endDay4" value="${endDay}" style="width: 15px;margin-left: 7px;margin-right: 7px;margin-top: 5px;">日
                <br/>
                部门：
                <select style="font-size: 14px;width: 200px;" name="depart2" id="depart2" onchange="getStaff()">
                    <c:forEach items="${departList}" var="depart2">
                        <c:if test="${depart2.id==eaData.depart}"><option value="${depart2.id}" selected="selected">${depart2.name}</option></c:if>
                        <c:if test="${depart2.id!=eaData.depart}"><option value="${depart2.id}">${depart2.name}</option></c:if>
                    </c:forEach>
                </select>
                <br /><br />
                人员：
                <select style="font-size: 14px;width: 200px;" name="staff" id="staff">
                    <c:forEach items="${staffList}" var="staff">
                        <c:if test="${staff.id==eaData.staff}"><option value="${staff.id}" selected="selected">${staff.name}</option></c:if>
                        <c:if test="${staff.id!=eaData.staff}"><option value="${staff.id}">${staff.name}</option></c:if>
                    </c:forEach>
                </select>
                <br /><br />
                <a class="btn btn-primary" onclick="getChartByProType4()">按项目统计</a>
                <a class="btn btn-primary" onclick="getChartByCateType4()">按用途统计</a>
                <br /><br />
                <a class="btn btn-danger" onclick="getChartByYearType4()">按年度统计</a>
                <a class="btn btn-danger" onclick="getChartByMonthType4()">按月份统计</a>
                <a class="btn btn-danger" onclick="getChartByDayType4()">按日期统计</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $("#paintbar").width(top.document.body.clientWidth*0.9*0.75);
    $("#paintpie").width(top.document.body.clientWidth*0.9*0.75);
    function getBarChart(arr1,arr2,arr3,arr4,arr5,arr6,arr7,arr8){
        $("#paintpie").css("display","none");
        $("#paintbar").css("display","inline-block");
        option = {
            backgroundColor: "#344b58",
            "title": {
                "text": arr1,
                "subtext": arr2,
                x: "4%",

                textStyle: {
                    color: '#fff',
                    fontSize: '22'
                },
                subtextStyle: {
                    color: '#90979c',
                    fontSize: '16',

                },
            },
            "tooltip": {
                "trigger": "axis",
                "axisPointer": {
                    "type": "shadow",
                    textStyle: {
                        color: "#fff"
                    }

                },
            },
            "grid": {
                "borderWidth": 0,
                "top": 110,
                "bottom": 95,
                textStyle: {
                    color: "#fff"
                }
            },
            "legend": {
                x: '4%',
                top: '11%',
                textStyle: {
                    color: '#90979c',
                },
                "data": arr3
            },


            "calculable": true,
            "xAxis": [{
                "type": "category",
                "axisLine": {
                    lineStyle: {
                        color: '#90979c'
                    }
                },
                "splitLine": {
                    "show": false
                },
                "axisTick": {
                    "show": false
                },
                "splitArea": {
                    "show": false
                },
                "axisLabel": {
                    "interval": 0,

                },
                "data": arr4,
            }],
            "yAxis": [{
                "type": "value",
                "splitLine": {
                    "show": false
                },
                "axisLine": {
                    lineStyle: {
                        color: '#90979c'
                    }
                },
                "axisTick": {
                    "show": false
                },
                "axisLabel": {
                    "interval": 0,

                },
                "splitArea": {
                    "show": false
                },

            }],
            "dataZoom": [{
                "show": true,
                "height": 30,
                "xAxisIndex": [
                    0
                ],
                bottom: 30,
                "start": arr7,
                "end": arr8,
                handleIcon: 'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
                handleSize: '110%',
                handleStyle:{
                    color:"#d3dee5",

                },
                textStyle:{
                    color:"#fff"},
                borderColor:"#90979c"


            }, {
                "type": "inside",
                "show": true,
                "height": 15,
                "start": 1,
                "end": 80
            }],
            "series": [{
                "name": "项目统计",
                "type": "bar",
                "stack": "总量",
                "barMaxWidth": 150,
                "barGap": "10%",
                "itemStyle": {
                    "normal": {
                        "color": arr5,
                        "label": {
                            "show": true,
                            "textStyle": {
                                "color": "#fff"
                            },
                            "position": "insideTop",
                            formatter: function(p) {
                                return p.value > 0 ? (p.value) : '';
                            }
                        }
                    }
                },
                "data": arr6,
            },
            ]
        };
        myChartBar.setOption(option);
    }

    function getPieChart(arr1,arr2,arr3,arr4,arr5,arr6){
        $("#paintbar").css("display","none");
        $("#paintpie").css("display","inline-block");
        option = {
            title : {
                text:arr1,
                subtext: arr2,
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                type: 'scroll',
                orient: 'vertical',
                right: 10,
                top: 20,
                bottom: 20,
                data: arr3,
                selected: arr4
            },
            series : [
                {
                    name: arr5,
                    type: 'pie',
                    radius : '80%',
                    center: ['50%', '50%'],
                    data: arr6,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        myChartPie.setOption(option);
    }
</script>
<script type="text/javascript">
    var excelTitle;
    function getBar(){
        var type = $("#hid-type").val();
        var titleValue = "";
        if($("#beginYear"+type).val()==null){
            $.jBox.tip('未选择开始年份', 'error');
            return false;
        }
        titleValue+= $("#beginYear"+type).val()+"年";
        if($("#beginMonth"+type).val()!=null){
            titleValue+= $("#beginMonth"+type).val()+"月";
        }
        titleValue+="-";
        titleValue+= $("#endYear"+type).val()+"年";
        if($("#endMonth"+type).val()!=null){
            titleValue+= $("#endMonth"+type).val()+"月";
        }
        titleValue+=$("#hid-title").val();
        var mapList =  $("#mapList").val();
        mapList = eval("("+mapList+")");
        var jsonName = [];
        var jsonData = [];
        var jsonTitle = ['项目'];
        var total = 0;
        //    var color = "rgba("+Math.floor(Math.random()*255)+","+Math.floor(Math.random()*255)+","+Math.floor(Math.random()*255)+",1)";
        var color = "rgba(0,191,183,1)";
        var num = 0;
        for(var count =0;count<mapList.length;count++){
            jsonName.push(mapList[count].name);
            jsonData.push(mapList[count].value);
            total += parseInt(mapList[count].value);
            num++;
        }
        total = $("#hid-total-num").val() +num+" "+$("#hid-total-money").val()+ total;
        var beginI = 20;
        var endI = 40;
        if($("#limitNum").val()!=0){
            beginI = 0;
            endI = 100;
        }
        excelTitle = titleValue;
        getBarChart(titleValue,total,jsonTitle,jsonName,color,jsonData,beginI,endI);
    }
    function getPie(){
        var type = $("#hid-type").val();
        var titleValue = "";
        if($("#beginYear"+type).val()==null){
            $.jBox.tip('未选择开始年份', 'error');
            return false;
        }
        titleValue+= $("#beginYear"+type).val()+"年";
        if($("#beginMonth"+type).val()!=null){
            titleValue+= $("#beginMonth"+type).val()+"月";
        }
        titleValue+="-";
        titleValue+= $("#endYear"+type).val()+"年";
        if($("#endMonth"+type).val()!=null){
            titleValue+= $("#endMonth"+type).val()+"月";
        }
        titleValue+=$("#hid-title").val();
        var mapList =  $("#mapList").val();
        mapList = eval("("+mapList+")");
        var jsonName = [];
        var jsonData = [];
        var jsonTitle = ['项目'];
        var total = 0;
        var num = 0;
        for(var count =0;count<mapList.length;count++){
            jsonName.push(mapList[count].name);
            jsonData.push(mapList[count].value);
            total += parseInt(mapList[count].value);
            num++;
        }
        total = $("#hid-total-num").val() +num+" "+$("#hid-total-money").val()+ total;
        excelTitle = titleValue;
        getPieChart(titleValue,total,jsonName,jsonData,jsonTitle,mapList);
    }
</script>
<script type="text/javascript">
    var myChartPie = echarts.init(document.getElementById('paintpie'));
    var myChartBar = echarts.init(document.getElementById('paintbar'));
    var hrefs;
    function getFormData(cate){
        var type = $("#hid-type").val();
        hrefs = "${ctx}/finance/eaChart?type="+type;
        if(cate==1){ // 按项目
            if(type==1){
                hrefs += "&cate=1";
            }else if(type==3){
                hrefs += "&cate=1&depart="+$("#depart").val()+"&departName="+$("#depart option:selected").text();
            }else if(type==4){
                hrefs += "&cate=1&depart="+$("#depart2").val()+"&departName="+$("#depart2 option:selected").text()
                        +"&staff="+$("#staff").val()+"&staffName="+$("#staff option:selected").text();
            }
        }
        if(cate==2){ // 按费用
            if(type==1){
                hrefs += "&cate=2";
            }else if(type==2){
                hrefs += "&cate=2&project="+$("#project").val()+"&projectName="+$("#project option:selected").text();
            }else if(type==3){
                hrefs += "&cate=2&depart="+$("#depart").val()+"&departName="+$("#depart option:selected").text();
            }else if(type==4){
                hrefs += "&cate=2&depart="+$("#depart2").val()+"&departName="+$("#depart2 option:selected").text()
                    +"&staff="+$("#staff").val()+"&staffName="+$("#staff option:selected").text();
            }

        }
        if(cate==3){ // 按部门
            if(type==1){
                hrefs += "&cate=3";
            }else if(type==2){
                hrefs += "&cate=3&project="+$("#project").val()+"&projectName="+$("#project option:selected").text();
            }
        }
        if(cate==4){ // 按员工
            if(type==2){
                hrefs += "&cate=4&project="+$("#project").val()+"&projectName="+$("#project option:selected").text();
            }else if(type==3){
                hrefs += "&cate=4&depart="+$("#depart").val()+"&departName="+$("#depart option:selected").text();
            }
        }
        if(cate==5){ // 按年度
            if(type==1){
                hrefs += "&cate=5";
            }else if(type==2){
                hrefs += "&cate=5&project="+$("#project").val()+"&projectName="+$("#project option:selected").text();
            }else if(type==3){
                hrefs += "&cate=5&depart="+$("#depart").val()+"&departName="+$("#depart option:selected").text();
            }else if(type==4){
                hrefs += "&cate=5&depart="+$("#depart2").val()+"&departName="+$("#depart2 option:selected").text()
                    +"&staff="+$("#staff").val()+"&staffName="+$("#staff option:selected").text();
            }
        }
        if(cate==6){ // 按月份
            if(type==1){
                hrefs += "&cate=6";
            }else if(type==2){
                hrefs += "&cate=6&project="+$("#project").val()+"&projectName="+$("#project option:selected").text();
            }else if(type==3){
                hrefs += "&cate=6&depart="+$("#depart").val()+"&departName="+$("#depart option:selected").text();
            }else if(type==4){
                hrefs += "&cate=6&depart="+$("#depart2").val()+"&departName="+$("#depart2 option:selected").text()
                    +"&staff="+$("#staff").val()+"&staffName="+$("#staff option:selected").text();
            }
        }
        if(cate==7){ // 按日期
            if(type==1){
                hrefs += "&cate=7";
            }else if(type==2){
                hrefs += "&cate=7&project="+$("#project").val()+"&projectName="+$("#project option:selected").text();
            }else if(type==3){
                hrefs += "&cate=7&depart="+$("#depart").val()+"&departName="+$("#depart option:selected").text();
            }else if(type==4){
                hrefs += "&cate=7&depart="+$("#depart2").val()+"&departName="+$("#depart2 option:selected").text()
                    +"&staff="+$("#staff").val()+"&staffName="+$("#staff option:selected").text();
            }
        }
        var chartType = 1;
        if($("#paintbar").css("display")=='none'){ // 图表形式
            chartType=2;
            hrefs += "&chartType="+chartType;
        }
        var limitNum = $("#limitNum").val();
        if(limitNum!=0){ // 限制数量
            hrefs+="&limitNum="+limitNum;
        }
        var beginYear = $("#beginYear"+type).val();
        if(beginYear!=null){ // 开始年份
            hrefs+="&beginYear="+beginYear;
        }
        var endYear = $("#endYear"+type).val();
        if(endYear!=null){ // 结束年份
            hrefs+="&endYear="+endYear;
        }
        var beginMonth = $("#beginMonth"+type).val();
        if(beginMonth!=null){ // 开始年份
            hrefs+="&beginMonth="+beginMonth;
        }
        var endMonth = $("#endMonth"+type).val();
        if(endMonth!=null){ // 结束年份
            hrefs+="&endMonth="+endMonth;
        }
    }
    function setTypes(){
        if($("#tongji1").css("display")=='block'){
            $("#hid-type").val(1);
        }
        if($("#tongji2").css("display")=='block'){
            $("#hid-type").val(2);
        }
        if($("#tongji3").css("display")=='block'){
            $("#hid-type").val(3);
        }
        if($("#tongji4").css("display")=='block'){
            $("#hid-type").val(4);
        }
    }

    function getStaff(){ // 获取员工
        $.ajax({
            url: '${ctx}/finance/eaChart/getStaff?depart='+$("#depart2").val(),
            type: 'GET',
            data: null,
            dataType:'json'
        }).success(function(data) {
            $("#staff option").remove();
            for(var i=0;i<data.length;i++){
                if(i==0){
                    $("#staff").append("<option value='"+data[i].id+"' selected='selected'>"+data[i].name+"</option>");
                    $("#s2id_staff .select2-chosen").html(data[i].name);
                }else{
                    $("#staff").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
                }
            }
        });
    }

    function orderAsc(){ // 升序
        setTypes();
        var cate = $("#hid-cate").val();
        getFormData(cate);
        hrefs+="&order=1";
        location.href = hrefs;
    }
    function orderDesc(){ // 降序
        setTypes();
        var cate = $("#hid-cate").val();
        getFormData(cate);
        hrefs+="&order=2";
        location.href = hrefs;
    }
    function order(){ // 常序
        setTypes();
        var cate = $("#hid-cate").val();
        getFormData(cate);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByProType1(){ // 按项目（公司）
        $("#hid-type").val(1);
        getFormData(1);
        hrefs+="&order=0";
        location.href = hrefs;
    }
    function getChartByCateType1(){ // 按费用（公司）
        $("#hid-type").val(1);
        getFormData(2);
        hrefs+="&order=0";
        location.href = hrefs;
    }
    function getChartByDepartType1(){ // 按部门（公司）
        $("#hid-type").val(1);
        getFormData(3);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByYearType1(){ // 按年度（项目）
        $("#hid-type").val(1);
        getFormData(5);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByMonthType1(){ // 按月份（项目）
        $("#hid-type").val(1);
        getFormData(6);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByDayType1(){ // 按日期（项目）
        $("#hid-type").val(1);
        getFormData(7);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByCateType2(){ // 按费用（项目）
        $("#hid-type").val(2);
        getFormData(2);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByDepartType2(){ // 按部门（项目）
        $("#hid-type").val(2);
        getFormData(3);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByStaffType2(){ // 按员工（项目）
        $("#hid-type").val(2);
        getFormData(4);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByYearType2(){ // 按年度（项目）
        $("#hid-type").val(2);
        getFormData(5);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByMonthType2(){ // 按月份（项目）
        $("#hid-type").val(2);
        getFormData(6);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByDayType2(){ // 按日期（项目）
        $("#hid-type").val(2);
        getFormData(7);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByProType3(){ // 按项目（部门）
        $("#hid-type").val(3);
        getFormData(1);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByCateType3(){ // 按费用（部门）
        $("#hid-type").val(3);
        getFormData(2);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByStaffType3(){ // 按员工（部门）
        $("#hid-type").val(3);
        getFormData(4);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByYearType3(){ // 按年度（部门）
        $("#hid-type").val(3);
        getFormData(5);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByMonthType3(){ // 按月份（部门）
        $("#hid-type").val(3);
        getFormData(6);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByDayType3(){ // 按日期（项目）
        $("#hid-type").val(3);
        getFormData(7);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByProType4(){ // 按项目（员工）
        $("#hid-type").val(4);
        getFormData(1);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByCateType4(){ // 按费用（员工）
        $("#hid-type").val(4);
        getFormData(2);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByYearType4(){ // 按年度（员工）
        $("#hid-type").val(4);
        getFormData(5);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByMonthType4(){ // 按月份（员工）
        $("#hid-type").val(4);
        getFormData(6);
        hrefs+="&order=0";
        location.href = hrefs;
    }

    function getChartByDayType4(){ // 按日期（员工）
        $("#hid-type").val(4);
        getFormData(7);
        hrefs+="&order=0";
        location.href = hrefs;
    }
    function excel(){
        $.jBox.tip('尚未支持', 'warning');
    }
    function pieChart(){
        setTypes();
        getPie();
    }
    function barChart(){
        setTypes();
        getBar();
    }
    $("#btnExport").click(function(){
        $.jBox.confirm("确认要导出统计信息吗？","系统提示",function(v,h,f){
            if(v=="ok"){
                location.href="${ctx}/finance/eaChart/export?cate="+$("#hid-cate").val()+"&title="+excelTitle;
            }
        });
    });
</script>
<script type="text/javascript">
    if($("#mapList").val()==''){
        $("#messageBox1").css("display","block");
    }else{
        if($("#chartType").val()==2){
            getPie();
        }else{
            getBar();
        }
    }
</script>

</body>
</html>
