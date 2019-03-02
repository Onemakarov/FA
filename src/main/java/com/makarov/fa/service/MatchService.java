package com.makarov.fa.service;

import com.makarov.fa.dao.MatchDao;
import com.makarov.fa.entity.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchService {

    private final MatchDao matchDao;

    @Autowired
    public MatchService(MatchDao matchDao) {
        this.matchDao = matchDao;
    }

    @Transactional
    public void addMatch(Match match) {
        matchDao.addMatch(match);
    }

    @Transactional
    public void addMatches(List<Match> matches) {
        for (Match match : matches) {
            addMatch(match);
        }
    }

    @Transactional
    public Match getMatchById(Long matchId) {
        return matchDao.getMachById(matchId);
    }

    @Transactional
    public List<Match> getMatchesBySeasonId(Long seasonId) {
        return matchDao.getAllMatchesBySeasonId(seasonId);
    }

    @Transactional
    public List<Match> getAllMatches() {
        return matchDao.getAllMatches();
    }
}
