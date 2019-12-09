<%--
  Created by IntelliJ IDEA.
  User: 李洁琼
  Date: 2019/12/7
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>congratulation!</title>
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="easyui/themes/icon.css"/>
    <link rel="stylesheet" href="easyui/themes/bootstrap/easyui.css"/>
    <script type="text/javascript" src="js/mainAction.js"></script>
</head>
<body class="easyui-layout">

<div style="float:right;padding-right:20px;padding-top:20px;">
    Account:${userInfo.name}&nbsp;&nbsp; <a href="../logout">【logout】</a>
</div>

<div data-options="region:'center'" style="padding:5px;background:#eee;">

    <div id="myTabs" class="easyui-tabs" data-options="fit:true">
        <div title="History" style="padding:20px;" data-options="closable:false">
            <div><button id="gohome" class="easyui-linkbutton" style="height: 30px;width: 45px" data-options="" onclick="goHome();">Home</button></div>
            <table id="" class="easyui-datagrid" data-options="url:'selectHistory',pageList:[3,5,7], pageSize:3, fit:true,pagination:true,singleSelect:true,toolbar:'#tb'">
                <thead>
                <tr>
<%--                    <th data-options="field:'ID'" width="100px">ID</th>--%>
                    <th data-options="field:'whiteID'" width="100px">whiteID</th>
                    <th data-options="field:'blackID'" width="100px">blackID</th>
                    <th data-options="field:'whiteName'" width="100px">whiteName</th>
                    <th data-options="field:'blackName'" width="100px">blackName</th>
                    <th data-options="field:'result'" width="100px">result</th>
                    <th data-options="field:'startTime'" width="100px">startTime</th>
                    <th data-options="field:'endTime'" width="100px">endTime</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script>
    function goHome(){
        window.location.href="index.jsp";
    }
</script>
</body>
</html>
