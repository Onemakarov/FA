package com.makarov.fa.converter;

import com.makarov.fa.entity.Team;
import com.makarov.fa.resourses.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamConverter {

    private final AreaConverter areaConverter;

    private final SquadConverter squadConverter;

    private final PlayerConverter playerConverter;

    @Autowired
    public TeamConverter(AreaConverter areaConverter, SquadConverter squadConverter, PlayerConverter playerConverter) {
        this.areaConverter = areaConverter;
        this.squadConverter = squadConverter;
        this.playerConverter = playerConverter;
    }

    public Team toEntity(TeamResource teamResource) {

        Team teamEntity = new Team();

        teamEntity.setId(teamResource.getId());
        teamEntity.setName(teamResource.getName());
        teamEntity.setShortName(teamResource.getShortName());
        teamEntity.setCrestUrl(teamResource.getCrestUrl());
        teamEntity.setTla(teamResource.getTla());
        teamEntity.setAddress(teamResource.getAddress());
//        teamEntity.setArea(areaConverter.toEntity(teamResource.getArea()));
        teamEntity.setClubColors(teamResource.getClubColors());
        teamEntity.setEmail(teamResource.getEmail());
        teamEntity.setFounded(teamResource.getFounded());
        teamEntity.setPhone(teamResource.getPhone());
        teamEntity.getSquad().setPlayers(playerConverter.toEntityList(teamResource.getSquad()));
        teamEntity.setVenue(teamResource.getVenue());
        teamEntity.setWebsite(teamResource.getWebsite());
        System.out.println(teamEntity);

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

