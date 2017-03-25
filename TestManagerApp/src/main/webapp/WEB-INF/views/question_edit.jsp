<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/3/25
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="cn.orditech.entity.Question" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%
    boolean isEdit = request.getAttribute ("isEdit")==null?false:true;
    Question question = (Question)request.getAttribute ("question");
%>
<%@ include file="header.jsp"%>
<form id="selectType">
    <input id="questionId" value="" style="display:none;"/>
    <div class="form-group" >
        <label for="questionType" class="control-label">试题类型</label>
        <select id="questionType" name="type" class="form-control">
            <option value="">选择类型</option>
            <option value="1">选择题</option>
            <option value="2">判断题</option>
        </select>
    </div>
    <div class="form-group">
        <label for="questionTitle" class="control-label">试题描述</label>
        <textarea id="questionTitle" name="title" class="form-control" rows="3"></textarea>
    </div>
    <div id="questiontype_1" class="question-type form-group" style="display:none;">
        <label class="control-label">可选项(请选中正确答案)</label>
        <div class="input-group">
            <div class="input-group-addon">A</div>
            <input type="text" class="option form-control" data-mark="A" placeholder="请输入选项描述">
            <div class="input-group-addon">
                <input type="radio" name="selectQuestionRadios" class="answer" value="A"/>
            </div>
        </div>
        <div class="input-group">
            <div class="input-group-addon">B</div>
            <input type="text" class="option form-control" data-mark="B" placeholder="请输入选项描述">
            <div class="input-group-addon">
                <input type="radio" name="selectQuestionRadios" class="answer" value="B"/>
            </div>
        </div>
        <div class="input-group">
            <div class="input-group-addon">C</div>
            <input type="text" class="option form-control" data-mark="C" placeholder="请输入选项描述">
            <div class="input-group-addon">
                <input type="radio" name="selectQuestionRadios" class="answer" value="C"/>
            </div>
        </div>
        <div class="input-group">
            <div class="input-group-addon">D</div>
            <input type="text" class="option form-control" data-mark="D" placeholder="请输入选项描述">
            <div class="input-group-addon">
                <input type="radio" name="selectQuestionRadios" class="answer" value="D"/>
            </div>
        </div>
    </div>
    <div id="questiontype_2" class="question-type form-group" style="display:none;">
        <label class="control-label">可选项</label><br/>
        <label class="radio-inline">
            <input type="text" class="option" data-mark="R" style="display:none;" value="正确"/>
            <input type="radio" name="judgeQuestionRadios" class="answer" value="R"/>正确
        </label>
        <label class="radio-inline">
            <input type="text" class="option" data-mark="W" style="display:none;" value="错误"/>
            <input type="radio" name="judgeQuestionRadios" class="answer" value="W"/>错误
        </label>
    </div>
</form>
<div class="col-sm-offset-10 col-sm-2">
    <button id="save" class="btn btn-default">保&nbsp;&nbsp;&nbsp;&nbsp;存</button>
</div>
<script type="text/javascript">
$(function(){
    var isEdit = <%=isEdit %>;
    var question = {};
    <%if(isEdit){%>question=<%=JSON.toJSONString(question)%>;<%}%>

    $("#questionType option").click(function(e){
        $(".question-type").hide();
        $("#questiontype_"+$(this).attr("value")).show();
        $("#questionType").attr("value",$(this).attr("value"));
    });


    if(isEdit){
        $("#questionId").val(question.id);
        $("#questionType").val(question.type);
        $("#questionType option[value='"+question.type+"']").click();
        $("#questionType").attr("disabled",true);
        $("#questionTitle").val(question.title);
        var options = JSON.parse(question.options);
        $("#questiontype_"+question.type+" .option").each(function(){
            var mark = $(this).data("mark");
            for(var i=0;i<options.length;i++){
                var option = options[i];
                if(mark == option.mark){
                    $(this).val(option.value);
                    break;
                }
            }
        });
        var answer = question.answer.split(",");
        $("#questiontype_"+question.type+" .answer").each(function(){
            var value = $(this).val();
            for(var i=0;i<answer.length;i++){
                if(value == answer[i]){
                    $(this).attr("checked",true);
                    break;
                }
            }
        });
    }

    function parseParam(){
        var id = $("#questionId").val();

        var title = $("#questionTitle").val();
        if(title==""){
            alert("请完善试题描述");
            return;
        }

        var type = $("#questionType").val();
        if(type==""){
            alert("请选择试题类型");
            return;
        }

        var options = new Array();
        var $options = $("#questiontype_"+$("#questionType").attr("value")+" .option");
        $options.each(function(){
            var option = {};
            option.mark=$(this).data("mark");
            option.value=$(this).val();
            if(!option.value){
                return;
            }
            options.push(option);
        });
        if(options.length!=$options.length){
            alert("请完善试题答案");
            return;
        }
        options = JSON.stringify(options);

        var answer = new Array();
        $("#questiontype_"+$("#questionType").attr("value")+" .answer:checked").each(function(){
            answer.push($(this).val());
        });
        if(answer.length==0){
            alert("请选中正确答案");
            return;
        }
        answer = answer.join(",");

        return {
            id:id,
            type:type,
            title:title,
            options:options,
            answer:answer
        };

    }

    $("#save").click(function(e){
        var param = parseParam();
        if(!param){
            return;
        }
        $.post("questionSave.htm",param,function(result,sucess){
            var json = JSON.parse(result);
            if(json.success){
                alert("保存成功");
                window.location.href = "questionAdd.htm";
            }else{
                alert("保存失败,",json.message);
            }
        });
    });
});
</script>
<%@ include file="tail.jsp"%>