package com.makarov.fa.converter;

import com.makarov.fa.entity.Squad;
import com.makarov.fa.entity.Team;
import com.makarov.fa.resourses.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamConverter {

    private final AreaConverter areaConverter;

    private final PlayerConverter playerConverter;

    private final CompetitionConverter competitionConverter;

    @Autowired
    public TeamConverter(AreaConverter areaConverter, PlayerConverter playerConverter, CompetitionConverter competitionConverter) {
        this.areaConverter = areaConverter;
        this.playerConverter = playerConverter;
        this.competitionConverter = competitionConverter;
    }

    public Team toEntity(TeamResource teamResource) {

        Team team = new Team();

        team.setId(teamResource.getId());
        if (teamResource.getActiveCompetitions() != null) {
            team.setActiveCompetitions(competitionConverter.toEntityList(teamResource.getActiveCompetitions()));
        }
        team.setName(teamResource.getName());
        team.setShortName(teamResource.getShortName());
        team.setCrestUrl(teamResource.getCrestUrl());
        team.setTla(teamResource.getTla());
        team.setAddress(teamResource.getAddress());
        if (teamResource.getArea() != null) {
            team.setArea(areaConverter.toEntity(teamResource.getArea()));
        }
        team.setClubColors(teamResource.getClubColors());
        team.setEmail(teamResource.getEmail());
        team.setFounded(teamResource.getFounded());
        team.setPhone(teamResource.getPhone());
        if (teamResource.getSquad() != null) {
            Squad squad = new Squad();
            squad.setPlayers((playerConverter.toEntityList(teamResource.getSquad())));
            team.setSquad(squad);
        }
        team.setVenue(teamResource.getVenue());
        team.setWebsite(teamResource.getWebsite());
        return team;
    }

    public List<Team> toEntityList(List<TeamResource> teamResources) {

        List<Team> teams = new ArrayList<>();

        for (TeamResource teamResource : teamResources) {
            teams.add(toEntity(teamResource));
        }
        return teams;
    }

    public TeamResource toResource(Team team) {

        TeamResource teamResource = new TeamResource();

        teamResource.setId(team.getId());
        teamResource.setActiveCompetitions(competitionConverter.toResourceList(team.getActiveCompetitions()));
        teamResource.setName(team.getName());
        teamResource.setShortName(team.getShortName());
        teamResource.setCrestUrl(team.getCrestUrl());
        teamResource.setTla(team.getTla());
        teamResource.setAddress(team.getAddress());
        teamResource.setArea(areaConverter.toResource(team.getArea()));
        teamResource.setClubColors(team.getClubColors());
        teamResource.setEmail(team.getEmail());
        teamResource.setFounded(team.getFounded());
        teamResource.setPhone(team.getPhone());
        teamResource.setSquad(playerConverter.toResourceList(team.getSquad().getPlayers()));
        teamResource.setVenue(team.getVenue());
        teamResource.setWebsite(team.getWebsite());
        return teamResource;
    }

    public List<TeamResource> toResourceList(List<Team> teams) {

        List<TeamResource> teamResources = new ArrayList<>();

        for (Team team : teams) {
            teamResources.add(toResource(team));
        }
        return teamResources;
    }
}

