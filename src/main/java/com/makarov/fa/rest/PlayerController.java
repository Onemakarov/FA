package com.makarov.fa.rest;

import com.makarov.fa.converter.PlayerConverter;
import com.makarov.fa.entity.Player;
import com.makarov.fa.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    private final PlayerConverter playerConverter;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerConverter playerConverter) {
        this.playerService = playerService;
        this.playerConverter = playerConverter;
    }

    @GetMapping("players")
    public ResponseEntity<ApiResponse> getAllPlayers() {

        List<Player> players = playerService.getAllPlayers();

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", playerConverter.toResourceList(players));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @GetMapping("players/{id}")
    public ResponseEntity<ApiResponse> getPlayer(@PathVariable("id") long playerId) {

        Player player = playerService.getPlayerById(playerId);

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", playerConverter.toResource(player));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @GetMapping("teams/{teamId}/players")
    public ResponseEntity<ApiResponse> getPlayersByTeamId(@PathVariable("teamId") long teamId) {

        List<Player> players = playerService.getPlayersByTeamId(teamId);

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", playerConverter.toResourceList(players));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

}
