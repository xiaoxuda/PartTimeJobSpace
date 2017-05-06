<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/17
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<script>
    function GetCookieByName(name) {
        //获取cookie字符串
        var strCookie = document.cookie;
        //将多cookie切割为多个名/值对
        var arrCookie = strCookie.split("; ");
        var cookieValue;
        //遍历cookie数组，处理每个cookie对
        for (var i = 0; i < arrCookie.length; i++) {
            var arr = arrCookie[i].split("=");
            //找到名称为name的cookie，并返回它的值
            if (name == arr[0]) {
                cookieValue = arr[1];
                break;
            }
        }
        return cookieValue;
    }
    function Change(m, s) {
        s = s - 1;
        if (s < 0) {
            s = 60 + s;
            m = m - 1;
        }
        if (m == 10 && s == 0) {
//            document.getElementById("input1").setAttribute("value","m:s"+m+":"+s)
            alert("离答题结束还有10分钟，请尽快完成题目！");
        }
        document.getElementById("divtime").innerHTML = "考试剩余时间："+m + "分" + s + "秒";
        document.cookie = "m=" + m;
        document.cookie = "s=" + s;
        setTimeout(function() {
             Change(m,s);
            }, 1000);
    }
    window.onload = function() {
    //SetCookie(20, 20);
        alert("开始");
    var m = GetCookieByName("m");
    var s = GetCookieByName("s");
    Change(m, s);
}
</script>

<div class="showTime">
    <label id="label1" value="剩余时间"></label>
    <%--剩余时间<input type="text" id="input1" value="">--%>
    <p id="divtime"></p>
</div>
<%@ include file="tail.jsp"%>
