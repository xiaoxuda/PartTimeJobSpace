<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="cn.orditech.entity.TestPaper" %>
<%@ page import="java.util.List" %>
<%
    List<TestPaper> questionList = (List<TestPaper>)request.getAttribute ("testPaperList");
%>
<%@ include file="header.jsp"%>
<table class="table table-bordered table-hover table-condensed">
    <thead>
        <td>ID</td>
        <td>试卷描述</td>
        <td>总分数</td>
        <td>操作</td>
    </thead>
    <tbody>
    <%for(TestPaper testPaper:questionList){%>
        <tr>
            <td><%=testPaper.getId()%></td>
            <td><%=testPaper.getTitle()%></td>
            <td><%=testPaper.getScore()%></td>
            <td><a href="testPaperEdit.htm?id=<%=testPaper.getId()%>">编辑</a></td>
        </tr>
    <%}%>
    </tbody>
</table>

<%@ include file="tail.jsp"%>
