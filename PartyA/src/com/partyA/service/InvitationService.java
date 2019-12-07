package com.partyA.service;

import com.partyA.bean.Invitation;
import com.partyA.dao.InvitationDao;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvitationService {

    public boolean addInvitation(Invitation invitation) {
        InvitationDao dao=new InvitationDao();
        int temp=dao.add(invitation);
        if(temp>0){
            return true;
        }else{
            return false;
        }
    }

    public String searchInvitation(int page,int show, String user){
        InvitationDao dao=new InvitationDao();
        List<Invitation> list=dao.searchAll(page, show, user);
        int number=dao.searchCount();
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
