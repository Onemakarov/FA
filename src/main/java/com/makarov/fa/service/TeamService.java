package com.makarov.fa.service;

import com.makarov.fa.converter.TeamConverter;
import com.makarov.fa.dao.TeamDao;
import com.makarov.fa.resourses.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    private final TeamDao teamDao;

    private final TeamConverter teamConverter;

    @Autowired
    public TeamService(TeamDao teamDao, TeamConverter teamConverter) {
        this.teamDao = teamDao;
        this.teamConverter = teamConverter;
    }

    @Transactional
    public void addTeam(TeamResource teamResource) {
        teamDao.addTeam(teamConverter.toEntity(teamResource));
    }

    @Transactional
    public void addAllTeam(List<TeamResource> teamResources) {
        for (TeamResource teamResource : teamResources) {
            addTeam(teamResource);
        }
    }
}
