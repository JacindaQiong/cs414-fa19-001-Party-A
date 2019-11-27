package com.partyA.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.partyA.bean.GameBoard;
import com.partyA.bean.Match;
import com.partyA.bean.User;
import org.json.JSONObject;

public class MoveServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String requestUrl = request.getRequestURI();
        String move = requestUrl.substring("PartyA_war_exploded/move/".length());
        User user1 = new User();
        User user2 = new User();
        GameBoard board = new GameBoard(new Match(user1, user2));
        if(true){
            String json = "{\n";
            json += "\"move\": " + JSONObject.quote(board.toString()) + ",\n";
            json += "}";
            response.getOutputStream().println(json);
        }
        else{
            //Return empty JSON object
            response.getOutputStream().println("{}");
        }
    }



    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //String name = request.getParameter("user");
        //String match = request.getParameter("match");
        System.out.println(request.getPathInfo());
        String move = request.getPathInfo();
        System.out.println("Move: " + move);

    }
}
