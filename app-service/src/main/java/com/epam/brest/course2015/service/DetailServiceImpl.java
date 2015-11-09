package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.DetailDao;
import com.epam.brest.course2015.domain.Detail;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pavel on 11/7/15.
 */

@Transactional
public class DetailServiceImpl implements DetailService {

    private DetailDao detailDao;

    public void setDetailDao(DetailDao detailDao) {
        this.detailDao = detailDao;
    }

    @Override
    public List<Detail> getAllDetails() {
        return detailDao.getAllDetails();
    }

    @Override
    public void deleteDetail(Integer detailId) {
        detailDao.deleteDetail(detailId);
    }

    @Override
    public void updateDetail(Detail detail) {
        detailDao.updateDetail(detail);
    }

    @Override
    public Integer addDetail(Detail detail) {
        return detailDao.addDetail(detail);
    }

    @Override
    public Detail getDetailById(Integer detailId) {
        return detailDao.getDetailById(detailId);
    }
}