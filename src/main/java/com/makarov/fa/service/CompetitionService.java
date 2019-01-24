package com.makarov.fa.service;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.dao.AreaDao;
import com.makarov.fa.dao.CompetitionDao;
import com.makarov.fa.entity.Area;
import com.makarov.fa.entity.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        competitionDao.addAllCompetitions(setAreasFromDbInCompetitions());
    }

    @Transactional
    public Competition getCompetitionById(Long competitionId) {
        return competitionDao.getCompetitionById(competitionId);
    }

    @Transactional
    List<Competition> setAreasFromDbInCompetitions() {
        List<Area> allAreas = areaDao.getAllAreas();
        List<Competition> competitionList = footballDataClient.getAllCompetitions();
        for (Competition competition : competitionList) {
            for (Area area : allAreas) {
                if (compareArea(competition, area)) {
                    competition.setArea(area);
                }
            }
        }
        return competitionList;
    }

    private static boolean compareArea(Competition competition, Area area) {
        return competition.getArea().getId().equals(area.getId());
    }
}
