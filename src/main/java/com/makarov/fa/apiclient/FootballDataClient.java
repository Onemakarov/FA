package com.makarov.fa.apiclient;

import com.makarov.fa.entity.Competition;
import com.makarov.fa.entity.CompetitionList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FootballDataClient {

    private final String footballDataUrl;

    private RestTemplate restTemplate;

    @Autowired
    public FootballDataClient(@Value("${footballDataUrl}") String footballDataUrl, RestTemplate restTemplate) {
        this.footballDataUrl = footballDataUrl;
        this.restTemplate = restTemplate;
    }

    public List<Competition> getAllCompetitions(){

        String url = "/competitions";

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "88b92c5a081d4412ba4eae1db4741c56");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<CompetitionList> competitionList = restTemplate
                .exchange(footballDataUrl + url, HttpMethod.GET, entity, CompetitionList.class);

        return competitionList.getBody().getCompetitionList();
    }

    public Competition getCompetitionById(String id) {

        String url = "/competitions/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "88b92c5a081d4412ba4eae1db4741c56");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<Competition> competition = restTemplate
                .exchange(footballDataUrl + url, HttpMethod.GET, entity, Competition.class);

        return competition.getBody();
    }
}
