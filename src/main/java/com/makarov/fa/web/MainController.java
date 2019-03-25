package com.makarov.fa.web;

import com.makarov.fa.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    private final MatchService matchService;

    public MainController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView model = new ModelAndView();
        model.setViewName("HelloWorld");
        return model;
    }
}
