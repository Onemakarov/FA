package com.makarov.fa.converter;

import com.makarov.fa.entity.Area;
import com.makarov.fa.resourses.AreaResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AreaConverter {

    public Area toEntity(AreaResource areaResource) {

        Area area = new Area();

        area.setId(areaResource.getId());
        area.setName(areaResource.getName());
        return area;
    }

    public List<Area> toEntityList(List<AreaResource> areaResources) {

        List<Area> areas = new ArrayList<>();

        for (AreaResource areaResource : areaResources) {
            areas.add(toEntity(areaResource));
        }
        return areas;
    }

    public AreaResource toResource(Area area) {

        AreaResource areaResource = new AreaResource();

        areaResource.setId(area.getId());
        areaResource.setName(area.getName());
        return areaResource;
    }

    public List<AreaResource> toResourceList(List<Area> areas) {

        List<AreaResource> areaResources = new ArrayList<>();

        for (Area area : areas) {
            areaResources.add(toResource(area));
        }
        return areaResources;
    }
}
