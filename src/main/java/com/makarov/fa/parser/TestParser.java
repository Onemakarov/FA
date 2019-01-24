package com.makarov.fa.parser;

import com.makarov.fa.service.AreaService;
import com.makarov.fa.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestParser {

    private CompetitionService competitionService;

    private AreaService areaService;

    @Autowired
    public TestParser(CompetitionService competitionService, AreaService areaService) {
        this.competitionService = competitionService;
        this.areaService = areaService;
    }

    //@Scheduled(fixedRate = 5000000)
    //public void test() {
        //areaService.addAllAreas();
        //competitionService.addAllCompetitions();
        //System.out.println(competitionService.getCompetitionById(2021L));
    //}
}
