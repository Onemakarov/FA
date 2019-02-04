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

    @Autowired
    public CompetitionConverter(AreaConverter areaConverter) {
        this.areaConverter = areaConverter;
    }

    public Competition toEntity(CompetitionResource competitionResource) {

        Competition competition = new Competition();

        competition.setId(competitionResource.getId());
        competition.setName(competitionResource.getName());
        competition.setArea(areaConverter.toEntity(competitionResource.getArea()));
        competition.setCode(competitionResource.getCode());
        competition.setEmblemUrl(competitionResource.getEmblemUrl());
        competition.setPlan(competitionResource.getPlan());

        return competition;
    }

    public List<Competition> toEntityList(List<CompetitionResource> competitionResources) {

        List<Competition> competitions = new ArrayList<>();

        for (CompetitionResource competitionResource : competitionResources) {
            competitions.add(toEntity(competitionResource));
        }
        return competitions;
    }
}

