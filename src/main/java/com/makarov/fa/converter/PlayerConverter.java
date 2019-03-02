package com.makarov.fa.converter;

import com.makarov.fa.entity.Player;
import com.makarov.fa.resourses.PlayerResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlayerConverter {

    public Player toEntity(PlayerResource playerResource) {

        Player playerEntity = new Player();

        playerEntity.setName(playerResource.getName());
        playerEntity.setFirstName(playerResource.getFirstName());
        playerEntity.setLastName(playerResource.getLastName());
        playerEntity.setDateOfBirth(playerResource.getDateOfBirth());
        playerEntity.setCountryOfBirth(playerResource.getCountryOfBirth());
        playerEntity.setNationality(playerResource.getNationality());
        playerEntity.setPosition(playerResource.getPosition());
        playerEntity.setShirtNumber(playerResource.getShirtNumber());
        playerEntity.setLastUpdated(playerResource.getLastUpdated());
        return playerEntity;
    }

    public List<Player> toEntityList(List<PlayerResource> playerResourceList) {

        List<Player> playersEntityList = new ArrayList<>();

        for (PlayerResource playerResource : playerResourceList) {
            playersEntityList.add(toEntity(playerResource));
        }
        return playersEntityList;
    }
}
