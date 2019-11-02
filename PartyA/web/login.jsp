<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<style type="text/css">
			#ant {
				width:400px;
				height:300px;
				background-color:#ccc;
			}
		</style>
		<title>Chess Game</title>
		<link href="CSS/style.css" rel="stylesheet" />
		<script language="javascript">

// function check(){
//    if(form1.manager.value==""){
//      alert("Please enter user name！");form1.manager.focus();return false;
//    }
//    if(form1.pwd.value==""){
//      alert("Please enter passward！");form1.pwd.focus();return false;
//    }
// }
</script>

	</head>

	<body>
		<div id="navigation" style="font-size: 12px; color: rgb(0,0,0)">
			Welcome to board game
		</div>
		<div id="main" style="padding-top: 5px; margin-bottom: 5px;" class="tableBorder_blue">
			<form action="login" method="post">
				<table width="255" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="69" colspan="2" align="center">
							&nbsp;
						</td>
					</tr>
<tr>
	<td width="74" height="30" >
		Username:
	</td>
	<td width="181">
		<input name="uname" type="text" >
	</td>
</tr>
<tr>
	<td height="30" >
		Passward&nbsp;&nbsp;&nbsp;&nbsp;：
	</td>
	<td>
		<input name="upass" type="password"/>
	</td>
</tr>
					<tr>
						<td height="30" colspan="2" align="center">
							<input type="submit" class="btn_green" value="submit">
							&nbsp;
							<input type="reset" class="btn_green" value="rewrite">
							&nbsp;
							<a href="page-register.jsp"><button>register</button></a>
						</td>
					</tr>
				</table>
		</div>
		<tr height="40px"></tr>
		<td colspan="2" align="center">
			<!-- 判断结果的代码 -->
			<c:choose>
				<c:when test="${param.msg==0 }">username or password is wrong</c:when>
				<c:when test="${param.msg==1 }">please login it at first</c:when>
			</c:choose>
		</td>
	</form>
	</body>
</html>
