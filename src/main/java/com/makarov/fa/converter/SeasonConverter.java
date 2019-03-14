package com.makarov.fa.converter;

import com.makarov.fa.entity.Season;
import com.makarov.fa.resourses.SeasonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeasonConverter {

    @Autowired
    public SeasonConverter() {
    }

    public Season toEntity(SeasonResource seasonResource) {

        Season season = new Season();

        season.setId(seasonResource.getId());
        season.setStartDate(seasonResource.getStartDate());
        season.setEndDate(seasonResource.getEndDate());
        return season;
    }

    public List<Season> toEntityList(List<SeasonResource> seasonResources) {

        List<Season> seasons = new ArrayList<>();

        for (SeasonResource seasonResource : seasonResources) {
            seasons.add(toEntity(seasonResource));
        }
        return seasons;
    }

    public SeasonResource toResource(Season season) {

        SeasonResource seasonResource = new SeasonResource();

        seasonResource.setId(season.getId());
        seasonResource.setStartDate(season.getStartDate());
        seasonResource.setEndDate(season.getEndDate());
        return seasonResource;
    }

    public List<SeasonResource> toResourceList(List<Season> seasons) {

        List<SeasonResource> seasonResources = new ArrayList<>();

        for (Season season : seasons) {
            seasonResources.add(toResource(season));
        }
        return seasonResources;
    }
}
