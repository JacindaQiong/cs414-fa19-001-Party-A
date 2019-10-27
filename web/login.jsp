<%@ page contentType="text/html; charset=gb2312" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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

function check(){
   if(form1.manager.value==""){
     alert("Please enter user name£¡");form1.manager.focus();return false;
   }
   if(form1.pwd.value==""){
     alert("Please enter passward£¡");form1.pwd.focus();return false;
   }
}
// window.onload = function () {
//     var odiv = document.getElementById("ant");
//     var obt = document.getElementById("bt");
//     obt.onclick = function () {
//         odiv.style.backgroundImage = "url('../images/top_bg.jpg')";
//         odiv.style.backgroundRepeat = "no-repeat";
//         odiv.style.backgroundPosition = "0px -33px";
//     }
// }
</script>

	</head>

	<body>
		<jsp:include page="" />
		<div id="navigation" style="font-size: 12px; color: rgb(0,0,0)">
			Welcome to board game
		</div>
		<div id="main" style="padding-top: 5px; margin-bottom: 5px;"
			class="tableBorder_blue">
			<form name="form1" method="post" action="manager.do?action=login"
				onSubmit="return check()">
				<table width="255" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="69" colspan="2" align="center">
							&nbsp;
						</td>
					</tr>
<tr>
	<td width="74" height="30" class="word_gray1">
		Username:
	</td>
	<td width="181">
		<input name="manager" type="text" id="manager"
			onFocus="this.style.backgroundColor='#FBFFD9'"
			onBlur="this.style.backgroundColor='#FFFFFF'">
	</td>
</tr>
<tr>
	<td height="30" class="word_gray1">
		Passward&nbsp;&nbsp;&nbsp;&nbsp;£º
	</td>
	<td>
		<input name="pwd" type="password" id="pwd"
			onFocus="this.style.backgroundColor='#FBFFD9'"
			onBlur="this.style.backgroundColor='#FFFFFF'">
	</td>
</tr>
					<tr>
						<td height="30" colspan="2" align="center">
							<input name="Submit2" type="submit" class="btn_green" value="submit">
							&nbsp;
							<input name="Submit3" type="reset" class="btn_green" value="rewrite">
						</td>
					</tr>
				</table>

			</form>
		</div>
		<jsp:include page="" />
	</body>
</html>
