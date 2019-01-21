package com.makarov.fa.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
public class SeasonList {

    @Getter
    @JsonProperty
    @JsonAlias(value = "seasons")
    private List<Season> seasonList;

    public List<Season> getSeasonList() {
        return seasonList;
    }
}
