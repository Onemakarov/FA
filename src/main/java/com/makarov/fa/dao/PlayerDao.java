package com.makarov.fa.dao;

import com.makarov.fa.entity.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PlayerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Player getPlayerById(Long playerId) {
        return entityManager.find(Player.class, playerId);
    }

    public List<Player> getPlayersByTeamId(Long teamId) {
        return null;
    }

    public void addPlayer(Player player) {
        entityManager.persist(player);
    }

    public void addPlayers(List<Player> players) {
        for (Player player : players) {
            addPlayer(player);
        }
    }
}
