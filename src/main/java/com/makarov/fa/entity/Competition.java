package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "competitions")
@Data
public class Competition extends AuditEntity {

    @Id
    private long Id;
}
