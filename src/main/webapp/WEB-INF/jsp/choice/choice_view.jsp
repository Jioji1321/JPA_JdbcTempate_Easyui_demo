<%--
  Created by IntelliJ IDEA.
  Student: jioji
  Date: 2019/08/17 0017
  Time: 19:37
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
                <td valign="top" height="50px;" style="padding-left:20px" width="250px;" hidden="true">${student.id}</td>
                <td valign="top" height="50px;" style="padding-left:20px" width="120px;">用户名</td>
                <td valign="top" height="50px;" style="padding-left:20px" width="250px;">${student.studentName}</td>
                <td valign="top" height="50px;" style="padding-left:20px" width="120px;">年龄</td>
                <td valign="top" height="50px;" style="padding-left:20px" width="250px;">${student.age}</td>
            </tr>
        </table>
    </div>
    <div title="其他信息">
        <table>

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
