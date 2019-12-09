package com.partyA.service;

import com.partyA.bean.Match;
import com.partyA.dao.MatchDao;

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

    public Map<String,Object> searchMatch(int userID, int page,int show){
        MatchDao dao=new MatchDao();
        List<Match> list=dao.searchAll(userID, page, show);
        int number=dao.searchCount(userID);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("page", page);
        map.put("rows", list);
        map.put("total", number);
        return map;
    }
}
