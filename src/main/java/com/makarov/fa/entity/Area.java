package com.makarov.fa.entity;

import lombok.Data;
import org.springframework.context.ApplicationEvent;
import sun.applet.AppletListener;


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
