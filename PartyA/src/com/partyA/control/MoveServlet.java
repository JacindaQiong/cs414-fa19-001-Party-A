package com.partyA.control;

import com.partyA.bean.GameBoard;
import com.partyA.bean.Match;
import com.partyA.bean.MoveConverter;
import com.partyA.bean.User;
import com.partyA.exception.IllegalMoveException;
import com.partyA.exception.IllegalPositionException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {
    //Temp initialization to get communication working
    User black = new User(1, "AAA", "abcx", "AAA@gamil.com");
    User white = new User(2, "BBB", "fgfd", "BBB@gamil.com");
    Match m = new Match(black, white);
    GameBoard board = new GameBoard(m);
    MoveConverter convert = new MoveConverter();

    public void init() throws ServletException {
        board.initialize();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //set to application/json later
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String fromX = request.getParameter("fromX");
        String fromY = request.getParameter("fromY");
        String toX = request.getParameter("toX");
        String toY = request.getParameter("toY");
        System.out.println("Move: fromX=" + fromX + "，fromY=" + fromY + "，toX=" + toX + "，toY=" + toY);

        out.write("okay!!");
        out.flush();
        out.close();
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String fromX = request.getParameter("fromX");
        String fromY = request.getParameter("fromY");
        String toX = request.getParameter("toX");
        String toY = request.getParameter("toY");
        System.out.println("Move: fromX=" + fromX + "，fromY=" + fromY + "，toX=" + toX + "，toY=" + toY);
        int status = 0;
        String result = "";
        ArrayList<String> boardArray = new ArrayList<String>();
        String tempBoard = "";
        int isLegal = 0;
        try {
            if (board.checkIsLegal(convert.toBackend(Integer.parseInt(fromX), Integer.parseInt(fromY)), convert.toBackend(Integer.parseInt(toX), Integer.parseInt(toY))) == 1) {
                isLegal = 1;
                status = board.move(convert.toBackend(Integer.parseInt(fromX), Integer.parseInt(fromY)), convert.toBackend(Integer.parseInt(toX), Integer.parseInt(toY)));
                System.out.println(board.toString());
            }
            boardArray = board.getPieceLocations();
            for (int i = 0; i < boardArray.size(); i++) {
                tempBoard += boardArray.get(i);
            }
        } catch (IllegalMoveException | IllegalPositionException e) {
            e.printStackTrace();
        }
        //Convert tempBoard to board that gets returned to client
        for (int i = 0; i < tempBoard.length(); i += 3) {
            String temp = "";
            temp += "['" + tempBoard.charAt(i + 0) + "' , ";
            temp += convert.toFrontEndCol(tempBoard.charAt(i + 1)) + " , ";
            temp += convert.toFrontEndRow(tempBoard.charAt(i + 2)) + "] ";
            result += temp;
        }
        JsonObject output = Json.createObjectBuilder()
                .add("legal",isLegal)
                .add("status", status)
                .add("board", result)
                .build();
        out.write("" + output);
        out.flush();
        out.close();

    }
}
