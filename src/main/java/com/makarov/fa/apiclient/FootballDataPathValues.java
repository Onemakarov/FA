package com.makarov.fa.apiclient;

import lombok.Getter;

public enum FootballDataPathValues {

    COMPETITION("/competitions/"), MATCH("/matches/"), TEAM("/teams/");

    @Getter
    private String path;

    FootballDataPathValues(String path) {
        this.path = path;
    }
}
