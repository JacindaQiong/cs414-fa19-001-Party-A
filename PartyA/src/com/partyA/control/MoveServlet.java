package com.partyA.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partyA.bean.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {
    //Temp initialization to get communication working
    User black = new User(1, "AAA", "abcx", "AAA@gamil.com");
    User white = new User(2, "BBB", "fgfd", "BBB@gamil.com");
    Match m = new Match(black, white);
    GameBoard board = new GameBoard(m);
//    MoveConverter convert = new MoveConverter();

    public void init() throws ServletException {
        board.initialize();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        int fromX = Integer.parseInt(request.getParameter("fromX"));
        int fromY = Integer.parseInt(request.getParameter("fromY"));
        int toX = Integer.parseInt(request.getParameter("toX"));
        int toY = Integer.parseInt(request.getParameter("toY"));
        fromX = (fromX-50)/50;
        fromY = (fromY-50)/50;
        toX = (toX-50)/50;
        toY = (toY-50)/50;

        try {
            String fromPos = String.valueOf((char) ('a' + fromX)) + (char) ('a' + fromY);
            String toPos = String.valueOf((char) ('a' + toX)) + (char) ('a' + toY);
            int status = board.move(fromPos,toPos);

            List<Map> list = new ArrayList<>();
            Piece[][] array  =board.getBoardArray();
            for(int x=0; x<11; x++){
                for(int y=0; y<11;y++){
                    if(array[x][y]!=null){
                        Map item = new HashMap();
                        Piece p = array[x][y];
                        item.put("txt",p.toString());
                        item.put("x",x*50+50);
                        item.put("y",y*50+50);
                        list.add(item);
                    }
                }
            }

            Map data = new HashMap();
            data.put("status",status);
            data.put("chess",list);
            ObjectMapper mapper = new ObjectMapper();
            out.write( mapper.writeValueAsString(data));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
