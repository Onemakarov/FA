package com.makarov.fa.rest;

import com.makarov.fa.converter.TeamConverter;
import com.makarov.fa.entity.Team;
import com.makarov.fa.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {

    private final TeamService teamService;

    private final TeamConverter teamConverter;

    public TeamController(TeamService teamService, TeamConverter teamConverter) {
        this.teamService = teamService;
        this.teamConverter = teamConverter;
    }

    @GetMapping("teams")
    public ResponseEntity<ApiResponse> getAllTeams() {

        List<Team> teams = teamService.getAllTeams();

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", teamConverter.toResourceList(teams));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @GetMapping("competitions/{competitionId}/teams")
    public ResponseEntity<ApiResponse> getTeamsByCompetitionId(@PathVariable("competitionId") long competitionId) {

        List<Team> teams = teamService.getTeamsByCompetitionId(competitionId);

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", teamConverter.toResourceList(teams));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }
}
