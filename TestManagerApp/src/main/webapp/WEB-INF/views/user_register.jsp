<%@ page import="cn.orditech.enums.SexTypeEnum" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.orditech.entity.Department" %>
<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<%
    List<Department> departments = (List<Department>)request.getAttribute ("departments");
%>
<style>
    .register-form{
        width:320px;
        margin-left:auto;
        margin-right:auto;
        margin-top:75px;
    }
</style>
<div class="register-form">
    <form class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-4" for="userDepartment">部门:</label>
            <div class=" col-sm-8">
                <select id="userDepartment" class="form-control" value="">
                    <option value=""></option>
                    <%for(Department department:departments){%>
                    <option value="<%=department.getCode()%>"><%=department.getName()%></option>
                    <%}%>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4" for="userName">姓名:</label>
            <div class=" col-sm-8">
                <input id="userName" name="name" class="form-control" placeholder=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4">性别:</label>
            <label class="radio-inline" style="margin-left:13px;">
                <input type="radio" name="sexRadio" class="sex" value="<%=SexTypeEnum.MAN.getType()%>"> 男
            </label>
            <label class="radio-inline">
                <input type="radio" name="sexRadio" class="sex" value="<%=SexTypeEnum.WOMAN.getType()%>"> 女
            </label>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4" for="userAccount">工号:</label>
            <div class=" col-sm-8">
                <input id="userAccount" name="account" class="form-control" placeholder=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4" for="userPassword">密码:</label>
            <div class=" col-sm-8">
                <input id="userPassword" name="password" class="form-control" type="password" placeholder=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-4" for="userPasswordConfirm">确认密码:</label>
            <div class=" col-sm-8">
                <input id="userPasswordConfirm" name="password" class="form-control" type="password" placeholder=""/>
            </div>
        </div>

    </form>
    <div class="form-group col-sm-offset-4 col-sm-4">
        <button id="register" class="btn btn-default">注&nbsp;&nbsp;&nbsp;&nbsp;册</button>
    </div>
<script type="text/javascript">register
    $("#userDepartment option").click(function(){
        $("#userDepartment").attr("value",$(this).attr("value"));
    });

    function parseParam(){
//        var department = $("#userDepartment").attr("value");
        var department = $("#userDepartment").val();
        if(department==""){
            alert("请选择所属部门");
            return;
        }
        var name = $("#userName").val();
        if(name ==""){
            alert("请输入姓名");
            return;
        }
        var sexEle=$(".sex:checked");
        if(sexEle.length==0){
            alert("请确定性别");
            return;
        }
        var sex = $(sexEle).val();
        var account = $("#userAccount").val();
        if(account==""){
            alert("请输入工号");
            return;
        }
        var password = $("#userPassword").val();
        if(password==""){
            alert("请输入密码");
            return;
        }
        var passwordConfirm = $("#userPasswordConfirm").val();
        if(password!=passwordConfirm){
            alert("密码不一致");
            return;
        }

        return {
            department:department,
            account:account,
            name:name,
            sex:sex,
            password:password
        };

    }

    $("#register").click(function(e){
        var param = parseParam();
        if(!param){
            return;
        }
        $.post("doRegister.htm",param,function(result,success){
            var json = JSON.parse(result);
            if(json.success){
                alert("注册成功");
                window.location.href = "login.htm";
            }else{
                alert("注册失败,",json.message);
            }
        });
    });

</script>
</div>
<%@ include file="tail.jsp"%>