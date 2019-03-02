package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AreaResourceList {

    @JsonProperty
    @JsonAlias(value = "area")
    private List<AreaResource> areaResourceList;
}
