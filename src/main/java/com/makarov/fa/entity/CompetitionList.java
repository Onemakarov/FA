package com.makarov.fa.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import java.util.List;

@ToString
public class CompetitionList {

    @Getter
    @JsonProperty
    @JsonAlias(value = "competitions")
    private List<Competition> competitionList;

    public List<Competition> getCompetitionList() {
        return competitionList;
    }
}
