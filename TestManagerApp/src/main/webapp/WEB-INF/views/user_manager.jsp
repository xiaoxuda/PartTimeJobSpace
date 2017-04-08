<%@ page import="java.util.List" %>
<%@ page import="cn.orditech.enums.SexTypeEnum" %>
<%@ page import="cn.orditech.entity.Department" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/4/7
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<%
    List<User> userList = (List<User>)request.getAttribute ("userList");
    Map<String,Department> departmentMap = (Map<String,Department>)request.getAttribute ("departmentMap");
%>

<table class="table table-bordered table-hover table-condensed">
    <thead>
    <td>工号</td>
    <td>姓名</td>
    <td>性别</td>
    <td>所属部门</td>
    <td>职位</td>
    <td></td>
    </thead>
    <tbody>
    <%
        for(User userItem:userList){
            Department department = departmentMap.get(userItem.getDepartment ());
            AuthorizationTypeEnum authLevel = AuthorizationTypeEnum.getByLevel (userItem.getLevel ());
    %>
    <tr>
        <td><%=userItem.getAccount()%></td>
        <td><%=userItem.getName()%></td>
        <td><%=SexTypeEnum.getTypeEnum (userItem.getSex ()).getDesc()%></td>
        <td><%=department.getName()%></td>
        <td><%=authLevel.getDesc()%></td>
        <td data-uid="<%=userItem.getId()%>">
            <a class="auth" id="confirm_auth" style="display: <%=(authLevel==AuthorizationTypeEnum.GENERAL_STAFF?"inline":"none")%>">设置主管</a>
            <a class="auth" id="cancel_auth" style="display: <%=(authLevel==AuthorizationTypeEnum.MANAGER_STAFF?"inline":"none")%>">撤销主管</a>
        </td>
    </tr>
    <%}%>
    </tbody>
</table>
<script>
    $("#confirm_auth").click(function(e){
        e.preventDefault();
        var suerId = $(this).parent("td:first").data("uid");
        $.post("confirmAuth.htm",{userId:suerId},function(result,sucess){
            var json = JSON.parse(result);
            if(json.success){
                alert("设置成功");
                $("#confirm_auth").hide();
                $("#cancel_auth").show();
            }else{
                alert("设置失败，"+json.message);
            }
        });
    });
    $("#cancel_auth").click(function(e){
        e.preventDefault();
        var suerId = $(this).parent("td:first").data("uid");
        $.post("cancelAuth.htm",{userId:suerId},function(result,sucess){
            var json = JSON.parse(result);
            if(json.success){
                alert("设置成功");
                $("#cancel_auth").hide();
                $("#confirm_auth").show();
            }else{
                alert("设置失败，"+json.message);
            }
        });
    });
</script>
<%@ include file="tail.jsp"%>