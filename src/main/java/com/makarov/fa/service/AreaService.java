package com.makarov.fa.service;

import com.makarov.fa.dao.AreaDao;
import com.makarov.fa.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaService {

    private AreaDao areaDao;

    @Autowired
    public AreaService(AreaDao areaDao) {
        this.areaDao = areaDao;
    }

    @Transactional
    public void addArea(Area area) {
        areaDao.addArea(area);
    }

    @Transactional
    public void addAreas(List<Area> areas) {
        for (Area area : areas) {
            addArea(area);
        }
    }

    @Transactional
    public Area getAreaById(Long id) {
        return areaDao.getAreaById(id);
    }

    @Transactional
    public List<Area> getAllAreas() {
        return areaDao.getAllAreas();
    }
}
