package com.makarov.fa.init;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.entity.*;
import com.makarov.fa.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitializationService implements ApplicationListener<ApplicationReadyEvent> {

    private final FootballDataClient footballDataClient;

    private final CompetitionService competitionService;

    private final TeamService teamService;

    private final MatchService matchService;

    private final AreaService areaService;

    private final SeasonService seasonService;

    @Autowired
    public InitializationService(CompetitionService competitionService, TeamService teamService, FootballDataClient footballDataClient, MatchService matchService, AreaService areaService, SeasonService seasonService) {
            this.competitionService = competitionService;
            this.teamService = teamService;
            this.footballDataClient = footballDataClient;
            this.matchService = matchService;
        this.areaService = areaService;
        this.seasonService = seasonService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        List<Competition> competitions = footballDataClient.getAllCompetitions();

        List<Match> matches = footballDataClient.getAllMatches();

        List<Team> teams = footballDataClient.getAllTeams();

        List<Area> areas = footballDataClient.getAllAreas(teams, competitions);

        areaService.addAreas(areas);
        setAreasInCompetitions(competitions);
        setAreasInTeams(teams);
        competitionService.addCompetitions(competitions);
        setCompetitionsInTeams(teams);
        teamService.addAllTeam(teams);
        setCompetitionsInMatches(matches);
        setSeasonInMatch(matches);
        matchService.addMatches(matches);
        System.out.println();
    }

    private void setSeasonInMatch(List<Match> matches) {

        List<Season> seasons = seasonService.getAllSeasons();
        for (Match match : matches) {
            for (Season season : seasons) {
                if (match.getSeason().getId().equals(season.getId())) {
                    match.setSeason(season);
                }
            }
        }
    }

    private void setCompetitionsInTeams(List<Team> teams) {

        List<Competition> competitions = competitionService.getAllCompetitions();
        for (Team team : teams) {
            List<Competition> activeCompetitions = new ArrayList<>();
            for (Competition activeCompetition : team.getActiveCompetitions()) {
                for (Competition competition : competitions) {
                    if (activeCompetition.getId().equals(competition.getId())) {
                        activeCompetitions.add(competition);
                    }
                }
            }
           team.setActiveCompetitions(activeCompetitions);
        }
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

    private void setAreasInTeams(List<Team> teams) {

        List<Area> areas = areaService.getAllAreas();
        for (Team team : teams) {
            for (Area area : areas) {
                if (team.getArea().getId().equals(area.getId())) {
                    team.setArea(area);
                }
            }
        }
    }

    private void setCompetitionsInMatches(List<Match> matches) {

        List<Competition> competitions = competitionService.getAllCompetitions();
        for (Match match : matches) {
            for (Competition competition : competitions) {
                if (match.getCompetition().getId().equals(competition.getId())) {
                    match.setCompetition(competition);
                }
            }
        }
    }
}
