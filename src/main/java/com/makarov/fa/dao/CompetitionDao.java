package com.makarov.fa.dao;

import com.makarov.fa.entity.Competition;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Log4j2
public class CompetitionDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Competition> getAllCompetitions() {
        return entityManager.createQuery("SELECT c FROM Competition c", Competition.class)
                .getResultList();
    }

    public Competition getCompetitionById(Long competitionId) {
        return entityManager.find(Competition.class, competitionId);
    }

    public void addCompetition(Competition competition) {
        entityManager.persist(competition);
    }

    public void addCompetitions(List<Competition> competitionList) {
        for (Competition competition : competitionList) {
            addCompetition(competition);
            System.out.println();
        }
    }
}
