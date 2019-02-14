package com.makarov.fa.apiclient;

import com.makarov.fa.resourses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.makarov.fa.apiclient.FootballDataPathValues.*;

@Component
public class FootballDataClient {

    private final String footballDataUrl;

    private RestTemplate restTemplate;

    private final List<Long> competitionsId = Arrays.asList(2000L, 2001L ,2002L, 2003L, 2013L, 2014L, 2015L, 2016L, 2017L, 2018L, 2019L, 2021L);

    @Autowired
    public FootballDataClient(@Value("${footballDataUrl}") String footballDataUrl, RestTemplate restTemplate) {
        this.footballDataUrl = footballDataUrl;
        this.restTemplate = restTemplate;
    }

    public List<CompetitionResource> getAllCompetitions(){

        List<CompetitionResource> competitionResources = new ArrayList<>();

        for (Long competitionId : competitionsId) {
            competitionResources.add(getCompetitionById(competitionId));
        }
        return  competitionResources;
    }

    public CompetitionResource getCompetitionById(Long id) {

        String url = footballDataUrl + "/competitions/" + id;

        ResponseEntity<CompetitionResource> competition = restTemplate
                .exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), CompetitionResource.class);

        return competition.getBody();
    }

    public AreaResourceList getAreaList(List<CompetitionResource> competitionResources) {

        List<AreaResource> areaArrayList = new ArrayList<>();
        AreaResourceList areaList = new AreaResourceList();

        for (CompetitionResource competitionResource : competitionResources) {
            if (!areaArrayList.contains(competitionResource.getArea())) {
                areaArrayList.add(competitionResource.getArea());
            }
        }
        areaList.setAreaResourceList(areaArrayList);
        return areaList;
    }

    public List<SeasonResource> getSeasons(List<CompetitionResource> competitionResources) {

        List<SeasonResource> seasons = new ArrayList<>();
        for (CompetitionResource competition : competitionResources) {
            seasons.addAll(competition.getSeasons());
        }
        return seasons;
    }

    private HttpEntity<String> getHttpEntityWithAuthToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "88b92c5a081d4412ba4eae1db4741c56");
        return new HttpEntity<String>("parameters", headers);
    }

    public List<TeamResource> getAllTeams() {

        List<TeamResource> teamResources = new ArrayList<>();

        for (Long competitionId : competitionsId) {

            String url = footballDataUrl + "competitions/" + competitionId + TEAM.getPath();

            ResponseEntity<TeamResourceList> listResponseEntity = restTemplate
                    .exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), TeamResourceList.class);

            teamResources.addAll(listResponseEntity.getBody().getTeamResourceList());
        }

        return teamResources;
    }

    public List<MatchResource> getAllMatches() {

        List<MatchResource> matchResources = new ArrayList<>();

        for (Long competitionId : competitionsId) {

            String url = footballDataUrl + "competitions/" + competitionId + MATCH.getPath();

            ResponseEntity<MatchResourceList> listResponseEntity = restTemplate
                    .exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), MatchResourceList.class);

            matchResources.addAll(listResponseEntity.getBody().getMatchResourceList());
        }

        return matchResources;
    }
}
