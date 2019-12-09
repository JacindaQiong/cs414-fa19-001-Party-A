package com.partyA.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partyA.bean.Invitation;
import com.partyA.dao.InvitationDao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvitationService {

    private InvitationDao invitationDao=new InvitationDao();

    public boolean addInvitation(Invitation invitation) {
        int temp=invitationDao.add(invitation);
        if(temp>0){
            return true;
        }else{
            return false;
        }
    }

    public String searchInvitation(int page,int show, String user){
        List<Invitation> list=invitationDao.searchAll(page, show, user);
        int number=invitationDao.searchCount();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("page", page);
        map.put("rows", list);
        map.put("total", number);
        ObjectMapper mapper=new ObjectMapper();
        String temp=null;
        try{
            temp=mapper.writeValueAsString(map);
        }catch(IOException e){
            e.printStackTrace();
        }
        return temp;
    }
}
