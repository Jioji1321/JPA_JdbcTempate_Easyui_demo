<%--
  Created by IntelliJ IDEA.
  Student: jioji
  Date: 2019/08/19 0019
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../include_head.jsp" %>
<head>
    <title>Edit</title>
</head>
<body style="margin:0px 0px 100px 0px;padding: 0;">
<div id="main">
    <form id="input-form" method="post">
        <table id="input-table">
            <tr>
                <td hidden="true">Id：</td>&ensp;&ensp;
                <td hidden="true"><input class="easyui-textbox" id="id" name="id" value="${course.id}" readonly="readonly"></td>
                <td>课程名：</td>&ensp;&ensp;
                <td><input class="easyui-textbox" id="courseName" name="courseName" required="true" value="${course.courseName}"></td>
                <td>年度：</td>
                <td><input class="easyui-textbox" id="year" name="year" required="true" value="${course.year}"></td>
            </tr>
            <tr>
                <td>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()">提交</a>
                    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="parent.$('#opened-windows').window('close');">关闭</a>
                </td>
            </tr>
            </td>
        </table>
    </form>
</div>

<script type="text/javascript">
    var id = document.getElementById("id").value;
    //数据加载
    $("#input-form").form("load", '/course/showCourseDetail?id=' + id);


    function save() {
        //将form中的数据转为json对象，可通过ajax发送
        var formObject = {};
        var formArray = $('#input-form').serializeArray();
        $.each(formArray, function (i, item) {
            formObject[item.name] = item.value;
        });
        var formJson = JSON.stringify(formObject);
        var jsonFormObj = eval('(' + formJson + ')');
        console.log(jsonFormObj);
        $.ajax({
            type: 'POST',
            url: '/course/saveCourse',
            data: jsonFormObj,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if(data.status == '200'){
                    $.messager.alert('保存成功','保存成功！','info');
                    parent.$('#opened-windows').window('close');
                    parent.$('#display-grid').datagrid('load');
                }
            },
            error: function (error) {
                console.log(error);
                $.messager.alert('提示', '保存出错！','error');
            }
        });
    }
</script>
</body>
</html>
