package com.makarov.fa.initializationservice;

import com.makarov.fa.service.AreaService;
import com.makarov.fa.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class InitializationService implements ApplicationListener<ApplicationReadyEvent> {

    private final AreaService areaService;

    private final CompetitionService competitionService;

    @Autowired
    public InitializationService(AreaService areaService, CompetitionService competitionService) {
        this.areaService = areaService;
        this.competitionService = competitionService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        areaService.addAllAreas();
        competitionService.addAllCompetitions();

    }
}
