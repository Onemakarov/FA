package com.makarov.fa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name ="squad")
@Data
public class Squad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "squad_player",
            joinColumns = {@JoinColumn(name = "squad_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id")}
    )
    private List<Player> players;
}
