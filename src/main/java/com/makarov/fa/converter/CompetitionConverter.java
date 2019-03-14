package com.makarov.fa.converter;

import com.makarov.fa.entity.Competition;
import com.makarov.fa.resourses.CompetitionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompetitionConverter {

    private final AreaConverter areaConverter;

    private final SeasonConverter seasonConverter;

    @Autowired
    public CompetitionConverter(AreaConverter areaConverter, SeasonConverter seasonConverter) {
        this.areaConverter = areaConverter;
        this.seasonConverter = seasonConverter;
    }

    public Competition toEntity(CompetitionResource competitionResource) {

        Competition competition = new Competition();

        competition.setId(competitionResource.getId());
        competition.setName(competitionResource.getName());
        competition.setArea(areaConverter.toEntity(competitionResource.getArea()));
        competition.setCode(competitionResource.getCode());
        competition.setEmblemUrl(competitionResource.getEmblemUrl());
        competition.setPlan(competitionResource.getPlan());
        if (competitionResource.getSeasons() != null) {
            competition.setSeasons(seasonConverter.toEntityList(competitionResource.getSeasons()));
        }
        return competition;
    }

    public List<Competition> toEntityList(List<CompetitionResource> competitionResources) {

        List<Competition> competitions = new ArrayList<>();

        for (CompetitionResource competitionResource : competitionResources) {
            competitions.add(toEntity(competitionResource));
        }
        return competitions;
    }

    public CompetitionResource toResource(Competition competition) {

        CompetitionResource competitionResource = new CompetitionResource();

        competitionResource.setId(competition.getId());
        competitionResource.setName(competition.getName());
        competitionResource.setArea(areaConverter.toResource(competition.getArea()));
        competitionResource.setCode(competition.getCode());
        competitionResource.setEmblemUrl(competition.getEmblemUrl());
        competitionResource.setPlan(competition.getPlan());
        competitionResource.setSeasons(seasonConverter.toResourceList(competition.getSeasons()));
        return competitionResource;
    }

    public List<CompetitionResource> toResourceList(List<Competition> competitions) {

        List<CompetitionResource> competitionResources = new ArrayList<>();

        for (Competition competition : competitions) {
            competitionResources.add(toResource(competition));
        }
        return competitionResources;
    }
}

