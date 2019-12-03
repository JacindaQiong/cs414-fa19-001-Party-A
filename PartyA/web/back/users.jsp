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
    <div>keep a space(I will change it after the function can be used)</div>

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
</table>
<div id="tb">
    <div id="btnNED">
     <a href="../addInvitation.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">invite</a>
     <a href="index.jsp" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">exit</a>
    </div>


 </div>
    </div>
</table>
    </div>
</div>

</body>
</html>