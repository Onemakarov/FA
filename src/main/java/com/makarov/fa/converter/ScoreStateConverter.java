package com.makarov.fa.converter;

import com.makarov.fa.entity.ScoreState;
import com.makarov.fa.resourses.ScoreStateResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScoreStateConverter {

    public ScoreState toEntity(ScoreStateResource scoreStateResource) {

        ScoreState scoreStateEntity = new ScoreState();

        scoreStateEntity.setHomeTeam(scoreStateResource.getHomeTeam());
        scoreStateEntity.setAwayTeam(scoreStateResource.getAwayTeam());

        return scoreStateEntity;
    }
}
