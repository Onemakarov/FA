package com.makarov.fa.service;

import com.makarov.fa.dao.CompetitionDao;
import com.makarov.fa.entity.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionDao competitionDao;

    @Autowired
    public CompetitionService(CompetitionDao competitionDao) {
        this.competitionDao = competitionDao;
    }

    @Transactional
    public void addCompetition(Competition competition) {
        competitionDao.addCompetition(competition);
    }

    @Transactional
    public void addCompetitions(List<Competition> competitions) {
        competitionDao.addCompetitions(competitions);
    }

    @Transactional
    public Competition getCompetitionById(Long competitionId) {
        return competitionDao.getCompetitionById(competitionId);
    }

    @Transactional
    public List<Competition> getAllCompetitions() {
        return competitionDao.getAllCompetitions();
    }

    @Transactional
    public boolean isNotAdded() {
        return competitionDao.getAllCompetitions().isEmpty();
    }
}
