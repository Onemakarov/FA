package com.makarov.fa.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class SeasonList {

    //@JsonProperty
    //@JsonAlias(value = "seasons")
    private List<Season> seasonList;

    public List<Season> getSeasonList() {
        return seasonList;
    }
}
