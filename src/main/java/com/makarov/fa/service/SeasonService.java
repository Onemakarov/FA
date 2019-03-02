package com.makarov.fa.service;

import com.makarov.fa.dao.SeasonDao;
import com.makarov.fa.entity.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeasonService {

    private final SeasonDao seasonDao;

    @Autowired
    public SeasonService(SeasonDao seasonDao) {
        this.seasonDao = seasonDao;
    }

    @Transactional
    public void addSeason(Season season) {
        seasonDao.addSeason(season);
    }

    @Transactional
    public void addAllSeasons(List<Season> seasons) {

        for (Season season : seasons) {
            seasonDao.addSeason(season);
        }
    }

    @Transactional
    public Season getSeasonById(Long seasonId) {
        return seasonDao.getSeasonById(seasonId);
    }

    @Transactional
    public List<Season> getAllSeasons() {
        return seasonDao.getAllSeasons();
    }
}