package com.makarov.fa.entity;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "teams")
@Data
public class Team {

    @Id
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Area area;

    @ManyToMany
    private List<Competition> activeCompetitions;

    @Column(name = "name")
    private String name;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "tla")
    private String tla;

    @Column(name = "crest_url")
    private String crestUrl;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "website")
    private String website;

    @Column(name = "email")
    private String email;

    @Column(name = "founded")
    private int founded;

    @Column(name = "club_colors")
    private String clubColors;

    @Column(name = "venue")
    private String venue;

    @ManyToMany
    private List<Player> squad;

    @Column(name = "lastUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
}
