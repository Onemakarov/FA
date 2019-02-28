package com.makarov.fa.apiclient;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.makarov.fa.apiclient.FootballDataPathValues.*;
import static java.util.stream.Collectors.*;

@Component
@Log4j2
public class FootballDataClient {

    private final String footballDataUrl;

    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final List<Long> competitionsId = Arrays.asList(2000L, 2001L ,2002L, 2003L, 2013L, 2014L, 2015L,2016L, 2017L,2018L, 2019L, 2018L, 2021L);//

    @Autowired
    public FootballDataClient(@Value("${footballDataUrl}") String footballDataUrl, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.footballDataUrl = footballDataUrl;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<CompetitionResource> getAllCompetitions(){

        List<CompetitionResource> competitionResources = new ArrayList<>();

        int count = 1;

        for (Long competitionId : competitionsId) {
//            if (count % 8 == 0) {
//                waitOneMinute();
//            }
            competitionResources.add(getCompetitionById(competitionId));
            count++;
        }
        log.info("got all competitions");

        return  competitionResources;
    }

    public <T> ResponseEntity<T> getResponseEntity(Class<T> tClass, String url) {

        while (true) {
            try {
                ResponseEntity<String> responseEntity = restTemplate
                        .exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), String.class);
                JavaType javaType = objectMapper.constructType(tClass);
                return objectMapper.readValue(responseEntity.getBody(), javaType);
            }catch (HttpClientErrorException.TooManyRequests errorException){
                log.error(errorException);
                waitOneMinute();
            }catch (IOException e) {
                log.error(e);
            }
        }
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

        log.info("getting competition id: " + id);
        CompetitionResource competition = getResponseEntity(CompetitionResource.class, url).getBody();
        log.info("get competition: id = " + id);

        return competition;
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

        log.info("getting players from team id = " + teamId);
        List<PlayerResource> players = getResponseEntity(PlayerResourceList.class, url).getBody().getPlayerResourceList();
        log.info("got players from team id = " + teamId);

        return players;
    }

    public List<PlayerResource> getAllPlayerResources(List<Team> teams) {

        Set<PlayerResource> playerResources = new HashSet<>();

        log.info("getting player resources from teams count: " + teams.size());
        for (Team team : teams) {
            playerResources.addAll(getPlayerResourcesByTeamId(team.getId()));
        }
        log.info("got all players");

        return (List<PlayerResource>) playerResources;
    }

    private HttpEntity<String> getHttpEntityWithAuthToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", "88b92c5a081d4412ba4eae1db4741c56");
        return new HttpEntity<String>("parameters", headers);
    }

    public List<TeamResource> getAllTeams() {


        return teamResources;
    }

    public TeamResource getTeamByCompetitionId(Long competitionId) {

        List<TeamResource> teamResources = new ArrayList<>();

        for (Long competitionId : competitionsId) {

            String url = footballDataUrl + "competitions/" + competitionId + TEAM.getPath();


            teamResources.addAll(getResponseEntity(TeamResource.class, url).getBody();
            log.info("get all teams from competition id = " + competitionId);
        }
        log.info("got all teams from competitions ids = " + competitionsId);

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
