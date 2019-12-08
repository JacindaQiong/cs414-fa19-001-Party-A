package com.partyA.dao;

import com.partyA.bean.Match;
import com.partyA.db.DBUtil;

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
}
