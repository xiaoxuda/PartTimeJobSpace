<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="cn.orditech.entity.TestPaper" %>
<%@ page import="cn.orditech.enums.QuestionTypeEnum" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="java.util.Collection" %>
<%@ page import="com.alibaba.fastjson.JSONArray" %>
<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/31
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%
    TestPaper testPaper = (TestPaper) request.getAttribute ("testPaper");
    Collection<JSONObject> questions = (Collection<JSONObject>) request.getAttribute ("questions");
%>
<%@ include file="header.jsp" %>
<h3 style="text-align:center;"><%=testPaper.getTitle ()%></h3>
<input id="testId" value="<%=testPaper.getId()%>" style="display:none;"/>
<%
    int i = 1;
    for (JSONObject question : questions) {
        JSONArray options = question.getJSONArray ("options");
%>
<div class="question form-group" data-questionid="<%=question.getLong("id")%>">
    <label class="control-label"><%=i%>.<%=question.getString ("title")%>?</label>
<%if (question.getInteger ("type") == QuestionTypeEnum.SINGLE_SELECT.getType ()) {%>
    <%for(int j=0;j<options.size ();j++){JSONObject option = options.getJSONObject(j);%>
    <div class="radio"><label>
    <input type="radio" name="radio_<%=i%>" class="answer" value="<%=option.getString("mark")%>"/>
    <%=option.getString("mark")%>.<%=option.getString("value")%>。
    </label></div>
    <%}%>
<%} else {%>
    <br/>
    <label class="radio-inline">
        <input type="radio" name="radio_<%=i%>" class="answer" value="R"/>正确
    </label>
    <label class="radio-inline">
        <input type="radio" name="radio_<%=i%>" class="answer" value="W"/>错误
    </label>
<%}%>
</div>
<%++i;}%>
<div class="col-sm-offset-10 col-sm-2">
    <button id="save" class="btn btn-default">保&nbsp;&nbsp;&nbsp;&nbsp;存</button>
</div>
<script type="text/javascript">
$(function(){
    function parseParam(){
        var testId = $("#testId").val();
        var answers = new Array();
        $(".question").each(function(){
            var questionId = $(this).data("questionid");
            var selectedRadio = $(this).find(".answer:checked");
            if(selectedRadio){
                answers.push({id:questionId,answer:$(selectedRadio).attr("value")});
            }
        });
        return {testId:testId,answer:JSON.stringify(answers)};
    }

    $("#save").click(function(e){
        if(!confirm("确认现在交卷吗？")){
            return;
        }
        var param = parseParam();
        if(!param){
            return;
        }

        $.post("submitTest.htm",param,function(result,sucess){
            var json = JSON.parse(result);
            if(json.success){
                alert("提交成功");
                window.location.href = "testResultList.htm";
            }else{
                alert("提交失败,",json.message);
            }
        });
    });
});
</script>
<%@ include file="tail.jsp" %>
