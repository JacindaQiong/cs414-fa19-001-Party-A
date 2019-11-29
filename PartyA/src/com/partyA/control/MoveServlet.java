package com.partyA.control;

import com.partyA.bean.GameBoard;
import com.partyA.bean.Match;
import com.partyA.bean.MoveConverter;
import com.partyA.bean.User;
import com.partyA.exception.IllegalMoveException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //set to application/json later
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String fromX    = request.getParameter("fromX");
        String fromY  = request.getParameter("fromY");
        String toX = request.getParameter("toX");
        String toY = request.getParameter("toY");
        System.out.println("Move: fromX=" + fromX+"，fromY="+fromY+"，toX="+toX+"，toY="+toY);

        out.write("okay!!");
        out.flush();
        out.close();
    }



    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String fromX    = request.getParameter("fromX");
        String fromY  = request.getParameter("fromY");
        String toX = request.getParameter("toX");
        String toY = request.getParameter("toY");
        System.out.println("Move: fromX=" + fromX+"，fromY="+fromY+"，toX="+toX+"，toY="+toY);
        //Temp initialization to get communication working
        User black = new User(1, "AAA", "abcx", "AAA@gamil.com");
        User white = new User(2, "BBB", "fgfd", "BBB@gamil.com");
        Match m = new Match(black, white);
        GameBoard board = new GameBoard(m);
        MoveConverter convert = new MoveConverter();
        board.initialize();
        int result =0;
        try {
            result = board.move(convert.toBackend(Integer.parseInt(fromX),Integer.parseInt(fromY)), convert.toBackend(Integer.parseInt(toX), Integer.parseInt(toY)));
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        }
        out.write("" +result);
        out.flush();
        out.close();

    }
}
