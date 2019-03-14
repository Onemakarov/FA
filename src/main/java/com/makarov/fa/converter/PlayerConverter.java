package com.makarov.fa.converter;

import com.makarov.fa.entity.Player;
import com.makarov.fa.resourses.PlayerResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerConverter {

    public Player toEntity(PlayerResource playerResource) {

        Player player = new Player();

        player.setName(playerResource.getName());
        player.setFirstName(playerResource.getFirstName());
        player.setLastName(playerResource.getLastName());
        player.setDateOfBirth(playerResource.getDateOfBirth());
        player.setCountryOfBirth(playerResource.getCountryOfBirth());
        player.setNationality(playerResource.getNationality());
        player.setPosition(playerResource.getPosition());
        player.setShirtNumber(playerResource.getShirtNumber());
        player.setLastUpdated(playerResource.getLastUpdated());
        return player;
    }

    public List<Player> toEntityList(List<PlayerResource> playerResourceList) {

        List<Player> players = new ArrayList<>();

        for (PlayerResource playerResource : playerResourceList) {
            players.add(toEntity(playerResource));
        }
        return players;
    }

    public PlayerResource toResource(Player player) {

        PlayerResource playerResource = new PlayerResource();

        playerResource.setName(player.getName());
        playerResource.setFirstName(player.getFirstName());
        playerResource.setLastName(player.getLastName());
        playerResource.setDateOfBirth(player.getDateOfBirth());
        playerResource.setCountryOfBirth(player.getCountryOfBirth());
        playerResource.setNationality(player.getNationality());
        playerResource.setPosition(player.getPosition());
        playerResource.setShirtNumber(player.getShirtNumber());
        playerResource.setLastUpdated(player.getLastUpdated());
        return playerResource;
    }

    public List<PlayerResource> toResourceList(List<Player> players) {

        List<PlayerResource> playerResources = new ArrayList<>();

        for (Player player : players) {
            playerResources.add(toResource(player));
        }
        return playerResources;
    }
}
