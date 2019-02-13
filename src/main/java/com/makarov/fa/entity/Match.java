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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    private Season season;

    @Temporal(TemporalType.DATE)
    @Column(name = "utc_date")
    private Date utcDate;

    @Column(name = "status")
    private String status;

    @Column(name = "matchday")
    private int matchDay;

    @Column(name = "stage")
    private String stage;

    @Column(name = "group1")
    private String group;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @OneToOne(cascade = CascadeType.ALL)
    private Score score;




}
