package com.makarov.fa.service;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.dao.AreaDao;
import com.makarov.fa.dao.CompetitionDao;
import com.makarov.fa.entity.Area;
import com.makarov.fa.entity.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompetitionService {

    private final CompetitionDao competitionDao;

    private final FootballDataClient footballDataClient;

    private final AreaDao areaDao;

    @Autowired
    public CompetitionService(CompetitionDao competitionDao, FootballDataClient footballDataClient, AreaDao areaDao) {
        this.competitionDao = competitionDao;
        this.footballDataClient = footballDataClient;
        this.areaDao = areaDao;
    }

    @Transactional
    public void addCompetition(Competition competition) {
        competitionDao.addCompetition(competition);
    }

    @Transactional
    public void addAllCompetitions() {
        competitionDao.addAllCompetitions();
    }
}
