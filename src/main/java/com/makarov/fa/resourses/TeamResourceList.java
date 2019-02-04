package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class TeamResourceList {

    @JsonProperty
    @JsonAlias(value = "teams")
    private List<TeamResource> teamResourceList;
}
