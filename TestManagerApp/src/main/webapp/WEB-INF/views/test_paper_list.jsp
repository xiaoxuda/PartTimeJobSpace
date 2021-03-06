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
<%@ page import="cn.orditech.enums.AuthorizationTypeEnum" %>
<%@ page import="cn.orditech.tool.RequestLocal" %>
<%
    List<TestPaper> questionList = (List<TestPaper>)request.getAttribute ("testPaperList");
    boolean author = RequestLocal.get().getUser ().getLevel ()>=AuthorizationTypeEnum.ADMINISTRATOR.getLevel ();
%>
<%@ include file="header.jsp"%>
<table class="table table-bordered table-hover table-condensed">
    <thead>
        <td>ID</td>
        <td>试卷描述</td>
        <td>总分数</td>
        <td>操作&nbsp;&nbsp;&nbsp;&nbsp;<a href="testPaperEdit.htm">添加试卷</a></td>
    </thead>
    <tbody>
    <%for(TestPaper testPaper:questionList){%>
        <tr>
            <td><%=testPaper.getId()%></td>
            <td><%=testPaper.getTitle()%></td>
            <td><%=testPaper.getScore()%></td>
            <td>
                <%if(author){%><a href="testPaperEdit.htm?id=<%=testPaper.getId()%>">编辑&nbsp;&nbsp;</a><%}%>
                <%--<%if(author){%><a href="testPaperDelete.htm?id=<%=testPaper.getId()%>">删除&nbsp;&nbsp;</a><%}%>--%>
                <a href="/test/startTest.htm?id=<%=testPaper.getId()%>">开始测试</a>
            </td>
        </tr>
    <%}%>
    </tbody>
</table>
<div>
    <form id="uploadForm" enctype="multipart/form-data" method="post" style="width:400px;display:inline;">
        <input type="file" name="file" style="display:inline;" required />
    </form>
    <button id="upload" class="btn">上传</button>
</div>
<script>
    var fileData = new FormData(document.getElementById("testFile"));
    $("#upload").click(function(){
        var formData = new FormData($( "#uploadForm" )[0]);
        $.ajax({
            url: '/testPaper/uploadTestPaper.htm' ,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                alert(returndata);
                window.location.href = "testPaperList.htm";
            },
            error: function (returndata) {
                alert(returndata);
            }
        });
    });
</script>
<%@ include file="tail.jsp"%>
