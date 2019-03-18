package com.makarov.fa.resourses;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SquadResource implements Resource {

    private List<PlayerResource> playerResourceList;
}
