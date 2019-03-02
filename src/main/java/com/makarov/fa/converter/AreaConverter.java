package com.makarov.fa.converter;

import com.makarov.fa.entity.Area;
import com.makarov.fa.resourses.AreaResource;
import org.springframework.stereotype.Component;

@Component
public class AreaConverter {

    public Area toEntity(AreaResource areaResource) {

        Area area = new Area();

        area.setId(areaResource.getId());
        area.setName(areaResource.getName());
        return area;
    }
}
