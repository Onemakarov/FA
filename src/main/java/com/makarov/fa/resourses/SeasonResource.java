package com.makarov.fa.resourses;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class SeasonResource implements Resource {

    private Long competitionId;

    private Long id;

    private Date startDate;

    private Date endDate;

    private TeamResource winner;
}
