package com.makarov.fa.dao;

import com.makarov.fa.entity.Competition;
import com.makarov.fa.entity.CompetitionList;
import com.makarov.fa.entity.Match;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CompetitionDao {

    @PersistenceContext
    private EntityManager entityManager;

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
}
