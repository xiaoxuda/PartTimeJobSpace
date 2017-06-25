<%@ page import="cn.orditech.enums.SexTypeEnum" %>
<%--
  Created by IntelliJ IDEA.
  User: kimi
  Date: 2017/6/11
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="header.jsp"%>
<table class="table table-bordered table-hover table-condensed">
    <thead>
    <td>ID</td>
    <td>姓名</td>
    <td>性别</td>
    <td>学校</td>
    <td>操作&nbsp;&nbsp;&nbsp;&nbsp;<a id="addStudent">录入信息</a></td>
    </thead>
    <tbody id="studentsTable">

    </tbody>
</table>

<div id="editModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">学生信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <input id="studentId" name="studentId" class="form-control" style="display:none" value="" />
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="organization">机构:</label>
                        <div class=" col-sm-9">
                            <input id="organization" class="form-control" disabled="true" value="京翰教育" />
                            <input id="orgId" name="orgId" class="form-control" style="display:none" value="1" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="name">姓名:</label>
                        <div class=" col-sm-9">
                            <input id="name" name="name" class="form-control" placeholder=""/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3">性别:</label>
                        <label class="radio-inline" style="margin-left:13px;">
                            <input type="radio" name="sexRadio" class="sex" value="<%=SexTypeEnum.MAN.getType()%>"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="sexRadio" class="sex" value="<%=SexTypeEnum.WOMAN.getType()%>"> 女
                        </label>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-3" for="school">学校:</label>
                        <div class=" col-sm-9">
                            <input id="school" name="school" class="form-control" placeholder=""/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button id="modalSaveBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    var sexMap={};
    <%for(SexTypeEnum sex:SexTypeEnum.values ()){%>
        sexMap[<%=sex.getType()%>]='<%=sex.getDesc()%>';
    <%}%>

    var students=[];

    function getStudents(){
        $.post("getStudents.htm",
                {},
                function(data,textStatus){
                    var json = JSON.parse(data);
                    if(json.success){
                        students = json.data;
                        showStudents(students);
                    }
                }
        );
    }
    function showStudents(students){
        var tbody = "";
        for(var i=0;i<students.length;i++){
            var student = students[i];
            tbody+= "<tr>"+
                    "<td>"+student.id+"</td>"+
                    "<td>"+student.name+"</td>"+
                    "<td>"+sexMap[student.sex]+"</td>"+
                    "<td>"+student.school+"</td>"+
                    "<td><a class='studentEdit' data-index='"+i+"'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;" +
                    "<a class='studentDelete' data-index='"+i+"'>删除</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "<a href='../score/list.htm?studentId="+student.id+"'>成绩</a>&nbsp;&nbsp;&nbsp;&nbsp;"+
                    "<a href='../score/curve.htm?studentId="+student.id+"'>成绩曲线</a></td>"+
                    "</tr>";
        }
        $("#studentsTable").html(tbody);
        bindItemEvents();
    }
    function bindItemEvents(){
        $(".studentEdit").click(function(e){
            e.preventDefault();
            var student = students[$(this).data("index")];
            cleanModal();
            initModal(student);
            $("#editModal").modal('show');
        });
        $(".studentDelete").click(function(e){
            e.preventDefault();
            var student = students[$(this).data("index")];
            if(!confirm("确实要删除"+student.name+"(ID="+student.id+")的所有信息吗？")){
                return;
            }
            $.post(
                "delete.htm",
                {studentId:student.id},
                function(data,textStatus){
                    var json = JSON.parse(data);
                    if(json.success){
                        getStudents();
                    }
                }
            );
        });
    }
    function cleanModal(){
        $("#studentId").val("");
        $("#name").val("");
        $(".sex").removeAttr("checked");
        $("#school").val("");
    }
    function initModal(student){
        $("#studentId").val(student.id);
        $("#name").val(student.name);
        $(".sex[value="+student.sex+"]").click();
        $("#school").val(student.school);
    }

    $("#addStudent").click(function(e){
        e.preventDefault();
        cleanModal();
        $("#editModal").modal('show');
    });

    function validateStudent(){
        var id=$("#studentId").val();
        var student={};
        if(id.length>0){
            student.id=id;
        }

        var orgId=$("#orgId").val();
        if(orgId.length==0){
            alert("请选择机构");
            return;
        }
        student.orgId = orgId;


        var name=$("#name").val();
        if(name.length==0){
            alert("请输入姓名");
            return;
        }
        student.name = name;

        var sexEle=$(".sex:checked");
        if(sexEle.length==0){
            alert("请选择性别");
            return;
        }
        student.sex = $(sexEle).attr("value");

        var school = $("#school").val();
        if(school.length==0){
            alert("请输入学校");
            return;
        }
        student.school = school;

        return student;
    }
    $("#modalSaveBtn").click(function(e){
        var student=validateStudent();
        $.post("save.htm",student,function(data,textStatus){
            $("#editModal").modal('hide');
            var json = JSON.parse(data);
            if(json.success){
                getStudents();
            }
        });
    });

    $("#testDate").datetimepicker({
        language:'zh-CN',
        format: 'yyyy-mm-dd',
        startView:2,
        minView:2,
        autoclose:true,
        todayHighlight:true
    });

    getStudents();
</script>
<%@ include file="tail.jsp"%>
