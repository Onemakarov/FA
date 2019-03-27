package com.makarov.fa.rest;

import com.makarov.fa.converter.CompetitionConverter;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CompetitionController {

    private final CompetitionService competitionService;

    private final CompetitionConverter competitionConverter;

    @Autowired
    public CompetitionController(CompetitionService competitionService, CompetitionConverter competitionConverter) {
        this.competitionService = competitionService;
        this.competitionConverter = competitionConverter;
    }

    @GetMapping("competitions")
    public ResponseEntity<ApiResponse> getAllCompetitions() {

        List<Competition> competitions = competitionService.getAllCompetitions();

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", competitionConverter.toResourceList(competitions));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }

    @GetMapping("competitions/{id}")
    public ResponseEntity<ApiResponse> getCompetition(@PathVariable("id") Long competitionId) {

        Competition competition = competitionService.getCompetitionById(competitionId);

        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK, "OK", competitionConverter.toResource(competition));

        return new ResponseEntity<>(apiResponse, apiResponse.getStatusCode());
    }
}
