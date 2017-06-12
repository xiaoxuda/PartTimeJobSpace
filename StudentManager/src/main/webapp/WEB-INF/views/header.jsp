<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
    <div class="top"><h1 class="stroke">学员成绩管理系统</h1></div>
    <ul class="nav nav-tabs" style="display:none;">
        <li role="presentation">
            <a href="/student/list.htm">学生管理</a>
        </li>
        <li>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<p id="divtime"></p>
        </li>
    </ul>
    <script>
        var uri = window.location.href;
        $(".nav li a").each(function(){
            var parent = $(this).parent("li:first");
            if(uri.indexOf($(this).attr("href"))>-1){
                $(parent).addClass("active");
            }else{
                $(parent).removeClass("active");
            }
        });
    </script>

    <div class="content">

