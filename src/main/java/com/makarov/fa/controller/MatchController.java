package com.makarov.fa.controller;

import com.makarov.fa.converter.MatchConverter;
import com.makarov.fa.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("matches")
public class MatchController {

    private final MatchService matchService;

    private final MatchConverter matchConverter;

    public MatchController(MatchService matchService, MatchConverter matchConverter) {
        this.matchService = matchService;
        this.matchConverter = matchConverter;
    }
}
