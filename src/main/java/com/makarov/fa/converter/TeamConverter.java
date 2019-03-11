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

        Team teamEntity = new Team();

        teamEntity.setId(teamResource.getId());
        teamEntity.setActiveCompetitions(competitionConverter.toEntityList(teamResource.getActiveCompetitions()));
        teamEntity.setName(teamResource.getName());
        teamEntity.setShortName(teamResource.getShortName());
        teamEntity.setCrestUrl(teamResource.getCrestUrl());
        teamEntity.setTla(teamResource.getTla());
        teamEntity.setAddress(teamResource.getAddress());
        teamEntity.setArea(areaConverter.toEntity(teamResource.getArea()));
        teamEntity.setClubColors(teamResource.getClubColors());
        teamEntity.setEmail(teamResource.getEmail());
        teamEntity.setFounded(teamResource.getFounded());
        teamEntity.setPhone(teamResource.getPhone());
        if (teamResource.getSquad() != null) {
            Squad squad = new Squad();
            squad.setPlayers((playerConverter.toEntityList(teamResource.getSquad())));
            System.out.println(squad.getPlayers());
            teamEntity.setSquad(squad);
        }
        teamEntity.setVenue(teamResource.getVenue());
        teamEntity.setWebsite(teamResource.getWebsite());
        return teamEntity;
    }

    public List<Team> toEntityList(List<TeamResource> teamResources) {

        List<Team> teams = new ArrayList<>();

        for (TeamResource teamResource : teamResources) {
            teams.add(toEntity(teamResource));
        }
        return teams;
    }
}

