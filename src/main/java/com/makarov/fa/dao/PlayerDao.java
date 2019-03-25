package com.makarov.fa.dao;

import com.makarov.fa.entity.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class PlayerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Player getPlayerById(Long playerId) {
        return entityManager.find(Player.class, playerId);
    }

    public List<Player> getPlayersByTeamId(Long teamId) {

        String query = "select p from player p " +
                "  left join squad_player sp on players.id = sp.player_id " +
                "  where sp.squad_id = any (select squad_id from teams where teams.id = :teamId)";
        return entityManager.createQuery(query, Player.class).setParameter("teamId", teamId).getResultList();
    }

    public void addPlayer(Player player) {
        entityManager.persist(player);
    }

    public void addPlayers(List<Player> players) {
        for (Player player : players) {
            addPlayer(player);
        }
    }

    public List<Player> getAllPlayers() {
        return entityManager.createQuery("SELECT p FROM Player p", Player.class)
                .getResultList();
    }
}
