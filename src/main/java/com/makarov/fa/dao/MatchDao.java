package com.makarov.fa.dao;

import com.makarov.fa.entity.Match;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MatchDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Match getMachById(long id) {
        return entityManager.find(Match.class, id);
    }

    public List<Match> getAllMatchesBySeasonId(long seasonId) {

        return entityManager.createQuery("SELECT m FROM Match m WHERE m.season.id = :seasonId", Match.class)
                .setParameter("seasonId", seasonId)
                .getResultList();
    }

    public void addMatches(List<Match> matches) {

        for (Match match : matches) {
            addMatch(match);
        }
    }

    public void addMatch(Match match) {

        entityManager.persist(match);
    }
}
