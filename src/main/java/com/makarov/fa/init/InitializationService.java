package com.makarov.fa.init;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.resourses.CompetitionResource;
import com.makarov.fa.resourses.MatchResource;
import com.makarov.fa.resourses.TeamResource;
import com.makarov.fa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class InitializationService implements ApplicationListener<ApplicationReadyEvent> {

    private final AreaService areaService;

    private final CompetitionService competitionService;

    private final SeasonService seasonService;

    private final TeamService teamService;

    private final FootballDataClient footballDataClient;

    private final MatchService matchService;

    @Autowired
    public InitializationService(AreaService areaService, CompetitionService competitionService, SeasonService seasonService, TeamService teamService, FootballDataClient footballDataClient, MatchService matchService) {
        this.areaService = areaService;
        this.competitionService = competitionService;
        this.seasonService = seasonService;
        this.teamService = teamService;
        this.footballDataClient = footballDataClient;
        this.matchService = matchService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        List<CompetitionResource> competitionResources = footballDataClient.getAllCompetitions();

        List<TeamResource> teamResources = footballDataClient.getAllTeams();

        List<MatchResource> matchResources = footballDataClient.getAllMatches();

        footballDataClient.setCompetitionInMatch(competitionResources, matchResources);
//        areaService.addAllAreas(competitionResources);
//        seasonService.addAllSeasons(competitionResources);
        competitionService.addAllCompetitions(competitionResources);
//        teamService.addAllTeam(teamResources);
//        matchService.addMatches(matchResources);
        System.out.println();
    }
}
