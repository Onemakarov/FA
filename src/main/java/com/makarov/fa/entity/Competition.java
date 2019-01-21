package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "competitions")
@Data
public class Competition extends AuditEntity {

    @Id
    private long Id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;

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

    public Area getArea() {
        return area;
    }
}
