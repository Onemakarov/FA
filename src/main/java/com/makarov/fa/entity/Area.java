package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "areas")
@Data
public class Area {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;
}
