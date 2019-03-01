package com.makarov.fa.init;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.converter.CompetitionConverter;
import com.makarov.fa.converter.MatchConverter;
import com.makarov.fa.converter.PlayerConverter;
import com.makarov.fa.converter.TeamConverter;
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

    private final PlayerService playerService;

    private final MatchService matchService;

    private final AreaService areaService;

    private final CompetitionConverter competitionConverter;

    private final MatchConverter matchConverter;

    private final TeamConverter teamConverter;

    private final PlayerConverter playerConverter;


    @Autowired
    public InitializationService(CompetitionService competitionService, TeamService teamService, FootballDataClient footballDataClient, MatchService matchService, AreaService areaService, CompetitionConverter competitionConverter, MatchConverter matchConverter, TeamConverter teamConverter, PlayerService playerService, PlayerConverter playerConverter) {
        this.competitionService = competitionService;
        this.teamService = teamService;
        this.footballDataClient = footballDataClient;
        this.matchService = matchService;
        this.areaService = areaService;
        this.competitionConverter = competitionConverter;
        this.matchConverter = matchConverter;
        this.teamConverter = teamConverter;
        this.playerService = playerService;
        this.playerConverter = playerConverter;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        List<Competition> competitions = competitionConverter.toEntityList(footballDataClient.getAllCompetitions());

        List<Area> areas = footballDataClient.getAllAreas(competitions);

        List<Match> matches = matchConverter.toEntityList(footballDataClient.getAllMatches());

        List<Team> teams = teamConverter.toEntityList(footballDataClient.getAllTeams());

//        List<Player> players = footballDataClient.getAllPlayerResourcesFromTeam(teams);

        areaService.addAreas(areas);
        setAreasInCompetitions(competitions);
        competitionService.addCompetitions(competitions);
        teamService.addAllTeam(teams);
        matchService.addMatches(matches);
//        playerService.addPlayers(players);
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
