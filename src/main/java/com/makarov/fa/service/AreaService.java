package com.makarov.fa.service;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.converter.AreaConverter;
import com.makarov.fa.dao.AreaDao;
import com.makarov.fa.entity.Area;
import com.makarov.fa.resourses.AreaResource;
import com.makarov.fa.resourses.AreaResourceList;
import com.makarov.fa.resourses.CompetitionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaService {

    private AreaDao areaDao;

    private FootballDataClient footballDataClient;

    private AreaConverter areaConverter;

    @Autowired
    public AreaService(AreaDao areaDao, FootballDataClient footballDataClient, AreaConverter areaConverter) {
        this.areaDao = areaDao;
        this.footballDataClient = footballDataClient;
        this.areaConverter = areaConverter;
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
