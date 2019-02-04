package com.makarov.fa.resourses;

import com.makarov.fa.entity.ScoreState;
import lombok.Data;
import org.springframework.stereotype.Component;

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
