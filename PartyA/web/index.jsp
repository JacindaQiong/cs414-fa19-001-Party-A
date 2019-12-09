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

          function getHistory(){
              window.location.href="history.jsp";
          }
          function invite(){
             window.location.href="<%=basePath %>back/users.jsp";
          }
      </script>
  </head>
  <body>
  <h1>partyA project</h1>
  <button id="invite" onclick="invite();">invite others</button>
 <button id="playGame" onclick="playGame();">play game</button>
  <button id="matchHistory" onclick="getHistory();">match history</button>

  </body>
</html>
