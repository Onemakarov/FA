package com.makarov.fa.converter;

import com.makarov.fa.entity.Score;
import com.makarov.fa.resourses.ScoreResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScoreConverter {

    private final ScoreStateConverter scoreStateConverter;

    @Autowired
    public ScoreConverter(ScoreStateConverter scoreStateConverter) {
        this.scoreStateConverter = scoreStateConverter;
    }

    public Score toEntity(ScoreResource scoreResource) {

        Score score = new Score();

        score.setId(scoreResource.getId());
        score.setWinner(scoreResource.getWinner());
        score.setDuration(scoreResource.getDuration());
        score.setFullTime(scoreStateConverter.toEntity(scoreResource.getFullTime()));
        score.setHalfTime(scoreStateConverter.toEntity(scoreResource.getHalfTime()));
        score.setExtraTime(scoreStateConverter.toEntity(scoreResource.getExtraTime()));
        score.setPenalties(scoreStateConverter.toEntity(scoreResource.getPenalties()));
        return score;
    }

    public List<Score> toEntityList(List<ScoreResource> scoreResources) {

        List<Score> scores = new ArrayList<>();

        for (ScoreResource scoreResource : scoreResources) {
            scores.add(toEntity(scoreResource));
        }
        return scores;
    }

    public ScoreResource toResource(Score score) {

        ScoreResource scoreResource = new ScoreResource();

        scoreResource.setId(score.getId());
        scoreResource.setWinner(score.getWinner());
        scoreResource.setDuration(score.getDuration());
        scoreResource.setFullTime(scoreStateConverter.toResource(score.getFullTime()));
        scoreResource.setHalfTime(scoreStateConverter.toResource(score.getHalfTime()));
        scoreResource.setExtraTime(scoreStateConverter.toResource(score.getExtraTime()));
        scoreResource.setPenalties(scoreStateConverter.toResource(score.getPenalties()));
        return scoreResource;
    }

    public List<ScoreResource> toResourceList(List<Score> scores) {

        List<ScoreResource> scoreResources = new ArrayList<>();

        for (Score score : scores) {
            scoreResources.add(toResource(score));
        }
        return scoreResources;
    }
}
