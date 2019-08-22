<%--
  Created by IntelliJ IDEA.
  Student: jioji
  Date: 2019/08/17 0017
  Time: 19:36
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
                <td hidden="true">Id：</td>
                <td hidden="true"><input class="easyui-textbox" id="id" name="id" value="${choice.id}" readonly="readonly"></td>
                <td hidden="true"><input class="easyui-textbox" id="studentId" name="studentId" value="${choice.studentId}" readonly="readonly"></td>
                <td>学生：</td>
                <%--<td>
                    <select id="student_add" class="easyui-combogrid" style="width:250px" data-options="
                        panelWidth: 500,
                        idField: 'id',
                        textField: 'studentName',
                        url: '/student/getStudentData',
                        method: 'get',
                        columns: [[
                            {field:'id',title:'用户编号',width:80},
                            {field:'studentName',title:'用户名',width:120},
                            {field:'age',title:'年龄',width:80,align:'right'}
                        ]],
                        fitColumns: true
                    " readonly="readonly">
                    </select>
                </td>--%>
                <td><input class="easyui-textbox" id="studentName" name="studentName" readonly="readonly" value="${choice.studentName}"></td>
                <td>选择了课程：</td>
                <td hidden="true"><input class="easyui-textbox" id="courseNameHidden" name="courseNameHidden" value="${choice.courseName}" readonly="readonly"></td>
                <%--<td>
                    <input id="courseName" name="courseName" style="width:250px;">
                </td>--%>
                <td>
                    <select id="courseName" name="courseName" style="width:250px;" class="easyui-combobox" data-options="
                         required:true,
                         panelHeight:'100',
                         url: '/course/getCourseNameComboBoxData',
                         valueField: 'id',
                         textField: 'text',
                         onLoadSuccess: function(){
                            $('#courseName').combobox('select',$('#courseNameHidden').val());
                         }
                    "></select>
                </td>

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

    /*$('#courseName').combobox({
        required:true,
        panelHeight:'100',
        url: '/course/getCourseNameComboBoxData',
        valueField: 'id',
        textField: 'text',
    });
    $('#courseName').combobox('setValue','${choice.courseName}');*/

    var id = document.getElementById("id").value;
    //数据加载
    $("#input-form").form("load", '/choice/selectChoiceById?id=' + id);

    function save() {

        var id = $('#id').val();
        console.log(id);
        var course = $('#courseName').combogrid('grid'); //获取datagrid对象
        var cou_obj = course.datagrid('getSelected'); //获取被选行对象
        console.log(cou_obj);

        if(cou_obj == null){
            $.messager.alert('确认', '请输入必填项!');
            return false;
        }

        var formObject = {};
        formObject['id'] = id;
        formObject['studentId'] = $('#studentId').val();
        formObject['courseId'] = cou_obj.id;
        var formJson = JSON.stringify(formObject);
        var jsonFormObj = eval('(' + formJson + ')');
        console.log(jsonFormObj);
        $.ajax({
            type: 'POST',
            url: '/choice/saveChoice',
            data: jsonFormObj,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if(data.status == '200'){
                    $.messager.alert('保存成功','保存成功！','info');
                    $('#show_choice').datagrid('load');
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
