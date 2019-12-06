<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>user</title>
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-en.js"></script>
    <link rel="stylesheet" href="../easyui/themes/icon.css"/>
    <link rel="stylesheet" href="../easyui/themes/default/easyui.css"/>
</head>
<body>
<div class="easyui-tabs">
    <div title="HOME" style="padding:80px;" data-options="closable:false">
        <table>
            <div>Invite Users to Play With</div>

            <div style="float:right;padding-right:20px;padding-top:20px;">
                Account:${userInfo.name}&nbsp;&nbsp; <a href="../logout">【logout】</a>
            </div>
            <div>
                <table id="tbUser" class="easyui-datagrid" data-options="url:'selectUser',pageList:[3,5,7], pageSize:3, fit:true,pagination:true,singleSelect:true,toolbar:'#tb'">
                    <thead>
                    <tr>
                        <th data-options="field:'id'" width="100px">ID</th>
                        <th data-options="field:'name'" width="100px">Name</th>

                    </tr>
                    </thead>
                    <div id="tb">
                        <div id="btnNED">
                            <a href="index.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">exit</a>
                        </div>
                    </div>
                </table>
                <div>View My Invitations</div>
                <div>
                    <table id="" class="easyui-datagrid" data-options="url:'selectInvitation',pageList:[3,5,7], pageSize:3, fit:true,pagination:true,singleSelect:true,toolbar:'#tb'">
                        <thead>
                        <tr>
                            <th data-options="field:'inviter'" width="100px">Inviter</th>
                            <th data-options="field:'invitee'" width="100px">Invitee</th>
                            <th data-options="field:'time'" width="100px">Time</th>
                        </tr>
                        </thead>
                    </table>

                    <div >
                        <a href="" class="easyui-linkbutton" data-options=""><button>Accept</button></a>
                        <a href="" class="easyui-linkbutton" data-options=""><button>Ignore</button></a>
                    </div>
                </div>

        </table>
    </div>
</div>
<script>$('#tbUser').datagrid({
    onClickRow: function (index,row){
        var invitee = row.name
        sendInvite(invitee)
    }
});

function sendInvite(name){
    var Invitee = name;
    var Inviter = ${userInfo.name};
    $.ajax({
        url: "sendInvite?invitee=" +Invitee+ "&inviter=" +Inviter,
        contentType : "text/html;charset=utf-8",
        dataType: "text",
        type: "post",
        async: true,
        success : function(data) {

        }
    });
}</script>
</body>
</html>