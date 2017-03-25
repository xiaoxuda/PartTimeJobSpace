<%@ page import="cn.orditech.entity.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.orditech.enums.QuestionTypeEnum" %><%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Question> questionList = (List<Question>)request.getAttribute ("questionList");
%>
<%@ include file="header.jsp"%>

<table class="table table-bordered table-hover table-condensed">
    <thead>
        <td>ID</td>
        <td>试题类型</td>
        <td>试题描述</td>
        <td>默认分数</td>
        <td>操作</td>
    </thead>
    <tbody>
    <%for(Question question:questionList){%>
        <tr>
            <td><%=question.getId()%></td>
            <td><%=QuestionTypeEnum.getTypeEnum (question.getType ()).getDesc()%></td>
            <td><%=question.getTitle()%></td>
            <td><%=question.getScore()%></td>
            <td><a href="questionEdit.htm?id=<%=question.getId()%>">编辑</a></td>
        </tr>
    <%}%>
    </tbody>
</table>

<%@ include file="tail.jsp"%>
