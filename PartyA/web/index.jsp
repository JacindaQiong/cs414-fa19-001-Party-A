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
              alert("you are going to play game.");
              window.location.href="<%=basePath %>game2.jsp";
          }
          function invite(){
              alert("you are going to invite others.");
          }
      </script>
  </head>
  <body>
  <h1>partyA project</h1>
  <button id="invite" onclick="invite();">invite others</button>
 <button id="playGame" onclick="playGame();">play game</button>
  </body>
</html>
