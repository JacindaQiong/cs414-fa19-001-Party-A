package com.partyA.dao;

import com.partyA.bean.Invitation;
import com.partyA.db.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvitationDao {
    public List<Invitation> searchAll(int page, int show, String user){
        List<Invitation> list=new ArrayList<Invitation>();
        String sql="select * from game_invitation LIMIT ?,?";
        DBUtil db=new DBUtil();
        ResultSet rs=db.query(sql,(page-1)*show,show);
        try {
            while(rs.next()){
                if(rs.getString(2).equals(user)) {
                    list.add(new Invitation(rs.getString(1), rs.getString(2), rs.getString(3)));
                }
                }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.close();
        return list;
    }

    public int searchCount() {
        int temp=0;
        String sql="select count(*) from game_invitation";
        DBUtil db=new DBUtil();
        ResultSet rs=db.query(sql);
        try {
            if(rs.next()){
                temp=rs.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.close();
        return temp;
    }

    public int add(Invitation invitation){
        int temp=-1;
        String sql="insert into game_invitation(game_inviter,game_invitee,invitation_time)values(?,?,?)";
        DBUtil db=new DBUtil();
        temp=db.update(sql,invitation.getInviter(),invitation.getInvitee(),invitation.getTime());
        db.close();
        return temp;
    }
}
