package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "scores")
@Data
public class Score extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "winner")
    private String winner;

    @Column(name = "duration")
    private String duration;

    @OneToOne(cascade = CascadeType.ALL)
    private ScoreState fullTime;

    @OneToOne(cascade = CascadeType.ALL)
    private ScoreState halfTime;

    @OneToOne(cascade = CascadeType.ALL)
    private ScoreState extraTime;

    @OneToOne(cascade = CascadeType.ALL)
    private ScoreState penalties;
}
