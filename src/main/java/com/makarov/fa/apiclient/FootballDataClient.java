package com.makarov.fa.apiclient;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.makarov.fa.converter.*;
import com.makarov.fa.entity.*;
import com.makarov.fa.resourses.*;
import lombok.extern.slf4j.Slf4j;
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

@Component
@Slf4j
public class FootballDataClient {

    private final String footballDataUrl;

    private final String authToken;

    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final List<Long> competitionsId = Arrays.asList(2021L);//2016L, 2018L,2000L, 2001L ,2002L, 2003L, 2013L, 2014L, 2015L,2019L, 2017L,

    private final CompetitionConverter competitionConverter;

    private final MatchConverter matchConverter;

    private final TeamConverter teamConverter;

    @Autowired
    public FootballDataClient(@Value("${footballDataUrl}") String footballDataUrl, @Value("${authToken}") String authToken, RestTemplate restTemplate, ObjectMapper objectMapper, AreaConverter areaConverter, CompetitionConverter competitionConverter, MatchConverter matchConverter, PlayerConverter playerConverter, TeamConverter teamConverter) {
        this.footballDataUrl = footballDataUrl;
        this.authToken = authToken;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.competitionConverter = competitionConverter;
        this.matchConverter = matchConverter;
        this.teamConverter = teamConverter;
    }

    private <T> T getResource(String url, Class<T> tClass) {

        while (true) {
            try {
                log.info("start response");
                ResponseEntity<String> responseEntity = restTemplate
                        .exchange(url, HttpMethod.GET, getHttpEntityWithAuthToken(), String.class);
                log.info("end response");
                JavaType javaType = objectMapper.constructType(tClass);
                return objectMapper.readValue(responseEntity.getBody(), javaType);
            } catch (HttpClientErrorException.TooManyRequests errorException){
                log.error(errorException.getMessage());
                Long secondToWait = Long.valueOf(errorException.getResponseHeaders().getFirst("X-RequestCounter-Reset"));
                waitSeconds(secondToWait + 2);
            } catch (IOException e) {
                log.error("got IO exception", e);
            }
        }
    }

    private void waitSeconds(Long seconds) {
        log.info("waiting for " + seconds);
        Thread timerThread = new Thread(() -> {
            for (long i = seconds; i >= 0; i--) {
                log.info("wating for " + i + " seconds");
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }});
        timerThread.setName("counter");
        timerThread.start();
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("stop waiting");
    }

    private HttpEntity<String> getHttpEntityWithAuthToken() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Auth-Token", authToken);
        return new HttpEntity<>("parameters", headers);
    }

    private CompetitionResource getCompetitionById(Long id) {

        String url = footballDataUrl + "/competitions/" + id;

        log.info("getting competition id: " + id);
        CompetitionResource competition = getResource(url, CompetitionResource.class);
        log.info("get competition: id = " + id);
        return competition;
    }

    public List<Competition> getAllCompetitions(){

        List<CompetitionResource> competitionResources = new ArrayList<>();

        log.info("getting all competitions: ids = " + competitionsId);
        for (Long competitionId : competitionsId) {
            competitionResources.add(getCompetitionById(competitionId));
        }
        log.info("got all competitions: ids = " + competitionsId);

        return competitionConverter.toEntityList(competitionResources);
    }

    private List<PlayerResource> getPlayerResourcesByTeamId(Long teamId) {

        String url = footballDataUrl + TEAM.getPath() + teamId;

        log.info("getting players from team id = " + teamId);
        List<PlayerResource> players = getResource(url, PlayerResourceList.class).getPlayerResourceList();
        log.info("got players from team id = " + teamId);
        return players;
    }

    public List<Player> getAllPlayerResourcesFromTeam(List<Team> teams) {

        List<Player> players = new ArrayList<>();

        log.info("getting player resources from teams count: " + teams.size());
        for (Team team : teams) {
            players.addAll(team.getSquad().getPlayers());
        }
        log.info("got all players");
        return players;
    }

    private List<TeamResource> getTeamsByCompetitionId(Long competitionId) {

        String url = footballDataUrl + "competitions/" + competitionId + TEAM.getPath();

        log.info("getting teams from competition: id = " + competitionId);
        TeamResourceList teams = getResource(url, TeamResourceList.class);
        log.info("got teams from competition: id = " + competitionId);
        return teams.getTeamResourceList();
    }

    private List<TeamResource> getAllTeamsFromCompetitions() {

        List<TeamResource> teamResources = new ArrayList<>();

        log.info("getting teams from competitions: ids = " + competitionsId);
        for (Long competitionId : competitionsId) {
            teamResources.addAll(getTeamsByCompetitionId(competitionId));
        }
        log.info("get all teams from competitions: ids = " + competitionsId);
        return teamResources;
    }

    public List<Long> getAllTeamIds(List<TeamResource> teamResources) {

        Set<Long> teamIds = new HashSet<>();

        for (TeamResource teamResource : teamResources) {
            teamIds.add(teamResource.getId());
        }
        return new ArrayList<>(teamIds);
    }

    private TeamResource getTeamById(Long teamId) {

        String url = footballDataUrl + TEAM.getPath() + teamId;
        return getResource(url, TeamResource.class);
    }

    public List<Team> getAllTeams() {

        List<TeamResource> teamResources = new ArrayList<>();
        List<Long> teamIds = getAllTeamIds(getAllTeamsFromCompetitions());
        log.info("getting teams: ids = " + teamIds);
        for (Long teamId : teamIds) {
            teamResources.add(getTeamById(teamId));
        }
        log.info("got teams: ids = " + teamIds);
        return teamConverter.toEntityList(teamResources);
    }

    public List<Match> getAllMatches() {

        List<MatchResource> matchResources = new ArrayList<>();

        log.info("getting all matches from competitions: ids = " + competitionsId);
        for (Long competitionId : competitionsId) {
            matchResources.addAll(getMatchesByCompetitionId(competitionId));
        }
        log.info("got all matches from competitions: ids = " + competitionsId);
        return matchConverter.toEntityList(matchResources);
    }

    private List<MatchResource> getMatchesByCompetitionId(Long competitionId) {

        String url = footballDataUrl + "competitions/" + competitionId + MATCH.getPath();

        log.info("getting matches from competition: id = " + competitionId);
        List<MatchResource> matchResourceList = getResource(url, MatchResourceList.class).getMatchResourceList();
        log.info("got matches from competition: id =" + competitionId);
        return matchResourceList;
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

    public List<SeasonResource> getAllSeasons(List<CompetitionResource> competitionResources) {

        List<SeasonResource> seasons = new ArrayList<>();
        for (CompetitionResource competition : competitionResources) {
            seasons.addAll(competition.getSeasons());
            log.info("got season from competition: comp id = " + competition.getId());
        }
        log.info("got all seasons");
        return seasons;
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

