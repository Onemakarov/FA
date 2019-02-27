package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.makarov.fa.entity.Squad;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Component
@Data
public class PlayerResource {

    private Long id;

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
    private List<PlayerResource> squad;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}

        if (obj == null || obj.getClass() != this.getClass()) {return false;}

        PlayerResource guest = (PlayerResource) obj;

        return id.equals(guest.getId()) || name.equals(guest.getName());
    }
}
