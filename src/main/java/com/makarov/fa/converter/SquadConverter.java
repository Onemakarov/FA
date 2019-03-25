package com.makarov.fa.converter;

import com.makarov.fa.entity.Squad;
import com.makarov.fa.resourses.SquadResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SquadConverter {

    private final PlayerConverter playerConverter;

    public SquadConverter(PlayerConverter playerConverter) {
        this.playerConverter = playerConverter;
    }

    public Squad toEntity(SquadResource squadResource) {

        Squad squad = new Squad();

        squad.setPlayers(playerConverter.toEntityList(squadResource.getPlayerResourceList()));
        return squad;
    }

    public List<Squad> toEntityList(List<SquadResource> squadResources) {

        List<Squad> squads = new ArrayList<>();

        for (SquadResource squadResource : squadResources) {
            squads.add(toEntity(squadResource));
        }
        return squads;
    }

    public SquadResource toResource(Squad squad) {

        SquadResource squadResource = new SquadResource();

        squadResource.setPlayerResourceList(playerConverter.toResourceList(squad.getPlayers()));
        return squadResource;
    }

    public List<SquadResource> toResourceList(List<Squad> squads) {

        List<SquadResource> squadResources = new ArrayList<>();

        for (Squad squad : squads) {
            squadResources.add(toResource(squad));
        }
        return squadResources;
    }
}
