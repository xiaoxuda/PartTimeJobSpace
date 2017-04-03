<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>考试管理系统</title>
    <meta http-equiv="charset" content="UTF-8">

    <script src="https://cdn.bootcss.com/jquery/2.2.0/jquery.min.js"></script>

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

    <style>
        .top{
            position: relative;
            width: 100%;
            height: 150px;
        }
        .left_nav{
            position: relative;
            float:left;
            width: 15%;
        }
        .content{
            float:left;
            width:80%;
        }
        .sub_content {
            width: 800px;
            margin-left: auto;
            margin-right: auto;
        }

    </style>
</head>
<body>
    <div class="top"></div>
    <div class="left_nav">
        <a href="/question/questionList.htm" data-level="0">试题库</a><br/>
        <a href="/question/questionEdit.htm" data-level="0">编辑试题</a><br/>
        <a href="/testPaper/testPaperList.htm" data-level="0">试卷库</a><br/>
        <a href="/testPaper/testPaperAdd.htm" data-level="0">添加试卷</a><br/>
        <a href="/test/testResultList.htm" data-level="0">考试成绩</a><br/>
    </div>
    <div class="content"><div class="sub_content">

