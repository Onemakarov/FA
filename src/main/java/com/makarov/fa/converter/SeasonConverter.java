package com.makarov.fa.converter;

import com.makarov.fa.entity.Season;
import com.makarov.fa.resourses.SeasonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeasonConverter {

    private final TeamConverter teamConverter;

    @Autowired
    public SeasonConverter(TeamConverter teamConverter) {
        this.teamConverter = teamConverter;
    }

    public Season toEntity(SeasonResource seasonResource) {

        Season season = new Season();

        season.setId(seasonResource.getId());
        season.setStartDate(seasonResource.getStartDate());
        season.setEndDate(seasonResource.getEndDate());
//        season.setWinner(teamConverter.toEntity(seasonResource.getWinner()));

        return season;
    }

    public List<Season> toEntityList(List<SeasonResource> seasonResources) {

        List<Season> seasons = new ArrayList<>();

        for (SeasonResource seasonResource : seasonResources) {
            seasons.add(toEntity(seasonResource));
        }
        return seasons;
    }
}
