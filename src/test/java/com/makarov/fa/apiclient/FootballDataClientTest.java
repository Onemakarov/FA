package com.makarov.fa.apiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makarov.fa.converter.*;
import com.makarov.fa.entity.Area;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.resourses.TeamResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FootballDataClientTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private CompetitionConverter competitionConverter;

    @Mock
    private AreaConverter areaConverter;

    @Mock
    private MatchConverter matchConverter;

    @Mock
    private PlayerConverter playerConverter;

    @Mock
    private TeamConverter teamConverter;

    private FootballDataClient footballDataClient;

    @Before
    public void init() {
        footballDataClient = new FootballDataClient("", "", restTemplate, objectMapper, areaConverter, competitionConverter, matchConverter, playerConverter, teamConverter);
    }

    @Test
    public void testGetAllTeamIdsReturnNotNull() {
        List<TeamResource> teams = generateTeamResourcesList();

        List<Long> allTeamIds = footballDataClient.getAllTeamIds(teams);

        assertNotNull(allTeamIds);
    }

    @Test
    public void testGetAllTeamIdsReturnDistinct() {
        List<TeamResource> teams = generateTeamResourcesList();
        List<Long> expectedTeamIds = teams
                .stream()
                .map(TeamResource::getId)
                .distinct()
                .collect(toList());

        List<Long> actualTeamIds = footballDataClient.getAllTeamIds(teams);

        assertArrayEquals(expectedTeamIds.toArray(), actualTeamIds.toArray());
    }

    @Test
    public void testGetAllAreasReturnNotNull() {
        List<Competition> competitions = generateCompetitionsList();

        List<Area> areas = footballDataClient.getAllAreas(competitions);

        assertNotNull(areas);
    }

    @Test
    public void testGetAllAreasReturnDistinct() {
        List<Competition> competitions = generateCompetitionsList();
        List<Long> expectedAreaIds = Arrays.asList(1L, 2L, 3L);

        List<Long> actualAreaIds = footballDataClient.getAllAreas(competitions)
                .stream()
                .map(Area::getId)
                .distinct()
                .collect(toList());

        assertArrayEquals(expectedAreaIds.toArray(), actualAreaIds.toArray());
    }

    private List<Competition> generateCompetitionsList() {
        List<Competition> competitions = new ArrayList<>();
        List<Area> areas = generateAreasList();

        for (Area area : areas) {
            Competition competition = new Competition();
            competition.setArea(area);
            competitions.add(competition);
        }
        return competitions;
    }

    private List<Area> generateAreasList() {
        List<Area> areas = new ArrayList<>();
        List<Long> areasIds = Arrays.asList(1L, 2L, 3L, 3L);

        for (Long areaId : areasIds) {
            Area area = new Area();
            area.setId(areaId);
            areas.add(area);
        }
        return areas;
    }

    private List<TeamResource> generateTeamResourcesList() {
        List<TeamResource> teamResources = new ArrayList<>();
        List<Long> teamIds = Arrays.asList(1L, 2L, 3L, 3L);

        for (Long teamId : teamIds) {
            TeamResource team = new TeamResource();
            team.setId(teamId);
            teamResources.add(team);
        }
        return teamResources;
    }
}