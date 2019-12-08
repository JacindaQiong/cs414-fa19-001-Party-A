package com.partyA.dao;

import com.partyA.bean.Match;
import com.partyA.db.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * User: Nana Yin
 * Date: 12/5/19
 */
public class MatchDao {
    public int add(Match match) {
        DBUtil db = null;
        try{
            String sql="insert into game_match(black_id,black_name,white_id,white_name,result,start_time,end_time)values(?,?,?,?,?,?,?)";
            db=new DBUtil();
            int result = db.update(sql,match.getBlackID(),match.getBlackName(),match.getWhiteID(),match.getWhiteName(),match.getResult(),match.getStartTime(),match.getEndTime());
            return result;
        }finally {
            db.close();
        }

    }

    public List<Match> searchAll(int page, int show){
        List<Match> list=new ArrayList<Match>();
        String sql="select id,white_id,white_name,black_id,black_name,result,start_time,end_time from game_match LIMIT ?,?";
        DBUtil db=new DBUtil();
        ResultSet rs=db.query(sql,(page-1)*show,show);
        try {
            while(rs.next()){
                int ID = rs.getInt(1);
                int whiteID = rs.getInt(2);
                String whiteName = rs.getString(3);
                int blackID = rs.getInt(4);
                String blackName = rs.getString(5);
                String result = rs.getString(6);
                String startTime = rs.getString(7);
                String endTime = rs.getString(8);
                Match match = new Match();
                match.setID(ID);
                match.setWhiteID(whiteID);
                match.setWhiteName(whiteName);
                match.setBlackID(blackID);
                match.setBlackName(blackName);
                match.setResult(result);
                match.setStartTime(startTime);
                match.setEndTime(endTime);

                list.add(match);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return list;
    }
    public int searchCount() {
        int temp=0;
        String sql="select count(*) from game_match";
        DBUtil db=new DBUtil();
        ResultSet rs=db.query(sql);
        try {
            if(rs.next()){
                temp=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return temp;
    }
}