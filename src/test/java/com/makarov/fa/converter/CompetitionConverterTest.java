package com.makarov.fa.converter;

import com.makarov.fa.entity.Competition;
import com.makarov.fa.resourses.CompetitionResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class CompetitionConverterTest {

    private CompetitionConverter competitionConverter;

    private AreaConverter areaConverter;

    private SeasonConverter seasonConverter;

    private TeamConverter teamConverter;

    private PlayerConverter playerConverter;

    @Before
    public void init() {
//        areaConverter = new AreaConverter();
//        playerConverter = new PlayerConverter();
//        teamConverter = new TeamConverter(areaConverter, playerConverter);
//        seasonConverter = new SeasonConverter(teamConverter);
//        competitionConverter = new CompetitionConverter(areaConverter, seasonConverter);
    }

    @Test
    public void testToEntityReturnNotNull() {
        CompetitionResource competitionResource = new CompetitionResource();

        Competition returnedCompetition = competitionConverter.toEntity(competitionResource);

        assertNotNull(returnedCompetition);
    }
}