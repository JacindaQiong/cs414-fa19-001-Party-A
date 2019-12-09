<%--
  Created by IntelliJ IDEA.
  User: 李洁琼
  Date: 2019/12/2
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>How To Play Hnefatafl</title>
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
        <div title="Guidebook" style="padding:20px;" data-options="closable:false">
            <div><button id="gohome" class="easyui-linkbutton" style="height: 30px;width: 45px" data-options="" onclick="goHome();">Home</button></div>
            <table>
                <tr><h1>GuideBook</h1></tr>
                <tr><h2>1. Chess Move</h2></tr>
                <tr><h3>Whether you are white or black, each piece can only move horizontally and vertically </h3></tr>

                <tr><h2>2.White Wins</h2></tr>
                <tr><h3>Protect the king from moving to any corner</h3></tr>
                <tr> <img src="${pageContext.request.contextPath}/images/king_win.JPG" alt="king wins" width="350px"></tr>
                <tr><h2>3.Black Wins</h2></tr>
                <tr><h3>Kill the king</h3></tr>
                <tr><h3>a. four black pieces surround the king</h3></tr>
                <tr> <img src="${pageContext.request.contextPath}/images/king_capture.JPG" alt="king captured" width="350px"></tr>
                <tr><h3>b. three black pieces surround the king on the edge</h3></tr>

                <tr><h2>4. Methods of destroying enemy soldiers</h2></tr>
                <tr><h3>a. Two horizontal pieces surround opposing pieces</h3></tr>

                <tr><h3>b. A chess piece and a corner box surround enemy pieces</h3></tr>

            </table>
        </div>
    </div>
    <script>function goHome(){
        window.location.href="index.jsp";
    }</script>
</div>


</body>
</html>
