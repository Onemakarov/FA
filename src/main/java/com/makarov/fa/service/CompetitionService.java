package com.makarov.fa.service;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.converter.CompetitionConverter;
import com.makarov.fa.dao.AreaDao;
import com.makarov.fa.dao.CompetitionDao;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.resourses.CompetitionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class CompetitionService {

    private final CompetitionDao competitionDao;

    private final FootballDataClient footballDataClient;

    private final AreaDao areaDao;

    private final CompetitionConverter competitionConverter;

    @Autowired
    public CompetitionService(CompetitionDao competitionDao, FootballDataClient footballDataClient, AreaDao areaDao, CompetitionConverter competitionConverter) {
        this.competitionDao = competitionDao;
        this.footballDataClient = footballDataClient;
        this.areaDao = areaDao;
        this.competitionConverter = competitionConverter;
    }

    @Transactional
    public void addCompetition(Competition competition) {
        competitionDao.addCompetition(competition);
    }

    @Transactional
    public void addAllCompetitions(List<Competition> competitions) {
        competitionDao.addAllCompetitions(competitions);
    }

    @Transactional
    public Competition getCompetitionById(Long competitionId) {
        return competitionDao.getCompetitionById(competitionId);
    }

//    public List<CompetitionResource> getCompetitionResources(List<Long> competitionsId) {
//    }

//    @Transactional
//    List<Competition> setAreasFromDbInCompetitions() {
//        List<Area> allAreas = areaDao.getAllAreas();
//        List<Competition> competitionList = footballDataClient.getAllCompetitions();
//        for (Competition competition : competitionList) {
//            for (Area area : allAreas) {
//                if (compareArea(competition, area)) {
//                    competition.setArea(area);
//                }
//            }
//        }
//        return competitionList;
//    }
//
//    private static boolean compareArea(Competition competition, Area area) {
//        return competition.getArea().getId().equals(area.getId());
//    }
}
