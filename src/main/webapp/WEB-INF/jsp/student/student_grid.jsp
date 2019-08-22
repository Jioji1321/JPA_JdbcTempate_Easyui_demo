<%--
  Created by IntelliJ IDEA.
  Student: jioji
  Date: 2019/08/17 0017
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../include_head.jsp"%>
<head>
    <title>Grid</title>
</head>
<body style="margin:0px 0px 100px 0px;padding: 0;">
<a href="/">回到首页</a>
<div id="toolbar">
    <table>
        <tr>
            <td>
                <input class="easyui-textbox" id="searchId" name="searchId" prompt="用户Id">
                <input class="easyui-textbox" id="searchStudentName" name="searchStudentName" prompt="用户名">
                <input class="easyui-textbox" id="searchAge" name="searchAge" prompt="年龄">
            </td>
            <td align="right">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="querySearchStudent()" title="搜索"></a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" onclick="add()" title="新增"></a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" onclick="edit()" title="编辑"></a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="del()" title="删除"></a>
            </td>
        </tr>
    </table>
</div>
<table id="display-grid" class="easyui-datagrid" style="width:700px;height:250px" url="/student/getStudentData" singleSelect="false" toolbar="#toolbar" fit="true" showHeader="true" nowrap="true">
    <thead>
        <tr>
            <th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'id', title:'Id', width:'33%'">id</th>
            <th data-options="field:'studentName', title:'Student Name', width:'33%'">用户名</th>
            <th data-options="field:'age', title:'Age', width:'33%'">年龄</th>
        </tr>
    </thead>
</table>


<script type="text/javascript">
    $(function() {
        $("#display-grid").datagrid({
            //双击事件
            onDblClickRow: function (index, row) {
                var id = row.id;
                var url = "/student/showStudentDetail?id="+id;
                console.log(url);
                parent.openWindowById("#opened-windows",url,"用户详情","800","530");
            }
        });
    });

    function add(){
        var url = "/student/addNewStudent";
        parent.openWindowById("#opened-windows",url,"新增用户","800","530");
    }

    function edit(){
        var row = $('#display-grid').datagrid('getSelected'); //获取选中的行
        if(!row){
            $.messager.alert('提示', '请选择一条记录！', 'info');
            return;
        }
        var id = row.id;
        var url = '/student/selectStudentById?id=' + id;
        parent.openWindowById("#opened-windows",url,"用户详情","800","530");

    }

    function del(){
        var rows = $('#display-grid').datagrid('getSelections'); //获取选中的行
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
                    url: '/student/deleteStudentsByIds',
                    data: {
                        ids: ids
                    },
                    success: function(data){
                        console.log(data);
                        $.messager.alert('删除成功','id为' + data.ids +'的用户已被删除！','info');
                        $('#display-grid').datagrid('reload');
                    },
                    error: function (error) {
                        console.log(error);
                        $.messager.alert('提示','删除失败','error');
                    }
                });
            }
        });
    }

    function querySearchStudent(){
        var id = $('#searchId').val();
        var studentname = $('#searchStudentName').val();
        var age = $('#searchAge').val();
        $('#display-grid').datagrid('load', {
            searchId: id,
            searchStudentName: studentname,
            searchAge: age
        });
    }

    $(function(){
        $('#searchId').textbox({
            icons:[{
                iconCls:'icon-clear',
                handler: function(e){
                    $(e.data.target).textbox('clear');
                }
            }]
        });
        $('#searchStudentName').textbox({
            icons:[{
                iconCls:'icon-clear',
                handler: function(e){
                    $(e.data.target).textbox('clear');
                }
            }]
        });
        $('#searchAge').textbox({
            icons:[{
                iconCls:'icon-clear',
                handler: function(e){
                    $(e.data.target).textbox('clear');
                }
            }]
        });
    });


</script>
</body>
</html>
