<%@ page import="cn.orditech.enums.AuthorizationTypeEnum" %>
<%@ page import="cn.orditech.entity.User" %>
<%@ page import="cn.orditech.tool.RequestLocal" %><%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    User user = RequestLocal.get ().getUser ();
    boolean logined = user == null?false:true;
%>
<html>
<head>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />

    <title>考试管理系统</title>

    <script src="https://cdn.bootcss.com/jquery/2.2.0/jquery.min.js"></script>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <style>
        body{
            padding-left:10%;
            padding-right:10%;
        }
        .top{
            position: relative;
            width: 100%;
            height: 150px;
            background-image: url("/img/top_img.jpg");
        }
        .nav{
            width: 85%;
            float:left;
        }
        .login-mark {
            height: 42px;
            text-align: center;
            float: left;
            width: 15%;
            border-bottom: 1px solid #ddd;
        }
        .login-mark > a {
            margin-right: 2px;
            line-height: 40px;
            border: 1px solid transparent;
            border-radius: 4px 4px 0 0;
        }
        .content{
            float:left;
            width:100%;
            margin-left: auto;
            margin-right: auto;
        }
        .stroke {
            padding-top: 50px;
            text-align: center;
            color: white;
            -webkit-text-stroke: 1px black;
            letter-spacing: 0.4em;
        }
        a{
            cursor:pointer;
        }
    </style>
</head>
<body>
    <div class="top"><h1 class="stroke">安全考试管理系统</h1></div>
    <ul class="nav nav-tabs">
        <li role="presentation">
            <a href="/admin/userList.htm" data-level="<%=AuthorizationTypeEnum.ADMINISTRATOR.getLevel()%>">人员管理</a>
        </li>
        <li role="presentation">
            <a href="/question/questionList.htm" data-level="<%=AuthorizationTypeEnum.ADMINISTRATOR.getLevel()%>">试题库</a>
        </li>
        <li role="presentation">
            <a href="/question/questionEdit.htm" data-level="<%=AuthorizationTypeEnum.ADMINISTRATOR.getLevel()%>">编辑试题</a>
        </li>
        <li role="presentation">
            <a href="/testPaper/testPaperList.htm" data-level="<%=AuthorizationTypeEnum.GENERAL_STAFF.getLevel()%>">试卷库</a>
        </li>
        <li role="presentation">
            <a href="/testPaper/testPaperEdit.htm" data-level="<%=AuthorizationTypeEnum.ADMINISTRATOR.getLevel()%>">试卷编辑</a>
        </li>
        <li role="presentation">
            <a href="/test/testResultList.htm" data-level="<%=AuthorizationTypeEnum.GENERAL_STAFF.getLevel()%>">考试成绩</a>
        </li>
    </ul>
    <script>
        <%if(logined){%>
        var uri = window.location.href;
        var level = <%=user.getLevel()%>;
        $(".nav li a").each(function(){
            var parent = $(this).parent("li:first");
            if(uri.indexOf($(this).attr("href"))>-1){
                $(parent).addClass("active");
            }else{
                $(parent).removeClass("active");
            }
            if($(this).data("level")>level){
                $(parent).hide();
            }else{
                $(parent).show();
            }
        });
        <%}%>
    </script>
    <div class="login-mark">
        <%if(logined){%>
        <a disabled="true"><%=user.getName ()%>,</a>
        <a href="/logout.htm">退出</a>
        <%}else{%>
        <a href="/register.htm">注册</a>
        <a href="/login.htm">登录</a>
        <%}%>
    </div>
    <div class="content">

