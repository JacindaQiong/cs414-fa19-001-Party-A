package com.partyA.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partyA.bean.*;
import com.partyA.service.MatchService;
import com.partyA.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/move")
public class MoveServlet extends HttpServlet {

    private Match match;
    private GameBoard board;

    private UserService userService=new UserService();
    private MatchService matchService=new MatchService();
    private SimpleDateFormat sdf = new SimpleDateFormat();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
        String flag = request.getParameter("flag");
        if("0".equals(flag)){
            //begin a game
            int blackID = Integer.parseInt(request.getParameter("blackID"));
            int whiteID = Integer.parseInt(request.getParameter("whiteID"));
            User blackUser = userService.getUserById(blackID);
            User whiteUser = userService.getUserById(whiteID);

            match = new Match(blackID, whiteID);
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
            Date begin_date = new Date();

            match.setStartTime(sdf.format(begin_date));
            match.setBlackName(blackUser.getName());
            match.setWhiteName(whiteUser.getName());
            board = new GameBoard(match);
            board.initialize();


            request.getSession().setAttribute("blackUser",blackUser);
            request.getSession().setAttribute("whiteUser",whiteUser);

            response.sendRedirect("jsp/play_game.jsp");
        }else{
            //move
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

            String fromPos = String.valueOf((char) ('a' + fromX)) + (char) ('a' + fromY);
            String toPos = String.valueOf((char) ('a' + toX)) + (char) ('a' + toY);
            int status = board.move(fromPos,toPos);
            if(status==0||status==1){
                //store the result into database
                sdf.applyPattern("yyyy-MM-dd HH:mm:ss a");
                Date end_date = new Date();

                match.setEndTime(sdf.format(end_date));
                match.setResult(status==0?"black won":"white won");
                matchService.saveMatch(match);
            }
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
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}