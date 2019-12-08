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
    <title>$Title$</title>
      <script type="text/javascript">
          function playGame(){
              window.location.href="<%=basePath %>move?flag=0&blackID=1&whiteID=2";
          }
          function invite(){
              window.location.href="<%=basePath%>back/users.jsp";
          }
          function history(){
              window.location.href="<%=basePath%>";
          }
      </script>
  </head>
  <body>
  <h1>Hnefatafl</h1>
  <button id="invite" onclick="invite();">Invite Others</button>
 <button id="playGame" onclick="playGame();">Play Game</button>
  <button id="history" onclick="history();">History</button>
  </body>
</html>
