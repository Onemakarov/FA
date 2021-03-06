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

    public Match getMachById(Long id) {
        return entityManager.find(Match.class, id);
    }

    public List<Match> getAllMatchesBySeasonId(Long seasonId) {
        return entityManager.createQuery("Select m From Match m Where m.season.id = :seasonId", Match.class)
                .setParameter("seasonId", seasonId)
                .getResultList();
    }

    public List<Match> getAllMatches() {
        return entityManager.createQuery("Select m From Match m ", Match.class)
                .getResultList();
    }

    public List<Match> getAllMatches(int limit) {
        return entityManager.createQuery("Select m From Match m", Match.class)
                .setMaxResults(limit)
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
