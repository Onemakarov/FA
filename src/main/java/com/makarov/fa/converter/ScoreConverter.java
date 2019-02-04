package com.makarov.fa.converter;

import com.makarov.fa.entity.Score;
import com.makarov.fa.resourses.ScoreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreConverter {

    private final ScoreStateConverter scoreStateConverter;

    @Autowired
    public ScoreConverter(ScoreStateConverter scoreStateConverter) {
        this.scoreStateConverter = scoreStateConverter;
    }

    public Score toEntity(ScoreResource scoreResource) {

        Score scoreEntity = new Score();

        scoreEntity.setId(scoreResource.getId());
        scoreEntity.setWinner(scoreResource.getWinner());
        scoreEntity.setDuration(scoreResource.getDuration());
        scoreEntity.setFullTime(scoreStateConverter.toEntity(scoreResource.getFullTime()));
        scoreEntity.setHalfTime(scoreStateConverter.toEntity(scoreResource.getHalfTime()));
        scoreEntity.setExtraTime(scoreStateConverter.toEntity(scoreResource.getExtraTime()));
        scoreEntity.setPenalties(scoreStateConverter.toEntity(scoreResource.getPenalties()));

        return scoreEntity;
    }
}
