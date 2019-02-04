package com.makarov.fa.dao;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.entity.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SeasonDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final FootballDataClient footballDataClient;

    @Autowired
    public SeasonDao(FootballDataClient footballDataClient) {
        this.footballDataClient = footballDataClient;
    }

    public List<Competition> getAllSeasons() {
        return null;
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