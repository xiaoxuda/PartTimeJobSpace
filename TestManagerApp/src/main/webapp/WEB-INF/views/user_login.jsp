<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
    <div class="logintop">
        <span>欢迎登录考试管理系统</span>
        <input type="hidden" name="message" id="message" value="${message}" />
    </div>
<form class="form-inline">
    <div class="form-group">
        <label class="control-label" for="userAccount">用户名</label>
        <input name="account" class="form-control" id="userAccount" placeholder="">
    </div>
    <br>
    <br>
    <div class="form-group">
        <label class="control-label" for="userPassword">密码</label>
        <input name="password" class="form-control" id="userPassword" type="password" placeholder="**">
    </div>

    <br>
    <br>
    <div class="checkbox">
        <label>
            <input type="checkbox" id="rememberme"> 记住我
        </label>
    </div>
</form>
<div class="col-sm-offset-1 col-sm-2">
    <button id="login" class="btn btn-default">登录</button>
</div>

<script type="text/javascript">
    function parseParam(){
        var account = $("#userAccount").val();
        if(account==""){
            alert("用户名不能为空");
            return;
        }

        var password = $("#userPassword").val();
        if(password==""){
            alert("密码不能为空");
            return;
        }

        var rememberMe = $("#rememberme").val();

        return {
            account:account,
            password:password,
            rememberMe:rememberMe
        };

    }

    $("#login").click(function(e){
        var param = parseParam();
        $.post("doLogin.htm",param,function(result,sucess){
            var json = JSON.parse(result);
            if(json.success){
                alert("登录成功");
                window.location.href = "/test/testResultList.htm";
            } else{
                alert("登录失败",json.message);
            }

        });
    });

</script>

<%@ include file="tail.jsp"%>