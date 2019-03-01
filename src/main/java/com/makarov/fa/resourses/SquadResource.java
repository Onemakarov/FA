package com.makarov.fa.resourses;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SquadResource {

    private List<PlayerResource> playerResourceList;
}
