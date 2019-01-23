package com.makarov.fa.service;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.dao.CompetitionDao;
import com.makarov.fa.entity.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionDao competitionDao;

    private final FootballDataClient footballDataClient;

    @Autowired
    public CompetitionService(CompetitionDao competitionDao, FootballDataClient footballDataClient) {
        this.competitionDao = competitionDao;
        this.footballDataClient = footballDataClient;
    }

    @Transactional
    public void addCompetition(Competition competition) {
        competitionDao.addCompetition(competition);
    }

    @Transactional
    public void addAllCompetitions() {
        List<Competition> competitionList = footballDataClient.getAllCompetitions();
        for (Competition competition : competitionList) {
            addCompetition(competition);
        }
    }
}
