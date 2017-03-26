<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
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
<form class="form-inline" action="<c:url value="/register.htm" />" method="post">
    <div class="form-group">
        <label class="control-label" for="userAccount">用户名</label>
        <input name="account" class="form-control" id="userAccount" placeholder="张三">
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
    <button id="register" class="btn btn-default">登录</button>
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
        var name = $("#userName").val();
        if(name ==""){
            alert("请输入姓名");
            return;
        }
        var department = $("#userDepartment").val();
        if(department ==""){
            alert("请选择部门")
            return;
        }
        var type = $("#userType").val();
        var sex = $("#sex1").val();
        if(sex ==""){
            sex = $("#sex2").val();
        }

        var rememberMe = $("#rememberme").val();
        return {
            account:account,
            password:password,
            name:name,
            department:department,
            type:type,
            sex:sex,
            rememberMe:rememberMe
        };

    }

    $("#register").click(function(e){
        var param = parseParam();
        $.get("register.htm",param,function(sucess,result){
            if(result.success){
                alert("保存成功");
                window.location.href = "register.htm";
            } else{
                alert("保存失败",result.message);
            }

        });
    });

</script>

<%@ include file="tail.jsp"%>