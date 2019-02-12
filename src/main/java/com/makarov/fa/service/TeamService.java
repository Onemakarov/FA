package com.makarov.fa.service;

import com.makarov.fa.converter.TeamConverter;
import com.makarov.fa.dao.TeamDao;
import com.makarov.fa.entity.Team;
import com.makarov.fa.resourses.TeamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamService {

    private final TeamDao teamDao;

    @Autowired
    public TeamService(TeamDao teamDao, TeamConverter teamConverter) {
        this.teamDao = teamDao;
    }

    @Transactional
    public void addTeam(Team team) {
        teamDao.addTeam(team);
    }

    @Transactional
    public void addAllTeam(List<Team> teams) {
        for (Team team : teams) {
            addTeam(team);
        }
    }
}
