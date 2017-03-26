<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/26
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%@ page import="cn.orditech.entity.TestPaper" %>
<%
    TestPaper testPaper = (TestPaper)request.getAttribute("testPaper");
    JSONArray questions = (JSONArray)request.getAttribute ("questions");
    boolean isEdit = Boolean.TRUE.equals (request.getAttribute ("isEdit"));
    if(!isEdit){
        questions = new JSONArray ();
    }
%>
<%@ include file="header.jsp"%>
<form>
    <input id="testPaperId" value="<%=isEdit?testPaper.getId():""%>" style="display:none;"/>
    <div class="form-group">
        <label for="testPaperTitle" class="control-label">试卷描述</label>
        <textarea id="testPaperTitle" name="title" class="form-control" rows="3"><%=isEdit?testPaper.getTitle():""%></textarea>
    </div>
    <label class="control-label">试题</label><br/>

    <table id="questions" class="table table-condensed">
        <thead>
            <td></td>
            <td>试题描述</td>
            <td>分数</td>
            <td></td>
        </thead>
        <%for(int i=0;i<questions.size ();i++){%>
        <tr class="question" data-questionid="<%=questions.getJSONObject (i).getLong("id")%>">
            <td class="col-sm-1"><%=questions.getJSONObject (i).getLong("id")%></td>
            <td class="col-sm-8"><%=questions.getJSONObject (i).getString("title")%></td>
            <td class="col-sm-2">
                <input type="number" class="score"  style="width:60px"
                       value="<%=questions.getJSONObject (i).getLong("score")%>"/>
            </td>
            <td class="col-sm-1"><a class="delete" href="#">删除</a></td>
        </tr>
        <%}%>
        <tr id="btn_add_question_tr">
            <td colspan="4">
                <input id="btn_add_question" type="button" class="btn btn-default col-sm-12" value="添&nbsp;&nbsp;&nbsp;&nbsp;加"/>
            </td>
        </tr>
    </table>
</form>
<div class="col-sm-offset-10 col-sm-2">
    <button id="save" class="btn btn-default">保&nbsp;&nbsp;&nbsp;&nbsp;存</button>
</div>

<div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <table class="table table-bordered table-hover table-condensed">
                <thead>
                <td>ID</td>
                <td>试题类型</td>
                <td>试题描述</td>
                <td>默认分数</td>
                <td>操作</td>
                </thead>
                <tbody id="questionsForSelect">

                </tbody>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(".question .delete").click(function(){
        $(this).parents("tr:first").remove();
    });

    $("#btn_add_question").click(function(){
        //已经加入过的试题集合
        var addedSet = {};
        $(".question").each(function(){
            addedSet[$(this).data("questionid")]=true;
        });
        //加载所有试题，稍后加上分页与类型选择
        var searchParam={
            pageNum:1,
            pageSize:1000,
            keyword:null,
            type:null};
        $.post("/question/pageQuery.htm",searchParam,function(result,success){
            var json = JSON.parse(result);
            if(json.success){
                for(var i=0;i<json.data.length;i++){
                    var question = json.data[i];
                    if(addedSet[question.id]){
                        continue;
                    }
                    addSelectQuestion(json.data[i],i);
                }
                $("#questionsForSelect .modal_selected").click(function(){
                    var param = json.data[$(this).data("index")];
                    $(this).parents("tr:first").remove();
                    addQuestion(param);
                });
                $(".modal").modal();
            }
        });

        function addSelectQuestion(question,index){
            var html = ''
                    + '<tr class="modal_question" data-questionid="'+question.id+'">'
                    +'<td class="col-sm-1">'+question.id+'</td>'
                    +'<td class="col-sm-7">'+question.title+'</td>'
                    +'<td class="col-sm-2">'+question.score+'</td>'
                    +'<td class="col-sm-2"><a class="modal_selected" href="#" data-index="'+index+'">添加</a></td>'
                    +'</tr>';
            $("#questionsForSelect").append(html);
        }
    });

    function addQuestion(question){
        var html = ''
            + '<tr class="question" data-questionid="'+question.id+'">'
            +'<td class="col-sm-1">'+question.id+'</td>'
            +'<td class="col-sm-8">'+question.title+'</td>'
            +'<td class="col-sm-2"><input type="number" class="score"  style="width:60px" value="'+question.score+'"/></td>'
            +'<td class="col-sm-1"><a class="delete" href="#">删除</a></td>'
            +'</tr>';
        $("#btn_add_question_tr").before(html);
    }

    function parseParam(){
        var id = $("#testPaperId").val();

        var title = $("#testPaperTitle").val();
        if(title==""){
            alert("请完善试卷描述");
            return;
        }

        var $questions = $(".question");
        var questions = new Array();
        var score = 0;
        for(var i=0;i<$questions.length;i++){
            var sc = $($($questions[i]).find(".score")[0]).val();
            if(sc==""){
                alert("分数不能为空");
                return;
            }
            score = score + sc*1;
            questions.push({id:$($questions[i]).data("questionid"),score:sc});
        }
        questions = JSON.stringify(questions);

        return {
            id:id,
            title:title,
            questions:questions,
            score:score
        };

    }

    $("#save").click(function(e){
        var param = parseParam();
        if(!param){
            return;
        }
        $.post("testPaperSave.htm",param,function(result,sucess){
            var json = JSON.parse(result);
            if(json.success){
                alert("保存成功");
                window.location.href = "testPaperList.htm";
            }else{
                alert("保存失败,",json.message);
            }
        });
    });
</script>

<%@ include file="tail.jsp"%>
