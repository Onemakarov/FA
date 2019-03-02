package com.makarov.fa.dao;

import com.makarov.fa.entity.Area;
import com.makarov.fa.entity.Competition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AreaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addArea(Area area) {
        entityManager.persist(area);
    }

    public List<Area> getAllAreas() {
        return entityManager.createQuery("SELECT a FROM Area a", Area.class)
                .getResultList();
    }

    public Area getAreaById(Long areaId) {
        return entityManager.find(Area.class, areaId);
    }
}
