package com.makarov.fa.dao;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.entity.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SeasonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Season> getAllSeasons() {
        Query query = entityManager.createNativeQuery("SELECT * FROM seasons", Season.class);
        return query.getResultList();
//        return entityManager.createQuery("SELECT s FROM Season s", Season.class)
//                .getResultList();
    }

    public Season getSeasonById(long seasonId) {
        return entityManager.find(Season.class, seasonId);
    }

    public void addSeason(Season season) {
        entityManager.persist(season);
    }

    public void addAllSeasons(List<Season> seasonList) {
        for (Season season : seasonList) {
            addSeason(season);
        }
    }
}