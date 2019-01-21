package com.makarov.fa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "seasons")
public class Season extends AuditEntity {

    @Id
    private long id;
}
