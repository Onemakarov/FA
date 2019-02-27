package com.makarov.fa.apiclient;

import com.makarov.fa.entity.Area;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.entity.Team;
import com.makarov.fa.resourses.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.makarov.fa.apiclient.FootballDataPathValues.*;
import static java.util.stream.Collectors.*;

@Component
@Log4j2
public class FootballDataClient {

    private final String footballDataUrl;

    private RestTemplate restTemplate;

    private final List<Long> competitionsId = Arrays.asList(2016L, 2021L);//2000L, 2001L ,2002L, 2003L, 2013L, 2014L, 2015L, 2017L,2018L, 2019L,

    @Autowired
    public FootballDataClient(@Value("${footballDataUrl}") String footballDataUrl, RestTemplate restTemplate) {
        this.footballDataUrl = footballDataUrl;
        this.restTemplate = restTemplate;
    }

    public List<CompetitionResource> getAllCompetitions(){

        List<CompetitionResource> competitionResources = new ArrayList<>();

        int count = 1;

        for (Long competitionId : competitionsId) {
            if (count % 8 == 0) {
                waitOneMinute();
            }
            competitionResources.add(getCompetitionById(competitionId));
            count++;
        }
        log.info("got all competitions");

        return  competitionResources;
    }

    private void waitOneMinute() {
        log.info("waiting");
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public CompetitionResource getCompetitionById(Long id) {

        String url = footballDataUrl + "/competitions/" + id;

        ResponseEntity<CompetitionResource> competition = restTemplate
                .exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), CompetitionResource.class);
        log.info("get competition: id = " + id);

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
            log.info("got season from competition: comp id = " + competition.getId());
        }
        log.info("got all seasons");
        return seasons;
    }

    public List<PlayerResource> getPlayerResourcesByTeamId(Long teamId) {

        String url = footballDataUrl + TEAM.getPath() + teamId;

        ResponseEntity<PlayerResourceList> playerResourceResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), PlayerResourceList.class);
        log.info("got players from team id = " + teamId);

        return playerResourceResponseEntity.getBody().getPlayerResourceList();

    }

    public List<PlayerResource> getAllPlayerResources(List<Team> teams) {

        List<PlayerResource> playerResourceList = new ArrayList<>();

        log.info("getting player resources from teams count: " + teams.size());

        int count = 1;
        for (Team team : teams) {
            if (count % 8 == 0) {waitOneMinute();}
            playerResourceList.addAll(getPlayerResourcesByTeamId(team.getId()));
            count++;
        }
        log.info("got all players");

        return playerResourceList
                .stream()
                .distinct()
                .collect(toList());
    }

    private HttpEntity<String> getHttpEntityWithAuthToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "88b92c5a081d4412ba4eae1db4741c56");
        return new HttpEntity<String>("parameters", headers);
    }

    public List<TeamResource> getAllTeams() {

        List<TeamResource> teamResources = new ArrayList<>();

        int count = 0;

        for (Long competitionId : competitionsId) {

            if (count % 8 == 0) {waitOneMinute();}

            String url = footballDataUrl + "competitions/" + competitionId + TEAM.getPath();

            ResponseEntity<TeamResourceList> listResponseEntity = restTemplate
                    .exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), TeamResourceList.class);

            teamResources.addAll(listResponseEntity.getBody().getTeamResourceList());
            log.info("get all teams from competition id = " + competitionId);
            count++;
        }
        log.info("got all teams from competitions ids = " + competitionsId);
        waitOneMinute();

        return teamResources;
    }

    public List<MatchResource> getAllMatches() {

        List<MatchResource> matchResources = new ArrayList<>();

        int count = 0;

        for (Long competitionId : competitionsId) {

            if (count % 8 == 0) {waitOneMinute();}

            String url = footballDataUrl + "competitions/" + competitionId + MATCH.getPath();

            ResponseEntity<MatchResourceList> listResponseEntity = restTemplate
                    .exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), MatchResourceList.class);

            matchResources.addAll(listResponseEntity.getBody().getMatchResourceList());
            log.info("got matches from competition id =" + competitionId);
            count++;
        }
        log.info("got all matches");

        return matchResources;
    }

    public List<Area> getAllAreas(List<Competition> competitions) {
        List<Long> areaIdList = new ArrayList<>();
        List<Area> areas = new ArrayList<>();

        for (Competition competition : competitions) {
            if (!areaIdList.contains(competition.getArea().getId())) {
                areaIdList.add(competition.getArea().getId());
                areas.add(competition.getArea());
            }
        }
        return areas;
    }
}
