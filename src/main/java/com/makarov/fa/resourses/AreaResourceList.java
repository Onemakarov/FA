package com.makarov.fa.resourses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.makarov.fa.entity.Area;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Data
public class AreaResourceList {

    @JsonProperty
    @JsonAlias(value = "area")
    private List<AreaResource> areaResourceList;
}
