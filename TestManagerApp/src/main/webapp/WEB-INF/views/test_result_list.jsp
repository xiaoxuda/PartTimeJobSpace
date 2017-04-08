<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.alibaba.fastjson.JSONObject" %>
<%@ page import="java.util.List" %>
<%
    List<JSONObject> testResultList = (List<JSONObject>)request.getAttribute ("testResultList");
%>
<%@ include file="header.jsp"%>
<table class="table table-bordered table-hover table-condensed">
    <thead>
        <td>试卷ID</td>
        <td>试卷名称</td>
        <td>用户ID</td>
        <td>用户名称</td>
        <td>得分</td>
    </thead>
    <tbody>
    <%for(JSONObject json:testResultList){%>
        <tr>
            <td><%=json.getLong("testId")%></td>
            <td><%=json.getString("title")%></td>
            <td><%=json.getLong("userId")%></td>
            <td><%=json.getString("userName")%></td>
            <td><%=json.getInteger ("score")%></td>
        </tr>
    <%}%>
    </tbody>
</table>

<%@ include file="tail.jsp"%>
