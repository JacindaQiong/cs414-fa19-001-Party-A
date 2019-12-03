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
<title>congratulation!</title>
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui/locale/easyui-lang-en.js"></script>
<link rel="stylesheet" href="../easyui/themes/icon.css"/>
<link rel="stylesheet" href="../easyui/themes/default/easyui.css"/>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:90px;">
    <div style="float:left;"> <img src="" height="85px" width="1360px"></div>
</div>

    <div style="float:right;padding-right:20px;padding-top:20px;">
        Account:${userInfo.name}&nbsp;&nbsp; <a href="../logout">【logout】</a>
    </div>

<div data-options="region:'center'" style="padding:5px;background:#eee;">

    <div class="easyui-tabs">
        <div title="HOME" style="padding:20px;" data-options="closable:false">

    <div> <a href="users.jsp"><button>Invitable-player</button></a></div>

    <div><a href=""><button>the guidebook for beginners</button>></a></div>

    <div><a href=""><button>invitation email</button></a></div>

        </div>
    </div>


</div>


</body>  
</html>