package com.makarov.fa.init;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.entity.*;
import com.makarov.fa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitializationService implements ApplicationListener<ApplicationReadyEvent> {

    private final FootballDataClient footballDataClient;

    private final CompetitionService competitionService;

    private final TeamService teamService;

    private final MatchService matchService;

    private final AreaService areaService;

    @Autowired
    public InitializationService(CompetitionService competitionService, TeamService teamService, FootballDataClient footballDataClient, MatchService matchService, AreaService areaService) {
            this.competitionService = competitionService;
            this.teamService = teamService;
            this.footballDataClient = footballDataClient;
            this.matchService = matchService;
        this.areaService = areaService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        List<Competition> competitions = footballDataClient.getAllCompetitions();

        List<Area> areas = footballDataClient.getAllAreas(competitions);

        List<Match> matches = footballDataClient.getAllMatches();

        List<Team> teams = footballDataClient.getAllTeams();

        areaService.addAreas(areas);
        setAreasInCompetitions(competitions);
        competitionService.addCompetitions(competitions);
        teamService.addAllTeam(teams);
        matchService.addMatches(matches);
        System.out.println();
    }

    private void setAreasInCompetitions(List<Competition> competitions) {
        List<Area> areas = areaService.getAllAreas();
        for (Competition competition : competitions) {
            for (Area area : areas) {
                if (competition.getArea().getId().equals(area.getId())) {
                    competition.setArea(area);
                }
            }
        }
    }
}
