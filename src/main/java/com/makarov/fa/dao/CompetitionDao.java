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

    public void addAllCompetitions(List<Competition> competitionList) {
        for (Competition competition : competitionList) {
            addCompetition(competition);
            System.out.println();
        }
    }
}
