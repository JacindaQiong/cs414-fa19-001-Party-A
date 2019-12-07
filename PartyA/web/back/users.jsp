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
                            <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">Send Invite</a>
                            <a href="index.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">exit</a>
                        </div>
                    </div>
                </table>
                <div>View My Invitations</div>
                <div>
                    <table id="tbInvites" class="easyui-datagrid" data-options="url:'selectInvitation?current=${userInfo.name}',pageList:[3,5,7], pageSize:3, fit:true,pagination:true,singleSelect:true,toolbar:'#tb'">
                        <thead>
                        <tr>
                            <th data-options="field:'inviter'" width="100px">Inviter</th>
                            <th data-options="field:'invitee'" width="100px">Invitee</th>
                            <th data-options="field:'time'" width="100px">Time</th>
                        </tr>
                        </thead>
                    </table>

                    <div >
                        <a id ="actBtn" href="#" class="easyui-linkbutton" class="easyui-linkbutton">Accept</a>
                        <a href="" class="easyui-linkbutton" data-options="">Ignore</a>
                    </div>
                </div>

        </table>
    </div>
</div>
<script>
    var Invitee;
    var Inviter;
    var invite_requester;
    var invite_responder;
    $('#tbUser').datagrid({
    onClickRow: function (index,row){
        var inviter = row.name
        getDetails(inviter)
    }
});

    $('#tbInvites').datagrid({
        onClickRow: function (index,row){
            var invite_name = row.invitee
            setInviteRequest(invite_name)
        }
    });
    $(function(){
        $('#actBtn').bind('click', function(){
            alert(invite_requester);
            alert(invite_responder);
           window.location = "../move?flag=0&blackID="+invite_requester+"&whiteID="+invite_responder;
        });
    });
function getDetails(name){
    Invitee = name;
    Inviter = ${userInfo.name};
    }
function sendInvite() {
    $.ajax({
        url: "sendInvite?invitee=" + Invitee + "&inviter=" + Inviter,
        contentType: "text/html;charset=utf-8",
        dataType: "text",
        type: "post",
        async: true,
        success: function (data) {
        }
    });
}
function setInviteRequest(invite_name) {
    invite_requester = invite_name;
    invite_responder = ${userInfo.name};
}


</script>
</body>
</html>