package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlayerResourceList {

    @Getter
    @JsonProperty
    @JsonAlias(value = "squad")
    private List<PlayerResource> playerResourceList;
}
