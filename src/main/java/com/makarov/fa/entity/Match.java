package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "matches")
@Data
public class Match extends AuditEntity {

    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "competition")
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "season")
    private Season season;

    @Temporal(TemporalType.DATE)
    @Column(name = "utcDate")
    private Date utcDate;

    @Column(name = "status")
    private String status;

    @Column(name = "matchday")
    private int matchDay;

    @Column(name = "stage")
    private String stage;

    @Column(name = "group")
    private String group;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "score")
    private Score score;




}
