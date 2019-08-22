<%--
  Created by IntelliJ IDEA.
  Student: jioji
  Date: 2019/08/20 0020
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../include_head.jsp" %>
<head>
    <title>Choice</title>
</head>
<body>
Choice Classes!
<a href="/">回到首页</a>
<form id="input-form" method="post">
    <table id="input-table">
        <tr>学生：</tr>
        <tr>
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
            " required="true">
            </select>
        </tr>
            选择了课程：
        <tr>
            <select id="course_add" class="easyui-combogrid" style="width:250px" data-options="
                panelWidth: 500,
                idField: 'id',
                textField: 'courseName',
                url: '/course/getCourseData',
                method: 'get',
                columns: [[
                    {field:'id',title:'课程编号',width:80},
                    {field:'courseName',title:'课程名',width:120},
                    {field:'year',title:'年度',width:80,align:'right'}
                ]],
                fitColumns: true
            " required="true">
            </select>
        </tr>
        <tr>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="save()">提交</a>
            </td>
        </tr>
        </tr>
    </table>
</form>
<%--<form id="delete-form" method="post">
    <table id="delete-table">
        <tr>学生：</tr>
        <tr>
            <select id="student_del" class="easyui-combogrid" style="width:250px" data-options="
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
                fitColumns: true,
                onSelect: function(index, row){
                    console.log(row.id);
                    var url = '/choice/getCourseDataByStudentId?id='+row.id;
                    $('#course_del').combobox('reload', url);
                }
            ">
            </select>
        </tr>
        删除了课程：
        <tr>
            <select id="course_del" class="easyui-combobox" style="width:250px" data-options="
                valueField:'id',
                textField:'text'
            ">
            </select>
        </tr>
        <tr>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton" onclick="del()">确定</a>
            </td>
        </tr>
        </tr>
    </table>
</form>--%>

<div id="toolbar">
    <table>
        <tr>
            <td>
                <input class="easyui-textbox" id="searchStudentName" name="searchStudentName" prompt="学生名">
                <input class="easyui-textbox" id="searchCourseName" name="searchCourseName" prompt="课程名">
                <input class="easyui-textbox" id="searchYear" name="searchYear" prompt="年度">
            </td>
            <td align="right">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="querySearchChoice()" title="搜索"></a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="edit()" title="编辑"></a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="del()" title="删除"></a>
            </td>
        </tr>
    </table>
</div>
<table id="show_choice" class="easyui-datagrid" url="/choice/getChoiceData" toolbar="#toolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id', title:'Id', width:'25%'">记录编号</th>
        <th data-options="field:'studentName', title:'Student Name', width:'25%'">学生名</th>
        <th data-options="field:'courseName', title:'Course Name', width:'25%'">课程名</th>
        <th data-options="field:'year', title:'Year', width:'24%'">年度</th>
    </tr>
    </thead>
</table>
<script type="text/javascript">

    function querySearchChoice(){
        var studentName = $('#searchStudentName').val();
        var courseName = $('#searchCourseName').val();
        var year = $('#searchYear').val();
        $('#show_choice').datagrid('load', {
            studentName: studentName,
            courseName: courseName,
            year: year
        });
    }

    $(function(){
        $('#searchStudentName').textbox({
            icons:[{
                iconCls:'icon-clear',
                handler: function(e){
                    $(e.data.target).textbox('clear');
                }
            }]
        });
        $('#searchCourseName').textbox({
            icons:[{
                iconCls:'icon-clear',
                handler: function(e){
                    $(e.data.target).textbox('clear');
                }
            }]
        });
        $('#searchYear').textbox({
            icons:[{
                iconCls:'icon-clear',
                handler: function(e){
                    $(e.data.target).textbox('clear');
                }
            }]
        });
    });

    function save() {
        var student = $('#student_add').combogrid('grid'); //获取datagrid对象
        var stu_obj = student.datagrid('getSelected'); //获取被选行对象
        var course = $('#course_add').combogrid('grid'); //获取datagrid对象
        var cou_obj = course.datagrid('getSelected'); //获取被选行对象

        if(stu_obj == null || cou_obj == null){
            $.messager.alert('确认','请填入必填信息！');
            return false;
        }

        var formObject = {};
        formObject['studentId'] = stu_obj.id;
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

    function edit() {
        var row = $('#show_choice').datagrid('getSelected'); //获取选中的行
        if(!row){
            $.messager.alert('提示', '请选择一条记录！', 'info');
            return;
        }
        var id = row.id;
        var url = '/choice/selectChoiceById?id=' + id;
        parent.openWindowById("#opened-windows",url,"修改选课","800","530");
    }

    function del() {
        var rows = $('#show_choice').datagrid('getSelections'); //获取选中的行
        var len = rows.length;
        if(len < 1){
            $.messager.alert('提示', "请选择需要删除的数据！", 'info');
            return;
        }
        var ids = '';
        for(var i = 0; i < len; i++){
            ids += rows[i].id + ',';
        }
        $.messager.confirm("操作提示", "是否删除" + len +"条数据？", function (data){
            if(data){
                $.ajax({
                    type: 'POST',
                    url: '/choice/deleteChoicesByIds',
                    data: {
                        ids: ids
                    },
                    success: function(data){
                        console.log(data);
                        $.messager.alert('删除成功','id为' + data.ids +'的记录已被删除！','info');
                        $('#show_choice').datagrid('reload');
                    },
                    error: function (error) {
                        console.log(error);
                        $.messager.alert('提示','删除失败','error');
                    }
                });
            }
        });
    }
</script>
</body>
</html>
