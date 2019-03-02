package com.makarov.fa.resourses;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Data
public class MatchResource {

    private long id;

    private SeasonResource season;

    private Date utcDate;

    private String status;

    private int matchDay;

    private String stage;

    private String group;

    private Date lastUpdated;

    private ScoreResource score;
}