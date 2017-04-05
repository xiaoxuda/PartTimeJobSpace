<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<style>
    .login-form{
        width:320px;
        margin-left:auto;
        margin-right:auto;
        margin-top:75px;
    }
</style>
<div class="login-form">
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-sm-3" for="userAccount">用户名:</label>
        <div class=" col-sm-9">
            <input id="userAccount" name="account" class="form-control" placeholder=""/>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3" for="userPassword">密码:</label>
        <div class=" col-sm-9">
            <input id="userPassword" name="password" class="form-control" type="password" placeholder=""/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-3 col-sm-9">
            <div class="checkbox">
                <label>
                    <input type="checkbox" id="rememberme"> 记住我
                </label>
            </div>
        </div>
    </div>
</form>
<div class="form-group col-sm-offset-3 col-sm-4">
    <button id="login" class="btn btn-default">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
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
</div>
<%@ include file="tail.jsp"%>