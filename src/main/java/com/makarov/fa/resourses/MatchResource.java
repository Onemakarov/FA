package com.makarov.fa.resourses;

import com.makarov.fa.entity.Team;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Data
public class MatchResource implements Resource {

    private long id;

    private CompetitionResource competition;

    private SeasonResource season;

    private Date utcDate;

    private String status;

    private int matchDay;

    private String stage;

    private String group;

    private Date lastUpdated;

    private ScoreResource score;

    private TeamResource homeTeam;

    private TeamResource awayTeam;
}