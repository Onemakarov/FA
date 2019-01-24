package com.makarov.fa.dao;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.entity.Area;
import com.makarov.fa.entity.Competition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CompetitionDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final AreaDao areaDao;

    private final FootballDataClient footballDataClient;

    @Autowired
    public CompetitionDao(AreaDao areaDao, FootballDataClient footballDataClient) {
        this.areaDao = areaDao;
        this.footballDataClient = footballDataClient;
    }

    public List<Competition> getAllCompetitions() {
        return entityManager.createQuery("SELECT c FROM Competition c", Competition.class)
                .getResultList();
    }

    public Competition getCompetitionById(long competitionId) {
        return entityManager.find(Competition.class, competitionId);
    }

    public void addCompetition(Competition competition) {
        entityManager.persist(competition);
    }

    public void addAllCompetitions() {
        List<Competition> competitionList = setAreasFromDbInCompetitions();
        for (Competition competition : competitionList) {
            addCompetition(competition);
        }
    }

    private List<Competition> setAreasFromDbInCompetitions() {
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
