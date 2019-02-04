package com.makarov.fa.converter;

import com.makarov.fa.entity.Match;
import com.makarov.fa.resourses.MatchResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        Match matchEntity = new Match();

        matchEntity.setId(matchResource.getId());
        matchEntity.setCompetition(competitionConverter.toEntity(matchResource.getCompetition()));
        matchEntity.setSeason(seasonConverter.toEntity(matchResource.getSeason()));
        matchEntity.setUtcDate(matchResource.getUtcDate());
        matchEntity.setStatus(matchResource.getStatus());
        matchEntity.setMatchDay(matchResource.getMatchDay());
        matchEntity.setStage(matchResource.getStage());
        matchEntity.setGroup(matchResource.getGroup());
        matchEntity.setScore(scoreConverter.toEntity(matchResource.getScore()));

        return matchEntity;
    }
}
