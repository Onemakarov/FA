package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ScoreState {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int homeTeam;

    private int awayTeam;
}
