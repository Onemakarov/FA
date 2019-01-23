package com.makarov.fa.entity;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "areas")
@Data
public class Area {

    @Id
    private long id;

    @Column(name = "name")
    private String name;
}
