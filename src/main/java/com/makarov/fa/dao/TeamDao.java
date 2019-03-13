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
        Query query = entityManager.createNativeQuery("SELECT * FROM teams", Team.class);
        return query.getResultList();
    }
}
