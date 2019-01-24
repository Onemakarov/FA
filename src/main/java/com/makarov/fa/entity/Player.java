package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "players")
@Data
public class Player {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "country_of_birth")
    private String countryOfBirth;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "position")
    private String position;

    @Column(name = "shirt_number")
    private int shirtNumber;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;
}
