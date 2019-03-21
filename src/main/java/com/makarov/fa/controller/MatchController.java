package com.makarov.fa.controller;

import com.makarov.fa.converter.MatchConverter;
import com.makarov.fa.entity.Match;
import com.makarov.fa.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.LinkOption;
import java.util.List;

@RestController
public class MatchController {

    private final MatchService matchService;

    private final MatchConverter matchConverter;

    @Autowired
    public MatchController(MatchService matchService, MatchConverter matchConverter) {
        this.matchService = matchService;
        this.matchConverter = matchConverter;
    }

    @GetMapping("matches")
    public ResponseEntity<ApiResponse> getAllMatches() {

        List<Match> matches = matchService.getAllMatches();

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", matchConverter.toResourceList(matches));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @GetMapping("matches/{id}")
    public ResponseEntity<ApiResponse> getMatch(@PathVariable("id") Long matchId) {

        Match match = matchService.getMatchById(matchId);

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", matchConverter.toResource(match));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @GetMapping("seasons/{id}/matches")
    public ResponseEntity<ApiResponse> getMatchesBySeasonId(@PathVariable("id") long seasonId) {

        List<Match> matches = matchService.getMatchesBySeasonId(seasonId);

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", matchConverter.toResourceList(matches));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }
}
