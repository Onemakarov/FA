package com.makarov.fa.entity;

import javax.persistence.*;

@Entity(name = "scores")
public class Score extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "winner")
    private String winner;

    @Column(name = "duration")
    private String duration;

    @Column(name = "full_time")
    private String fullTime;

    @Column(name = "half_time")
    private String halfTime;

    @Column(name = "extra_time")
    private String extraTime;

    @Column(name = "penalties")
    private String penalties;

}
