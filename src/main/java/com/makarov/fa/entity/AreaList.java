package com.makarov.fa.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class AreaList {

    @JsonProperty
    @JsonAlias(value = "area")
    private List<Area> areaList;
}
