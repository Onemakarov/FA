package com.makarov.fa.web;

import com.makarov.fa.converter.MatchConverter;
import com.makarov.fa.entity.Match;
import com.makarov.fa.entity.Score;
import com.makarov.fa.entity.ScoreState;
import com.makarov.fa.entity.Team;
import com.makarov.fa.resourses.TeamResource;
import com.makarov.fa.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@Controller
public class MainController {

    private final MatchService matchService;

    private final MatchConverter matchConverter;

    public MainController(MatchService matchService, MatchConverter matchConverter) {
        this.matchService = matchService;
        this.matchConverter = matchConverter;
    }

    @GetMapping("/")
    public ModelAndView index() {

        List<Match> matches = matchService.getAllMatches(10);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("matches", matchConverter.toResourceList(matches));
        return model;
    }
}
