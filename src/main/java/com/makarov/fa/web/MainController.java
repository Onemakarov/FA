package com.makarov.fa.web;

import com.makarov.fa.converter.CompetitionConverter;
import com.makarov.fa.converter.MatchConverter;
import com.makarov.fa.entity.*;
import com.makarov.fa.service.CompetitionService;
import com.makarov.fa.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final MatchService matchService;

    private final MatchConverter matchConverter;

    private final CompetitionService competitionService;

    private final CompetitionConverter competitionConverter;

    public MainController(MatchService matchService, MatchConverter matchConverter, CompetitionService competitionService, CompetitionConverter competitionConverter) {
        this.matchService = matchService;
        this.matchConverter = matchConverter;
        this.competitionService = competitionService;
        this.competitionConverter = competitionConverter;
    }

    @GetMapping("/")
    public ModelAndView index() {

        List<Match> matches = matchService.getAllMatches();
        List<Competition> competitions = competitionService.getAllCompetitions();

        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("matches", matchConverter.toResourceList(matches));
        model.addObject("competitions", competitionConverter.toResourceList(competitions));
        return model;
    }

    @GetMapping("/matches/{matchId}")
    public ModelAndView getMatchById(@PathVariable("matchId") long matchId) {

        Match match = matchService.getMatchById(matchId);

        ModelAndView model = new ModelAndView();
        model.setViewName("match");
        model.addObject("match", matchConverter.toResource(match));
        return model;
    }
}
