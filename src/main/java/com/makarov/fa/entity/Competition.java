package com.makarov.fa.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "competitions")
@Data
public class Competition extends AuditEntity {

    @Id
    private Long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;

    @OneToOne(cascade = CascadeType.ALL)
    private Season currentSeason;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "emblem_url")
    private String emblemUrl;

    @Column(name = "plan")
    private String plan;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Season> seasons;
}
