package com.makarov.fa.resourses;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class SeasonResource {

    private Long id;

    private Date startDate;

    private Date endDate;

    private TeamResource winner;
}
