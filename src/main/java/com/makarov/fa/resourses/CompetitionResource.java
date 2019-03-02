package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@Slf4j
public class CompetitionResource {

    private Long Id;

    private AreaResource area;

    private String name;

    private String code;

    private String emblemUrl;

    private String plan;

    @JsonProperty(value = "seasons")
    private List<SeasonResource> seasons;
}

