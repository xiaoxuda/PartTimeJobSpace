<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
    <div class="logintop">
        <span>欢迎登录飞龙公司ERP运营管理系统</span>
        <ul>
            <li>
                <a href="#">帮助</a>
            </li>
            <li>
                <a href="#">关于</a>
            </li>
        </ul>
    </div>
    <form action="<c:url value="/register" />" method="post">
        <div class="loginbody">
            <!--span class="systemlogo"></span-->

            <div class="loginbox">
                <ul>
                    <li>
                        <input name="account" type="text" class="loginuser" value="" />
                    </li>
                    <li>
                        <input name="password" type="password" class="loginpwd" value="" />
                    </li>
                    <li>
                        <input name="name" type="text" value="" />
                    </li>
                    <li>
                        <input type="radio" name="sex" value="男" checked>男
                        <br>
                        <input type="radio" name="sex" value="女">女
                    </li>

                    <li>
                        <input name="" type="submit" class="loginbtn" value="登录" />
                        <input name="" type="button" class="loginbtn" value="取消" />
                    </li>
                </ul>
            </div>
        </div>
    </form>

<%@ include file="tail.jsp"%>