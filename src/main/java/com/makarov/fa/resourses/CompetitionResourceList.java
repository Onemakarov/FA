package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CompetitionResourceList {

    @JsonProperty
    @JsonAlias(value = "competitions")
    private List<CompetitionResource> competitionResourceList;
}
