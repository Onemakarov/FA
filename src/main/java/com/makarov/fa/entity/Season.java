package com.makarov.fa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "seasons")
public class Season extends AuditEntity {

    @Id
    private Long id;


}
