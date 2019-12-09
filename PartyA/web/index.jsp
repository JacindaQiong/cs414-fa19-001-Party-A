<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>Welcome</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="easyui/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="easyui/themes/icon.css"/>
    <link rel="stylesheet" href="easyui/themes/bootstrap/easyui.css"/>
    <script type="text/javascript" src="js/mainAction.js"></script>
    <script type="text/javascript">
        function playGame(){
            window.location.href="<%=basePath %>move?flag=0&blackID=1&whiteID=2";
        }
        function getHistory(){
            window.location.href="history.jsp";
        }
        function invite(){
            window.location.href="<%=basePath %>back/users.jsp";
        }
        function guidebook(){
            window.location.href="guidebook.jsp";
        }
        function logout(){
            if(confirm("comfirm logout？")){
                window.location.href="<%=basePath %>logout";
            }
        }
    </script>
    <style type="text/css">
        #title{
            text-align: center;
        }
        #title1{
            text-align: center;
        }
    </style>
</head>
<body class="easyui-layout">
<div data-options="region:'north'" style="height:50px;">
    <div style="float:left;">
        <h3>Account:${userInfo.name}&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onclick="logout()">【logout】</a></h3>
    </div>
</div>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <div class="easyui-tabs" >
        <div title="Hnefatafl" style="padding:50px;" >
            <table>
                <h1 id="title1">Welcome to Play Hnefatafl</h1>
                <div id="title">
                    <div><button id="guidebook" class="easyui-linkbutton" style="height: 50px;width: 100px" data-options="iconCls:'icon-edit'" onclick="guidebook();">How To Play</button></div>
                    <div>&nbsp;&nbsp;</div>
                    <div><button id="invite" class="easyui-linkbutton" style="height: 50px;width: 100px" data-options="iconCls:'icon-man'" onclick="invite();"> Invite Others</button></div>
                    <div>&nbsp;&nbsp;</div>
                    <div><button id="playGame" class="easyui-linkbutton" style="height: 50px;width: 100px"  data-options="iconCls:'icon-ok'"onclick="playGame();"> Play Game</button></div>
                    <div>&nbsp;&nbsp;</div>
                    <div><button id="matchHistory" class="easyui-linkbutton" style="height: 50px;width: 100px"  data-options="iconCls:'icon-search'"onclick="getHistory();"> Match History</button></div>
                </div>
            </table>
        </div>
    </div>
</div>
</body>
</html>