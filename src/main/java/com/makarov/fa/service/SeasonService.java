package com.makarov.fa.service;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.converter.SeasonConverter;
import com.makarov.fa.dao.AreaDao;
import com.makarov.fa.dao.SeasonDao;
import com.makarov.fa.entity.Season;
import com.makarov.fa.resourses.CompetitionResource;
import com.makarov.fa.resourses.SeasonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeasonService {

    private final SeasonDao seasonDao;

    private final FootballDataClient footballDataClient;

    private final SeasonConverter seasonConverter;

    @Autowired
    public SeasonService(AreaDao areaDao, SeasonDao seasonDao, FootballDataClient footballDataClient, SeasonConverter seasonConverter) {
        this.seasonDao = seasonDao;
        this.footballDataClient = footballDataClient;
        this.seasonConverter = seasonConverter;
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