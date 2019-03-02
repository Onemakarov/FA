package com.makarov.fa.resourses;

import lombok.Data;

@Data
public class ScoreResource {

    private Long id;

    private String winner;

    private String duration;

    private ScoreStateResource fullTime;

    private ScoreStateResource halfTime;

    private ScoreStateResource extraTime;

    private ScoreStateResource penalties;
}
