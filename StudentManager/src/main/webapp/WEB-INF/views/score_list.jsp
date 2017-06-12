<%@ page import="cn.orditech.entity.Course" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
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
<p>编号：<%=student.getId()%>&nbsp;&nbsp;&nbsp;&nbsp;姓名：<%=student.getName()%></p>
<table class="table table-bordered table-hover table-condensed">
    <thead>
    <td>ID</td>
    <td>科目</td>
    <td>考试时间</td>
    <td>得分</td>
    <td>操作&nbsp;&nbsp;&nbsp;&nbsp;<a id="addScore">录入成绩</a>
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="/student/list.htm">返回学员列表</a></td>
    </thead>
    <tbody id="scoresTable">

    </tbody>
</table>

<div id="editModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">学生成绩</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input id="studentId" name="studentId" class="form-control" style="display:none" value="" />
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="courses4Save">科目:</label>
                        <div class=" col-sm-9">
                            <select id="courses4Save" class="form-control" value="">
                                <option value=""></option>
                                <%for(Course course:courses){%>
                                <option value="<%=course.getId ()%>"><%=course.getName()%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="testDate">考试时间:</label>
                        <div class=" col-sm-9">
                            <input id="testDate" name="testDate" class="form-control" data-date-format="yyyy-mm-dd" placeholder=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="score">成绩:</label>
                        <div class=" col-sm-9">
                            <input id="score" name="score" class="form-control" placeholder=""/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="modalSaveBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    var student = <%=JSON.toJSONString (student)%>;

    var coursesMap={};
    <%for(Course course:courses){%>
        coursesMap[<%=course.getId()%>]='<%=course.getName()%>';
    <%}%>

    var scores=[];

    function getScores(){
        $.post("/score/getScores.htm",
                {studentId:student.id},
                function(data,textStatus){
                    var json = JSON.parse(data);
                    if(json.success){
                        scores = json.data;
                        showScores(scores);
                    }
                }
        );
    }
    function showScores(scores){
        var tbody = "";
        for(var i=0;i<scores.length;i++){
            var score = scores[i];
            tbody+= "<tr>"+
                    "<td>"+score.id+"</td>"+
                    "<td>"+coursesMap[score.course]+"</td>"+
                    "<td>"+score.testDate+"</td>"+
                    "<td>"+score.score+"</td>"+
                    "<td><a class='scoreEdit' data-index='"+i+"'>编辑</a></tr>";
        }
        $("#scoresTable").html(tbody);
        bindItemEvents();
    }
    function bindItemEvents(){
        $(".scoreEdit").click(function(e){
            e.preventDefault();
            var score = scores[$(this).data("index")];
            cleanModal();
            initModal(score);
            $("#editModal").modal('show');
        });
    }
    function cleanModal(){
        $("#courses4Save option:first").click();
        $("#score").val("");
        $("#testDate").val("");
    }
    function initModal(score){
        var option = $("#courses4Save option[value="+score.course+"]");
        $("#courses4Save").val($(option).attr("value"));
        $("#score").val(score.score);
        $("#testDate").val(score.testDate);
    }

    $("#addScore").click(function(e){
        e.preventDefault();
        cleanModal();
        $("#editModal").modal('show');
    });

    function validateScore(){
        var courseEle=$("#courses4Save option:selected");
        if(courseEle.length==0){
            alert("请选择科目");
            return;
        }

        var testDate=$("#testDate").val();
        if(testDate.length==0){
            alert("请输入成绩");
            return;
        }

        var scores=$("#score").val();
        if(scores.length==0){
            alert("请输入成绩");
            return;
        }

        var score = {};
        score.studentId=student.id;
        score.orgId=student.orgId;
        score.course=$(courseEle).attr("value");
        score.testDate=testDate;
        score.score=scores;
        return score;
    }
    $("#modalSaveBtn").click(function(e){
        var score=validateScore();
        $.post("/score/save.htm",score,function(data,textStatus){
            $("#editModal").modal('hide');
            var json = JSON.parse(data);
            if(json.success){
                getScores();
            }
        });
    });

    getScores();


</script>
<%@ include file="tail.jsp"%>