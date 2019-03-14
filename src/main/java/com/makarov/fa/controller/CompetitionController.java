package com.makarov.fa.controller;

import com.makarov.fa.converter.CompetitionConverter;
import com.makarov.fa.resourses.CompetitionResource;
import com.makarov.fa.service.CompetitionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    private final CompetitionConverter competitionConverter;

    public CompetitionController(CompetitionService competitionService, CompetitionConverter competitionConverter) {
        this.competitionService = competitionService;
        this.competitionConverter = competitionConverter;
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResource>> getAllCompetitions() {

        List<CompetitionResource> competitionResources = competitionConverter.
                toResourceList(competitionService.getAllCompetitions());

        return new ResponseEntity<List<CompetitionResource>>(competitionResources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionResource> getCompetition(@PathVariable("id") Long competitionId) {

        CompetitionResource competitionResource = competitionConverter.
                toResource(competitionService.getCompetitionById(competitionId));

        return new ResponseEntity<CompetitionResource>(competitionResource, HttpStatus.OK);
    }
}
