<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user</title>
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="../easyui/themes/icon.css"/>
<link rel="stylesheet" href="../easyui/themes/default/easyui.css"/>
</head>
<body>
<table>
    <div>2222222222</div>
    <div>
  <table id="tbUser" class="easyui-datagrid" data-options="url:'selectUser',pageList:[3,5,7], pageSize:3, fit:true,pagination:true,singleSelect:true,toolbar:'#tb'">
     <thead>   
        <tr>   
            <th data-options="field:'id'" width="80px">ID</th>
            <th data-options="field:'name'" width="80px">Name</th>
            <th data-options="field:'email'" width="80px">email</th>
        </tr>
    </thead>   
</table>
<div id="tb">
    <div id="btnNED">
     <a href="" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">invite</a>
    </div>
    
       
 </div>
    </div>
</table>

</body>
</html>