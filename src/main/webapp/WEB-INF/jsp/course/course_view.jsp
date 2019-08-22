<%--
  Created by IntelliJ IDEA.
  Student: jioji
  Date: 2019/08/19 0019
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="../include_head.jsp" %>
<head>
    <title>View</title>
</head>
<body style="margin:0px 0px 100px 0px;padding: 0;">
<div id="main" class="easyui-tabs">
    <div title="基本信息">
        <table>
            <tr>
                <td valign="top" height="50px;" style="padding-left:20px" width="250px;" hidden="true">${course.id}</td>
                <td valign="top" height="50px;" style="padding-left:20px" width="120px;">课程名</td>
                <td valign="top" height="50px;" style="padding-left:20px" width="250px;">${course.courseName}</td>
                <td valign="top" height="50px;" style="padding-left:20px" width="120px;">年度</td>
                <td valign="top" height="50px;" style="padding-left:20px" width="250px;">${course.year}</td>
            </tr>
        </table>
    </div>
    <div title="其他信息">
        <table id="display-grid" class="easyui-datagrid" style="width:700px;height:250px" url="/course/getCourseData" singleSelect="false" toolbar="#toolbar" fit="true" showHeader="true" nowrap="true">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'id', title:'Id', width:'33%'">id</th>
                <th data-options="field:'courseName', title:'Course Name', width:'33%'">课程名</th>
                <th data-options="field:'year', title:'Year', width:'33%'">年度</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script type="text/javascript">
    /*关闭窗口*/
    function close_window() {

        // 获取窗口状态——注意easyUI对象属性的获取方式！！！
        parent.$('#opened_windows').window('close');
    }
</script>
</body>
</html>
