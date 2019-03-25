package com.makarov.fa.service;

import com.makarov.fa.dao.PlayerDao;
import com.makarov.fa.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerDao playerDao;

    @Autowired
    public PlayerService(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Transactional
    public void addPlayer(Player player) {
        playerDao.addPlayer(player);
    }

    @Transactional
    public void addPlayers(List<Player> players) {
        playerDao.addPlayers(players);
    }

    @Transactional
    public Player getPlayerById(Long playerId) {
        return playerDao.getPlayerById(playerId);
    }

    @Transactional
    public List<Player> getPlayersByTeamId(Long teamId) {
        return playerDao.getPlayersByTeamId(teamId);
    }

    public List<Player> getAllPlayers() {
        return playerDao.getAllPlayers();
    }
}
