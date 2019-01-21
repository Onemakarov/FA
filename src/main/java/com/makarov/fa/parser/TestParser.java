package com.makarov.fa.parser;

import com.makarov.fa.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestParser {

    private CompetitionService competitionService;

    @Autowired
    public TestParser(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @Scheduled(fixedRate = 50000)
    public void test() {
        competitionService.addAllCompetitions();
    }
}
