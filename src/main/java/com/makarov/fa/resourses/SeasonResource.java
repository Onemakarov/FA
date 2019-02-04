package com.makarov.fa.resourses;

import com.makarov.fa.entity.Team;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Component
@Data
public class SeasonResource {

    private Long id;

    private Date startDate;

    private Date endDate;

    private TeamResource winner;
}
