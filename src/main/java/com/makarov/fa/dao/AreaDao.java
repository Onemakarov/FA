package com.makarov.fa.dao;

import com.makarov.fa.entity.Area;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AreaDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void addArea(Area area) {
        entityManager.persist(area);
    }
}
