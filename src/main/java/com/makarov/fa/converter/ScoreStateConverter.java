package com.makarov.fa.converter;

import com.makarov.fa.entity.ScoreState;
import com.makarov.fa.resourses.ScoreStateResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScoreStateConverter {

    public ScoreState toEntity(ScoreStateResource scoreStateResource) {

        ScoreState scoreState = new ScoreState();

        scoreState.setHomeTeam(scoreStateResource.getHomeTeam());
        scoreState.setAwayTeam(scoreStateResource.getAwayTeam());
        return scoreState;
    }

    public List<ScoreState> toEntityList(List<ScoreStateResource> scoreStateResources) {

        List<ScoreState> scoreStates = new ArrayList<>();

        for (ScoreStateResource scoreStateResource : scoreStateResources) {
            scoreStates.add(toEntity(scoreStateResource));
        }
        return scoreStates;
    }

    public ScoreStateResource toResource(ScoreState scoreState) {

        ScoreStateResource scoreStateResource = new ScoreStateResource();

        scoreStateResource.setHomeTeam(scoreState.getHomeTeam());
        scoreStateResource.setAwayTeam(scoreState.getAwayTeam());
        return scoreStateResource;
    }

    public List<ScoreStateResource> toResourceList(List<ScoreState> scoreStates) {

        List<ScoreStateResource> scoreStateResources = new ArrayList<>();

        for (ScoreState scoreState : scoreStates) {
            scoreStateResources.add(toResource(scoreState));
        }
        return scoreStateResources;
    }
}
