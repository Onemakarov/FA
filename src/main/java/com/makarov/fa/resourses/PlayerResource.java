package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.makarov.fa.entity.Squad;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@Data
public class PlayerResource {

    private String name;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String countryOfBirth;

    private String nationality;

    private String position;

    private int shirtNumber;

    private Date lastUpdated;

    @JsonAlias(value = "squad")
    private SquadResource squad;
}
