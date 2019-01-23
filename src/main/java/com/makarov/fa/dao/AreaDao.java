package com.makarov.fa.dao;

import com.makarov.fa.entity.Area;
import com.makarov.fa.entity.Competition;
import com.makarov.fa.entity.CompetitionList;
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

    public void addCompetition(Competition competition) {
        entityManager.persist(competition);
    }

    public List<Area> getAllAreas() {
        return entityManager.createQuery("SELECT a FROM Area a", Area.class)
                .getResultList();
    }
}
