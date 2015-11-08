package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.DetailDao;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pavel on 11/7/15.
 */

@Transactional
public class DetailServiceImpl implements DetailService {

    private DetailDao detailDao;

    public void setDetailDao(DetailDao detailDao) {
        this.detailDao = detailDao;
    }

}