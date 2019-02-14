package com.makarov.fa.init;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.converter.CompetitionConverter;
import com.makarov.fa.converter.MatchConverter;
import com.makarov.fa.converter.TeamConverter;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.entity.Match;
import com.makarov.fa.entity.Team;
import com.makarov.fa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitializationService implements ApplicationListener<ApplicationReadyEvent> {

    private final CompetitionService competitionService;

    private final TeamService teamService;

    private final FootballDataClient footballDataClient;

    private final MatchService matchService;

    private final CompetitionConverter competitionConverter;

    private final MatchConverter matchConverter;

    private final TeamConverter teamConverter;

    @Autowired
    public InitializationService(CompetitionService competitionService, TeamService teamService, FootballDataClient footballDataClient, MatchService matchService, CompetitionConverter competitionConverter, MatchConverter matchConverter, TeamConverter teamConverter) {
        this.competitionService = competitionService;
        this.teamService = teamService;
        this.footballDataClient = footballDataClient;
        this.matchService = matchService;
        this.competitionConverter = competitionConverter;
        this.matchConverter = matchConverter;
        this.teamConverter = teamConverter;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        List<Competition> competitions = competitionConverter.toEntityList(footballDataClient.getAllCompetitions());

        List<Match> matches = matchConverter.toEntityList(footballDataClient.getAllMatches());

        List<Team> teams = teamConverter.toEntityList(footballDataClient.getAllTeams());

//        areaService.addAreas(competitionResources);
//        seasonService.addAllSeasons(competitionResources);
        parseCompetitions(competitions);
        teamService.addAllTeam(teams);
        matchService.addMatches(matches);
        System.out.println();
    }

    private void parseCompetitions(List<Competition> competitions) {
        competitionService.addCompetitions(competitions);
    }
}
