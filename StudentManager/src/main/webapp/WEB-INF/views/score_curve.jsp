<%@ page import="cn.orditech.entity.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.orditech.entity.Student" %>
<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/6/11
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<%
    Student student = (Student) request.getAttribute("student");
    List<Course> courses = (List<Course>) request.getAttribute ("courses");
%>
    <script src="http://cdn.bootcss.com/echarts/3.2.2/echarts.min.js"></script>

    <style type="text/css">
        #info{
            width: 85%;
            float:left;
        }
        #returnLink {
            height: 42px;
            text-align: center;
            float: left;
            width: 15%;
            border-bottom: 1px solid #ddd;
        }
        #returnLink > a {
            margin-right: 2px;
            line-height: 40px;
            border: 1px solid transparent;
            border-radius: 4px 4px 0 0;
        }

        .chart_container {
            width: 880px;
            height: 350px;
            margin: 50px auto;
            display: none;
        }

        #course_select {
            width: 530px;
            margin: 20PX auto 10px auto;
        }
    </style>

<div id="info"><p>编号：<%=student.getId()%>&nbsp;&nbsp;&nbsp;&nbsp;姓名：<%=student.getName()%></p></div>
<div id="returnLink"><a href="../student/list.htm">返回学员列表</a></div>
<div id="course_select" class="input-group">
    <div class="input-group" style="width:420px;">
        <span class="input-group-addon">请选择科目</span>
        <select id="courses4Select" class="form-control" value="">
            <%for(Course course:courses){%>
            <option value="<%=course.getId ()%>"><%=course.getName()%></option>
            <%}%>
        </select>
    </div>
</div>
<div id="score_chart" class="chart_container"></div>

<script type="text/javascript">

    var studentId=<%=student.getId()%>;

    //保存由服务器获取的数据
    var srcData = {};

    /**
     * 获取成绩数据
     * @param course 课程代码
     */
    function ajaxGetCurveData(course) {
        $.post(
            'getScores.htm',
            {course: course,studentId:studentId},
            function (data, status) {
                if (status == 'success' && data) {
                    srcData = JSON.parse(data).data;
                    generateBusinessChart('score_chart', '考试成绩', srcData, 1);
                } else {
                    alert("抱歉，服务器出错了，没有找到您要的数据！");
                }
            }
        );
    }

    /**
     * 将原始数据提取到需要展示的数组中
     * @param src 原始数据
     * @param xData 接受运算结果，做为x轴的值
     * @param yData 接受运算结果，做为折线的值
     * @param diviysor 单位转换除数
     */
    function extraData(src, xData, yData, xMap, divisor) {
        //提取年份，并默认升序排序
        for (var i=0;i<src.length;i++) {
            xData[i] = src[i].testDate;
            xMap[xData[i]] = src[i];
        }
        xData.sort();

        for (var i = 0; i < xData.length; i++) {
            yData[i] = xMap[xData[i]].score;
            yData[i] = Math.ceil(yData[i]*100/xMap[xData[i]].perfectScore);
        }
    }

    /**
     * 绘制业绩chart
     * @param text 图表标题
     * @param srcData 绘图数据
     * @param divisor 单位转换除数
     */
    function generateBusinessChart(container, text, srcData, divisor) {
        var xData = [];
        var sDatas = [];
        var xMap = {};
        extraData(srcData, xData, sDatas, xMap, divisor);

        var myChart = echarts.init(document.getElementById(container));

        var option = {
            title: {
                text: text
            },
            tooltip: {
                trigger: 'axis',
                formatter: function (params) {
                    var score = xMap[params[0].name];
                    return '考试时间:' + score.testDate + '<br />' +
                            '成绩:' + score.score + '<br />' +
                            '满分:' + score.perfectScore + '<br />' +
                            '班级排名:' + undefined2Str(score.rankClass) + '<br />' +
                            '学校排名:' + undefined2Str(score.rankSchool);
                }
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    data: xData
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    type: 'line',
                    label: {
                        normal: {
                            show: true,
                            position: 'top'
                        }
                    },
                    data: sDatas
                }
            ]
        };

        myChart.setOption(option);

        $('#' + container).show();
    }

    $("#courses4Select").change(function(){
        var course = $("#courses4Select option:selected").attr("value");
        ajaxGetCurveData(course);
    });

    ajaxGetCurveData(<%=courses.get(0).getId()%>);
</script>
<%@include file="tail.jsp"%>