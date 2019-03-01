package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.makarov.fa.entity.Area;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Data
public class TeamResource {


    private Long id;

    private AreaResource area;

    private List<CompetitionResource> activeCompetitions;

    private String name;

    private String shortName;

    private String tla;

    private String crestUrl;

    private String address;

    private String phone;

    private String website;

    private String email;

    private int founded;

    private String clubColors;

    private String venue;

    @JsonAlias(value = "squad")
    private List<PlayerResource> squad;

    private Date lastUpdated;

}
