<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<style type="text/css">
			#ant {
				background-color: #b5c9cc;
				text-align: center;
				width: 500px;
				height: 400px;
				margin: auto;
				top: 0;
				left: 0;
				right: 0;
				bottom: 0;
			}
			#title{
				text-align: center;
			}
		</style>
		<title>Hnefatafl</title>
	</head>

	<body>
		<div id="title" style="font-size: 30px; color: rgb(0,0,0)">
			Welcome to Hnefatafl
		</div>
		<div id="ant" style="padding-top: 5px; margin-bottom: 5px;">
			<form action="login" method="post">
				<table border=0 style="width:80% ;height:80%">
                   <tr>
	               <td width="74" height="30" style="font-size: 20px;">
		           Username&nbsp;&nbsp;&nbsp;&nbsp;£º
	               </td>
				   <td width="181">
		           <input style="font-size: 20px;"name="uname" type="text" >
				   </td>
			       </tr>
					<tr>
	               <td height="30" style="font-size: 20px;">
	               	Password&nbsp;&nbsp;&nbsp;&nbsp;£º
				   </td>
					<td>
		           <input style="font-size: 20px;" name="upass" type="password"/>
					</td>
			        </tr>
					<tr>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<td height="30" colspan="10" align="center">
							<input style="font-size: 20px;" type="submit" value="login">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input style="font-size: 20px;" type="reset" value="clean">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
				<table>
				<tr>I don't have account!<a href="page-register.jsp"><small> register</small></a>
				</tr>
				</table>
          </div>

		<td align="center">
			<c:choose>
				<c:when test="${param.msg==0 }"><h1>username or password is wrong</h1></c:when>
				<c:when test="${param.msg==1 }"><h1>please login it at first</h1></c:when>
			</c:choose>
		</td>
	</form>
	</body>
</html>
