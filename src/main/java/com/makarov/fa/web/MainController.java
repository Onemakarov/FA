package com.makarov.fa.web;

import com.makarov.fa.entity.Match;
import com.makarov.fa.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    private final MatchService matchService;

    public MainController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public ModelAndView index() {

        List<Match> matches = matchService.getAllMatches(10);

        for (long i = 0; i <= 3; i++) {
            Match match = new Match();
            match.setId(i);
            matches.add(match);
        }

        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        model.addObject("matches", matches);
        return model;
    }
}
