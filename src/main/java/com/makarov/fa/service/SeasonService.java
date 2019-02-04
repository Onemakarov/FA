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
    public void addAllSeasons(List<CompetitionResource> competitionResources) {

        List<Season> seasonList = seasonConverter.toEntityList(getSeasonsResources(competitionResources));

        for (Season season : seasonList) {
            seasonDao.addSeason(season);
        }
    }

    private List<SeasonResource> getSeasonsResources(List<CompetitionResource> competitionResources) {

        List<SeasonResource> seasonResources = new ArrayList<>();

        for (CompetitionResource competitionResource : competitionResources) {
            seasonResources.addAll(competitionResource.getSeasons());
        }
        return seasonResources;
    }
}