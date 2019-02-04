package com.makarov.fa.service;

import com.makarov.fa.converter.MatchConverter;
import com.makarov.fa.dao.MatchDao;
import com.makarov.fa.resourses.MatchResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatchService {

    private final MatchDao matchDao;

    private final MatchConverter matchConverter;

    @Autowired
    public MatchService(MatchDao matchDao, MatchConverter matchConverter) {

        this.matchDao = matchDao;
        this.matchConverter = matchConverter;
    }

    @Transactional
    public void addMatch(MatchResource matchResource) {

        matchDao.addMatch(matchConverter.toEntity(matchResource));
    }

    @Transactional
    public void addMatches(List<MatchResource> matchResources) {

        for (MatchResource matchResource : matchResources) {
            addMatch(matchResource);
        }
    }
}
