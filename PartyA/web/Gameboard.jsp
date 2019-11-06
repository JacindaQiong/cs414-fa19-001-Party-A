<%--
  Created by IntelliJ IDEA.
  User: 李洁琼
  Date: 2019/11/5
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style type="text/css">
        #top{
            text-align: center;
        }
        #all{
        width: 10px;
        height: 10px;
        background-color: #fff;
        float: left;
        }
        .im img{ width:20px; height:20px}

    </style>
    <title>Gameboard</title>
</head>
<body>
<div style="float:right;padding-right:20px;padding-top:20px;">
    【Account】:&nbsp;&nbsp;${userInfo.name}&nbsp;&nbsp; <a href="logout">【LOGOUT】</a>
</div>
<%--<div board="1">--%>
    <%--<script>--%>
        <%--for(var i=1;i<=11;i++){--%>
            <%--for(var j=1;j<=11;j++){--%>
                    <%--document.write("<div id='all'></div>");--%>
            <%--}--%>
        <%--}--%>
    <%--</script>--%>
<%--</div>--%>
<table border="1">
    <tr class="im">
        <td><img src="image/box.jpg"></td><td></td><img src="image/blank.png"><td></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td></td><img src="image/blank.png"><td></td><td><img src="image/box.jpg"></td>
    </tr>
    <tr class="im">
        <td></td><td></td><td></td><td></td><td></td><td><img src="image/black.jpg"></td><td></td><td></td><td></td><td></td><td></td>
    </tr>
    <tr class="im">
        <td><img src="image/blank.png"></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
    </tr>
    <tr class="im">
        <td><img src="image/black.jpg"></td><td></td><td></td><td></td><td></td><td><img src="image/white.jpg"></td><td></td><td></td><td></td><td></td><td><img src="image/black.jpg"></td>
    </tr>
    <tr class="im">
        <td><img src="image/black.jpg"></td><td></td><td></td><td></td><td><img src="image/white.jpg"></td><td><img src="image/white.jpg"></td><td><img src="image/white.jpg"></td><td></td><td></td><td></td><td><img src="image/black.jpg"></td>
    </tr>
    <tr class="im">
        <td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td><img src="image/blank.png"></td><td><img src="image/white.jpg"></td><td><img src="image/white.jpg"></td><td><img src="image/king.png"></td><td><img src="image/white.jpg"></td><td><img src="image/white.jpg"></td><td><img src="image/blank.png"></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td>
    </tr>
    <tr class="im">
        <td><img src="image/black.jpg"></td><td></td><td></td><td></td><td><img src="image/white.jpg"></td><td><img src="image/white.jpg"></td><td><img src="image/white.jpg"></td><td></td><td></td><td></td><td><img src="image/black.jpg"></td>
    </tr>
    <tr class="im">
        <td><img src="image/black.jpg"></td><td></td><td></td><td></td><td></td><td><img src="image/white.jpg"></td><td></td><td></td><td></td><td></td><td><img src="image/black.jpg"></td>
    </tr>
    <tr class="im">
        <td><img src="image/blank.png"></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
    </tr>
    <tr class="im">
        <td></td><td></td><td></td><td></td><td></td><td><img src="image/black.jpg"></td><td></td><td></td><td></td><td></td><td></td>
    </tr>
    <tr class="im">
        <td><img src="image/box.jpg"></td><td></td><td></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td><img src="image/black.jpg"></td><td></td><td></td><td><img src="image/box.jpg"></td>
    </tr>
</table>
<div id="top">
    <%--<img coords="82,126,82,126" src="/image/1.jpg" height="500", width="500"/>--%>
</div>
</body>
</html>
