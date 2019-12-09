<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Invite Users</title>
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/locale/easyui-lang-en.js"></script>
    <link rel="stylesheet" href="../easyui/themes/icon.css"/>
    <link rel="stylesheet" href="../easyui/themes/bootstrap/easyui.css"/>
</head>
<body>
<div class="easyui-tabs">
    <div title="Invite" style="padding:80px;" data-options="closable:false">
        <table>
            <h1>Invite Users to Play With</h1>

            <div style="float:right;padding-right:20px;padding-top:20px;">
                Account:${userInfo.name}&nbsp;&nbsp; <a href="../logout">【logout】</a>
            </div>
            <div><button id="gohome" class="easyui-linkbutton" style="height: 30px;width: 45px" data-options="" onclick="goHome();">Home</button></div>
            <div>
                <table id="tbUser" class="easyui-datagrid" style="width:100%;height:250px" data-options="url:'selectUser',pageList:[3,5,7], pageSize:3, fit:false,pagination:true,singleSelect:true,toolbar:'#tb'">
                    <thead>
                    <tr>
                        <th data-options="field:'id'" width="100px">ID</th>
                        <th data-options="field:'name'" width="100px">Name</th>

                    </tr>
                    </thead>
                    <div id="tb">
                        <div id="btnNED">
                            <a id="sndBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">Send Invite</a>
                            <a href="index.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">exit</a>
                        </div>
                    </div>
                </table>
                <h1>View My Invitations</h1>
                <div>
                    <table id="tbInvites" class="easyui-datagrid" data-options="url:'selectInvitation?current=${userInfo.name}',pageList:[3,5,7], pageSize:5, fit:true,pagination:true,singleSelect:true,toolbar:'#tb'">
                        <thead>
                        <tr>
                            <th data-options="field:'inviter'" width="100px">Inviter</th>
                            <th data-options="field:'invitee'" width="100px">Invitee</th>
                            <th data-options="field:'time'" width="100px">Time</th>
                            <th data-options="field:'inviterID'" width="100px">InviterID</th>
                            <th data-options="field:'inviteeID'" width="100px">InviteeID</th>
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
    var Invitee_id;
    var Inviter;
    var Inviter_id;
    var invite_requester;
    var invite_responder;
    $('#tbUser').datagrid({
    onClickRow: function (index,row){
        var invitee_id =row.id;
        var invitee = row.name;
        getDetails(invitee,invitee_id)
    }
});

    $('#tbInvites').datagrid({
        onClickRow: function (index,row){
            var inviter_id= row.inviterID;
            var invitee_id = row.inviteeID;
            setInviteRequest(inviter_id, invitee_id)
        }
    });
    $(function(){
        $('#actBtn').bind('click', function(){
           window.location = "../move?flag=0&blackID="+Inviter_id+"&whiteID="+Invitee_id;
        });
    });
    $(function(){
        $('#sndBtn').bind('click', function(){
            sendInvite();
            alert("Invite sent to " +Invitee+"!");
        });
    });
function getDetails(name,id){
    Invitee = name;
    Invitee_id=id;
    Inviter = '<%= session.getAttribute("name") %>';
    Inviter_id=${userInfo.id};

}
function sendInvite() {
    $.ajax({
        url: "sendInvite?invitee=" + Invitee + "&inviter=" + Inviter +"&inviter_id=" +Inviter_id+"&invitee_id="+Invitee_id,
        contentType: "text/html;charset=utf-8",
        dataType: "text",
        type: "post",
        async: false,
        success: function (data) {
        }
    });
}
function setInviteRequest(inviterID, inviteeID) {
    Inviter_id = inviterID;
    Invitee_id = inviteeID;
}
function goHome(){
        window.location.href="http://localhost:8080/PartyA_war_exploded/index.jsp";
}


</script>
</body>
</html>