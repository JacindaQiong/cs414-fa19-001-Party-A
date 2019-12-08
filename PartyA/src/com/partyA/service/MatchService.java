package com.partyA.service;

import com.partyA.bean.Match;
import com.partyA.dao.MatchDao;

/**
 * User: Nana Yin
 * Date: 12/5/19
 */
public class MatchService {
    private MatchDao matchDao=new MatchDao();

    public int saveResult(Match match) {
        return matchDao.add(match);
    }
}
