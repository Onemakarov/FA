package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "areas")
@Data
public class Area {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    public void setId(Long id) {
        this.id = id;
    }
}
