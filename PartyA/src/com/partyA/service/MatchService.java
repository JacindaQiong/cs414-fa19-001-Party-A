package com.partyA.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.partyA.bean.Match;
import com.partyA.dao.MatchDao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Nana Yin
 * Date: 12/5/19
 */
public class MatchService {
    private MatchDao matchDao=new MatchDao();

    public int saveMatch(Match match) {
        return matchDao.add(match);
    }

    public String searchMatch(int page,int show){
        MatchDao dao=new MatchDao();
        List<Match> list=dao.searchAll(page, show);
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
