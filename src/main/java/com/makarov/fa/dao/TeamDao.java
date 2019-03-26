package com.makarov.fa.dao;

import com.makarov.fa.entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TeamDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addTeam(Team team) {
        entityManager.persist(team);
    }

    public void addAllTeam(List<Team> teams) {
        for (Team team : teams) {
            addTeam(team);
        }
    }

    public List<Team> getAllTeams() {
        return entityManager.createQuery("select t from teams t", Team.class).getResultList();
    }

    public List<Team> getTeamsByCompetitionId(long competitionId) {

        String query = "select t from teams t inner join teams_active_competitions tac on t.id = tac.team_id where tac.competition_id = :competitionId";
        return entityManager.createQuery(query, Team.class).setParameter("competitionId", competitionId).getResultList();
    }
}
