package com.makarov.fa.service;

import com.makarov.fa.apiclient.FootballDataClient;
import com.makarov.fa.dao.AreaDao;
import com.makarov.fa.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaService {

    private AreaDao areaDao;

    private FootballDataClient footballDataClient;

    @Autowired
    public AreaService(AreaDao areaDao, FootballDataClient footballDataClient) {
        this.areaDao = areaDao;
        this.footballDataClient = footballDataClient;
    }

    @Transactional
    public void addArea(Area area) {
        areaDao.addArea(area);
    }

    @Transactional
    public void addAllAreas() {
    }
}
