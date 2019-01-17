package com.makarov.fa.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Score extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
