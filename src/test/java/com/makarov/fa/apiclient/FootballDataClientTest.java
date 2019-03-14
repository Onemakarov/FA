package com.makarov.fa.apiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makarov.fa.converter.*;
import com.makarov.fa.entity.Area;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.entity.Team;
import com.makarov.fa.resourses.TeamResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

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
        List<Team> teams = generateTeamList();

        List<Area> areas = footballDataClient.getAllAreas(teams, competitions);

        assertNotNull(areas);
    }

    @Test
    public void testGetAllAreasReturnDistinct() {
        List<Competition> competitions = generateCompetitionsList();
        List<Team> teams = generateTeamList();
        List<Long> expectedAreaIds = Arrays.asList(1L, 2L, 3L);

        List<Long> actualAreaIds = footballDataClient.getAllAreas(teams, competitions)
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

    private List<Team> generateTeamList() {
        List<Team> teams = new ArrayList<>();
        List<Long> teamIds = Arrays.asList(1L, 2L, 3L, 3L);

        for (Long teamId : teamIds) {
            Team team = new Team();
            team.setId(teamId);
            teams.add(team);
        }
        return teams;
    }
}
