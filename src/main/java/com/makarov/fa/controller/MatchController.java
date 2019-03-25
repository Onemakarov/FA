package com.makarov.fa.controller;

import com.makarov.fa.converter.MatchConverter;
import com.makarov.fa.resourses.MatchResource;
import com.makarov.fa.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("matches")
public class MatchController {

    private final MatchService matchService;

    private final MatchConverter matchConverter;

    public MatchController(MatchService matchService, MatchConverter matchConverter) {
        this.matchService = matchService;
        this.matchConverter = matchConverter;
    }

    @GetMapping
    public ResponseEntity<List<MatchResource>> getAllMatches() {

        List<MatchResource> matchResources = matchConverter
                .toResourceList(matchService.getAllMatches());

        return new ResponseEntity<List<MatchResource>>(matchResources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResource> getMatch(@PathVariable("id") Long matchId) {

        MatchResource matchResource = matchConverter.toResource(matchService.getMatchById(matchId));

        return new ResponseEntity<MatchResource>(matchResource, HttpStatus.OK);
    }
}
