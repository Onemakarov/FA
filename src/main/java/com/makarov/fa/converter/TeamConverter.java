package com.makarov.fa.converter;

import com.makarov.fa.entity.Season;
import com.makarov.fa.entity.Team;
import com.makarov.fa.resourses.SeasonResource;
import com.makarov.fa.resourses.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamConverter {

    private final AreaConverter areaConverter;

    @Autowired
    public TeamConverter(AreaConverter areaConverter) {
        this.areaConverter = areaConverter;
    }

    public Team toEntity(TeamResource teamResource) {

        Team team = new Team();

        team.setId(teamResource.getId());
        team.setName(teamResource.getName());
        team.setShortName(teamResource.getShortName());
        team.setCrestUrl(teamResource.getCrestUrl());
        team.setTla(teamResource.getTla());
        team.setAddress(teamResource.getAddress());
//        team.setArea(areaConverter.toEntity(teamResource.getArea()));
        team.setClubColors(teamResource.getClubColors());
        team.setEmail(teamResource.getEmail());
        team.setFounded(teamResource.getFounded());
        team.setPhone(teamResource.getPhone());
       // team.setSquad(teamResource.getSquad());
        team.setVenue(teamResource.getVenue());
        team.setWebsite(teamResource.getWebsite());
        System.out.println(team);

        return team;
    }

    public List<Team> toEntityList(List<TeamResource> teamResources) {

        List<Team> teams = new ArrayList<>();

        for (TeamResource teamResource : teamResources) {
            teams.add(toEntity(teamResource));
        }
        return teams;
    }
}

