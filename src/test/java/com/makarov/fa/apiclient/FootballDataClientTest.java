package com.makarov.fa.apiclient;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FootballDataClientTest {

    @Mock
    private FootballDataClient footballDataClient;

    private List<TeamResource> teams = new ArrayList<>();

    @Before
    public void init() {

    }

    @Test
    public void testGetAllTeamIds() {
        TeamResource team1 = new TeamResource();
        team1.setId(1L);
        teams.add(team1);

        List<Long> allTeamIds = footballDataClient.getAllTeamIds(teams);

        assertNotNull(allTeamIds);
    }
}