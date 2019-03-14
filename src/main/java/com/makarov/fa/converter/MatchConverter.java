package com.makarov.fa.converter;

import com.makarov.fa.entity.Match;
import com.makarov.fa.resourses.MatchResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MatchConverter {

    private final CompetitionConverter competitionConverter;

    private final SeasonConverter seasonConverter;

    private final ScoreConverter scoreConverter;

    @Autowired
    public MatchConverter(CompetitionConverter competitionConverter, SeasonConverter seasonConverter, ScoreConverter scoreConverter) {
        this.competitionConverter = competitionConverter;
        this.seasonConverter = seasonConverter;
        this.scoreConverter = scoreConverter;
    }

    public Match toEntity(MatchResource matchResource) {

        Match match = new Match();

        match.setId(matchResource.getId());
        match.setCompetition(competitionConverter.toEntity(matchResource.getCompetition()));
        match.setSeason(seasonConverter.toEntity(matchResource.getSeason()));
        match.setUtcDate(matchResource.getUtcDate());
        match.setStatus(matchResource.getStatus());
        match.setMatchDay(matchResource.getMatchDay());
        match.setStage(matchResource.getStage());
        match.setGroup(matchResource.getGroup());
        match.setScore(scoreConverter.toEntity(matchResource.getScore()));
        return match;
    }

    public List<Match> toEntityList(List<MatchResource> matchResources) {

        List<Match> matches = new ArrayList<>();

        for (MatchResource matchResource : matchResources) {
            matches.add(toEntity(matchResource));
        }
        return matches;
    }

    public MatchResource toResource(Match match) {

        MatchResource matchResource = new MatchResource();

        matchResource.setId(match.getId());
        matchResource.setCompetition(competitionConverter.toResource(match.getCompetition()));
        matchResource.setSeason(seasonConverter.toResource(match.getSeason()));
        matchResource.setUtcDate(match.getUtcDate());
        matchResource.setStatus(match.getStatus());
        matchResource.setMatchDay(match.getMatchDay());
        matchResource.setStage(match.getStage());
        matchResource.setGroup(match.getGroup());
        matchResource.setScore(scoreConverter.toResource(match.getScore()));
        return matchResource;
    }

    public List<MatchResource> toResourceList(List<Match> matches) {

        List<MatchResource> matchResources = new ArrayList<>();

        for (Match match : matches) {
            matchResources.add(toResource(match));
        }
        return matchResources;
    }
}
