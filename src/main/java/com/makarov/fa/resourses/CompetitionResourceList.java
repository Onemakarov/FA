package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
public class CompetitionResourceList {

    @Getter
    @JsonProperty
    @JsonAlias(value = "competitions")
    private List<CompetitionResource> competitionResourceList;
}
